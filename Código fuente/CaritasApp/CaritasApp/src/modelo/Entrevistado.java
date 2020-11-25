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
public class Entrevistado {

    public int id;

    public String dni;

    public String nombre;

    public String sexo;

    public String fecha_nacimiento;

    public String telefono1;

    public String telefono2;

    public String email;

    public String direccion;

    public String codigo_postal;

    public String poblacion;

    public String provincia;

    public String nacionalidad;

    public String estado_civil;

    public String fecha_entrevista;

    public String observaciones;

    public Blob foto;

    public String tiene_foto;
    
    public String es_voluntario;

    //Constructor sin id
    public Entrevistado(String dni, String nombre, String sexo, String fecha_nacimiento, String telefono1, String telefono2, String email, String direccion, String codigo_postal, String poblacion, String provincia, String nacionalidad, String estado_civil, String fecha_entrevista, String observaciones, Blob foto, String tiene_foto, String es_voluntario) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.email = email;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.nacionalidad = nacionalidad;
        this.estado_civil = estado_civil;
        this.fecha_entrevista = fecha_entrevista;
        this.observaciones = observaciones;
        this.foto = foto;
        this.tiene_foto = tiene_foto;
        this.es_voluntario = es_voluntario;
    }
    
    //Constructor sin id y sin foto
    public Entrevistado(String dni, String nombre, String sexo, String fecha_nacimiento, String telefono1, String telefono2, String email, String direccion, String codigo_postal, String poblacion, String provincia, String nacionalidad, String estado_civil, String fecha_entrevista, String observaciones, String tiene_foto, String es_voluntario) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.email = email;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.nacionalidad = nacionalidad;
        this.estado_civil = estado_civil;
        this.fecha_entrevista = fecha_entrevista;
        this.observaciones = observaciones;
        this.tiene_foto = tiene_foto;
        this.es_voluntario = es_voluntario;
    }
    

    //Constructor con id
    public Entrevistado(int id, String dni, String nombre, String sexo, String fecha_nacimiento, String telefono1, String telefono2, String email, String direccion, String codigo_postal, String poblacion, String provincia, String nacionalidad, String estado_civil, String fecha_entrevista, String observaciones, Blob foto, String tiene_foto, String es_voluntario) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.email = email;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.nacionalidad = nacionalidad;
        this.estado_civil = estado_civil;
        this.fecha_entrevista = fecha_entrevista;
        this.observaciones = observaciones;
        this.foto = foto;
        this.tiene_foto = tiene_foto;
        this.es_voluntario = es_voluntario;
    }

    //Gets and Sets
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
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

    public String getFecha_entrevista() {
        return fecha_entrevista;
    }

    public void setFecha_entrevista(String fecha_entrevista) {
        this.fecha_entrevista = fecha_entrevista;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public String getEs_voluntario() {
        return es_voluntario;
    }

    public void setEs_voluntario(String es_voluntario) {
        this.es_voluntario = es_voluntario;
    }
    
}
