package cr.ac.una.tarea.controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AperturaCuentasViewController extends Controller implements Initializable{
    @FXML
    private AnchorPane root;

    @FXML
    private StackPane stackPane;

    @FXML
    private HBox hbox;

    @FXML
    private VBox info;

    @FXML
    private VBox cuentaspre;

    @FXML
    private VBox cuentas;

    @FXML

    private Separator separator1;

    @FXML

    private Separator separator2;

    @FXML
    private MFXTextField txfNombre;

    @FXML
    private MFXTextField txfFolio;





    @Override
        public void initialize() {
            //TODO
        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
