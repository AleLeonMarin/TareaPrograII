package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;

public class DepositoAsociadoController extends Controller implements Initializable {

    @FXML
    private Spinner<?> SpinnerCien;

    @FXML
    private Spinner<?> SpinnerCinco;

    @FXML
    private Spinner<?> SpinnerCincoMil;

    @FXML
    private Spinner<?> SpinnerCincuenta;

    @FXML
    private Spinner<?> SpinnerDiez;

    @FXML
    private Spinner<?> SpinnerDiezmil;

    @FXML
    private Spinner<?> SpinnerDosMil;

    @FXML
    private Spinner<?> SpinnerMil;

    @FXML
    private Spinner<?> SpinnerQuinientos;

    @FXML
    private Spinner<?> SpinnerVeintemil;

    @FXML
    private Spinner<?> SpinnerVeintiCinco;

    @FXML
    private MFXButton btnBuscar;

    @FXML
    private MFXButton btnDepositar;

    @FXML
    private MFXComboBox<?> cmbCuentas;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    void onActionBtnBuscar(ActionEvent event) {

    }

    @FXML
    void onActionDepositar(ActionEvent event) {

    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }

}
