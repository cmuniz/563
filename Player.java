import java.util.Stack;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> previusRoom;

    public Player(Room currentRoom){
        this.currentRoom = currentRoom;
        previusRoom = new Stack<>();
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
