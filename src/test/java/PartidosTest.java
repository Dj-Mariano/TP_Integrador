import ArgentinaPrograma_TP_Final.modelo.modelo.modelo.Equipo;
import ArgentinaPrograma_TP_Final.modelo.modelo.modelo.Partido;
import ArgentinaPrograma_TP_Final.modelo.modelo.modelo.ResultadoEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartidosTest {
    @Test
    public void probarResultadoPerdedor1(){
        Equipo equipo1= new Equipo("Pais 1", "1");
        Equipo equipo2= new Equipo("Pais 2", "2");
        Partido partido = new Partido(equipo1, equipo2, 1,2 );
//        partido.Resultado(equipo1);
        assertEquals (ResultadoEnum.PERDEDOR, partido.Resultado(equipo1));
    }
    @Test
    public void probarResultadoPerdedor2(){
        Equipo equipo1= new Equipo("Pais 1", "1");
        Equipo equipo2= new Equipo("Pais 2", "2");
        Partido partido = new Partido(equipo1, equipo2, 2,1 );
//        partido.Resultado(equipo2);
        assertEquals (ResultadoEnum.PERDEDOR, partido.Resultado(equipo2));
    }
    @Test
    public void probarResultadoGanador1(){
        Equipo equipo1= new Equipo("Pais 1", "1");
        Equipo equipo2= new Equipo("Pais 2", "2");
        Partido partido = new Partido(equipo1, equipo2, 2,1 );
//        partido.Resultado(equipo1);
        assertEquals (ResultadoEnum.GANADOR, partido.Resultado(equipo1));
    }
    @Test
    public void probarResultadoGanador2(){
        Equipo equipo1= new Equipo("Pais 1", "1");
        Equipo equipo2= new Equipo("Pais 2", "2");
        Partido partido = new Partido(equipo1, equipo2, 1,2 );
//        partido.Resultado(equipo2);
        assertEquals (ResultadoEnum.GANADOR, partido.Resultado(equipo2));
    }
    @Test
    public void probarResultadoEmpate(){
        Equipo equipo1= new Equipo("Pais 1", "1");
        Equipo equipo2= new Equipo("Pais 2", "2");
        Partido partido = new Partido(equipo1, equipo2, 2,2 );
//        partido.Resultado(equipo1);
        assertEquals (ResultadoEnum.EMPATE, partido.Resultado(equipo1));
    }
}
