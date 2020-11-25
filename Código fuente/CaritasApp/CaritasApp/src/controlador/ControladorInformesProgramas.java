/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.ConnectionClass;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JFileChooser;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableWorkbook;
import jxl.common.Logger;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import modelo.Equipo;
import modelo.Programa;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorInformesProgramas implements Initializable {

    URL rutaExcelProgramas, rutaPDFProgramas;
    Image imgExcel, imgPDF;

    ObservableList<Programa> listaProgramas = FXCollections.observableArrayList();
    @FXML
    private Button excelProgramas;
    @FXML
    private Button pdfPrograma;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addImage();
    }

    // Añadir imagenes a botones
    public void addImage() {

        //Boton de excel equipos
        rutaExcelProgramas = getClass().getResource("/img/imageExportar.png");
        imgExcel = new Image(rutaExcelProgramas.toString(), 40, 40, false, true);
        excelProgramas.setGraphic(new ImageView(imgExcel));

        //Boton de PDF equipos
        rutaPDFProgramas = getClass().getResource("/img/imageInforme.png");
        imgPDF = new Image(rutaPDFProgramas.toString(), 40, 40, false, true);
        pdfPrograma.setGraphic(new ImageView(imgPDF));
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
                        java.util.logging.Logger.getLogger(ControladorInformesProgramas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            woorBook.write();
            try {
                woorBook.close();
            } catch (WriteException ex) {
                java.util.logging.Logger.getLogger(ControladorInformesProgramas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ControladorInformesProgramas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generarPDFEquipos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaListaInformeEquipos.fxml"));
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
    private void generarExcelProgramas(ActionEvent event) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM programas ORDER BY programa ASC");
            while (rs.next()) {
                listaProgramas.add(new Programa(rs.getString("programa")));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControladorInformesProgramas.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[][] entrada = new String[listaProgramas.size() + 1][8];
        entrada[0][0] = "PROGRAMA";

        if (!listaProgramas.isEmpty()) {
            int i = 1;
            for (Programa v : listaProgramas) {
                entrada[i][0] = v.nombre;
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
                    alert.setContentText("Fichero de programas exportado con éxito");
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
    private void generarPDFProgramas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaListaInformeProgramas.fxml"));
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
