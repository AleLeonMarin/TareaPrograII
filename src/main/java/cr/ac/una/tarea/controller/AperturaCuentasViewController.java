package cr.ac.una.tarea.controller;

import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
import cr.ac.una.tarea.util.AppContext;


public class AperturaCuentasViewController extends Controller implements Initializable {

    @FXML
    private MFXListView<Account> listVAbiertas;

    @FXML
    private MFXListView<AccountType> listVDisponibles;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txfNombre;

    Account account = new Account();

    private ObservableList<AccountType> accountType;
    private ObservableList<Account> accounts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initialize() {
        accountType = ((ObservableList<AccountType>) AppContext.getInstance().get("TiposCuentas"));
        readAccount();
        loadInfo();
    }

    @FXML
    void onDragDetectedAbiertas(MouseEvent event) {
        onDragDetectedListas(event, listVDisponibles);
    }

    @FXML
    void onDragDetectedDisponibles(MouseEvent event) {
        onDragDetectedListas(event, listVDisponibles);
    }

    @FXML
    void onDragDroppedAbiertas(DragEvent event) {

        onDragDroppedListas(event, listVDisponibles);

    }

    @FXML
    void onDragDroppedDisponibles(DragEvent event) {
            
            onDragDroppedListas(event, listVDisponibles);

    }

    @FXML
    void onDragOverAbiertas(DragEvent event) {

        onDragOverListas(event , listVDisponibles);

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

        MFXListView<AccountType> listaOrigen = (MFXListView<AccountType>) event.getGestureSource();
        System.out.print("Se quito" + item + "de la lista" + listaOrigen.getId() + "\n");
        MFXListView<Account> destino = (MFXListView<Account>) event.getSource();
        System.out.print("Se agrego" + item + "a la lista" + destino.getId() + "\n");
        destino.getItems().add(new Account(item));
        listaOrigen.getItems().remove(new AccountType(item));

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