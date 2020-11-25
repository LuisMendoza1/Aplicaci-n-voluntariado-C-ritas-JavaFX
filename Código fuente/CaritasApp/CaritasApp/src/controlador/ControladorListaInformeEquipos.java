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
import java.util.LinkedList;
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
public class ControladorListaInformeEquipos implements Initializable {

    @FXML
    private Button btnCancelarConsulta;
    @FXML
    private Button btnGenerarFicha;

    ObservableList<Equipo> listaEquipos = FXCollections.observableArrayList();

    ObservableList<Voluntario> listaVoluntarios = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Equipo, String> colZona;
    @FXML
    private TableColumn<Equipo, String> colPoblacion;
    @FXML
    private TableColumn<Equipo, String> colDireccion;
    @FXML
    private TableColumn<Equipo, String> colCodigo;
    @FXML
    private TableColumn<Equipo, String> colNombre;
    @FXML
    private TableColumn<Equipo, String> colProvincia;
    @FXML
    private TableColumn<Equipo, String> colTelefono;
    @FXML
    private TableView<Equipo> tablaEquipos;
    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM equipos");
            while (rs.next()) {
                listaEquipos.add(new Equipo(rs.getInt("id"), rs.getString("nombre"), rs.getString("zona"), rs.getString("poblacion"),
                        rs.getString("direccion"), rs.getString("codigo_postal"), rs.getString("telefono"), rs.getString("provincia")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorListaInformeEquipos.class.getName()).log(Level.SEVERE, null, ex);
        }
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colZona.setCellValueFactory(new PropertyValueFactory<>("zona"));
        colPoblacion.setCellValueFactory(new PropertyValueFactory<>("poblacion"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo_postal"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));

        FilteredList<Equipo> listaEquiposFiltrada = new FilteredList<>(listaEquipos, e -> true);
        tablaEquipos.setItems(listaEquiposFiltrada);
        search.textProperty().addListener((prop, old, text) -> {
            listaEquiposFiltrada.setPredicate(equipo -> {
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
        alert.setContentText("¿Desea cancelar el informe?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarConsulta.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void generarFicha(ActionEvent event) {
        int numPag = 1;
        if (tablaEquipos.getSelectionModel().getSelectedItem() != null) {
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
                                    cb3.setTextMatrix(200, 800);
                                    cb3.setColorFill(new BaseColor(201, 0, 0));
                                    cb3.showText("Equipo de Cáritas Parroquial");
                                    cb3.endText();

                                    // Fecha
                                    PdfContentByte cb = writer.getDirectContent();
                                    BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb.setColorFill(new BaseColor(0, 0, 0));
                                    cb.setFontAndSize(bf, 11);
                                    cb.beginText();
                                    cb.setTextMatrix(490, 810);
                                    Date date = new Date();
                                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    String fecha = dateFormat.format(date);
                                    cb.showText(fecha);
                                    // Número de página
                                    cb.setTextMatrix(300, 30);
                                    cb.showText(Integer.toString(numPag));
                                    cb.endText();

                                    // Datos
                                    PdfContentByte cb9 = writer.getDirectContent();
                                    BaseFont bf9 = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb9.setColorFill(new BaseColor(201, 0, 0));
                                    cb9.setFontAndSize(bf9, 12);
                                    cb9.beginText();
                                    cb9.setTextMatrix(250, 740);
                                    cb9.showText("EQUIPO:");
                                    cb9.setTextMatrix(40, 740);
                                    cb9.showText("POBLACIÓN:");
                                    cb9.setTextMatrix(40, 720);
                                    cb9.showText("DIRECCIÓN:");
                                    cb9.setTextMatrix(40, 700);
                                    cb9.showText("C.P.:");
                                    cb9.setTextMatrix(220, 700);
                                    cb9.showText("TFNO:");
                                    cb9.endText();

                                    // Completar campos base de datos
                                    PdfContentByte cb11 = writer.getDirectContent();
                                    BaseFont bf11 = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb11.setColorFill(new BaseColor(0, 0, 0));
                                    cb11.setFontAndSize(bf11, 11);
                                    cb11.beginText();
                                    Equipo equipo = tablaEquipos.getSelectionModel().getSelectedItem();
                                    cb11.setTextMatrix(310, 740);
                                    if (equipo.getNombre() != null) {
                                        cb11.showText(equipo.getNombre());
                                    }
                                    cb11.setTextMatrix(120, 740);
                                    if (equipo.getPoblacion() != null) {
                                        cb11.showText(equipo.getPoblacion());
                                    }
                                    cb11.setTextMatrix(120, 720);
                                    if (equipo.getDireccion() != null) {
                                        cb11.showText(equipo.getDireccion());
                                    }
                                    cb11.setTextMatrix(80, 700);
                                    if (equipo.getCodigo_postal() != null) {
                                        cb11.showText(equipo.getCodigo_postal());
                                    }
                                    cb11.setTextMatrix(260, 700);
                                    if (equipo.getTelefono() != null) {
                                        cb11.showText(equipo.getTelefono());
                                    }
                                    cb11.endText();

                                    // Empezar a escribir: VOLUNTARIOS
                                    PdfContentByte cb5 = writer.getDirectContent();
                                    BaseFont bf5 = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                    cb5.setFontAndSize(bf5, 12);
                                    cb5.setColorFill(new BaseColor(201, 0, 0));
                                    cb5.beginText();
                                    cb5.setTextMatrix(40, 670);
                                    cb5.showText("CARGO");
                                    cb5.setTextMatrix(120, 670);
                                    cb5.showText("NOMBRE");
                                    cb5.setTextMatrix(300, 670);
                                    cb5.showText("DIRECCIÓN");
                                    cb5.setTextMatrix(480, 670);
                                    cb5.showText("TELÉFONOS");
                                    cb5.setTextMatrix(40, 665);
                                    cb5.showText("_____________________________________________________________________________________");
                                    cb5.setTextMatrix(40, 664);
                                    cb5.showText("_____________________________________________________________________________________");
                                    cb5.endText();

                                    try {
                                        rs = connection.createStatement().executeQuery("SELECT * FROM voluntario WHERE equipo_1 = " + "'" + tablaEquipos.getSelectionModel().getSelectedItem().getNombre() + "'");
                                        while (rs.next()) {
                                            listaVoluntarios.add(new Voluntario(rs.getString("nombre"), rs.getString("telefono_1"), rs.getString("telefono_2"), rs.getString("email"),
                                                    rs.getString("direccion_1"), rs.getString("codigo_postal_1"), rs.getString("cargo_1")));
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ControladorListaInformeEquipos.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    PdfContentByte cb6 = writer.getDirectContent();

                                    int sizeVertical = 644;
                                    if (!listaVoluntarios.isEmpty()) {
                                        for (Voluntario v : listaVoluntarios) {
                                            BaseFont bf6 = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                            cb6.setColorFill(new BaseColor(0, 0, 0));
                                            cb6.setFontAndSize(bf6, 9);
                                            if (sizeVertical > 80) {
                                                cb6.beginText(); // Abrimos el cb6
                                                cb6.setTextMatrix(40, sizeVertical);
                                                if (v.cargo_1 != null) {
                                                    cb6.showText(v.cargo_1);
                                                }
                                                cb6.setTextMatrix(120, sizeVertical);
                                                if (v.nombre != null) {
                                                    cb6.showText(v.nombre);
                                                }
                                                cb6.setTextMatrix(300, sizeVertical);
                                                if (v.direccion_1 != null) {
                                                    cb6.showText(v.direccion_1);
                                                }
                                                cb6.setTextMatrix(510, sizeVertical);
                                                if (v.telefono_1 != null) {
                                                    cb6.showText(v.telefono_1);
                                                }
                                                BaseFont bf12 = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                                cb6.setColorFill(new BaseColor(201, 0, 0));
                                                cb6.setFontAndSize(bf12, 9);
                                                cb6.setTextMatrix(160, sizeVertical - 15);
                                                cb6.showText("C.Postal:");
                                                cb6.setTextMatrix(255, sizeVertical - 15);
                                                cb6.showText("Correo E.:");
                                                cb6.setColorFill(new BaseColor(0, 0, 0));
                                                cb6.setFontAndSize(bf6, 9);
                                                cb6.setTextMatrix(200, sizeVertical - 15);
                                                if (v.codigo_postal_1 != null) {
                                                    cb6.showText(v.codigo_postal_1);
                                                }
                                                cb6.setTextMatrix(300, sizeVertical - 15);
                                                if (v.email != null) {
                                                    cb6.showText(v.email);
                                                }
                                                cb6.setTextMatrix(510, sizeVertical - 15);
                                                if (v.telefono_2 != null) {
                                                    cb6.showText(v.telefono_2);
                                                }
                                                cb5.setTextMatrix(40, sizeVertical - 20);
                                                cb5.showText("_________________________________________________________________________________________________________________");
                                                cb6.endText(); // Cerramos el cb6
                                                sizeVertical = sizeVertical - 40;
                                            } else { // Si tiene que saltar de página
                                                documento.newPage(); // Nueva página
                                                sizeVertical = 700; // Reiniciamos el inicio de la página
                                                numPag++;

                                                PdfContentByte cb4 = writer.getDirectContent();
                                                BaseFont bf4 = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                                cb5.setFontAndSize(bf4, 12);
                                                cb4.setColorFill(new BaseColor(201, 0, 0));
                                                cb4.beginText(); // Abrimos cb3
                                                cb4.setColorFill(new BaseColor(201, 0, 0));
                                                cb4.setTextMatrix(40, 730);
                                                cb4.showText("CARGO");
                                                cb4.setTextMatrix(120, 730);
                                                cb4.showText("NOMBRE");
                                                cb4.setTextMatrix(300, 730);
                                                cb4.showText("DIRECCIÓN");
                                                cb4.setTextMatrix(480, 730);
                                                cb4.showText("TELÉFONOS");
                                                cb4.setTextMatrix(40, 725);
                                                cb4.showText("_____________________________________________________________________________________");
                                                cb4.setTextMatrix(40, 724);
                                                cb4.showText("_____________________________________________________________________________________");

                                                cb4.setFontAndSize(bf, 11);
                                                cb4.setColorFill(new BaseColor(0, 0, 0));
                                                // Fecha
                                                cb4.setTextMatrix(490, 810);
                                                cb4.showText(fecha);
                                                // Número de página
                                                cb4.setTextMatrix(300, 30);
                                                cb4.showText(Integer.toString(numPag));

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
                                                            Logger.getLogger(ControladorListaInformeVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                                                        }
                                                    }
                                                } catch (SQLException ex) {
                                                    Logger.getLogger(ControladorListaCarnetVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                                                }

                                                cb4.endText(); // Cerramos cb2

                                                PdfContentByte cb7 = writer.getDirectContent();
                                                cb7.beginText(); // Abrimos cb7

                                                BaseFont bf7 = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                                cb7.setColorFill(new BaseColor(0, 0, 0));
                                                cb7.setFontAndSize(bf7, 9);
                                                if (sizeVertical > 80) {
                                                    cb7.setTextMatrix(40, sizeVertical);
                                                    if (v.cargo_1 != null) {
                                                        cb7.showText(v.cargo_1);
                                                    }
                                                    cb7.setTextMatrix(120, sizeVertical);
                                                    if (v.nombre != null) {
                                                        cb7.showText(v.nombre);
                                                    }
                                                    cb7.setTextMatrix(300, sizeVertical);
                                                    if (v.direccion_1 != null) {
                                                        cb7.showText(v.direccion_1);
                                                    }
                                                    cb7.setTextMatrix(510, sizeVertical);
                                                    if (v.telefono_1 != null) {
                                                        cb7.showText(v.telefono_1);
                                                    }
                                                    BaseFont bf12 = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                                                    cb7.setColorFill(new BaseColor(201, 0, 0));
                                                    cb7.setFontAndSize(bf12, 9);
                                                    cb7.setTextMatrix(160, sizeVertical - 15);
                                                    cb7.showText("C.Postal:");
                                                    cb7.setTextMatrix(255, sizeVertical - 15);
                                                    cb7.showText("Correo E.:");
                                                    cb7.setColorFill(new BaseColor(0, 0, 0));
                                                    cb7.setFontAndSize(bf12, 9);
                                                    cb7.setTextMatrix(200, sizeVertical - 15);
                                                    if (v.codigo_postal_1 != null) {
                                                        cb7.showText(v.codigo_postal_1);
                                                    }
                                                    cb7.setTextMatrix(300, sizeVertical - 15);
                                                    if (v.email != null) {
                                                        cb7.showText(v.email);
                                                    }
                                                    cb7.setTextMatrix(510, sizeVertical - 15);
                                                    if (v.telefono_2 != null) {
                                                        cb7.showText(v.telefono_2);
                                                    }
                                                    cb7.setTextMatrix(40, sizeVertical - 20);
                                                    cb7.showText("_________________________________________________________________________________________________________________");
                                                    cb7.endText(); // Cerramos el cb6
                                                    sizeVertical = sizeVertical - 40;
                                                }
                                            }
                                        }
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
                            alert.setContentText("Informe del equipo creado con éxito");
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
            alert.setContentText("Debe de seleccionar un equipo de la tabla para generar su informe");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
}
