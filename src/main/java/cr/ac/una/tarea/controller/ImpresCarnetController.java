package cr.ac.una.tarea.controller;

import java.awt.Desktop;
import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.Chunk;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ImpresCarnetController extends Controller implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(ImpresCarnetController.class);

    @FXML
    private MFXButton btnGenerar;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txtNombre;

    @Override
    public void initialize() {
        // --
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // --
    }

    @FXML
    void onActionBtnGenerar(ActionEvent event) {
        createtPDF();
    }

    public void createtPDF() {

        // Crear un documento nuevo
        Document documento = new Document(PageSize.A4);

        try {
            // Establecer el nombre del archivo
            String ruta = System.getProperty("user.home");

            // Crear un escritor para el archivo
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Carnet.pdf"));

            Image header = Image.getInstance("./tarea/resources/PDFbackground.jpeg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("THE CARNET IS A WORD");

            // Abrir el documento
            documento.open();

            documento.add(header);
            documento.add(parrafo);

            documento.close();

            Desktop desktop = Desktop.getDesktop();
            File file = new File(ruta);
            desktop.open(file);

            // Mostrar un mensaje de confirmación
            logger.info("PDF file created successfully: {}");
        } catch (Exception ex) {
            logger.error("Error creating PDF file:", ex);
        }
    }

}