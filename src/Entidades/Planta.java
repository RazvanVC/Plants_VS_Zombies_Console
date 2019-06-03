package Entidades;
import java.util.*;

/**
 * @author DanielGG
 */
public abstract class Planta extends Entidad {
    
    //Atributos
    public int coste;

    //Constructor
    public Planta(int daño, int resistencia) {
        super(daño, resistencia);
    }
    
    //Métodos
    
    //Getters
    /**
     * Get the value of coste
     *
     * @return the value of coste
     */
    public int getCoste() {
        return coste;
    }
}