package cr.ac.una.tareav2;

import cr.ac.una.tareav2.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    final String MAIN_VIEW = "PaginaPrincipal";

    /*
     * II. Cambiar manera de usar las rutas
     * Igual aca, las rutas deberian estar en alguna variable o constante
     */
    @Override
    public void start(Stage stage) throws IOException {

        FlowController.getInstance().InitializeFlow(stage, null);
        stage.getIcons().add(new Image("cr/ac/una/tareav2/resources/PrincipalBackGround.jpg"));
        stage.setTitle("Equilibrio Tours");
        FlowController.getInstance().goViewInWindow(MAIN_VIEW);
    }

    public static void main(String[] args) {
        launch();
    }

}