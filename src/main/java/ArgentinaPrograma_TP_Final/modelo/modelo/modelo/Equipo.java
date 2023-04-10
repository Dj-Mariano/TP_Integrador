package ArgentinaPrograma_TP_Final.modelo.modelo.modelo;

public class Equipo {
    private String nombreEquipo;
    private String descripcion;

    public Equipo(String nombreEquipo, String descripcion){
        this.nombreEquipo = nombreEquipo;
        this.descripcion = descripcion;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }
}
