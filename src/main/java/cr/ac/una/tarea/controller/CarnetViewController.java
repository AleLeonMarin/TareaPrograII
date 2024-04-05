package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import cr.ac.una.tarea.model.Associated;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CarnetViewController extends Controller implements Initializable {

    ArrayList<Associated> asociados = new ArrayList<>();

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imgVFoto;

    @FXML
    private ImageView imgVLogo;

    @FXML
    private Label lblCoope;

    @FXML
    private Label lblFolio;

    @FXML
    private Label lblNombre;

    @Override
    public void initialize() {
        // Empty
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Empyt
    }

}
