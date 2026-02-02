package view.GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainDashboardView {

    public Scene createScene(MainDashboardController uiCtrl) {

        TextField nrProgramStatesField = new TextField();
        nrProgramStatesField.setEditable(false);

        ListView<Integer> prgIdList = new ListView<>();

        TableView<HeapRow> heapTable = new TableView<>();
        TableView<SymbolTableRow> symTable = new TableView<>();
        TableView<LatchTableRow> latchTable = new TableView<>();

        ListView<String> outList = new ListView<>();
        ListView<String> fileTableList = new ListView<>();
        ListView<String> exeStackList = new ListView<>();

        Button oneStepButton = new Button("Run one step");
        Button backButton = new Button("Back to program list");

        buildHeapTableColumns(heapTable);
        buildSymTableColumns(symTable);
        buildLatchTableColumns(latchTable);


        HBox topBar = new HBox(10,
                new Label("Nr. PrgStates:"),  nrProgramStatesField, oneStepButton, backButton
        );
        topBar.setPadding(new Insets(10));

        VBox leftPane = new VBox(8, new Label("Program IDs"), prgIdList);
        leftPane.setPadding(new Insets(10));
        leftPane.setPrefWidth(180);

        VBox centerPane = new VBox(8,
                new Label("Execution Stack (top first)"), exeStackList,
                new Label("Symbol Table"), symTable,
                new Label("Latch Table"), latchTable
        );
        centerPane.setPadding(new Insets(10));
        VBox.setVgrow(exeStackList, Priority.ALWAYS);
        VBox.setVgrow(symTable, Priority.ALWAYS);

        VBox rightPane = new VBox(8,
                new Label("Heap"), heapTable,
                new Label("Out"), outList,
                new Label("File Table"), fileTableList
        );
        rightPane.setPadding(new Insets(10));
        VBox.setVgrow(heapTable, Priority.ALWAYS);
        VBox.setVgrow(outList, Priority.ALWAYS);
        VBox.setVgrow(fileTableList, Priority.ALWAYS);
        VBox.setVgrow(latchTable, Priority.ALWAYS);

        SplitPane split = new SplitPane(centerPane, rightPane);
        split.setDividerPositions(0.55);

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(leftPane);
        root.setCenter(split);

        uiCtrl.bindControls( nrProgramStatesField, prgIdList, heapTable, outList,
                fileTableList, symTable, exeStackList, oneStepButton,backButton, latchTable
        );
        return new Scene(root, 1150, 680);
    }

    private void buildHeapTableColumns(TableView<HeapRow> heapTable) {
        TableColumn<HeapRow, Integer> addrCol = new TableColumn<>("Address");
        addrCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addrCol.setPrefWidth(120);

        TableColumn<HeapRow, String> valCol = new TableColumn<>("Value");
        valCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        valCol.setPrefWidth(320);

        heapTable.getColumns().setAll(addrCol, valCol);
    }

    private void buildSymTableColumns(TableView<SymbolTableRow> symTable) {
        TableColumn<SymbolTableRow, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(160);

        TableColumn<SymbolTableRow, String> valCol = new TableColumn<>("Value");
        valCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        valCol.setPrefWidth(320);

        symTable.getColumns().setAll(nameCol, valCol);
    }

    private void buildLatchTableColumns(TableView<LatchTableRow> latchTable) {
        TableColumn<LatchTableRow, Integer> addrCol = new TableColumn<>("Location");
        addrCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        addrCol.setPrefWidth(120);

        TableColumn<LatchTableRow, String> valCol = new TableColumn<>("Value");
        valCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        valCol.setPrefWidth(320);

        latchTable.getColumns().setAll(addrCol, valCol);
    }
}
