package ArgentinaPrograma_TP_Final.modelo;

public class Pronostico {
    private Fase fase;
    private Ronda ronda;
    private Persona persona;
    private Partido partido;
    private ResultadoEnum resultadoEnum;


    public Pronostico(Fase fase, Ronda ronda, Persona persona, Partido partido, ResultadoEnum resultadoEnum){
        this.fase = fase;
        this.ronda = ronda;
        this.persona = persona;
        this.partido = partido;
        this.resultadoEnum=resultadoEnum;
    }
    public Partido getPartido() { return partido; }
    public Fase getFase() {
        return fase;
    }
    public Ronda getRonda() {
        return ronda;
    }
    public Persona getPersona() {
        return persona;
    }
    public boolean pronosticoAcertado(){
        return this.partido.getResultado().equals(this.resultadoEnum);
    }

}
