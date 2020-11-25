/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorEquipos implements Initializable {

    URL rutaNuevoEquipo, rutaConsultarEquipos, rutaEliminarEquipo;
    Image imgNuevoEquipo, imgConsultarEquipos, imgEliminarEquipo;

    @FXML
    private Button nuevoEquipo;
    @FXML
    private Button consultaEquipos;
    @FXML
    private Button eliminaEquipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addImage();
    }

    @FXML
    private void agregarEquipo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaNuevoEquipo.fxml"));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ControladorParroquias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void consultarEquipos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaConsultaEquipos.fxml"));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ControladorParroquias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eliminarEquipo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaEliminarEquipo.fxml"));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ControladorParroquias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Añadir imagenes a botones
    public void addImage() {
        //Boton de nuevo equipo
        rutaNuevoEquipo = getClass().getResource("/img/imageAltas.jpg");
        imgNuevoEquipo = new Image(rutaNuevoEquipo.toString(), 40, 40, false, true);
        nuevoEquipo.setGraphic(new ImageView(imgNuevoEquipo));

        //Boton de consultar equipos
        rutaConsultarEquipos = getClass().getResource("/img/imageVer.jpg");
        imgConsultarEquipos = new Image(rutaConsultarEquipos.toString(), 40, 40, false, true);
        consultaEquipos.setGraphic(new ImageView(imgConsultarEquipos));

        // Boton eliminar equipo
        rutaEliminarEquipo = getClass().getResource("/img/imageBajas.jpg");
        imgEliminarEquipo = new Image(rutaEliminarEquipo.toString(), 40, 40, false, true);
        eliminaEquipo.setGraphic(new ImageView(imgEliminarEquipo));
    }
}
