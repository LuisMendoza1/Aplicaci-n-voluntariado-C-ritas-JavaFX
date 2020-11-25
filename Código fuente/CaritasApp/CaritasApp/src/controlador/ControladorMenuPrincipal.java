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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorMenuPrincipal implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnVoluntarios;
    @FXML
    private Button btnInformes;
    @FXML
    private Button btnParroquias;
    @FXML
    private Button btnEquipos;
    @FXML
    private Button btnProgramas;

    public boolean clickInicio = false,
            clickVoluntarios = false,
            clickInformes = false,
            clickParroquias = false,
            clickEquipos = false,
            clickProgramas = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void inicio(MouseEvent event) {
        clickInicio = true;
        clickVoluntarios = false;
        clickInformes = false;
        clickParroquias = false;
        clickEquipos = false;
        clickProgramas = false;
        btnInicio.setTextFill(Color.web("#f8a6a6"));
        btnVoluntarios.setTextFill(Color.web("#ffffff"));
        btnInformes.setTextFill(Color.web("#ffffff"));
        btnParroquias.setTextFill(Color.web("#ffffff"));
        btnEquipos.setTextFill(Color.web("#ffffff"));
        btnProgramas.setTextFill(Color.web("#ffffff"));
        bp.setCenter(ap);
    }

    @FXML
    private void voluntarios(MouseEvent event) {
        clickInicio = false;
        clickVoluntarios = true;
        clickInformes = false;
        clickParroquias = false;
        clickEquipos = false;
        clickProgramas = false;
        btnInicio.setTextFill(Color.web("#ffffff"));
        btnVoluntarios.setTextFill(Color.web("#f8a6a6"));
        btnInformes.setTextFill(Color.web("#ffffff"));
        btnParroquias.setTextFill(Color.web("#ffffff"));
        btnEquipos.setTextFill(Color.web("#ffffff"));
        btnProgramas.setTextFill(Color.web("#ffffff"));
        loadPage("/vista/VistaVoluntarios");
    }

    @FXML
    private void informes(MouseEvent event) {
        clickInicio = false;
        clickVoluntarios = false;
        clickInformes = true;
        clickParroquias = false;
        clickEquipos = false;
        clickProgramas = false;
        btnInicio.setTextFill(Color.web("#ffffff"));
        btnVoluntarios.setTextFill(Color.web("#ffffff"));
        btnInformes.setTextFill(Color.web("#f8a6a6"));
        btnParroquias.setTextFill(Color.web("#ffffff"));
        btnEquipos.setTextFill(Color.web("#ffffff"));
        btnProgramas.setTextFill(Color.web("#ffffff"));
        loadPage("/vista/VistaInformes");
    }

    @FXML
    private void parroquias(MouseEvent event) {
        clickInicio = false;
        clickVoluntarios = false;
        clickInformes = false;
        clickParroquias = true;
        clickEquipos = false;
        clickProgramas = false;
        btnInicio.setTextFill(Color.web("#ffffff"));
        btnVoluntarios.setTextFill(Color.web("#ffffff"));
        btnInformes.setTextFill(Color.web("#ffffff"));
        btnParroquias.setTextFill(Color.web("#f8a6a6"));
        btnEquipos.setTextFill(Color.web("#ffffff"));
        btnProgramas.setTextFill(Color.web("#ffffff"));
        loadPage("/vista/VistaParroquias");
    }

    @FXML
    private void equipos(MouseEvent event) {
        clickInicio = false;
        clickVoluntarios = false;
        clickInformes = false;
        clickParroquias = false;
        clickEquipos = true;
        clickProgramas = false;
        btnInicio.setTextFill(Color.web("#ffffff"));
        btnVoluntarios.setTextFill(Color.web("#ffffff"));
        btnInformes.setTextFill(Color.web("#ffffff"));
        btnParroquias.setTextFill(Color.web("#ffffff"));
        btnEquipos.setTextFill(Color.web("#f8a6a6"));
        btnProgramas.setTextFill(Color.web("#ffffff"));
        loadPage("/vista/VistaEquipos");
    }

    @FXML
    private void programas(MouseEvent event) {
        clickInicio = false;
        clickVoluntarios = false;
        clickInformes = false;
        clickParroquias = false;
        clickEquipos = false;
        clickProgramas = true;
        btnInicio.setTextFill(Color.web("#ffffff"));
        btnVoluntarios.setTextFill(Color.web("#ffffff"));
        btnInformes.setTextFill(Color.web("#ffffff"));
        btnParroquias.setTextFill(Color.web("#ffffff"));
        btnEquipos.setTextFill(Color.web("#ffffff"));
        btnProgramas.setTextFill(Color.web("#f8a6a6"));
        loadPage("/vista/VistaProgramas");
    }

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ControladorMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }

    // Al pasar el raton por Inicio cambia color de letra
    @FXML
    private void cambiarColorExitInicio(MouseEvent event) {
        if (!clickInicio) {
            btnInicio.setTextFill(Color.web("#ffffff"));
        }
    }

    @FXML
    private void cambiarColorIntroInicio(MouseEvent event) {
        btnInicio.setTextFill(Color.web("#f8a6a6"));
    }

    // Al pasar el raton por Voluntarios cambia color de letra
    @FXML
    private void cambiarColorExitVoluntarios(MouseEvent event) {
        if (!clickVoluntarios) {
            btnVoluntarios.setTextFill(Color.web("#ffffff"));
        }
    }

    @FXML
    private void cambiarColorIntroVoluntarios(MouseEvent event) {
        btnVoluntarios.setTextFill(Color.web("#f8a6a6"));
    }

    // Al pasar el raton por Informes cambia color de letra
    @FXML
    private void cambiarColorExitInformes(MouseEvent event) {
        if (!clickInformes) {
            btnInformes.setTextFill(Color.web("#ffffff"));
        }
    }

    @FXML
    private void cambiarColorIntroInformes(MouseEvent event) {
        btnInformes.setTextFill(Color.web("#f8a6a6"));
    }

    // Al pasar el raton por Parroquias cambia color de letra
    @FXML
    private void cambiarColorExitParroquias(MouseEvent event) {
        if (!clickParroquias) {
            btnParroquias.setTextFill(Color.web("#ffffff"));
        }
    }

    @FXML
    private void cambiarColorIntroParroquias(MouseEvent event) {
        btnParroquias.setTextFill(Color.web("#f8a6a6"));
    }

    // Al pasar el raton por Equipos cambia color de letra
    @FXML
    private void cambiarColorExitEquipos(MouseEvent event) {
        if (!clickEquipos) {
            btnEquipos.setTextFill(Color.web("#ffffff"));
        }
    }

    @FXML
    private void cambiarColorIntroEquipos(MouseEvent event) {
        btnEquipos.setTextFill(Color.web("#f8a6a6"));
    }

    @FXML
    private void cambiarColorExitProgramas(MouseEvent event) {
        if (!clickProgramas) {
            btnProgramas.setTextFill(Color.web("#ffffff"));
        }
    }

    @FXML
    private void cambiarColorIntroProgramas(MouseEvent event) {
        btnProgramas.setTextFill(Color.web("#f8a6a6"));
    }

}
