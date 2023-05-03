package cr.ac.una.tareav2.controller;

import cr.ac.una.tareav2.Tours;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author aletr
 */
public class VistaInfoController extends Controller implements Initializable {

    /*
     * Tratemos siempre de separar la logica de la vista del controlador
     */
    public static List<HBox> pane = new ArrayList<>();
    private Tours infomaTours = new Tours();

    @FXML
    private HBox pane1;
    @FXML
    private HBox pane2;
    @FXML
    private HBox pane3;
    @FXML
    private HBox pane4;
    @FXML
    private HBox pane5;
    @FXML
    private MFXButton btnCancelar;
    @FXML
    private MFXButton btnComprar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Tours tours = new Tours();
        pane.add(pane1);
        pane.add(pane2);
        pane.add(pane3);
        pane.add(pane4);
        pane.add(pane5);
        infomaTours.mostrarInfo(true);
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnCancelar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnComprar(ActionEvent event) {
    }

}
