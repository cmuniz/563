import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private HashMap<String, Option> validCommands;
    private static final String VALOR_DEFECTO = Option.DESCONOCIDO.toString();

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        addCommands();
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        Set<String> commands = validCommands.keySet();
        for(String comando : commands){
            System.out.print(comando + " ");
        }
        System.out.println();
    }

    private void addCommands(){
        validCommands.put(Option.VE.toString().toLowerCase(), Option.VE);
        validCommands.put(Option.SALIR.toString().toLowerCase(), Option.SALIR);
        validCommands.put(Option.AYUDA.toString().toLowerCase(), Option.AYUDA);
        validCommands.put(Option.MIRAR.toString().toLowerCase(), Option.MIRAR);
        validCommands.put(Option.COMER.toString().toLowerCase(), Option.COMER);
        validCommands.put(Option.VOLVER.toString().toLowerCase(), Option.VOLVER);
        validCommands.put(Option.COGER.toString().toLowerCase(), Option.COGER);
        validCommands.put(Option.DEJAR.toString().toLowerCase(), Option.DEJAR);
        validCommands.put(Option.OBJETOS.toString().toLowerCase(), Option.OBJETOS);
    }

    /**
     * Return the Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return The Option correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public Option getCommandWord(String commandWord){
        if(isCommand(commandWord)){
            return validCommands.get(commandWord);
        }
        else{
            return Option.DESCONOCIDO;
        }
    }
}
