package cr.ac.una.tareav2;

import cr.ac.una.tareav2.controller.PaginaPrincipalController;
import cr.ac.una.tareav2.controller.VistaInfoController;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Alejandro
 */
public class Tours {

    /* 
        Recomendacion de pasar todo el codigo a ingles,
        ya que mejora la lectura entre varios programadores.
     */
    private int indice = 0;
    private String name, empresa, categoria, precio,
            FechadeSalida,
            FechadeRegreso,
            Orden,
            cordenadas;

    /*
        name, company, category, price, departureDate, returnDate, order, coordinates
     */
    List<TourContainer> tours = new ArrayList<>();
    List<TourContainer> information = new ArrayList<>();

    /*
        Lista de tours
     */
    List<String> tourList;

    public Tours() {
        generateTours();
        generateInfo();
    }

    /*
        El constructor tiene demasiados parametros, dificulta la extensibilidad del codigo.
        Cambiar por un constructor con pocos parametros pero con metodos aparte para agregar
        los atributos que necesite, esto se puede mejorar con el patron de diseno builder.
    
        https://refactoring.guru/design-patterns/builder
     */
    public Tours(String name, String empresa, String categoria, Double precio, String FechadeSalida, String FechadeRegreso, String Orden, String coordenadas) {
        this.name = name;
        this.empresa = empresa;
        this.categoria = categoria;
        this.precio = precio.toString();
        this.FechadeSalida = FechadeSalida;
        this.FechadeRegreso = FechadeRegreso;
        this.Orden = Orden;
        this.cordenadas = coordenadas;
    }

    /*
        Esto se puede mejorar poniendo los nombres de los tours en una lista,
        asi se saben que tours hay, y se reduce el codigo, recorriendo la lista.
     */
    private List<String> createTourList() { 
       return tourList = new ArrayList<String>();
    }
    
    private List<String> fillTourList() {
        tourList.add("Descubre Olan");
        tourList.add("Paraiso Costeno");
        /*
            ... continua
        */ 
        return tourList;
    }

    private void generateTours() {
        tours.add(new TourContainer("Descubre Olan"));
        tours.add(new TourContainer("Paraiso Costeno"));
        tours.add(new TourContainer("CrossFire"));
        tours.add(new TourContainer("Oasis en la Montana"));
        tours.add(new TourContainer("AdventureTrail"));
        tours.add(new TourContainer("Un Paseo por el arte de Costa Rica"));
        tours.add(new TourContainer("Descubre San Jose en un dia"));
        tours.add(new TourContainer("Rutas a playas paradisiacas"));
        tours.add(new TourContainer("Caminando por las valles de la felicidad"));
        tours.add(new TourContainer("Rutas del Sabor"));
        tours.add(new TourContainer("Conoce el Parque de Diversiones"));
        tours.add(new TourContainer("Descubre las Nauyaca"));
    }

    /*
        Se vuelve muy dificil de leer y mantener, reducir los parametros.
    */
    private void generateInfo() {
        information.add(new TourContainer(" Nombre : Descubre Olan ", "Empresa: Ecotours ", "Categotia: Cultura y Aventura ", "Precio: 27 500 colones", "Fecha de Salida: 10 de mayo ", "Fecha de llegada: 10 de mayo", "Orden: Desayuno(7:00 - 8 :00),Conocimiento de la capilla (10:30 a 12:00), Visita al cementerio de muertos vivientes(10:30 - 12:00), Almuerzo (12:00 - 13:00),Senderismo con visita a una catarata(13:00 - 15:00", "pene", "pene2"));
        information.add(new TourContainer(" Nombre : Paraiso Costeno", "Empresa :GoodTravel", "Categoria : Playa", "Precio : 15 000 colones ", "Fecha de Salida : 15 de mayo ", "Fecha de Llegada : 15 de mayo", "Cupos : 27", "Desayuno(8:30 - 9 :30),Kayak (10:30 a 12:00), Almuerzo (12:00 - 13:00),Estadia en la playa (13:00 - 16:00)", "Itinerario •	Tour playa (incluye transporte, esnórquel y kayak), Lugar : Parque Nacional Corcovado , Puntarenas , Fecha de Llegada : 07 de mayo , Hora de llegada 8:30 , Hora de  salida : 4:00 pm , Duracion en el lugar : 10 horas con 30 minutos , Orden : Desayuno(8:30 - 9 :30),Kayak (10:30 a 12:00), Almuerzo (12:00 - 13:00),Estadia en la playa (13:00 - 16:00)"));
        information.add(new TourContainer(" Nombre : CrossFire", "Empresa :XtremeTours ", "Categoria : Aventura", "Precio : 40 000 colones ", "Fecha de Salida : 13 de junio ", "Fecha de Llegada : 13 de junio", "Cupos :32 ", "Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)", "Itinerario Tour Aventura (incluye trasnporte desde San Isidro del General y almuerzo), Lugar : Rivas, Perez Zeledon , San Jose , Fecha de Llegada : 13 de junio , Hora de llegada : 11:00 , Hora de  salida : 17:30 pm , Duracion en el lugar : 6 horas con 30 minutos , Orden : Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)"));
        information.add(new TourContainer("Nombre : Oasis en la Montana", "Empresa : LionTours ", "Categoria : Naturaleza y Aventura", "Precio : 30 000 colones ", "Fecha de Salida : 20 de junio ", "Fecha de Llegada : 20 de junio", "Cupos : 30 ", "Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)", "Itinerario Tour Aventura (incluye trasnporte desde San Isidro del General y almuerzo), Lugar : Rivas, Perez Zeledon , San Jose , Fecha de Llegada : 13 de junio , Hora de llegada : 11:00 , Hora de  salida : 17:30 pm , Duracion en el lugar : 6 horas con 30 minutos , Orden : Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)"));
        information.add(new TourContainer("Nombre : AdventureTrail", "Empresa : MaconTours ", "Categoria : Aventura", "Precio : 72 000 colones ", "Fecha: 7 de Salida : 3 de julio ", "Fecha de Llegada : 3 de julio", "Cupos : 20 ", "Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)", "Itinerario Tour Aventura (incluye trasnporte desde San Isidro del General y almuerzo), Lugar : Rivas, Perez Zeledon , San Jose , Fecha de Llegada : 13 de junio , Hora de llegada : 11:00 , Hora de  salida : 17:30 pm , Duracion en el lugar : 6 horas con 30 minutos , Orden : Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)"));
        information.add(new TourContainer("Nombre : Un Paseo por el arte de Costa Rica", "Empresa : PaseAndo ", "Categoria : Historico", "Precio : 50 000 colones ", "Fecha de Salida : 10 de julio ", "Fecha de Llegada : 10 de julio", "Cupos : 15 ", "Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)", "Itinerario Tour Aventura (incluye trasnporte desde San Isidro del General y almuerzo), Lugar : Rivas, Perez Zeledon , San Jose , Fecha de Llegada : 13 de junio , Hora de llegada : 11:00 , Hora de  salida : 17:30 pm , Duracion en el lugar : 6 horas con 30 minutos , Orden : Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)"));
        information.add(new TourContainer("Nombre : Descubre San Jose en un dia ", "Empresa :MarinTours ", "Categoria : Cultural", "Precio : 25 000 colones ", "Fecha de Salida : 21 de julio ", "Fecha de Llegada : 21 de julio", "Cupos :20 ", "Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)", "Itinerario Tour Aventura (incluye trasnporte desde San Isidro del General y almuerzo), Lugar : Rivas, Perez Zeledon , San Jose , Fecha de Llegada : 13 de junio , Hora de llegada : 11:00 , Hora de  salida : 17:30 pm , Duracion en el lugar : 6 horas con 30 minutos , Orden : Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)"));
        information.add(new TourContainer("Nombre : Rutas a playas paradisiacas", "Empresa :CostaRican Tours ", "Categoria : Playa", "Precio : 45 000 colones ", "Fecha de Salida : 27 de julio ", "Fecha de Llegada : 27 de julio", "Cupos : 30 ", "Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)", "Itinerario Tour Aventura (incluye trasnporte desde San Isidro del General y almuerzo), Lugar : Rivas, Perez Zeledon , San Jose , Fecha de Llegada : 13 de junio , Hora de llegada : 11:00 , Hora de  salida : 17:30 pm , Duracion en el lugar : 6 horas con 30 minutos , Orden : Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)"));
        information.add(new TourContainer("Nombre : Caminando por los valles de la felicidad", "Empresa :Explore Tours ", "Categoria : Aventura", "Precio : 55 000 colones ", "Fecha de Salida : 6 de agosto ", "Fecha de Llegada : 6 de agosto ", "Cupos :20 ", "Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)", "Itinerario Tour Aventura (incluye trasnporte desde San Isidro del General y almuerzo), Lugar : Rivas, Perez Zeledon , San Jose , Fecha de Llegada : 13 de junio , Hora de llegada : 11:00 , Hora de  salida : 17:30 pm , Duracion en el lugar : 6 horas con 30 minutos , Orden : Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)"));
        information.add(new TourContainer("Nombre : Rutas del Sabor", "Empresa :GastroTours ", "Categoria : Gastronomico", "Precio : 50 000 colones ", "Fecha de Salida : 8 de octubre ", "Fecha de Llegada : 8 de octubre ", "Cupos :15 ", "Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)", "Itinerario Tour Aventura (incluye trasnporte desde San Isidro del General y almuerzo), Lugar : Rivas, Perez Zeledon , San Jose , Fecha de Llegada : 13 de junio , Hora de llegada : 11:00 , Hora de  salida : 17:30 pm , Duracion en el lugar : 6 horas con 30 minutos , Orden : Almuerzo (11:30 - 12:30),Juego de PaintBall (13:00 - 15:00)"));
    }

    /*
        salida.add(new InfoTours(" 20 de junio"));
        salida.add(new InfoTours(" 3 de julio"));
        salida.add(new InfoTours(" 10 de julio"));
        salida.add(new InfoTours(" 21 de julio"));
        salida.add(new InfoTours(" 27 de julio"));
        salida.add(new InfoTours(" 6 de agosto"));
        salida.add(new InfoTours(" 8 de octubre"));
    }

    private void generateLlegadas() {
        llegada.add(new InfoTours(" "));
        llegada.add(new InfoTours(" 15 de mayo"));
        llegada.add(new InfoTours(" 13 de junio"));
        llegada.add(new InfoTours(" 20 de junio"));
        llegada.add(new InfoTours(" 3 de julio"));
        llegada.add(new InfoTours(" 10 de julio"));
        llegada.add(new InfoTours(" 21 de julio"));
        llegada.add(new InfoTours(" 27 de julio"));
        llegada.add(new InfoTours(" 6 de agosto"));
        llegada.add(new InfoTours(" 8 de octubre"));
    }

    private void generateOrden() {
        orden.add(new InfoTours(")"));
        orden.add(new InfoTours(""));
        orden.add(new InfoTours(""));
        orden.add(new InfoTours("Desayuno(7:00 - 8 :00),Senderismo (8:00 a 12:00), Almuerzo (12:00 - 13:00), Visita el rio celeste (13:00 - 15:00)"));
        orden.add(new InfoTours("Desayuno(7:00 - 8 :00),Paseo a caballo (8:00 a 10:00), Canopy (10:00 - 12:00), Almuerzo (12:00 - 13:00),Visita el rio guachipelin (13:00 - 15:00)"));
        orden.add(new InfoTours("Desayuno(7:00 - 8 :00),Visita el museo de jade (10:30 a 12:00), Visita el museo de oro precolombino (12:30 - 13:00), Almuerzo (12:00 - 13:00),Visita el museo de Nacional (14:00 - 16:00), Visita el Museo de Arte Costarricense (17:00 - 19:00)"));
        orden.add(new InfoTours("Desayuno(8:00 - 9:00),Asamblea legislativa (10:30 a 11:30), Casa Presidencial (12:30 - 13:30), Almuerzo en el mercado central de San Jose (14:00 - 15:00),Visita el mercado central de San Jose (15:00 - 17:00)"));
        orden.add(new InfoTours("Playa Tamarindo (6:00 - 9:00),Playa Famingo (9:00 - 12:00), Almuerzo (12:00 - 13:00),Playa Samara (13:00 - 16:00), Playa Nosara (16:00 - 19:00)"));
        orden.add(new InfoTours("Desayuno(7:00 - 8 :00),Tour del cafe (8:00 - 10:00), Canopy (10:30 - 12:00), Almuerzo (12:00 - 13:00),Senderismo con varias atracciones (13:00 - 15:00), Plaza de la democracia y de la abolicon del ejercito (16:00 - 17:30)"));
        orden.add(new InfoTours("Doris Metropolitan (9:00 - 11:00), Tin Jo (11:00 a 13:00), Product C (13:00 - 15:00), La terrase (15:00 - 18:00"));
    }*/
    
    /*
        I. Mostrar1
    */
    public void mostrar(boolean moreless) {
        indice = moreless ? indice : indice == 0 ? indice - 6 : indice - 12;
        indice = indice >= 0 ? indice : tours.size() + indice;

        for (VBox pane : PaginaPrincipalController.panes) {
            indice = indice >= tours.size() ? 0 : indice;
            pane.getChildren().clear();
            pane.getChildren().add(tours.get(indice));
            indice += 1;
        }
    }

    public void derecha() {
        mostrar(true);
    }

    public void izquierda() {
        mostrar(false);
    }

    /*
        II. Mostrar2
    */
    
    /*
        Siempre que hayan metodos repetidos, se pueden reducir a un metodo,
        con los parametros necesarios. Ademas no deberia de utilizar booleanos
        como parametros, porque tendria que velar dos posibilidades.
    */
    public void mostrarInfo(boolean mostrar) {
        indice = mostrar ? indice : indice == 1 ? indice - 1 : indice - 1;
        indice = indice >= 1 ? indice : information.size() + indice + 1;

        for (HBox pane : VistaInfoController.pane) {
            indice = indice >= information.size() ? 0 : indice;
            pane.getChildren().clear();
            pane.getChildren().add(information.get(indice));
            indice = 1;
        }
    }

}
