package excepciones;
import ArgentinaPrograma_TP_Final.modelo.modelo.modelo.Equipo;

public class EquipoYaExistenteException extends RuntimeException{
    public EquipoYaExistenteException(String equipo){
        super("El equipo "+ equipo + " ya existe.");
    }
}
