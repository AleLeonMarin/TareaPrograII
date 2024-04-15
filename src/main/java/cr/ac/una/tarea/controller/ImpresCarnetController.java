package cr.ac.una.tarea.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import cr.ac.una.tarea.model.Associated;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ImpresCarnetController extends Controller implements Initializable {

    @FXML
    private MFXButton btnGenerar;

    @FXML
    private MFXButton btnBuscar;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txtNombre;

    ObservableList<Associated> asociate;

    @Override
    public void initialize() {

        asociate = ((ObservableList<Associated>) AppContext.getInstance().get("Asociados"));
        readAsociado();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // --
    }

    @FXML
    void onActionBtnGenerar(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("CarnetView");
    }

    @FXML
    void onActionBtnBuscar(ActionEvent event) {

        if (txfFolio.getText().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(), "Debe ingresar un folio");
        }

        try {

            String folio = txfFolio.getText();
            for (Associated asociado : asociate) {
                if (asociado.getFolio().equals(folio)) {
                    txtNombre.setText(asociado.getName() + " " + asociado.getLastName());

                }

            }

        } catch (Exception e) {
            Logger.getLogger(ImpresCarnetController.class.getName()).log(Level.SEVERE, "Error al buscar asociado", e);
        }
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