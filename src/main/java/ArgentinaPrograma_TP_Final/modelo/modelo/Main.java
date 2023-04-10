package ArgentinaPrograma_TP_Final.modelo.modelo;

import ArgentinaPrograma_TP_Final.modelo.modelo.modelo.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        LectorDeArchivos lectorDeArchivos = new LectorDeArchivos("C:\\Users\\maria\\OneDrive\\DOCUMENTOS MARIANO\\B-JAVA UTN\\TP Integrador\\TP_Integrador\\src\\main\\resources\\pronostico.csv","C:\\Users\\maria\\OneDrive\\DOCUMENTOS MARIANO\\B-JAVA UTN\\TP Integrador\\TP_Integrador\\src\\main\\resources\\resultados.csv");
        List<Equipo> equipos = lectorDeArchivos.getEquipos();
        List<Partido> partidos = lectorDeArchivos.getPartidos(equipos);
        List<Ronda> rondas = lectorDeArchivos.getRondas(partidos);
        List<Persona> personas = lectorDeArchivos.getPersonas(equipos,partidos);


        for(Ronda r : rondas){
            r.imprimirPartidos();
        }

        for(Persona p : personas){
            p.mostrarPuntos();
        }
    }
}