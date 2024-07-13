/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aletr
 */
public class LogInViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView wallpaper;
    @FXML
    private MFXButton btnProfes;
    @FXML
    private MFXButton btnFuncionarios;
    @FXML
    private MFXButton btnAsociados;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wallpaper.fitHeightProperty().bind(root.heightProperty());
        wallpaper.fitWidthProperty().bind(root.widthProperty());
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnProfes(ActionEvent event) {
        FlowController.getInstance().goMain("ProfeView");
        ((Stage) btnProfes.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnFuncionarios(ActionEvent event) {
        FlowController.getInstance().goMain("FuncionariosView");
        ((Stage) btnFuncionarios.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnAsociados(ActionEvent event) {
        FlowController.getInstance().goMain("AsociadosView");
        ((Stage) btnAsociados.getScene().getWindow()).close();
    }

}
