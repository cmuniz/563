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

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {

    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(Option comando : Option.values()){
            if(comando.getDescription().equals(aString)){
                return true;
            }
        }
        return false;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){

        for(Option comando : Option.values()){
            System.out.print(comando.getDescription() + " ");
        }
        System.out.println();
    }

    /**
     * Return the Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return The Option correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public Option getCommandWord(String commandWord){

        for(Option comando : Option.values()){
            if(comando.getDescription().equals(commandWord)){
                return comando;
            }
        }

        return Option.DESCONOCIDO; 
    }
}
