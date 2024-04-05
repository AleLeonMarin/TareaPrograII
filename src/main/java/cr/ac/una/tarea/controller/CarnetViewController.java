package cr.ac.una.tarea.controller;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import cr.ac.una.tarea.model.Associated;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

public class CarnetViewController extends Controller implements Initializable {

    //ArrayList<Associated> asociados = new ArrayList<>();

    Associated asociado = new Associated();
    @FXML
    private AnchorPane root;
    
    @FXML
    private ImageView imgVFoto;

    @FXML
    private ImageView imgVLogo;

    @FXML
    private Label lblCoope;

    @FXML
    private Label lblFolio;

    @FXML
    private Label lblNombre;

    @Override
    public void initialize() {

        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createPdf();
    }

    public void createPdf(){

        try {
            Document documento = new Document(PageSize.A4);
            PdfWriter.getInstance(documento, new FileOutputStream("Carnet" + asociado.getName() + asociado.getFolio() + ".pdf"));
            documento.open();

            WritableImage image = root.snapshot(new SnapshotParameters(), null);
            File output = new File("Carnet" + asociado.getName() + asociado.getFolio() + ".png");

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", output);
            float pageWidth = documento.getPageSize().getWidth();
            float pageHeight = documento.getPageSize().getHeight();
            BufferedImage bufferedImage = ImageIO.read(output);
            float imageWidth = bufferedImage.getWidth();
            float imageHeight = bufferedImage.getHeight();
            float scale = Math.min(pageWidth / imageWidth, pageHeight / imageHeight);

            Image imagen = Image.getInstance(output.getPath());
            imagen.scaleToFit(imageWidth * scale, imageHeight * scale);
            imagen.setAlignment(Image.ALIGN_CENTER);
            documento.add(imagen);

            documento.close();

            Desktop desktop = Desktop.getDesktop();

            File file = new File("Carnet" + asociado.getName() + asociado.getFolio() + ".pdf");
            desktop.open(file);

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
