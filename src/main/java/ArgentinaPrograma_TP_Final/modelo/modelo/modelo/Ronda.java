package ArgentinaPrograma_TP_Final.modelo.modelo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
    private String numeroRonda;
    private List<Partido> partidos;
    private List<Persona> persona;

    public Ronda(String numeroRonda){
        this.numeroRonda = numeroRonda;
        this.partidos = new ArrayList<>();
        this.persona = new ArrayList<>();
    }

    public String getNumeroRonda() {
        return numeroRonda;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public void setPersona(List<Persona> persona) {
        this.persona = persona;
    }
    public void imprimirPartidos(){
        System.out.println("--------------------------------------------------");
        System.out.println("En la Ronda " + numeroRonda + " La jugaron los siguentes equipos");
        for(Partido p : partidos){

            System.out.println("Equipo 1: " + p.getEquipo1().getNombreEquipo() + " - Equipo 2: " + p.getEquipo2().getNombreEquipo());
        }
        System.out.println("--------------------------------------------------");
    }
}
