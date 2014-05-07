import java.util.Stack;
import java.util.ArrayList;

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
    private ArrayList<Item> items;
    public static final int PESO_MAX = 15;

    public Player(Room currentRoom){
        this.currentRoom = currentRoom;
        previusRoom = new Stack<>();
        items = new ArrayList<>(); 
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
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

    public void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription() + "\n");
    }

    public void eat(){
        System.out.println("You have eaten now and you are not hungry any more");
    }

    public void back(){
        if(!previusRoom.empty()){
            currentRoom = previusRoom.pop();
            printLocationInfo();
        }
        else{
            System.out.println("No se puede ir m�s atr�s. Ya est� en el inicio");
        }

    }

    public void take(Item item){       
        if(item.getTransportable()){
            if(pesoTotal() +  item.getPeso() <= PESO_MAX){
                items.add(item);
            }
            else{
                System.out.println("No puede llevar este objeto. Debe soltar algun objeto");
            }
        }
        else{
            System.out.println("El item no se puede coger");
        }
    }

    public Item drop(int index){
        return items.remove(index);
    }

    public void items(){
        for(Item item : items){
            System.out.println(item.toString());
        }
    }

    private int pesoTotal(){
        int peso = 0;
        for(Item item : items){
            peso += item.getPeso();
        }
        return peso;
    }
}
