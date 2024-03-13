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
public class AsociadosViewController extends Controller implements Initializable {

    @FXML
    private BorderPane BorderPane;
    @FXML
    private MFXButton btnRegistrar;
    @FXML
    private MFXButton btnDepositos;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private MFXButton btnConsultas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionBtnRegistrar(ActionEvent event) {

        FlowController.getInstance().goView("RegistrarAsociado");
    }

    @FXML
    private void onActionBtnDepositos(ActionEvent event) {

        FlowController.getInstance().goView("RetirDepos");
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {

        FlowController.getInstance().salir();
    }

    @FXML
    private void onActionBtnConsultas(ActionEvent event) {

    }

    @Override
    public void initialize() {
        
    }
    
}
