package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Flow;

public class FuncionariosViewController extends Controller implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private MFXButton btnAperCuentas;

    @FXML
    private MFXButton btnCarnet;

    @FXML
    private MFXButton btnManAso;

    @FXML
    private MFXButton btnRetirDepo;

    @FXML
    private MFXButton btnSalir;

    @FXML
    void onActionBtnAperCuentas(ActionEvent event) {
        FlowController.getInstance().goView("ConsultaCuenta");

    }

    @FXML
    void onActionBtnCarnet(ActionEvent event) {
        FlowController.getInstance().goView("ImpresCarnet");

    }

    @FXML
    void onActionBtnManAso(ActionEvent event) {
        FlowController.getInstance().goView("EditarAsociado");

    }

    @FXML
    void onActionBtnRetirDepo(ActionEvent event) {
        FlowController.getInstance().goView("RetirDepos");

    }

    @FXML
    void onActionBtnSalir(ActionEvent event) {

        FlowController.getInstance().salir();

    }

    @Override
    public void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
