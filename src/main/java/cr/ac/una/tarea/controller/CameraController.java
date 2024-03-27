package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.Associated;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea.Associated;
import javafx.fxml.Initializable;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.Flow;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
public class CameraController extends Controller implements Initializable {

    @FXML
    private ImageView previewImageView;
    @FXML
    private MFXButton btnTakePhoto;
    @FXML
    private MFXButton btnRetake;
    @FXML
    private MFXButton BtnGuardar;
    @FXML
    private MFXButton BtnSalir;
    private Webcam webcam;
    private static final Logger logger = LoggerFactory.getLogger(CameraController.class);
    private boolean runPreview = true;
    private volatile boolean isPreviewThreadRunning = false;
    Associated asociado = new Associated();
    @Override
    public void initialize() {
        BtnGuardar.setVisible(false);
        BtnGuardar.setDisable(true);
        btnRetake.setVisible(false);
        btnRetake.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            webcam = Webcam.getDefault(); // Assign the default webcam to the variable
            webcam.setViewSize(new Dimension(WebcamResolution.VGA.getWidth(), WebcamResolution.VGA.getHeight()));
            startCameraPreview();
           // SeePhoto.setDisable(true);
        } catch (Exception e){
            logger.error("Failed to open the camera", e);
        }
    }

    private void startCameraPreview() {
        Task<Void> task;
        task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    webcam.open();
                    while(runPreview){
                        BufferedImage image = webcam.getImage();
                        Image javafxImage = SwingFXUtils.toFXImage(image, null);
                        Platform.runLater(() -> previewImageView.setImage (javafxImage));

                        synchronized (this) {
                            wait(10); // Wait for 30 milliseconds
                        }
                    }
                   // TakePic.setDisable(true);
                } catch (Exception e) {
                    logger.error("Camera stopped", e);
                } finally {
                    stopCameraPreview();
                    isPreviewThreadRunning = false;
                }
                return null;
            }
        };
        new Thread(task).start();
        isPreviewThreadRunning = true;
    }
    private void stopCameraPreview() {
        runPreview = false;
        webcam.close();
    }
    @FXML
    public void onActionBtnTakePhoto(ActionEvent event) {
        try {
            startCameraPreview();
            BufferedImage image = webcam.getImage();

            // Check if folio is empty or null
            String folio = asociado.getFolio();
            if (folio == null || folio.isEmpty()) {
                // Handle empty folio (provide default or prompt user)
                logger.warn("Folio is empty, using default filename.");
                folio = "default_image";
            }
                System.out.println("Folio retrieved: " + folio);

                String fileName = folio + ".jpg";

                ImageIO.write(image, "JPG", new File(fileName));




        } catch (Exception e) {
            logger.error("Error capturing image..", e);
        } finally {
            //  startCameraPreview();
            stopCameraPreview();
            BtnGuardar.setDisable(false);
            BtnGuardar.setVisible(true);
            btnRetake.setDisable(false);
            btnRetake.setVisible(true);
            btnTakePhoto.setDisable(true);
            btnTakePhoto.setVisible(false);
        }
    }
    @FXML
    public void onActionBtnRetake(ActionEvent event){
        runPreview = true;
        startCameraPreview(); // Restart the preview loop (may not create a new thread)

        // Check if the camera preview thread is likely running
        if (isPreviewThreadRunning) {
            try {
                BufferedImage image = webcam.getImage();
                String fileName = asociado.getFolio() + ".jpg";
                ImageIO.write(image, "JPG", new File(fileName));
                logger.info("Image retaken and saved: " + fileName);
            } catch (Exception e) {
                logger.error("Error retaking image", e);
            }
        } else {
            // Handle the case where preview thread isn't running (optional)
            logger.warn("Camera preview not yet ready for capturing image.");
        }
            btnTakePhoto.setDisable(false);
            btnTakePhoto.setVisible(true);
    }

    @FXML
    public void onActionBtnGuardar(ActionEvent event) throws IOException {
        try {
            BufferedImage image = webcam.getImage();
            String fileName = asociado.getFolio() + ".jpg";
            ImageIO.write(image, "JPG", new File(fileName));
            logger.info("Image saved successfully: " + fileName); // Log success message
        } catch (IOException e) {
            logger.error("Error saving image: ", e);  // Log error message with exception
        } finally {
            stopCameraPreview();
        }

    }

    @FXML
    public void onActionBtnSalir(ActionEvent event){
        ((Stage) BtnSalir.getScene().getWindow()).close();
    }

}

