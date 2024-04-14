package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.model.AccountType;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private ObservableList<AccountType> accountType;

    @Override
    public void initialize() {
        accountType = ((ObservableList<AccountType>) AppContext.getInstance().get("TiposCuentas"));
        readAccount();
        loadInfo(cmbCuentas, accountType);
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

    public void readAccount() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("../resources/AccountType.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                AccountType account = new AccountType(name);
                accountType.add(account);
            }
            br.close();

        } catch (IOException ex) {

            Logger.getLogger(EditarAsociadoController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);

        }
    }

    public void loadInfo(MFXComboBox<String> cmbCuentas, ObservableList<AccountType> accountType) {

        cmbCuentas.getItems().clear();
        for (AccountType accountType1 : accountType) {
            cmbCuentas.getItems().add(accountType1.getName());
        }
    }
}
