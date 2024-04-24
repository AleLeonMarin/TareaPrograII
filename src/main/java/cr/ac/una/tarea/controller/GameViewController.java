package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.model.Account;
import cr.ac.una.tarea.model.Associated;
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

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameViewController extends Controller implements Initializable {

    Random random = new Random();
    private final int GuessedNumber = new Random().nextInt(100) + 1;

    String Price = "20000";
    String filePathCuentas = "CuentasAsociados.txt";

    private ObservableList<Associated> asociate;
    private ObservableList<Account> accounts;

    @FXML
    public MFXButton btnGenerar;

    @FXML
    public MFXButton btnBuscarFolio;

    @FXML
    public MFXComboBox cmbCuenta;

    @FXML
    public MFXTextField txtFieldIngresar;

    @FXML
    public MFXTextField txtFieldBuscarFolio;

    @Override
    public void initialize() {
        asociate = ((ObservableList<Associated>) AppContext.getInstance().get("Asociados"));
        accounts = ((ObservableList<Account>) AppContext.getInstance().get("Cuentas"));
        ReadFolio();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onActionSearch(ActionEvent actionEvent) {
        String folio = txtFieldBuscarFolio.getText();

        if (txtFieldBuscarFolio.getText().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),
                    "Debe ingresar un folio");
        }

        cmbCuenta.getItems().clear();
        List<String> accountTypes = searchFolioInFile(folio);

        if (!accountTypes.isEmpty()) {
            cmbCuenta.setItems(FXCollections.observableArrayList(accountTypes));
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),
                    "Folio no encontrado");
        }
    }
    @FXML
    public void onActionGuess(ActionEvent actionEvent) {
        if (cmbCuenta.getSelectionModel().getSelectedItem() == null) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Juego", getStage(),
                    "Debe seleccionar un tipo de cuenta");
            return;
        }
        GuessNumber();
    }

    public void ReadFolio() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePathCuentas));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String folio = parts[0];
                Account acc = new Account(folio);
                accounts.add(acc);
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(AperturaCuentasViewController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }

    }

    private List<String> searchFolioInFile(String folio) {
        List<String> accountTypes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePathCuentas))) {
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

    // Método para verificar si el numero digitado coincide con el generado aleatorio
    public void GuessNumber(){
        int TypedNumber;
        String folio = txtFieldBuscarFolio.getText();
        String selectedAcc = cmbCuenta.getSelectedItem().toString();

        try {
            TypedNumber = Integer.parseInt(txtFieldIngresar.getText());
            if (TypedNumber > 100) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", getStage(), "Por favor, ingrese un número entre 1 y 100.");
                return;
            }
        } catch (NumberFormatException e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", getStage(), "Por favor, ingrese un número válido.");
            return;
        }

        do {
            txtFieldIngresar.clear();

            // Cada intento que el asociado no adivine, el dinero se divide en 2.
            int currentPrice = (int) Double.parseDouble(Price);
            int newPrice = currentPrice / 2;
            Price = String.valueOf(newPrice);

            if (TypedNumber > GuessedNumber) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Numero Adivinador", getStage(), "Muy Arriba...");
            } else if (TypedNumber < GuessedNumber) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Numero Adivinador", getStage(), "Muy Abajo...");
            } else{
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Numero Adivinador", getStage(),
                        "Felicidades, ganaste!");
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Numero Adivinador", getStage(),
                        "El monto deposito fue de " + Price + "₡ a la cuenta " + selectedAcc);

                // Agregar el dinero a la cuenta del asociado
                updateBalanceForAccountTypeInFile(folio, selectedAcc, Integer.parseInt(Price));
                CleanScreen();
            }

            break;
        } while (TypedNumber != GuessedNumber);
    }

    // Actualizar el balance para un tipo de cuenta en el archivo al agregar una nueva suma
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
        try (BufferedReader br = new BufferedReader(new FileReader(filePathCuentas))) {
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePathCuentas))) {
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
        try (BufferedReader br = new BufferedReader(new FileReader(filePathCuentas))) {
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
        return null;  // Retorna nul si el balance para el tipo de cuenta específica no se encuentra
    }

    // Después de ganar o perder la pantalla se limpia
    void CleanScreen(){
        cmbCuenta.getItems().clear();
        txtFieldBuscarFolio.clear();
        txtFieldIngresar.clear();
    }
}
