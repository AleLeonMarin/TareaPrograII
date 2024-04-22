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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import cr.ac.una.tarea.model.Account;
import cr.ac.una.tarea.model.AccountType;
import cr.ac.una.tarea.model.Associated;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Mensaje;
import javafx.scene.control.Alert;

public class AperturaCuentasViewController extends Controller implements Initializable {

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
        readAccount();
        readAsociado();
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
        String _Folio = txfFolio.getText();
        List<AccountType> removedAccounts = new ArrayList<>(listVAbiertas.getItems());
        listVAbiertas.getItems().clear();
        listVDisponibles.getItems().addAll(removedAccounts);
        // Método externo
        AddToTxt();
        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Tipos de cuentas", getStage(),
                "Se registro cuenta nueva/as al asociado | Carnet " + _Folio);

    }

    @FXML
    public void onActionBtnBuscar(ActionEvent actionEvent) {
        String folio = txfFolio.getText();
        // Busca un folio vacío
        if (folio.isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),
                    "Debe ingresar un folio");
            return;  // Exit the method if folio is empty
        }

        boolean found = false;
        for (Associated associated : asociate) {
            if (associated.getFolio().equals(folio)) {
                txfNombre.setText(associated.getName().toString());
                found = true;
                break;
            }
        }

        if (!found) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Buscar Folio", getStage(),
                    "Folio no encontrado");
        }
    }

    public void AddToTxt() {
        try {
            File file = new File("Cuentas_Drag-and-Drop.txt");
            file.createNewFile(); // Lo crea si no existe

            StringBuilder sb = new StringBuilder();
            for (Account acc : accounts) {
                sb.append(acc.getId() + ",")
                .append(acc.getBalance() + ",")
                .append(acc.getAccountType() + "\n");
            }
            String content = sb.toString();
            java.nio.file.Files.write(file.toPath(), content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            Logger.getLogger(AperturaCuentasViewController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
        }

    }

    public void readAsociado() {
        try {
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
        lista.getItems().add(new AccountType(item));

        MFXListView<?> listaOrigen = (MFXListView<?>) event.getGestureSource();
        listaOrigen.getItems().remove(new AccountType(item));
        event.setDropCompleted(true);
        event.consume();

        // Agregar cuenta a la ObservableList
        String fol = txfFolio.getText();
        Account account = new Account(fol, String.valueOf(0), item);
        accounts.add(account);

    }

    private void onDragOverListas(DragEvent event , MFXListView<AccountType> lista){
        if(event.getGestureSource() != event.getSource() && event.getDragboard().hasString()){
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }


}