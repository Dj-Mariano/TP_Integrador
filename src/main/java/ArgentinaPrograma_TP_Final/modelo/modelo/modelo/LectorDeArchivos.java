package ArgentinaPrograma_TP_Final.modelo.modelo.modelo;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorDeArchivos {
    private String rutaPronosticos;
    private String rutaResultados;

    public LectorDeArchivos(String rutaPronosticos, String rtaResultados) {
        this.rutaPronosticos = rutaPronosticos;
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
                if (buscarEquipo(fila[1],equipos)==true) {
                    //System.out.println("Equipo ya existente");
                } else {
                    Equipo equipo = new Equipo(fila[1], "Vacio");
                    equipos.add(equipo);
                }
                if (buscarEquipo(fila[4],equipos)==true) {
                    //System.out.println("Equipo ya existente");
                } else {
                    Equipo equipo = new Equipo(fila[4], "Vacio");
                    equipos.add(equipo);
                }
            }
        } catch(CsvValidationException | IOException e){
            throw new RuntimeException(e);
        }
            return equipos;
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
                   if (e.getNombreEquipo().equals(fila[1])){
                       p.setEquipo1(e);
                   }
               }
               for(Equipo e : equipos) {
                   if (e.getNombreEquipo().equals(fila[4])){
                       p.setEquipo2(e);
                   }
               }
               p.agregarGoles(Integer.parseInt(fila[2]),Integer.parseInt(fila[3]));
               p.setRonda(fila[0]);
               partidos.add(p);
           }
       } catch(CsvValidationException | IOException e){
           throw new RuntimeException(e);
       }
       return partidos;
    }
    public List<Persona> getPersonas(List<Equipo> equipos, List<Partido> partidos) {
        List<Persona> personas = new ArrayList<>();

        CSVParser parser = null;
        CSVReader lector = null;

        try {
            parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();
            lector = new CSVReaderBuilder(new FileReader(this.rutaPronosticos))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            String[] fila;
            int c = 0;
            int n = 0;
            while ((fila = lector.readNext()) != null) {
                if(posicionPersona(fila[0],personas)==-1){
                    Persona p = new Persona();
                    p.setNombre(fila[0]);
                    p.agregarApuesta(partidos.get(c),equipos.get(posicionEquipo(fila[1],equipos)),resultado(fila[2]));
                    personas.add(p);
                } else {
                    personas.get(posicionPersona(fila[0],personas)).agregarApuesta(partidos.get(c),equipos.get(posicionEquipo(fila[1],equipos)),resultado(fila[2]));
                }
                c++;
                if(c>=partidos.size()) {
                    c=0;}
            }
        } catch(CsvValidationException | IOException e){
            throw new RuntimeException(e);
        }
        return personas;
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
                if (buscarRonda(fila[0],rondas)==true) {
                    //((System.out.println("Ronda Existente");
                } else {
                    List<Partido> aux = new ArrayList<>();
                    Ronda ronda = new Ronda(fila[0]);
                    for(int i=0;i<partidos.size();i++)
                    {
                        if(partidos.get(i).getRonda().equals(fila[0]))
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
    private boolean buscarEquipo(String equipo, List<Equipo> listEquipos){
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
    private boolean buscarRonda(String nombreRonda, List<Ronda> listRonda){
        int cont = 0;
        for(Ronda r : listRonda) {
            if (r.getNumeroRonda().equals(nombreRonda)){
                break;
            } else{
                cont++;}
        }
        if(cont==listRonda.size())
            return false;
        else
            return true;
    }
    private int posicionPersona(String nombre, List<Persona> listPersonas){
        int c = -1;
        for(Persona p : listPersonas) {
            if (p.getNombre().equals(nombre)){
                c = listPersonas.indexOf(p);
                break;
            } else{
                c = -1;}
        }
        return c;
    }
    private int posicionEquipo(String equipo, List<Equipo> listEquipos){
        int c = 0;
        for(Equipo e : listEquipos) {
            if (e.getNombreEquipo().equals(equipo)){
                c = listEquipos.indexOf(e);
                break;
            } else {
                c = 0;}
        }
        return c;
    }
    private ResultadoEnum resultado(String resultado){
        if(resultado.equals("GANADOR")) {
            return ResultadoEnum.GANADOR;
        } else if (resultado.equals("PERDEDOR")){
            return ResultadoEnum.PERDEDOR;
        } else if (resultado.equals("EMPATE")) {
            return ResultadoEnum.EMPATE;
        } else {
            return ResultadoEnum.NO_PARTICIPO;
        }
    }

}
