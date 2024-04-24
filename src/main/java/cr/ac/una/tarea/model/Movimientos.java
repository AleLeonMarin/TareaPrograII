package cr.ac.una.tarea.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movimientos {

    public SimpleStringProperty folio;
    public SimpleStringProperty total;
    public SimpleStringProperty depositos;
    public SimpleStringProperty retiros;
    public SimpleStringProperty tipoDeCuenta;

    public Movimientos() {

        this.folio = new SimpleStringProperty();
        this.total = new SimpleStringProperty();
        this.depositos = new SimpleStringProperty();
        this.retiros = new SimpleStringProperty();
        this.tipoDeCuenta = new SimpleStringProperty();

    }

    public Movimientos(String folio, String total, String depositos, String retiros , String tipoDeCuenta) {

        this();
        this.folio.set(folio);
        this.total.set(total);
        this.depositos.set(depositos);
        this.retiros.set(retiros);
        this.tipoDeCuenta.set(tipoDeCuenta);

    }


    public Movimientos(Movimientos movimientos) {

        this();
        this.folio.set(movimientos.getFolio());
        this.total.set(movimientos.getTotal());
        this.depositos.set(movimientos.getDepositos());
        this.retiros.set(movimientos.getRetiros());
        this.tipoDeCuenta.set(movimientos.getTipoDeCuenta());
    }

    public void setMovimientos(Movimientos movimientos) {
        this.folio.set(movimientos.getFolio());
        this.total.set(movimientos.getTotal());
        this.depositos.set(movimientos.getDepositos());
        this.retiros.set(movimientos.getRetiros());
        this.tipoDeCuenta.set(movimientos.getTipoDeCuenta());
    }

    public String getFolio() {
        return folio.get();
    }
    
    public void setFolio(String folio) {
        this.folio.set(folio);
    }

    public StringProperty folioProperty() {
        return folio;
    }

    public String getTotal() {
        return total.get();
    }

    public void setTotal(String total) {
        this.total.set(total);
    }
    public StringProperty totalProperty() {
        return total;
    }

    public String getDepositos() {
        return depositos.get();
    }

    public void setDepositos(String depositos) {
        this.depositos.set(depositos);
    }
    public StringProperty depositosProperty() {
        return depositos;
    }
    public String getRetiros(){
        return retiros.get();
    }

    public void setRetiros(String retiros){
        this.retiros.set(retiros);
    }
    public StringProperty retirosProperty(){
        return retiros;
    }
     public String getTipoDeCuenta() {
        return tipoDeCuenta.get();
    }

    public void setTipoDeCuenta(String tipoDeCuenta) {
        this.tipoDeCuenta.set(tipoDeCuenta);
    }
    public StringProperty tipoDeCuentaProperty() {
        return tipoDeCuenta;
    }

    public String toString() {
        return folio.get() + "," + total.get() + "," + "+"+depositos.get() + "," + "-"+retiros.get() + "," + tipoDeCuenta.get();
    }
    
    public void createTxtMovements(Movimientos mov) throws IOException{
        try{
        BufferedWriter writer = new BufferedWriter(new FileWriter("Movimientos.txt", true));
        writer.write(mov.toString());
        writer.newLine();
        writer.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
}
