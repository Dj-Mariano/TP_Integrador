package ArgentinaPrograma_TP_Final.modelo.modelo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nombre;
    private double puntos;

    public Persona() {
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addPuntos(double puntos) {
        this.puntos = this.puntos + puntos;
    }

    public double getPuntos() {
        return puntos;
    }

    public static void imprimirPuntaje(Persona pers){
        System.out.println("--------------------------------------------------");
        System.out.println( "El pronóstico de "+ pers.getNombre() +" ha obtenido "+ pers.getPuntos()+ " puntos." );
    }

    public String getNombre() {
        return nombre;
    }
}
