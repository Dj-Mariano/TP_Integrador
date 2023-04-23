package ArgentinaPrograma_TP_Final.modelo;
import java.util.ArrayList;
import java.util.List;

public class Ronda {
    private int numeroRonda;
    private String fase;
    private List<Partido> partidos;

    public Ronda(int numeroRonda, String fase){
        this.numeroRonda = numeroRonda;
        this.fase = fase;
        this.partidos = new ArrayList<>();
    }

    public String getFase() {
        return fase;
    }
    public int getNumeroRonda() {
        return numeroRonda;
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
            throw new RuntimeException("El partido no existe");
        }
        return partido;
    }
}
