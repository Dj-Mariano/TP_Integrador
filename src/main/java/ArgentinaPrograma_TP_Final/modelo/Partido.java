package ArgentinaPrograma_TP_Final.modelo;

import excepciones.ValorGolesException;

public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;
    private String ronda;

    public Partido(){
    }
    public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2){
        this.equipo1=equipo1;
        this.equipo2=equipo2;
        this.golesEquipo1=golesEquipo1;
        this.golesEquipo2=golesEquipo2;
    }
    public boolean juegan(String equipo1, String equipo2){
        return (this.equipo1.getNombreEquipo().equals(equipo1) && this.equipo2.getNombreEquipo().equals(equipo2)) ||
                (this.equipo1.getNombreEquipo().equals(equipo2) && this.equipo2.getNombreEquipo().equals(equipo1));
    }
    protected void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }
    protected void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }
    protected void setRonda(String ronda) {
        this.ronda = ronda;
    }
    public String getRonda() {
        return ronda;
    }
    public Equipo getEquipo1() {
        return equipo1;
    }
    public Equipo getEquipo2() {
        return equipo2;
    }
    //Verificar que los goles sea un num entero y > 0
    protected void agregarGoles(int golesEquipo1, int golesEquipo2){
        if(((golesEquipo1%1)==0) && (golesEquipo1>=0)){
            this.golesEquipo1=golesEquipo1;
        } else {
            throw new ValorGolesException(golesEquipo1, equipo1.getNombreEquipo());
        }
        if(((golesEquipo2%1)==0) && (golesEquipo2>=0)){
            this.golesEquipo2=golesEquipo2;
        } else {
            throw new ValorGolesException(golesEquipo2, equipo2.getNombreEquipo());
        }
    }
    public ResultadoEnum getResultado() {
        if(golesEquipo1>golesEquipo2){
            return ResultadoEnum.GANA1;
        } else if (golesEquipo2 > golesEquipo1){
            return ResultadoEnum.GANA2;
        } else {
            return ResultadoEnum.EMPATE;
        }
    }
}