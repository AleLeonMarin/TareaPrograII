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
import javax.imageio.ImageIO;
public class CameraController extends Controller implements Initializable {

    @FXML
    private MFXButton SetCameraState;
    @FXML
    private ImageView previewImageView;
    @FXML
    private MFXButton TakePic;
    @FXML
    private MFXButton CheckPic;

    private Webcam webcam;
    private boolean isRunning;

    @Override
    public void initialize() {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webcam = Webcam.getDefault();
        if (webcam == null) {
            System.err.println("No webcam found!");
            SetCameraState.setDisable(true); // Disable button if no webcam
        } else {
            webcam.setViewSize(new Dimension(WebcamResolution.VGA.getWidth(), WebcamResolution.VGA.getHeight()));
        }
    }

    @FXML
    private void handleCloseCamera(ActionEvent event){
        if (!isRunning) {
            startCameraPreview();
            SetCameraState.setText("Stop");
        } else {
            stopCameraPreview();
            SetCameraState.setText("Start");
        }
        isRunning = !isRunning;
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
                        Thread.sleep(103); // Update preview at 30 FPS (adjust as needed)
                    }
                    webcam.close();
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

    @FXML
    public void onHandleTakePic(ActionEvent event) throws IOException {
        try {
            // Capture the image
            BufferedImage image = webcam.getImage();
            // Generate a unique filename (refer to your existing code for this)
            ImageIO.write(image, "JPG", new File("New_photo.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error capturing image!");
        }
    }
}
