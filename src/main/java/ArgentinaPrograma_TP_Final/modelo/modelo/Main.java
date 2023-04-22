package ArgentinaPrograma_TP_Final.modelo.modelo;
import ArgentinaPrograma_TP_Final.modelo.modelo.modelo.*;

import java.sql.SQLOutput;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        LectorDeArchivos lectorDeArchivos = new LectorDeArchivos(args[0], args[1]);
        List<Equipo> equipos = lectorDeArchivos.getEquipos();
        List<Partido> partidos = lectorDeArchivos.getPartidos(equipos);
        List<Persona> personas = lectorDeArchivos.getPersonas(partidos);
        List<Ronda> rondas = lectorDeArchivos.getRondas(partidos);
        List<Pronostico> pronosticos = lectorDeArchivos.getPronosticos(equipos,partidos,personas);

        GestorDB DB = new GestorDB(lectorDeArchivos);
        DB.levantarPronosticos();

        //calculo puntos
        for(Pronostico p1 : pronosticos){
            p1.Calcularpuntos();
            p1.imprimirPronostico();
        }

        for(Ronda r : rondas){
            r.imprimirPartidos();
        }

        Persona.imprimirPuntaje(personas.get(0));
        Persona.imprimirPuntaje(personas.get(1));
    }
}