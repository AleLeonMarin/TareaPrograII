package cr.ac.una.tarea.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import cr.ac.una.tarea.model.Associated;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class ConsultaCuentaController extends Controller implements Initializable {

    @FXML
    private MFXButton btnBuscar;

    @FXML
    private MFXButton btnDetallado;

    @FXML
    private MFXButton btnResumido;

    @FXML
    private MFXComboBox<?> cmbCuentas;

    @FXML
    private MFXTextField txfNombre;

    @FXML
    private MFXTextField txfFolio;

    private ObservableList<Associated> asociate;

    @Override
    public void initialize() {
        asociate = ((ObservableList<Associated>) AppContext.getInstance().get("Asociados"));
        readAsociado();
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
                    txfNombre.setText(asociado.getName());
                }
            }

        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(), "Error al buscar el folio");
        }

    }

    @FXML
    void onActionBtnDetallado(ActionEvent event) {

    }

    @FXML
    void onActionBtnResumido(ActionEvent event) {

    }

    @FXML
    void onActionCmbCuentas(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

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

        } catch (IOException e) {

            Logger.getLogger(ImpresCarnetController.class.getName()).log(Level.SEVERE, "Error al buscar asociado", e);

        }

    }
}