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
import modelo.Parroquia;
import modelo.Voluntario;

/**
 *
 * @author Luis Mendoza
 */
public class ControladorEliminarParroquia implements Initializable {

    ObservableList<Parroquia> listaParroquias = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Parroquia, String> colParroco;
    @FXML
    private TableColumn<Parroquia, String> colZona;
    @FXML
    private TableColumn<Parroquia, String> colPoblacion;
    @FXML
    private TableColumn<Parroquia, String> colDireccion;
    @FXML
    private TableColumn<Parroquia, String> colCodigo;
    @FXML
    private TableColumn<Parroquia, String> colCarteles;
    @FXML
    private TableColumn<Parroquia, String> colTripticos;
    @FXML
    private TableColumn<Parroquia, String> colSobres;
    @FXML
    private TableColumn<Parroquia, String> colUnidades;
    @FXML
    private TableColumn<Parroquia, String> colNombre;
    @FXML
    private TableColumn<Parroquia, String> colProvincia;

    @FXML
    private TableView<Parroquia> tablaParroquias;
    @FXML
    private Button btnCancelarEliminacion;
    @FXML
    private Button btnEliminarParroquia;
    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM parroquias");
            while (rs.next()) {
                listaParroquias.add(new Parroquia(rs.getInt("id"), rs.getString("nombre"), rs.getString("parroco"), rs.getString("zona"), rs.getString("poblacion"),
                        rs.getString("direccion"), rs.getString("codigo_postal"), rs.getString("provincia"), rs.getString("carteles"), rs.getString("tripticos"),
                        rs.getString("sobres"), rs.getString("unidades_didacticas")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEliminarParroquia.class.getName()).log(Level.SEVERE, null, ex);
        }
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colParroco.setCellValueFactory(new PropertyValueFactory<>("parroco"));
        colZona.setCellValueFactory(new PropertyValueFactory<>("zona"));
        colPoblacion.setCellValueFactory(new PropertyValueFactory<>("poblacion"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo_postal"));
        colProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        colCarteles.setCellValueFactory(new PropertyValueFactory<>("carteles"));
        colTripticos.setCellValueFactory(new PropertyValueFactory<>("tripticos"));
        colSobres.setCellValueFactory(new PropertyValueFactory<>("sobres"));
        colUnidades.setCellValueFactory(new PropertyValueFactory<>("unidades_didacticas"));

        FilteredList<Parroquia> listaParroquiasFiltrada = new FilteredList<>(listaParroquias, p -> true);
        tablaParroquias.setItems(listaParroquiasFiltrada);
        search.textProperty().addListener((prop, old, text) -> {
            listaParroquiasFiltrada.setPredicate(parroquia -> {
                if (text == null || text.isEmpty()) {
                    return true;
                }
                String name = parroquia.getNombre();
                return name.contains(text);
            });
        });
    }

    @FXML
    private void cancelarEliminacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar la eliminación de la parroquia?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarEliminacion.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void eliminarParroquia(ActionEvent event) {
        if (tablaParroquias.getSelectionModel().getSelectedItem() != null) {
            //Seleccionar la parroquia de la tabla
            Parroquia parroquia = tablaParroquias.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("¿Desea confirmar la eliminación de la parroquia?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    String sql = "DELETE FROM parroquias WHERE id = " + parroquia.getId();
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
                alert1.setContentText("Parroquia eliminada");
                Optional<ButtonType> result1 = alert1.showAndWait();
                Stage stage = (Stage) btnEliminarParroquia.getScene().getWindow();
                stage.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Debe de seleccionar una parroquia de la tabla para registrar la eliminación");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
}
