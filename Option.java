
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    VE("ve"), 
    SALIR("salir"),
    AYUDA("ayuda"),
    MIRAR("mirar"),
    COME("come"),
    VOLVER("volver"),
    COGE("coge"),
    DEJA("deja"),
    OBJETOS("objetos"),
    DESCONOCIDO("desconocido");
    
    private final String description;
    
    Option(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }
}
