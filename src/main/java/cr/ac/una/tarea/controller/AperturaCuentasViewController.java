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
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
    private MFXButton btnBuscar;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txfNombre;

    Account account = new Account();

    private ObservableList<AccountType> accountType;
    private ObservableList<Account> accounts;
    private ObservableList<Associated> asociate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initialize() {
        asociate = ((ObservableList<Associated>) AppContext.getInstance().get("Asociados"));
        accountType = ((ObservableList<AccountType>) AppContext.getInstance().get("TiposCuentas"));
        accounts = ((ObservableList<Account>) AppContext.getInstance().get("Cuentas"));
        readAccount();
        loadInfo();
    }

    @FXML
    void onActionBtnBuscar(ActionEvent event) {

        if(txfFolio.getText().isEmpty()){
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(), "Debe ingresar un folio");
        }

            try{
            
            String folio = txfFolio.getText();

            for(Associated associated : asociate){
                if(associated.getFolio().equals(folio)){
                    txfNombre.setText(associated.getName().toString());
                }
            }
            
        }catch(Exception e){
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(), "Error al buscar el folio");
        
        
    }

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
        listaOrigen.getItems().remove(item);
        event.setDropCompleted(true);
        event.consume();
        
    }

    private void onDragOverListas(DragEvent event , MFXListView<AccountType> lista){
        if(event.getGestureSource() != event.getSource() && event.getDragboard().hasString()){
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }
}