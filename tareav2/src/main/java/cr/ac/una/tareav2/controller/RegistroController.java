/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tareav2.controller;

import cr.ac.una.tareav2.util.FlowController;
import cr.ac.una.tareav2.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aletr
 */
public class RegistroController extends Controller implements Initializable {

    @FXML
    private MFXButton btnLogIn;
    @FXML
    private MFXTextField txfName;
    @FXML
    private MFXTextField txfLastName;
    @FXML
    private MFXTextField txfID;
    @FXML
    private MFXTextField txfNumber;
    @FXML
    private MFXTextField txfEmail;
    @FXML
    private MFXButton btnCancel;
    @FXML
    private MFXButton btnLogInView;
    @FXML
    private AnchorPane rootRegister;
    @FXML
    private MFXCheckbox checkboxCliente;
    @FXML
    private MFXCheckbox checkboxAdmin;

    @FXML
    private MFXTextField txfDate;
    @FXML
    private MFXTextField psfClave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onActionBtnLogIn(ActionEvent event) {

        try {

            if (txfName.getText() == null || txfName.getText().isBlank()) {

                new Mensaje().show(AlertType.ERROR, "Validacion de Nombre", getStage(), "Es necesario digitar un nombre para registrar");

            } else if (txfLastName.getText() == null || txfLastName.getText().isBlank()) {

                new Mensaje().show(AlertType.ERROR, "Validacion de Apellidos", getStage(), "Es necesario digitar apellidos para registrar");

            } else if (txfID.getText() == null || txfID.getText().isBlank()) {

                new Mensaje().show(AlertType.ERROR, "Validacion de Cedula", getStage(), "Es necesario digitar una cedula para registrar");

            } else if (txfNumber.getText() == null || txfNumber.getText().isBlank()) {

                new Mensaje().show(AlertType.ERROR, "Validacion de Numero telefonico", getStage(), "Es necesario digitar un numero telefonico para registrar");

            } else if (txfEmail.getText() == null || txfEmail.getText().isBlank()) {

                new Mensaje().show(AlertType.ERROR, "Validacion de Correo", getStage(), "Es necesario digitar un correo electronico para registrar");

            } else if (txfDate.getText() == null || txfDate.getText().isBlank()) {

                new Mensaje().show(AlertType.ERROR, "Validacion de Fecha de Nacimiento", getStage(), "Es necesario digitar una fecha de nacimiento para registrar");

            } else if (psfClave.getText() == null || psfClave.getText().isBlank()) {
                new Mensaje().show(AlertType.ERROR, "Validacion de clave de ingreso", getStage(), "Es necesario ingresar una clave");
            } else {

                FlowController.getInstance().goViewInWindow("LogInVIew");

            }

        } catch (Exception ex) {

            Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, "Error ingresado", ex);
            new Mensaje().show(Alert.AlertType.ERROR, "Registro", getStage(), "Error al sistema ingresado");
        }

    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {

        txfName.clear();
        txfLastName.clear();
        txfID.clear();
        txfNumber.clear();
        txfEmail.clear();
        txfDate.clear();
        psfClave.clear();
        FlowController.getInstance().goViewInWindow("LogInView");
        ((Stage) btnCancel.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnLogInView(ActionEvent event) {

        FlowController.getInstance().goViewInWindow("LogInView");
        ((Stage) btnLogIn.getScene().getWindow()).close();
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionCheckBoxCliente(ActionEvent event) {
    }

    @FXML
    private void onActionCheckBoxAdmin(ActionEvent event) {
    }

}
