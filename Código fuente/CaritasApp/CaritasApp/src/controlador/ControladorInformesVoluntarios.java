/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.ConnectionClass;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableWorkbook;
import jxl.common.Logger;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import modelo.Entrevistado;
import modelo.Voluntario;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorInformesVoluntarios implements Initializable {

    @FXML
    private Button excelVoluntarios;
    @FXML
    private Button pdfVoluntarios;
    @FXML
    private Button carnetVoluntario;
    @FXML
    private Button excelBajas;
    @FXML
    private Button excelEntrevistados;
    @FXML
    private Button pdfBajas;
    @FXML
    private Button pdfEntrevistado;

    URL rutaExcelVoluntarios, rutaPDFVoluntarios, rutaCarnet, rutaExcelBajas, rutaExcelEntrevistados, rutaPDFEntrevistado, rutaPDFBaja;
    Image imgExcel, imgPDF, imgCarnet;

    ObservableList<Voluntario> listaVoluntarios = FXCollections.observableArrayList();
    ObservableList<Entrevistado> listaEntrevistados = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addImage();
    }

    // Añadir imagenes a botones
    public void addImage() {

        //Boton de carnet voluntario
        rutaCarnet = getClass().getResource("/img/imageCarnet.jpg");
        imgCarnet = new Image(rutaCarnet.toString(), 40, 40, false, true);
        carnetVoluntario.setGraphic(new ImageView(imgCarnet));

        //Boton de excel voluntarios
        rutaExcelVoluntarios = getClass().getResource("/img/imageExportar.png");
        imgExcel = new Image(rutaExcelVoluntarios.toString(), 40, 40, false, true);
        excelVoluntarios.setGraphic(new ImageView(imgExcel));

        //Boton de excel bajas
        rutaExcelBajas = getClass().getResource("/img/imageExportar.jpg");
        excelBajas.setGraphic(new ImageView(imgExcel));

        //Boton de excel bajas
        rutaExcelEntrevistados = getClass().getResource("/img/imageExportar.jpg");
        excelEntrevistados.setGraphic(new ImageView(imgExcel));

        //Boton de PDF voluntarios
        rutaPDFVoluntarios = getClass().getResource("/img/imageInforme.png");
        imgPDF = new Image(rutaPDFVoluntarios.toString(), 40, 40, false, true);
        pdfVoluntarios.setGraphic(new ImageView(imgPDF));

        //Boton de PDF entrevistados
        rutaPDFEntrevistado = getClass().getResource("/img/imageInforme.jpg");
        pdfEntrevistado.setGraphic(new ImageView(imgPDF));

        //Boton de PDF entrevistados
        rutaPDFBaja = getClass().getResource("/img/imageInforme.jpg");
        pdfBajas.setGraphic(new ImageView(imgPDF));
    }

    @FXML
    private void generarExcelVoluntarios(ActionEvent event) throws IOException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM voluntario WHERE baja_caritas = '0' ORDER BY dni ASC");
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
                if (rs.getString("baja_caritas").equals("0")) {
                    baja = "SI";
                }
                if (rs.getString("tiene_foto").equals("1")) {
                    tieneFoto = "SI";
                }
                listaVoluntarios.add(new Voluntario(rs.getString("dni"), rs.getString("nombre"), rs.getString("sexo"), rs.getString("fecha_nacimiento").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), rs.getString("telefono_1"),
                        rs.getString("telefono_2"), rs.getString("email"), rs.getString("direccion_1"), rs.getString("codigo_postal_1"), rs.getString("poblacion_1"),
                        rs.getString("provincia"), rs.getString("nacionalidad"), rs.getString("estado_civil"), rs.getString("estudios"), rs.getString("lugar_estudios"), rs.getString("observaciones_formacion"),
                        rs.getString("idioma_1"), rs.getString("idioma_2"), rs.getString("idioma_3"), rs.getString("nivel_1"), rs.getString("nivel_2"), rs.getString("nivel_3"), rs.getString("informatica"),
                        rs.getString("situacion_laboral"), carnet, rs.getString("experiencia_laboral"), rs.getString("fecha_alta").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), rs.getString("zona"), rs.getString("poblacion_2"),
                        rs.getString("equipo_1"), rs.getString("direccion_2"), rs.getString("codigo_postal_2"), rs.getString("telefono_3"), rs.getString("cargo_1"), rs.getString("programa_1"),
                        rs.getString("programa_2"), rs.getString("cargo_2"), rs.getString("cargo_3"), rs.getString("observaciones_voluntario"), consejo, ficha,
                        delitos, publicacion, acuerdo, antecedentes, autorizacion, rgpd, email, postales, rs.getString("observaciones_documentacion"), baja, rs.getString("fecha_baja").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), tieneFoto));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[][] entrada = new String[listaVoluntarios.size() + 1][53];
        entrada[0][0] = "DNI";
        entrada[0][1] = "NOMBRE";
        entrada[0][2] = "SEXO";
        entrada[0][3] = "FECHA DE NACIMIENTO";
        entrada[0][4] = "TELÉFONO 1";
        entrada[0][5] = "TELÉFONO 2";
        entrada[0][6] = "EMAIL";
        entrada[0][7] = "DIRECCIÓN 1";
        entrada[0][8] = "CÓDIGO POSTAL 1";
        entrada[0][9] = "POBLACIÓN 1";
        entrada[0][10] = "PROVINCIA";
        entrada[0][11] = "NACIONALIDAD";
        entrada[0][12] = "ESTADO CIVIL";
        entrada[0][13] = "ESTUDIOS";
        entrada[0][14] = "LUGAR ESTUDIOS";
        entrada[0][15] = "OBSERVACIONES FORMACIÓN";
        entrada[0][16] = "IDIOMA 1";
        entrada[0][17] = "IDIOMA 2";
        entrada[0][18] = "IDIOMA 3";
        entrada[0][19] = "NIVEL 1";
        entrada[0][20] = "NIVEL 2";
        entrada[0][21] = "NIVEL 3";
        entrada[0][22] = "INFORMÁTICA";
        entrada[0][23] = "SITUACIÓN LABORAL";
        entrada[0][24] = "CARNÉT";
        entrada[0][25] = "EXPERIENCIA LABORAL";
        entrada[0][26] = "FECHA DE ALTA";
        entrada[0][27] = "ZONA";
        entrada[0][28] = "POBLACIÓN 2";
        entrada[0][29] = "EQUIPO";
        entrada[0][30] = "DIRECCIÓN 2";
        entrada[0][31] = "CÓDIGO POSTAL 2";
        entrada[0][32] = "TELÉFONO 3";
        entrada[0][33] = "CARGO 1";
        entrada[0][34] = "PROGRAMA 1";
        entrada[0][35] = "PROGRAMA 2";
        entrada[0][36] = "CARGO 2";
        entrada[0][37] = "CARGO 3";
        entrada[0][38] = "OBSERVACIONES VOLUNTARIO";
        entrada[0][39] = "CONSEJO DIOCESANO";
        entrada[0][40] = "FICHA VOLUNTARIO";
        entrada[0][41] = "DELITOS SEXUALES";
        entrada[0][42] = "PUBLICACIÓN DE IMAGEN";
        entrada[0][43] = "ACUERDO DE COLABORACIÓN";
        entrada[0][44] = "ANTECEDENTES PENALES";
        entrada[0][45] = "AUTORIZACIÓN PATERNA";
        entrada[0][46] = "RGPD";
        entrada[0][47] = "ENVÍOS EMAIL";
        entrada[0][48] = "ENVÍOS POSTALES";
        entrada[0][49] = "OBSERVACIONES DOCUMENTACIÓN";
        entrada[0][50] = "ACTIVO/A";
        entrada[0][51] = "FECHA DE BAJA";
        entrada[0][52] = "FOTO";

        if (!listaVoluntarios.isEmpty()) {
            int i = 1;
            for (Voluntario v : listaVoluntarios) {
                entrada[i][0] = v.dni;
                entrada[i][1] = v.nombre;
                entrada[i][2] = v.sexo;
                entrada[i][3] = v.fecha_nacimiento;
                entrada[i][4] = v.telefono_1;
                entrada[i][5] = v.telefono_2;
                entrada[i][6] = v.email;
                entrada[i][7] = v.direccion_1;
                entrada[i][8] = v.codigo_postal_1;
                entrada[i][9] = v.poblacion_1;
                entrada[i][10] = v.provincia;
                entrada[i][11] = v.nacionalidad;
                entrada[i][12] = v.estado_civil;
                entrada[i][13] = v.estudios;
                entrada[i][14] = v.lugar_estudios;
                entrada[i][15] = v.observaciones_formacion;
                entrada[i][16] = v.idioma_1;
                entrada[i][17] = v.idioma_2;
                entrada[i][18] = v.idioma_3;
                entrada[i][19] = v.nivel_1;
                entrada[i][20] = v.nivel_2;
                entrada[i][21] = v.nivel_3;
                entrada[i][22] = v.informatica;
                entrada[i][23] = v.situacion_laboral;
                entrada[i][24] = v.carnet;
                entrada[i][25] = v.experiencia_laboral;
                entrada[i][26] = v.fecha_alta;
                entrada[i][27] = v.zona;
                entrada[i][28] = v.poblacion_2;
                entrada[i][29] = v.equipo_1;
                entrada[i][30] = v.direccion_2;
                entrada[i][31] = v.codigo_postal_2;
                entrada[i][32] = v.telefono_3;
                entrada[i][33] = v.cargo_1;
                entrada[i][34] = v.programa_1;
                entrada[i][35] = v.programa_2;
                entrada[i][36] = v.cargo_2;
                entrada[i][37] = v.cargo_3;
                entrada[i][38] = v.observaciones_voluntario;
                entrada[i][39] = v.consejo_diocesano;
                entrada[i][40] = v.ficha_voluntario;
                entrada[i][41] = v.delitos_sexuales;
                entrada[i][42] = v.publicacion_imagen;
                entrada[i][43] = v.acuerdo_colaboracion;
                entrada[i][44] = v.antecedentes_penales;
                entrada[i][45] = v.autorizacion_paterna;
                entrada[i][46] = v.rgpd;
                entrada[i][47] = v.envios_email;
                entrada[i][48] = v.envios_postales;
                entrada[i][49] = v.observaciones_documentacion;
                entrada[i][50] = v.baja_caritas;
                entrada[i][51] = v.fecha_baja;
                entrada[i][52] = v.tiene_foto;
                i++;
            }
        }

        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.setDialogTitle("Seleccione la ruta donde guardar el fichero");
        f.showSaveDialog(null);
        if (f.getSelectedFile() != null) {
            String ruta;
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
                ruta = rutaFichero.toString() + "/" + respuesta.get() + ".xls";
                File file = new File(ruta);
                if (!file.exists()) {
                    generarExcel(entrada, ruta);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Fichero de voluntarios exportado con éxito");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("El nombre del fichero ya existe");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    private void generarExcelBajas(ActionEvent event) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM voluntario WHERE baja_caritas = '1' ORDER BY dni ASC");
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
                if (rs.getString("baja_caritas").equals("0")) {
                    baja = "SI";
                }
                if (rs.getString("tiene_foto").equals("1")) {
                    tieneFoto = "SI";
                }
                listaVoluntarios.add(new Voluntario(rs.getString("dni"), rs.getString("nombre"), rs.getString("sexo"), rs.getString("fecha_nacimiento").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), rs.getString("telefono_1"),
                        rs.getString("telefono_2"), rs.getString("email"), rs.getString("direccion_1"), rs.getString("codigo_postal_1"), rs.getString("poblacion_1"),
                        rs.getString("provincia"), rs.getString("nacionalidad"), rs.getString("estado_civil"), rs.getString("estudios"), rs.getString("lugar_estudios"), rs.getString("observaciones_formacion"),
                        rs.getString("idioma_1"), rs.getString("idioma_2"), rs.getString("idioma_3"), rs.getString("nivel_1"), rs.getString("nivel_2"), rs.getString("nivel_3"), rs.getString("informatica"),
                        rs.getString("situacion_laboral"), carnet, rs.getString("experiencia_laboral"), rs.getString("fecha_alta").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), rs.getString("zona"), rs.getString("poblacion_2"),
                        rs.getString("equipo_1"), rs.getString("direccion_2"), rs.getString("codigo_postal_2"), rs.getString("telefono_3"), rs.getString("cargo_1"), rs.getString("programa_1"),
                        rs.getString("programa_2"), rs.getString("cargo_2"), rs.getString("cargo_3"), rs.getString("observaciones_voluntario"), consejo, ficha,
                        delitos, publicacion, acuerdo, antecedentes, autorizacion, rgpd, email, postales, rs.getString("observaciones_documentacion"), baja, rs.getString("fecha_baja").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), tieneFoto));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[][] entrada = new String[listaVoluntarios.size() + 1][53];
        entrada[0][0] = "DNI";
        entrada[0][1] = "NOMBRE";
        entrada[0][2] = "SEXO";
        entrada[0][3] = "FECHA DE NACIMIENTO";
        entrada[0][4] = "TELÉFONO 1";
        entrada[0][5] = "TELÉFONO 2";
        entrada[0][6] = "EMAIL";
        entrada[0][7] = "DIRECCIÓN 1";
        entrada[0][8] = "CÓDIGO POSTAL 1";
        entrada[0][9] = "POBLACIÓN 1";
        entrada[0][10] = "PROVINCIA";
        entrada[0][11] = "NACIONALIDAD";
        entrada[0][12] = "ESTADO CIVIL";
        entrada[0][13] = "ESTUDIOS";
        entrada[0][14] = "LUGAR ESTUDIOS";
        entrada[0][15] = "OBSERVACIONES FORMACIÓN";
        entrada[0][16] = "IDIOMA 1";
        entrada[0][17] = "IDIOMA 2";
        entrada[0][18] = "IDIOMA 3";
        entrada[0][19] = "NIVEL 1";
        entrada[0][20] = "NIVEL 2";
        entrada[0][21] = "NIVEL 3";
        entrada[0][22] = "INFORMÁTICA";
        entrada[0][23] = "SITUACIÓN LABORAL";
        entrada[0][24] = "CARNÉT";
        entrada[0][25] = "EXPERIENCIA LABORAL";
        entrada[0][26] = "FECHA DE ALTA";
        entrada[0][27] = "ZONA";
        entrada[0][28] = "POBLACIÓN 2";
        entrada[0][29] = "EQUIPO";
        entrada[0][30] = "DIRECCIÓN 2";
        entrada[0][31] = "CÓDIGO POSTAL 2";
        entrada[0][32] = "TELÉFONO 3";
        entrada[0][33] = "CARGO 1";
        entrada[0][34] = "PROGRAMA 1";
        entrada[0][35] = "PROGRAMA 2";
        entrada[0][36] = "CARGO 2";
        entrada[0][37] = "CARGO 3";
        entrada[0][38] = "OBSERVACIONES VOLUNTARIO";
        entrada[0][39] = "CONSEJO DIOCESANO";
        entrada[0][40] = "FICHA VOLUNTARIO";
        entrada[0][41] = "DELITOS SEXUALES";
        entrada[0][42] = "PUBLICACIÓN DE IMAGEN";
        entrada[0][43] = "ACUERDO DE COLABORACIÓN";
        entrada[0][44] = "ANTECEDENTES PENALES";
        entrada[0][45] = "AUTORIZACIÓN PATERNA";
        entrada[0][46] = "RGPD";
        entrada[0][47] = "ENVÍOS EMAIL";
        entrada[0][48] = "ENVÍOS POSTALES";
        entrada[0][49] = "OBSERVACIONES DOCUMENTACIÓN";
        entrada[0][50] = "ACTIVO/A";
        entrada[0][51] = "FECHA DE BAJA";
        entrada[0][52] = "FOTO";

        if (!listaVoluntarios.isEmpty()) {
            int i = 1;
            for (Voluntario v : listaVoluntarios) {
                entrada[i][0] = v.dni;
                entrada[i][1] = v.nombre;
                entrada[i][2] = v.sexo;
                entrada[i][3] = v.fecha_nacimiento;
                entrada[i][4] = v.telefono_1;
                entrada[i][5] = v.telefono_2;
                entrada[i][6] = v.email;
                entrada[i][7] = v.direccion_1;
                entrada[i][8] = v.codigo_postal_1;
                entrada[i][9] = v.poblacion_1;
                entrada[i][10] = v.provincia;
                entrada[i][11] = v.nacionalidad;
                entrada[i][12] = v.estado_civil;
                entrada[i][13] = v.estudios;
                entrada[i][14] = v.lugar_estudios;
                entrada[i][15] = v.observaciones_formacion;
                entrada[i][16] = v.idioma_1;
                entrada[i][17] = v.idioma_2;
                entrada[i][18] = v.idioma_3;
                entrada[i][19] = v.nivel_1;
                entrada[i][20] = v.nivel_2;
                entrada[i][21] = v.nivel_3;
                entrada[i][22] = v.informatica;
                entrada[i][23] = v.situacion_laboral;
                entrada[i][24] = v.carnet;
                entrada[i][25] = v.experiencia_laboral;
                entrada[i][26] = v.fecha_alta;
                entrada[i][27] = v.zona;
                entrada[i][28] = v.poblacion_2;
                entrada[i][29] = v.equipo_1;
                entrada[i][30] = v.direccion_2;
                entrada[i][31] = v.codigo_postal_2;
                entrada[i][32] = v.telefono_3;
                entrada[i][33] = v.cargo_1;
                entrada[i][34] = v.programa_1;
                entrada[i][35] = v.programa_2;
                entrada[i][36] = v.cargo_2;
                entrada[i][37] = v.cargo_3;
                entrada[i][38] = v.observaciones_voluntario;
                entrada[i][39] = v.consejo_diocesano;
                entrada[i][40] = v.ficha_voluntario;
                entrada[i][41] = v.delitos_sexuales;
                entrada[i][42] = v.publicacion_imagen;
                entrada[i][43] = v.acuerdo_colaboracion;
                entrada[i][44] = v.antecedentes_penales;
                entrada[i][45] = v.autorizacion_paterna;
                entrada[i][46] = v.rgpd;
                entrada[i][47] = v.envios_email;
                entrada[i][48] = v.envios_postales;
                entrada[i][49] = v.observaciones_documentacion;
                entrada[i][50] = v.baja_caritas;
                entrada[i][51] = v.fecha_baja;
                entrada[i][52] = v.tiene_foto;
                i++;
            }
        }

        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.setDialogTitle("Seleccione la ruta donde guardar el fichero");
        f.showSaveDialog(null);
        if (f.getSelectedFile() != null) {
            String ruta;
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
                ruta = rutaFichero.toString() + "/" + respuesta.get() + ".xls";
                File file = new File(ruta);
                if (!file.exists()) {
                    generarExcel(entrada, ruta);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Fichero de bajas exportado con éxito");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("El nombre del fichero ya existe");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    private void generarEntrevistados(ActionEvent event) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM entrevistado ORDER BY dni ASC");
            while (rs.next()) {

                String tieneFoto = "NO";
                String esVoluntario = "NO";
                if (rs.getString("tiene_foto").equals("1")) {
                    tieneFoto = "SI";
                }
                if (rs.getString("es_voluntario").equals("1")) {
                    esVoluntario = "SI";
                }
                listaEntrevistados.add(new Entrevistado(rs.getString("dni"), rs.getString("nombre"), rs.getString("sexo"), rs.getString("fecha_nacimiento").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"), rs.getString("telefono1"),
                        rs.getString("telefono2"), rs.getString("email"), rs.getString("direccion"), rs.getString("codigo_postal"), rs.getString("poblacion"),
                        rs.getString("provincia"), rs.getString("nacionalidad"), rs.getString("estado_civil"), rs.getString("fecha_entrevista").replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1"),
                        rs.getString("observaciones"), tieneFoto, esVoluntario));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[][] entrada = new String[listaEntrevistados.size() + 1][53];
        entrada[0][0] = "DNI";
        entrada[0][1] = "NOMBRE";
        entrada[0][2] = "SEXO";
        entrada[0][3] = "FECHA DE NACIMIENTO";
        entrada[0][4] = "TELÉFONO 1";
        entrada[0][5] = "TELÉFONO 2";
        entrada[0][6] = "EMAIL";
        entrada[0][7] = "DIRECCIÓN";
        entrada[0][8] = "CÓDIGO POSTAL";
        entrada[0][9] = "POBLACIÓN";
        entrada[0][10] = "PROVINCIA";
        entrada[0][11] = "NACIONALIDAD";
        entrada[0][12] = "ESTADO CIVIL";
        entrada[0][13] = "FECHA ENTREVISTA";
        entrada[0][14] = "OBSERVACIONES";
        entrada[0][15] = "TIENE FOTO";
        entrada[0][16] = "ES VOLUNTARIO";

        if (!listaEntrevistados.isEmpty()) {
            int i = 1;
            for (Entrevistado v : listaEntrevistados) {
                entrada[i][0] = v.dni;
                entrada[i][1] = v.nombre;
                entrada[i][2] = v.sexo;
                entrada[i][3] = v.fecha_nacimiento;
                entrada[i][4] = v.telefono1;
                entrada[i][5] = v.telefono2;
                entrada[i][6] = v.email;
                entrada[i][7] = v.direccion;
                entrada[i][8] = v.codigo_postal;
                entrada[i][9] = v.poblacion;
                entrada[i][10] = v.provincia;
                entrada[i][11] = v.nacionalidad;
                entrada[i][12] = v.estado_civil;
                entrada[i][13] = v.fecha_entrevista;
                entrada[i][14] = v.observaciones;
                entrada[i][15] = v.tiene_foto;
                entrada[i][16] = v.es_voluntario;
                i++;
            }
        }

        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.setDialogTitle("Seleccione la ruta donde guardar el fichero");
        f.showSaveDialog(null);
        if (f.getSelectedFile() != null) {
            String ruta;
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
                ruta = rutaFichero.toString() + "/" + respuesta.get() + ".xls";
                File file = new File(ruta);
                if (!file.exists()) {
                    generarExcel(entrada, ruta);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Fichero de entrevistados exportado con éxito");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("El nombre del fichero ya existe");
                    alert.showAndWait();
                }
            }
        }
    }

    public void generarExcel(String[][] entrada, String ruta) {
        try {
            WorkbookSettings conf = new WorkbookSettings();
            conf.setEncoding("ISO-8859-1");
            WritableWorkbook woorBook = Workbook.createWorkbook(new File(ruta), conf);

            WritableSheet sheet = woorBook.createSheet("Resultado", 0);

            WritableFont h = new WritableFont(WritableFont.COURIER, 11, WritableFont.NO_BOLD);
            WritableCellFormat hFormat = new WritableCellFormat(h);

            for (int i = 0; i < entrada.length; i++) {
                for (int j = 0; j < entrada[i].length; j++) {
                    try {
                        sheet.addCell(new jxl.write.Label(j, i, entrada[i][j], hFormat));
                    } catch (WriteException ex) {
                        java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            woorBook.write();
            try {
                woorBook.close();
            } catch (WriteException ex) {
                java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ControladorInformesVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void generarPDFVoluntarios(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaListaInformeVoluntarios.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void generarCarnetVoluntario(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaListaCarnetVoluntarios.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void generarPDFBajas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaListaInformeBajas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void generarPDFEntrevistado(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaListaInformeEntrevistados.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
