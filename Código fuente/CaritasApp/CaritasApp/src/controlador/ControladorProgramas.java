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
public class ControladorProgramas implements Initializable {

    URL rutaNuevoPrograma, rutaConsultarProgramas, rutaEliminarPrograma;
    Image imgNuevoPrograma, imgConsultarProgramas, imgEliminarPrograma;

    @FXML
    private Button nuevoPrograma;
    @FXML
    private Button consultaProgramas;
    @FXML
    private Button eliminaProgramas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addImage();
    }

    // Añadir imagenes a botones
    public void addImage() {
        //Boton de nuevo equipo
        rutaNuevoPrograma = getClass().getResource("/img/imageAltas.jpg");
        imgNuevoPrograma = new Image(rutaNuevoPrograma.toString(), 40, 40, false, true);
        nuevoPrograma.setGraphic(new ImageView(imgNuevoPrograma));

        //Boton de consultar equipos
        rutaConsultarProgramas = getClass().getResource("/img/imageVer.jpg");
        imgConsultarProgramas = new Image(rutaConsultarProgramas.toString(), 40, 40, false, true);
        consultaProgramas.setGraphic(new ImageView(imgConsultarProgramas));

        // Boton eliminar equipo
        rutaEliminarPrograma = getClass().getResource("/img/imageBajas.jpg");
        imgEliminarPrograma = new Image(rutaEliminarPrograma.toString(), 40, 40, false, true);
        eliminaProgramas.setGraphic(new ImageView(imgEliminarPrograma));
    }

    @FXML
    private void agregarPrograma(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaNuevoPrograma.fxml"));
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
    private void consultarProgramas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaConsultaProgramas.fxml"));
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
    private void eliminarProgramas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaEliminarPrograma.fxml"));
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
}
