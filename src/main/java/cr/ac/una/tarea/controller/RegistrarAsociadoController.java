package cr.ac.una.tarea.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarAsociadoController extends Controller implements Initializable{

    @FXML
    private MFXButton btnFoto;

    @FXML
    private ImageView imgVFoto;

    @FXML
    private MFXTextField txfEdad;

    @FXML
    private MFXTextField txfNombre;



    @Override
    public void initialize() {
        //TODO
    }

    @FXML
    void onActionBtnFoto(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
