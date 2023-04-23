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

        //imprimo datos de las rondas
        for(Ronda r : rondas){
            r.imprimirPartidos();
        }
        calcularPuntos(DB,rondas,fases,config.get(3),config.get(4),config.get(5));
        imprimirResultados(DB.getPersonas());
    }

    private static void imprimirResultados(List<Persona> personas) {
        for (Persona p : personas){
            p.imprimirPuntaje();
        }
    }
    private static void calcularPuntos(GestorDB baseDatos,List<Ronda> rondas,List<Fase> fases, String puntosAcierto, String puntosRonda, String puntosFase) {
        List<Pronostico> pronosticos = baseDatos.getPronosticos();
        for (Pronostico p : pronosticos){
            if(p.pronosticoAcertado()){
                p.getPersona().sumarPuntos(Integer.parseInt(puntosAcierto));
                p.getPersona().agregarAcierto();
            }
        }
        List<Persona> personas = baseDatos.getPersonas();
        for(Persona per : personas){
            for (Ronda r : rondas){
                if (baseDatos.acertoTodosPronosticosRonda(per,r.getNumeroRonda())){
                    per.sumarPuntos(Integer.parseInt(puntosRonda));
                }
            }
            for (Fase f : fases){
                if (baseDatos.acertoTodosPronosticosFases(per,f.getNumeroFase())){
                    per.sumarPuntos(Integer.parseInt(puntosFase));
                }
            }
        }
    }

}