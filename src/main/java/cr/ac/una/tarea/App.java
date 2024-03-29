package cr.ac.una.tarea;

import cr.ac.una.tarea.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.setTitle("Cooperativa UNA-KIDS");
        FlowController.getInstance().goViewInWindow("LogInView");


    }

    public static void main(String[] args) {

        launch();
    }

}