package excepciones;

import ArgentinaPrograma_TP_Final.modelo.modelo.modelo.Equipo;

public class EquipoIncorrectoException extends RuntimeException{
    public EquipoIncorrectoException(Equipo equipo){
        super("El equipo " + equipo.getNombreEquipo() + " No participo del partido.");
    }
}
