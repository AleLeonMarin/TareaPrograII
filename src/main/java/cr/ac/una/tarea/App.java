package cr.ac.una.tarea;

import cr.ac.una.tarea.model.Account;
import cr.ac.una.tarea.model.AccountType;
import cr.ac.una.tarea.model.Associated;
import cr.ac.una.tarea.model.Cooperativa;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {

    Cooperativa cooperativa = new Cooperativa();
    Associated asociado = new Associated();
   
    
    
    @Override
    public void start(Stage stage) throws IOException {

        ObservableList<Cooperativa> cooperativas = FXCollections.observableArrayList();
        ObservableList<Account> accounts = FXCollections.observableArrayList();
        ObservableList<AccountType> accountType = FXCollections.observableArrayList();
        ObservableList<Associated> associate = FXCollections.observableArrayList();

        AppContext.getInstance().set("cooperativa", cooperativas);
        AppContext.getInstance().set("Asociados", associate);
        AppContext.getInstance().set("Cuentas", accounts);
        AppContext.getInstance().set("TiposCuentas", accountType);


        FlowController.getInstance().InitializeFlow(stage, null);
        if (cooperativa.getName() == null || cooperativa.getLogo() == null) {
            FlowController.getInstance().goViewInWindow("LogInView");
        } else {
            FlowController.getInstance().goViewInWindow("LogInView");
            stage.setTitle(cooperativa.getName());
            stage.getIcons().add(new Image(cooperativa.getLogo()));
        } 

        

    }

    public static void main(String[] args) {

        launch();
    }
}