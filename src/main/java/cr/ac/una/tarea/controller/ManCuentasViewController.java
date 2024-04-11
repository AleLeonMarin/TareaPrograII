package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.model.AccountType;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class ManCuentasViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAgregar;
    @FXML
    private MFXButton btnEliminar;
    @FXML
    private MFXButton btnGuardar;
    @FXML
    private MFXButton btnEditar;
    @FXML
    private MFXComboBox<String> cmbCuentas;
    @FXML
    private AnchorPane root;
    @FXML
    private MFXTextField txfNomCuentas;

    @Override
    public void initialize() {

        cmbCuentas.setDisable(true);
        cmbCuentas.setVisible(false);

    }

    @FXML
    void onActionBtnAgregar(ActionEvent event) {
        try {
            if (txfNomCuentas.getText() == null || txfNomCuentas.getText().isEmpty()) {

                new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Cuenta", getStage(),
                        "Debe ingresar el nombre de la cuenta");
            } else {

                AccountType accounttype = new AccountType(txfNomCuentas.getText());
                accounttype.setAccountType(accounttype);
                accounttype.createFile(accounttype);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Agregar Cuenta", getStage(),
                        "Cuenta agregada correctamente");

            }

        } catch (Exception ex) {
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
    void onActionBtnEditar(ActionEvent event) {

    }

    @FXML
    void onActionCmbCuentas(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
