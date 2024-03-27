package cr.ac.una.tarea.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea.Associated;
import cr.ac.una.tarea.util.FlowController;
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
    private volatile boolean runPreview = true;

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
                }finally {
                    stopCameraPreview();
                }
                return null;
            }
        };
        new Thread(task).start();
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
            String fileName = asociado.getFolio() + ".jpg";
            ImageIO.write(image, "JPG", new File(fileName));
            // SeePhoto.setDisable(false);

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
        try {
          startCameraPreview();
            startCameraPreview();
            BufferedImage image = webcam.getImage();
            String fileName = asociado.getFolio() + ".jpg";
            ImageIO.write(image, "JPG", new File(fileName));
        } catch (Exception e) {
            logger.error("Error restarting camera preview", e);
        } finally {
            btnTakePhoto.setDisable(false);
            btnTakePhoto.setVisible(true);
            stopCameraPreview();
        }
    }

    @FXML
    public void onActionBtnGuardar(ActionEvent event){

    }

    @FXML
    public void onActionBtnSalir(ActionEvent event){
        ((Stage) BtnSalir.getScene().getWindow()).close();
    }




}

