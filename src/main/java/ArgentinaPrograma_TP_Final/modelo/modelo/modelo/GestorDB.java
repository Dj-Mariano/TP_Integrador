package ArgentinaPrograma_TP_Final.modelo.modelo.modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDB {

    private String db;
    private String user;
    private String pass;
    Connection con;
    Statement stmt;
    private List<Pronostico> Pronosticos;
    private List<Persona> personas;
    private LectorDeArchivos lectorDeArchivos;

    public GestorDB(LectorDeArchivos lectorDeArchivos) {
        this.lectorDeArchivos=lectorDeArchivos;
        this.Pronosticos=new ArrayList<>();
        this.personas= new ArrayList<>();
    }

//    public GestorDB(String db, String user, String pass){
//        this.db=db;
//        this.user=user;
//        this.pass=pass;
//        this.Pronosticos=new ArrayList<>();
//
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con= DriverManager.getConnection(this.db, this.user, this.pass);
//            stmt=con.createStatement();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        this.cargarPronosticos();
//    }
//
//    private void cargarPronosticos() {
//        ResultSet rs=null;
//        try{
//            rs=stmt.executeQuery("select * from pronosticos");
//            while(rs.next()){
//                this.Pronosticos.add(new Pronostico
//                        (rs.getString("persona"),rs.getString("equipo1"),rs.getString("gana1"),rs.getString("empate"), rs.getString("gana2"), rs.getString("equipo2"),));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void levantarPronosticos() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(this.db, this.user, this.pass);
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from pronostico");

            while(res.next()){

                //Instancio pronostico
                Fase fase = LectorDeArchivos.obtenerFase(res.getInt("fase"));
                Ronda ronda = fase.obtenerRonda(res.getInt("ronda"));
                Persona persona = this.obtenerPersona(res.getString("persona"));
                Partido partido = ronda.obtenerPartido(res.getString("equipo_1"),res.getString("equipo_2"));
                //Agrego el pronostico a la lista

//                Ronda ronda= LectorDeArchivos.obtenerRonda();
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);

        }
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

}
