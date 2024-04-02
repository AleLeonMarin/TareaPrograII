package cr.ac.una.tarea.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cr.ac.una.tarea.Associated;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
public class CameraController extends Controller implements Initializable {

    public Button btnTakePhoto;
    public MFXButton btnRetake;
    public ImageView PhotoTaken;
    public MFXButton btnSavePic;
    public MFXButton btnExitCam;
    @FXML
    private ImageView previewImageView;
    private Webcam webcam;
    private static final Logger logger = LoggerFactory.getLogger(CameraController.class);
    private ScheduledExecutorService executor;
    Image defaultImage = new Image(getClass().getResourceAsStream("/cr/ac/una/tarea/resources/PreviewPhoto.jpeg"));
    Associated asociados = new Associated();

    public void initialize() {

    }

    // Se encarga de iniciar la vista previa de la camara mediante
    // tareas de un hilo de manera programada
    private void startCameraPreview() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            Image image = SwingFXUtils.toFXImage(webcam.getImage(), null);
            previewImageView.setImage(image); // Muestra la camara en el imageView 'PreviewImageView'
        }, 0, 33, TimeUnit.MILLISECONDS); // 30 FPS
    }

    // Cierra y apaga la camara del sistema
    private void stopCameraPreview() {
        executor.shutdown();
        webcam.close();
    }

    // Captura la imagen y le asigna el folio del asociado a la foto
    @FXML
    private void takePicture() {
        try {
            // crea el archivo y el tipo de formato
            File file = new File(String.format(asociados.getFolio() + ".jpg", System.currentTimeMillis()));
            ImageIO.write(webcam.getImage(), "JPG", file);
            logger.info("Image saved successfully: " + file);
            // Funcion para mostrar la foto tomada
            displayCapturedImage(file);
        } catch (IOException e) {
            logger.error("Error saving image: ", e);
        }
        btnRetake.setDisable(false);
        btnSavePic.setDisable(false);
        btnTakePhoto.setDisable(true);
    }

    // Se encarga de mostrar la imagen capturada en el ImageView llamado PhotoTaken
    private void displayCapturedImage(File file) {
        try {
            Image image = new Image(file.toURI().toString());
            PhotoTaken.setImage(image);
        } catch (Exception e) {
            logger.error("Error displaying captured image: ", e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       try{
           btnRetake.setDisable(true);
           btnSavePic.setDisable(true);

           //  Investiga la existencia de alguna camara en el sistema
           webcam = Webcam.getDefault();
           // Establece la resolucion de la vista previa de la camara en VGA
           webcam.setViewSize(WebcamResolution.VGA.getSize());

           webcam.open();
           startCameraPreview();
       } catch (Exception ex){
           logger.error("Camera not found: ", ex);
       }
    }

    // Funcion para volver a capturar la imagen
    public void onActionBtnRet(ActionEvent event) {
        PhotoTaken.setImage(null);
        btnRetake.setDisable(true);
        btnSavePic.setDisable(true);
        btnTakePhoto.setDisable(false);
        PhotoTaken.setImage(defaultImage);
    }

    // Guarda la foto y cierra la ventana de camera
    public void onActionBtnSavePic(ActionEvent event) {
        btnTakePhoto.setDisable(true);
        btnRetake.setDisable(true);
        stopCameraPreview();
        new Mensaje().showModal(Alert.AlertType.ERROR, "Vista Camara", getStage(), "Foto guardada exitosamente");
        ((Stage) btnExitCam.getScene().getWindow()).close();
    }

    public void onActionBtnExitCam(ActionEvent event) {
        stopCameraPreview();
        ((Stage) btnExitCam.getScene().getWindow()).close();
    }
}