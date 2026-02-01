package view.GUI;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.programState.ProgramState;
import model.value.IValue;

import java.util.*;
import java.util.stream.Collectors;

public class MainDashboardController {

    private final Controller ctrl;
    private TextField nrPrgStatesField;
    private ListView<Integer> prgIdList;
    private TableView<HeapRow> heapTable;
    private ListView<String> outList;
    private ListView<String> fileTableList;
    private TableView<SymbolTableRow> symTable;
    private ListView<String> exeStackList;
    private Button oneStepButton;
    private Button backButton;

    private ProgramState lastDisplayedProgram = null;


    public MainDashboardController(Controller ctrl) {
        this.ctrl = ctrl;
    }

    private void wireEvents(){


        prgIdList.getSelectionModel().selectedItemProperty().addListener((obs, oldVersion, newVersion) -> {

            if (newVersion != null) refreshSelectedProgramViews(newVersion);
        });

        oneStepButton.setOnAction(e -> {
            runOneStepAsync();
        });

        backButton.setOnAction(e -> {
            AppNavigator.showProgramSelect();
        });
    }


    private void runOneStepAsync() {
        oneStepButton.setDisable(true);

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                ctrl.oneStepGui();
                return null;
            }
        };

        task.setOnSucceeded(e -> {
            refreshAll();
            oneStepButton.setDisable(false);
        });

        task.setOnFailed(e -> {
            Throwable ex = task.getException();
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, ex == null ? "Unknown error" : ex.toString()).showAndWait();
            refreshAll();
            oneStepButton.setDisable(false);
        });
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    private void refreshAll() {
        List<ProgramState> programs = ctrl.getPrograms();

        if (!programs.isEmpty()) {

            nrPrgStatesField.setText(String.valueOf(programs.size()));

            List<Integer> ids = programs.stream()
                    .map(ProgramState::id)
                    .collect(Collectors.toList());
            prgIdList.setItems(FXCollections.observableArrayList(ids));

            Integer selectedProgram = prgIdList.getSelectionModel().getSelectedItem();
            if (selectedProgram == null || !ids.contains(selectedProgram)) {
                prgIdList.getSelectionModel().select(0);
                selectedProgram = prgIdList.getSelectionModel().getSelectedItem();
            }
            Integer finalSelectedProgram = selectedProgram;
            ProgramState current = programs.stream()
                    .filter(p -> p.id() == finalSelectedProgram)
                    .findFirst()
                    .orElse(programs.get(0));

            lastDisplayedProgram = current;

            setSharedViews(current);
            refreshSelectedProgramViews(current.id());
            oneStepButton.setDisable(false);

        } else if (lastDisplayedProgram != null) {
            nrPrgStatesField.setText("0");
            prgIdList.getItems().clear();
            setSharedViews(lastDisplayedProgram);
            exeStackList.getItems().clear();
            oneStepButton.setDisable(true);
        }
    }


    private void setSharedViews(ProgramState prg) {

        Map<Integer, IValue> heapMap = prg.heap().getContent();
        List<HeapRow> heapRows = heapMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new HeapRow(e.getKey(), String.valueOf(e.getValue())))
                .collect(Collectors.toList());
        heapTable.getItems().setAll(heapRows);

        outList.getItems().setAll(prg.out().toString());

        fileTableList.getItems().setAll(toStringList(prg.fileTable()));
    }

    private void refreshSelectedProgramViews(int id) {
        ProgramState prg = ctrl.getPrograms().stream()
                .filter(p -> p.id() == id)
                .findFirst()
                .orElse(null);

        if (prg == null) {
            symTable.getItems().clear();
            exeStackList.getItems().clear();
            return;
        }

        List<SymbolTableRow> symRows = prg.symbolTable().getAllKeys().stream()
                .sorted()
                .map(k -> new SymbolTableRow(k, String.valueOf(prg.symbolTable().lookup(k))))
                .collect(Collectors.toList());
        symTable.getItems().setAll(symRows);

        exeStackList.getItems().setAll(splitLines(prg.executionStack().toString()));
    }

    private static List<String> splitLines(String s) {
        if (s == null || s.isEmpty()) return List.of();
        return Arrays.stream(s.split("\\R"))
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
    }


    private static List<String> toStringList(Object obj) {
        if (obj == null) return List.of();
        return splitLines(obj.toString());
    }

    public void bindControls(TextField nrPrgStatesField, ListView<Integer> prgIdList, TableView<HeapRow> heapTable, ListView<String> outList, ListView<String> fileTableList, TableView<SymbolTableRow> symTable, ListView<String> exeStackList, Button oneStepButton, Button backButton) {
        this.nrPrgStatesField = nrPrgStatesField;
        this.prgIdList = prgIdList;
        this.heapTable = heapTable;
        this.outList = outList;
        this.fileTableList = fileTableList;
        this.symTable = symTable;
        this.exeStackList = exeStackList;
        this.oneStepButton = oneStepButton;
        this.backButton = backButton;

        wireEvents();
    }

    public void initAfterView() {
        
        refreshAll();
    }
}
