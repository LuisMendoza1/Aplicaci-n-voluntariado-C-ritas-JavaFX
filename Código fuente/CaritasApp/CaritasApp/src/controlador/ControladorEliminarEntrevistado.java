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

/**
 *
 * @author Luis Mendoza
 */
public class ControladorEliminarEntrevistado implements Initializable {

    @FXML
    private TableView<Entrevistado> tablaEntrevistados;
    @FXML
    private TableColumn<Entrevistado, String> colDNI;
    @FXML
    private TableColumn<Entrevistado, String> colNombre;
    @FXML
    private TableColumn<Entrevistado, String> colSexo;
    @FXML
    private TableColumn<Entrevistado, String> colNacimiento;
    @FXML
    private TableColumn<Entrevistado, String> colTelefono1;
    @FXML
    private TableColumn<Entrevistado, String> colTelefono2;
    @FXML
    private TableColumn<Entrevistado, String> colEmail;
    @FXML
    private TableColumn<Entrevistado, String> colPoblacion1;
    @FXML
    private TableColumn<Entrevistado, String> colProvincia;
    @FXML
    private TableColumn<Entrevistado, String> colNacionalidad;
    @FXML
    private TableColumn<Entrevistado, String> colEstadoCivil;
    @FXML
    private TableColumn<Entrevistado, String> colFechaAlta;
    @FXML
    private TableColumn<Entrevistado, String> colObservacionesVoluntario;
    @FXML
    private TableColumn<Entrevistado, String> colFoto;
    @FXML
    private TableColumn<Entrevistado, String> colDir;
    @FXML
    private TableColumn<Entrevistado, String> colCP;
    @FXML
    private TableColumn<Entrevistado, String> colEsVoluntario;

    ObservableList<Entrevistado> listaEntrevistados = FXCollections.observableArrayList();
    @FXML
    private Button btnCancelarEliminación;
    @FXML
    private Button btnEliminarEntrevistado;
    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM entrevistado");
            while (rs.next()) {
                String tieneFoto = "NO";
                String esVoluntario = "NO";
                if (rs.getString("tiene_foto").equals("1")) {
                    tieneFoto = "SI";
                }
                if (rs.getString("es_voluntario").equals("1")) {
                    esVoluntario = "SI";
                }
                listaEntrevistados.add(new Entrevistado(rs.getInt("id"), rs.getString("dni"), rs.getString("nombre"), rs.getString("sexo"),
                        rs.getString("fecha_nacimiento").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), rs.getString("telefono1"), rs.getString("telefono2"), rs.getString("email"),
                        rs.getString("direccion"), rs.getString("codigo_postal"), rs.getString("poblacion"), rs.getString("provincia"),
                        rs.getString("nacionalidad"), rs.getString("estado_civil"), rs.getString("fecha_entrevista").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), rs.getString("observaciones"),
                        rs.getBlob("foto"), tieneFoto, esVoluntario));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEliminarEntrevistado.class.getName()).log(Level.SEVERE, null, ex);
        }

        colDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colNacimiento.setCellValueFactory(new PropertyValueFactory<>("fecha_nacimiento"));
        colTelefono1.setCellValueFactory(new PropertyValueFactory<>("telefono1"));
        colTelefono2.setCellValueFactory(new PropertyValueFactory<>("telefono2"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colCP.setCellValueFactory(new PropertyValueFactory<>("codigo_postal"));
        colPoblacion1.setCellValueFactory(new PropertyValueFactory<>("poblacion"));
        colProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        colEstadoCivil.setCellValueFactory(new PropertyValueFactory<>("estado_civil"));
        colFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fecha_entrevista"));
        colObservacionesVoluntario.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        colFoto.setCellValueFactory(new PropertyValueFactory<>("tiene_foto"));
        colEsVoluntario.setCellValueFactory(new PropertyValueFactory<>("es_voluntario"));

        FilteredList<Entrevistado> listaEntrevistadosFiltrada = new FilteredList<>(listaEntrevistados, e -> true);
        tablaEntrevistados.setItems(listaEntrevistadosFiltrada);
        search.textProperty().addListener((prop, old, text) -> {
            listaEntrevistadosFiltrada.setPredicate(entrevistado -> {
                if (text == null || text.isEmpty()) {
                    return true;
                }
                String name = entrevistado.getNombre();
                return name.contains(text);
            });
        });

    }

    // Boton cancelar para cerrar la ventana
    @FXML
    private void cancelarEliminación(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar la consulta?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarEliminación.getScene().getWindow();
            stage.close();
        }
    }

    // Eliminar entrevistado
    @FXML
    private void eliminarEntrevistado(ActionEvent event) {
        if (tablaEntrevistados.getSelectionModel().getSelectedItem() != null) {
            //Seleccionar el voluntario de la tabla
            Entrevistado entrevistado = tablaEntrevistados.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("ATENCIÓN: si continua la operación el entrevistado/a quedará eliminado/a definitivamente. ¿Desea confirmar la eliminación");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    String sql = "DELETE FROM entrevistado WHERE id = " + entrevistado.getId();
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
                alert1.setContentText("Entrevistado/a eliminado/a");
                Optional<ButtonType> result1 = alert1.showAndWait();
                Stage stage = (Stage) btnEliminarEntrevistado.getScene().getWindow();
                stage.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Debe de seleccionar un/a voluntario/a de la tabla para registrar la baja");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
}
