package cr.ac.una.tareav2;

import cr.ac.una.tareav2.util.FlowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        
        FlowController.getInstance().InitializeFlow(stage,  null);
        stage.getIcons().add(new Image("cr/ac/una/tareav2/resources/PrincipalBackGround.jpg"));
        stage.setTitle("Tours");
        FlowController.getInstance().goViewInWindow("LogInView");
    }

    public static void main(String[] args) {
        launch();
    }

}