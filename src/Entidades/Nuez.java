package Entidades;


/**
 * @author DanielGG
 */
public class Nuez extends Planta {
    
    //Constructor
    /*
    * Crea una Nuez.
    */
    public Nuez() {
        //Se inicializan los valores.
        super(0, 10); //Daño y Resistencia heredadas de la clase Planta.
        this.coste = 50; //Coste para plantar.
        this.ciclos = 0; //Inicio de ciclos.
    }

    //Métodos
    @Override
    /**
     * Éste método hace que el Nuez realice su accion.
     */
    public void Accion() {
    }


    @Override
    /**
     * Devuelve un string del Nuez con su resistencia.
     */
        public String toString() {
        return "N(" + this.resistencia + ")";
    }
}
