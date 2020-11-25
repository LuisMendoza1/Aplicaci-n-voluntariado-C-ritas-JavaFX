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
public class ControladorNuevoEquipo implements Initializable {

    ObservableList<String> listaPoblaciones = FXCollections.observableArrayList();

    ObservableList<String> listaProvincias = FXCollections.observableArrayList();

    ObservableList<String> listaZona = FXCollections.observableArrayList();

    @FXML
    private TextField nombreEquipo;
    @FXML
    private TextField direccionEquipo;
    @FXML
    private TextField codigoEquipo;
    @FXML
    private ComboBox<String> poblacionEquipo;
    @FXML
    private ComboBox<String> provinciaEquipo;
    @FXML
    private Button btnCancelarEquipo;
    @FXML
    private Button btnCreaEquipo;
    @FXML
    private ComboBox<String> zonaEquipo;
    @FXML
    private TextField telefonoEquipo;

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
            Logger.getLogger(ControladorNuevoEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        poblacionEquipo.setItems(listaPoblaciones);
        provinciaEquipo.setItems(listaProvincias);
        zonaEquipo.setItems(listaZona);
    }

    @FXML
    private void cancelarEquipo(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar el ingreso del nuevo equipo?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarEquipo.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void crearEquipo(ActionEvent event) {
        //Comprobar datos nulos ComboBox
        String poblacion = "";
        if (poblacionEquipo.getValue() != null) {
            poblacion = poblacionEquipo.getValue().toString();
        }
        String provincia = "";
        if (provinciaEquipo.getValue() != null) {
            provincia = provinciaEquipo.getValue().toString();
        }
        String zona = "";
        if (zonaEquipo.getValue() != null) {
            zona = zonaEquipo.getValue().toString();
        }

        // Insertar campos a la BD
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "INSERT IGNORE INTO Equipos (nombre,zona,poblacion,direccion,codigo_postal,telefono,provincia) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nombreEquipo.getText());
            pst.setString(2, zona);
            pst.setString(3, poblacion);
            pst.setString(4, direccionEquipo.getText());
            pst.setString(5, codigoEquipo.getText());
            pst.setString(6, telefonoEquipo.getText());
            pst.setString(7, provincia);
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorNuevoEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea guardar los datos del equipo?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setContentText("Equipo creado con éxito");
            alert2.showAndWait();
            Stage stage = (Stage) btnCreaEquipo.getScene().getWindow();
            stage.close();
        }
    }
}
