/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.sql.Connection;
import conexion.ConnectionClass;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorNuevaParroquia implements Initializable {

    ObservableList<String> listaPoblaciones = FXCollections.observableArrayList();

    ObservableList<String> listaProvincias = FXCollections.observableArrayList();

    ObservableList<String> listaZona = FXCollections.observableArrayList();

    @FXML
    private TextField nombreParroco;
    @FXML
    private TextField nombreParroquia;
    @FXML
    private TextField direccionParroquia;
    @FXML
    private TextField codigoParroquia;
    @FXML
    private ComboBox<String> poblacionParroquia;
    @FXML
    private ComboBox<String> provinciaParroquia;
    @FXML
    private Button btnCancelarParroquia;
    @FXML
    private Button btnCreaParroquia;
    @FXML
    private ComboBox<String> zonaParroquia;
    @FXML
    private TextField cartelesParroquias;
    @FXML
    private TextField sobresParroquias;
    @FXML
    private TextField tripticosParroquias;
    @FXML
    private TextField unidadesParroquias;
    @FXML
    private TextField otrasParroquias;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            ResultSet rs1 = connection.createStatement().executeQuery("SELECT * FROM poblaciones");
            ResultSet rs2 = connection.createStatement().executeQuery("SELECT * FROM provincias");
            ResultSet rs3 = connection.createStatement().executeQuery("SELECT * FROM zonas");
            while (rs1.next()) {
                listaPoblaciones.add((rs1.getString("nombre")));
            }
            while (rs2.next()) {
                listaProvincias.add((rs2.getString("nombre")));
            }
            while (rs3.next()) {
                listaZona.add((rs3.getString("zona")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorNuevaParroquia.class.getName()).log(Level.SEVERE, null, ex);
        }
        poblacionParroquia.setItems(listaPoblaciones);
        provinciaParroquia.setItems(listaProvincias);
        zonaParroquia.setItems(listaZona);
    }

    @FXML
    private void cancelarParroquia(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar el ingreso de la nueva parroquia?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarParroquia.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void crearParroquia(ActionEvent event) {
        //Comprobar datos nulos ComboBox
        String poblacion = "";
        if (poblacionParroquia.getValue() != null) {
            poblacion = poblacionParroquia.getValue().toString();
        }
        String provincia = "";
        if (provinciaParroquia.getValue() != null) {
            provincia = provinciaParroquia.getValue().toString();
        }
        String zona = "";
        if (zonaParroquia.getValue() != null) {
            zona = zonaParroquia.getValue().toString();
        }

        // Insertar campos a la BD
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "INSERT IGNORE INTO Parroquias (nombre,parroco,zona,poblacion,direccion,codigo_postal,provincia,carteles,tripticos,sobres,unidades_didacticas) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nombreParroquia.getText());
            pst.setString(2, nombreParroco.getText());
            pst.setString(3, zona);
            pst.setString(4, poblacion);
            pst.setString(5, direccionParroquia.getText());
            pst.setString(6, codigoParroquia.getText());
            pst.setString(7, provincia);
            pst.setString(8, cartelesParroquias.getText());
            pst.setString(9, tripticosParroquias.getText());
            pst.setString(10, sobresParroquias.getText());
            pst.setString(11, unidadesParroquias.getText());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorNuevaParroquia.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea guardar los datos de la parroquia?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setContentText("Parroquia creada con éxito");
            alert2.showAndWait();
            Stage stage = (Stage) btnCreaParroquia.getScene().getWindow();
            stage.close();
        }
    }
}
