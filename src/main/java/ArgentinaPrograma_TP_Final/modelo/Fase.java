package ArgentinaPrograma_TP_Final.modelo;
import java.util.ArrayList;
import java.util.List;

public class Fase {
    private int numeroFase;
    private List<Ronda> rondas;

    public Fase(int numeroFase){
        this.numeroFase = numeroFase;
        this.rondas = new ArrayList<>();
    }

    public int getNumeroFase() {
        return numeroFase;
    }
    public void setRonda(List<Ronda> rondas) {
        this.rondas = rondas;
    }
    public Ronda obtenerRonda(int numeroRonda) {
        Ronda ronda = null;

        for(Ronda r : this.rondas){
            if(r.getNumeroRonda() == numeroRonda){
                ronda = r;
            }
        }
        if(ronda == null){
            throw new RuntimeException("La ronda no existe");
        }
        return ronda;
    }
}
