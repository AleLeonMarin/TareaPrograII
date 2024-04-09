package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.App;
import cr.ac.una.tarea.model.AccountType;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManCuentasViewController extends Controller implements Initializable{

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
    
    private ObservableList<AccountType> accountTypes = FXCollections.observableArrayList();
    AccountType accountType; 
    
    
    
    
    @Override
    public void initialize() {
        List<String> account = (List<String>) AppContext.getInstance().get("TiposCuentas");
        
        if (account != null) {
            for (String cuenta : account) {
                AccountType accountType = new AccountType(cuenta);
                accountTypes.add(accountType);
            }
            //cmbCuentas.setItems(accountTypes);
        }
        
    }
    @FXML
    void onActionBtnAgregar(ActionEvent event)  {
        try{
            if( txfNomCuentas.getText() == null ||txfNomCuentas.getText().isEmpty()){

                new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Cuenta", getStage(), "Debe ingresar el nombre de la cuenta");
            }else{

                AccountType account = new AccountType(txfNomCuentas.getText());
                account.cuentas.add(txfNomCuentas.getText());
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Agregar Cuenta", getStage(), "Cuenta agregada correctamente");
                account.createFile(account);
            }

        } catch(Exception ex){
            new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Cuenta", getStage(), "Error al agregar la cuenta");
        }


    }

    @FXML
    void onActionBtnEliminar(ActionEvent event) {

        txfNomCuentas.clear();
    }


    @FXML
    void onActionBtnGuardar(ActionEvent event) {

    }

    @FXML
    void onActionBtnEditar(ActionEvent event) {
        cmbCuentas.setDisable(false);
        cmbCuentas.setVisible(true);
       // cmbCuentas.getItems().sort();
    }

    @FXML
    void onActionCmbCuentas(ActionEvent event) {



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
