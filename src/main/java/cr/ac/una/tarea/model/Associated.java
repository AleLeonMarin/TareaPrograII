package cr.ac.una.tarea.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Associated {

    public String name;
    public String lastName;
    public int age;
    public String Folio;
    public String Photo;
    public String Cuentas;
    public ArrayList<String> Associate = new ArrayList<String>();

    public Associated() {

    }

    public Associated(String name, String lastName, int age, String photo) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        Photo = photo;

    }

    public ArrayList<String> getAssociate() {
        return Associate;
    }

    public void setAssociate(ArrayList<String> associate) {
        Associate = associate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getCuentas() {
        return Cuentas;
    }

    public void setCuentas(String cuentas) {
        Cuentas = cuentas;
    }

    public String createFolio(String name, int age) {
        
        final int max = 20;
        final int min = 10;
        int range = max - min + 1;
        int random = (int) (Math.random() * range) + min;

        Folio = name.substring(0, 1) + lastName.substring(0, 1) + age + random;

       return Folio;
    }

    public void addAssociated() {
        Associated asociado = new Associated(name, lastName, age, Photo);
        asociado.Associate.add(asociado.getName());
        asociado.Associate.add(String.valueOf(asociado.getAge()));
        asociado.Associate.add(asociado.getFolio());
        asociado.Associate.add(asociado.getPhoto());
    }

    public void addToFile(Associated asociado) throws IOException {

        asociado.addAssociated();

        BufferedWriter writer = new BufferedWriter(new FileWriter("Asociados.txt",true));
        try {

            writer.write(asociado.getAssociate().toString());
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(Associated asociado) throws IOException {
       asociado.addToFile(asociado);
    }



}







