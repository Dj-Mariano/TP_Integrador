package ArgentinaPrograma_TP_Final.modelo;
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
                    throw new RuntimeException("No se establecio un resultado");
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
            if(p.getFase().equals(pronostico.getFase())
                    && p.getRonda().equals(pronostico.getRonda())
                    && p.getPersona().equals(pronostico.getPersona())){
                //throw new RuntimeException("Ya hay un pronostico de x persona para el partido y");
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
}
