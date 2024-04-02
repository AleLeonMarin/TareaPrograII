package cr.ac.una.tarea.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Account {

    public ArrayList<String> cuentas = new ArrayList<String>();

    public String name;



    public Account() {
    }

    public Account(String name){
        this.name = name;
    }



    public ArrayList<String> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<String> cuentas) {
        this.cuentas = cuentas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addAccount(){
        Account accounts = new Account(name);
        accounts.cuentas.add(accounts.getName());

    }

    public void deleteAccount(){
        Account accounts = new Account(name);
        accounts.cuentas.remove(accounts.getName());
    }

    public void addToFile(Account account) throws IOException {

        account.addAccount();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Cuentas.txt",true));

        try {

            writer.write(account.getCuentas().toString());
            writer.newLine();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(Account account) throws IOException {
        account.addToFile(account);
    }
}
