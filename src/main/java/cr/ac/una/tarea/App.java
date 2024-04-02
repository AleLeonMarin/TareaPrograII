package cr.ac.una.tarea;

import cr.ac.una.tarea.model.Cooperativa;
import cr.ac.una.tarea.util.FlowController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    Cooperativa cooperativa = new Cooperativa();

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        if (cooperativa.getName() == null || cooperativa.getLogo() == null) {
            FlowController.getInstance().goViewInWindow("LogInView");
        } else {
            FlowController.getInstance().goViewInWindow("LogInView");
            stage.setTitle(cooperativa.getName());
            stage.getIcons().add(new Image(cooperativa.getLogo()));
        }

    }

    public static void main(String[] args) {

        launch();
    }

}