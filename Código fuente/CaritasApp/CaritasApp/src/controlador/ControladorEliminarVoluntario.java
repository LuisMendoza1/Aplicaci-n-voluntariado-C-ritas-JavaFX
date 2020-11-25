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
import modelo.Voluntario;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorEliminarVoluntario implements Initializable {

    @FXML
    private TableView<Voluntario> tablaVoluntarios;
    @FXML
    private TableColumn<Voluntario, String> colDNI;
    @FXML
    private TableColumn<Voluntario, String> colNombre;
    @FXML
    private TableColumn<Voluntario, String> colSexo;
    @FXML
    private TableColumn<Voluntario, Date> colNacimiento;
    @FXML
    private TableColumn<Voluntario, Integer> colTelefono1;
    @FXML
    private TableColumn<Voluntario, Integer> colTelefono2;
    @FXML
    private TableColumn<Voluntario, String> colEmail;
    @FXML
    private TableColumn<Voluntario, String> colDir1;
    @FXML
    private TableColumn<Voluntario, String> colCP1;
    @FXML
    private TableColumn<Voluntario, String> colCP2;
    @FXML
    private TableColumn<Voluntario, String> colPoblacion1;
    @FXML
    private TableColumn<Voluntario, String> colPoblacion2;
    @FXML
    private TableColumn<Voluntario, String> colProvincia;
    @FXML
    private TableColumn<Voluntario, String> colNacionalidad;
    @FXML
    private TableColumn<Voluntario, String> colEstadoCivil;
    @FXML
    private TableColumn<Voluntario, String> colEstudios;
    @FXML
    private TableColumn<Voluntario, String> colLugEstudios;
    @FXML
    private TableColumn<Voluntario, String> colObsFormacion;
    @FXML
    private TableColumn<Voluntario, String> colIdioma1;
    @FXML
    private TableColumn<Voluntario, String> colNivel1;
    @FXML
    private TableColumn<Voluntario, String> colIdioma2;
    @FXML
    private TableColumn<Voluntario, String> colNivel2;
    @FXML
    private TableColumn<Voluntario, String> colIdioma3;
    @FXML
    private TableColumn<Voluntario, String> colNivel3;
    @FXML
    private TableColumn<Voluntario, String> colInformatica;
    @FXML
    private TableColumn<Voluntario, String> colSitLab;
    @FXML
    private TableColumn<Voluntario, String> colCarnet;
    @FXML
    private TableColumn<Voluntario, String> colExperienciaLaboral;
    @FXML
    private TableColumn<Voluntario, String> colFechaAlta;
    @FXML
    private TableColumn<Voluntario, String> colZona;
    @FXML
    private TableColumn<Voluntario, String> colEquipo1;
    @FXML
    private TableColumn<Voluntario, String> colCargo1;
    @FXML
    private TableColumn<Voluntario, String> colCargo2;
    @FXML
    private TableColumn<Voluntario, String> colCargo3;
    @FXML
    private TableColumn<Voluntario, String> colPrograma1;
    @FXML
    private TableColumn<Voluntario, String> colPrograma2;
    @FXML
    private TableColumn<Voluntario, Integer> colObservacionesVoluntario;
    @FXML
    private TableColumn<Voluntario, Integer> colConsejo;
    @FXML
    private TableColumn<Voluntario, Integer> colFicha;
    @FXML
    private TableColumn<Voluntario, Integer> colDelitos;
    @FXML
    private TableColumn<Voluntario, Integer> colImagen;
    @FXML
    private TableColumn<Voluntario, Integer> colColaboracion;
    @FXML
    private TableColumn<Voluntario, Integer> colAntecedentes;
    @FXML
    private TableColumn<Voluntario, Integer> colPadres;
    @FXML
    private TableColumn<Voluntario, Integer> colRGPD;
    @FXML
    private TableColumn<Voluntario, Integer> colEnviosEmail;
    @FXML
    private TableColumn<Voluntario, Integer> colEnviosPostales;
    @FXML
    private TableColumn<Voluntario, Integer> colObsDocumentacion;
    @FXML
    private TableColumn<Voluntario, Integer> colBaja;
    @FXML
    private TableColumn<Voluntario, Date> colFechaBaja;
    @FXML
    private TableColumn<Voluntario, String> colTelefono3;
    @FXML
    private TableColumn<Voluntario, String> colDir2;
    @FXML
    private TableColumn<Voluntario, String> colFoto;

    ObservableList<Voluntario> listaVoluntarios = FXCollections.observableArrayList();
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM voluntario");
            while (rs.next()) {
                String carnet = "NO";
                String ficha = "NO";
                String consejo = "NO";
                String delitos = "NO";
                String publicacion = "NO";
                String acuerdo = "NO";
                String antecedentes = "NO";
                String autorizacion = "NO";
                String rgpd = "NO";
                String email = "NO";
                String postales = "NO";
                String baja = "NO";
                String tieneFoto = "NO";
                if (rs.getString("carnet").equals("1")) {
                    carnet = "SI";
                }
                if (rs.getString("ficha_voluntario").equals("1")) {
                    ficha = "SI";
                }
                if (rs.getString("consejo_diocesano").equals("1")) {
                    consejo = "SI";
                }
                if (rs.getString("delitos_sexuales").equals("1")) {
                    delitos = "SI";
                }
                if (rs.getString("publicacion_imagen").equals("1")) {
                    publicacion = "SI";
                }
                if (rs.getString("acuerdo_colaboracion").equals("1")) {
                    acuerdo = "SI";
                }
                if (rs.getString("antecedentes_penales").equals("1")) {
                    antecedentes = "SI";
                }
                if (rs.getString("autorizacion_paterna").equals("1")) {
                    autorizacion = "SI";
                }
                if (rs.getString("rgpd").equals("1")) {
                    rgpd = "SI";
                }
                if (rs.getString("envios_email").equals("1")) {
                    email = "SI";
                }
                if (rs.getString("envios_postales").equals("1")) {
                    postales = "SI";
                }
                if (rs.getString("baja_caritas").equals("1")) {
                    baja = "SI";
                }
                if (rs.getString("tiene_foto").equals("1")) {
                    tieneFoto = "SI";
                }
                listaVoluntarios.add(new Voluntario(rs.getInt("id"), rs.getString("dni"), rs.getString("nombre"), rs.getString("sexo"), rs.getString("fecha_nacimiento").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), rs.getString("telefono_1"),
                        rs.getString("telefono_2"), rs.getString("email"), rs.getString("direccion_1"), rs.getString("codigo_postal_1"), rs.getString("poblacion_1"),
                        rs.getString("provincia"), rs.getString("nacionalidad"), rs.getString("estado_civil"), rs.getString("estudios"), rs.getString("lugar_estudios"), rs.getString("observaciones_formacion"),
                        rs.getString("idioma_1"), rs.getString("idioma_2"), rs.getString("idioma_3"), rs.getString("nivel_1"), rs.getString("nivel_2"), rs.getString("nivel_3"), rs.getString("informatica"),
                        rs.getString("situacion_laboral"), carnet, rs.getString("experiencia_laboral"), rs.getString("fecha_alta").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), rs.getString("zona"), rs.getString("poblacion_2"),
                        rs.getString("equipo_1"), rs.getString("direccion_2"), rs.getString("codigo_postal_2"), rs.getString("telefono_3"), rs.getString("cargo_1"), rs.getString("programa_1"),
                        rs.getString("programa_2"), rs.getString("cargo_2"), rs.getString("cargo_3"), rs.getString("observaciones_voluntario"), consejo, ficha,
                        delitos, publicacion, acuerdo, antecedentes, autorizacion, rgpd, email, postales, rs.getString("observaciones_documentacion"), baja, rs.getString("fecha_baja").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), rs.getBlob("foto"), tieneFoto));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEliminarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }

        colDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colNacimiento.setCellValueFactory(new PropertyValueFactory<>("fecha_nacimiento"));
        colTelefono1.setCellValueFactory(new PropertyValueFactory<>("telefono_1"));
        colTelefono2.setCellValueFactory(new PropertyValueFactory<>("telefono_2"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDir1.setCellValueFactory(new PropertyValueFactory<>("direccion_1"));
        colCP1.setCellValueFactory(new PropertyValueFactory<>("codigo_postal_1"));
        colPoblacion1.setCellValueFactory(new PropertyValueFactory<>("poblacion_1"));
        colProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        colEstadoCivil.setCellValueFactory(new PropertyValueFactory<>("estado_civil"));
        colEstudios.setCellValueFactory(new PropertyValueFactory<>("estudios"));
        colLugEstudios.setCellValueFactory(new PropertyValueFactory<>("lugar_estudios"));
        colObsFormacion.setCellValueFactory(new PropertyValueFactory<>("observaciones_formacion"));
        colIdioma1.setCellValueFactory(new PropertyValueFactory<>("idioma_1"));
        colIdioma2.setCellValueFactory(new PropertyValueFactory<>("idioma_2"));
        colIdioma3.setCellValueFactory(new PropertyValueFactory<>("idioma_3"));
        colNivel1.setCellValueFactory(new PropertyValueFactory<>("nivel_1"));
        colNivel2.setCellValueFactory(new PropertyValueFactory<>("nivel_2"));
        colNivel3.setCellValueFactory(new PropertyValueFactory<>("nivel_3"));
        colInformatica.setCellValueFactory(new PropertyValueFactory<>("informatica"));
        colSitLab.setCellValueFactory(new PropertyValueFactory<>("situacion_laboral"));
        colCarnet.setCellValueFactory(new PropertyValueFactory<>("carnet"));
        colExperienciaLaboral.setCellValueFactory(new PropertyValueFactory<>("experiencia_laboral"));
        colFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fecha_alta"));
        colZona.setCellValueFactory(new PropertyValueFactory<>("zona"));
        colPoblacion2.setCellValueFactory(new PropertyValueFactory<>("poblacion_2"));
        colEquipo1.setCellValueFactory(new PropertyValueFactory<>("equipo_1"));
        colDir2.setCellValueFactory(new PropertyValueFactory<>("direccion_2"));
        colCP2.setCellValueFactory(new PropertyValueFactory<>("codigo_postal_2"));
        colTelefono3.setCellValueFactory(new PropertyValueFactory<>("telefono_3"));
        colCargo1.setCellValueFactory(new PropertyValueFactory<>("cargo_1"));
        colPrograma1.setCellValueFactory(new PropertyValueFactory<>("programa_1"));
        colPrograma2.setCellValueFactory(new PropertyValueFactory<>("programa_2"));
        colCargo2.setCellValueFactory(new PropertyValueFactory<>("cargo_2"));
        colCargo3.setCellValueFactory(new PropertyValueFactory<>("cargo_3"));
        colObservacionesVoluntario.setCellValueFactory(new PropertyValueFactory<>("observaciones_voluntario"));
        colConsejo.setCellValueFactory(new PropertyValueFactory<>("consejo_diocesano"));
        colFicha.setCellValueFactory(new PropertyValueFactory<>("ficha_voluntario"));
        colDelitos.setCellValueFactory(new PropertyValueFactory<>("delitos_sexuales"));
        colImagen.setCellValueFactory(new PropertyValueFactory<>("publicacion_imagen"));
        colColaboracion.setCellValueFactory(new PropertyValueFactory<>("acuerdo_colaboracion"));
        colAntecedentes.setCellValueFactory(new PropertyValueFactory<>("antecedentes_penales"));
        colPadres.setCellValueFactory(new PropertyValueFactory<>("autorizacion_paterna"));
        colRGPD.setCellValueFactory(new PropertyValueFactory<>("rgpd"));
        colEnviosEmail.setCellValueFactory(new PropertyValueFactory<>("envios_email"));
        colEnviosPostales.setCellValueFactory(new PropertyValueFactory<>("envios_postales"));
        colObsDocumentacion.setCellValueFactory(new PropertyValueFactory<>("observaciones_documentacion"));
        colBaja.setCellValueFactory(new PropertyValueFactory<>("baja_caritas"));
        colFechaBaja.setCellValueFactory(new PropertyValueFactory<>("fecha_baja"));
        colFoto.setCellValueFactory(new PropertyValueFactory<>("tiene_foto"));

        FilteredList<Voluntario> listaVoluntariosFiltrada = new FilteredList<>(listaVoluntarios, v -> true);
        tablaVoluntarios.setItems(listaVoluntariosFiltrada);
        search.textProperty().addListener((prop, old, text) -> {
            listaVoluntariosFiltrada.setPredicate(voluntario -> {
                if (text == null || text.isEmpty()) {
                    return true;
                }
                String name = voluntario.getNombre();
                return name.contains(text);
            });
        });
    }

    // Boton cancelar para cerrar la ventana
    @FXML
    private void cancelarEliminacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar la eliminación del voluntario/a?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        }
    }

    // Eliminación del voluntario
    @FXML
    private void eliminarVoluntario(ActionEvent event) {
        if (tablaVoluntarios.getSelectionModel().getSelectedItem() != null) {
            //Seleccionar el voluntario de la tabla
            Voluntario voluntario = tablaVoluntarios.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("ATENCIÓN: si continua la operación el voluntario/a quedará eliminado definitivamente. ¿Desea confirmar la eliminación");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    String sql = "DELETE FROM voluntario WHERE id = " + voluntario.getId();
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
                alert1.setContentText("Voluntario/a eliminado/a");
                Optional<ButtonType> result1 = alert1.showAndWait();
                Stage stage = (Stage) btnEliminar.getScene().getWindow();
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
