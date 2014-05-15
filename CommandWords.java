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
    private static final String VALOR_DEFECTO = Option.UNKNOWN.toString();

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
        validCommands.put(Option.GO.toString().toLowerCase(), Option.GO);
        validCommands.put(Option.QUIT.toString().toLowerCase(), Option.QUIT);
        validCommands.put(Option.HELP.toString().toLowerCase(), Option.HELP);
        validCommands.put(Option.LOOK.toString().toLowerCase(), Option.LOOK);
        validCommands.put(Option.EAT.toString().toLowerCase(), Option.EAT);
        validCommands.put(Option.BACK.toString().toLowerCase(), Option.BACK);
        validCommands.put(Option.TAKE.toString().toLowerCase(), Option.TAKE);
        validCommands.put(Option.DROP.toString().toLowerCase(), Option.DROP);
        validCommands.put(Option.ITEMS.toString().toLowerCase(), Option.ITEMS);
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
            return Option.UNKNOWN;
        }
    }
}
