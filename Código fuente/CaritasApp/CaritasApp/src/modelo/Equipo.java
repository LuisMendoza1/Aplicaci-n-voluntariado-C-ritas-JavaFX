/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Luis Mendoza
 */
public class Equipo {

    public int id;

    public String nombre;
    
    public String zona;
    
    public String poblacion;
    
    public String direccion;
    
    public String codigo_postal;
    
    public String telefono;
    
    public String provincia;

    //Constructor con id
    public Equipo(int id, String nombre, String zona, String poblacion, String direccion, String codigo_postal, String telefono, String provincia) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
        this.poblacion = poblacion;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.telefono = telefono;
        this.provincia = provincia;
    }

    //Constructor sin id
    public Equipo(String nombre, String zona, String poblacion, String direccion, String codigo_postal, String telefono, String provincia) {
        this.nombre = nombre;
        this.zona = zona;
        this.poblacion = poblacion;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.telefono = telefono;
        this.provincia = provincia;
    }

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

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
}
