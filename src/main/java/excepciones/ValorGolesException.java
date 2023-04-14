package excepciones;

public class ValorGolesException extends RuntimeException{
    public ValorGolesException(int goles, String nombre){
        super("La cantidad" + goles + " de goles para " + nombre + " es incorrecto");
    }
}
