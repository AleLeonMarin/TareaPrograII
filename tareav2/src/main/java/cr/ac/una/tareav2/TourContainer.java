/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public TourContainer(String name) {

        this.nombre.setText(name);
        info.setOnMouseClicked(event -> {

            FlowController.getInstance().goViewInWindow("VistaInfo");

        });
        this.setPrefSize(300, 300);
        this.getChildren().add(nombre);
        this.getChildren().add(info);

    }
}
