/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tareav2.controller;

import cr.ac.una.tareav2.Tours;
import cr.ac.una.tareav2.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    private AnchorPane rootCenter;
    @FXML
    private MFXButton btnNext;
    @FXML
    private VBox space1;
    @FXML
    private VBox space2;
    @FXML
    private VBox space3;
    @FXML
    private VBox space4;
    @FXML
    private VBox space5;
    @FXML
    private VBox space6;
    @FXML
    private SplitMenuButton splitFilter;
    
    
    public static List<VBox> panes = new ArrayList<>();
    private Tours infoTours = new Tours();
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Tours tour = new Tours();
        panes.add(space1);
        panes.add(space2);
        panes.add(space3);
        panes.add(space4);
        panes.add(space5);
        panes.add(space6);
        infoTours.mostrar(true);

    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnLogIn(ActionEvent event) {
        
        FlowController.getInstance().goViewInWindow("LogInView");
        ((Stage) btnLogIn.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnBuy(ActionEvent event) {
        
        FlowController.getInstance().goViewInWindow("Register");
        ((Stage) btnBuy.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnBack(ActionEvent event) {
        
    infoTours.izquierda();    
    }

    @FXML
    private void onActionBtnNext(ActionEvent event) {
      
      infoTours.derecha();

    }

    @FXML
    private void onActionSplitFilter(ActionEvent event) {
        
        
        
    }
}
