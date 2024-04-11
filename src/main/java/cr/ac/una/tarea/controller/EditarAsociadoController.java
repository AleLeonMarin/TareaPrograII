package cr.ac.una.tarea.controller;


import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ResourceBundle;

public class EditarAsociadoController extends Controller implements Initializable{

    @FXML
    private MFXButton btnBuscar;

    @FXML
    private MFXButton btnFoto;

    @FXML
    private MFXButton btnGuardar;

    @FXML
    private ImageView imgFoto;

    @FXML
    private StackPane root;
    
    @FXML
    private MFXTextField txfFechNacimeinto;
    
    @FXML
    private MFXTextField txfFolio;
    
    @FXML
    private MFXTextField txfNombre;

   
    @FXML
    void onActionBtnBuscar(ActionEvent event) {
        
        try{

        if (txfFolio.getText().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),  "Debe ingresar un folio");
            
        }
        else {
           
        }
        
    }catch (Exception e){
        new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Asociado", getStage(),  "Error al buscar asociado");

    }
}

    @FXML
    void onActionBtnFoto(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("Camera");
    }

    @FXML
    void onActionBtnGuardar(ActionEvent event) {

    }

    @Override
    public void initialize() {

        AppContext.getInstance().get("Asociados");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    
}
