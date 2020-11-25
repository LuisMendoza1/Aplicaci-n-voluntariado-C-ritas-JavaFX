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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorEntrevista implements Initializable {

    @FXML
    private ToggleGroup sexo;
    @FXML
    private TextField nombreEntrevista;
    @FXML
    private TextField dniEntrevista;
    @FXML
    private DatePicker nacimientoEntrevista;
    @FXML
    private TextField telefono1Entrevista;
    @FXML
    private TextField telefono2Entrevista;
    @FXML
    private TextField emailEntrevista;
    @FXML
    private TextField direccionEntrevista;
    @FXML
    private TextField codigoEntrevista;
    @FXML
    private ComboBox<String> poblacionEntrevista;
    @FXML
    private ComboBox<String> provinciaEntrevista;
    @FXML
    private TextField nacionalidadEntrevista;
    @FXML
    private ComboBox<String> estadoEntrevista;
    @FXML
    private DatePicker fechaEntrevista;
    @FXML
    private TextArea observacionesEntrevista;
    @FXML
    private Button btnGuardarEntrevista;
    @FXML
    private Button btnCancelarEntrevista;
    @FXML
    private Button btnSubirFoto;
    @FXML
    private Button btnEliminarFoto;
    @FXML
    private ImageView fondoFoto;

    final FileChooser fileChooser = new FileChooser();

    private URL rutaFoto;

    private Image imgFoto;

    private File fileFoto;

    private FileInputStream fis;

    private String tieneFoto;

    private String esVoluntario;

    ObservableList<String> listaPoblaciones = FXCollections.observableArrayList();

    ObservableList<String> listaProvincias = FXCollections.observableArrayList();

    ObservableList<String> listaEstado = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            ResultSet rs1 = connection.createStatement().executeQuery("SELECT * FROM poblaciones");
            ResultSet rs2 = connection.createStatement().executeQuery("SELECT * FROM provincias");
            ResultSet rs3 = connection.createStatement().executeQuery("SELECT * FROM estado_civil");
            while (rs1.next()) {
                listaPoblaciones.add((rs1.getString("nombre")));
            }
            while (rs2.next()) {
                listaProvincias.add((rs2.getString("nombre")));
            }
            while (rs3.next()) {
                listaEstado.add((rs3.getString("estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }

        poblacionEntrevista.setItems(listaPoblaciones);
        provinciaEntrevista.setItems(listaProvincias);
        estadoEntrevista.setItems(listaEstado);

        rutaFoto = getClass().getResource("/img/imageSinFoto.png");
        imgFoto = new Image(rutaFoto.toString(), 145, 145, false, true);
        fondoFoto.setImage(imgFoto);
        BufferedImage imgAux;
        try {
            imgAux = ImageIO.read(rutaFoto);
            fileFoto = new File("imageSinFoto.png");
            ImageIO.write(imgAux, "png", fileFoto);
        } catch (IOException ex) {
            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
        tieneFoto = "0";
        esVoluntario = "0";
    }

    @FXML
    private void subirFoto(ActionEvent event) {
        Stage stage = (Stage) btnSubirFoto.getScene().getWindow();
        fileFoto = fileChooser.showOpenDialog(stage);
        if (fileFoto.isFile()
                && (fileFoto.getName().contains(".jpg") || fileFoto.getName().contains(".png")
                || fileFoto.getName().contains(".bmp") || fileFoto.getName().contains(".gif"))) {
            try {
                String URLimage = fileFoto.toURI().toURL().toString();
                imgFoto = new Image(URLimage);
                fondoFoto.setImage(imgFoto);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tieneFoto = "1";
    }

    @FXML
    private void eliminarFoto(ActionEvent event) {
        Stage stage = (Stage) btnEliminarFoto.getScene().getWindow();
        rutaFoto = getClass().getResource("/img/imageSinFoto.png");
        imgFoto = new Image(rutaFoto.toString(), 145, 145, false, true);
        fondoFoto.setImage(imgFoto);
        BufferedImage imgAux;
        try {
            imgAux = ImageIO.read(rutaFoto);
            fileFoto = new File("imageSinFoto.png");
            ImageIO.write(imgAux, "png", fileFoto);
        } catch (IOException ex) {
            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
        tieneFoto = "0";
    }

    // Boton cancelar para cerrar la ventana
    @FXML
    private void cancelarEntrevista(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar la entrevista?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancelarEntrevista.getScene().getWindow();
            stage.close();
        }
    }

    // Boton siguiente para preguntar si desea incorporar al voluntario
    @FXML
    private void guardarEntrevista(ActionEvent event) {
        if (dniEntrevista.getText().toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Los campos con '*' son obligatorios");
            alert.showAndWait();
        } else {
            // Conectar a la base de datos
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            //Comprobar datos nulos
            String sex = "";
            RadioButton sexoEntrevista = null;
            if ((RadioButton) sexo.getSelectedToggle() != null) {
                sexoEntrevista = (RadioButton) sexo.getSelectedToggle();
                sex = sexoEntrevista.getText();
            }
            String fNacimiento = "";
            if (nacimientoEntrevista.getValue() != null) {
                fNacimiento = nacimientoEntrevista.getValue().toString().replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1");
            }
            String poblacion = "";
            if (poblacionEntrevista.getValue() != null) {
                poblacion = poblacionEntrevista.getValue().toString();
            }
            String provincia = "";
            if (provinciaEntrevista.getValue() != null) {
                provincia = provinciaEntrevista.getValue().toString();
            }
            String estado = "";
            if (estadoEntrevista.getValue() != null) {
                estado = estadoEntrevista.getValue().toString();
            }
            String fEntrevista = "";
            if (fechaEntrevista.getValue() != null) {
                fEntrevista = fechaEntrevista.getValue().toString().replaceAll("(\\d+)-(\\d+)-(\\d+)", "$3/$2/$1");
            }

            // Comprobar DNI duplicado
            int vecesDni = 0;
            String sql1 = "SELECT COUNT(*) FROM Entrevistado WHERE dni = '" + dniEntrevista.getText() + "'";
            Statement statement1;
            try {
                statement1 = connection.createStatement();
                ResultSet result = statement1.executeQuery(sql1);
                while (result.next()) {
                    vecesDni = result.getInt("COUNT(*)");
                }
                result.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorEntrevista.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Si existe el DNI no insertar
            if (vecesDni > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("El/la entrevistado/a ya existe");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("¿Desea guardar la entrevista?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    // Insertar campos a la BD
                    String sql = "INSERT IGNORE INTO Entrevistado (dni,nombre,sexo,fecha_nacimiento,telefono1,telefono2,email,direccion,codigo_postal,poblacion,provincia,nacionalidad,estado_civil,fecha_entrevista,observaciones,foto,tiene_foto,es_voluntario) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pst;
                    try {
                        pst = connection.prepareStatement(sql);

                        try {
                            pst.setString(1, dniEntrevista.getText());
                            pst.setString(2, nombreEntrevista.getText());
                            pst.setString(3, sex);
                            pst.setString(4, fNacimiento);
                            pst.setString(5, telefono1Entrevista.getText());
                            pst.setString(6, telefono2Entrevista.getText());
                            pst.setString(7, emailEntrevista.getText());
                            pst.setString(8, direccionEntrevista.getText());
                            pst.setString(9, codigoEntrevista.getText());
                            pst.setString(10, poblacion);
                            pst.setString(11, provincia);
                            pst.setString(12, nacionalidadEntrevista.getText());
                            pst.setString(13, estado);
                            pst.setString(14, fEntrevista);
                            pst.setString(15, observacionesEntrevista.getText());
                            try {
                                fis = new FileInputStream(fileFoto);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            pst.setBinaryStream(16, (InputStream) fis, (int) fileFoto.length());
                            pst.setString(17, tieneFoto);
                            pst.setString(18, esVoluntario);
                            pst.execute();
                            pst.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ControladorEntrevista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorEntrevista.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setHeaderText(null);
                    alert1.setContentText("Entrevista guardada con éxito");
                    alert1.showAndWait();

                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setHeaderText(null);
                    alert2.setContentText("¿Desea realizar la ficha de voluntario?");
                    Optional<ButtonType> result2 = alert2.showAndWait();
                    if (result2.get() == ButtonType.OK) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaNuevoVoluntario.fxml"));
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.getIcons().add(new Image("/img/imageAPP.png"));
                            stage.setTitle("Cáritas Diocesana Albacete");
                            stage.setScene(scene);

                            //Enviar datos al ControladorNuevoVoluntario
                            ControladorNuevoVoluntario controladorNuevoVoluntario = loader.getController();
                            controladorNuevoVoluntario.inicializarDatosPersonales(dniEntrevista.getText(), nombreEntrevista.getText(), sex, fNacimiento, telefono1Entrevista.getText(),
                                    telefono2Entrevista.getText(), emailEntrevista.getText(), direccionEntrevista.getText(),
                                    codigoEntrevista.getText(), poblacion, provincia, nacionalidadEntrevista.getText(), estado, fileFoto, tieneFoto);

                            stage.showAndWait();
                            Stage stage1 = (Stage) btnGuardarEntrevista.getScene().getWindow();
                            stage1.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ControladorVoluntarios.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        Stage stage = (Stage) btnGuardarEntrevista.getScene().getWindow();
                        stage.close();
                    }
                }
            }
        }
    }
}
