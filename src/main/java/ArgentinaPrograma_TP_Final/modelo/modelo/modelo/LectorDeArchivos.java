package ArgentinaPrograma_TP_Final.modelo.modelo.modelo;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import excepciones.EquipoYaExistenteException;
import excepciones.RondaNoExistenteException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorDeArchivos {
    private String rutaPronosticos;
    private String rutaResultados;
    List<Ronda> rondas;
    List<Fase> fases;

    public LectorDeArchivos(String rutaPronosticos, String rtaResultados) {
        this.rutaPronosticos = rutaPronosticos;
        this.rutaResultados = rtaResultados;
    }

    public Fase obtenerFase(int numeroFase) {
        Fase fase = null;

        for(Fase f : this.fases){
            if(f.getNumeroFase() == numeroFase){
                fase = f;
            }
        }
        if(fase == null){
            throw new RuntimeException("La fase no existe");
        }

        return fase;
    }
    public String getConfiguracion(){
        return "algo";
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
                if (buscarEquipo(fila[1],equipos)==true) throw new EquipoYaExistenteException(fila[1]);
                else {
                    Equipo equipo = new Equipo(fila[1], "Vacio");
                    equipos.add(equipo);
                }
                if (buscarEquipo(fila[4],equipos)==true) throw new EquipoYaExistenteException(fila[1]);
                else {
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
    public List<Persona> getPersonas(List<Partido> partidos) {
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
                    personas.add(p);
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
    public List<Pronostico> getPronosticos(List<Equipo> equipos, List<Partido> partidos, List<Persona> personas) {
        List<Pronostico> pronosticos = new ArrayList<>();

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
                Pronostico p = new Pronostico();
                p.setPersona(personas.get(posicionPersona(fila[0],personas)));
                p.setPartido(partidos.get(c));
                p.setEquipo(equipos.get(posicionEquipo(fila[1],equipos)),partidos.get(c));
                if((fila[2].equals("X"))&&(fila[3].equals(""))&&(fila[4].equals(""))) {
                    p.setResultadoEnum(ResultadoEnum.GANADOR);
                } else if ((fila[2].equals(""))&&(fila[3].equals(""))&&(fila[4].equals("X"))) {
                    p.setResultadoEnum(ResultadoEnum.PERDEDOR);
                } else if ((fila[2].equals(""))&&(fila[3].equals("X"))&&(fila[4].equals(""))){
                    p.setResultadoEnum(ResultadoEnum.EMPATE);
                } else {
                    System.out.println("Error al crear el pronostico");
                }
                pronosticos.add(p);
                Pronostico p1 = new Pronostico();
                p1.setPersona(personas.get(posicionPersona(fila[0],personas)));
                p1.setPartido(partidos.get(c));
                p1.setEquipo(equipos.get(posicionEquipo(fila[5],equipos)),partidos.get(c));
                if((fila[2].equals("X"))&&(fila[3].equals(""))&&(fila[4].equals(""))) {
                    p1.setResultadoEnum(ResultadoEnum.PERDEDOR);
                } else if ((fila[2].equals(""))&&(fila[3].equals(""))&&(fila[4].equals("X"))) {
                    p1.setResultadoEnum(ResultadoEnum.GANADOR);
                } else if ((fila[2].equals(""))&&(fila[3].equals("X"))&&(fila[4].equals(""))){
                    p1.setResultadoEnum(ResultadoEnum.EMPATE);
                } else {
                    System.out.println("Error al crear el pronostico");
                }
                pronosticos.add(p1);
                c++;
                if(c>=partidos.size()) {
                    c=0;}
            }
        } catch(CsvValidationException | IOException e){
            throw new RuntimeException(e);
        }
        return pronosticos;
    }
    public List<Ronda> getRondas(List<Partido> partidos) {
        rondas = new ArrayList<>();

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
                    Ronda ronda = new Ronda(Integer.parseInt(fila[0]));
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
    public Ronda obtenerRonda(int numeroRonda){
        Ronda ronda= null;

        for (Ronda r : this.rondas){
            if(r.getNumeroRonda()==numeroRonda){
                ronda=r;
            }
            if(ronda == null){
                throw new RondaNoExistenteException(numeroRonda);
            }
        }
        return ronda;
    }

}