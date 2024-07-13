package cr.ac.una.tarea.controller;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RetirDeposController extends Controller implements Initializable {

    String filePath = "CuentasAsociados.txt";

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXButton btnBuscar;

    @FXML
    private MFXButton btnRetirar;

    @FXML
    private MFXButton btnDepositar;

    @FXML
    private MFXComboBox<String> cmbCuentas;

    @FXML
    private Spinner<Integer> SpinnerCien;

    @FXML
    private Spinner<Integer> SpinnerCinco;

    @FXML
    private Spinner<Integer> SpinnerDiez;

    @FXML
    private Spinner<Integer> SpinnerCincoMil;

    @FXML
    private Spinner<Integer> SpinnerCincuenta;

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

    private ObservableList<Account> accounts;

    @Override
    public void initialize() {
        txfFolio.clear();
        cmbCuentas.getItems().clear();
        accounts = ((ObservableList<Account>) AppContext.getInstance().get("Cuentas"));
        ReadFolio();
        readAsociado();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void onActionBtnBuscar(ActionEvent event) {

        boolean folioEncontrado = false;
        boolean cuentaEncontrada = false;

        String folio = txfFolio.getText().toUpperCase();
        txfFolio.setText(folio);

        if (folio.isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),
                    "Debe ingresar un folio");
            return;
        }

        // Limpiar antes de agregar al ComboBox
        cmbCuentas.getItems().clear(); // **No borrar linea
        List<String> accountTypes = searchFolioInFile(folio);
        folioEncontrado = true;
        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Buscar Folio", getStage(),
                "Folio encontrado");

        if (!accountTypes.isEmpty()) {
            cmbCuentas.setItems(FXCollections.observableArrayList(accountTypes));
            cuentaEncontrada = true;
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Buscar Folio", getStage(),
                    "Cuentas asociadas al folio " + folio + " encontradas");
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Buscar Folio", getStage(),
                    "No se encontraron cuentas asociadas al folio " + folio);
        }
    }

    @FXML
    public void onActionDepositar(ActionEvent event) throws IOException {
        // Verificar si no se ha seleccionado nada en el ComboBox
        if (cmbCuentas.getSelectionModel().getSelectedItem() == null) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),
                    "Debe seleccionar un tipo de cuenta");
            return;
        }
        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Depósito", getStage(),
                "Se han depositado " + SumMoney() + " a la cuenta");
        // Obtener el folio y el tipo de cuenta seleccionados
        String folio = txfFolio.getText();
        String selectedAccountType = cmbCuentas.getSelectionModel().getSelectedItem();

        // Obtener el monto total a depositar sumando los valores de los spinners
        int depositAmount = SumMoney();
        String total = String.valueOf(depositAmount);

        Movimientos mov = new Movimientos(folio, total, total, "0", selectedAccountType);
        mov.setMovimientos(mov);
        mov.createTxtMovements(mov);
        // Actualizar el saldo en el archivo para el tipo de cuenta seleccionado
        updateBalanceForAccountTypeInFile(folio, selectedAccountType, depositAmount);
        cmbCuentas.clear();
        cmbCuentas.getSelectionModel().clearSelection();
        CleanSpinners();
    }

    @FXML
    public void onActionRetirar(ActionEvent actionEvent) throws IOException {
        // Verificar si no se ha seleccionado nada en el ComboBox
        if (cmbCuentas.getSelectionModel().getSelectedItem() == null) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),
                    "Debe seleccionar un tipo de cuenta");
            return;
        }
        // Métodos de Retiro - - - - - - -

        // Obtener el folio y el tipo de cuenta seleccionados
        String folio = txfFolio.getText();
        String selectedAccountType = cmbCuentas.getSelectionModel().getSelectedItem();

        // Obtener el saldo actual para el tipo de cuenta seleccionado
        String currentBalanceStr = getBalanceFromAccountType(folio, selectedAccountType);
        if (currentBalanceStr != null) {
            int currentBalance = Integer.parseInt(currentBalanceStr);

            // Calcular el monto a retirar
            int withdrawalAmount = SumMoney();

            // Verificar si el monto a retirar es menor o igual al saldo actual
            if (withdrawalAmount <= currentBalance) {
                // Realizar la resta del monto a retirar del saldo actual
                int newBalance = currentBalance - withdrawalAmount;

                // Actualizar el saldo en el archivo
                updateBalanceForAccountTypeInFile(folio, selectedAccountType, String.valueOf(newBalance));

                // Mostrar un mensaje informativo con el saldo retirado
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Retirar", getStage(),
                        "El dinero retirado fue de " + withdrawalAmount);

                Movimientos mov = new Movimientos(txfFolio.getText(), String.valueOf(newBalance), "0",
                        String.valueOf(withdrawalAmount), selectedAccountType);
                mov.setMovimientos(mov);
                mov.createTxtMovements(mov);
            } else {
                // Mostrar un mensaje de error si el monto a retirar es mayor que el saldo
                // actual
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", getStage(),
                        "El saldo disponible es insuficiente para realizar este retiro");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", getStage(),
                    "No se pudo encontrar el saldo actual para el tipo de cuenta " + selectedAccountType);
        }

        // - - - - - - - - - - - - - - - -
        cmbCuentas.clear();
        cmbCuentas.getSelectionModel().clearSelection();
        CleanSpinners();
    }

    // Suma todos los valores de los spinners
    public int SumMoney() {
        return SpinnerCinco.getValue() * 5 + SpinnerDiez.getValue() * 10 + SpinnerVeintiCinco.getValue() * 25 +
                SpinnerCincuenta.getValue() * 50 + SpinnerCien.getValue() * 100
                + SpinnerQuinientos.getValue() * 500 +
                SpinnerMil.getValue() * 1000 + SpinnerDosMil.getValue() * 2000
                + SpinnerCincoMil.getValue() * 5000 +
                SpinnerDiezmil.getValue() * 10000 + SpinnerVeintemil.getValue() * 20000;
    }

    public void ReadFolio() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String folio = parts[0];
                Account acc = new Account(folio);
                accounts.add(acc);
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(AperturaCuentasViewController.class.getName()).log(Level.SEVERE, "Error al leer archivo",
                    ex);
        }

    }

    public void readAsociado() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
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
            Logger.getLogger(EditarAsociadoController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }
    }

    // Buscar los tipos de cuenta existentes en el folio
    private List<String> searchFolioInFile(String folio) {
        List<String> accountTypes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(folio)) {
                    accountTypes.add(parts[2]);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RetirDeposController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }
        return accountTypes;
    }

    // Guarda un valor agregado [balance] en el archivo de texto
    private void updateBalanceForAccountType() {
        boolean act = false;
        String totalMoney = String.valueOf(SumMoney());
        String folio = txfFolio.getText();
        String selectedAccountType = cmbCuentas.getSelectedItem().toString();

        // For each para pasar los valores null
        for (Account account : accounts) {
            if (account.getId() == null || account.getAccountType() == null) {
                continue;
            }

            if (account.getId().equals(folio) && account.getAccountType().equals(selectedAccountType)) {
                account.setBalance(totalMoney);
                act = true;
                break;
            }
        }

        if (act) {
            try {
                File file = new File(filePath);
                file.createNewFile();

                StringBuilder sb = new StringBuilder();
                for (Account account : accounts) {
                    if (account.getId() == null || account.getBalance() == null || account.getAccountType() == null) {
                        // Skip if any essential information is null
                        continue;
                    }
                    sb.append(account.getId()).append(",")
                            .append(account.getBalance()).append(",")
                            .append(account.getAccountType()).append("\n");
                }
                String content = sb.toString();

                java.nio.file.Files.write(file.toPath(), content.getBytes());
            } catch (IOException e) {
                Logger.getLogger(RetirDeposController.class.getName()).log(Level.SEVERE, "Error al sobreescribir", e);
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Encontrar asociado", getStage(),
                    "No se encontró el asociado");
        }
    }

    // Limpiar spinners seleccionados con las denominaciones, después de retirar |
    // depositar
    public void CleanSpinners() {
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
    }

    // Actualizar el balance para un tipo de cuenta en el archivo al agregar una
    // nueva suma
    private void updateBalanceForAccountTypeInFile(String folio, String accountType, int additionalAmount) {
        // Lee el txt existente
        String currentBalanceStr = getBalanceFromAccountType(folio, accountType);
        if (currentBalanceStr != null) {
            // Convierte el balance actual de un String a un Integer
            int currentBalance = Integer.parseInt(currentBalanceStr);
            // Suma los 2 balances
            int newBalance = currentBalance + additionalAmount;
            // Convierte de nuevo a String
            String newBalanceStr = String.valueOf(newBalance);
            // Actualizar el balance in el archivo txt con el nuevo valor
            updateBalanceForAccountTypeInFile(folio, accountType, newBalanceStr);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", getStage(),
                    "No se pudo encontrar el saldo actual para el tipo de cuenta " + accountType);
        }
    }

    // Actualiza el balance para el tipo de cuenta en el archivo txt
    private void updateBalanceForAccountTypeInFile(String folio, String accountType, String newBalance) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(folio) && parts[2].equals(accountType)) {
                    line = parts[0] + "," + newBalance + "," + parts[2];
                }
                lines.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(RetirDeposController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }

        // Write the updated lines back to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(RetirDeposController.class.getName()).log(Level.SEVERE, "Error al escribir archivo", ex);
        }
    }

    // Obtener un balance para el tipo de cuenta del archivo
    private String getBalanceFromAccountType(String folio, String accountType) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(folio) && parts[2].equals(accountType)) {
                    return parts[1];
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RetirDeposController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }
        return null; // Retorna nul si el balance para el tipo de cuenta especifica no se encuentra
    }

}