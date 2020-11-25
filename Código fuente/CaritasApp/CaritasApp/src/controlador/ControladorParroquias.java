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
public class ControladorParroquias implements Initializable {

    @FXML
    private Button nuevaParroquia;
    @FXML
    private Button consultaParroquias;
    @FXML
    private Button eliminaParroquias;

    URL rutaNuevaParroquia, rutaConsultarParroquias, rutaEliminarParroquia;
    Image imgNuevaParroquia, imgConsultarParroquias, imgEliminarParroquia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addImage();
    }

    @FXML
    private void agregarParroquia(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaNuevaParroquia.fxml"));
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
    private void consultarParroquias(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaConsultaParroquias.fxml"));
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
    private void eliminarParroquia(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaEliminarParroquia.fxml"));
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
        //Boton de nueva parroquia
        rutaNuevaParroquia = getClass().getResource("/img/imageAltas.jpg");
        imgNuevaParroquia = new Image(rutaNuevaParroquia.toString(), 40, 40, false, true);
        nuevaParroquia.setGraphic(new ImageView(imgNuevaParroquia));

        //Boton de consultar parroquias
        rutaConsultarParroquias = getClass().getResource("/img/imageVer.jpg");
        imgConsultarParroquias = new Image(rutaConsultarParroquias.toString(), 40, 40, false, true);
        consultaParroquias.setGraphic(new ImageView(imgConsultarParroquias));

        // Boton eliminar parroquia
        rutaEliminarParroquia = getClass().getResource("/img/imageBajas.jpg");
        imgEliminarParroquia = new Image(rutaEliminarParroquia.toString(), 40, 40, false, true);
        eliminaParroquias.setGraphic(new ImageView(imgEliminarParroquia));
    }
}
