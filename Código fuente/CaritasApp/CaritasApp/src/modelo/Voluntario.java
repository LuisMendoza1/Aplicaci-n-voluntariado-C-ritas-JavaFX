/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author Luis Mendoza
 */
public class Voluntario {

    public int id;

    public String dni;

    public String nombre;

    public String sexo;

    public String fecha_nacimiento;

    public String telefono_1;

    public String telefono_2;

    public String email;

    public String direccion_1;

    public String codigo_postal_1;

    public String poblacion_1;

    public String provincia;

    public String nacionalidad;

    public String estado_civil;

    public String estudios;

    public String lugar_estudios;

    public String observaciones_formacion;

    public String idioma_1;

    public String idioma_2;

    public String idioma_3;

    public String nivel_1;

    public String nivel_2;

    public String nivel_3;

    public String informatica;

    public String situacion_laboral;

    public String carnet;

    public String experiencia_laboral;

    public String fecha_alta;

    public String zona;

    public String poblacion_2;

    public String equipo_1;

    public String direccion_2;

    public String codigo_postal_2;

    public String telefono_3;

    public String cargo_1;

    public String programa_1;

    public String programa_2;

    public String cargo_2;

    public String cargo_3;

    public String observaciones_voluntario;

    public String consejo_diocesano;

    public String ficha_voluntario;

    public String delitos_sexuales;

    public String publicacion_imagen;

    public String acuerdo_colaboracion;

    public String antecedentes_penales;

    public String autorizacion_paterna;

    public String rgpd;

    public String envios_email;

    public String envios_postales;

    public String observaciones_documentacion;

    public String baja_caritas;

    public String fecha_baja;

    public Blob foto;

    public String tiene_foto;

    //Constructor sin id
    public Voluntario(String dni, String nombre, String sexo, String fecha_nacimiento, String telefono_1, String telefono_2, String email, String direccion_1, String codigo_postal_1, String poblacion_1, String provincia, String nacionalidad, String estado_civil, String estudios, String lugar_estudios, String observaciones_formacion, String idioma_1, String idioma_2, String idioma_3, String nivel_1, String nivel_2, String nivel_3, String informatica, String situacion_laboral, String carnet, String experiencia_laboral, String fecha_alta, String zona, String poblacion_2, String equipo_1, String direccion_2, String codigo_postal_2, String telefono_3, String cargo_1, String programa_1, String programa_2, String cargo_2, String cargo_3, String observaciones_voluntario, String consejo_diocesano, String ficha_voluntario, String delitos_sexuales, String publicacion_imagen, String acuerdo_colaboracion, String antecedentes_penales, String autorizacion_paterna, String rgpd, String envios_email, String envios_postales, String observaciones_documentacion, String baja_caritas, String fecha_baja, Blob foto, String tiene_foto) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono_1 = telefono_1;
        this.telefono_2 = telefono_2;
        this.email = email;
        this.direccion_1 = direccion_1;
        this.codigo_postal_1 = codigo_postal_1;
        this.poblacion_1 = poblacion_1;
        this.provincia = provincia;
        this.nacionalidad = nacionalidad;
        this.estado_civil = estado_civil;
        this.estudios = estudios;
        this.lugar_estudios = lugar_estudios;
        this.observaciones_formacion = observaciones_formacion;
        this.idioma_1 = idioma_1;
        this.idioma_2 = idioma_2;
        this.idioma_3 = idioma_3;
        this.nivel_1 = nivel_1;
        this.nivel_2 = nivel_2;
        this.nivel_3 = nivel_3;
        this.informatica = informatica;
        this.situacion_laboral = situacion_laboral;
        this.carnet = carnet;
        this.experiencia_laboral = experiencia_laboral;
        this.fecha_alta = fecha_alta;
        this.zona = zona;
        this.poblacion_2 = poblacion_2;
        this.equipo_1 = equipo_1;
        this.direccion_2 = direccion_2;
        this.codigo_postal_2 = codigo_postal_2;
        this.telefono_3 = telefono_3;
        this.cargo_1 = cargo_1;
        this.programa_1 = programa_1;
        this.programa_2 = programa_2;
        this.cargo_2 = cargo_2;
        this.cargo_3 = cargo_3;
        this.observaciones_voluntario = observaciones_voluntario;
        this.consejo_diocesano = consejo_diocesano;
        this.ficha_voluntario = ficha_voluntario;
        this.delitos_sexuales = delitos_sexuales;
        this.publicacion_imagen = publicacion_imagen;
        this.acuerdo_colaboracion = acuerdo_colaboracion;
        this.antecedentes_penales = antecedentes_penales;
        this.autorizacion_paterna = autorizacion_paterna;
        this.rgpd = rgpd;
        this.envios_email = envios_email;
        this.envios_postales = envios_postales;
        this.observaciones_documentacion = observaciones_documentacion;
        this.baja_caritas = baja_caritas;
        this.fecha_baja = fecha_baja;
        this.foto = foto;
        this.tiene_foto = tiene_foto;
    }

    //Constructor con id
    public Voluntario(int id, String dni, String nombre, String sexo, String fecha_nacimiento, String telefono_1, String telefono_2, String email, String direccion_1, String codigo_postal_1, String poblacion_1, String provincia, String nacionalidad, String estado_civil, String estudios, String lugar_estudios, String observaciones_formacion, String idioma_1, String idioma_2, String idioma_3, String nivel_1, String nivel_2, String nivel_3, String informatica, String situacion_laboral, String carnet, String experiencia_laboral, String fecha_alta, String zona, String poblacion_2, String equipo_1, String direccion_2, String codigo_postal_2, String telefono_3, String cargo_1, String programa_1, String programa_2, String cargo_2, String cargo_3, String observaciones_voluntario, String consejo_diocesano, String ficha_voluntario, String delitos_sexuales, String publicacion_imagen, String acuerdo_colaboracion, String antecedentes_penales, String autorizacion_paterna, String rgpd, String envios_email, String envios_postales, String observaciones_documentacion, String baja_caritas, String fecha_baja, Blob foto, String tiene_foto) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono_1 = telefono_1;
        this.telefono_2 = telefono_2;
        this.email = email;
        this.direccion_1 = direccion_1;
        this.codigo_postal_1 = codigo_postal_1;
        this.poblacion_1 = poblacion_1;
        this.provincia = provincia;
        this.nacionalidad = nacionalidad;
        this.estado_civil = estado_civil;
        this.estudios = estudios;
        this.lugar_estudios = lugar_estudios;
        this.observaciones_formacion = observaciones_formacion;
        this.idioma_1 = idioma_1;
        this.idioma_2 = idioma_2;
        this.idioma_3 = idioma_3;
        this.nivel_1 = nivel_1;
        this.nivel_2 = nivel_2;
        this.nivel_3 = nivel_3;
        this.informatica = informatica;
        this.situacion_laboral = situacion_laboral;
        this.carnet = carnet;
        this.experiencia_laboral = experiencia_laboral;
        this.fecha_alta = fecha_alta;
        this.zona = zona;
        this.poblacion_2 = poblacion_2;
        this.equipo_1 = equipo_1;
        this.direccion_2 = direccion_2;
        this.codigo_postal_2 = codigo_postal_2;
        this.telefono_3 = telefono_3;
        this.cargo_1 = cargo_1;
        this.programa_1 = programa_1;
        this.programa_2 = programa_2;
        this.cargo_2 = cargo_2;
        this.cargo_3 = cargo_3;
        this.observaciones_voluntario = observaciones_voluntario;
        this.consejo_diocesano = consejo_diocesano;
        this.ficha_voluntario = ficha_voluntario;
        this.delitos_sexuales = delitos_sexuales;
        this.publicacion_imagen = publicacion_imagen;
        this.acuerdo_colaboracion = acuerdo_colaboracion;
        this.antecedentes_penales = antecedentes_penales;
        this.autorizacion_paterna = autorizacion_paterna;
        this.rgpd = rgpd;
        this.envios_email = envios_email;
        this.envios_postales = envios_postales;
        this.observaciones_documentacion = observaciones_documentacion;
        this.baja_caritas = baja_caritas;
        this.fecha_baja = fecha_baja;
        this.foto = foto;
        this.tiene_foto = tiene_foto;
    }
    
    //Constructor sin id ni foto
    public Voluntario(String dni, String nombre, String sexo, String fecha_nacimiento, String telefono_1, String telefono_2, String email, String direccion_1, String codigo_postal_1, String poblacion_1, String provincia, String nacionalidad, String estado_civil, String estudios, String lugar_estudios, String observaciones_formacion, String idioma_1, String idioma_2, String idioma_3, String nivel_1, String nivel_2, String nivel_3, String informatica, String situacion_laboral, String carnet, String experiencia_laboral, String fecha_alta, String zona, String poblacion_2, String equipo_1, String direccion_2, String codigo_postal_2, String telefono_3, String cargo_1, String programa_1, String programa_2, String cargo_2, String cargo_3, String observaciones_voluntario, String consejo_diocesano, String ficha_voluntario, String delitos_sexuales, String publicacion_imagen, String acuerdo_colaboracion, String antecedentes_penales, String autorizacion_paterna, String rgpd, String envios_email, String envios_postales, String observaciones_documentacion, String baja_caritas, String fecha_baja, String tiene_foto) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono_1 = telefono_1;
        this.telefono_2 = telefono_2;
        this.email = email;
        this.direccion_1 = direccion_1;
        this.codigo_postal_1 = codigo_postal_1;
        this.poblacion_1 = poblacion_1;
        this.provincia = provincia;
        this.nacionalidad = nacionalidad;
        this.estado_civil = estado_civil;
        this.estudios = estudios;
        this.lugar_estudios = lugar_estudios;
        this.observaciones_formacion = observaciones_formacion;
        this.idioma_1 = idioma_1;
        this.idioma_2 = idioma_2;
        this.idioma_3 = idioma_3;
        this.nivel_1 = nivel_1;
        this.nivel_2 = nivel_2;
        this.nivel_3 = nivel_3;
        this.informatica = informatica;
        this.situacion_laboral = situacion_laboral;
        this.carnet = carnet;
        this.experiencia_laboral = experiencia_laboral;
        this.fecha_alta = fecha_alta;
        this.zona = zona;
        this.poblacion_2 = poblacion_2;
        this.equipo_1 = equipo_1;
        this.direccion_2 = direccion_2;
        this.codigo_postal_2 = codigo_postal_2;
        this.telefono_3 = telefono_3;
        this.cargo_1 = cargo_1;
        this.programa_1 = programa_1;
        this.programa_2 = programa_2;
        this.cargo_2 = cargo_2;
        this.cargo_3 = cargo_3;
        this.observaciones_voluntario = observaciones_voluntario;
        this.consejo_diocesano = consejo_diocesano;
        this.ficha_voluntario = ficha_voluntario;
        this.delitos_sexuales = delitos_sexuales;
        this.publicacion_imagen = publicacion_imagen;
        this.acuerdo_colaboracion = acuerdo_colaboracion;
        this.antecedentes_penales = antecedentes_penales;
        this.autorizacion_paterna = autorizacion_paterna;
        this.rgpd = rgpd;
        this.envios_email = envios_email;
        this.envios_postales = envios_postales;
        this.observaciones_documentacion = observaciones_documentacion;
        this.baja_caritas = baja_caritas;
        this.fecha_baja = fecha_baja;
        this.tiene_foto = tiene_foto;
    }

    public Voluntario(String nombre, String telefono_1, String telefono_2, String email, String direccion_1, String codigo_postal_1, String cargo_1) {
        this.nombre = nombre;
        this.telefono_1 = telefono_1;
        this.telefono_2 = telefono_2;
        this.email = email;
        this.direccion_1 = direccion_1;
        this.codigo_postal_1 = codigo_postal_1;
        this.cargo_1 = cargo_1;
    }
               
    //Gets and Sets
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getTelefono_1() {
        return telefono_1;
    }

    public void setTelefono_1(String telefono_1) {
        this.telefono_1 = telefono_1;
    }

    public String getTelefono_2() {
        return telefono_2;
    }

    public void setTelefono_2(String telefono_2) {
        this.telefono_2 = telefono_2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion_1() {
        return direccion_1;
    }

    public void setDireccion_1(String direccion_1) {
        this.direccion_1 = direccion_1;
    }

    public String getCodigo_postal_1() {
        return codigo_postal_1;
    }

    public void setCodigo_postal_1(String codigo_postal_1) {
        this.codigo_postal_1 = codigo_postal_1;
    }

    public String getPoblacion_1() {
        return poblacion_1;
    }

    public void setPoblacion_1(String poblacion_1) {
        this.poblacion_1 = poblacion_1;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getLugar_estudios() {
        return lugar_estudios;
    }

    public void setLugar_estudios(String lugar_estudios) {
        this.lugar_estudios = lugar_estudios;
    }

    public String getObservaciones_formacion() {
        return observaciones_formacion;
    }

    public void setObservaciones_formacion(String observaciones_formacion) {
        this.observaciones_formacion = observaciones_formacion;
    }

    public String getIdioma_1() {
        return idioma_1;
    }

    public void setIdioma_1(String idioma_1) {
        this.idioma_1 = idioma_1;
    }

    public String getIdioma_2() {
        return idioma_2;
    }

    public void setIdioma_2(String idioma_2) {
        this.idioma_2 = idioma_2;
    }

    public String getIdioma_3() {
        return idioma_3;
    }

    public void setIdioma_3(String idioma_3) {
        this.idioma_3 = idioma_3;
    }

    public String getNivel_1() {
        return nivel_1;
    }

    public void setNivel_1(String nivel_1) {
        this.nivel_1 = nivel_1;
    }

    public String getNivel_2() {
        return nivel_2;
    }

    public void setNivel_2(String nivel_2) {
        this.nivel_2 = nivel_2;
    }

    public String getNivel_3() {
        return nivel_3;
    }

    public void setNivel_3(String nivel_3) {
        this.nivel_3 = nivel_3;
    }

    public String getInformatica() {
        return informatica;
    }

    public void setInformatica(String informatica) {
        this.informatica = informatica;
    }

    public String getSituacion_laboral() {
        return situacion_laboral;
    }

    public void setSituacion_laboral(String situacion_laboral) {
        this.situacion_laboral = situacion_laboral;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getExperiencia_laboral() {
        return experiencia_laboral;
    }

    public void setExperiencia_laboral(String experiencia_laboral) {
        this.experiencia_laboral = experiencia_laboral;
    }

    public String getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getPoblacion_2() {
        return poblacion_2;
    }

    public void setPoblacion_2(String poblacion_2) {
        this.poblacion_2 = poblacion_2;
    }

    public String getEquipo_1() {
        return equipo_1;
    }

    public void setEquipo_1(String equipo_1) {
        this.equipo_1 = equipo_1;
    }

    public String getDireccion_2() {
        return direccion_2;
    }

    public void setDireccion_2(String direccion_2) {
        this.direccion_2 = direccion_2;
    }

    public String getCodigo_postal_2() {
        return codigo_postal_2;
    }

    public void setCodigo_postal_2(String codigo_postal_2) {
        this.codigo_postal_2 = codigo_postal_2;
    }

    public String getTelefono_3() {
        return telefono_3;
    }

    public void setTelefono_3(String telefono_3) {
        this.telefono_3 = telefono_3;
    }

    public String getCargo_1() {
        return cargo_1;
    }

    public void setCargo_1(String cargo_1) {
        this.cargo_1 = cargo_1;
    }

    public String getPrograma_1() {
        return programa_1;
    }

    public void setPrograma_1(String programa_1) {
        this.programa_1 = programa_1;
    }

    public String getPrograma_2() {
        return programa_2;
    }

    public void setPrograma_2(String programa_2) {
        this.programa_2 = programa_2;
    }

    public String getCargo_2() {
        return cargo_2;
    }

    public void setCargo_2(String cargo_2) {
        this.cargo_2 = cargo_2;
    }

    public String getCargo_3() {
        return cargo_3;
    }

    public void setCargo_3(String cargo_3) {
        this.cargo_3 = cargo_3;
    }

    public String getObservaciones_voluntario() {
        return observaciones_voluntario;
    }

    public void setObservaciones_voluntario(String observaciones_voluntario) {
        this.observaciones_voluntario = observaciones_voluntario;
    }

    public String getConsejo_diocesano() {
        return consejo_diocesano;
    }

    public void setConsejo_diocesano(String consejo_diocesano) {
        this.consejo_diocesano = consejo_diocesano;
    }

    public String getFicha_voluntario() {
        return ficha_voluntario;
    }

    public void setFicha_voluntario(String ficha_voluntario) {
        this.ficha_voluntario = ficha_voluntario;
    }

    public String getDelitos_sexuales() {
        return delitos_sexuales;
    }

    public void setDelitos_sexuales(String delitos_sexuales) {
        this.delitos_sexuales = delitos_sexuales;
    }

    public String getPublicacion_imagen() {
        return publicacion_imagen;
    }

    public void setPublicacion_imagen(String publicacion_imagen) {
        this.publicacion_imagen = publicacion_imagen;
    }

    public String getAcuerdo_colaboracion() {
        return acuerdo_colaboracion;
    }

    public void setAcuerdo_colaboracion(String acuerdo_colaboracion) {
        this.acuerdo_colaboracion = acuerdo_colaboracion;
    }

    public String getAntecedentes_penales() {
        return antecedentes_penales;
    }

    public void setAntecedentes_penales(String antecedentes_penales) {
        this.antecedentes_penales = antecedentes_penales;
    }

    public String getAutorizacion_paterna() {
        return autorizacion_paterna;
    }

    public void setAutorizacion_paterna(String autorizacion_paterna) {
        this.autorizacion_paterna = autorizacion_paterna;
    }

    public String getRgpd() {
        return rgpd;
    }

    public void setRgpd(String rgpd) {
        this.rgpd = rgpd;
    }

    public String getEnvios_email() {
        return envios_email;
    }

    public void setEnvios_email(String envios_email) {
        this.envios_email = envios_email;
    }

    public String getEnvios_postales() {
        return envios_postales;
    }

    public void setEnvios_postales(String envios_postales) {
        this.envios_postales = envios_postales;
    }

    public String getObservaciones_documentacion() {
        return observaciones_documentacion;
    }

    public void setObservaciones_documentacion(String observaciones_documentacion) {
        this.observaciones_documentacion = observaciones_documentacion;
    }

    public String getBaja_caritas() {
        return baja_caritas;
    }

    public void setBaja_caritas(String baja_caritas) {
        this.baja_caritas = baja_caritas;
    }

    public String getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(String fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public String getTiene_foto() {
        return tiene_foto;
    }

    public void setTiene_foto(String tiene_foto) {
        this.tiene_foto = tiene_foto;
    }

}
