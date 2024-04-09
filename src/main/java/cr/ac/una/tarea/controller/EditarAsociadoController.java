package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.App;
import cr.ac.una.tarea.model.Associated;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.Observable;
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
    
   
    ObservableList<Associated> asociados = FXCollections.observableArrayList();

    ObservableList<Associated> asociado = (ObservableList<Associated>) AppContext.getInstance().get("Asociados");
   
    @FXML
    void onActionBtnBuscar(ActionEvent event) {
        
        try{

        if (txfFolio.getText().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),  "Debe ingresar un folio");
            
        }
        else {
            AppContext.getInstance().get(("Asociados"));
            for (Associated associate : asociados) {
                txfFolio.setText(associate.getFolio().toString());
                txfNombre.setText(associate.getName());
                txfFechNacimeinto.setText(String.valueOf(associate.getAge()));
                //imgFoto.setImage(associate.getPhoto();
            }
        }
        //if (txfNombre.getText().isEmpty()){
           // new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Nombre", getStage(),  "Debe ingresar un nombre");
            
        //}else{
        //    AppContext.getInstance().set("Nombre", txfNombre.getText());
        //}
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
