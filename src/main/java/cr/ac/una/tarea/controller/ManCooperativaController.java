package cr.ac.una.tarea.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import cr.ac.una.tarea.model.Cooperativa;
import cr.ac.una.tarea.util.Mensaje;

public class ManCooperativaController extends Controller implements Initializable {

    @FXML
    private MFXButton btnLogo;

    @FXML
    private ImageView imgvLogo;

    @FXML
    private MFXButton btnGuardar;

    @FXML
    private MFXTextField txfNomCooperativa;
    String logoPath;
    Cooperativa cooperativa = new Cooperativa();

    @Override
    public void initialize() {

    }

    @FXML
    void onActionBtnGuardar(ActionEvent event) {

        try {
            if (txfNomCooperativa.getText().isEmpty() || txfNomCooperativa.getText().isBlank()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Cooperativa", getStage(),
                        "El campo nombre de la cooperativa no puede estar vacio");
            } else if (imgvLogo.getImage().equals(null)) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Registrar Cooperativa", getStage(),
                        "El campo logo de la cooperativa no puede estar vacio");

            } else {

                Cooperativa cooperativa = new Cooperativa(txfNomCooperativa.getText(), imgvLogo.getImage().getUrl());
                cooperativa.setCooperativa(cooperativa);
                cooperativa.createFile(cooperativa);
                Controller.iconChanger(getStage(), imgvLogo.getImage());
                Controller.nameChanger(getStage(), txfNomCooperativa.getText());
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Registrar Cooperativa", getStage(),
                        "Cooperativa registrada con exito");
            }
        } catch (Exception ex) {
            Logger.getLogger(ManCooperativaController.class.getName()).log(Level.SEVERE, "Error loading image", ex);
        }

    }

    @FXML
    void onActionBtnLogo(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escoga el logo de la cooperativa");
        File selectedFile = fileChooser.showOpenDialog(null);
        
        // Construct the relative path within the project
        String relativePath = "./CoopeLogos/" + selectedFile.getName();
        
        try {
           String path = "./CoopeLogos";
            File folder = new File(path);
            if (!folder.exists()) {
                // Create the file
                folder.mkdir();
            }
        
            // Move the image file
            Path source = Paths.get(selectedFile.getPath());
            Path destiny = Paths.get(path, selectedFile.getName());
            Files.move(source, destiny);
            System.out.println("File moved successfully");
        
            // Use the absolute path (including project directory) to set the image
            String absolutePath = "file:" +  relativePath;
            Image icon = new Image(absolutePath);
            imgvLogo.setImage(icon);
        
        } catch (Exception ex) {
            Logger.getLogger(ManCooperativaController.class.getName()).log(Level.SEVERE, "Error moving image", ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
