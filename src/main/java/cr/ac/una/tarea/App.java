package cr.ac.una.tarea;

import cr.ac.una.tarea.controller.AsociadosViewController;
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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {

    Cooperativa cooperativa = new Cooperativa();
    private static String accessParameter = "";
    ObservableList<Cooperativa> cooperativas = FXCollections.observableArrayList();
    ObservableList<Account> accounts = FXCollections.observableArrayList();
    ObservableList<AccountType> accountType = FXCollections.observableArrayList();
    ObservableList<Associated> associate = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) throws IOException {

        readCoope();
        for (Cooperativa cooperativa : cooperativas) {
            stage.setTitle(cooperativa.getName());
            stage.getIcons().add(new Image(cooperativa.getLogo()));
        }

        AppContext.getInstance().set("cooperativa", cooperativas);
        AppContext.getInstance().set("Asociados", associate);
        AppContext.getInstance().set("Cuentas", accounts);
        AppContext.getInstance().set("TiposCuentas", accountType);
        
        FlowController.getInstance().InitializeFlow(stage, null);
        //FlowController.getInstance().goViewInWindow("LogInView");
        goViewByAccess(accessParameter);

    }
    public void readCoope() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Cooperativa.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String logo = parts[1];
                Cooperativa cooperativa = new Cooperativa(name, logo);
                cooperativas.add(cooperativa);
            }
            br.close();

        } catch (IOException ex) {

            Logger.getLogger(App.class.getName()).log(Level.SEVERE, "Error al leer archivo", ex);

        }
    }

    private void goViewByAccess(String access){
        if (access.equals("A")){
            FlowController.getInstance().goMain("AsociadosView");
        }
        if (access.equals("F")){
            FlowController.getInstance().goMain("FuncionariosView");
        }
        if(access.equals("P")){
            FlowController.getInstance().goMain("ProfeView");
        }
    }
    
    public static void main(String[] args) {
        if (args.length>0){
            accessParameter = args[0];
        }

        launch();
    }


}
