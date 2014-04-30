
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
    
    public Item(String description, int peso){
        this.description = description;
        this.peso = peso;
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

}
