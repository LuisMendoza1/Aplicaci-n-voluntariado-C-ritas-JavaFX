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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Entrevistado;
import modelo.Equipo;
import modelo.Parroquia;
import modelo.Programa;
import modelo.Voluntario;

/**
 *
 * @author Luis Mendoza
 */
public class ControladorEliminarPrograma implements Initializable {

    @FXML
    private Button btnCancelarEliminacion;
    @FXML
    private TableColumn<Equipo, String> colNombre;
    @FXML
    private TextField search;
    @FXML
    private Button btnEliminarPrograma;
    @FXML
    private TableView<Programa> tablaProgramas;

    ObservableList<Programa> listaProgramas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM programas");
            while (rs.next()) {
                listaProgramas.add(new Programa(rs.getInt("id"), rs.getString("programa")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEliminarPrograma.class.getName()).log(Level.SEVERE, null, ex);
        }
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
      
        FilteredList<Programa> listaProgramasFiltrada = new FilteredList<>(listaProgramas, e -> true);
        tablaProgramas.setItems(listaProgramasFiltrada);
        search.textProperty().addListener((prop, old, text) -> {
            listaProgramasFiltrada.setPredicate(equipo -> {
                if (text == null || text.isEmpty()) {
                    return true;
                }
                String name = equipo.getNombre();
                return name.contains(text);
            });
        });
    }

    @FXML
    private void cancelarEliminacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar la eliminación del programa?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarEliminacion.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void eliminarPrograma(ActionEvent event) {
        if (tablaProgramas.getSelectionModel().getSelectedItem() != null) {
            //Seleccionar el equipo de la tabla
            Programa programa = tablaProgramas.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Desea confirmar la eliminación del programa?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    String sql = "DELETE FROM programas WHERE id = " + programa.getId();
                    ConnectionClass connectionClass = new ConnectionClass();
                    Connection connection = connectionClass.getConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.execute();
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setHeaderText(null);
                alert1.setContentText("Programa eliminado");
                Optional<ButtonType> result1 = alert1.showAndWait();
                Stage stage = (Stage) btnEliminarPrograma.getScene().getWindow();
                stage.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Debe de seleccionar un programa de la tabla para registrar la eliminación");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

}
