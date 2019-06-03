package Excepciones;

/**
 * Es una excepción que trata cualquier error producido por las plantas.
 * @author DanielGG
 */
public class ExcepcionPlanta extends Exception {

    public ExcepcionPlanta(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "System message > " + this.getMessage();
    }
}
