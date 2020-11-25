/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorVoluntarios implements Initializable {

    @FXML
    private Button consultaActivos;
    @FXML
    private Button consultaBajas;
    @FXML
    private Button altaVoluntario;
    @FXML
    private Button consultarEntrevistado;
    @FXML
    private Button bajaVoluntario;
    @FXML
    private Button eliminarVoluntario;
    @FXML
    private Button eliminarEntrevistado;

    URL rutaAlta, rutaBaja, rutaConsultaAltas, rutaConsultaBajas, rutaBajaEntrevistado, rutaConsultarEntrevistado, rutaBajaVoluntario;
    Image imgAlta, imgBaja, imgConsultaAltas, imgConsultaBajas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addImage();
    }

    // Añadir imagenes a botones
    public void addImage() {

        //Boton de alta voluntarios
        rutaAlta = getClass().getResource("/img/imageAltas.jpg");
        imgAlta = new Image(rutaAlta.toString(), 40, 40, false, true);
        altaVoluntario.setGraphic(new ImageView(imgAlta));

        //Boton de consulta activos
        rutaConsultaAltas = getClass().getResource("/img/imageVer.jpg");
        imgConsultaAltas = new Image(rutaConsultaAltas.toString(), 40, 40, false, true);
        consultaActivos.setGraphic(new ImageView(imgConsultaAltas));

        //Boton de consulta bajas
        rutaConsultaBajas = getClass().getResource("/img/imageBajas.jpg");
        imgConsultaBajas = new Image(rutaConsultaBajas.toString(), 40, 40, false, true);
        consultaBajas.setGraphic(new ImageView(imgConsultaAltas));

        //Boton de consultar entrevistados
        consultarEntrevistado.setGraphic(new ImageView(imgConsultaAltas));

        //Boton de dar de baja voluntario
        bajaVoluntario.setGraphic(new ImageView(imgConsultaBajas));

        //Boton de eliminar voluntario
        eliminarVoluntario.setGraphic(new ImageView(imgConsultaBajas));

        //Boton de eliminar entrevistado
        eliminarEntrevistado.setGraphic(new ImageView(imgConsultaBajas));
    }

    // Boton Añadir Voluntario
    @FXML
    private void agregarVoluntario(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaEntrevista.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Botón Consultar Voluntario
    @FXML
    private void consultarVoluntarios(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaConsultaVoluntarios.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Botón Consultar Bajas
    @FXML
    private void consultarBajas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaConsultaBajas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Botón Consultar Entrevistados
    @FXML
    private void consultarEntrevistados(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaConsultaEntrevistados.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void darDeBajaVoluntario(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaDarDeBajaVoluntario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eliminarVoluntario(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaEliminarVoluntario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eliminarEntrevistado(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaEliminarEntrevistado.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("/img/imageAPP.png"));
            stage.setTitle("Cáritas Diocesana Albacete");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
