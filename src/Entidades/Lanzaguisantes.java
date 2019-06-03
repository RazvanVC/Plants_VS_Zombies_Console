package Entidades;
import static Game.StartGaming.columnas;
import static Game.StartGaming.tablero;

/**
 * @author DanielGG
 */
public class Lanzaguisantes extends Planta {
    
    //Constructor
    /*
    * Crea un Lanzaguisantes.
    */
    public Lanzaguisantes() {
        //Se inicializan los valores.
        super(1, 3); //Daño y Resistencia heredadas de la clase Planta.
        this.coste = 50; //Coste para plantar.
        this.ciclos = 0; //Inicio de ciclos.
    }

    //Métodos
    @Override
    /**
     * Éste método hace que el Lanzaguisantes dispare a los zombies.
     */
    public void Accion() {
        boolean Disparo = false; //Se inicia el disparo a false.
        Entidad e; //Inicializamos la entidad.
        int i = this.getPosColumna(); //Guardamos la posición del Lanzaguisantes en una variable.
        while (!Disparo) { //Mientras el Lanzaguisantes no haya disparado.
            if (i < columnas) { //Si la posición de la columna a comprobar es menor al número de columnas, entra en el bucle.
                if (tablero[this.getPosFila()-1][i].isOcupado()) { //Si una casilla de la fila del Lanzaguisantes está ocupada, se mete en el bucle.
                    e = tablero[this.getPosFila()-1][i].getContenido(); //Se guarda el contenido de la casilla ocupada.
                    if (e instanceof Zombie) { //Si es un Zombie
                        ((Zombie) e).resistencia--; //Su resistencia disminuye en 1.
                        return;
                    } else { //Si no es un Zombie 
                        i++; //Aumentamos en 1 la columna a comprobar.
                    }

                } else { //Si no está ocupada
                    i++; //Aumentamos en 1 la columna a comprobar.
                }
            } else { //Si la posición de la columna a comprobar es mayor al número de columnas totales.
                break; //Sale del bucle.
            }
        }
    }


    @Override
    /**
     * Devuelve un string del Lanzaguisantes con su resistencia.
     */
        public String toString() {
        return "L(" + this.resistencia + ")";
    }
}
