package cr.ac.una.tarea.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account{

    public SimpleStringProperty id; 
    public SimpleStringProperty balance;
    public SimpleStringProperty accountType;

    public Account(){

        this.id = new SimpleStringProperty();
        this.balance = new SimpleStringProperty();
        this.accountType = new SimpleStringProperty();

    }
    
    public Account(String accountType){

        this(); 
        this.accountType.set(accountType.toString());

    }
    public Account(String id, String balance, String accountType){
        
        this(); 
        this.id.set(id);
        this.balance.set(balance);
        this.accountType.set(accountType);

    }

    public Account(String id, String balance, AccountType accountType){
        
        this(); 
        this.id.set(id);
        this.balance.set(balance);
        this.accountType.set(accountType.toString());

    }


    public Account(Account account){
            
        this(); 
        this.id.set(account.getId());
        this.balance.set(account.getBalance());
        this.accountType.set(account.getAccountType());
    }

    public void setAccount(Account account){
        this.id.set(account.getId());
        this.balance.set(account.getBalance());
        this.accountType.set(account.getAccountType());
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getBalance(){
        return balance.get();
    }

    public void setBalance(String balance){ this.balance.set(balance); }
    public StringProperty balanceProperty() {
        return balance;
    }

    public String getAccountType(){
        return accountType.get();
    }

    public void setAccountType(String accountType){
        this.accountType.set(accountType);
    }

    public String toStringName(){
        return accountType.get();
    }

    public StringProperty accountTypeProperty() {
        return accountType;
    }

    public String toString(){
        return id.get() + ", " + balance.get() + ", " + accountType.get();
    }

}