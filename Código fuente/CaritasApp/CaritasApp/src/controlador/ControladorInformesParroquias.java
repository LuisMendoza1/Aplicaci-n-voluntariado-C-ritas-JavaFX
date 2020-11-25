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
import modelo.Parroquia;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorInformesParroquias implements Initializable {

    @FXML
    private Button excelParroquias;
    @FXML
    private Button pdfParroquia;

    URL rutaExcelParroquias, rutaPDFParroquias;
    Image imgExcel, imgPDF;

    ObservableList<Parroquia> listaParroquias = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addImage();
    }

    // Añadir imagenes a botones
    public void addImage() {

        //Boton de excel parroquias
        rutaExcelParroquias = getClass().getResource("/img/imageExportar.png");
        imgExcel = new Image(rutaExcelParroquias.toString(), 40, 40, false, true);
        excelParroquias.setGraphic(new ImageView(imgExcel));

        //Boton de PDF parroquias
        rutaPDFParroquias = getClass().getResource("/img/imageInforme.png");
        imgPDF = new Image(rutaPDFParroquias.toString(), 40, 40, false, true);
        pdfParroquia.setGraphic(new ImageView(imgPDF));
    }

    @FXML
    private void generarExcelParroquias(ActionEvent event) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM parroquias ORDER BY nombre ASC");

            while (rs.next()) {
                listaParroquias.add(new Parroquia(rs.getString("nombre"), rs.getString("parroco"), rs.getString("zona"), rs.getString("poblacion"), rs.getString("direccion"),
                        rs.getString("codigo_postal"), rs.getString("provincia"), rs.getString("carteles"), rs.getString("tripticos"), rs.getString("sobres"),
                        rs.getString("unidades_didacticas")));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControladorInformesParroquias.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[][] entrada = new String[listaParroquias.size() + 1][11];
        entrada[0][0] = "NOMBRE";
        entrada[0][1] = "PÁRROCO";
        entrada[0][2] = "ZONA";
        entrada[0][3] = "POBLACIÓN";
        entrada[0][4] = "DIRECCIÓN";
        entrada[0][5] = "CÓDIGO POSTAL";
        entrada[0][6] = "PROVINCIA";
        entrada[0][7] = "CARTELES";
        entrada[0][8] = "TRIPTICOS";
        entrada[0][9] = "SOBRES";
        entrada[0][10] = "UNIDADES DIDÁCTICAS";

        if (!listaParroquias.isEmpty()) {
            int i = 1;
            for (Parroquia v : listaParroquias) {
                entrada[i][0] = v.nombre;
                entrada[i][1] = v.parroco;
                entrada[i][2] = v.zona;
                entrada[i][3] = v.poblacion;
                entrada[i][4] = v.direccion;
                entrada[i][5] = v.codigo_postal;
                entrada[i][6] = v.provincia;
                entrada[i][7] = v.carteles;
                entrada[i][8] = v.tripticos;
                entrada[i][9] = v.sobres;
                entrada[i][10] = v.unidades_didacticas;
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
                    alert.setContentText("Fichero de parroquias exportado con éxito");
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
                        java.util.logging.Logger.getLogger(ControladorInformesParroquias.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            woorBook.write();
            try {
                woorBook.close();
            } catch (WriteException ex) {
                java.util.logging.Logger.getLogger(ControladorInformesParroquias.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ControladorInformesParroquias.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void generarPDFParroquia(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaListaInformeParroquias.fxml"));
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
