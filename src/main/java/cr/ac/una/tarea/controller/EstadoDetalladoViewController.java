package cr.ac.una.tarea.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import cr.ac.una.tarea.model.Account;
import cr.ac.una.tarea.model.Associated;
import cr.ac.una.tarea.model.Movimientos;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class EstadoDetalladoViewController extends Controller implements Initializable{

    @FXML
    private MFXButton btnBuscar;

    @FXML
    private MFXComboBox<String> cmbCuentas;

    @FXML
    private ImageView imgVFoto;

    @FXML
    private TableView<Movimientos> tbVMovimientos;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txfNombre;

    ObservableList<Associated> asociate;
    ObservableList<Account> accounts;

    @FXML
    void onActionBtnBuscar(ActionEvent event) {

        if (txfFolio.getText().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(), "Debe ingresar un folio");
        }

        try {

            String folio = txfFolio.getText();
            for (Associated asociado : asociate) {
                if (asociado.getFolio().equals(folio)) {
                    txfNombre.setText(asociado.getName());
                }
            }

            for(Account cuenta : accounts){
                if(cuenta.getId().equals(folio)){
                    cmbCuentas.getItems().add(cuenta.getAccountType());
                }
            }

        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(), "Error al buscar el folio");
        }

    }

    @FXML
    void onActionCmbCuentas(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        asociate = ((ObservableList<Associated>) AppContext.getInstance().get("Asociados"));
        accounts = ((ObservableList<Account>) AppContext.getInstance().get("Cuentas"));
        readAsociado();
        readCuentas();
    }

    public void readAsociado() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Asociados.txt"));
            String line;

            // Ciclo que recorre hasta el que archivo txt sea nulo
            while ((line = br.readLine()) != null) {
                // Separa cada parte de la información del asociado con una ','
                String[] parts = line.split(",");
                String name = parts[0];
                String lastName = parts[1];
                String folio = parts[2];
                String age = parts[3];
                String photo = parts[4];
                // Crea un objeto de asociado y agrega los argumentos a la lista
                Associated asociado = new Associated(name, lastName, folio, age, photo);
                asociate.add(asociado);
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(EstadoResumidoViewController.class.getName()).log(Level.SEVERE,
                    "Error al leer archivo", ex);
        }
    }

    public void readCuentas(){
        try{
        BufferedReader br = new BufferedReader(new FileReader("CuentasAsociados.txt"));
        String line;

        while((line = br.readLine()) != null){
            String[] parts = line.split(",");
            String folio = parts[0];
            String saldo = parts[1];
            String tipoCuenta = parts[2];

            Account cuenta = new Account(folio, saldo, tipoCuenta);
            accounts.add(cuenta);

            

        }
        br.close();
        }catch(IOException e){
            Logger.getLogger(ImpresCarnetController.class.getName()).log(Level.SEVERE, "Error al buscar cuentas", e);
        }
    }


}
