/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Toolkit;
import conexion.ConnectionClass;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorModificarVoluntario implements Initializable {

    @FXML
    private TextField dniVoluntario;
    @FXML
    private TextField nombreVoluntario;
    @FXML
    private ToggleGroup sexo;
    @FXML
    private DatePicker nacimientoVoluntario;
    @FXML
    private TextField telefono1Voluntario;
    @FXML
    private TextField telefono2Voluntario;
    @FXML
    private TextField emailVoluntario;
    @FXML
    private TextField direccion1Voluntario;
    @FXML
    private TextField codigo1Voluntario;
    @FXML
    private ComboBox<String> poblacion1Voluntario;
    @FXML
    private ComboBox<String> provinciaVoluntario;
    @FXML
    private TextField nacionalidadVoluntario;
    @FXML
    private ComboBox<String> estadoVoluntario;
    @FXML
    private TextArea observaciones1Voluntario;
    @FXML
    private ComboBox<String> estudiosVoluntario;
    @FXML
    private TextField lugarEstudiosVoluntario;
    @FXML
    private ComboBox<String> idioma1Voluntario;
    @FXML
    private ComboBox<String> idioma2Voluntario;
    @FXML
    private ComboBox<String> idioma3Voluntario;
    @FXML
    private ComboBox<String> nivel1Voluntario;
    @FXML
    private ComboBox<String> nivel2Voluntario;
    @FXML
    private ComboBox<String> nivel3Voluntario;
    @FXML
    private ComboBox<String> informaticaVoluntario;
    @FXML
    private ComboBox<String> laboralVoluntario;
    @FXML
    private CheckBox carnetVoluntario;
    @FXML
    private TextArea experienciaVoluntario;
    @FXML
    private DatePicker altaVoluntario;
    @FXML
    private ComboBox<String> zonaVoluntario;
    @FXML
    private ComboBox<String> poblacion2Voluntario;
    @FXML
    private ComboBox<String> equipoVoluntario;
    @FXML
    private TextField direccion2Voluntario;
    @FXML
    private TextField codigo2Voluntario;
    @FXML
    private ComboBox<String> cargoEquipoVoluntario;
    @FXML
    private ComboBox<String> programa1Voluntario;
    @FXML
    private ComboBox<String> programa2Voluntario;
    @FXML
    private ComboBox<String> cargo1Voluntario;
    @FXML
    private TextField telefono3Voluntario;
    @FXML
    private ComboBox<String> cargo2Voluntario;
    @FXML
    private TextArea observaciones2Voluntario;
    @FXML
    private CheckBox consejoVoluntario;
    @FXML
    private TextArea observaciones3Voluntario;
    @FXML
    private CheckBox enviosVoluntario;
    @FXML
    private CheckBox postalesVoluntario;
    @FXML
    private CheckBox bajaVoluntario;
    @FXML
    private DatePicker fBajaVoluntario;
    @FXML
    private CheckBox fichaVoluntario;
    @FXML
    private CheckBox delitosVoluntario;
    @FXML
    private CheckBox publicacionVoluntarios;
    @FXML
    private CheckBox acuerdoVoluntario;
    @FXML
    private CheckBox antecedentesVoluntario;
    @FXML
    private CheckBox autorizacionVoluntario;
    @FXML
    private CheckBox rgpdVoluntario;
    @FXML
    private Button btnCancelarVoluntario;
    @FXML
    private Button btnGuardarVoluntario;
    @FXML
    private RadioButton sexoHombre;
    @FXML
    private RadioButton sexoMujer;
    @FXML
    private Button btnSubirFoto;
    @FXML
    private Button btnEliminarFoto;
    @FXML
    private ImageView fondoFoto;

    private int idVoluntario;

    private String dniPredefinido;

    private Image imgFoto;

    private File fileFoto;

    private FileInputStream fis;

    final FileChooser fileChooser = new FileChooser();

    private URL rutaFoto;

    private Image image;

    private Boolean cambiaFoto;

    private String tieneFoto;

    ObservableList<String> listaPoblaciones = FXCollections.observableArrayList();

    ObservableList<String> listaProvincias = FXCollections.observableArrayList();

    ObservableList<String> listaEstado = FXCollections.observableArrayList();

    ObservableList<String> listaEstudios = FXCollections.observableArrayList();

    ObservableList<String> listaIdioma = FXCollections.observableArrayList();

    ObservableList<String> listaNivel = FXCollections.observableArrayList();

    ObservableList<String> listaLaboral = FXCollections.observableArrayList();

    ObservableList<String> listaZona = FXCollections.observableArrayList();

    ObservableList<String> listaEquipos = FXCollections.observableArrayList();

    ObservableList<String> listaCargos = FXCollections.observableArrayList();

    ObservableList<String> listaProgramas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            ResultSet rs1 = connection.createStatement().executeQuery("SELECT * FROM poblaciones");
            ResultSet rs2 = connection.createStatement().executeQuery("SELECT * FROM provincias");
            ResultSet rs3 = connection.createStatement().executeQuery("SELECT * FROM estado_civil");
            ResultSet rs4 = connection.createStatement().executeQuery("SELECT * FROM estudios");
            ResultSet rs5 = connection.createStatement().executeQuery("SELECT * FROM idiomas");
            ResultSet rs6 = connection.createStatement().executeQuery("SELECT * FROM nivel");
            ResultSet rs7 = connection.createStatement().executeQuery("SELECT * FROM situacion_laboral");
            ResultSet rs8 = connection.createStatement().executeQuery("SELECT * FROM zonas");
            ResultSet rs9 = connection.createStatement().executeQuery("SELECT * FROM equipos");
            ResultSet rs10 = connection.createStatement().executeQuery("SELECT * FROM cargos");
            ResultSet rs11 = connection.createStatement().executeQuery("SELECT * FROM programas");
            while (rs1.next()) {
                listaPoblaciones.add((rs1.getString("nombre")));
            }
            while (rs2.next()) {
                listaProvincias.add((rs2.getString("nombre")));
            }
            while (rs3.next()) {
                listaEstado.add((rs3.getString("estado")));
            }
            while (rs4.next()) {
                listaEstudios.add((rs4.getString("estudios")));
            }
            while (rs5.next()) {
                listaIdioma.add((rs5.getString("idioma")));
            }
            while (rs6.next()) {
                listaNivel.add((rs6.getString("nivel")));
            }
            while (rs7.next()) {
                listaLaboral.add((rs7.getString("situacion")));
            }
            while (rs8.next()) {
                listaZona.add((rs8.getString("zona")));
            }
            while (rs9.next()) {
                listaEquipos.add((rs9.getString("nombre")));
            }
            while (rs10.next()) {
                listaCargos.add((rs10.getString("cargo")));
            }
            while (rs11.next()) {
                listaProgramas.add((rs11.getString("programa")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
        poblacion1Voluntario.setItems(listaPoblaciones);
        poblacion2Voluntario.setItems(listaPoblaciones);
        provinciaVoluntario.setItems(listaProvincias);
        estadoVoluntario.setItems(listaEstado);
        estudiosVoluntario.setItems(listaEstudios);
        idioma1Voluntario.setItems(listaIdioma);
        idioma2Voluntario.setItems(listaIdioma);
        idioma3Voluntario.setItems(listaIdioma);
        nivel1Voluntario.setItems(listaNivel);
        nivel2Voluntario.setItems(listaNivel);
        nivel3Voluntario.setItems(listaNivel);
        informaticaVoluntario.setItems(listaNivel);
        laboralVoluntario.setItems(listaLaboral);
        zonaVoluntario.setItems(listaZona);
        equipoVoluntario.setItems(listaEquipos);
        cargoEquipoVoluntario.setItems(listaCargos);
        cargo1Voluntario.setItems(listaCargos);
        cargo2Voluntario.setItems(listaCargos);
        programa1Voluntario.setItems(listaProgramas);
        programa2Voluntario.setItems(listaProgramas);
        cambiaFoto = false;
    }

    //Datos de la incorporación a la vista de Nuevo Voluntario
    public void modificarDatosPersonales(int idModificacion, String dniModificacion, String nombreModificacion, String sexoModificacion, String nacimientoModificacion, String tel1Modificacion,
            String tel2Modificacion, String emailModificacion, String direccionModificacion, String codigoModificacion, String poblacionModificacion,
            String provinciaModificacion, String nacionalidadModificacion, String estadoModificacion, String estudiosModificacion, String lugarModificacion,
            String observacionesformacionModificacion, String idioma1Modificacion, String idioma2Modificacion, String idioma3Modificacion, String nivel1Modificacion,
            String nivel2Modificacion, String nivel3Modificacion, String informaticaModificacion, String situacionModificacion, String experienciaModificacion,
            String carnetModificacion, String altaModificacion, String zonaModificacion, String poblacion2Modificacion, String equipo1Modificacion,
            String direccion2Modificacion, String codigo2Modificacion, String tel3Modificacion, String cargo1Modificacion, String programa1Modificacion,
            String programa2Modificacion, String cargo2Modificacion, String cargo3Modificacion, String observacionesvoluntariadoModificacion,
            String consejoModificacion, String fichaModificacion, String delitosModificacion, String publicacionModificacion, String acuerdoModificacion,
            String antecedentesModificacion, String autorizacionModificacion, String rgpdModificacion, String enviosModificacion, String postalesModificacion,
            String obsevacionesdocumentacionModificacion, String bajaModificacion, String fechabajaModificacion, Blob fotoModificacion, String tienefotoModificacion) {
        idVoluntario = idModificacion;
        dniPredefinido = dniModificacion;
        dniVoluntario.setText(dniModificacion);
        nombreVoluntario.setText(nombreModificacion);
        if (sexoModificacion.equals("H")) {
            sexoHombre.setSelected(true);
            sexoMujer.setSelected(false);
        }
        if (sexoModificacion.equals("M")) {
            sexoHombre.setSelected(false);
            sexoMujer.setSelected(true);
        }
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date fechaNac = new Date();
        try {
            if (!nacimientoModificacion.equals("")) {
                fechaNac = format.parse(nacimientoModificacion);
                nacimientoVoluntario.setValue(fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
        } catch (ParseException ex) {
            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
        telefono1Voluntario.setText(tel1Modificacion);
        telefono2Voluntario.setText(tel2Modificacion);
        emailVoluntario.setText(emailModificacion);
        direccion1Voluntario.setText(direccionModificacion);
        codigo1Voluntario.setText(codigoModificacion);
        if (poblacionModificacion != null) {
            poblacion1Voluntario.setPromptText(poblacionModificacion);
            poblacion1Voluntario.setValue(poblacionModificacion);
        }
        if (provinciaModificacion != null) {
            provinciaVoluntario.setPromptText(provinciaModificacion);
            provinciaVoluntario.setValue(provinciaModificacion);
        }
        nacionalidadVoluntario.setText(nacionalidadModificacion);
        if (estadoModificacion != null) {
            estadoVoluntario.setPromptText(estadoModificacion);
            estadoVoluntario.setValue(estadoModificacion);
        }
        if (estudiosModificacion != null) {
            estudiosVoluntario.setPromptText(estudiosModificacion);
            estudiosVoluntario.setValue(estudiosModificacion);
        }
        lugarEstudiosVoluntario.setText(lugarModificacion);
        observaciones1Voluntario.setText(observacionesformacionModificacion);
        if (idioma1Modificacion != null) {
            idioma1Voluntario.setPromptText(idioma1Modificacion);
            idioma1Voluntario.setValue(idioma1Modificacion);
        }
        if (idioma2Modificacion != null) {
            idioma2Voluntario.setPromptText(idioma2Modificacion);
            idioma2Voluntario.setValue(idioma2Modificacion);
        }
        if (idioma3Modificacion != null) {
            idioma3Voluntario.setPromptText(idioma3Modificacion);
            idioma3Voluntario.setValue(idioma3Modificacion);
        }
        if (nivel1Modificacion != null) {
            nivel1Voluntario.setPromptText(nivel1Modificacion);
            nivel1Voluntario.setValue(nivel1Modificacion);
        }
        if (nivel2Modificacion != null) {
            nivel2Voluntario.setPromptText(nivel2Modificacion);
            nivel2Voluntario.setValue(nivel2Modificacion);
        }
        if (nivel3Modificacion != null) {
            nivel3Voluntario.setPromptText(nivel3Modificacion);
            nivel3Voluntario.setValue(nivel3Modificacion);
        }
        if (informaticaModificacion != null) {
            informaticaVoluntario.setPromptText(informaticaModificacion);
            informaticaVoluntario.setValue(informaticaModificacion);
        }
        if (situacionModificacion != null) {
            laboralVoluntario.setPromptText(situacionModificacion);
            laboralVoluntario.setValue(situacionModificacion);
        }
        experienciaVoluntario.setText(experienciaModificacion);
        if (carnetModificacion.equals("SI")) {
            carnetVoluntario.setSelected(true);
        }
        Date fechaAlta = new Date();
        try {
            if (!altaModificacion.equals("")) {
                fechaAlta = format.parse(altaModificacion);
                altaVoluntario.setValue(fechaAlta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
        } catch (ParseException ex) {
            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (zonaModificacion != null) {
            zonaVoluntario.setPromptText(zonaModificacion);
            zonaVoluntario.setValue(zonaModificacion);
        }
        if (poblacion2Modificacion != null) {
            poblacion2Voluntario.setPromptText(poblacion2Modificacion);
            poblacion2Voluntario.setValue(poblacion2Modificacion);
        }
        if (equipo1Modificacion != null) {
            equipoVoluntario.setPromptText(equipo1Modificacion);
            equipoVoluntario.setValue(equipo1Modificacion);
        }
        direccion2Voluntario.setText(direccion2Modificacion);
        codigo2Voluntario.setText(codigo2Modificacion);
        telefono3Voluntario.setText(tel3Modificacion);
        if (cargo1Modificacion != null) {
            cargoEquipoVoluntario.setPromptText(cargo1Modificacion);
            cargoEquipoVoluntario.setValue(cargo1Modificacion);
        }
        if (programa1Modificacion != null) {
            programa1Voluntario.setPromptText(programa1Modificacion);
            programa1Voluntario.setValue(programa1Modificacion);
        }
        if (programa2Modificacion != null) {
            programa2Voluntario.setPromptText(programa2Modificacion);
            programa2Voluntario.setValue(programa2Modificacion);
        }
        if (cargo2Modificacion != null) {
            cargo1Voluntario.setPromptText(cargo2Modificacion);
            cargo1Voluntario.setValue(cargo2Modificacion);
        }
        if (cargo3Modificacion != null) {
            cargo2Voluntario.setPromptText(cargo3Modificacion);
            cargo2Voluntario.setValue(cargo3Modificacion);
        }
        observaciones2Voluntario.setText(observacionesvoluntariadoModificacion);
        if (consejoModificacion.equals("SI")) {
            consejoVoluntario.setSelected(true);
        }
        if (fichaModificacion.equals("SI")) {
            fichaVoluntario.setSelected(true);
        }
        if (delitosModificacion.equals("SI")) {
            delitosVoluntario.setSelected(true);
        }
        if (publicacionModificacion.equals("SI")) {
            publicacionVoluntarios.setSelected(true);
        }
        if (acuerdoModificacion.equals("SI")) {
            acuerdoVoluntario.setSelected(true);
        }
        if (antecedentesModificacion.equals("SI")) {
            antecedentesVoluntario.setSelected(true);
        }
        if (autorizacionModificacion.equals("SI")) {
            autorizacionVoluntario.setSelected(true);
        }
        if (rgpdModificacion.equals("SI")) {
            rgpdVoluntario.setSelected(true);
        }
        if (enviosModificacion.equals("SI")) {
            enviosVoluntario.setSelected(true);
        }
        if (postalesModificacion.equals("SI")) {
            postalesVoluntario.setSelected(true);
        }
        observaciones3Voluntario.setText(obsevacionesdocumentacionModificacion);
        if (bajaModificacion.equals("SI")) {
            bajaVoluntario.setSelected(true);
        }
        Date fechaBaja = new Date();
        try {
            if (!fechabajaModificacion.equals("")) {
                fechaBaja = format.parse(fechabajaModificacion);
                fBajaVoluntario.setValue(fechaBaja.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
        } catch (ParseException ex) {
            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Obtener imagen de la BD
        try {
            try {
                byte[] imageByte = fotoModificacion.getBytes(1, (int) fotoModificacion.length());
                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByte));
                ImageIcon icono = new ImageIcon(bufferedImage);
                image = SwingFXUtils.toFXImage(bufferedImage, null);
                fondoFoto.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (tienefotoModificacion.equals("SI")) {
            tieneFoto = "1";
        } else {
            tieneFoto = "0";
        }
    }

    @FXML
    private void subirFoto(ActionEvent event) {
        Stage stage = (Stage) btnSubirFoto.getScene().getWindow();
        fileFoto = fileChooser.showOpenDialog(stage);
        if (fileFoto.isFile()
                && (fileFoto.getName().contains(".jpg") || fileFoto.getName().contains(".png")
                || fileFoto.getName().contains(".bmp") || fileFoto.getName().contains(".gif"))) {
            try {
                String URLimage = fileFoto.toURI().toURL().toString();
                imgFoto = new Image(URLimage);
                fondoFoto.setImage(imgFoto);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cambiaFoto = true;
        tieneFoto = "1";
    }

    @FXML
    private void eliminarFoto(ActionEvent event) {
        Stage stage = (Stage) btnEliminarFoto.getScene().getWindow();
        rutaFoto = getClass().getResource("/img/imageSinFoto.png");
        imgFoto = new Image(rutaFoto.toString(), 145, 145, false, true);
        fondoFoto.setImage(imgFoto);
        BufferedImage imgAux;
        try {
            imgAux = ImageIO.read(rutaFoto);
            fileFoto = new File("imageSinFoto.png");
            ImageIO.write(imgAux, "png", fileFoto);
        } catch (IOException ex) {
            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
        cambiaFoto = true;
        tieneFoto = "0";
    }

    @FXML
    private void cancelarVoluntario(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar la modificación de los datos del voluntario/a?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarVoluntario.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void guardarVoluntario(ActionEvent event
    ) {
        // Comprobar DNI duplicado
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        int vecesDni = 0;
        String sql1 = "SELECT COUNT(*) FROM Voluntario WHERE dni = '" + dniVoluntario.getText() + "'";
        Statement statement1;
        try {
            statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(sql1);
            while (result.next()) {
                vecesDni = result.getInt("COUNT(*)");
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEntrevista.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Si existe el DNI no actualizar
        if (vecesDni > 0 && !dniVoluntario.getText().equals(dniPredefinido)) { // si el DNI es el mismo que antes, que deje guardar
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Ya existe otro/a voluntario/a con el mismo DNI");
            alert.showAndWait();
        } else {
            // Actualizar campos a la base de datos
            if (dniVoluntario.getText().toString().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Los campos con '*' son obligatorios");
                alert.showAndWait();
            } else {
                //Comprobar datos nulos RadioButtons
                String sex = "";
                RadioButton sexoEntrevista = null;
                if ((RadioButton) sexo.getSelectedToggle() != null) {
                    sexoEntrevista = (RadioButton) sexo.getSelectedToggle();
                    sex = sexoEntrevista.getText();
                }
                //Comprobar datos nulos DatePickers
                String fNacimiento = "";
                if (nacimientoVoluntario.getValue() != null) {
                    fNacimiento = nacimientoVoluntario.getValue().toString().replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1");
                }
                String fAlta = "";
                if (altaVoluntario.getValue() != null) {
                    fAlta = altaVoluntario.getValue().toString().replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1");
                }
                String fBaja = "";
                if (fBajaVoluntario.getValue() != null) {
                    fBaja = fBajaVoluntario.getValue().toString().replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1");
                }
                //Comprobar datos nulos ComboBox
                String poblacion1 = "";
                if (poblacion1Voluntario.getValue() != null) {
                    poblacion1 = poblacion1Voluntario.getValue().toString();
                }
                String poblacion2 = "";
                if (poblacion2Voluntario.getValue() != null) {
                    poblacion2 = poblacion2Voluntario.getValue().toString();
                }
                String provincia = "";
                if (provinciaVoluntario.getValue() != null) {
                    provincia = provinciaVoluntario.getValue().toString();
                }
                String estado = "";
                if (estadoVoluntario.getValue() != null) {
                    estado = estadoVoluntario.getValue().toString();
                }
                String estudios = "";
                if (estudiosVoluntario.getValue() != null) {
                    estudios = estudiosVoluntario.getValue().toString();
                }
                String idioma1 = "";
                if (idioma1Voluntario.getValue() != null) {
                    idioma1 = idioma1Voluntario.getValue().toString();
                }
                String idioma2 = "";
                if (idioma2Voluntario.getValue() != null) {
                    idioma2 = idioma2Voluntario.getValue().toString();
                }
                String idioma3 = "";
                if (idioma3Voluntario.getValue() != null) {
                    idioma3 = idioma3Voluntario.getValue().toString();
                }
                String nivel1 = "";
                if (nivel1Voluntario.getValue() != null) {
                    nivel1 = nivel1Voluntario.getValue().toString();
                }
                String nivel2 = "";
                if (nivel2Voluntario.getValue() != null) {
                    nivel2 = nivel2Voluntario.getValue().toString();
                }
                String nivel3 = "";
                if (nivel3Voluntario.getValue() != null) {
                    nivel3 = nivel3Voluntario.getValue().toString();
                }
                String informatica = "";
                if (informaticaVoluntario.getValue() != null) {
                    informatica = informaticaVoluntario.getValue().toString();
                }
                String laboral = "";
                if (laboralVoluntario.getValue() != null) {
                    laboral = laboralVoluntario.getValue().toString();
                }
                String zona = "";
                if (zonaVoluntario.getValue() != null) {
                    zona = zonaVoluntario.getValue().toString();
                }
                String equipo = "";
                if (equipoVoluntario.getValue() != null) {
                    equipo = equipoVoluntario.getValue().toString();
                }
                String cargoEquipo = "";
                if (cargoEquipoVoluntario.getValue() != null) {
                    cargoEquipo = cargoEquipoVoluntario.getValue().toString();
                }
                String cargo1 = "";
                if (cargo1Voluntario.getValue() != null) {
                    cargo1 = cargo1Voluntario.getValue().toString();
                }
                String cargo2 = "";
                if (cargo2Voluntario.getValue() != null) {
                    cargo2 = cargo2Voluntario.getValue().toString();
                }
                String programa1 = "";
                if (programa1Voluntario.getValue() != null) {
                    programa1 = programa1Voluntario.getValue().toString();
                }
                String programa2 = "";
                if (programa2Voluntario.getValue() != null) {
                    programa2 = programa2Voluntario.getValue().toString();
                }
                //Comprobar datos CheckBox
                int carnet = 0;
                if (carnetVoluntario.isSelected()) {
                    carnet = 1;
                }
                int consejo = 0;
                if (consejoVoluntario.isSelected()) {
                    consejo = 1;
                }
                int ficha = 0;
                if (fichaVoluntario.isSelected()) {
                    ficha = 1;
                }
                int delitos = 0;
                if (delitosVoluntario.isSelected()) {
                    delitos = 1;
                }
                int publicacion = 0;
                if (publicacionVoluntarios.isSelected()) {
                    publicacion = 1;
                }
                int acuerdo = 0;
                if (acuerdoVoluntario.isSelected()) {
                    acuerdo = 1;
                }
                int antecedentes = 0;
                if (antecedentesVoluntario.isSelected()) {
                    antecedentes = 1;
                }
                int autorizacion = 0;
                if (autorizacionVoluntario.isSelected()) {
                    autorizacion = 1;
                }
                int rgpd = 0;
                if (rgpdVoluntario.isSelected()) {
                    rgpd = 1;
                }
                int enviosEmail = 0;
                if (enviosVoluntario.isSelected()) {
                    enviosEmail = 1;
                }
                int enviosPostales = 0;
                if (postalesVoluntario.isSelected()) {
                    enviosPostales = 1;
                }
                int baja = 0;
                if (bajaVoluntario.isSelected()) {
                    baja = 1;
                }
                // Actualizar campos a la BD
                String sql = "UPDATE voluntario SET dni = ?, nombre = ?, sexo = ?, fecha_nacimiento = ?, telefono_1 = ?, telefono_2 = ?, email = ?, direccion_1 = ?, codigo_postal_1 = ?,poblacion_1 = ?, provincia = ?, nacionalidad = ?, estado_civil = ?,"
                        + "estudios = ?, lugar_estudios = ?, observaciones_formacion = ?, idioma_1 = ?, idioma_2 = ?, idioma_3 = ?, nivel_1 = ?, nivel_2 = ?, nivel_3 = ?, informatica = ?,situacion_laboral = ?, carnet = ?, experiencia_laboral = ?, fecha_alta = ?, zona = ?,"
                        + "poblacion_2 = ?, equipo_1 = ?, direccion_2 = ?, codigo_postal_2 = ?, telefono_3 = ?, cargo_1 = ?, programa_1 = ?, programa_2 = ?, cargo_2 = ?, cargo_3 = ?, observaciones_voluntario = ?, consejo_diocesano = ?,ficha_voluntario = ?, delitos_sexuales = ?,"
                        + "publicacion_imagen = ?, acuerdo_colaboracion = ?, antecedentes_penales = ?, autorizacion_paterna = ?, rgpd = ?, envios_email = ?, envios_postales = ?, observaciones_documentacion = ?, baja_caritas = ?, fecha_baja = ?, tiene_foto = ? "
                        + "WHERE id = " + idVoluntario;
                try {
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, dniVoluntario.getText());
                    pst.setString(2, nombreVoluntario.getText());
                    pst.setString(3, sex);
                    pst.setString(4, fNacimiento);
                    pst.setString(5, telefono1Voluntario.getText());
                    pst.setString(6, telefono2Voluntario.getText());
                    pst.setString(7, emailVoluntario.getText());
                    pst.setString(8, direccion1Voluntario.getText());
                    pst.setString(9, codigo1Voluntario.getText());
                    pst.setString(10, poblacion1);
                    pst.setString(11, provincia);
                    pst.setString(12, nacionalidadVoluntario.getText());
                    pst.setString(13, estado);
                    pst.setString(14, estudios);
                    pst.setString(15, lugarEstudiosVoluntario.getText());
                    pst.setString(16, observaciones1Voluntario.getText());
                    pst.setString(17, idioma1);
                    pst.setString(18, idioma2);
                    pst.setString(19, idioma3);
                    pst.setString(20, nivel1);
                    pst.setString(21, nivel2);
                    pst.setString(22, nivel3);
                    pst.setString(23, informatica);
                    pst.setString(24, laboral);
                    pst.setInt(25, carnet);
                    pst.setString(26, experienciaVoluntario.getText());
                    pst.setString(27, fAlta);
                    pst.setString(28, zona);
                    pst.setString(29, poblacion2);
                    pst.setString(30, equipo);
                    pst.setString(31, direccion2Voluntario.getText());
                    pst.setString(32, codigo2Voluntario.getText());
                    pst.setString(33, telefono3Voluntario.getText());
                    pst.setString(34, cargoEquipo);
                    pst.setString(35, programa1);
                    pst.setString(36, programa2);
                    pst.setString(37, cargo1);
                    pst.setString(38, cargo2);
                    pst.setString(39, observaciones2Voluntario.getText());
                    pst.setInt(40, consejo);
                    pst.setInt(41, ficha);
                    pst.setInt(42, delitos);
                    pst.setInt(43, publicacion);
                    pst.setInt(44, acuerdo);
                    pst.setInt(45, antecedentes);
                    pst.setInt(46, autorizacion);
                    pst.setInt(47, rgpd);
                    pst.setInt(48, enviosEmail);
                    pst.setInt(49, enviosPostales);
                    pst.setString(50, observaciones3Voluntario.getText());
                    pst.setInt(51, baja);
                    pst.setString(52, fBaja);
                    pst.setString(53, tieneFoto);

                    pst.execute();
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Actualizar foto en BD solo si esta es cambiada
                if (cambiaFoto) {
                    String sql2 = "UPDATE voluntario SET foto = ? WHERE id = " + idVoluntario;
                    try {
                        PreparedStatement pst = connection.prepareStatement(sql2);
                        try {
                            fis = new FileInputStream(fileFoto);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        pst.setBinaryStream(1, (InputStream) fis, (int) fileFoto.length());

                        pst.execute();
                        pst.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                // Es voluntario = 1
                if (baja == 0) {
                    String sql2 = "UPDATE entrevistado SET es_voluntario = ? WHERE dni = '" + dniVoluntario.getText() + "'";
                    try {
                        PreparedStatement pst2 = connection.prepareStatement(sql2);
                        pst2.setString(1, "1");
                        pst2.execute();
                        pst2.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    String sql2 = "UPDATE entrevistado SET es_voluntario = ? WHERE dni = '" + dniVoluntario.getText() + "'";
                    try {
                        PreparedStatement pst2 = connection.prepareStatement(sql2);
                        pst2.setString(1, "0");
                        pst2.execute();
                        pst2.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("¿Desea guardar los datos del voluntario?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setHeaderText(null);
                    alert1.setContentText("Cambios guardados con éxito");
                    alert1.showAndWait();
                    Stage stageAux = (Stage) btnGuardarVoluntario.getScene().getWindow();
                    stageAux.close();
                }
            }
        }
    }

    @FXML
    private void agregarZonaPorPoblacion(MouseEvent event) {
        String poblacionAux = "";
        if (poblacion2Voluntario.getValue() != null) {
            poblacionAux = poblacion2Voluntario.getValue().toString();
            listaZona.clear();
            zonaVoluntario.setItems(listaZona);
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            try {
                ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM equipos WHERE poblacion = '" + poblacionAux + "'");
                while (rs.next()) {
                    listaZona.add((rs.getString("zona")));
                }

            } catch (SQLException ex) {
                Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaZona.add("");
        }
        zonaVoluntario.setItems(listaZona);
    }

    @FXML
    private void obtenerEquipoPorPoblacion(MouseEvent event) {
        String poblacionAux = "";
        if (poblacion2Voluntario.getValue() != null) {
            poblacionAux = poblacion2Voluntario.getValue().toString();
            listaEquipos.clear();
            equipoVoluntario.setItems(listaEquipos);
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            try {
                ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM equipos WHERE poblacion = '" + poblacionAux + "'");
                while (rs.next()) {
                    listaEquipos.add((rs.getString("nombre")));
                }

            } catch (SQLException ex) {
                Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaEquipos.add("");
        }
        equipoVoluntario.setItems(listaEquipos);
    }

    @FXML
    private void resetZonaYEquipo(ActionEvent event) {
        zonaVoluntario.setPromptText("");
        zonaVoluntario.setValue("");
        equipoVoluntario.setPromptText("");
        equipoVoluntario.setValue("");
    }
}
