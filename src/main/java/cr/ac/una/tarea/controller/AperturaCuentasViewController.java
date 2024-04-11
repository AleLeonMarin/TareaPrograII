package cr.ac.una.tarea.controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import cr.ac.una.tarea.model.Account;
import cr.ac.una.tarea.model.AccountType;
import cr.ac.una.tarea.util.AppContext;

public class AperturaCuentasViewController extends Controller implements Initializable {

    @FXML
    private TableView<Account> tbViewAbiertas;

    @FXML
    private TableView<AccountType> tbViewCuentas;

    @FXML
    private TableColumn<AccountType, String> tbDisponibles;


    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txfNombre;

    Account account = new Account();

    private ObservableList<AccountType> accountType;

    @Override
    public void initialize() {

        accountType = ((ObservableList<AccountType>) AppContext.getInstance().get("TiposCuentas"));
        readAccount();
        loadInfo();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    
    public void readAccount(){ 
        try {
            BufferedReader br = new BufferedReader(new FileReader("AccountType.txt"));
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
    private void loadInfo(){

        accountType = ((ObservableList<AccountType>) AppContext.getInstance().get("TiposCuentas"));

        tbDisponibles.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        tbViewCuentas.setItems(accountType);
        tbViewCuentas.refresh();

    }
}
