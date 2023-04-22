package excepciones;

public class RondaNoExistenteException extends RuntimeException{
    public RondaNoExistenteException(int numeroRonda){
        super("La ronda "+ numeroRonda + " ya existe.");
    }

}
