package cr.ac.una.tarea.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import cr.ac.una.tarea.model.Account;
import cr.ac.una.tarea.model.Movimientos;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class DepositoAsociadoController extends Controller implements Initializable {

    @FXML
    private Spinner<Integer> SpinnerCien;

    @FXML
    private Spinner<Integer> SpinnerCinco;

    @FXML
    private Spinner<Integer> SpinnerCincoMil;

    @FXML
    private Spinner<Integer> SpinnerCincuenta;

    @FXML
    private Spinner<Integer> SpinnerDiez;

    @FXML
    private Spinner<Integer> SpinnerDiezmil;

    @FXML
    private Spinner<Integer> SpinnerDosMil;

    @FXML
    private Spinner<Integer> SpinnerMil;

    @FXML
    private Spinner<Integer> SpinnerQuinientos;

    @FXML
    private Spinner<Integer> SpinnerVeintemil;

    @FXML
    private Spinner<Integer> SpinnerVeintiCinco;

    @FXML
    private MFXButton btnBuscar;

    @FXML
    private MFXButton btnDepositar;

    @FXML
    private MFXComboBox<String> cmbCuentas;

    @FXML
    private MFXTextField txfFolio;

    ObservableList<Account> accounts;
    

    @Override
    public void initialize() {
        cmbCuentas.getItems().clear();
        txfFolio.clear();
        // TODO Auto-generated method stub
        accounts = (ObservableList<Account>) AppContext.getInstance().get("Cuentas");
        readAsociado();
        ReadFolio();

        // Valores del spinner -> min, max, valorInicial
        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                0);
        SpinnerCinco.setValueFactory(valueFactory2);

        SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                0);
        SpinnerDiez.setValueFactory(valueFactory3);

        SpinnerValueFactory<Integer> valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                0);
        SpinnerVeintiCinco.setValueFactory(valueFactory4);

        SpinnerValueFactory<Integer> valueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                0);
        SpinnerCincuenta.setValueFactory(valueFactory5);

        SpinnerValueFactory<Integer> valueFactory6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                0);
        SpinnerCien.setValueFactory(valueFactory6);

        SpinnerValueFactory<Integer> valueFactory7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                0);
        SpinnerQuinientos.setValueFactory(valueFactory7);

        SpinnerValueFactory<Integer> valueFactory8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                0);
        SpinnerMil.setValueFactory(valueFactory8);

        SpinnerValueFactory<Integer> valueFactory9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                0);
        SpinnerDosMil.setValueFactory(valueFactory9);

        SpinnerValueFactory<Integer> valueFactory10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                0);
        SpinnerCincoMil.setValueFactory(valueFactory10);

        SpinnerValueFactory<Integer> valueFactory11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                0);
        SpinnerDiezmil.setValueFactory(valueFactory11);

        SpinnerValueFactory<Integer> valueFactory12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                0);
        SpinnerVeintemil.setValueFactory(valueFactory12);

    }

    @FXML
    void onActionBtnBuscar(ActionEvent event) {

        String folio = txfFolio.getText();
        System.out.println("Folio: " + folio);
        if (folio.isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),
                    "Debe ingresar un folio");
            return;
        }

        // Limpiar antes de agregar al ComboBox
        cmbCuentas.getItems().clear(); // **No borrar linea
        List<String> accountTypes = searchFolioInFile(folio);

        if (!accountTypes.isEmpty()) {
            cmbCuentas.setItems(FXCollections.observableArrayList(accountTypes));
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Buscar Folio", getStage(),
                    "No se encontraron cuentas asociadas al folio " + folio);
        }

    }

    @FXML
    void onActionDepositar(ActionEvent event) {

        int cantidadCinco = SpinnerCinco.getValue();
        int cantidadDiez = SpinnerDiez.getValue();
        int cantidadVeinticinco = SpinnerVeintiCinco.getValue();
        int cantidadCincuenta = SpinnerCincuenta.getValue();
        int cantidadCien = SpinnerCien.getValue();
        int cantidadQuinientos = SpinnerQuinientos.getValue();
        int cantidadMil = SpinnerMil.getValue();
        int cantidadDosmil = SpinnerDosMil.getValue();
        int cantidadCincomil = SpinnerCincoMil.getValue();
        int cantidadDiezmil = SpinnerDiezmil.getValue();
        int cantidadVeintemil = SpinnerVeintemil.getValue();
        String accountType = cmbCuentas.getValue();
        String Folio = txfFolio.getText();
        int depositTotal = SumMoney();

        if (cmbCuentas.getSelectionModel().getSelectedItem() == null) {

            new Mensaje().showModal(Alert.AlertType.ERROR, "Deposito", getStage(),
                    "Debe seleccionar un tipo de cuenta");
            return;

        }
        // Declarar un contador
        int contador = readCounterFromFile("Pendientes.txt");
        // Incrementar el contador
        contador++;

        try {

            // Crear un FileWriter para escribir en el archivo
            FileWriter fileWriter = new FileWriter("Pendientes.txt", true);

            // Escribir en el archivo con el contador progresivo
            fileWriter.write(contador + "," + Folio + "," + depositTotal + "," + cantidadVeintemil + ","
                    + cantidadDiezmil + "," + cantidadCincomil + "," + cantidadDosmil + "," + cantidadMil + ","
                    + cantidadQuinientos + "," + cantidadCien + "," + cantidadCincuenta + "," + cantidadVeinticinco
                    + "," + cantidadDiez + "," + cantidadCinco + "," + accountType + "\n");

            // Cerrar el FileWriter
            fileWriter.close();

            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Deposito", getStage(),
                    "Deposito realizado con exito, el total depositado es: " + depositTotal + " colones");
        } catch (Exception ex) {
            Logger.getLogger(DepositoAsociadoController.class.getName()).log(Level.SEVERE, "Error al escribir archivo",
                    ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Deposito", getStage(),
                    "Error al realizar el deposito");
        } finally {
            SpinnerCinco.getValueFactory().setValue(0);
            SpinnerDiez.getValueFactory().setValue(0);
            SpinnerVeintiCinco.getValueFactory().setValue(0);
            SpinnerCincuenta.getValueFactory().setValue(0);
            SpinnerCien.getValueFactory().setValue(0);
            SpinnerQuinientos.getValueFactory().setValue(0);
            SpinnerMil.getValueFactory().setValue(0);
            SpinnerDosMil.getValueFactory().setValue(0);
            SpinnerCincoMil.getValueFactory().setValue(0);
            SpinnerDiezmil.getValueFactory().setValue(0);
            SpinnerVeintemil.getValueFactory().setValue(0);
            cmbCuentas.clear();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }

    public void readAsociado() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("CuentasAsociados.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String folio = parts[0];
                String Balance = parts[1];
                String AccountType = parts[2];
                Account acc = new Account(folio, Balance, AccountType);
                accounts.add(acc);
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(DepositoAsociadoController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }
    }

    public void ReadFolio() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("CuentasAsociados.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String folio = parts[0];
                Account acc = new Account(folio);
                accounts.add(acc);
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(DepositoAsociadoController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }

    }

    private List<String> searchFolioInFile(String folio) {
        List<String> accountTypes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("CuentasAsociados.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(folio)) {
                    accountTypes.add(parts[2]);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DepositoAsociadoController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }
        return accountTypes;
    }

    public int SumMoney() {
        return SpinnerCinco.getValue() * 5 + SpinnerDiez.getValue() * 10 + SpinnerVeintiCinco.getValue() * 25 +
                SpinnerCincuenta.getValue() * 50 + SpinnerCien.getValue() * 100
                + SpinnerQuinientos.getValue() * 500 +
                SpinnerMil.getValue() * 1000 + SpinnerDosMil.getValue() * 2000
                + SpinnerCincoMil.getValue() * 5000 +
                SpinnerDiezmil.getValue() * 10000 + SpinnerVeintemil.getValue() * 20000;
    }

    private static int readCounterFromFile(String fileName) {
        int contador = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Dividir la línea por comas y tomar el primer elemento como el contador
                String[] parts = line.split(",");
                contador = Integer.parseInt(parts[0]);
            }
        } catch (IOException | NumberFormatException e) {
            // Si hay un error, simplemente regresamos 0
            contador = 0;
        }
        return contador;
    }

    


}
