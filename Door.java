
/**
 * Write a description of class Door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door
{
    private Room habitacion1;
    private Room habitacion2;
    //La puerta se cierra cuando el jugador pasa por ella una vez
    private boolean autocierre;
    private boolean abierta;

    public Door(Room habitacion1, Room habitacion2, boolean autocierre){
        this.habitacion1 = habitacion1;
        this.habitacion2 = habitacion2;
        this.autocierre = autocierre;
        abierta = true;
    }
    
    /**
     * Devuelve la habitacion a la que sales si pasas por la puerta
     * @param habitacionActual
     */
    public Room getSalida(Room habitacionActual){
        Room salida;
        if(habitacionActual == habitacion1){
            salida = habitacion1;
        }
        else{
            salida = habitacion2;
        }
        return salida;
    }

    public void cerrarPuerta(){
        if(autocierre){
            abierta = false;
        }
    }

    public boolean getAbierta(){
        return abierta;
    }
}
