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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player(createRooms());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms()
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
        
        
        
        
        Door puerta1 = new Door(mazmorras, pasilloNorte, true);
        mazmorras.setExit("oeste", puerta1);
        pasilloNorte.setExit("este", puerta1);

        Door puerta2 = new Door(pasilloSur, torreEste, false);
        pasilloSur.setExit("este", puerta2);
        torreEste.setExit("oeste",puerta2);

        Door puerta3 = new Door(torreOeste, pasilloSur, true);
        torreOeste.setExit("este", puerta3);
        pasilloSur.setExit("oeste", puerta3);

        Door puerta4 = new Door(pasilloNorte, torturas, false);
        pasilloNorte.setExit("oeste", puerta4);       
        torturas.setExit("este", puerta4);

        Door puerta5 = new Door(pasadizo, pasilloNorte, false);
        pasadizo.setExit("norte", puerta5);
        pasilloNorte.setExit("sur", puerta5);

        Door puerta6 = new Door(pasadizo, pasilloSur,false);
        pasadizo.setExit("sur", puerta6);        
        pasilloSur.setExit("norte", puerta6);

        Door puerta7 = new Door(pasilloNorte, taberna, false);
        pasilloNorte.setExit("norte", puerta7);
        taberna.setExit("sur", puerta7);

        Door puerta8 = new Door(bodega, taberna, false);
        bodega.setExit("sur", puerta8);
        taberna.setExit("norte", puerta8);

        Door puerta9 = new Door(torreEste, escaleras, true);
        torreEste.setExit("sureste", puerta9);
        escaleras.setExit("noroeste", puerta9);
        
        

        mazmorras.addItem(new Item("pala", 5, true));
        torturas.addItem(new Item("grilletes", 4, false));
        bodega.addItem(new Item("vino", 1, true));
        taberna.addItem(new Item("caliz", 1, true));
        torreEste.addItem(new Item("silla", 7, false));
        torreOeste.addItem(new Item("huevodragon", 10, false));
        pasadizo.addItem(new Item("llave", 1, true));
        pasilloSur.addItem(new Item("espada", 8, true));
        pasilloNorte.addItem(new Item("pergamino", 0, true));
        escaleras.addItem(new Item("tablon", 3, true));

        return escaleras;  // start game outside
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
        System.out.println("Type '"+ Option.HELP.getDescription() + "' if you need help.");
        System.out.println();
        player.printLocationInfo();
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

        Option commandWord = command.getCommandWord();

        switch(commandWord) {
            case HELP: 
            printHelp();
            break;
            case GO: 
            player.goRoom(command);
            break;
            case QUIT: 
            wantToQuit = quit(command);
            break;
            case LOOK: 
            player.printLocationInfo();
            break;
            case EAT:
            player.eat();
            break;
            case BACK: 
            player.back();
            break;
            case TAKE: 
            player.take(command);
            break;
            case DROP: 
            player.drop(command);
            break;
            case ITEMS: 
            player.items();
            break;
            default: 
            System.out.println("Desconocido");
            break;
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

}
