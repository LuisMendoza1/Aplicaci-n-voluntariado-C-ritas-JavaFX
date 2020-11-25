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
public class ControladorModificarParroquia implements Initializable {

    @FXML
    private TextField nombreModificacionParroco;
    @FXML
    private TextField nombreModificacionParroquia;
    @FXML
    private TextField otrasModificacionParroquias;
    @FXML
    private TextField direccionModificacionParroquia;
    @FXML
    private TextField codigoModificacionParroquia;
    @FXML
    private ComboBox<String> poblacionModificacionParroquia;
    @FXML
    private ComboBox<String> provinciaModificacionParroquia;
    @FXML
    private Button btnCancelarParroquia;
    @FXML
    private Button btnModificarParroquia;
    @FXML
    private ComboBox<String> zonaModificacionParroquia;
    @FXML
    private TextField cartelesModificacionParroquias;
    @FXML
    private TextField sobresModificacionParroquias;
    @FXML
    private TextField tripticosModificacionParroquias;
    @FXML
    private TextField unidadesModificacionParroquias;

    private int idParroquia;
    
    ObservableList<String> listaPoblaciones = FXCollections.observableArrayList();

    ObservableList<String> listaProvincias = FXCollections.observableArrayList();

    ObservableList<String> listaZona = FXCollections.observableArrayList();

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
        poblacionModificacionParroquia.setItems(listaPoblaciones);
        provinciaModificacionParroquia.setItems(listaProvincias);
        zonaModificacionParroquia.setItems(listaZona);
    }

    public void modificarDatosParroquia(int idModificacion, String nombreModificacion, String parrocoModificacion, String zonaModificacion,
            String poblacionModificacion, String direccionModificacion, String codigoModificacion, String provinciaModificacion, String cartelesModificacion,
            String tripticoModificacion, String sobresModificacion, String unidadesModificacion) {
        idParroquia = idModificacion;
        nombreModificacionParroquia.setText(nombreModificacion);
        nombreModificacionParroco.setText(parrocoModificacion);
        direccionModificacionParroquia.setText(direccionModificacion);
        codigoModificacionParroquia.setText(codigoModificacion);
        cartelesModificacionParroquias.setText(cartelesModificacion);
        tripticosModificacionParroquias.setText(tripticoModificacion);
        sobresModificacionParroquias.setText(sobresModificacion);
        unidadesModificacionParroquias.setText(unidadesModificacion);
        if (poblacionModificacion != null) {
            poblacionModificacionParroquia.setPromptText(poblacionModificacion);
            poblacionModificacionParroquia.setValue(poblacionModificacion);
        }
        if (provinciaModificacion != null) {
            provinciaModificacionParroquia.setPromptText(provinciaModificacion);
            provinciaModificacionParroquia.setValue(provinciaModificacion);
        }
        if (zonaModificacion != null) {
            zonaModificacionParroquia.setPromptText(zonaModificacion);
            zonaModificacionParroquia.setValue(zonaModificacion);
        }
    }

    @FXML
    private void cancelarParroquia(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar la modificación de los datos de la parroquia?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarParroquia.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void modificarParroquia(ActionEvent event) {
        //Comprobar datos nulos ComboBox
        String poblacion = "";
        if (poblacionModificacionParroquia.getValue() != null) {
            poblacion = poblacionModificacionParroquia.getValue().toString();
        }
        String provincia = "";
        if (provinciaModificacionParroquia.getValue() != null) {
            provincia = provinciaModificacionParroquia.getValue().toString();
        }
        String zona = "";
        if (zonaModificacionParroquia.getValue() != null) {
            zona = zonaModificacionParroquia.getValue().toString();
        }
        // Actualizar campos a la base de datos
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "UPDATE parroquias SET nombre = ?, parroco = ?, zona = ?, poblacion = ?, direccion = ?, codigo_postal = ?,"
                + " provincia = ?, carteles = ?, tripticos = ?,sobres = ?, unidades_didacticas = ? WHERE id = " + idParroquia;
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nombreModificacionParroquia.getText());
            pst.setString(2, nombreModificacionParroco.getText());
            pst.setString(3, zona);
            pst.setString(4, poblacion);
            pst.setString(5, direccionModificacionParroquia.getText());
            pst.setString(6, codigoModificacionParroquia.getText());
            pst.setString(7, provincia);
            pst.setString(8, cartelesModificacionParroquias.getText());
            pst.setString(9, tripticosModificacionParroquias.getText());
            pst.setString(10, sobresModificacionParroquias.getText());
            pst.setString(11, unidadesModificacionParroquias.getText());

            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea guardar los datos de la parroquia?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Cambios guardados con éxito");
            alert1.showAndWait();
            Stage stageAux = (Stage) btnModificarParroquia.getScene().getWindow();
            stageAux.close();
        }
    }

}
