package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.model.Associated;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import static java.util.logging.Level.*;

public class RegistrarAsociadoController extends Controller implements Initializable {

    @FXML
    private MFXButton btnFoto;

    @FXML
    private ImageView imgVFoto;

    @FXML
    private MFXTextField txfEdad;

    @FXML
    private MFXTextField txfNombre;

    @FXML
    private MFXButton btnRegistrar;

    @FXML
    private MFXTextField txfApellido;

    Associated asociado = new Associated();
    @Override
    public void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onActionShowImage(MouseEvent event) {
        LoadPhoto();
    }

    public void LoadPhoto() {
        String photoPath = "./Photos/foto1.jpg";
        File file_photo = new File(photoPath);

        if (file_photo.exists()) {
            Image image = new Image(file_photo.toURI().toString());
            imgVFoto.setImage(image);
        } else{

        }
    }

    // Associated asociado
    public void RenamePhoto(Associated asociado) {
        File file = new File("./Photos/foto1.jpg");

        if (file.exists()) {
            String filepath = file.getParent();
            String newFileName = asociado.getFolio() + ".jpg";
            File newFile = new File(filepath, newFileName);

            if (file.renameTo(newFile)) {
                asociado.Associate.add(String.valueOf(newFile));
                LoadPhoto();
            } else {
                System.out.println("Failed to rename");
            }
        } else {
            System.out.println("In-existing photo");
        }
    }

    @FXML
    void onActionBtnCamera(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("Camera");
    }

    @FXML
    void onActionBtnRegistrar(ActionEvent event) {

        try {
            if (txfNombre.getText() == null || txfNombre.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(),
                        "Debe ingresar el nombre del asociado");
            } else if (txfEdad.getText() == null || txfEdad.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(),
                        "Debe ingresar la edad del asociado");
            } else if (txfApellido.getText() == null || txfApellido.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(),
                        "Debe ingresar el apellido del asociado");
            } else if (imgVFoto.getImage() == null || imgVFoto.getImage().isError()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(),
                        "Debe tomarse la foto del asociado");
            } else {
                Associated asociado = new Associated(
                        txfNombre.getText(),
                        txfApellido.getText(),
                        Integer.parseInt(txfEdad.getText()),
                        "foto"
                );
                asociado.createFolio(asociado.getName(), asociado.getAge());
                asociado.Associate.add(asociado.getName());
                asociado.Associate.add(String.valueOf(asociado.getAge()));
                asociado.Associate.add(asociado.getFolio());
                RenamePhoto(asociado);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Registrar Asociado", getStage(),
                        "Asociado registrado con éxito, su numero de folio es: " + asociado.getFolio());
                asociado.createFile(asociado);
            }
        } catch (Exception ex) {
            Logger.getLogger(RegistrarAsociadoController.class.getName()).log(SEVERE,
                    "Error al Registrar Asociado", ex);
        }
    }

}
