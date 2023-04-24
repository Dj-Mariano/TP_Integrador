package excepciones;

public class ResultadoNoEstablecidoException extends RuntimeException {
    public ResultadoNoEstablecidoException() {
        super("No fue posible establecer un resultado");
    }
}
