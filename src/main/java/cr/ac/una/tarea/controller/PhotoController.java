
package cr.ac.una.tarea.controller;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.security.PrivateKey;
import java.util.ResourceBundle;

import com.sun.scenario.effect.impl.prism.PrImage;
import cr.ac.una.tarea.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class PhotoController extends Controller implements Initializable {

    @FXML
    private ImageView CheckImage;
    @FXML
    private MFXButton btnRepeatPic;
    @FXML
    private MFXButton btnSavePic;
    private File imageFileToSave; // Store the image file for saving

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void initialize() {

    }

    public void setImageToDisplay(File imageFile) {
        // Load the image into the CheckImage ImageView
        Image image = new Image(imageFile.toURI().toString());
        CheckImage.setImage(image);
    }

    @FXML
    public void handleSavePic(ActionEvent event) {
        // Implement logic to save the image using imageFileToSave
    }

    @FXML
    public void handleRepeatPic(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("Camera");
    }
    
}
