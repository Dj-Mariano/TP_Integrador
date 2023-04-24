package ArgentinaPrograma_TP_Final.modelo;
import excepciones.PronosticoYaExistenteException;
import excepciones.ResultadoNoEstablecidoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDB {

    private String db;
    private String user;
    private String pass;
    private List<Pronostico> Pronosticos;
    private List<Persona> personas;
    private LectorDeArchivos lectorDeArchivos;

    public GestorDB(String db, String user, String pass, LectorDeArchivos lectorDeArchivos) {
        this.db = db;
        this.user = user;
        this.pass = pass;
        this.lectorDeArchivos = lectorDeArchivos;
        this.Pronosticos = new ArrayList<>();
        this.personas = new ArrayList<>();
    }
    public void levantarPronosticos() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + this.db, this.user, this.pass);
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from pronostico");

            while(res.next()){

                Fase fase = lectorDeArchivos.obtenerFase(res.getInt("fase"));
                Ronda ronda = fase.obtenerRonda(res.getInt("ronda"));
                Persona persona = this.obtenerPersona(res.getString("persona"));
                Partido partido = ronda.obtenerPartido(res.getString("equipo_1"),res.getString("equipo_2"));

                boolean gana1 = res.getBoolean("gana_1");
                boolean empata = res.getBoolean("empata");
                boolean gana2 = res.getBoolean("gana_2");

                ResultadoEnum resultado = null;

                if(gana1){
                    resultado = ResultadoEnum.GANA1;
                } else if (gana2) {
                    resultado = ResultadoEnum.GANA2;
                } else if (empata) {
                    resultado = ResultadoEnum.EMPATE;
                } else {
                    throw new ResultadoNoEstablecidoException();
                }

                Pronostico pronostico = new Pronostico(fase, ronda, persona, partido, resultado);

                this.agregarPronostico(pronostico);
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);

        }
    }
    private void agregarPronostico(Pronostico pronostico) {
        for(Pronostico p : this.Pronosticos){
            if(p.getPersona().equals(pronostico.getPersona())
                    && ((p.getPartido().getEquipo1().equals(pronostico.getPartido().getEquipo1())
                    && p.getPartido().getEquipo2().equals(pronostico.getPartido().getEquipo2()))
                    || (p.getPartido().getEquipo1().equals(pronostico.getPartido().getEquipo2())
                    &&p.getPartido().getEquipo2().equals(pronostico.getPartido().getEquipo1())))){
                throw new PronosticoYaExistenteException(p.getPersona().getNombre(),
                        p.getPartido().getEquipo1().getNombreEquipo(),
                        p.getPartido().getEquipo2().getNombreEquipo());
            }
        }
        this.Pronosticos.add(pronostico);
    }
    private Persona obtenerPersona(String nombrePersona) {
        Persona persona = null;

        for(Persona p : this.personas){
            if(p.getNombre().equals(nombrePersona)){
                persona = p;
            }
        }
        if(persona == null){
            persona = new Persona();
            persona.setNombre(nombrePersona);
            this.personas.add(persona);
        }
        return persona;
    }
    public List<Pronostico> getPronosticos() {
        return Pronosticos;
    }
    public List<Persona> getPersonas() {
        return personas;
    }
    public boolean acertoTodosPronosticosRonda(Persona p, int ronda){
        //filtro los pronosticos que son de la perosna p
        List<Pronostico> pronosticosFiltrado = this.Pronosticos.stream().filter(pronostico -> pronostico.getPersona().equals(p)).toList();
        //vuelvo a filtrar los pronosticos que son de la ronda
        pronosticosFiltrado = pronosticosFiltrado.stream().filter(pronostico -> pronostico.getRonda().getNumeroRonda() == ronda).toList();

        return pronosticosFiltrado.stream().allMatch(pronostico -> pronostico.pronosticoAcertado());
    }
    public boolean acertoTodosPronosticosFases(Persona p, int fase){
        //filtro los pronosticos que son de la perosna p
        List<Pronostico> pronosticosFiltrado = this.Pronosticos.stream().filter(pronostico -> pronostico.getPersona().equals(p)).toList();
        //vuelvo a filtrar los pronosticos que son de la fase
        pronosticosFiltrado = pronosticosFiltrado.stream().filter(pronostico -> pronostico.getFase().getNumeroFase() == fase).toList();

        return pronosticosFiltrado.stream().allMatch(pronostico -> pronostico.pronosticoAcertado());
    }
}
