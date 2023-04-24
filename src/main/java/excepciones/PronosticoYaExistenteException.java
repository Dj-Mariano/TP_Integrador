package excepciones;

import ArgentinaPrograma_TP_Final.modelo.Partido;
import ArgentinaPrograma_TP_Final.modelo.Pronostico;

public class PronosticoYaExistenteException extends RuntimeException{

    public PronosticoYaExistenteException(String nombre, String equipo1, String equipo2){
        super("Ya existe un pronostico de " + nombre + " para el partido entre "+ equipo1 + " y "+ equipo2 + ".");
    }
}
