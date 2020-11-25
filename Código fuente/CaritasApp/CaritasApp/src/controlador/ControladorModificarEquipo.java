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
public class ControladorModificarEquipo implements Initializable {

    private int idEquipo;

    ObservableList<String> listaPoblaciones = FXCollections.observableArrayList();

    ObservableList<String> listaProvincias = FXCollections.observableArrayList();

    ObservableList<String> listaZona = FXCollections.observableArrayList();
    @FXML
    private TextField nombreModificacionEquipo;
    @FXML
    private TextField direccionModificacionEquipo;
    @FXML
    private TextField codigoModificacionEquipo;
    @FXML
    private ComboBox<String> poblacionModificacionEquipo;
    @FXML
    private ComboBox<String> provinciaModificacionEquipo;
    @FXML
    private Button btnCancelarEquipo;
    @FXML
    private Button btnGuardarEquipo;
    @FXML
    private ComboBox<String> zonaModificacionEquipo;
    @FXML
    private TextField telefonoModificacionEquipo;

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
        poblacionModificacionEquipo.setItems(listaPoblaciones);
        provinciaModificacionEquipo.setItems(listaProvincias);
        zonaModificacionEquipo.setItems(listaZona);
    }

    public void modificarDatosEquipo(int idModificacion, String nombreModificacion, String zonaModificacion,
            String poblacionModificacion, String direccionModificacion, String codigoModificacion, String telefonoModificacion, String provinciaModificacion) {
        idEquipo = idModificacion;
        nombreModificacionEquipo.setText(nombreModificacion);
        direccionModificacionEquipo.setText(direccionModificacion);
        codigoModificacionEquipo.setText(codigoModificacion);
        telefonoModificacionEquipo.setText(telefonoModificacion);
        if (poblacionModificacion != null) {
            poblacionModificacionEquipo.setPromptText(poblacionModificacion);
            poblacionModificacionEquipo.setValue(poblacionModificacion);
        }
        if (provinciaModificacion != null) {
            provinciaModificacionEquipo.setPromptText(provinciaModificacion);
            provinciaModificacionEquipo.setValue(provinciaModificacion);
        }
        if (zonaModificacion != null) {
            zonaModificacionEquipo.setPromptText(zonaModificacion);
            zonaModificacionEquipo.setValue(zonaModificacion);
        }
    }

    @FXML
    private void cancelarEquipo(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar la modificación de los datos del equiipo?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarEquipo.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void guardarEquipo(ActionEvent event) {
        //Comprobar datos nulos ComboBox
        String poblacion = "";
        if (poblacionModificacionEquipo.getValue() != null) {
            poblacion = poblacionModificacionEquipo.getValue().toString();
        }
        String provincia = "";
        if (provinciaModificacionEquipo.getValue() != null) {
            provincia = provinciaModificacionEquipo.getValue().toString();
        }
        String zona = "";
        if (zonaModificacionEquipo.getValue() != null) {
            zona = zonaModificacionEquipo.getValue().toString();
        }
        // Actualizar campos a la base de datos
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "UPDATE equipos SET nombre = ?, zona = ?, poblacion = ?, direccion = ?, codigo_postal = ?,"
                + " telefono = ?, provincia = ? WHERE id = " + idEquipo;
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nombreModificacionEquipo.getText());
            pst.setString(2, zona);
            pst.setString(3, poblacion);
            pst.setString(4, direccionModificacionEquipo.getText());
            pst.setString(5, codigoModificacionEquipo.getText());
            pst.setString(6, telefonoModificacionEquipo.getText());
            pst.setString(7, provincia);
            
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea guardar los datos del equipo?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Cambios guardados con éxito");
            alert1.showAndWait();
            Stage stageAux = (Stage) btnGuardarEquipo.getScene().getWindow();
            stageAux.close();
        }
    }

}
