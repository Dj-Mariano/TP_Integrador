package ArgentinaPrograma_TP_Final.modelo.modelo.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDB {

//    private String db;
//    private String user;
//    private String pass;
//    Connection con;
//    Statement stmt;
//    List<Pronostico> Pronosticos;
//
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
}
