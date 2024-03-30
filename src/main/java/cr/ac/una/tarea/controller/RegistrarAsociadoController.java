package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.Associated;
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

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrarAsociadoController extends Controller implements Initializable{

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
    private String photoPath = "./";
    private String route = photoPath + "null.jpg";

    @Override
    public void initialize() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Image image = new Image("file:" + route);
            imgVFoto.setImage(image);
        } catch (Exception e) {
            Logger.getLogger(RegistrarAsociadoController.class.getName()).log(Level.SEVERE, "Error loading image", e);
            // Display a user-friendly error message
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

                new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(), "Debe ingresar el nombre del asociado");
            } else if (txfEdad.getText() == null || txfEdad.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(), "Debe ingresar la edad del asociado");
            } else if (txfApellido.getText() == null || txfApellido.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(), "Debe ingresar el apellido del asociado");

            } else if (imgVFoto.getImage() == null || imgVFoto.getImage().isError()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Asociado", getStage(), "Debe tomarse la foto del asociado");
            }else{
                Associated asociado = new Associated(txfNombre.getText(), txfApellido.getText(), Integer.parseInt(txfEdad.getText()), "foto");
                asociado.Associate.add(asociado.getName());
                asociado.Associate.add(String.valueOf(asociado.getAge()));
                asociado.Associate.add(asociado.createFolio(asociado.getName(), asociado.getAge()));
                asociado.Associate.add(asociado.getPhoto());
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Registrar Asociado", getStage(), "Asociado registrado con éxito, su numero de folio es: " + asociado.createFolio(asociado.getName(), asociado.getAge()));
                asociado.createFile(asociado);
            }
        }catch (Exception ex){
            Logger.getLogger(RegistrarAsociadoController.class.getName()).log(Level.SEVERE,"Error al Registrar Asociado",ex);
        }



    }

}
