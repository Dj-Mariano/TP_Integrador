package ArgentinaPrograma_TP_Final.modelo;
import excepciones.PartidoNoExistenteException;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
    private int numeroRonda;
    private int numerofase;
    private List<Partido> partidos;

    public Ronda(int numeroRonda, int numerofase){
        this.numeroRonda = numeroRonda;
        this.numerofase = numerofase;
        this.partidos = new ArrayList<>();
    }

    public int getFase() {
        return numerofase;
    }
    public int getNumeroRonda() {
        return numeroRonda;
    }
    public List<Partido> getPartidos() {
        return partidos;
    }
    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }
    public void imprimirPartidos(){
        System.out.println("--------------------------------------------------");
        System.out.println("En la Ronda " + numeroRonda + " jugaron los siguentes equipos:");
        for(Partido p : partidos){
            System.out.println("Equipo 1: " + p.getEquipo1().getNombreEquipo() + " - Equipo 2: " + p.getEquipo2().getNombreEquipo());
        }
    }
    public Partido obtenerPartido(String equipo1, String equipo2) {
        Partido partido = null;

        for(Partido p : this.partidos){
            if(p.juegan(equipo1,equipo2)){
                partido = p;
            }
        }

        if(partido == null){
            throw new PartidoNoExistenteException(partido);
        }
        return partido;
    }
}
