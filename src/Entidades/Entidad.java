/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author DanielGG
 */
public abstract class Entidad {

    //Atributos
    private final int daño;
    public int resistencia;
    public int ciclos;
    public int posColumna;
    public int posFila;
    
    //Constructor
    public Entidad(int daño, int resistencia) {
        this.daño = daño;
        this.resistencia = resistencia;
    }
    
    //Métodos
    
    //Getters
    /**
     * Get the value of resistencia
     *
     * @return the value of resistencia
     */
    public int getResistencia() {
        return resistencia;
    }
    /**
     * Get the value of daño
     *
     * @return the value of daño
     */
    public int getDaño() {
        return daño;
    }
    /**
     * Get the value of ciclos
     *
     * @return the value of ciclos
     */
    public int getCiclos() {
        return ciclos;
    }
    /**
     * Get the value of posColumna
     *
     * @return the value of posColumna
     */
    public int getPosColumna() {
        return posColumna;
    }
    /**
     * Get the value of posFila
     *
     * @return the value of posFila
     */
    public int getPosFila() {
        return posFila;
    }
    
    //Setters
    /**
     * Set the value of resistencia
     *
     * @param resistencia new value of resistencia
     */
    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }
    /**
     * Set the value of ciclos
     *
     * @param ciclos new value of ciclos
     */
    public void setCiclos(int ciclos){
        this.ciclos=ciclos;
    }
    /**
     * Set the value of posColumna
     *
     * @param posColumna new value of posColumna
     */
    public void setPosColumna(int posColumna) {
        this.posColumna = posColumna;
    }
    /**
     * Set the value of posFila
     *
     * @param posFila new value of posFila
     */
    public void setPosFila(int posFila) {
        this.posFila = posFila;
    }

    //Otros
    public abstract void Accion();
}