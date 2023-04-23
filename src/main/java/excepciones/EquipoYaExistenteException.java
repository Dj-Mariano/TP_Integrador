package excepciones;

public class EquipoYaExistenteException extends RuntimeException{
    public EquipoYaExistenteException(String equipo){
        super("El equipo "+ equipo + " ya existe.");
    }
}
