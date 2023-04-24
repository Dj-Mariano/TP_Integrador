package ArgentinaPrograma_TP_Final.modelo;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import excepciones.EquipoYaExistenteException;
import excepciones.FaseNoExistenteException;
import excepciones.RondaYaExistenteException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorDeArchivos {
    private String rutaConfiguracion;
    private String rutaResultados;

    List<Fase> fases = new ArrayList<>();

    public LectorDeArchivos(String rutaPronosticos, String rtaResultados) {
        this.rutaConfiguracion = rutaPronosticos;
        this.rutaResultados = rtaResultados;
    }

    public List<Equipo> getEquipos() {
        List<Equipo> equipos = new ArrayList<>();

        CSVParser parser = null;
        CSVReader lector = null;

        try {
            parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();
            lector = new CSVReaderBuilder(new FileReader(this.rutaResultados))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            String[] fila;
            while ((fila = lector.readNext()) != null) {

                if (existeEquipo(fila[2],equipos)==true) {
                    try {
                    } catch (EquipoYaExistenteException e) {
                        throw new EquipoYaExistenteException((fila[2]));
                    }
                }
                else {
                    Equipo equipo = new Equipo(fila[2], "Vacio");
                    equipos.add(equipo);
                }
                if (existeEquipo(fila[5],equipos)==true) {
                    try {
                    } catch (EquipoYaExistenteException e) {
                        throw new EquipoYaExistenteException((fila[5]));
                    }
                }
                else {
                    Equipo equipo = new Equipo(fila[5], "Vacio");
                    equipos.add(equipo);
                }
            }
        } catch(CsvValidationException | IOException e){
            throw new RuntimeException(e);
        }
        return equipos;
    }
    private boolean existeEquipo(String equipo, List<Equipo> listEquipos){
        int cont = 0;
        for(Equipo e : listEquipos) {
            if (e.getNombreEquipo().equals(equipo)){
                break;
            } else{
                cont++;}
        }
        if(cont==listEquipos.size())
            return false;
        else
            return true;
    }
    public List<Partido> getPartidos(List<Equipo> equipos) {
        List<Partido> partidos = new ArrayList<>();
        CSVParser parser = null;
        CSVReader lector = null;

        try {
            parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();
            lector = new CSVReaderBuilder(new FileReader(this.rutaResultados))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            String[] fila;

            while ((fila = lector.readNext()) != null) {
                Partido p = new Partido();
                for(Equipo e : equipos) {
                    if (e.getNombreEquipo().equals(fila[2])){
                        p.setEquipo1(e);
                    }
                }
                for(Equipo e : equipos) {
                    if (e.getNombreEquipo().equals(fila[5])){
                        p.setEquipo2(e);
                    }
                }
                p.agregarGoles(Integer.parseInt(fila[3]),Integer.parseInt(fila[4]));
                p.setRonda(fila[1]);
                partidos.add(p);
            }
        } catch(CsvValidationException | IOException e){
            throw new RuntimeException(e);
        }
        return partidos;
    }
    public List<Ronda> getRondas(List<Partido> partidos) {
        List<Ronda> rondas = new ArrayList<>();

        CSVParser parser = null;
        CSVReader lector = null;

        try {
            parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();
            lector = new CSVReaderBuilder(new FileReader(this.rutaResultados))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            String[] fila;

            while ((fila = lector.readNext()) != null) {

                if (existeRonda(fila[1], rondas)) {
                    try {
                    } catch (RondaYaExistenteException e) {
                        throw new RondaYaExistenteException(Integer.parseInt(fila[1]));
                    }

                } else {
                    List<Partido> aux = new ArrayList<>();
                    Ronda ronda = new Ronda(Integer.parseInt(fila[1]), fila[0]);
                    for (int i = 0; i < partidos.size(); i++) {
                        if (partidos.get(i).getRonda().equals(fila[1]))
                            aux.add(partidos.get(i));
                    }
                    ronda.setPartidos(aux);
                    rondas.add(ronda);
                }
            }

        } catch(CsvValidationException | IOException e){
            throw new RuntimeException(e);
        }
        return rondas;
    }
    private boolean existeRonda(String nombreRonda, List<Ronda> listRonda){
        int cont = 0;
        for(Ronda r : listRonda) {
            if (String.valueOf(r.getNumeroRonda()).equals(nombreRonda)){
                break;
            } else{
                cont++;}
        }
        if(cont==listRonda.size())
            return false;
        else
            return true;
    }
    public List<Fase> getFases(List<Ronda> rondas) {

        CSVParser parser = null;
        CSVReader lector = null;

        try {
            parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();
            lector = new CSVReaderBuilder(new FileReader(this.rutaResultados))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            String[] fila;

            while ((fila = lector.readNext()) != null) {
                    List<Ronda> aux = new ArrayList<>();
                    Fase fase = new Fase(Integer.parseInt(fila[0]));
                    for (Ronda ronda : rondas) {
                        if (ronda.getFase().equals(fila[0]))
                            aux.add(ronda);
                    }
                    fase.setRonda(aux);
                    fases.add(fase);
            }

        } catch(CsvValidationException | IOException e){
            throw new RuntimeException(e);
        }
        return fases;
    }
    private boolean existeFase(String numeroFase, List<Fase> listFase){
        int cont = 0;
        for(Fase f : listFase) {
            if (String.valueOf(f.getNumeroFase()).equals(numeroFase)){
                break;
            } else{
                cont++;}
        }
        if(cont==listFase.size())
            return false;
        else
            return true;
    }
    public List<String> getConfiguracion(){
        List<String> config = new ArrayList<>();

        CSVParser parser = null;
        CSVReader lector = null;

        try {
            parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();
            lector = new CSVReaderBuilder(new FileReader(this.rutaConfiguracion))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            String[] fila;

            while ((fila = lector.readNext()) != null) {
                for(int i = 0; i < fila.length; i++){
                    config.add(i,fila[i]);
                }
            }
        } catch(CsvValidationException | IOException e){
            throw new RuntimeException(e);
        }
        return config;
    }
    public Fase obtenerFase(int numeroFase) {
        Fase fase = null;

        for(Fase f : this.fases){
            if(f.getNumeroFase() == numeroFase){
                fase = f;
            }
        }

        if(fase == null){
            throw new FaseNoExistenteException(numeroFase);
        }
        return fase;
    }

}