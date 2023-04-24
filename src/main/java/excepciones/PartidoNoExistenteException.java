package excepciones;

import ArgentinaPrograma_TP_Final.modelo.Partido;

public class PartidoNoExistenteException  extends RuntimeException {
    public PartidoNoExistenteException(Partido partido) {
        super("El partido " + partido + " no existe.");
    }
}
