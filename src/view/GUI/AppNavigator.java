package view.GUI;

import controller.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.GUI.MainDashboardView;

public class AppNavigator {
    private static Stage stage;
    public static void init(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Toy Language Interpreter");
    }

    public static void showProgramSelect() {
        Scene newScene = new ProgramSelectionView().createScene();
        stage.setScene(newScene);
        stage.show();
    }
    public static void showMainDashboard(Controller controller) {
        MainDashboardController uiCtrl = new MainDashboardController(controller);
        MainDashboardView view = new MainDashboardView();
        Scene scene = view.createScene(uiCtrl);
        stage.setScene(scene);
        stage.show();

        uiCtrl.initAfterView();
    }
}
