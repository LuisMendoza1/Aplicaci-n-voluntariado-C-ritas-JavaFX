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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableWorkbook;
import jxl.common.Logger;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import modelo.Voluntario;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorInformes implements Initializable {

    @FXML
    private Button informesVoluntarios;
    @FXML
    private Button informesParroquias;
    @FXML
    private Button informesEquipos;
    @FXML
    private Button informesProgramas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void seleccionarInformesVoluntarios(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaInformesVoluntarios.fxml"));
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
    private void seleccionarInformesParroquias(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaInformesParroquias.fxml"));
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
    private void seleccionarInformesEquipos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaInformesEquipos.fxml"));
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
    private void seleccionarInformesProgramas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaInformesProgramas.fxml"));
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
