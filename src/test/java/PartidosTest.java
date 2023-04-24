import ArgentinaPrograma_TP_Final.modelo.Equipo;
import ArgentinaPrograma_TP_Final.modelo.Partido;
import ArgentinaPrograma_TP_Final.modelo.ResultadoEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartidoTest {
    private Equipo equipo1;
    private Equipo equipo2;
    private Partido partido;


    //GANA 2
    @Test
    public void testPartido1() {
        this.equipo1 = new Equipo("Arg", "");
        this.equipo2 = new Equipo("Br", "");
        this.partido = new Partido(this.equipo1, this.equipo2, 2, 3);
        assertEquals(this.partido.getResultado(), ResultadoEnum.GANA2);
    }

    //GANA 1
    @Test
    public void testPartido2() {
        this.equipo1 = new Equipo("Arg", "");
        this.equipo2 = new Equipo("Br", "");
        this.partido = new Partido(this.equipo1, this.equipo2, 5, 1);
        assertEquals(this.partido.getResultado(), ResultadoEnum.GANA1);
    }

    //EMPATE
    @Test
    public void testPartido3() {
        this.equipo1 = new Equipo("Arg", "");
        this.equipo2 = new Equipo("Br", "");
        this.partido = new Partido(this.equipo1, this.equipo2, 3, 3);
        assertEquals(this.partido.getResultado(), ResultadoEnum.EMPATE);
    }





    //Test anteriores arreglados
    @Test
    public void probarResultadoPerdedor1(){
        Equipo equipo1= new Equipo("Pais 1", "1");
        Equipo equipo2= new Equipo("Pais 2", "2");
        Partido partido = new Partido(equipo1, equipo2, 1,2 );
        assertEquals (ResultadoEnum.GANA2, partido.getResultado());
    }
    @Test
    public void probarResultadoPerdedor2(){
        Equipo equipo1= new Equipo("Pais 1", "1");
        Equipo equipo2= new Equipo("Pais 2", "2");
        Partido partido = new Partido(equipo1, equipo2, 2,1 );
        assertEquals (ResultadoEnum.GANA1, partido.getResultado());
    }
    @Test
    public void probarResultadoGanador1(){
        Equipo equipo1= new Equipo("Pais 1", "1");
        Equipo equipo2= new Equipo("Pais 2", "2");
        Partido partido = new Partido(equipo1, equipo2, 2,1 );
        assertEquals (ResultadoEnum.GANA1, partido.getResultado());
    }
    @Test
    public void probarResultadoGanador2(){
        Equipo equipo1= new Equipo("Pais 1", "1");
        Equipo equipo2= new Equipo("Pais 2", "2");
        Partido partido = new Partido(equipo1, equipo2, 1,2 );
        assertEquals (ResultadoEnum.GANA2, partido.getResultado());
    }
    @Test
    public void probarResultadoEmpate(){
        Equipo equipo1= new Equipo("Pais 1", "1");
        Equipo equipo2= new Equipo("Pais 2", "2");
        Partido partido = new Partido(equipo1, equipo2, 2,2 );
        assertEquals (ResultadoEnum.EMPATE, partido.getResultado());
    }
}
