package cr.ac.una.tarea;

import cr.ac.una.tarea.model.Account;
import cr.ac.una.tarea.model.AccountType;
import cr.ac.una.tarea.model.Associated;
import cr.ac.una.tarea.model.Cooperativa;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    Cooperativa cooperativa = new Cooperativa();

    ObservableList<Cooperativa> cooperativas = FXCollections.observableArrayList();
    ObservableList<Associated> associate = FXCollections.observableArrayList();
    ObservableList<Account> accounts = FXCollections.observableArrayList();
    ObservableList<AccountType> accountType = FXCollections.observableArrayList();
    

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

        AppContext.getInstance().set("cooperativa", cooperativa);
        AppContext.getInstance().set("Asociados", associate);
        AppContext.getInstance().set("Cuentas", accounts);
        AppContext.getInstance().set("TiposCuentas", accountType);

    }

    public static void main(String[] args) {

        launch();
    }

}