package cr.ac.una.tarea.model;

import javafx.beans.property.SimpleStringProperty;

public class Pendientes {

    public SimpleStringProperty folio; 
    public SimpleStringProperty total; 
    public SimpleStringProperty Billetes20Mil;
    public SimpleStringProperty Billetes10Mill;
    public SimpleStringProperty Billetes5Mil;
    public SimpleStringProperty Billetes2Mil;
    public SimpleStringProperty Billetes1Mil;
    public SimpleStringProperty Moneda500;
    public SimpleStringProperty Moneda100;
    public SimpleStringProperty Moneda50;
    public SimpleStringProperty Moneda25;
    public SimpleStringProperty Moneda10;
    public SimpleStringProperty Moneda5;

    public Pendientes() {
        this.folio = new SimpleStringProperty();
        this.total = new SimpleStringProperty();
        this.Billetes20Mil = new SimpleStringProperty();
        this.Billetes10Mill = new SimpleStringProperty();
        this.Billetes5Mil = new SimpleStringProperty();
        this.Billetes2Mil = new SimpleStringProperty();
        this.Billetes1Mil = new SimpleStringProperty();
        this.Moneda500 = new SimpleStringProperty();
        this.Moneda100 = new SimpleStringProperty();
        this.Moneda50 = new SimpleStringProperty();
        this.Moneda25 = new SimpleStringProperty();
        this.Moneda10 = new SimpleStringProperty();
        this.Moneda5 = new SimpleStringProperty();
    }
    public Pendientes (String folio , String total){
        this.folio = new SimpleStringProperty(folio);
        this.total = new SimpleStringProperty(total);
    }

    public Pendientes (String folio , String Billetes20Mil, String Billetes10Mill, String Billetes5Mil, String Billetes2Mil, String Billetes1Mil, String Moneda500, String Moneda100, String Moneda50, String Moneda25, String Moneda10, String Moneda5){
        
        this();
        this.folio.set(folio);
        this.Billetes20Mil.set(Billetes20Mil);
        this.Billetes10Mill.set(Billetes10Mill);
        this.Billetes5Mil.set(Billetes5Mil);
        this.Billetes2Mil.set(Billetes2Mil);
        this.Billetes1Mil.set(Billetes1Mil);
        this.Moneda500.set(Moneda500);
        this.Moneda100.set(Moneda100);
        this.Moneda50.set(Moneda50);
        this.Moneda25.set(Moneda25);
        this.Moneda10.set(Moneda10);
        this.Moneda5.set(Moneda5);

    }

    public Pendientes(Pendientes pendientes){
        
        this();
        this.folio.set(pendientes.getFolio());
        this.total.set(pendientes.getTotal());
        this.Billetes20Mil.set(pendientes.getBilletes20Mil());
        this.Billetes10Mill.set(pendientes.getBilletes10Mill());
        this.Billetes5Mil.set(pendientes.getBilletes5Mil());
        this.Billetes2Mil.set(pendientes.getBilletes2Mil());
        this.Billetes1Mil.set(pendientes.getBilletes1Mil());
        this.Moneda500.set(pendientes.getMoneda500());
        this.Moneda100.set(pendientes.getMoneda100());
        this.Moneda50.set(pendientes.getMoneda50());
        this.Moneda25.set(pendientes.getMoneda25());
        this.Moneda10.set(pendientes.getMoneda10());
        this.Moneda5.set(pendientes.getMoneda5());

    }

    public void setPendientes(Pendientes pendientes){
        this.folio.set(pendientes.getFolio());
        this.total.set(pendientes.getTotal());
        this.Billetes20Mil.set(pendientes.getBilletes20Mil());
        this.Billetes10Mill.set(pendientes.getBilletes10Mill());
        this.Billetes5Mil.set(pendientes.getBilletes5Mil());
        this.Billetes2Mil.set(pendientes.getBilletes2Mil());
        this.Billetes1Mil.set(pendientes.getBilletes1Mil());
        this.Moneda500.set(pendientes.getMoneda500());
        this.Moneda100.set(pendientes.getMoneda100());
        this.Moneda50.set(pendientes.getMoneda50());
        this.Moneda25.set(pendientes.getMoneda25());
        this.Moneda10.set(pendientes.getMoneda10());
        this.Moneda5.set(pendientes.getMoneda5());
    }

    public String getFolio() {
        return folio.get();
    }
    public void setFolio(String folio) {
        this.folio.set(folio);
    }
    public String getTotal() {
        return total.get();
    }
    public void setTotal(String total) {
        this.total.set(total);
    }
    public String getBilletes20Mil() {
        return Billetes20Mil.get();
    }
    public void setBilletes20Mil(String Billetes20Mil) {
        this.Billetes20Mil.set(Billetes20Mil);
    }
    public String getBilletes10Mill() {
        return Billetes10Mill.get();
    }
    public void setBilletes10Mill(String Billetes10Mill) {
        this.Billetes10Mill.set(Billetes10Mill);
    }
    public String getBilletes5Mil() {
        return Billetes5Mil.get();
    }
    public void setBilletes5Mil(String Billetes5Mil) {
        this.Billetes5Mil.set(Billetes5Mil);
    }
    public String getBilletes2Mil() {
        return Billetes2Mil.get();
    }

    public String getBilletes1Mil() {
        return Billetes1Mil.get();
    }
    public void setBilletes1Mil(String Billetes1Mil) {
        this.Billetes1Mil.set(Billetes1Mil);
    }
    public String getMoneda500() {
        return Moneda500.get();
    }
    public void setMoneda500(String Moneda500) {
        this.Moneda500.set(Moneda500);
    }
    public String getMoneda100() {
        return Moneda100.get();
    }
    public void setMoneda100(String Moneda100) {
        this.Moneda100.set(Moneda100);
    }
    public String getMoneda50() {
        return Moneda50.get();
    }
    public void setMoneda50(String Moneda50) {
        this.Moneda50.set(Moneda50);
    }
    public String getMoneda25() {
        return Moneda25.get();
    }
    public void setMoneda25(String Moneda25) {
        this.Moneda25.set(Moneda25);
    }
    public String getMoneda10() {
        return Moneda10.get();
    }
    public void setMoneda10(String Moneda10) {
        this.Moneda10.set(Moneda10);
    }
    public String getMoneda5() {
        return Moneda5.get();
    }
    public void setMoneda5(String Moneda5) {
        this.Moneda5.set(Moneda5);
    }

    public String toString(){
        return folio.get() + "," + total.get() + "," + Billetes20Mil.get() + "," + Billetes10Mill.get() + "," + Billetes5Mil.get() + "," + Billetes2Mil.get() + "," + Billetes1Mil.get() + "," + Moneda500.get() + "," + Moneda100.get() + "," + Moneda50.get() + "," + Moneda25.get() + "," + Moneda10.get() + "," + Moneda5.get();
    }

    
}
