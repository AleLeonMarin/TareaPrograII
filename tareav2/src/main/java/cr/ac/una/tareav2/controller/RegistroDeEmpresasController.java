/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tareav2.controller;

import cr.ac.una.tareav2.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author aletr
 */
public class RegistroDeEmpresasController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private MFXTextField txfNomEmpresa;
    @FXML
    private MFXTextField txfCedulaJur;
    @FXML
    private MFXTextField txfRuta;
    @FXML
    private MFXTextField txfTelefono;
    @FXML
    private MFXTextField txfEmail;
    @FXML
    private MFXButton btnRegistrar;
    @FXML
    private MFXButton btnCancelar;
    @FXML
    private MFXButton btnInicio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionBtnRegistrar(ActionEvent event) {
        
        
        
        
        
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        
        txfNomEmpresa.clear();
        txfCedulaJur.clear();
        txfRuta.clear();
        txfTelefono.clear();
        txfEmail.clear();
   
    }

    @FXML
    private void onActionBtnInicio(ActionEvent event) {
        
        FlowController.getInstance().goViewInWindow("LogInView");
        
    }
    
}
