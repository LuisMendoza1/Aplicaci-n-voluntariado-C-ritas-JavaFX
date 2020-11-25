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
public class Parroquia {

    public int id;

    public String nombre;

    public String parroco;

    public String zona;

    public String poblacion;

    public String direccion;

    public String codigo_postal;

    public String provincia;

    public String carteles;

    public String tripticos;

    public String sobres;

    public String unidades_didacticas;

    
    //Constructor con id
    public Parroquia(int id, String nombre, String parroco, String zona, String poblacion, String direccion, String codigo_postal, String provincia, String carteles, String tripticos, String sobres, String unidades_didacticas) {
        this.id = id;
        this.nombre = nombre;
        this.parroco = parroco;
        this.zona = zona;
        this.poblacion = poblacion;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.provincia = provincia;
        this.carteles = carteles;
        this.tripticos = tripticos;
        this.sobres = sobres;
        this.unidades_didacticas = unidades_didacticas;
    }

    //Constructor sin id
    public Parroquia(String nombre, String parroco, String zona, String poblacion, String direccion, String codigo_postal, String provincia, String carteles, String tripticos, String sobres, String unidades_didacticas) {
        this.nombre = nombre;
        this.parroco = parroco;
        this.zona = zona;
        this.poblacion = poblacion;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.provincia = provincia;
        this.carteles = carteles;
        this.tripticos = tripticos;
        this.sobres = sobres;
        this.unidades_didacticas = unidades_didacticas;
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

    public String getParroco() {
        return parroco;
    }

    public void setParroco(String parroco) {
        this.parroco = parroco;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCarteles() {
        return carteles;
    }

    public void setCarteles(String carteles) {
        this.carteles = carteles;
    }

    public String getTripticos() {
        return tripticos;
    }

    public void setTripticos(String tripticos) {
        this.tripticos = tripticos;
    }

    public String getSobres() {
        return sobres;
    }

    public void setSobres(String sobres) {
        this.sobres = sobres;
    }

    public String getUnidades_didacticas() {
        return unidades_didacticas;
    }

    public void setUnidades_didacticas(String unidades_didacticas) {
        this.unidades_didacticas = unidades_didacticas;
    }
    
    
    
}
