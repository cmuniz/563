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
}
