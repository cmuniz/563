
/**
 * Write a description of class Door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door
{
    private Room salida;
    private boolean autocierre;
    private boolean abierta;

    public Door(Room salida, boolean autocierre){
        this.salida = salida;
        this.autocierre = autocierre;
        abierta = true;
    }

    public Room getSalida(){
        return salida;
    }

    public void cerrarPuerta(){
        if(autocierre){
            abierta = false;
        }
    }
    
    public void setSalida(Room salida){
        this.salida = salida;
    }
}
