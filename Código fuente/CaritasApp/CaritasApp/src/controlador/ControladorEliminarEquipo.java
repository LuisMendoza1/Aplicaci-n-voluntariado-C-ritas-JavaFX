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
import modelo.Voluntario;

/**
 *
 * @author Luis Mendoza
 */
public class ControladorEliminarEquipo implements Initializable {

    @FXML
    private Button btnEliminarEquipo;
    @FXML
    private Button btnCancelarEliminacion;

    ObservableList<Equipo> listaEquipos = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Equipo, String> colZona;
    @FXML
    private TableColumn<Equipo, String> colPoblacion;
    @FXML
    private TableColumn<Equipo, String> colDireccion;
    @FXML
    private TableColumn<Equipo, String> colCodigo;
    @FXML
    private TableColumn<Equipo, String> colNombre;
    @FXML
    private TableColumn<Equipo, String> colProvincia;
    @FXML
    private TableColumn<Equipo, String> colTelefono;
    @FXML
    private TableView<Equipo> tablaEquipos;
    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM equipos");
            while (rs.next()) {
                listaEquipos.add(new Equipo(rs.getInt("id"), rs.getString("nombre"), rs.getString("zona"), rs.getString("poblacion"),
                        rs.getString("direccion"), rs.getString("codigo_postal"), rs.getString("telefono"), rs.getString("provincia")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEliminarEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colZona.setCellValueFactory(new PropertyValueFactory<>("zona"));
        colPoblacion.setCellValueFactory(new PropertyValueFactory<>("poblacion"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo_postal"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));

        FilteredList<Equipo> listaEquiposFiltrada = new FilteredList<>(listaEquipos, e -> true);
        tablaEquipos.setItems(listaEquiposFiltrada);
        search.textProperty().addListener((prop, old, text) -> {
            listaEquiposFiltrada.setPredicate(equipo -> {
                if (text == null || text.isEmpty()) {
                    return true;
                }
                String name = equipo.getNombre();
                return name.contains(text);
            });
        });
    }

    @FXML
    private void eliminarEquipo(ActionEvent event) {
        if (tablaEquipos.getSelectionModel().getSelectedItem() != null) {
            //Seleccionar el equipo de la tabla
            Equipo equipo = tablaEquipos.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Desea confirmar la eliminación del equipo?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    String sql = "DELETE FROM equipos WHERE id = " + equipo.getId();
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
                alert1.setContentText("Equipo eliminado");
                Optional<ButtonType> result1 = alert1.showAndWait();
                Stage stage = (Stage) btnEliminarEquipo.getScene().getWindow();
                stage.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Debe de seleccionar un equipo de la tabla para registrar la eliminación");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    private void cancelarEliminacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar la eliminación del equipo?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarEliminacion.getScene().getWindow();
            stage.close();
        }
    }

}
