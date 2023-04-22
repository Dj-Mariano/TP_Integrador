package ArgentinaPrograma_TP_Final.modelo.modelo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Fase {
    private int numeroFase;
    private List<Ronda> rondas;
    public Fase(int numeroFase){
        this.numeroFase=numeroFase;
        this.rondas= new ArrayList<Ronda>();
    }

    public int getNumeroFase() {
        return numeroFase;
    }

    public Ronda obtenerRonda(int ronda) {
        Ronda ronda = null;

        for(Ronda r : this.rondas){
            if(r.getNumeroRonda() == numeroFase){
                ronda = r;
            }
        }
        if(ronda == null){
            throw new RuntimeException("La ronda no existe");
        }
        return ronda;
    }
}
