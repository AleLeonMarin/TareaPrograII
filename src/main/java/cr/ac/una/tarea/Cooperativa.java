package cr.ac.una.tarea;

import java.util.ArrayList;

public class Cooperativa {

    public String name;
    public String logo;
    public ArrayList<String> Cooperativa = new ArrayList<String>();

    public Cooperativa() {
    }

    public Cooperativa(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ArrayList<String> getCooperativa() {
        return Cooperativa;
    }

    public void setCooperativa(ArrayList<String> cooperativa) {
        Cooperativa = cooperativa;
    }


    public void addCooperativa() {

        Cooperativa cooperativa = new Cooperativa(name , logo);

        cooperativa.Cooperativa.add(cooperativa.getName());
        cooperativa.Cooperativa.add(cooperativa.getLogo());
    }


    
}
