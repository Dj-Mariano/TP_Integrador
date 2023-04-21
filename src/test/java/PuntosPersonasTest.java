import ArgentinaPrograma_TP_Final.modelo.modelo.modelo.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class PuntosPersonasTest {
    @Test

    public void ProbarPuntos(){
        Persona persona1 =new Persona();
        persona1.setNombre("Mariana");
        Equipo equipo1=new Equipo("Argentina", "1");
        Equipo equipo2=new Equipo("Polonia", "2");
        Partido partido1  = new Partido(equipo1,equipo2,2,1);
        Pronostico pronostico1 = new Pronostico(persona1, partido1, equipo2, ResultadoEnum.PERDEDOR);
        Pronostico pronostico2= new Pronostico(persona1, partido1, equipo1, ResultadoEnum.GANADOR);
        pronostico1.Calcularpuntos();
        pronostico2.Calcularpuntos();
        assertEquals(1, persona1.getPuntos(), 0.01);
    }
}
