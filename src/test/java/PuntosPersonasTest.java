import ArgentinaPrograma_TP_Final.modelo.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PuntosPersonasTest {
    Persona persona1;
    Persona persona2;
    Equipo equipo1;
    Equipo equipo2;
    Partido partido1;
    Pronostico pronosticoP1;
    Pronostico pronosticoP2;
    Ronda ronda;
    Fase fase;

    Ronda ronda2;
    Partido partido2;
    Pronostico pronostico2P1;
    Pronostico pronostico2P2;
    List<Pronostico> pronosticos = new ArrayList<>();

    private void calcularPuntosTest(List<Pronostico> pronosticos, int puntosAcierto) {

        for (Pronostico p : pronosticos) {
            if (p.pronosticoAcertado()) {
                p.getPersona().sumarPuntos(puntosAcierto);
                p.getPersona().agregarAcierto();
            }
        }
    }


    public boolean acertoTodosPronosticosFasesTest(Persona p, int fase){

        List<Pronostico> pronosticosFiltrado = this.pronosticos.stream().filter(pronostico -> pronostico.getPersona().equals(p)).toList();

        pronosticosFiltrado = pronosticosFiltrado.stream().filter(pronostico -> pronostico.getFase().getNumeroFase() == fase).toList();

        return pronosticosFiltrado.stream().allMatch(pronostico -> pronostico.pronosticoAcertado());
    }

    @Test
    public void ProbarSumaDePuntosYAciertos(){
        fase= new Fase(1);
        ronda= new Ronda(1, fase.getNumeroFase());
        partido1  = new Partido(equipo1,equipo2,2,1);
        persona1= new Persona();
        persona1.setNombre("Mariana");
        persona2= new Persona();
        persona2.setNombre("Luis");
        equipo1=new Equipo("Argentina", "1");
        equipo2=new Equipo("Polonia", "2");

        pronosticoP1 = new Pronostico(fase, ronda, persona1, partido1, ResultadoEnum.GANA1);
        pronosticoP2 = new Pronostico(fase, ronda, persona2, partido1, ResultadoEnum.GANA2);
        pronosticos.add(pronosticoP1);
        pronosticos.add(pronosticoP2);
        calcularPuntosTest(pronosticos, 1);

        assertTrue(persona1.getPuntos()> persona2.getPuntos() && persona1.getCantAciertos()> persona2.getCantAciertos());
    }

    @Test
    public void ProbarTodosLosAciertos(){
        fase= new Fase(1);

        ronda= new Ronda(1, fase.getNumeroFase());
        ronda2= new Ronda(2, fase.getNumeroFase());

        partido1  = new Partido(equipo1,equipo2,0,1);
        partido2  = new Partido(equipo1,equipo2,4,2);

        persona1= new Persona();
        persona1.setNombre("Jesus");
        persona2= new Persona();
        persona2.setNombre("Alejo");

        equipo1=new Equipo("Chile", "1");
        equipo2=new Equipo("Uruguay", "2");

        pronosticoP1 = new Pronostico(fase, ronda, persona1, partido1, ResultadoEnum.GANA2);
        pronosticoP2 = new Pronostico(fase, ronda, persona2, partido1, ResultadoEnum.EMPATE);
        pronostico2P1 = new Pronostico(fase, ronda2, persona1, partido2, ResultadoEnum.GANA1);
        pronostico2P2 = new Pronostico(fase, ronda2, persona2, partido2, ResultadoEnum.GANA2);

        pronosticos.add(pronosticoP1);
        pronosticos.add(pronosticoP2);
        pronosticos.add(pronostico2P1);
        pronosticos.add(pronostico2P2);

        assertTrue(acertoTodosPronosticosFasesTest(persona1, 1));
    }
}
