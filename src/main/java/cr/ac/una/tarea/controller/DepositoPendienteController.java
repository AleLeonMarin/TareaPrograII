package cr.ac.una.tarea.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import cr.ac.una.tarea.model.Pendientes;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DepositoPendienteController extends Controller implements Initializable {

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
    private MFXButton btnDepositar;

    @FXML
    private TableView<Pendientes> tbvPendientes;

    private List<Spinner<Integer>> spinners;

    private ObservableList<Account> accounts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        accounts = (ObservableList<Account>) AppContext.getInstance().get("Cuentas");
        confTable();
        loadInfoColumns();
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
    void onActionDepositar(ActionEvent event) throws IOException {

        if (tbvPendientes.getSelectionModel().getSelectedItem() == null) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),
                    "Debe seleccionar un tipo de cuenta");
            return;
        }
        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Depósito", getStage(),
                "Se han depositado " + SumMoney() + " a la cuenta");
        // Obtener el folio y el tipo de cuenta seleccionados
        String folio = tbvPendientes.getSelectionModel().getSelectedItem().getFolio();
        String selectedAccountType = tbvPendientes.getSelectionModel().getSelectedItem().getTipoDeCuenta();

        // Obtener el monto total a depositar sumando los valores de los spinners
        int depositAmount = SumMoney();
        String deposito = String.valueOf(depositAmount);

        // Actualizar el saldo en el archivo para el tipo de cuenta seleccionado
        updateBalanceForAccountTypeInFile(folio, selectedAccountType, depositAmount);
        String version = tbvPendientes.getSelectionModel().getSelectedItem().getVersion();
        deleteItem(version);
        tbvPendientes.getItems().remove(tbvPendientes.getSelectionModel().getSelectedItem());

        Movimientos movimientos = new Movimientos(folio, deposito , deposito , "0", selectedAccountType);
        movimientos.setMovimientos(movimientos);
        movimientos.createTxtMovements(movimientos);
    }

    public void confTable() {

        TableColumn<Pendientes, String> colFolio = new TableColumn<>("Folio");
        colFolio.setCellValueFactory(new PropertyValueFactory<>("folio"));
        TableColumn<Pendientes, String> colTotal = new TableColumn<>("Total");
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        TableColumn<Pendientes, String> colaccuntType = new TableColumn<>("Tipo de Cuenta");
        colaccuntType.setCellValueFactory(new PropertyValueFactory<>("tipoDeCuenta"));
        TableColumn <Pendientes ,String > colVersion = new TableColumn<>("Version");
        colVersion.setCellValueFactory(new PropertyValueFactory<>("version"));

        tbvPendientes.getColumns().addAll(colVersion,colFolio, colTotal, colaccuntType);

        tbvPendientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadInfoSpinners(newSelection); // Load data into spinners
            }
        });

    }

    public void loadInfoColumns() {
        try {
            tbvPendientes.getItems().clear();
            BufferedReader br = new BufferedReader(new FileReader("Pendientes.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String version = values[0];
                String folio = values[1];
                String total = values[2];
                String accountType = values[14];
                System.out.println(folio + "," + total + "," + accountType);

                Pendientes account = new Pendientes(version ,folio, total, accountType);
                tbvPendientes.getItems().add(account);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadInfoSpinners(Pendientes selectedPendiente) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("Pendientes.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String total = values[2]; // Assuming folio is the unique identifier
                if (total.equals(selectedPendiente.getTotal())) {
                    // Assuming the spinner order matches the order of values in the file
                    String billete20mil = values[3];
                    String billete10mil = values[4];
                    String billete5mil = values[5];
                    String billete2mil = values[6];
                    String billete1mil = values[7];
                    String moneda500 = values[8];
                    String moneda100 = values[9];
                    String moneda50 = values[10];
                    String moneda25 = values[11];
                    String moneda10 = values[12];
                    String moneda5 = values[13];

                    SpinnerVeintemil.getValueFactory().setValue(Integer.parseInt(billete20mil));
                    SpinnerDiezmil.getValueFactory().setValue(Integer.parseInt(billete10mil));
                    SpinnerCincoMil.getValueFactory().setValue(Integer.parseInt(billete5mil));
                    SpinnerDosMil.getValueFactory().setValue(Integer.parseInt(billete2mil));
                    SpinnerMil.getValueFactory().setValue(Integer.parseInt(billete1mil));
                    SpinnerQuinientos.getValueFactory().setValue(Integer.parseInt(moneda500));
                    SpinnerCien.getValueFactory().setValue(Integer.parseInt(moneda100));
                    SpinnerCincuenta.getValueFactory().setValue(Integer.parseInt(moneda50));
                    SpinnerVeintiCinco.getValueFactory().setValue(Integer.parseInt(moneda25));
                    SpinnerDiez.getValueFactory().setValue(Integer.parseInt(moneda10));
                    SpinnerCinco.getValueFactory().setValue(Integer.parseInt(moneda5));
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadSpinners() {
        spinners = new ArrayList<>();
        spinners.add(SpinnerCien);
        spinners.add(SpinnerCinco);
        spinners.add(SpinnerCincoMil);
        spinners.add(SpinnerCincuenta);
        spinners.add(SpinnerDiez);
        spinners.add(SpinnerDiezmil);
        spinners.add(SpinnerDosMil);
        spinners.add(SpinnerMil);
        spinners.add(SpinnerQuinientos);
        spinners.add(SpinnerVeintemil);
        spinners.add(SpinnerVeintiCinco);
    }

    public int SumMoney() {
        return SpinnerCinco.getValue() * 5 + SpinnerDiez.getValue() * 10 + SpinnerVeintiCinco.getValue() * 25 +
                SpinnerCincuenta.getValue() * 50 + SpinnerCien.getValue() * 100
                + SpinnerQuinientos.getValue() * 500 +
                SpinnerMil.getValue() * 1000 + SpinnerDosMil.getValue() * 2000
                + SpinnerCincoMil.getValue() * 5000 +
                SpinnerDiezmil.getValue() * 10000 + SpinnerVeintemil.getValue() * 20000;
    }

    public void readAsociado() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("CuentasAs,ociados.txt"));
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
            Logger.getLogger(DepositoPendienteController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
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
            Logger.getLogger(DepositoPendienteController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }
        return accountTypes;
    }

    private void updateBalanceForAccountType() {
        boolean act = false;
        String totalMoney = String.valueOf(SumMoney());
        String folio = tbvPendientes.getSelectionModel().getSelectedItem().getFolio();
        String selectedAccountType = tbvPendientes.getSelectionModel().getSelectedItem().getTipoDeCuenta();

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
                File file = new File("CuentasAsociados.txt");
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
        try (BufferedReader br = new BufferedReader(new FileReader("CuentasAsociados.txt"))) {
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("CuentasAsociados.txt"))) {
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
        try (BufferedReader br = new BufferedReader(new FileReader("CuentasAsociados.txt"))) {
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
        return null;  // Retorna nul si el balance para el tipo de cuenta especifica no se encuentra
    }

    public void deleteItem(String id) {
        File account = new File("Pendientes.txt");
        StringBuffer temp = new StringBuffer();
    
        try {
            BufferedReader br = new BufferedReader(new FileReader(account));
            String line;
    
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(",");
    
                // If the line does not contain the ID to delete, add it to the temporary content
                if (partes.length >= 1 && !partes[0].equals(id)) {
                    temp.append(line).append("\r\n");
                } else {
                    System.out.println("ID found and deleted: " + id);
                }
            }
            br.close(); // Close the BufferedReader
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    
        try {
            FileWriter writer = new FileWriter(account);
            writer.write(temp.toString());
            writer.close(); // Close the FileWriter
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    



}
