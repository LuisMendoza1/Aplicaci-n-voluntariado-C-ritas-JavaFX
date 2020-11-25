/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
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
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import modelo.Voluntario;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorListaInformeBajas implements Initializable {

    @FXML
    private Button btnCancelarConsulta;
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
    private Button btnGenerarFicha;
    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM voluntario WHERE baja_caritas = '1'");
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
            Logger.getLogger(ControladorListaInformeBajas.class.getName()).log(Level.SEVERE, null, ex);
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
    private void cancelarConsulta(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar el informe?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarConsulta.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void generarFicha(ActionEvent event) {
        if (tablaVoluntarios.getSelectionModel().getSelectedItem() != null) {
            Document documento = new Document();
            String ruta = "";
            try {
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                f.setDialogTitle("Seleccione la ruta donde guardar el fichero");
                f.showSaveDialog(null);
                if (f.getSelectedFile() != null) {
                    ruta = f.getSelectedFile().getPath();
                    StringBuilder rutaFichero = new StringBuilder(ruta);
                    for (int i = 0; i < rutaFichero.length(); i++) {
                        if (rutaFichero.charAt(i) == '\\') {
                            rutaFichero.setCharAt(i, '/');
                        }
                    }
                    TextInputDialog dialogoTextual = new TextInputDialog();
                    dialogoTextual.setTitle("Entrada de texto");
                    dialogoTextual.setHeaderText("Ingresa el nombre del fichero:");
                    dialogoTextual.initStyle(StageStyle.UTILITY);
                    Optional<String> respuesta = dialogoTextual.showAndWait();
                    if (respuesta.isPresent()) {
                        ruta = rutaFichero.toString() + "/" + respuesta.get() + ".pdf";
                        File file = new File(ruta);
                        if (!file.exists()) {
                            FileOutputStream fichero = new FileOutputStream(ruta);
                            try {
                                PdfWriter writer = PdfWriter.getInstance(documento, fichero);
                                documento.open();
                                try {
                                    String rut = new File(".").getAbsolutePath();
                                    StringBuilder rutaFichero1 = new StringBuilder(rut);
                                    for (int i = 0; i < rutaFichero1.length(); i++) {
                                        if (rutaFichero1.charAt(i) == '\\') {
                                            rutaFichero1.setCharAt(i, '/');
                                        }
                                    }
                                    // Imagen Cáritas
                                    ConnectionClass connectionClass = new ConnectionClass();
                                    Connection connection = connectionClass.getConnection();
                                    ResultSet rs;
                                    try {
                                        rs = connection.createStatement().executeQuery("SELECT * FROM imagenes WHERE nombre = 'imageCaritas'");
                                        while (rs.next()) {
                                            com.itextpdf.text.Image imagenCaritas;
                                            try {
                                                byte[] imageByte = rs.getBlob("imagen").getBytes(1, (int) rs.getBlob("imagen").length());
                                                BufferedImage bufferedImage2 = ImageIO.read(new ByteArrayInputStream(imageByte));
                                                imagenCaritas = com.itextpdf.text.Image.getInstance(bufferedImage2, null);
                                                imagenCaritas.scalePercent(8f);
                                                imagenCaritas.setAbsolutePosition(30f, 750f);
                                                documento.add(imagenCaritas);
                                            } catch (SQLException ex) {
                                                Logger.getLogger(ControladorListaInformeBajas.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ControladorListaCarnetVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    // Página 1
                                    // Empezar a escribir: TÍTULO
                                    PdfContentByte cb3 = writer.getDirectContent();
                                    BaseFont bf3 = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb3.setFontAndSize(bf3, 18);
                                    cb3.beginText();
                                    cb3.setTextMatrix(230, 800);
                                    cb3.setColorFill(new BaseColor(201, 0, 0));
                                    cb3.showText("Informe de la Baja");
                                    cb3.endText();

                                    // Texto
                                    PdfContentByte cb = writer.getDirectContent();
                                    BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb.setFontAndSize(bf, 11);
                                    cb.setColorFill(new BaseColor(0, 0, 0));
                                    cb.beginText();
                                    // Fecha
                                    cb.setTextMatrix(490, 810);
                                    Date date = new Date();
                                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    String fecha = dateFormat.format(date);
                                    cb.showText(fecha);
                                    // Número de página
                                    cb.setTextMatrix(300, 30);
                                    cb.showText("1");
                                    cb.endText();

                                    // Datos
                                    PdfContentByte cb4 = writer.getDirectContent();
                                    BaseFont bf4 = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb4.setColorFill(new BaseColor(201, 0, 0));
                                    cb4.setFontAndSize(bf4, 12);
                                    cb4.beginText();
                                    cb4.setTextMatrix(150, 710);
                                    cb4.showText("DNI/NIF:");
                                    cb4.setTextMatrix(150, 680);
                                    cb4.showText("Nombre:");
                                    cb4.setTextMatrix(150, 650);
                                    cb4.showText("Fecha de nacimiento:");
                                    cb4.setTextMatrix(40, 620);
                                    cb4.showText("Sexo:");
                                    cb4.setTextMatrix(40, 590);
                                    cb4.showText("Email:");
                                    cb4.setTextMatrix(40, 560);
                                    cb4.showText("Código Postal 1:");
                                    cb4.setTextMatrix(40, 530);
                                    cb4.showText("Código Postal 2:");
                                    cb4.setTextMatrix(40, 500);
                                    cb4.showText("Teléfono 1:");
                                    cb4.setTextMatrix(40, 470);
                                    cb4.showText("Teléfono 2:");
                                    cb4.setTextMatrix(40, 440);
                                    cb4.showText("Teléfono 3:");
                                    cb4.setTextMatrix(40, 410);
                                    cb4.showText("Dirección 1:");
                                    cb4.setTextMatrix(40, 380);
                                    cb4.showText("Dirección 2:");
                                    cb4.setTextMatrix(40, 350);
                                    cb4.showText("Población 1:");
                                    cb4.setTextMatrix(40, 320);
                                    cb4.showText("Población 2:");
                                    cb4.setTextMatrix(40, 290);
                                    cb4.showText("Nacionalidad:");
                                    cb4.setTextMatrix(40, 260);
                                    cb4.showText("Estado Civil:");
                                    cb4.setTextMatrix(40, 230);
                                    cb4.showText("Provincia:");
                                    cb4.setTextMatrix(40, 200);
                                    cb4.showText("Estudios:");
                                    cb4.setTextMatrix(40, 170);
                                    cb4.showText("Especialidad:");
                                    cb4.setTextMatrix(300, 620);
                                    cb4.showText("Idioma 1:");
                                    cb4.setTextMatrix(300, 590);
                                    cb4.showText("Idioma 2:");
                                    cb4.setTextMatrix(300, 560);
                                    cb4.showText("Idioma 3:");
                                    cb4.setTextMatrix(420, 620);
                                    cb4.showText("Nivel 1:");
                                    cb4.setTextMatrix(420, 590);
                                    cb4.showText("Nivel 2:");
                                    cb4.setTextMatrix(420, 560);
                                    cb4.showText("Nivel 3:");
                                    cb4.setTextMatrix(300, 530);
                                    cb4.showText("Informática:");
                                    cb4.setTextMatrix(300, 500);
                                    cb4.showText("Situación laboral:");
                                    cb4.setTextMatrix(300, 470);
                                    cb4.showText("Carnet:");
                                    cb4.setTextMatrix(300, 440);
                                    cb4.showText("Fecha de alta:");
                                    cb4.setTextMatrix(300, 410);
                                    cb4.showText("Zona:");
                                    cb4.setTextMatrix(300, 380);
                                    cb4.showText("Equipo:");
                                    cb4.setTextMatrix(300, 350);
                                    cb4.showText("Programa 1:");
                                    cb4.setTextMatrix(300, 320);
                                    cb4.showText("Programa 2:");
                                    cb4.setTextMatrix(300, 290);
                                    cb4.showText("Cargo 1:");
                                    cb4.setTextMatrix(300, 260);
                                    cb4.showText("Cargo 2:");
                                    cb4.setTextMatrix(300, 230);
                                    cb4.showText("Cargo 3:");
                                    cb4.endText();

                                    // Completar campos base de datos
                                    Voluntario voluntario = tablaVoluntarios.getSelectionModel().getSelectedItem();
                                    PdfContentByte cb5 = writer.getDirectContent();
                                    BaseFont bf5 = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb5.setColorFill(new BaseColor(0, 0, 0));
                                    cb5.setFontAndSize(bf5, 11);
                                    cb5.beginText();
                                    cb5.setTextMatrix(210, 710);
                                    if (voluntario.getDni() != null) {
                                        cb5.showText(voluntario.getDni());
                                    }
                                    cb5.setTextMatrix(200, 680);
                                    if (voluntario.getNombre() != null) {
                                        cb5.showText(voluntario.getNombre());
                                    }
                                    cb5.setTextMatrix(265, 650);
                                    if (voluntario.getFecha_nacimiento() != null) {
                                        cb5.showText(voluntario.getFecha_nacimiento());
                                    }
                                    cb5.setTextMatrix(80, 620);
                                    if (voluntario.getSexo() != null) {
                                        cb5.showText(voluntario.getSexo());
                                    }
                                    cb5.setTextMatrix(80, 590);
                                    if (voluntario.getEmail() != null) {
                                        cb5.showText(voluntario.getEmail());
                                    }
                                    cb5.setTextMatrix(125, 560);
                                    if (voluntario.getCodigo_postal_1() != null) {
                                        cb5.showText(voluntario.getCodigo_postal_1());
                                    }
                                    cb5.setTextMatrix(125, 530);
                                    if (voluntario.getCodigo_postal_2() != null) {
                                        cb5.showText(voluntario.getCodigo_postal_2());
                                    }
                                    cb5.setTextMatrix(100, 500);
                                    if (voluntario.getTelefono_1() != null) {
                                        cb5.showText(voluntario.getTelefono_1());
                                    }
                                    cb5.setTextMatrix(100, 470);
                                    if (voluntario.getTelefono_2() != null) {
                                        cb5.showText(voluntario.getTelefono_2());
                                    }
                                    cb5.setTextMatrix(100, 440);
                                    if (voluntario.getTelefono_3() != null) {
                                        cb5.showText(voluntario.getTelefono_3());
                                    }
                                    cb5.setTextMatrix(110, 410);
                                    if (voluntario.getDireccion_1() != null) {
                                        cb5.showText(voluntario.getDireccion_1());
                                    }
                                    cb5.setTextMatrix(110, 380);
                                    if (voluntario.getDireccion_2() != null) {
                                        cb5.showText(voluntario.getDireccion_2());
                                    }
                                    cb5.setTextMatrix(110, 350);
                                    if (voluntario.getPoblacion_1() != null) {
                                        cb5.showText(voluntario.getPoblacion_1());
                                    }
                                    cb5.setTextMatrix(110, 320);
                                    if (voluntario.getPoblacion_2() != null) {
                                        cb5.showText(voluntario.getPoblacion_2());
                                    }
                                    cb5.setTextMatrix(120, 290);
                                    if (voluntario.getNacionalidad() != null) {
                                        cb5.showText(voluntario.getNacionalidad());
                                    }
                                    cb5.setTextMatrix(110, 260);
                                    if (voluntario.getEstado_civil() != null) {
                                        cb5.showText(voluntario.getEstado_civil());
                                    }
                                    cb5.setTextMatrix(100, 230);
                                    if (voluntario.getProvincia() != null) {
                                        cb5.showText(voluntario.getProvincia());
                                    }
                                    cb5.setTextMatrix(90, 200);
                                    if (voluntario.getEstudios() != null) {
                                        cb5.showText(voluntario.getEstudios());
                                    }
                                    cb5.setTextMatrix(110, 170);
                                    if (voluntario.getLugar_estudios() != null) {
                                        cb5.showText(voluntario.getLugar_estudios());
                                    }
                                    cb5.setTextMatrix(350, 620);
                                    if (voluntario.getIdioma_1() != null) {
                                        cb5.showText(voluntario.getIdioma_1());
                                    }
                                    cb5.setTextMatrix(350, 590);
                                    if (voluntario.getIdioma_2() != null) {
                                        cb5.showText(voluntario.getIdioma_2());
                                    }
                                    cb5.setTextMatrix(350, 560);
                                    if (voluntario.getIdioma_3() != null) {
                                        cb5.showText(voluntario.getIdioma_3());
                                    }
                                    cb5.setTextMatrix(465, 620);
                                    if (voluntario.getNivel_1() != null) {
                                        cb5.showText(voluntario.getNivel_1());
                                    }
                                    cb5.setTextMatrix(465, 590);
                                    if (voluntario.getNivel_2() != null) {
                                        cb5.showText(voluntario.getNivel_2());
                                    }
                                    cb5.setTextMatrix(465, 560);
                                    if (voluntario.getNivel_3() != null) {
                                        cb5.showText(voluntario.getNivel_3());
                                    }
                                    cb5.setTextMatrix(370, 530);
                                    if (voluntario.getInformatica() != null) {
                                        cb5.showText(voluntario.getInformatica());
                                    }
                                    cb5.setTextMatrix(390, 500);
                                    if (voluntario.getSituacion_laboral() != null) {
                                        cb5.showText(voluntario.getSituacion_laboral());
                                    }
                                    cb5.setTextMatrix(350, 470);
                                    if (voluntario.getCarnet() != null) {
                                        cb5.showText(voluntario.getCarnet());
                                    }
                                    cb5.setTextMatrix(375, 440);
                                    if (voluntario.getFecha_alta() != null) {
                                        cb5.showText(voluntario.getFecha_alta());
                                    }
                                    cb5.setTextMatrix(340, 410);
                                    if (voluntario.getZona() != null) {
                                        cb5.showText(voluntario.getZona());
                                    }
                                    cb5.setTextMatrix(350, 380);
                                    if (voluntario.getEquipo_1() != null) {
                                        cb5.showText(voluntario.getEquipo_1());
                                    }
                                    cb5.setTextMatrix(370, 350);
                                    if (voluntario.getPrograma_1() != null) {
                                        cb5.showText(voluntario.getPrograma_1());
                                    }
                                    cb5.setTextMatrix(370, 320);
                                    if (voluntario.getPrograma_2() != null) {
                                        cb5.showText(voluntario.getPrograma_2());
                                    }
                                    cb5.setTextMatrix(350, 290);
                                    if (voluntario.getCargo_1() != null) {
                                        cb5.showText(voluntario.getCargo_1());
                                    }
                                    cb5.setTextMatrix(350, 260);
                                    if (voluntario.getCargo_2() != null) {
                                        cb5.showText(voluntario.getCargo_2());
                                    }
                                    cb5.setTextMatrix(350, 230);
                                    if (voluntario.getCargo_3() != null) {
                                        cb5.showText(voluntario.getCargo_3());
                                    }

                                    cb5.endText();

                                    // Foto Voluntario
                                    com.itextpdf.text.Image foto;
                                    try {
                                        byte[] imageByte = voluntario.getFoto().getBytes(1, (int) voluntario.getFoto().length());
                                        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByte));
                                        foto = com.itextpdf.text.Image.getInstance(bufferedImage, null);
                                        Rectangle rectangle = new Rectangle(90, 90);
                                        foto.scaleToFit(rectangle);
                                        foto.setAbsolutePosition(40f, 640f);
                                        documento.add(foto);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ControladorListaInformeBajas.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    // Página 2
                                    documento.newPage();
                                    // Imagen Cáritas
                                    try {
                                        rs = connection.createStatement().executeQuery("SELECT * FROM imagenes WHERE nombre = 'imageCaritas'");
                                        while (rs.next()) {
                                            com.itextpdf.text.Image imagenCaritas;
                                            try {
                                                byte[] imageByte = rs.getBlob("imagen").getBytes(1, (int) rs.getBlob("imagen").length());
                                                BufferedImage bufferedImage2 = ImageIO.read(new ByteArrayInputStream(imageByte));
                                                imagenCaritas = com.itextpdf.text.Image.getInstance(bufferedImage2, null);
                                                imagenCaritas.scalePercent(8f);
                                                imagenCaritas.setAbsolutePosition(30f, 750f);
                                                documento.add(imagenCaritas);
                                            } catch (SQLException ex) {
                                                Logger.getLogger(ControladorListaInformeBajas.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ControladorListaCarnetVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    // Datos

                                    PdfContentByte cb7 = writer.getDirectContent();
                                    BaseFont bf7 = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb7.setColorFill(new BaseColor(201, 0, 0));
                                    cb7.setFontAndSize(bf7, 12);
                                    cb7.beginText();
                                    cb7.setTextMatrix(40, 730);
                                    cb7.showText("Ficha voluntariado:");
                                    cb7.setTextMatrix(40, 700);
                                    cb7.showText("Acuerdo colaboración:");
                                    cb7.setTextMatrix(40, 670);
                                    cb7.showText("Delitos sexuales:");
                                    cb7.setTextMatrix(40, 640);
                                    cb7.showText("Antecedentes penales:");
                                    cb7.setTextMatrix(40, 610);
                                    cb7.showText("RGPD:");
                                    cb7.setTextMatrix(40, 580);
                                    cb7.showText("Publicación de imagen:");
                                    cb7.setTextMatrix(40, 550);
                                    cb7.showText("Autorización paterna:");
                                    cb7.setTextMatrix(40, 520);
                                    cb7.showText("Envíos email:");
                                    cb7.setTextMatrix(40, 490);
                                    cb7.showText("Envíos postales:");
                                    cb7.setTextMatrix(40, 460);
                                    cb7.showText("Consejo diocesano:");
                                    cb7.endText();

                                    // Texto
                                    PdfContentByte cb6 = writer.getDirectContent();
                                    BaseFont bf6 = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb6.setFontAndSize(bf6, 11);
                                    cb6.setColorFill(new BaseColor(0, 0, 0));
                                    cb6.beginText();
                                    // Datos
                                    cb7.setTextMatrix(145, 730);
                                    if (voluntario.getFicha_voluntario() != null) {
                                        cb7.showText(voluntario.getFicha_voluntario());
                                    }
                                    cb7.setTextMatrix(160, 700);
                                    if (voluntario.getAcuerdo_colaboracion() != null) {
                                        cb7.showText(voluntario.getAcuerdo_colaboracion());
                                    }
                                    cb7.setTextMatrix(130, 670);
                                    if (voluntario.getDelitos_sexuales() != null) {
                                        cb7.showText(voluntario.getDelitos_sexuales());
                                    }
                                    cb7.setTextMatrix(155, 640);
                                    if (voluntario.getAntecedentes_penales() != null) {
                                        cb7.showText(voluntario.getAntecedentes_penales());
                                    }
                                    cb7.setTextMatrix(80, 610);
                                    if (voluntario.getRgpd() != null) {
                                        cb7.showText(voluntario.getRgpd());
                                    }
                                    cb7.setTextMatrix(165, 580);
                                    if (voluntario.getPublicacion_imagen() != null) {
                                        cb7.showText(voluntario.getPublicacion_imagen());
                                    }
                                    cb7.setTextMatrix(155, 550);
                                    if (voluntario.getAutorizacion_paterna() != null) {
                                        cb7.showText(voluntario.getAutorizacion_paterna());
                                    }
                                    cb7.setTextMatrix(115, 520);
                                    if (voluntario.getEnvios_email() != null) {
                                        cb7.showText(voluntario.getEnvios_email());
                                    }
                                    cb7.setTextMatrix(125, 490);
                                    if (voluntario.getEnvios_postales() != null) {
                                        cb7.showText(voluntario.getEnvios_postales());
                                    }
                                    cb7.setTextMatrix(140, 460);
                                    if (voluntario.getConsejo_diocesano() != null) {
                                        cb7.showText(voluntario.getConsejo_diocesano());
                                    }
                                    // Fecha
                                    cb6.setTextMatrix(490, 810);
                                    cb6.showText(fecha);
                                    // Número de página
                                    cb6.setTextMatrix(300, 30);
                                    cb6.showText("2");
                                    cb6.endText();

                                    documento.close();

                                } catch (BadElementException | IOException ex) {
                                    java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } catch (DocumentException ex) {
                                java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setContentText("Informe de la baja creado con éxito");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setContentText("El nombre del fichero ya existe");
                            alert.showAndWait();
                        }
                    }
                }

            } catch (FileNotFoundException ex) {
                java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Debe de seleccionar un/a voluntario/a de baja de la tabla para generar su informe");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
}
