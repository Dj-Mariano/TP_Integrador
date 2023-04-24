package excepciones;

public class FaseNoExistenteException extends RuntimeException {
    public FaseNoExistenteException(int fase){super("La fase "+ fase + " no existe.");
    }
}
