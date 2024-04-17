package cr.ac.una.tarea.controller;

import cr.ac.una.tarea.model.AccountType;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;

public class ManCuentasViewController extends Controller implements Initializable {

    String filePath = "./AccountType.txt";

    @FXML
    private MFXButton btnAgregar;

    @FXML
    private MFXButton btnEliminar;

    @FXML
    private MFXButton btnEditar;

    @FXML
    private MFXComboBox<String> cmbCuentas;

    @FXML
    private MFXTextField txfNomCuentas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @Override
    public void initialize() {
        LoadTXTFile();
    }

    @FXML
    void onActionBtnAgregar(ActionEvent event) {
        try {
            if (txfNomCuentas.getText() == null || txfNomCuentas.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Cuenta", getStage(),
                        "Ingrese un nombre para la cuenta!");
            } else {
                String newTypeName = txfNomCuentas.getText();
                cmbCuentas.getItems().add(newTypeName);

                // Crear un nuevo objeto tipo de cuenta con nuevo nombre
                AccountType accountType = new AccountType(newTypeName);

                // Método -> Anexar la nueva cuenta al MXFComboBox & .txt
                AppendData(accountType);

                txfNomCuentas.clear();
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Agregar Cuenta", getStage(),
                        "Cuenta agregada exitosamente!");
            }
        } catch (Exception ex) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Cuenta", getStage(),
                    "Error al agregar cuenta nueva!" + ex.getMessage());
        }
    }

    @FXML
    void onActionBtnEliminar(ActionEvent event) {
        // Declarar la variable selectedTypeName correctamente
        String selectedTypeName = cmbCuentas.getSelectionModel().getSelectedItem().toString();

        // Imprimir el valor de selectedTypeName

        // Asignar el texto a txfNomCuentas
        txfNomCuentas.setText(selectedTypeName);

        String name = cmbCuentas.getSelectedItem();

        if (selectedTypeName != null) {
            // Borrar item del MFXComboBox
            cmbCuentas.getItems().remove(selectedTypeName);
            cmbCuentas.clear();
            txfNomCuentas.clear();
            cmbCuentas.getSelectionModel().clearSelection();

            // Método → Elimina el tipo de cuenta del txt
            deleteItem(name);

            // Limpiar parte gráfica | Mensaje
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Cuenta", getStage(),
                    "Cuenta eliminada exitosamente!");
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Cuenta", getStage(),
                    "Seleccione una cuenta para eliminar!");
        }

    }

    @FXML
    void onActionBtnEditar(ActionEvent event) {
        String selectedTypeName = cmbCuentas.getSelectionModel().getSelectedItem();
        String id = cmbCuentas.getSelectedItem();

        if (selectedTypeName != null) {
            if (txfNomCuentas.getText() == null || txfNomCuentas.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Editar Cuenta", getStage(),
                        "Ingrese un nuevo nombre para la cuenta!");
                return;
            }
            String newTypeName = txfNomCuentas.getText();

            
            // Actualizar los items del MFXComboBox
            int SelectedIndex = cmbCuentas.getSelectionModel().getSelectedIndex();
            cmbCuentas.getItems().remove(SelectedIndex);
            cmbCuentas.getItems().add(SelectedIndex, newTypeName);
            cmbCuentas.getSelectionModel().clearSelection();
            // ---- cmbCuentas.getItems().add(newTypeName);
            txfNomCuentas.setText(newTypeName);
            cmbCuentas.clear();

            // Método editar MFXComboBox & .txt
            editItem(id, newTypeName);

            // Limpiar parte gráfica | Mensaje
            cmbCuentas.clear();
            txfNomCuentas.clear();
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Editar Cuenta", getStage(),
                    "Cuenta editada exitosamente!");
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Editar Cuenta", getStage(),
                    "Seleccione una cuenta para editar!");
        }
    }

    // <-------------------------------------------------------------------------->

    public void LoadTXTFile() {
        try{

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            ObservableList<String> accounts = FXCollections.observableArrayList();
            while ((line = br.readLine()) != null){
                String[] partes = line.split(",");
                String name = partes[0];
                accounts.add(name);
            }

            cmbCuentas.setItems(accounts);
            br.close();

        }catch (IOException ex){

            Logger.getLogger(ManCuentasViewController.class.getName()).log(Level.SEVERE, "Error al leer archivo" , ex);

        }

        
    }

    public void AppendData(AccountType accountType) {
        try (FileWriter fileWriter = new FileWriter(filePath, true);
                BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(accountType.toString() + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteItem(String id) {
        File account = new File(filePath);
        StringBuffer temp = new StringBuffer();

        try {
            BufferedReader br = new BufferedReader(new FileReader(account));
            String line;

            while ((line = br.readLine()) != null) {
                String[] partes = line.split(",");

                // If the line does not contain the ID to delete, add it to the temporary content
                if (partes.length >= 1 && !partes[0].equals(id)) {
                    temp.append(line).append("\r\n");
                } else {
                    System.out.println("ID found and deleted: " + id);
                }
            }
            br.close(); // Close the BufferedReader
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileWriter writer = new FileWriter(account);
            writer.write(temp.toString());
            writer.close(); // Close the FileWriter
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editItem(String id, String newLine) {
        File account = new File(filePath);
        StringBuffer temp = new StringBuffer();
    
        try {
            BufferedReader br = new BufferedReader(new FileReader(account));
            String line;
    
            while ((line = br.readLine())!= null) {
                String[] partes = line.split(",");
    
                // Si la línea contiene el ID a editar, reemplazarla con la nueva línea
                if (partes.length >= 1 && partes[0].equals(id)) {
                    temp.append(newLine).append("\r\n");
                    System.out.println("ID encontrado y editado: " + id);
                }else{
                    temp.append(line).append("\r\n");
                }
            }
            br.close(); // Cierra el BufferedReader
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    
        try {
            FileWriter writer = new FileWriter(account);
            writer.write(temp.toString());
            writer.close(); // Cierra el FileWriter
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    
        System.out.println("Archivo actualizado exitosamente");
    }

    

    @FXML
    void onActionCmbCuentas(ActionEvent event) {

        String selectedTypeName = cmbCuentas.getSelectionModel().getSelectedItem().toString();

        // Imprimir el valor de selectedTypeName
        System.out.print(selectedTypeName);

        // Asignar el texto a txfNomCuentas
        txfNomCuentas.setText(selectedTypeName);


    }

}