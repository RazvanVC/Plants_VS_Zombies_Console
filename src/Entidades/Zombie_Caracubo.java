/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;


import static Game.StartGaming.tablero;
import Tablero.Casilla;
import static Game.StartGaming.victoriaZombies;

/**
 *
 * @author razvanvc
 */
public class Zombie_Caracubo extends Zombie{

    //Constructor
    public Zombie_Caracubo() { //Se inicializan los valores.
        super(1, 8); //Daño y Resistencia heredadas de la clase Zombie.
        this.ciclos = 0; //Inicio de ciclos.
    }

    //Métodos
    @Override
    /**
     * Éste método hace que el zombie avance o ataque.
     */
    public void Accion() {
        Entidad e; //Se inicializa la entidad.
        if (tablero[this.getPosColumna()][this.getPosFila() - 1].isOcupado()) { //Si la casilla por la que quiere avanzar el zombie está ocupada.
            e = tablero[this.getPosColumna()][this.getPosFila() - 1].getContenido(); //Se guarda la entidad de esa casilla.
            if (e instanceof Planta) { //Si es una planta.
                ((Planta) e).resistencia--; //Disminuye su resistencia en 1.
            }
        } else if (this.ciclos == 4) { //Si los ciclos del zombie son iguales a 2.
            tablero[this.getPosColumna()][this.getPosFila()] = new Casilla(new Vacio()); //Llena de vacío la posición del zombie
            this.posFila--; //Disminuye la posición de su fila en 1.
            if (this.posFila == 0) { //Win condition zombie.
                victoriaZombies = true;
            }
            tablero[this.getPosColumna()][this.getPosFila()] = new Casilla(this); //Coloca al zombie en su nueva posición.
            this.ciclos = 0; //Resetea los ciclos del zombie a 0.
        } else { //Si no
            this.ciclos++; //Aumentan los ciclos en 1.
        }
    }

    @Override
    /**
     * Devuelve un string del Zombie_Comun con su resistencia
     */
    public String toString() {
        return "C(" + this.resistencia + ')';
    }
    
}
