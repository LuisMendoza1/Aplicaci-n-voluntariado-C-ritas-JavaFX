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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * FXML Controller class
 *
 * @author Luis Mendoza
 */
public class ControladorModificarEntrevista implements Initializable {

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
    @FXML
    private RadioButton sexoHombre;
    @FXML
    private RadioButton sexoMujer;

    final FileChooser fileChooser = new FileChooser();

    private URL rutaFoto;

    private Image imgFoto;

    private File fileFoto;

    private FileInputStream fis;

    private String tieneFoto;

    private int idEntrevistado;

    private String dniPredefinido;

    private Image image;

    private Boolean cambiaFoto;

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
        cambiaFoto = false;
    }

    public void modificarDatosPersonales(int idModificacion, String dniModificacion, String nombreModificacion, String sexoModificacion, String nacimientoModificacion, String tel1Modificacion,
            String tel2Modificacion, String emailModificacion, String direccionModificacion, String codigoModificacion, String poblacionModificacion, String provinciaModificacion, String nacionalidadModificacion,
            String estadoModificacion, String fechaentrevistaModificacion, String obervacionesModificacion, Blob fotoModificacion, String tienefotoModificacion, String esvoluntarioModificacion) {
        idEntrevistado = idModificacion;
        dniPredefinido = dniModificacion;
        dniEntrevista.setText(dniModificacion);
        nombreEntrevista.setText(nombreModificacion);
        if (sexoModificacion.equals("H")) {
            sexoHombre.setSelected(true);
            sexoMujer.setSelected(false);
        }
        if (sexoModificacion.equals("M")) {
            sexoHombre.setSelected(false);
            sexoMujer.setSelected(true);
        }
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date fechaNac = new Date();
        try {
            if (!nacimientoModificacion.equals("")) {
                fechaNac = format.parse(nacimientoModificacion);
                nacimientoEntrevista.setValue(fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
        } catch (ParseException ex) {
            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
        telefono1Entrevista.setText(tel1Modificacion);
        telefono2Entrevista.setText(tel2Modificacion);
        emailEntrevista.setText(emailModificacion);
        direccionEntrevista.setText(direccionModificacion);
        codigoEntrevista.setText(codigoModificacion);
        if (poblacionModificacion != null) {
            poblacionEntrevista.setPromptText(poblacionModificacion);
            poblacionEntrevista.setValue(poblacionModificacion);
        }
        if (provinciaModificacion != null) {
            provinciaEntrevista.setPromptText(provinciaModificacion);
            provinciaEntrevista.setValue(provinciaModificacion);
        }
        nacionalidadEntrevista.setText(nacionalidadModificacion);
        if (estadoModificacion != null) {
            estadoEntrevista.setPromptText(estadoModificacion);
            estadoEntrevista.setValue(estadoModificacion);
        }
        Date fechaEntrev = new Date();
        try {
            if (!fechaentrevistaModificacion.equals("")) {
                fechaEntrev = format.parse(fechaentrevistaModificacion);
                fechaEntrevista.setValue(fechaEntrev.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
        } catch (ParseException ex) {
            Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
        observacionesEntrevista.setText(obervacionesModificacion);

        // Obtener imagen de la BD
        try {
            byte[] imageByte = fotoModificacion.getBytes(1, (int) fotoModificacion.length());
            try {
                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByte));
                ImageIcon icono = new ImageIcon(bufferedImage);
                image = SwingFXUtils.toFXImage(bufferedImage, null);
                fondoFoto.setImage(image);
                fileFoto = new File("foto.png");
                fileFoto.createNewFile();
                FileOutputStream fos = new FileOutputStream(fileFoto);
                fos.write(imageByte);
                fos.flush();
                fos.close();

            } catch (IOException ex) {
                Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(tienefotoModificacion.equals("SI")){
             tieneFoto = "1";
        } else{
            tieneFoto = "0";
        }
        esVoluntario = esvoluntarioModificacion;
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
        cambiaFoto = true;
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
        cambiaFoto = true;
        tieneFoto = "0";
    }

    // Boton cancelar para cerrar la ventana
    @FXML
    private void cancelarEntrevista(ActionEvent event
    ) {
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
    private void guardarEntrevista(ActionEvent event
    ) {
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
                Logger.getLogger(ControladorModificarEntrevista.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Si existe el DNI no insertar
            if (vecesDni > 0 && !dniEntrevista.getText().equals(dniPredefinido)) { // si el DNI es el mismo que antes, que deje guardar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Ya existe otro/a entrevistado/a con el mismo DNI");
                alert.showAndWait();
            } else {
                // Actualizar campos a la base de datos
                if (dniEntrevista.getText().toString().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Los campos con '*' son obligatorios");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("¿Desea guardar la entrevista?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        // Actualizar campos en la BD
                        String sql = "UPDATE entrevistado SET dni = ?, nombre = ?, sexo = ?, fecha_nacimiento = ?, telefono1 = ?, telefono2 = ?, email = ?,"
                                + " direccion = ?, codigo_postal = ?, poblacion = ?, provincia = ?, nacionalidad = ?, estado_civil = ?,"
                                + " fecha_entrevista = ?, observaciones = ?, tiene_foto = ? WHERE id = " + idEntrevistado;
                        try {
                            PreparedStatement pst;
                            pst = connection.prepareStatement(sql);
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
                            pst.setString(16, tieneFoto);

                            pst.execute();
                            pst.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ControladorModificarEntrevista.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        // Actualizar foto en BD solo si esta es cambiada
                        if (cambiaFoto) {
                            String sql2 = "UPDATE entrevistado SET foto = ? WHERE id = " + idEntrevistado;
                            try {
                                PreparedStatement pst = connection.prepareStatement(sql2);
                                try {
                                    fis = new FileInputStream(fileFoto);
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(ControladorNuevoVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                pst.setBinaryStream(1, (InputStream) fis, (int) fileFoto.length());

                                pst.execute();
                                pst.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(ControladorModificarVoluntario.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setHeaderText(null);
                        alert1.setContentText("Entrevista guardada con éxito");
                        alert1.showAndWait();

                        //Si ya es voluntario, que no pregunte la incorporación
                        if (esVoluntario != "1") {
                            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                            alert2.setHeaderText(null);
                            alert2.setContentText("¿Desea realizar la ficha de voluntario/a?");
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
                            }
                        }
                        Stage stage = (Stage) btnGuardarEntrevista.getScene().getWindow();
                        stage.close();
                    }
                }
            }
        }
    }
}
