package cr.ac.una.tarea.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import cr.ac.una.tarea.model.Account;
import cr.ac.una.tarea.model.AccountType;
import cr.ac.una.tarea.model.Associated;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Mensaje;
import javafx.scene.control.Alert;

public class AperturaCuentasViewController extends Controller implements Initializable {

    String path = "CuentasAsociados.txt";
    File filepath = new File(path);

    @FXML
    private MFXListView<AccountType> listVAbiertas;

    @FXML
    private MFXListView<AccountType> listVDisponibles;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txfNombre;

    @FXML
    private MFXButton btnBuscar;

    @FXML
    public MFXButton btnSave;

    @FXML
    public MFXButton btnLimpiar;

    private ObservableList<AccountType> accountType;
    private ObservableList<Account> accounts;
    private ObservableList<Associated> asociate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @Override
    public void initialize() {
        asociate = ((ObservableList<Associated>) AppContext.getInstance().get("Asociados"));
        accountType = ((ObservableList<AccountType>) AppContext.getInstance().get("TiposCuentas"));
        accounts = ((ObservableList<Account>) AppContext.getInstance().get("Cuentas"));
        readAsociado();
        readAccount();
        loadInfo();
        txfFolio.setAllowEdit(true);
    }

    @FXML
    void onDragDetectedAbiertas(MouseEvent event) {
        onDragDetectedListas(event, listVAbiertas);
    }

    @FXML
    void onDragDetectedDisponibles(MouseEvent event) {
        onDragDetectedListas(event, listVDisponibles);
    }

    @FXML
    void onDragDroppedAbiertas(DragEvent event) {
        onDragDroppedListas(event, listVAbiertas);
    }

    @FXML
    void onDragDroppedDisponibles(DragEvent event) {
        onDragDroppedListas(event, listVDisponibles);
    }

    @FXML
    void onDragOverAbiertas(DragEvent event) {
        onDragOverListas(event , listVAbiertas);
    }

    @FXML
    void onDragOverDisponibles(DragEvent event) {
        onDragOverListas(event , listVDisponibles);
    }

    @FXML
    public void onActionBtnLimpiar(ActionEvent actionEvent) {
        txfFolio.clear();
        txfNombre.clear();
    }

    @FXML
    public void onActionBtnSave(ActionEvent actionEvent) {
        if (txfFolio.getText().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Folio no encontrado", getStage(),
                    "Debe ingresar un folio");
            return;
        }

        if (listVAbiertas.getItems().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Lista vacía", getStage(),
                    "La lista de cuentas abiertas está vacía.");
            return;
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Agregar Cuenta", getStage(),
                    "Agregado el tipo de cuenta al asociado exitosamente");
        }
    }

    @FXML
    public void onActionBtnBuscar(ActionEvent actionEvent) {

        boolean asociadoEncontrado = false;
        String folio = txfFolio.getText().toUpperCase();
        txfFolio.setText(folio);
        // Busca un folio vacío
        if (folio.isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),
                    "Debe ingresar un folio");
            return;  // Exit the method if folio is empty
        }

        for (Associated associated : asociate) {
            if (associated.getFolio().equals(folio)) {
                txfNombre.setText(associated.getName());
                asociadoEncontrado = true;
                  break;
            }
        }

        if(!asociadoEncontrado){
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Asociado", getStage(),
                    "Asociado no encontrado");
            return;
        }else{
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Buscar Asociado", getStage(),
                    "Asociado encontrado");
        }

        // Clear listVAbiertas before loading new account types
        listVAbiertas.getItems().clear();

        try (BufferedReader br = new BufferedReader(new FileReader("CuentasAsociados.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(folio)) {
                    String accountType = parts[2];
                    listVAbiertas.getItems().add(new AccountType(accountType));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AperturaCuentasViewController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }

        verifyAndRemoveDuplicates();
    }

    public void AddToTxt(String Folio, String Balance, String AccountType) {
        try {
            FileWriter writer = new FileWriter(filepath, true); // Append mode

            // Create a new Account object with the provided parameters
            Account newAccount = new Account(Folio, Balance, AccountType);

            // Append data for the new account
            writer.write(newAccount.getId() + ",");
            writer.write(newAccount.getBalance() + ",");
            writer.write(newAccount.getAccountType() + "\n");

            // Add the new account to the accounts list
            accounts.add(newAccount);

            writer.close(); // Close the writer
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFromTxt(String Folio, String Balance, String AccountType) {
        try {
            // Create a temporary file
            File tempFile = new File("temp.txt");
            FileWriter writer = new FileWriter(tempFile);

            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String currentFolio = parts[0];
                String currentBalance = parts[1];
                String currentAccountType = parts[2];

                // Compare with the parameters to determine if it needs to be deleted
                if (!currentFolio.equals(Folio) || !currentBalance.equals(Balance) || !currentAccountType.equals(AccountType)) {
                    // If the current line does not match the parameters, write it to the temporary file
                    writer.write(line + "\n");
                }
            }
            br.close();
            writer.close();

            // Delete the original file
            filepath.delete();

            // Rename the temporary file to the original file name
            tempFile.renameTo(filepath);

            // Optionally, update the accounts list after deletion
            // accounts.removeIf(account -> account.getId().equals(Folio) && account.getBalance().equals(Balance) && account.getAccountType().equals(AccountType));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readAccount(){
        try {
            // Usar para no duplicar los items en la lista de cuentas disponibles para abrir
            accountType.clear();
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
            Logger.getLogger(AperturaCuentasViewController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }

    }

    public void readAsociado() {
        try {
            // Usar para no duplicar los items en la lista de cuentas disponibles para abrir
            asociate.clear();
            BufferedReader br = new BufferedReader(new FileReader("Asociados.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String lastName = parts[1];
                String folio = parts[2];
                String age = parts[3];
                String photo = parts[4];

                Associated asociado = new Associated(name, lastName, folio, age, photo);
                asociate.add(asociado);
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(EditarAsociadoController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }
    }

    private void loadInfo(){

        listVDisponibles.setItems(accountType);

        listVDisponibles.setOnDragDetected(this::onDragDetectedDisponibles);
        listVDisponibles.setOnDragOver(this::onDragOverDisponibles);
        listVDisponibles.setOnDragDropped(this::onDragDroppedDisponibles);

        listVAbiertas.setOnDragDetected(this::onDragDetectedAbiertas);
        listVAbiertas.setOnDragOver(this::onDragOverAbiertas);
        listVAbiertas.setOnDragDropped(this::onDragDroppedAbiertas);

    }

    private void onDragDetectedListas(MouseEvent event , MFXListView<AccountType> lista){
        String itemToDrag = lista.getSelectionModel().getSelectedValue().toString();
        Dragboard dragboard = lista.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putString(itemToDrag);
        dragboard.setContent(content);

        event.consume();
    }

    private void onDragDroppedListas( DragEvent event , MFXListView<AccountType> lista ){
        String item = event.getDragboard().getString();
        AccountType selectedAccountType = new AccountType(item);
        String folio = txfFolio.getText();

        if (lista == listVDisponibles && isBalanceGreaterThanZero(item)) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al mover cuenta", getStage(),
                    "No se puede mover una cuenta con saldo mayor a 0.");
            return;
        }

        if (lista == listVAbiertas) {
            // Moving from listVDisponibles to listVAbiertas
            listVAbiertas.getItems().add(selectedAccountType);
            AddToTxt(folio, "0", item);

        } else if (lista == listVDisponibles) {
            // Moving from listVAbiertas to listVDisponibles
            listVDisponibles.getItems().add(selectedAccountType);
            deleteFromTxt(folio, "0", item);
        }

        MFXListView<?> listaOrigen = (MFXListView<?>) event.getGestureSource();
        listaOrigen.getItems().remove(selectedAccountType);
        event.setDropCompleted(true);
        event.consume();

        // Deselect the dragged item after it's saved
        listaOrigen.getSelectionModel().clearSelection();
    }

    private boolean isBalanceGreaterThanZero(String accountTypeName) {
        try (BufferedReader br = new BufferedReader(new FileReader("CuentasAsociados.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[2].equals(accountTypeName)) {
                    // Assuming balance is stored as the second element in the parts array
                    double balance = Double.parseDouble(parts[1]);
                    // Check if balance is greater than zero
                    return balance > 0;
                }
            }
        } catch (IOException | NumberFormatException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace(); // For demonstration purposes
        }
        return false;
    }

    private void onDragOverListas(DragEvent event , MFXListView<AccountType> lista){
        if(event.getGestureSource() != event.getSource() && event.getDragboard().hasString()){
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    private void verifyAndRemoveDuplicates() {
        // Iterate over the items in listVAbiertas
        for (AccountType item : listVAbiertas.getItems()) {
            // Check if the item exists in listVDisponibles
            if (listVDisponibles.getItems().contains(item)) {
                // If it does, remove it from listVDisponibles
                listVDisponibles.getItems().remove(item);
            }
        }
    }


}