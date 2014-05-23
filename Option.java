
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO("ve"), 
    QUIT("salir"),
    HELP("ayuda"),
    LOOK("mirar"),
    EAT("come"),
    BACK("volver"),
    TAKE("coge"),
    DROP("deja"),
    ITEMS("objetos"),
    UNKNOWN("");
    
    private final String description;
    
    Option(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }
}
