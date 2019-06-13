/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;


import Entidades.*;

/**
 * Son objetos que contienen entidades.
 * @author razvanvc
 */
public class Casilla {
    
    //Atributos
    private Entidad contenido;
    private boolean ocupado;

    //Constructor
    public Casilla(Vacio vacio) {
    this.contenido = new Vacio();
    this.ocupado = false;
    }

    public Casilla(Entidad e) {
    this.contenido = e;
    this.ocupado = true;
    }
    
    //Métodos
    
    //Getters
    /**
     * Get the value of Contenido
     *
     * @return the value of Contenido
     */
    public Entidad getContenido() {
        return contenido;
    }
    
    //Setters
    /**
     * Set the value of ocupado
     *
     * @param ocupado new value of ocupado
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    /**
     * Set the value of Contenido
     *
     * @param Contenido new value of Contenido
     */
    public void setContenido(Entidad Contenido) {
        this.contenido = Contenido;
    }
    
    //Otros
    /**
     * Devuelve si esta ocupada la casilla
     * 
     * @return ocupado
     */
    public boolean isOcupado() {
        return ocupado;
    }
    @Override
    public String toString() {
        
        return contenido.toString();
    }
}