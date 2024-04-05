package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class ImpresCarnetController extends Controller implements Initializable {

    @FXML
    private Button btnGenerar;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txtNombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, null)
    }

    @FXML
    void onActionBtnGenerar(ActionEvent event) {
        // TODO
    }

    @Override
    public void initialize() {
        //
    }

}
