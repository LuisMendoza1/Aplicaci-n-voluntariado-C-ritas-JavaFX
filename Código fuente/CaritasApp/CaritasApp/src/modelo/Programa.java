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
public class Programa {

    public int id;

    public String nombre;

    public Programa(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Programa(String nombre) {
        this.nombre = nombre;
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

   
    
}
