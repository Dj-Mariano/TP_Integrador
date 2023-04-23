package ArgentinaPrograma_TP_Final.modelo;

public class Persona {
    private String nombre;
    private Integer puntos;
    private Integer cantAciertos;

    public Persona() {
        this.puntos = 0;
        this.cantAciertos = 0;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void sumarPuntos(Integer puntosSuma) {
        this.puntos = this.puntos + puntosSuma;
    }
    public void agregarAcierto() {
        this.cantAciertos++;
    }
    public void imprimirPuntaje(){
        System.out.println("--------------------------------------------------");
        System.out.println(nombre + " ha obtenido " + puntos + " puntos, y acertó: " + cantAciertos + " Resultados");
    }

    public String getNombre() {
        return nombre;
    }

}
