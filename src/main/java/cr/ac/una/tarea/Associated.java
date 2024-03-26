package cr.ac.una.tarea;

import cr.ac.una.tarea.util.FlowController;
import io.github.palexdev.virtualizedfx.utils.VSPUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Associated {

    public ArrayList<String> arr = new ArrayList<>();
    public String name;
    public int age;
    public String Folio;
    public String Photo;
    public String Cuentas;
    public int Cont;


    public Associated(String name, int age, String folio, String photo, String cuentas) {
        this.name = name;
        this.age = age;
        Folio = folio;
        Photo = photo;
        this.Cuentas = cuentas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String folio) {
        Folio = folio;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public void createFolio(String name, int age){
        String Folio = name.substring(0,1) + age;
    }

}
