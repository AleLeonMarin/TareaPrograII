package cr.ac.una.tarea.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

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
    void onActionBtnAgregar(ActionEvent event) {

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
