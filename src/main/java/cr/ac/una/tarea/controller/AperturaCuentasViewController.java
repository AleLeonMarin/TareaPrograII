package cr.ac.una.tarea.controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import cr.ac.una.tarea.Account;
import cr.ac.una.tarea.Associated;

public class AperturaCuentasViewController extends Controller implements Initializable{
   
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tbViewAbiertas;

    @FXML
    private TableView<?> tbViewCuentas;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txfNombre;

    Account account = new Account();
    Associated associated = new Associated();




    @Override
        public void initialize() {
            //TODO
        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
