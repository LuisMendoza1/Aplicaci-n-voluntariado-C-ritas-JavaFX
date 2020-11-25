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
public class ControladorModificarPrograma implements Initializable {

    private int idPrograma;

    @FXML
    private TextField nombreModificacionPrograma;
    @FXML
    private Button btnCancelarPrograma;
    @FXML
    private Button btnGuardarPrograma;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void modificarDatosPrograma(int idModificacion, String nombreModificacion) {
        idPrograma = idModificacion;
        nombreModificacionPrograma.setText(nombreModificacion);
    }

    @FXML
    private void cancelarPrograma(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar la modificación de los datos del programa?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarPrograma.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void guardarPrograma(ActionEvent event) {

        // Actualizar campos a la base de datos
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "UPDATE programas SET programa = ? WHERE id = " + idPrograma;
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nombreModificacionPrograma.getText());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea guardar los datos del programa?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Cambios guardados con éxito");
            alert1.showAndWait();
            Stage stageAux = (Stage) btnGuardarPrograma.getScene().getWindow();
            stageAux.close();
        }
    }
}
