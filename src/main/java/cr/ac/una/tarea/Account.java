package cr.ac.una.tarea;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Account {

    public ArrayList<String> cuentas = new ArrayList<String>();

    public String name;
    public String type;
    public float balance;



    public Account() {
    }

    public Account(String name, String type){
        this.name = name;
        this.type = type;
    }

    public Account(String name, String type, float balance) {
        this.name = name;
        this.type = type;
        this.balance = balance;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void addAccount(){
        Account accounts = new Account(name, type);
        accounts.cuentas.add(accounts.getName());
        accounts.cuentas.add(accounts.getType());

    }

    public void deleteAccount(){
        Account accounts = new Account(name, type);
        accounts.cuentas.remove(accounts.getName());
        accounts.cuentas.remove(accounts.getType());
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
