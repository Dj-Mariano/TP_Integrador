package ArgentinaPrograma_TP_Final.modelo.modelo;
import ArgentinaPrograma_TP_Final.modelo.modelo.modelo.*;

import java.sql.SQLOutput;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        LectorDeArchivos lectorDeArchivos = new LectorDeArchivos("src\\main\\resources\\pronostico.csv","src\\main\\resources\\resultados.csv");
        List<Equipo> equipos = lectorDeArchivos.getEquipos();
        List<Partido> partidos = lectorDeArchivos.getPartidos(equipos);
        List<Persona> personas = lectorDeArchivos.getPersonas(partidos);
        List<Ronda> rondas = lectorDeArchivos.getRondas(partidos);
        List<Pronostico> pronosticos = lectorDeArchivos.getPronosticos(equipos,partidos,personas);

        //calculo puntos


        for(Pronostico p1 : pronosticos){
            p1.puntos();
            p1.imprimirPronostico();
        }

        for(Ronda r : rondas){
            r.imprimirPartidos();
        }

        Persona.devolverPuntos(personas.get(0));
        Persona.devolverPuntos(personas.get(1));
    }
}