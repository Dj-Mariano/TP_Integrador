package excepciones;

public class RondaYaExistenteException extends RuntimeException {
    public RondaYaExistenteException(int ronda) {
        super("La ronda " + ronda + " ya existe.");
    }
}
