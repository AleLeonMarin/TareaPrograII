package cr.ac.una.tarea.controller;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditarAsociadoController extends Controller implements Initializable {

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

    private ObservableList<Associated> asociate;

    @FXML
    void onActionBtnBuscar(ActionEvent event) {

        if (txfFolio.getText().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(), "Debe ingresar un folio");
        }

        try {
            String folio = txfFolio.getText();

            for (Associated associate : asociate) {
                if (associate.getFolio().equals(folio)) {
                    txfNombre.setText(associate.getName().toString());
                    txfFechNacimeinto.setText(associate.getAge().toString());
                    String photo = associate.getFolio() + ".jpg";
                    String path = "./Photos/" + photo;
                    File file = new File(path);

                    if (file.exists()){
                        Image image = new Image(file.toURI().toString());
                        imgFoto.setImage(image);
                    }
                   
                }
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Asociado", getStage(), "Error al buscar asociado");

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

        asociate = ((ObservableList<Associated>) AppContext.getInstance().get("Asociados"));
        readAsociado();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void readAsociado() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Asociados.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String lastName = parts[1];
                String folio = parts[2];
                String age = parts[3];
                String photo = parts[4];

                Associated asociado = new Associated(name, lastName, folio, age, photo);
                asociate.add(asociado);
            }
            br.close();

        } catch (IOException ex) {

            Logger.getLogger(EditarAsociadoController.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);

        }
    }



}
