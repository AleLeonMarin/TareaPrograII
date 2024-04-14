package cr.ac.una.tarea.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.imageio.ImageIO;

public class CameraController extends Controller implements Initializable {

    @FXML
    public MFXButton btnTakePhoto;
    @FXML
    public MFXButton btnRetake;
    @FXML
    public ImageView PhotoTaken;
    @FXML
    public MFXButton btnSavePic;
    @FXML
    public MFXButton btnExitCam;
    @FXML
    private ImageView previewImageView;

    private Webcam webcam;
    private ScheduledExecutorService executor;
    private static final Logger logger = LoggerFactory.getLogger(CameraController.class);
    Image defaultImage = new Image(getClass().getResourceAsStream("../resources/LogIn.jpg"));

    public void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnRetake.setDisable(true);
        btnSavePic.setDisable(true);
        try {
            // Obtiene la instancia de la clase camara y busca la camara predeterminada del sistema
            webcam = Webcam.getDefault();
            // Representa una resolucion de 640x480 pix y devolve la resolucion seleccionada
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            webcam.open();
            startCameraPreview();
            // webcam.setFlashMode(FlashMode.ON);
        } catch (Exception ex) {
            logger.error("Camera not found ", ex);
            btnTakePhoto.setDisable(true);
            btnSavePic.setDisable(true);
            btnRetake.setDisable(true);
        }
    }

    // Se encarga de iniciar la vista previa de la camara mediante
    // tareas de un hilo de manera programada
    private void startCameraPreview() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            Image image = SwingFXUtils.toFXImage(webcam.getImage(), null);
            previewImageView.setImage(image); // Muestra la camara en el imageView 'PreviewImageView'
        }, 1, 33, TimeUnit.MILLISECONDS); // 30 FPS
    }

    // Cierra y apaga la camara del sistema
    void stopCameraPreview() {
        executor.shutdown();
        webcam.close();
    }

    private void displayCapturedImage(File file) {
        try {
            /* Convierte el archivo file en cadena URI (Identificador de Recurso Uniforme) que representa la ubicación
            del archivo, y posterior el toString() lo convierte a una cadena de texto */
            Image image = new Image(file.toURI().toString());
            PhotoTaken.setImage(image);
        } catch (Exception e) {
            logger.error("Error displaying captured image: ", e);
        }
    }

    // Captura la imagen y le asigna el folio del asociado a la foto
    @FXML
    private void onActionCapturePhoto() {

        try {
            String fotos = "./Photos";
            File folder = new File(fotos);
            if (!folder.exists()) {
                folder.mkdir();
            }

            String fileName = "foto1.jpg";

            File file = new File(folder , fileName);
            // crea el archivo y el tipo de formato
            ImageIO.write(webcam.getImage(), "JPG", file);
            logger.info("Image saved successfully: {}", file);
            // Funcion para mostrar la foto tomada
            displayCapturedImage(file);
        } catch (IOException e) {
            logger.error("Error saving image: ", e);
        }

        btnRetake.setDisable(false);
        btnSavePic.setDisable(false);
        btnTakePhoto.setDisable(true);
        btnExitCam.setDisable(true);
    }

    // Funcion para volver a capturar la imagen
    @FXML
    public void onActionRetakePhoto(ActionEvent event) {
        PhotoTaken.setImage(null);
        btnRetake.setDisable(true);
        btnSavePic.setDisable(true);
        btnTakePhoto.setDisable(false);
        btnExitCam.setDisable(false);
        PhotoTaken.setImage(defaultImage);

        // Borrar foto tomada
        File file = new File(String.format("./Photos/foto1" + ".jpg", System.currentTimeMillis()));
        file.delete();
    }

    // Guarda la foto y cierra la ventana de camera
    @FXML
    public void onActionSavePhoto(ActionEvent event) {
        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Vista Camara", getStage(), "Foto guardada exitosamente");
        btnRetake.setDisable(true);
        PhotoTaken.setImage(defaultImage);
        btnTakePhoto.setDisable(false);
        btnSavePic.setDisable(true);
        stopCameraPreview();
        ((Stage) btnExitCam.getScene().getWindow()).close();
    }

    @FXML
    public void onActionExitCamera(ActionEvent event) {
        btnTakePhoto.setDisable(false);
        btnSavePic.setDisable(false);
        stopCameraPreview();
        ((Stage) btnExitCam.getScene().getWindow()).close();
    }

}