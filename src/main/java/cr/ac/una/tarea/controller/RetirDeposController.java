package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class RetirDeposController extends Controller implements Initializable {

    @FXML
    public MFXComboBox<String> cmbBuscarAsociado;

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
    void onActionCmbCombox(ActionEvent event) {

    }

    // Funciones que determinan si es 0 v != 0
    public boolean VerificarCinco(){
        return (SpinnerCinco.getValue() == 0) ? false : true;
    }

    public boolean VerificarDiez(){
        return (SpinnerDiez.getValue() == 0) ? false : true;
    }

    public boolean VerificarVeintiCinco(){
        return (SpinnerVeintiCinco.getValue() == 0) ? false : true;
    }

    public boolean VerificarCincuenta(){
        return (SpinnerCincuenta.getValue() == 0) ? false : true;
    }

    public boolean VerificarCien(){
        return (SpinnerCien.getValue() == 0) ? false : true;
    }

    public boolean VerificarQuinientos(){
        return (SpinnerQuinientos.getValue() == 0) ? false : true;
    }

    public boolean VerificarMil(){
        return (SpinnerMil.getValue() == 0) ? false : true;
    }

    public boolean VerificarDosMil(){
        return (SpinnerDosMil.getValue() == 0) ? false : true;
    }

    public boolean VerificarCincoMil(){
        return (SpinnerCincoMil.getValue() == 0) ? false : true;
    }

    public boolean VerificarDiezMil(){
        return (SpinnerDiezmil.getValue() == 0) ? false : true;
    }

    public boolean VerificarVeinteMil(){
        return (SpinnerVeintemil.getValue() == 0) ? false : true;
    }

    // Funciones que multiplican el valor del spinner por su denominacion
    public int SaveSpinnerCinco() {
        return VerificarCinco() ? SpinnerCinco.getValue() * 5 : 0;
    }

    public int SaveSpinnerDiez() {
        return VerificarDiez() ? SpinnerDiez.getValue() * 10 : 0;
    }

    public int SaveSpinnerVeintiCinco() {
        return VerificarVeintiCinco() ? SpinnerVeintiCinco.getValue() * 25 : 0;
    }

    public int SaveSpinnerCincuenta() {
        return VerificarCincuenta() ? SpinnerCincuenta.getValue() * 50 : 0;
    }

    public int SaveSpinnerCien() {
        return VerificarCien() ? SpinnerCien.getValue() * 100 : 0;
    }

    public int SaveSpinnerQuinientos() {
        return VerificarQuinientos() ? SpinnerQuinientos.getValue() * 500 : 0;
    }

    public int SaveSpinnerMil() {
        return VerificarMil() ? SpinnerMil.getValue() * 1000 : 0;
    }

    public int SaveSpinnerDosMil() {
        return VerificarDosMil() ? SpinnerDosMil.getValue() * 2000 : 0;
    }

    public int SaveSpinnerCincoMil() {
        return VerificarCincoMil() ? SpinnerCincoMil.getValue() * 5000 : 0;
    }

    public int SaveSpinnerDiezMil() {
        return VerificarDiezMil() ? SpinnerDiezmil.getValue() * 10000 : 0;
    }

    public int SaveSpinnerVeinteMil() {
        return VerificarVeinteMil() ? SpinnerVeintemil.getValue() * 20000 : 0;
    }

    // Suma los valores finales de todos los spinners
    public int SumarTodo(){
        return  SaveSpinnerCinco() + SaveSpinnerDiez() + SaveSpinnerVeintiCinco() +
                SaveSpinnerCincuenta() + SaveSpinnerCien() + SaveSpinnerQuinientos() +
                SaveSpinnerMil() + SaveSpinnerDosMil() + SaveSpinnerCincoMil() +
                SaveSpinnerDiezMil() + SaveSpinnerVeinteMil();
    }

    @FXML
    void onActionDepositar(ActionEvent event) {
        new Mensaje().showModal(Alert.AlertType.ERROR, "Depositos", getStage(),
                "El dinero depositado es de " + SumarTodo());
    }

    @FXML
    void onActionBuscarAsoc(ActionEvent actionEvent) {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactory1 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory1.setValue(0);

        SpinnerValueFactory<Integer> valueFactory2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory2.setValue(0);

        SpinnerValueFactory<Integer> valueFactory3 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory3.setValue(0);

        SpinnerValueFactory<Integer> valueFactory4 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory4.setValue(0);

        SpinnerValueFactory<Integer> valueFactory5 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory5.setValue(0);

        SpinnerValueFactory<Integer> valueFactory6 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory6.setValue(0);

        SpinnerValueFactory<Integer> valueFactory7 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory7.setValue(0);

        SpinnerValueFactory<Integer> valueFactory8 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory8.setValue(0);

        SpinnerValueFactory<Integer> valueFactory9 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory9.setValue(0);

        SpinnerValueFactory<Integer> valueFactory10 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory10.setValue(0);

        SpinnerValueFactory<Integer> valueFactory11 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory11.setValue(0);

        SpinnerValueFactory<Integer> valueFactory12 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory12.setValue(0);

        SpinnerVeintemil.setValueFactory(valueFactory1);
        SpinnerCien.setValueFactory(valueFactory2);
        SpinnerCinco.setValueFactory(valueFactory3);
        SpinnerCincoMil.setValueFactory(valueFactory4);
        SpinnerCincuenta.setValueFactory(valueFactory5);
        SpinnerDiezmil.setValueFactory(valueFactory6);
        SpinnerDosMil.setValueFactory(valueFactory7);
        SpinnerMil.setValueFactory(valueFactory8);
        SpinnerQuinientos.setValueFactory(valueFactory9);
        SpinnerVeintiCinco.setValueFactory(valueFactory10);
        SpinnerVeintemil.setValueFactory(valueFactory11);
        SpinnerDiez.setValueFactory(valueFactory12);
    }

    @FXML
    public void onActionSelectCuenta(ActionEvent actionEvent) {

    }
}