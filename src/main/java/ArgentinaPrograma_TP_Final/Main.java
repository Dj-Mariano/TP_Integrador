package ArgentinaPrograma_TP_Final;
import ArgentinaPrograma_TP_Final.modelo.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        LectorDeArchivos lectorDeArchivos = new LectorDeArchivos(args[0], args[1]);

        //leo csv resultado
        List<Equipo> equipos = lectorDeArchivos.getEquipos();
        List<Partido> partidos = lectorDeArchivos.getPartidos(equipos);
        List<Ronda> rondas = lectorDeArchivos.getRondas(partidos);
        List<Fase> fases = lectorDeArchivos.getFases(rondas);
        List<String> config = lectorDeArchivos.getConfiguracion();

        GestorDB DB = new GestorDB(config.get(0), config.get(1), config.get(2), lectorDeArchivos);
        DB.levantarPronosticos();

        List<Pronostico> pronosticos = DB.getPronosticos();

        System.out.println(pronosticos.size());

    }
}