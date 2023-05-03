/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tareav2.controller;

import cr.ac.una.tareav2.util.FlowController;
import cr.ac.una.tareav2.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aletr
 */
public class LogInViewController extends Controller implements Initializable {

    @FXML
    private MFXTextField txfUser;
    @FXML
    private MFXTextField psfPass;
    @FXML
    private MFXButton BtnPrincipalPage;
    @FXML
    private MFXButton btnRegister;
    @FXML
    private AnchorPane rootLog;
    @FXML
    private MFXButton btnCancel;
    @FXML
    private MFXButton btnLogIn;

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
            if (txfUser.getText() == null || txfUser.getText().isBlank()) {
                new Mensaje().show(AlertType.ERROR, "Validacion de Usuario", getStage(),
                        "Es necesario digitar un usuario para ingresar al sistema");
            } else if (psfPass.getText() == null || psfPass.getText().isBlank()) {
                new Mensaje().show(AlertType.ERROR, "Validacion de la clave", getStage(),
                        "Es necesario digitar una clave para ingresar al sistema");
            } else {
                FlowController.getInstance().goViewInWindow("PaginaPrincipal");
            }
        } catch (Exception e) {
            Logger.getLogger(LogInViewController.class.getName()).log(Level.SEVERE, "Error ingresado", e);
            new Mensaje().show(Alert.AlertType.ERROR, "LogIn", getStage(), "Error al sistema ingresado");
        }
        ((Stage) btnLogIn.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnPrincipalPage(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("PaginaPrincipal");
        ((Stage) BtnPrincipalPage.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnRegister(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("Registro");
        ((Stage) btnRegister.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnCancel(ActionEvent event) {
        txfUser.clear();
        psfPass.clear();
        FlowController.getInstance().goViewInWindow("PaginaPrincipal");
        ((Stage) btnCancel.getScene().getWindow()).close();
    }

    @Override
    public void initialize() {
    }

}
