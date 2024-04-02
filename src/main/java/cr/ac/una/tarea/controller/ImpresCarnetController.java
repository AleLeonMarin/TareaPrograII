
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author OUTLET
 */
public class ImpresCarnetController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnGenerar;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txtNombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void onActioBtnGenerar(ActionEvent event) {

    }

    @Override
    public void initialize() {

    }

}
