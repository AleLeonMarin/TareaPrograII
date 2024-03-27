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
    private MFXButton TakePic;
    @FXML
    private MFXButton SeePhoto;
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
        SeePhoto.setVisible(false);
        SeePhoto.setDisable(true);

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

    public void TakePic() {
        try {
            BufferedImage image = webcam.getImage();
            nameImage(image);
            ImageIO.write(image, "JPG", new File("./"));
            // SeePhoto.setDisable(false);

        } catch (Exception e) {
            logger.error("Error capturing image..", e);
        } finally {
            stopCameraPreview();
            BtnGuardar.setDisable(false);
            BtnGuardar.setVisible(true);
            SeePhoto.setDisable(false);
            SeePhoto.setVisible(true);
        }
    }

    private void stopCameraPreview() {
        runPreview = false;
        webcam.close();
    }

    public void nameImage(BufferedImage image) {
        String filename = asociado.getFolio() + ".jpg";
    }
   @FXML
    public void onActionTakePic(ActionEvent event) {
        TakePic();
    }
    private void closeCameraWindow() {
       // FlowController.getInstance().salir();
    }
    @FXML
    public void onActionSeePic(ActionEvent event){
        closeCameraWindow();
        TakePic();
    }

    @FXML
    public void onActionBtnGuardar(ActionEvent event){

    }

    @FXML
    public void onActionBtnSalir(ActionEvent event){
        ((Stage) BtnSalir.getScene().getWindow()).close();
    }




}

