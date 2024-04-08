package cr.ac.una.tarea.controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;
import cr.ac.una.tarea.model.Account;

public class AperturaCuentasViewController extends Controller implements Initializable {

    @FXML
    private TableView<?> tbViewAbiertas;

    @FXML
    private TableView<?> tbViewCuentas;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txfNombre;

    Account account = new Account();

    @Override
    public void initialize() {
        // --
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // --
    }
}
