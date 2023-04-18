package ArgentinaPrograma_TP_Final.modelo.modelo.modelo;

import excepciones.EquipoIncorrectoException;

public class Pronostico {
    private Persona persona;
    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultadoEnum;

    public Pronostico() {
    }

    public Pronostico(Persona pers, Partido part, Equipo equi, ResultadoEnum resultadoEnum){
        this.persona=pers;
        this.partido=part;
        this.equipo=equi;
        this.resultadoEnum=resultadoEnum;
    }

    protected void setPersona(Persona persona) {
        this.persona = persona;
    }
    protected void setPartido(Partido partido) {
        this.partido = partido;
    }
    protected void setEquipo(Equipo equipo, Partido partido) {
        if((partido.getEquipo1().getNombreEquipo().equals(equipo.getNombreEquipo()))||(partido.getEquipo2().getNombreEquipo().equals(equipo.getNombreEquipo()))){
            this.equipo = equipo;
        } else{
            throw new EquipoIncorrectoException(equipo);
        }
    }
    protected void setResultadoEnum(ResultadoEnum resultadoEnum) {
        this.resultadoEnum = resultadoEnum;
    }
    public void imprimirPronostico(){
        System.out.println("--------------------------------------------------");
        System.out.println("El pronóstico de " +persona.getNombre() + " para el partido de " + partido.getEquipo1().getNombreEquipo() + " contra " + partido.getEquipo2().getNombreEquipo() + " es:");
        System.out.println(equipo.getNombreEquipo() + " " + resultadoEnum);
    }

    public void puntos(){
        double cant = 0.5;
        if(partido.Resultado(equipo)==resultadoEnum){
            persona.addPuntos(cant);
        }
    }
}
