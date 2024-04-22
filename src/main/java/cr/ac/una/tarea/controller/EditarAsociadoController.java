package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.model.Associated;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditarAsociadoController extends Controller implements Initializable {

    @FXML
    public MFXButton btnEliminar;

    @FXML
    public MFXButton btnSelectPhoto;

    @FXML
    public MFXButton btnLimpiar;

    @FXML
    private MFXButton btnBuscar;

    @FXML
    private MFXButton btnActualizar;

    @FXML
    private MFXTextField txfEdad;

    @FXML
    private MFXTextField txfFolio;

    @FXML
    private MFXTextField txfNombre;

    @FXML
    private ImageView imgFoto;

    private ObservableList<Associated> asociate;

    @Override
    public void initialize() {
        asociate = ((ObservableList<Associated>) AppContext.getInstance().get("Asociados"));
        readAsociado();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    void onActionBuscar(ActionEvent event) {
        // Verificar que el MFXTextField no este vacío
        if (txfFolio.getText().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Folio", getStage(),
                    "Debe ingresar un folio");
        }
        try {
            String folio = txfFolio.getText();
            // Bucle que recorre los datos almacenados del asociado
            for (Associated associate : asociate) {
                // Verifica que el folio sea igual al que se busco
                if (associate.getFolio().equals(folio)) {
                    txfNombre.setText(associate.getName().toString());
                    txfEdad.setText(associate.getAge().toString());
                    String photo = associate.getFolio() + ".jpg";
                    String path = "./Photos/" + photo;
                    File file = new File(path);
                    // Hacer Set de la imagen en el ImageView [si la foto existe]
                    if (file.exists()) {
                        Image image = new Image(file.toURI().toString());
                        imgFoto.setImage(image);
                    }
                }
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Asociado", getStage(),
                    "Error al buscar asociado");
        }
    }

    // Hacer Set de los nuevos datos editados de los asociados y guardarlos
    @FXML
    void onActionEditar(ActionEvent event) {
        if (txfFolio.getText().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Actualizar Asociado", getStage(),
                    "Debe ingresar un folio");
            return;
        }

        String folio = txfFolio.getText();
        boolean Act = false;

        // Iterar la lista de los asociados
        for (int i = 0; i < asociate.size(); i++) {
                Associated associate = asociate.get(i);
                if (associate.getFolio().equals(folio)) {

                    associate.setName(txfNombre.getText());
                    associate.setAge(txfEdad.getText());
                    Act = true;
                    break;
                }
        }

        if (Act) {
            // Sobreescribir la lista de asociados con los nuevos datos
            try {
                // Read the image from the file
                File file = new File("Asociados.txt");
                file.createNewFile(); // Create the file if it doesn't exist

                StringBuilder sb = new StringBuilder();
                for (Associated associate : asociate) {

                    sb.append(associate.getName() + ",")
                            .append(associate.getLastName() + ",")
                            .append(associate.getFolio() + ",")
                            .append(associate.getAge() + ",")
                            .append(associate.getPhoto() + "\n");
                }
                String content = sb.toString();

                /* La librería [java.nio.file] permite trabajar con archivos y directorios
                que contiene un método estático que escribe datos en un archivo por 2 argumentos
                1. → file.toPath(), convierte el archivo en un objeto Path
                2. → content.getBytes() convierte el contenido en un byte array */
                java.nio.file.Files.write(file.toPath(), content.getBytes());

                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Actualizar Asociado", getStage(),
                        "Asociado actualizado exitosamente");
            } catch (IOException ex) {
                Logger.getLogger(EditarAsociadoController.class.getName()).log(Level.SEVERE,
                        "Error al escribir archivo", ex);
                new Mensaje().showModal(Alert.AlertType.ERROR, "Actualizar Asociado", getStage(),
                        "Error al actualizar asociado en el archivo");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Actualizar Asociado", getStage(),
                    "Asociado no encontrado!");
        }
    }

    @FXML
    public void onActionClean(ActionEvent actionEvent) {
        txfFolio.clear();
        txfEdad.clear();
        txfNombre.clear();
        imgFoto.setImage(null);
    }

    @FXML
    public void onActionEliminar(ActionEvent actionEvent) {
        if (txfFolio.getText().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Asociado", getStage(),
                    "Debe ingresar un folio");
            return;
        }

        String folio = txfFolio.getText();
        boolean AsoRemovido = false;

        // Iterar a través de la lista de asociados
        for (int i = 0; i < asociate.size(); i++) {
            Associated associate = asociate.get(i);
            if (associate.getFolio().equals(folio)) {
                asociate.remove(i);
                AsoRemovido = true;
                break;
            }
        }

        if (AsoRemovido) {
            // Borrar el asociado del txt
            try {
                File file = new File("Asociados.txt");
                file.createNewFile(); // Create the file if it doesn't exist

                // Objeto dinámico que permite expandir el número de caracteres de la cadena que encapsula
                StringBuilder sb = new StringBuilder();
                for (Associated associate : asociate) {
                    sb.append(associate.getName() + ",")
                            .append(associate.getLastName() + ",")
                            .append(associate.getFolio() + ",")
                            .append(associate.getAge() + ",")
                            .append(associate.getPhoto() + "\n");
                }
                String content = sb.toString();
                java.nio.file.Files.write(file.toPath(), content.getBytes());

                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Asociado", getStage(),
                        "Asociado eliminado exitosamente");
                // Limpia los espacios de texto después de eliminar el asociado
                txfNombre.clear();
                txfEdad.clear();
                imgFoto.setImage(null);
                txfFolio.clear();
            } catch (IOException ex) {
                Logger.getLogger(EditarAsociadoController.class.getName()).log(Level.SEVERE,
                        "Error al escribir archivo", ex);
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Asociado", getStage(),
                        "Error al eliminar asociado del archivo");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Asociado", getStage(),
                    "Asociado no encontrado!");
        }
    }

    @FXML
    public void onActionOpenFiles(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                "Image Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                // Read the new image file
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

                // Obtener el folio de el TXTTextField
                String folio = txfFolio.getText();
                String newImageFilename = folio + ".jpg";
                String newImagePath = "./Photos/" + newImageFilename;
                Files.copy(selectedFile.toPath(), Path.of(newImagePath), StandardCopyOption.REPLACE_EXISTING);

                // Get the current image (if any) from the ImageView
                Image currentImage = imgFoto.getImage();

                // Set the new image to the ImageView
                imgFoto.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", getStage(),
                        "Error al abrir la imagen");
            }
        }
    }

    public void readAsociado() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Asociados.txt"));
            String line;

            // Ciclo que recorre hasta el que archivo txt sea nulo
            while ((line = br.readLine()) != null) {
                // Separa cada parte de la información del asociado con una ','
                String[] parts = line.split(",");
                String name = parts[0];
                String lastName = parts[1];
                String folio = parts[2];
                String age = parts[3];
                String photo = parts[4];
                // Crea un objeto de asociado y agrega los argumentos a la lista
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
