package cr.ac.una.tarea.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class EstadoDetalladoViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnBuscar;

    @FXML
    private MFXButton btnLimpiar;

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
            txfFolio.clear();
            return;
        }
    
        try {
            String folio = txfFolio.getText().toUpperCase();
            txfFolio.setText(folio); // Convertir a mayúsculas
    
            boolean asociadoEncontrado = false;
    
            // Buscar asociado
            for (Associated asociado : asociate) {
                if (asociado.getFolio().equalsIgnoreCase(folio)) { 
                    txfNombre.setText(asociado.getName());
                    asociadoEncontrado = true;
                    break; 
                }
            }
    
            if (!asociadoEncontrado) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(), "Asociado no encontrado");
                return; 
            }
    
            boolean cuentasEncontradas = false;
    
            // Buscar cuentas asociadas
            for (Account cuenta : accounts) {
                if (cuenta.getId().equalsIgnoreCase(folio)) { 
                    cmbCuentas.getItems().add(cuenta.getAccountType());
                    cuentasEncontradas = true;
                }
            }
    
            // Mostrar mensaje dependiendo de si se encontraron cuentas o no
            if (!cuentasEncontradas) {
                new Mensaje().showModal(Alert.AlertType.WARNING, "Buscar Folio", getStage(), "No se encontraron cuentas asociadas al folio");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Buscar Folio", getStage(), "Asociado encontrado");
            }
    
        } catch (NullPointerException e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(), "Error al buscar el folio");
        }

    }

    @FXML
    void onActionCmbCuentas(ActionEvent event) {
        tbVMovimientos.getItems().clear();
        tbVMovimientos.getColumns().clear();
        populateTableView();

        try {
            BufferedReader br = new BufferedReader(new FileReader("Movimientos.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String folio = parts[0];
                String total = parts[1];
                String depositos = parts[2];
                String retiros = parts[3];
                String tipoCuenta = parts[4];

                if (folio.equals(txfFolio.getText())
                        && tipoCuenta.equals(cmbCuentas.getSelectionModel().getSelectedItem())) {
                    Movimientos movimientos = new Movimientos(folio, total, depositos, retiros, tipoCuenta);
                    // Verificar si la fila ya existe en la TableView
                    if (!filaExisteEnTableView(movimientos)) {
                        tbVMovimientos.getItems().add(movimientos);
                    }
                }
            }
            br.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void onActionBtnLimpiar(ActionEvent event) {
        txfFolio.clear();
        txfNombre.clear();
        cmbCuentas.getItems().clear();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }

    @Override
    public void initialize() {
        txfFolio.clear();
        cmbCuentas.getItems().clear();
        txfNombre.clear();
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

    public void readCuentas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("CuentasAsociados.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String folio = parts[0];
                String saldo = parts[1];
                String tipoCuenta = parts[2];

                Account cuenta = new Account(folio, saldo, tipoCuenta);
                accounts.add(cuenta);

            }
            br.close();
        } catch (IOException e) {
            Logger.getLogger(EstadoDetalladoViewController.class.getName()).log(Level.SEVERE, "Error al buscar cuentas", e);
        }
    }

    public void populateTableView() {

        TableColumn<Movimientos, String> colFolio = new TableColumn<>("Folio");
        colFolio.setCellValueFactory(new PropertyValueFactory<>("folio"));
        TableColumn<Movimientos, String> colTotal = new TableColumn<>("Total");
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        TableColumn<Movimientos, String> colDepositos = new TableColumn<>("Depositos");
        colDepositos.setCellValueFactory(new PropertyValueFactory<>("depositos"));
        TableColumn<Movimientos, String> colRetiros = new TableColumn<>("Retiros");
        colRetiros.setCellValueFactory(new PropertyValueFactory<>("retiros"));
        TableColumn<Movimientos, String> colTipoDeCuenta = new TableColumn<>("Tipo de Cuenta");
        colTipoDeCuenta.setCellValueFactory(new PropertyValueFactory<>("tipoDeCuenta"));

        tbVMovimientos.getColumns().addAll(colFolio, colTotal, colDepositos, colRetiros, colTipoDeCuenta);
    }

    // Método para verificar si la fila ya existe en la TableView
    private boolean filaExisteEnTableView(Movimientos movimientos) {
        for (Movimientos fila : tbVMovimientos.getItems()) {
            if (fila.equals(movimientos)) {
                return true;
            }
        }
        return false;
    }

}
