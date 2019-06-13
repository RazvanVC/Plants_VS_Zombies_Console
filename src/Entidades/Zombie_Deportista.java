package Entidades;

import static Game.StartGaming.tablero;
import Tablero.Casilla;
import static Game.StartGaming.victoriaZombies;

/**
 * @author DanielGG
 */
public class Zombie_Deportista extends Zombie {

    //Constructor
    public Zombie_Deportista() { //Se inicializan los valores.
        super(1, 2); //Daño y Resistencia heredadas de la clase Zombie.
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
        } else { //Si los ciclos del zombie son iguales a 2.
            tablero[this.getPosColumna()][this.getPosFila()] = new Casilla(new Vacio()); //Llena de vacío la posición del zombie
            this.posFila--; //Disminuye la posición de su fila en 1.
            if (this.posFila == 0) { //Win condition zombie.
                victoriaZombies = true;
            }
            tablero[this.getPosColumna()][this.getPosFila()] = new Casilla(this); //Coloca al zombie en su nueva posición.
        }
    }

    @Override
    /**
     * Devuelve un string del Zombie_Comun con su resistencia
     */
    public String toString() {
        return "D(" + this.resistencia + ')';
    }
}
