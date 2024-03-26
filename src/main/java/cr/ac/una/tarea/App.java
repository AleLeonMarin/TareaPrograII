package cr.ac.una.tarea;

import cr.ac.una.tarea.util.FlowController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    Associated asociado = new Associated();

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.setTitle("Cooperativa UNA-KIDS");
        FlowController.getInstance().goViewInWindow("LogInView");
        asociado.createFile(asociado);

    }

    public static void main(String[] args) {

        launch();
    }

}