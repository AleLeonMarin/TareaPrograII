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
    private MFXButton btnConsultaDetallado;
    @FXML
    private MFXButton btnConsultaResumido;
    @FXML
    private MFXButton btnJuego;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void onActionBtnRegistrar(ActionEvent event) {

        FlowController.getInstance().goView("RegistrarAsociado");
    }

    @FXML
    private void onActionBtnDepositos(ActionEvent event) {

        FlowController.getInstance().goView("DepositoAsociados");
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {

        FlowController.getInstance().salir();
    }

    @FXML
    private void onActionBtnConsultaDetallado(ActionEvent event) {

        FlowController.getInstance().goView("EstadoDetalloView");

    }
    @FXML
    private void onActionBtnConsultaResumido(ActionEvent event) {

        FlowController.getInstance().goView("EstadoResumidoView");

    }

    @FXML
    private void onActionBtnJuego(ActionEvent event) {

        FlowController.getInstance().goView("GameView");

    }

    @Override
    public void initialize() {
        // --
    }

}
