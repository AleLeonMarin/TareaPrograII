
package cr.ac.una.tareav2;

import cr.ac.una.tareav2.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author aletr
 */
public class TourContainer extends VBox {

    private String Tour;
    private ImageView info = new ImageView(new Image("cr/ac/una/tareav2/resources/Logo.png", 100, 100, false, true));
    Label nombre = new Label();
    Label empresas = new Label();
    Label categorias = new Label();
    Label precio = new Label();
    Label fechaDeSalida = new Label();
    Label fechaDeLlegada = new Label();
    Label cupos = new Label();
    Label orden = new Label();
    Label itinerario = new Label();

    public TourContainer(String name) {
        this.nombre.setText(name);
        info.setOnMouseClicked(event -> {
            FlowController.getInstance().goViewInWindow("VistaInfo");
        });
        this.setPrefSize(300, 300);
        this.getChildren().add(nombre);
        this.getChildren().add(info);
    }

    /*
     * III. Reducir codigo, es mejor a la hora de leer y depurar
     * Separar el codigo en muchas partes.
     */
    public TourContainer(String name, String empresa, String categoria, String Precio, String FechaDeSalida,
            String FechaDellegada, String Cupos, String Orden, String Itinerario) {
        this.nombre.setText(name);
        this.empresas.setText(empresa);
        this.categorias.setText(categoria);
        this.precio.setText(Precio);
        this.fechaDeSalida.setText(FechaDeSalida);
        this.fechaDeLlegada.setText(FechaDellegada);
        this.cupos.setText(Cupos);
        this.orden.setText(Orden);
        this.itinerario.setText(Itinerario);
        this.setPrefSize(200, 200);
        this.getChildren().add(nombre);
        this.getChildren().add(empresas);
        this.getChildren().add(categorias);
        this.getChildren().add(precio);
        this.getChildren().add(fechaDeSalida);
        this.getChildren().add(fechaDeLlegada);
        this.getChildren().add(cupos);
        this.getChildren().add(orden);
        this.getChildren().add(itinerario);
    }
}
