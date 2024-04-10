package cr.ac.una.tarea.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class AccountType {

    public ArrayList<String> cuentas = new ArrayList<String>();

    public String name;



    public AccountType() {
    }

    public AccountType(String name){
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
        AccountType accounts = new AccountType(name);
        accounts.cuentas.add(accounts.getName());


    }

    public void deleteAccount(){
        AccountType accounts = new AccountType(name);
        accounts.cuentas.remove(accounts.getName());
    
    }

    public void addToFile(AccountType account) throws IOException {

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

    public void createFile(AccountType account) throws IOException {
        account.addToFile(account);
    }

    public static ArrayList<String> readFileToList(String filePath, ArrayList<String> cuentas) throws IOException {

        BufferedReader reader = null;
        
        try {
          reader = new BufferedReader(new FileReader("./Cuentas.txt"));
          String line;
          while ((line = reader.readLine()) != null) {
            cuentas.add(line);
          }
        } finally {
          if (reader != null) {
            reader.close();
          }
        }
        
        return cuentas;
      }
      

}
