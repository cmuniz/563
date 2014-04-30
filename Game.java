import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> previusRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        previusRoom = new Stack<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room mazmorras, taberna, bodega, torreEste, torreOeste, pasadizo, pasilloSur, pasilloNorte, torturas, escaleras;

        // create the rooms
        mazmorras = new Room("mazmorras");//, 
        torturas = new Room("torturas");//, "grilletes", 4);
        bodega = new Room("bodega");//, "vino especiado", 1);
        taberna = new Room("taberna");//, "caliz", 1);
        torreEste = new Room("torre este");//, "silla", 7);
        torreOeste = new Room("torre oeste");//, "huevo de dragon", 10);
        pasadizo = new Room("pasadizo");//, "llave", 1);
        pasilloSur = new Room("pasillo sur");//, "espada", 8);
        pasilloNorte = new Room("pasillo norte");//, "pergamino", 0);
        escaleras = new Room("escaleras");//, "tablon", 3);

        // initialise room exits
        // (Room north, Room east, Room south, Room west, Room southEast, Room northWest) 

        mazmorras.setExit("west", pasilloNorte);
        bodega.setExit("south", taberna);
        taberna.setExit("north", bodega);
        taberna.setExit("south", pasilloNorte);
        torreEste.setExit("west", pasilloSur);
        torreEste.setExit("southeast", escaleras);
        torreOeste.setExit("east", pasilloSur);
        pasadizo.setExit("north", pasilloNorte);
        pasadizo.setExit("south", pasilloSur);
        pasilloSur.setExit("norht", pasadizo);
        pasilloSur.setExit("east", torreEste);
        pasilloSur.setExit("west", torreOeste);
        pasilloNorte.setExit("north", taberna);
        pasilloNorte.setExit("east", mazmorras);
        pasilloNorte.setExit("south", pasadizo);
        pasilloNorte.setExit("west", torturas);
        torturas.setExit("east", pasilloNorte);
        escaleras.setExit("northwest", torreEste);

        mazmorras.addItem(new Item("pala", 5));
        torturas.addItem(new Item("grilletes", 4));
        bodega.addItem(new Item("vino especiado", 1));
        taberna.addItem(new Item("caliz", 1));
        torreEste.addItem(new Item("silla", 7));
        torreOeste.addItem(new Item("huevo de dragon", 10));
        pasadizo.addItem(new Item("llave", 1));
        pasilloSur.addItem(new Item("espada", 8));
        pasilloNorte.addItem(new Item("pergamino", 0));
        escaleras.addItem(new Item("tablon", 3));

        currentRoom = escaleras;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("look")) {
            printLocationInfo();
        }
        else if(commandWord.equals("eat")){
            eat();
        }
        else if(commandWord.equals("back")){
            back();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
        //         System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        Room nextRoom = currentRoom.getExit(command.getSecondWord());

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previusRoom.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription() + "\n");
    }

    private void eat(){
        System.out.println("You have eaten now and you are not hungry any more");
    }

    private void back(){
        if(!previusRoom.empty()){
            currentRoom = previusRoom.pop();
            printLocationInfo();
        }
        else{
            System.out.println("No se puede ir más atrás. Ya está en el inicio");
        }
    }
}
