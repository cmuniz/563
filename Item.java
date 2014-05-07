
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private String description;
    private int peso;
    private boolean transportable;
    
    public Item(String description, int peso, boolean transportable){
        this.description = description;
        this.peso = peso;
        this.transportable = transportable;
    }
    
    public String getDescription(){
        return description;
    }
    
    public int getPeso(){
        return peso;
    }
    
    public String toString(){
        return getDescription() + "; peso: " + getPeso();
    }

    public boolean getTransportable(){
        return transportable;
    }
}
