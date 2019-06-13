package Entidades;

import static Game.StartGaming.tablero;
import Tablero.Casilla;

/**
 * @author DanielGG
 */
public class PetaCereza extends Planta {

    //Constructor
    /*
    * Crea una Petacereza.
     */
    public PetaCereza() {
        //Se inicializan los valores.
        super(10, 2); //Daño y Resistencia heredadas de la clase Planta.
        this.coste = 50; //Coste para plantar.
        this.ciclos = 0; //Inicio de ciclos.
    }

    //Métodos
    @Override
    /**
     * Éste método hace que el Petacereza explote.
     */
    public void Accion() {
        Entidad e;
        if (this.ciclos == 2) {
            if (this.posFila == 0) {
                if (this.posColumna-1 == 0) {
                    e = tablero[this.posFila + 1][this.posColumna].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila + 1][this.posColumna + 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila][this.posColumna + 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                } else {
                    e = tablero[this.posFila][this.posColumna - 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila + 1][this.posColumna - 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila + 1][this.posColumna].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila + 1][this.posColumna + 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila][this.posColumna + 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                }
            } else if (this.posFila == 5) {
                if (this.posColumna == 0) {
                    e = tablero[this.posFila - 1][this.posColumna].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila - 1][this.posColumna + 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila][this.posColumna + 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                } else {
                    e = tablero[this.posFila][this.posColumna - 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila + 1][this.posColumna - 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila + 1][this.posColumna].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila + 1][this.posColumna + 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                    e = tablero[this.posFila][this.posColumna + 1].getContenido();
                    if (e instanceof Zombie) {
                        ((Zombie) e).resistencia -= 10;
                    }
                }
            } else if (this.posColumna == 0) {
                e = tablero[this.posFila - 1][this.posColumna].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
                e = tablero[this.posFila - 1][this.posColumna + 1].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
                e = tablero[this.posFila][this.posColumna + 1].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
                e = tablero[this.posFila + 1][this.posColumna + 1].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
                e = tablero[this.posFila + 1][this.posColumna].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
            } else {
                e = tablero[this.posFila][this.posColumna - 1].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
                e = tablero[this.posFila - 1][this.posColumna - 1].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
                e = tablero[this.posFila - 1][this.posColumna].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
                e = tablero[this.posFila - 1][this.posColumna + 1].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
                e = tablero[this.posFila][this.posColumna + 1].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
                e = tablero[this.posFila + 1][this.posColumna + 1].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
                e = tablero[this.posFila + 1][this.posColumna].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
                e = tablero[this.posFila + 1][this.posColumna - 1].getContenido();
                if (e instanceof Zombie) {
                    ((Zombie) e).resistencia -= 10;
                }
            }
            Vacio v = new Vacio();
            Casilla c = new Casilla(v);
            tablero[this.posFila][this.posColumna] = c;
        } else {
            this.ciclos++;
        }
    }

    @Override
    /**
     * Devuelve un string del Petacereza con su resistencia.
     */
    public String toString() {
        return "P(" + this.resistencia + ")";
    }
}
