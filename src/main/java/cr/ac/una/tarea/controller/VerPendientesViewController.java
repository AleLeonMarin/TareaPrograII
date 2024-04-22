package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class VerPendientesViewController extends Controller implements Initializable{

    @FXML
    private TableView<?> tbVPendientes;

    @FXML
   private MFXButton btnVerDeposito;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    @FXML

    void onActionBtnVerDeposito(ActionEvent event) {
        // TODO Auto-generated method stub

        FlowController.getInstance().goViewInWindow("DepositoPendiente");
        
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        
    }

}
