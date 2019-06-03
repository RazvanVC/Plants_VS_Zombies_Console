package Excepciones;

/**
 * Es una excepción que trata cualquier error no producido por las plantas.
 * @author DanielGG
 */
public class ExcepcionJuego extends Exception {

    public ExcepcionJuego(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "ERROR: " + this.getMessage();
    }
}
