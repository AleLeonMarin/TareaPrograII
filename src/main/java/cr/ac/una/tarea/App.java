package cr.ac.una.tarea;

import cr.ac.una.tarea.model.Account;
import cr.ac.una.tarea.model.AccountType;
import cr.ac.una.tarea.model.Associated;
import cr.ac.una.tarea.model.Cooperativa;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

import javax.security.auth.login.AppConfigurationEntry;

/**
 * JavaFX App
 */
public class App extends Application {

    ObservableList<AccountType> accounttypes = FXCollections.observableArrayList();
    ObservableList<Associated> associate = FXCollections.observableArrayList();
    ObservableList<Cooperativa> coope = FXCollections.observableArrayList();
    ObservableList<Account> account = FXCollections.observableArrayList();


    Cooperativa cooperativa = new Cooperativa();

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        if (cooperativa.getName() == null || cooperativa.getLogo() == null) {
            FlowController.getInstance().goViewInWindow("LogInView");
        } else {
            FlowController.getInstance().goViewInWindow("LogInView");
            stage.setTitle(cooperativa.getName());
            stage.getIcons().add(new Image(cooperativa.getLogo()));
        }


        AppContext.getInstance().set("Asociados", associate);
        AppContext.getInstance().set("Tipos de Cuenta", accounttypes);
        AppContext.getInstance().set("Cuentas", account);
        AppContext.getInstance().set("Cooperativa", coope);


    }

    public static void main(String[] args) {

        launch();
    }

}