package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.util.FlowController;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onActionBtnCamera(ActionEvent event) {

    }

}
