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
import modelo.Parroquia;
import modelo.Voluntario;

/**
 *
 * @author Luis Mendoza
 */
public class ControladorConsultaParroquias implements Initializable {

    @FXML
    private Button btnCancelarConsulta;
    @FXML
    private Button btnModificarParroquia;

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
            Logger.getLogger(ControladorConsultaParroquias.class.getName()).log(Level.SEVERE, null, ex);
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
    private void modificarParroquia(ActionEvent event) {
        if (tablaParroquias.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaModificarParroquia.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.getIcons().add(new Image("/img/imageAPP.png"));
                stage.setTitle("Cáritas Diocesana Albacete");
                stage.setScene(scene);

                //Enviar datos al ControladorModificarParroquia
                ControladorModificarParroquia controladorModificarParroquia = loader.getController();
                Parroquia parroquia = tablaParroquias.getSelectionModel().getSelectedItem();

                controladorModificarParroquia.modificarDatosParroquia(parroquia.getId(), parroquia.getNombre(), parroquia.getParroco(), parroquia.getZona(), parroquia.getPoblacion(),
                        parroquia.getDireccion(), parroquia.getCodigo_postal(), parroquia.getProvincia(), parroquia.getCarteles(), parroquia.getTripticos(), parroquia.getSobres(),
                        parroquia.getUnidades_didacticas());

                stage.showAndWait();
                Stage stage1 = (Stage) btnModificarParroquia.getScene().getWindow();
                stage1.close();
            } catch (IOException ex) {
                Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Debe de seleccionar una parroquia de la tabla para modificar sus datos");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

}
