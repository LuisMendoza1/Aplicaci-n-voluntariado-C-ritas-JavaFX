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
import modelo.Entrevistado;
import modelo.Voluntario;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorListaInformeEntrevistados implements Initializable {

    @FXML
    private Button btnCrearInformeEntrevistado;
    @FXML
    private Button btnCancelarInforme;
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
    @FXML
    private TextField search;

    ObservableList<Entrevistado> listaEntrevistados = FXCollections.observableArrayList();

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
            Logger.getLogger(ControladorConsultaEntrevistados.class.getName()).log(Level.SEVERE, null, ex);
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
    private void cancelarInforme(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar el informe?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarInforme.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void generarFicha(ActionEvent event) {
        if (tablaEntrevistados.getSelectionModel().getSelectedItem() != null) {
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
                                                Logger.getLogger(ControladorListaInformeEntrevistados.class.getName()).log(Level.SEVERE, null, ex);
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
                                    cb3.setTextMatrix(210, 800);
                                    cb3.setColorFill(new BaseColor(201, 0, 0));
                                    cb3.showText("Informe del Entrevistado");
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
                                    cb4.showText("Código Postal:");
                                    cb4.setTextMatrix(40, 530);
                                    cb4.showText("Fecha de Entrevista:");
                                    cb4.setTextMatrix(40, 500);
                                    cb4.showText("Teléfono 1:");
                                    cb4.setTextMatrix(40, 470);
                                    cb4.showText("Teléfono 2:");
                                    cb4.setTextMatrix(40, 440);
                                    cb4.showText("Es voluntario:");
                                    cb4.setTextMatrix(40, 410);
                                    cb4.showText("Dirección:");
                                    cb4.setTextMatrix(40, 380);
                                    cb4.showText("Población:");
                                    cb4.setTextMatrix(40, 350);
                                    cb4.showText("Provincia:");
                                    cb4.setTextMatrix(40, 320);
                                    cb4.showText("Nacionalidad:");
                                    cb4.setTextMatrix(40, 290);
                                    cb4.showText("Estado Civil:");
                                    cb4.endText();

                                    // Completar campos base de datos
                                    Entrevistado entrevistado = tablaEntrevistados.getSelectionModel().getSelectedItem();
                                    PdfContentByte cb5 = writer.getDirectContent();
                                    BaseFont bf5 = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb5.setColorFill(new BaseColor(0, 0, 0));
                                    cb5.setFontAndSize(bf5, 11);
                                    cb5.beginText();
                                    cb5.setTextMatrix(210, 710);
                                    if (entrevistado.getDni() != null) {
                                        cb5.showText(entrevistado.getDni());
                                    }
                                    cb5.setTextMatrix(200, 680);
                                    if (entrevistado.getNombre() != null) {
                                        cb5.showText(entrevistado.getNombre());
                                    }
                                    cb5.setTextMatrix(265, 650);
                                    if (entrevistado.getFecha_nacimiento() != null) {
                                        cb5.showText(entrevistado.getFecha_nacimiento());
                                    }
                                    cb5.setTextMatrix(80, 620);
                                    if (entrevistado.getSexo() != null) {
                                        cb5.showText(entrevistado.getSexo());
                                    }
                                    cb5.setTextMatrix(80, 590);
                                    if (entrevistado.getEmail() != null) {
                                        cb5.showText(entrevistado.getEmail());
                                    }
                                    cb5.setTextMatrix(125, 560);
                                    if (entrevistado.getCodigo_postal() != null) {
                                        cb5.showText(entrevistado.getCodigo_postal());
                                    }
                                    cb5.setTextMatrix(150, 530);
                                    if (entrevistado.getFecha_entrevista() != null) {
                                        cb5.showText(entrevistado.getFecha_entrevista());
                                    }
                                    cb5.setTextMatrix(100, 500);
                                    if (entrevistado.getTelefono1() != null) {
                                        cb5.showText(entrevistado.getTelefono1());
                                    }
                                    cb5.setTextMatrix(100, 470);
                                    if (entrevistado.getTelefono2() != null) {
                                        cb5.showText(entrevistado.getTelefono2());
                                    }
                                    cb5.setTextMatrix(120, 440);
                                    if (entrevistado.getEs_voluntario() != null) {
                                        cb5.showText(entrevistado.getEs_voluntario());
                                    }
                                    cb5.setTextMatrix(110, 410);
                                    if (entrevistado.getDireccion() != null) {
                                        cb5.showText(entrevistado.getDireccion());
                                    }
                                    cb5.setTextMatrix(110, 380);
                                    if (entrevistado.getPoblacion() != null) {
                                        cb5.showText(entrevistado.getPoblacion());
                                    }
                                    cb5.setTextMatrix(110, 350);
                                    if (entrevistado.getProvincia() != null) {
                                        cb5.showText(entrevistado.getProvincia());
                                    }
                                    cb5.setTextMatrix(125, 320);
                                    if (entrevistado.getNacionalidad() != null) {
                                        cb5.showText(entrevistado.getNacionalidad());
                                    }
                                    cb5.setTextMatrix(120, 290);
                                    if (entrevistado.getEstado_civil() != null) {
                                        cb5.showText(entrevistado.getEstado_civil());
                                    }

                                    cb5.endText();

                                    // Foto Voluntario
                                    com.itextpdf.text.Image foto;
                                    try {
                                        byte[] imageByte = entrevistado.getFoto().getBytes(1, (int) entrevistado.getFoto().length());
                                        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByte));
                                        foto = com.itextpdf.text.Image.getInstance(bufferedImage, null);
                                        Rectangle rectangle = new Rectangle(90, 90);
                                        foto.scaleToFit(rectangle);
                                        foto.setAbsolutePosition(40f, 640f);
                                        documento.add(foto);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ControladorListaInformeEntrevistados.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    documento.close();

                                } catch (BadElementException | IOException ex) {
                                    java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } catch (DocumentException ex) {
                                java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setContentText("Informe de entrevistado creado con éxito");
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
            alert.setContentText("Debe de seleccionar un/a entrevistado/a de la tabla para generar su informe");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
}
