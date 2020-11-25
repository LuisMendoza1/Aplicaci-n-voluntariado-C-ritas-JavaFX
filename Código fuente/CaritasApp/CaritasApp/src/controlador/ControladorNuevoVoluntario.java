/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.ConnectionClass;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorNuevoVoluntario implements Initializable {

    @FXML
    private TextField nombreVoluntario;
    @FXML
    private TextField dniVoluntario;
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
    private Button btnSubirFoto;
    @FXML
    private Button btnEliminarFoto;
    @FXML
    private RadioButton sexoHombre;
    @FXML
    private RadioButton sexoMujer;
    @FXML
    private ImageView fondoFoto;

    final FileChooser fileChooser = new FileChooser();

    private URL rutaFoto;

    private Image imgFoto;

    private File fileFoto;

    private FileInputStream fis;

    private String tieneFoto;

    private String esVoluntario;

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

    //Datos de la incorporación a la vista de Nuevo Voluntario
    public void inicializarDatosPersonales(String dniIncorporacion, String nombreIncorporacion, String sexoIncorporacion, String nacimientoIncorporacion, String tel1Incorporacion,
            String tel2Incorporacion, String emailIncorporacion, String direccionIncorporacion, String codigoIncorporacion, String poblacionIncorporacion,
            String provinciaIncorporacion, String nacionalidadIncorporacion, String estadoIncorporacion, File fotoIncorporacion, String tienefotoIncorporacion) {
        dniVoluntario.setText(dniIncorporacion);
        nombreVoluntario.setText(nombreIncorporacion);
        if (sexoIncorporacion.equals("H")) {
            sexoHombre.setSelected(true);
            sexoMujer.setSelected(false);
        }
        if (sexoIncorporacion.equals("M")) {
            sexoHombre.setSelected(false);
            sexoMujer.setSelected(true);
        }
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date fechaNac = new Date();
        if (nacimientoIncorporacion != "") {
            try {
                fechaNac = format.parse(nacimientoIncorporacion);
                nacimientoVoluntario.setValue(fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            } catch (ParseException ex) {
                Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        telefono1Voluntario.setText(tel1Incorporacion);
        telefono2Voluntario.setText(tel2Incorporacion);
        emailVoluntario.setText(emailIncorporacion);
        direccion1Voluntario.setText(direccionIncorporacion);
        codigo1Voluntario.setText(codigoIncorporacion);
        if (poblacionIncorporacion != null) {
            poblacion1Voluntario.setPromptText(poblacionIncorporacion);
        }
        if (provinciaIncorporacion != null) {
            provinciaVoluntario.setPromptText(provinciaIncorporacion);
        }
        nacionalidadVoluntario.setText(nacionalidadIncorporacion);
        if (estadoIncorporacion != null) {
            estadoVoluntario.setPromptText(estadoIncorporacion);
        }

        String URLimage;
        try {
            URLimage = fotoIncorporacion.toURI().toURL().toString();
            Image imageLoad = new Image(URLimage);
            fondoFoto.setImage(imageLoad);
            fileFoto = fotoIncorporacion;
        } catch (MalformedURLException ex) {
            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
        tieneFoto = "1";
        if (tienefotoIncorporacion.equals("NO")) {
            tieneFoto = "0";
        }
    }

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
            ResultSet rs8 = connection.createStatement().executeQuery("SELECT * FROM cargos");
            ResultSet rs9 = connection.createStatement().executeQuery("SELECT * FROM programas");
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
                listaCargos.add((rs8.getString("cargo")));
            }
            while (rs9.next()) {
                listaProgramas.add((rs9.getString("programa")));
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
        cargoEquipoVoluntario.setItems(listaCargos);
        cargo1Voluntario.setItems(listaCargos);
        cargo2Voluntario.setItems(listaCargos);
        programa1Voluntario.setItems(listaProgramas);
        programa2Voluntario.setItems(listaProgramas);

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
    }

    public VBox crearPagina(int pageIndex) {
        VBox pageBox = new VBox();
        return pageBox;
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
                Image imageLoad = new Image(URLimage);
                fondoFoto.setImage(imageLoad);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        tieneFoto = "0";
    }

    @FXML
    private void cancelarVoluntario(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar el alta del voluntario/a?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarVoluntario.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void guardarVoluntario(ActionEvent event) {
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

        // Si existe el DNI no insertar
        if (vecesDni > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("El voluntario/a ya existe");
            alert.showAndWait();
        } else {
            // Insertar campos a la base de datos
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
                if (poblacion1Voluntario.getPromptText() != null) {
                    poblacion1 = poblacion1Voluntario.getPromptText().toString();
                }
                if (poblacion1Voluntario.getValue() != null) {
                    poblacion1 = poblacion1Voluntario.getValue().toString();
                }
                String poblacion2 = "";
                if (poblacion2Voluntario.getValue() != null) {
                    poblacion2 = poblacion2Voluntario.getValue().toString();
                }
                String provincia = "";
                if (provinciaVoluntario.getPromptText() != null) {
                    provincia = provinciaVoluntario.getPromptText().toString();
                }
                if (provinciaVoluntario.getValue() != null) {
                    provincia = provinciaVoluntario.getValue().toString();
                }
                String estado = "";
                if (estadoVoluntario.getPromptText() != null) {
                    estado = estadoVoluntario.getPromptText().toString();
                }
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
                if (baja == 0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("¿Desea crear el/la voluntario/a nuevo?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        try {
                            // Insertar campos a la BD
                            String sql = "INSERT IGNORE INTO Voluntario (dni,nombre,sexo,fecha_nacimiento,telefono_1,telefono_2,email,direccion_1,codigo_postal_1,poblacion_1,provincia,nacionalidad,estado_civil,"
                                    + "estudios,lugar_estudios,observaciones_formacion,idioma_1,idioma_2,idioma_3,nivel_1,nivel_2,nivel_3,informatica,situacion_laboral,carnet,experiencia_laboral,fecha_alta,zona,"
                                    + "poblacion_2,equipo_1,direccion_2,codigo_postal_2,telefono_3,cargo_1,programa_1,programa_2,cargo_2,cargo_3,observaciones_voluntario,consejo_diocesano,ficha_voluntario,delitos_sexuales,"
                                    + "publicacion_imagen,acuerdo_colaboracion,antecedentes_penales,autorizacion_paterna,rgpd,envios_email,envios_postales,observaciones_documentacion,baja_caritas,fecha_baja,foto,tiene_foto) "
                                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                            PreparedStatement pst = connection.prepareStatement(sql);
                            try {
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
                                try {
                                    fis = new FileInputStream(fileFoto);
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                pst.setBinaryStream(53, (InputStream) fis, (int) fileFoto.length());
                                pst.setString(54, tieneFoto);
                                pst.execute();
                                pst.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setHeaderText(null);
                            alert2.setContentText("Voluntario/a creado con éxito");
                            alert2.showAndWait();
                        } catch (SQLException ex) {
                            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String sql2 = "UPDATE entrevistado SET es_voluntario = ? WHERE dni = '" + dniVoluntario.getText() + "'";
                        try {
                            PreparedStatement pst2 = connection.prepareStatement(sql2);
                            pst2.setString(1, "1");
                            pst2.execute();
                            pst2.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        Stage stage = (Stage) btnGuardarVoluntario.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void obtenerZonaPorPoblacion(MouseEvent event) {
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
