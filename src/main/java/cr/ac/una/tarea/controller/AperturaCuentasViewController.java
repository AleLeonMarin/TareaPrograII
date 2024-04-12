package cr.ac.una.tarea.controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class AperturaCuentasViewController extends Controller implements Initializable {

    @FXML
    private TableView<Account> tbViewAbiertas;

    @FXML
    private TableView<AccountType> tbViewCuentas;

    @FXML
    private TableColumn<AccountType, String> tbDisponibles;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txfNombre;

    Account account = new Account();

    private ObservableList<AccountType> accountType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void initialize() {
        accountType = ((ObservableList<AccountType>) AppContext.getInstance().get("TiposCuentas"));
        readAccount();
        loadInfo();
    }

    private void tableViewsDragEvents() {

        /*Arrastre para la fila de las Cooperativas*/
        tbViewAbiertas.setRowFactory((TableView<Account> e) -> {
            TableRow<Account> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (!row.isEmpty()) {
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.putString(mensajeDragAndDrop("C", cuentasApertura.indexOf(row.getItem())));
                    db.setContent(cc);
                    event.consume();
                    // Lo de cuentasApertura solucionar & Cuentas abiertas
                }
            });
            return row;
        });

        /*Arrastre para la fila de las cuentas Abiertas (Asociado)*/
        tbViewCuentas.setRowFactory((TableView<AccountType> e) -> {
            TableRow<AccountType> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (!row.isEmpty()) {
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.putString(mensajeDragAndDrop("A", cuentasAbiertas.indexOf(row.getItem())));
                    db.setContent(cc);
                    event.consume();
                }
            });
            return row;
        });

        /*Tabla de Cuentas Abiertas*/
        tbViewAbiertas.setOnDragOver(eh -> {
            Dragboard db = eh.getDragboard();
            String s = eh.getDragboard().getString();
            if (s.charAt(0) == 'C'){
                eh.acceptTransferModes(TransferMode.MOVE);
            }
            eh.consume();
        });

        tbViewAbiertas.setOnDragDropped(eh -> {
            Dragboard db = eh.getDragboard();
            String s = eh.getDragboard().getString();
            s = s.substring(1);
            if (eh.getDragboard().hasString()) {
                CuentaDto cuenta = cuentasApertura.get(Integer.parseInt(s));
                cuentasAbiertas.add(cuenta);
                tbViewAbiertas.refresh();
                eh.setDropCompleted(true);
            }
            else {
                eh.setDropCompleted(false);
            }
            eh.consume();
        });
    }

    private String mensajeDragAndDrop(String s, int pos) {
        /*Codigo de transferencia.*/
        /*Se usa para distinguir y filtrar lo que se manda en el string del Drag And Drop */
        String respuesta = new String();
        respuesta = s + pos;
        return respuesta;
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
            
            Logger.getLogger(EditarAsociadoController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);
            
        }
    }
    private void loadInfo(){
        accountType = ((ObservableList<AccountType>) AppContext.getInstance().get("TiposCuentas"));

        tbDisponibles.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbViewCuentas.setItems(accountType);
        tbViewCuentas.refresh();

    }
}