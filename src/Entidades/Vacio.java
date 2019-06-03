/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author razvanvc
 */
public class Vacio extends Entidad{

    //Constructor
    public Vacio() {
        super(0, 0);
    }

    //Métodos
    @Override
    public void Accion() {    
    }
    @Override
    public String toString() {
        return "    ";
    }    
}
