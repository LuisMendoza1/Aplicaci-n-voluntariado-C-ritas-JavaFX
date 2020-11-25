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
public class ControladorConsultaProgramas implements Initializable {

    @FXML
    private Button btnCancelarConsulta;
    @FXML
    private Button btnModificarPrograma;
    @FXML
    private TableColumn<Equipo, String> colNombre;
    @FXML
    private TableView<Programa> tablaProgramas;
    @FXML
    private TextField search;
    
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
            Logger.getLogger(ControladorConsultaProgramas.class.getName()).log(Level.SEVERE, null, ex);
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
    private void cancelarConsulta(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar la consulta?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarConsulta.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void modificarPrograma(ActionEvent event) {
        if (tablaProgramas.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaModificarPrograma.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.getIcons().add(new Image("/img/imageAPP.png"));
                stage.setTitle("Cáritas Diocesana Albacete");
                stage.setScene(scene);

                //Enviar datos al ControladorModificarEquipo
                ControladorModificarPrograma controladorModificarPrograma = loader.getController();
                Programa programa = tablaProgramas.getSelectionModel().getSelectedItem();

                controladorModificarPrograma.modificarDatosPrograma(programa.getId(), programa.getNombre());

                stage.showAndWait();
                Stage stage1 = (Stage) btnModificarPrograma.getScene().getWindow();
                stage1.close();
            } catch (IOException ex) {
                Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Debe de seleccionar un programa de la tabla para modificar sus datos");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

}
