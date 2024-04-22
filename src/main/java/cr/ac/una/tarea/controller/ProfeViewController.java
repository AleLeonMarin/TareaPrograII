package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class ProfeViewController extends Controller implements Initializable {

    @FXML
    public MFXButton btnSalir;

    @FXML
    private MFXButton btnManCuentas;

    @FXML
    private MFXButton btmManCoope;

    @FXML
    private BorderPane root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @Override
    public void initialize() {}

    @FXML
    private void onActionBtnManCuentas(ActionEvent event) {
        FlowController.getInstance().goView("ManCuentasView");
    }

    @FXML
    private void onActionBtmManCoope(ActionEvent event) {
        FlowController.getInstance().goView("ManCooperativa");
    }

    @FXML
    public void onActionBtnSalir(ActionEvent actionEvent) {
        FlowController.getInstance().salir();
    }
}
