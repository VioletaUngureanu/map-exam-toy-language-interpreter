package view.GUI;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.programState.ExecutionStack;
import model.programState.IExecutionStack;
import model.statement.IStatement;
import programExamples.a3Examples.ExamplesA3;
import programExamples.a4Examples.ExamplesA4;
import programExamples.a5Examples.ExamplesA5;
import programExamples.conditionalAssignmentExample.ConditionalAssignmentExample;
import programExamples.countDownLatchExample.LatchExample;

import java.util.List;

public class ProgramSelectionView {
    List<IStatement> programList;


    public ProgramSelectionView() {
        ExamplesA5 examples = new ExamplesA5();
        ExamplesA4 examplesA4 = new ExamplesA4();
        ExamplesA3 examplesA3 = new ExamplesA3();
        IStatement ex2 = examplesA4.getExRead();
        IStatement ex1 = examples.getExample1();
        IStatement ex3 = examplesA4.getExWrite();
        IStatement ex4 = examplesA4.getEx1GarbageCollector();
        IStatement ex5 = examplesA4.getEx2GarbageCollector();
        IStatement ex6 = examplesA4.getExHeapAlloc();
        IStatement ex7 = examplesA3.getExample1();
        IStatement ex8 = examplesA4.getExWhile();

        ConditionalAssignmentExample conditionalAssignmentStatement = new ConditionalAssignmentExample();
        IStatement conditionalAssignmentStatementExample = conditionalAssignmentStatement.getExample();

        LatchExample latchExample = new LatchExample();
        IStatement latchStatement = latchExample.getExample();
        this.programList = List.of(
                ex1,
                ex2, ex3, ex4, ex5, ex6, ex7, ex8,
                conditionalAssignmentStatementExample,
                latchStatement
        );
    }


    public Scene createScene() {
        Label title = new Label("Select a program");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ListView<String> listView = new ListView<>();
        for (IStatement program : programList) {
            listView.getItems().add(program.toString());
        }

        Button startButton = getButton(listView);

        VBox root = new VBox(10, title, listView, startButton);
        root.setPadding(new Insets(12));
        VBox.setVgrow(listView, Priority.ALWAYS);

        return new Scene(root, 900, 600);
    }

    private Button getButton(ListView<String> listView) {
        Button startButton = new Button("Start");
        startButton.setDefaultButton(true);

        startButton.setOnAction(e -> {
            int idx = listView.getSelectionModel().getSelectedIndex();
            if (idx < 0) {
                new Alert(Alert.AlertType.WARNING, "Please select a program first.").showAndWait();
                return;
            }

            IStatement selected = programList.get(idx);

            try {
                Controller ctrl = CreatorOfController.buildControllerForStatement(selected);
                AppNavigator.showMainDashboard(ctrl);
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        });
        return startButton;
    }
}
