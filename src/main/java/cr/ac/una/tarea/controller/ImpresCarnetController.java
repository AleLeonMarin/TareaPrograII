package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import cr.ac.una.tarea.model.Associated;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class ImpresCarnetController extends Controller implements Initializable {

    @FXML
    private Button btnGenerar;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txtNombre;
    
    CarnetViewController carnet = new CarnetViewController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    void onActionBtnGenerar(ActionEvent event) {
        carnet.createPdf();

    }

    @Override
    public void initialize() {
        //
    }

}
