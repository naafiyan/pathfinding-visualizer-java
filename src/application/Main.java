package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        PaneOrganizer org = new PaneOrganizer();
        primaryStage.setTitle("Pathfinding Visualizer");
        primaryStage.setScene(new Scene(org.getRoot(), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
