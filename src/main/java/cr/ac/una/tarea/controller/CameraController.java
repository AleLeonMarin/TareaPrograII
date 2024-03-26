package cr.ac.una.tarea.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.Initializable;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
// import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class CameraController extends Controller implements Initializable {

    @FXML
    private ImageView previewImageView;
    @FXML
    private MFXButton TakePic;
 
    private Webcam webcam;

    @Override
    public void initialize() {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            webcam = Webcam.getDefault(); // Assign the default webcam to the variable
            webcam.setViewSize(new Dimension(WebcamResolution.VGA.getWidth(),WebcamResolution.VGA.getHeight()));
            TakePic.setDisable(false);
            startCameraPreview();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error initializing camera!");
        }
    }
    
    private void startCameraPreview() {
        Task<Void> task;
        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    webcam.open();
                    while(true){
                        BufferedImage image = webcam.getImage();
                        Image javafxImage = SwingFXUtils.toFXImage(image, null);
                        Platform.runLater(() -> previewImageView.setImage (javafxImage));
                        Thread.sleep(30);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Camera closed!");
                }  
                return null;
            }
        };
        new Thread(task).start();
    }

    private void stopCameraPreview() {
       webcam.close();    
    }
    
    @FXML
    public void onHandleTakePic(ActionEvent event) throws IOException {
        try {
            BufferedImage image = webcam.getImage();
            String filename = "New_photo.jpg";
            ImageIO.write(image, "JPG", new File(filename));
            Stage stage = (Stage) previewImageView.getScene().getWindow(); // Get the current stage
            stage.close(); // Close the stage
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error capturing image!");
        }
       stopCameraPreview();
    }
}
