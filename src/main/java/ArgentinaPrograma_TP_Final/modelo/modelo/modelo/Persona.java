package ArgentinaPrograma_TP_Final.modelo.modelo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nombre;
    private int puntos;
    private List<Partido> partido;
    private List<Equipo> equipo;
    private List<ResultadoEnum> resultado;

    public Persona(){
        this.partido = new ArrayList<>();
        this.equipo = new ArrayList<>();
        this.resultado = new ArrayList<>();
    }

    public void getPartido() {
        System.out.println(equipo.get(0).getNombreEquipo());
        System.out.println(equipo.get(1).getNombreEquipo());

        System.out.println(partido.get(0).getEquipo1().getNombreEquipo());
        System.out.println(partido.get(1).getEquipo1().getNombreEquipo());
    }

    public List<Equipo> getEquipo() {
        return equipo;
    }

    protected void setNombre(String nombre) {
        this.nombre = nombre;
    }

    protected void agregarApuesta(Partido partido, Equipo equipo, ResultadoEnum resultado){
        //verificar si el partido ya esta en la lista
        if(this.partido.contains(partido)) {
            //System.out.println("La persona " + nombre + " ya aposto por ese partido");
        }
        else {
            this.partido.add(partido);
            this.equipo.add(equipo);
            this.resultado.add(resultado);
        }
    }

    private int getPuntos() {
        for(int i = 0; i < partido.size(); i++)
        {
            if(partido.get(i).Resultado(equipo.get(i))==resultado.get(i)){
                puntos++;
            }
        }
        return puntos;
    }
    private int getPartidosAcertados() {
        int acertados = 0;
        for(int i = 0; i < partido.size(); i++)
        {
            if(partido.get(i).Resultado(equipo.get(i))==resultado.get(i)){
                acertados++;
            }
        }
        return acertados;
    }
    public void mostrarPuntos(){
        System.out.println("--------------------------------------------------");
        System.out.println("La persona " + nombre + " obtuvo " + getPuntos() + " puntos y acerto " + getPartidosAcertados() + " resultados");
    }
    public String getNombre() {
        return nombre;
    }
}
