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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import modelo.Entrevistado;
import modelo.Equipo;
import modelo.Parroquia;
import modelo.Voluntario;

/**
 *
 * @author Luis Mendoza
 */
public class ControladorListaInformeParroquias implements Initializable {

    @FXML
    private Button btnCancelarConsulta;
    @FXML
    private Button btnGenerarFicha;

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
            Logger.getLogger(ControladorListaInformeParroquias.class.getName()).log(Level.SEVERE, null, ex);
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
        alert.setContentText("¿Desea cancelar el informe?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarConsulta.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void generarFicha(ActionEvent event) {
        if (tablaParroquias.getSelectionModel().getSelectedItem() != null) {
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
                                                Logger.getLogger(ControladorListaInformeVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ControladorListaCarnetVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    // Empezar a escribir: TÍTULO
                                    PdfContentByte cb3 = writer.getDirectContent();
                                    BaseFont bf3 = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb3.setFontAndSize(bf3, 18);
                                    cb3.beginText();
                                    cb3.setTextMatrix(250, 800);
                                    cb3.setColorFill(new BaseColor(201, 0, 0));
                                    cb3.showText("Informe Parroquial");
                                    cb3.endText();

                                    // Texto
                                    PdfContentByte cb = writer.getDirectContent();
                                    BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb.setFontAndSize(bf, 11);
                                    cb.beginText();

                                    // Fecha
                                    cb.setTextMatrix(490, 810);
                                    Date date = new Date();
                                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    String fecha = dateFormat.format(date);
                                    cb.setColorFill(new BaseColor(0, 0, 0));
                                    cb.showText(fecha);

                                    // Número de página
                                    cb.setTextMatrix(300, 30);
                                    cb.showText("1");

                                    cb.endText();

                                    // Datos
                                    PdfContentByte cb2 = writer.getDirectContent();
                                    BaseFont bf2 = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb2.setColorFill(new BaseColor(201, 0, 0));
                                    cb2.setFontAndSize(bf2, 12);
                                    cb2.beginText();
                                    cb2.setTextMatrix(40, 740);
                                    cb2.showText("NOMBRE DE LA PARROQUIA:");
                                    cb2.setTextMatrix(40, 720);
                                    cb2.showText("PÁRROCO:");
                                    cb2.setTextMatrix(40, 700);
                                    cb2.showText("ZONA:");
                                    cb2.setTextMatrix(40, 680);
                                    cb2.showText("POBLACIÓN:");
                                    cb2.setTextMatrix(40, 660);
                                    cb2.showText("DIRECCIÓN:");
                                    cb2.setTextMatrix(40, 640);
                                    cb2.showText("CÓDIGO POSTAL:");
                                    cb2.setTextMatrix(40, 620);
                                    cb2.showText("PROVINCIA:");
                                    cb2.setTextMatrix(40, 600);
                                    cb2.showText("CARTELES:");
                                    cb2.setTextMatrix(40, 580);
                                    cb2.showText("TRIPTICOS:");
                                    cb2.setTextMatrix(40, 560);
                                    cb2.showText("SOBRES:");
                                    cb2.setTextMatrix(40, 540);
                                    cb2.showText("UNIDADES DIDÁCTICAS:");
                                    cb2.endText();

                                    // Completar campos base de datos
                                    Parroquia parroquia = tablaParroquias.getSelectionModel().getSelectedItem();
                                    PdfContentByte cb4 = writer.getDirectContent();
                                    BaseFont bf4 = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb4.setColorFill(new BaseColor(0, 0, 0));
                                    cb4.setFontAndSize(bf4, 11);
                                    cb4.beginText();
                                    cb4.setTextMatrix(210, 740);
                                    if (parroquia.getNombre() != null) {
                                        cb4.showText(parroquia.getNombre());
                                    }
                                    cb4.setTextMatrix(110, 720);
                                    if (parroquia.getParroco() != null) {
                                        cb4.showText(parroquia.getParroco());
                                    }
                                    cb4.setTextMatrix(80, 700);
                                    if (parroquia.getZona() != null) {
                                        cb4.showText(parroquia.getZona());
                                    }
                                    cb4.setTextMatrix(120, 680);
                                    if (parroquia.getPoblacion() != null) {
                                        cb4.showText(parroquia.getPoblacion());
                                    }
                                    cb4.setTextMatrix(120, 660);
                                    if (parroquia.getDireccion() != null) {
                                        cb4.showText(parroquia.getDireccion());
                                    }
                                    cb4.setTextMatrix(150, 640);
                                    if (parroquia.getCodigo_postal() != null) {
                                        cb4.showText(parroquia.getCodigo_postal());
                                    }
                                    cb4.setTextMatrix(120, 620);
                                    if (parroquia.getProvincia() != null) {
                                        cb4.showText(parroquia.getProvincia());
                                    }
                                    cb4.setTextMatrix(110, 600);
                                    if (parroquia.getCarteles() != null) {
                                        cb4.showText(parroquia.getCarteles());
                                    }
                                    cb4.setTextMatrix(110, 580);
                                    if (parroquia.getTripticos() != null) {
                                        cb4.showText(parroquia.getTripticos());
                                    }
                                    cb4.setTextMatrix(100, 560);
                                    if (parroquia.getSobres() != null) {
                                        cb4.showText(parroquia.getSobres());
                                    }
                                    cb4.setTextMatrix(190, 540);
                                    if (parroquia.getUnidades_didacticas() != null) {
                                        cb4.showText(parroquia.getUnidades_didacticas());
                                    }
                                    cb4.endText();

                                    documento.close();
                                } catch (BadElementException | IOException ex) {
                                    java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } catch (DocumentException ex) {
                                java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setContentText("Informe de la parroquia creado con éxito");
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
            alert.setContentText("Debe de seleccionar una parroquia de la tabla para generar su informe");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

}
