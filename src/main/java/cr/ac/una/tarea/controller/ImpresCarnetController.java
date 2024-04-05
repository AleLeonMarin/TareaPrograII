package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ImpresCarnetController extends Controller implements Initializable {

    @FXML
    private MFXButton btnGenerar;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txtNombre;

    @FXML
    void onActionBtnGenerar(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("CarnetView");
    }

    @Override
    public void initialize() {
        // --
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // --
    }

}
