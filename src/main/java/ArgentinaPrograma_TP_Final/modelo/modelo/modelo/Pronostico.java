package ArgentinaPrograma_TP_Final.modelo.modelo.modelo;

public class Pronostico {
    private Persona persona;
    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultadoEnum;

    public Pronostico() {
    }

    protected void setPersona(Persona persona) {
        this.persona = persona;
    }
    protected void setPartido(Partido partido) {
        this.partido = partido;
    }
    protected void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    protected void setResultadoEnum(ResultadoEnum resultadoEnum) {
        this.resultadoEnum = resultadoEnum;
    }
    public void imprimirPronostico(){
        System.out.println("--------------------------------------------------");
        System.out.println("El ponostico de " +persona.getNombre() + " para el partido de " + partido.getEquipo1().getNombreEquipo() + " contra " + partido.getEquipo2().getNombreEquipo() + " es:");
        System.out.println(equipo.getNombreEquipo() + " " + resultadoEnum);
    }

    public void puntos(){
        double cant = 0.5;
        if(partido.Resultado(equipo)==resultadoEnum){
            persona.addPuntos(cant);
        }
    }
}
