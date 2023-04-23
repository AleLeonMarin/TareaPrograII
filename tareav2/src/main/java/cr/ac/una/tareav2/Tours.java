/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tareav2;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Alejandro
 */
public class Tours {

    private String name;

    public Tours(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    private List<Tours> generateTours() {

        List<Tours> tours = new ArrayList<>();

             Tours tour1 = new Tours("Descubre Olan");
        Tours tour2 = new Tours("CrossFire");
        Tours tour3 = new Tours("Paraiso Costeno");
        Tours tour4 = new Tours("Oasis en la Montana");
        Tours tour5 = new Tours("AdventureTrail");
        Tours tour6 = new Tours("Un Pase por el arte de Costa Rica");
        Tours tour7 = new Tours("Descubre San Jose en un dia");
        Tours tour8 = new Tours("Rutas a playas paradisiacas");
        Tours tour9 = new Tours("Caminando por las valles de la felicidad");
        Tours tour10 = new Tours("Rutas del Sabor");
        Tours tour11 = new Tours("Conoce el Parque de Diversiones");
        Tours tour12 = new Tours("Descubre las Nauyaca");
        Tours tour13= new Tours("aja1");
        Tours tour14= new Tours("aja2");
        Tours tour15= new Tours("aja3");
        tours.add(tour1);
        tours.add(tour2);
        tours.add(tour3);
        tours.add(tour4);
        tours.add(tour5);
        tours.add(tour6);
        tours.add(tour7);
        tours.add(tour8);
        tours.add(tour9);
        tours.add(tour10);
        tours.add(tour11);
        tours.add(tour12);
        tours.add(tour13);
        tours.add(tour14);
        tours.add(tour15);


        return tours;
    }

    public void createContent(AnchorPane rootCenter) {

        List<Tours> tours = generateTours();

        int i;
        for (i = 0; i < tours.size(); i++) {

            StackPane stackpane = new StackPane();
            AnchorPane.setTopAnchor(stackpane, 0.0);
            AnchorPane.setBottomAnchor(stackpane, 0.0);
            AnchorPane.setLeftAnchor(stackpane, 0.0);
            AnchorPane.setRightAnchor(stackpane, 0.0);

            VBox save = new VBox();
            save.setAlignment(Pos.CENTER);
            save.setSpacing(15.0);
            HBox display = new HBox();
            display.setAlignment(Pos.CENTER);
            display.setSpacing(25.0);
            HBox display2 = new HBox();
            display2.setAlignment(Pos.CENTER);
            display2.setSpacing(25.0);
            stackpane.getStyleClass().add("stack-pane-tours");

            for (int j = 0; j < 6 && i + j < tours.size(); j++) {

                VBox contenedorTour = new VBox();
                contenedorTour.setAlignment(Pos.CENTER);
                contenedorTour.setSpacing(15.0);
                Label title = new Label(tours.get(j).getName());
                StackPane carrusel = new StackPane();
                MFXButton info = new MFXButton();
                ImageView Carrusel = new ImageView();
                carrusel.getChildren().addAll(Carrusel, info);
                contenedorTour.getChildren().addAll(title, carrusel);

                if (display.getChildren().size() >= 3) {

                    display2.getChildren().add(contenedorTour);

                } else {

                    display.getChildren().add(contenedorTour);

                }
            }
            save.getChildren().addAll(display, display2);
            stackpane.getChildren().add(save);
            rootCenter.getChildren().add(stackpane);

        }
    }

}
