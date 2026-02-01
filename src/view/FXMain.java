package view;

import javafx.application.Application;
import javafx.stage.Stage;
import view.GUI.AppNavigator;

public class FXMain extends Application {
    @Override
    public void start(Stage primaryStage) {

        AppNavigator.init(primaryStage);
        AppNavigator.showProgramSelect();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
