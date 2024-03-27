package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.Account;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManCuentasViewController extends Controller implements Initializable{

    @FXML
    private MFXButton btnAgregar;

    @FXML
    private MFXButton btnEliminar;

    @FXML
    private MFXButton btnGuardar;

    @FXML
    private MFXButton btnSalir;

    @FXML
    private MFXComboBox<?> cmbCuentas;

    @FXML
    private AnchorPane root;

    @FXML
    private MFXTextField txfId;

    @FXML
    private MFXTextField txfNomCuentas;


    @Override
    public void initialize() {

    }
    @FXML
    void onActionBtnAgregar(ActionEvent event)  {
        try{
            if( txfNomCuentas.getText() == null ||txfNomCuentas.getText().isEmpty()){

                new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Cuenta", getStage(), "Debe ingresar el nombre de la cuenta");
            } else if (txfId.getText() == null || txfId.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Cuenta", getStage(), "Debe ingresar el tipo de la cuenta");
            }else{

                Account account = new Account(txfNomCuentas.getText(), txfId.getText());
                account.cuentas.add(txfNomCuentas.getText());
                account.cuentas.add(txfId.getText());
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Agregar Cuenta", getStage(), "Cuenta agregada correctamente");
                account.createFile(account);
            }

        } catch(Exception ex){
            new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Cuenta", getStage(), "Error al agregar la cuenta");
        }


    }

    @FXML
    void onActionBtnEliminar(ActionEvent event) {

    }

    @FXML
    void onActionBtnGuardar(ActionEvent event) {

    }

    @FXML
    void onActionBtnSalir(ActionEvent event) {

    }

    @FXML
    void onActionCmbCuentas(ActionEvent event) {



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
