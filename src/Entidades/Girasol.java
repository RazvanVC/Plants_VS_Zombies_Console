package Entidades;
import static Game.StartGaming.soles;

/**
 * @author DanielGG
 */
public class Girasol extends Planta {
    
    //Constructor
    public Girasol() { //Se inicializan los valores. 
        super(0, 1); //Daño y Resistencia heredadas de la clase Planta.
        this.coste= 20; //Coste para plantar.
        this.ciclos= 0; //Inicio de ciclos.
    }
    
    //Métodos
    @Override
    /**
     * Éste método hace que el Girasol vaya generando soles.
     */
    public void Accion() {
        this.ciclos++; //Aumenta en 1 los ciclos.
        if (this.ciclos == 2){ //Si los ciclos del Girasol lleguan a ser igual a 2.
            soles += 10; //Los soles totales aumentan en 10.
            this.ciclos= 0; //Se reinician los ciclos a 0.
        }    
    }

    @Override
    /**
     * Devuelve un string del Girasol con su resistencia.
     */
    public String toString() {
        return "G("+ this.resistencia + ")" ;
    }
}
