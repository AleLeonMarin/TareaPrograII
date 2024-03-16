package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;
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
import javax.imageio.ImageIO;

public class CameraController extends Controller implements Initializable { 
    
    @FXML
    private MFXButton startButton;
    @FXML
    private ImageView previewImageView;
    @FXML
    private MFXButton TakePic;
    @FXML
    private MFXButton LookPhoto;

    private Webcam webcam;
    private boolean isRunning;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webcam = Webcam.getDefault();
        if (webcam == null) {
            System.err.println("No webcam found!");
            startButton.setDisable(true); // Disable button if no webcam
        } else {
            webcam.setViewSize(new Dimension(WebcamResolution.VGA.getWidth(), WebcamResolution.VGA.getHeight()));
        }
    }

    @FXML
    public void handleStartStopAction(ActionEvent event) {
        if (!isRunning) {
            startCameraPreview();
            startButton.setText("Stop");
        } else {
            stopCameraPreview();
            startButton.setText("Start");
        }
        isRunning = !isRunning;
    }
    @FXML
    public void HandleTake(ActionEvent event){
      //  ImageIO.write(webcam.getImage(), 'JPG', new File("PhotoTaken.jpg"));
    }

    private void startCameraPreview() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    webcam.open();
                    while (isRunning) {
                        BufferedImage image = webcam.getImage();
                        Image javafxImage = SwingFXUtils.toFXImage(image, null);
                        Platform.runLater(() -> previewImageView.setImage(javafxImage));
                        Thread.sleep(63); // Update preview at 30 FPS (adjust as needed)
                    }
                    webcam.close(); // Close the webcam directly (no SwingUtilities)
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    private void stopCameraPreview() {
        webcam.close();
    }

    @Override
    public void initialize() {
        
    }
    
}
