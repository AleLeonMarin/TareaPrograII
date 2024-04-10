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
    public static final ObservableList<Cooperativa> cooperativas = FXCollections.observableArrayList();
    public static final ObservableList<Associated> associate = FXCollections.observableArrayList();
    public static final ObservableList<Account> accounts = FXCollections.observableArrayList();
    public static final ObservableList<AccountType> accountType = FXCollections.observableArrayList();
    

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        if (cooperativa.getName() == null || cooperativa.getLogo() == null) {
            FlowController.getInstance().goViewInWindow("LogInView");
            readAssociates();
        } else {
            FlowController.getInstance().goViewInWindow("LogInView");
            stage.setTitle(cooperativa.getName());
            stage.getIcons().add(new Image(cooperativa.getLogo()));
        } 

        AppContext.getInstance().set("cooperativa", cooperativas);
        AppContext.getInstance().set("Asociados", associate);
        AppContext.getInstance().set("Cuentas", accounts);
        AppContext.getInstance().set("TiposCuentas", accountType);

    }

    public static void main(String[] args) {

        launch();
    }

    public void readAssociates(){
        try {
            File file = new File("./Asociados.txt");
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String userData [] = myReader.nextLine().split(",");
                if (userData.length == 5 ) {
                    String name = userData[0];
                    String lastName = userData[1];
                    String age = userData[2];
                    String folio = userData[3];
                    String imageName = userData[4];
                

                    Associated associated = new Associated(name, lastName, age, folio, imageName);
                    associate.add(associated);
                }
            }

            

            myReader.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger("Error saving image: ");
        }finally{

            for(Associated associate : associate) {
                System.out.println(associate);
            }
        }

        
    }
    
    public static ObservableList<Associated> getAsociados(){
        return associate;
    }
}