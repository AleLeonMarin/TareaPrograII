package cr.ac.una.tarea.controller;

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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import io.github.palexdev.materialfx.controls.models.spinner.IntegerSpinnerModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RetirDeposController extends Controller implements Initializable {

        @FXML
        public MFXComboBox<String> cmbBuscarAsociado;

        @FXML
        public MFXButton btnRetirar;

        @FXML
        private MFXButton btnDepositar;

        @FXML
        private MFXComboBox<?> cmbCuentas;

        @FXML
        private Spinner<Integer> SpinnerCien;

        @FXML
        private Spinner<Integer> SpinnerCinco;

        @FXML
        private Spinner<Integer> SpinnerDiez;

        @FXML
        private Spinner<Integer> SpinnerCincoMil;

        @FXML
        private Spinner<Integer> SpinnerCincuenta;

        @FXML
        private Spinner<Integer> SpinnerDiezmil;

        @FXML
        private Spinner<Integer> SpinnerDosMil;

        @FXML
        private Spinner<Integer> SpinnerMil;

        @FXML
        private Spinner<Integer> SpinnerQuinientos;

        @FXML
        private Spinner<Integer> SpinnerVeintemil;

        @FXML
        private Spinner<Integer> SpinnerVeintiCinco;

        @FXML
        private MFXButton btnBuscar;

        @FXML
        private MFXTextField txfNomAsociado;

        @FXML
        private MFXTextField txfFolio;

        private ObservableList<Associated> asociate;

        @Override
        public void initialize() {

                asociate = ((ObservableList<Associated>) AppContext.getInstance().get("Asociados"));
                readAsociado();
        }

        @FXML
        void onActionCmbCombox(ActionEvent event) {

        }

        @FXML
        void onActionBtnBuscar(ActionEvent event) {

                if (txfFolio.getText().isEmpty()) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),
                                        "Debe ingresar un folio");
                }

                try {

                        String folio = txfFolio.getText();

                        for (Associated associated : asociate) {
                                if (associated.getFolio().equals(folio)) {
                                        txfNomAsociado.setText(associated.getName().toString());
                                }
                        }
                } catch (Exception e) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Asociado", getStage(),
                                        "Error al buscar asociado");

                }
        }

        // Suma los valores finales de todos los spinners
        public int SumarTodo() {
                return SpinnerCinco.getValue() * 5 + SpinnerDiez.getValue() * 10 + SpinnerVeintiCinco.getValue() * 25 +
                                SpinnerCincuenta.getValue() * 50 + SpinnerCien.getValue() * 100
                                + SpinnerQuinientos.getValue() * 500 +
                                SpinnerMil.getValue() * 1000 + SpinnerDosMil.getValue() * 2000
                                + SpinnerCincoMil.getValue() * 5000 +
                                SpinnerDiezmil.getValue() * 10000 + SpinnerVeintemil.getValue() * 10000;
        }

        @FXML
        void onActionDepositar(ActionEvent event) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Depositos", getStage(),
                                "El dinero depositado es de " + SumarTodo());
        }

        @FXML
        void onActionBuscarAsoc(ActionEvent actionEvent) {

        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                // Limites de escogencia + Set valor inicial del spinner -> min, max,
                // initialValue

                SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                                0);
                SpinnerCinco.setValueFactory(valueFactory2);

                SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                                0);
                SpinnerDiez.setValueFactory(valueFactory3);

                SpinnerValueFactory<Integer> valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                                0);
                SpinnerVeintiCinco.setValueFactory(valueFactory4);

                SpinnerValueFactory<Integer> valueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                                0);
                SpinnerCincuenta.setValueFactory(valueFactory5);

                SpinnerValueFactory<Integer> valueFactory6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                                0);
                SpinnerCien.setValueFactory(valueFactory6);

                SpinnerValueFactory<Integer> valueFactory7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                                0);
                SpinnerQuinientos.setValueFactory(valueFactory7);

                SpinnerValueFactory<Integer> valueFactory8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                                0);
                SpinnerMil.setValueFactory(valueFactory8);

                SpinnerValueFactory<Integer> valueFactory9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                                0);
                SpinnerDosMil.setValueFactory(valueFactory9);

                SpinnerValueFactory<Integer> valueFactory10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                                0);
                SpinnerCincoMil.setValueFactory(valueFactory10);

                SpinnerValueFactory<Integer> valueFactory11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                                0);
                SpinnerDiezmil.setValueFactory(valueFactory11);

                SpinnerValueFactory<Integer> valueFactory12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
                                0);
                SpinnerVeintemil.setValueFactory(valueFactory12);

        }

        @FXML
        public void onActionSelectCuenta(ActionEvent actionEvent) {

        }

        @FXML
        public void onActionRetirar(ActionEvent actionEvent) {

        }

        private void readAsociado() {

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

                        Logger.getLogger(EditarAsociadoController.class.getName()).log(Level.SEVERE,
                                        "Error al leer archivo", ex);

                }
        }

}