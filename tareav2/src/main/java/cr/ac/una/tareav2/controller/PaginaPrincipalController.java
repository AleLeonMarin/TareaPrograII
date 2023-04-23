/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tareav2.controller;

import cr.ac.una.tareav2.Tours;
import cr.ac.una.tareav2.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author aletr
 */
public class PaginaPrincipalController extends Controller implements Initializable {

    @FXML
    private AnchorPane rootPrincipalPage;
    @FXML
    private MFXButton btnLogIn;
    @FXML
    private MFXButton btnBuy;
    @FXML
    private MFXButton btnBack;
    @FXML
    private AnchorPane rootCenter;
    @FXML
    private MFXButton btnNext;

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Tours tour = new Tours("Tour");
        tour.createContent(rootCenter);

    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnLogIn(ActionEvent event) {
        
        FlowController.getInstance().goViewInWindow("Registro");
        ((Stage) btnLogIn.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnBuy(ActionEvent event) {
        
        FlowController.getInstance().goViewInWindow("Register");
        ((Stage) btnBuy.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnBack(ActionEvent event) {
        
        Tours tour = new Tours("tours");
        tour.createContent(rootCenter);
        
        TranslateTransition translateBack = new TranslateTransition(Duration.seconds(1),rootCenter);
        translateBack.setByX(-100);
        translateBack.play();
        
    }

    @FXML
    private void onActionBtnNext(ActionEvent event) {
        
        Tours tour = new Tours("tours");
        tour.createContent(rootCenter);
        
        TranslateTransition translateNext = new TranslateTransition(Duration.seconds(1),rootCenter);
        translateNext.setByX(100);
        translateNext.play();

    }
}
