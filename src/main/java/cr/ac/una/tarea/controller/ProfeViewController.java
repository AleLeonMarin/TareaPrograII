/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
  * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author aletr
 */
public class ProfeViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnManCuentas;
    @FXML
    private MFXButton btmManCoope;
    @FXML
    private BorderPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void onActionBtnManCuentas(ActionEvent event) {

        FlowController.getInstance().goView("ManCuentasView");
    }

    @FXML
    private void onActionBtmManCoope(ActionEvent event) {

        FlowController.getInstance().goView("ManCooperativa");
        
    }
    
}
