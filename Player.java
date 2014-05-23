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
    public static final int PESO_MAX = 5;

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
        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if(currentRoom.puertaCerrda(direction)){
            System.out.println("La puerta esta cerrada");
        }
        else {
            previusRoom.push(currentRoom);
            currentRoom.cerrarPuerta(direction);
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
            System.out.println("No se puede ir más atrás. Ya está en el inicio");
        }

    }

    public void take(Command command){ 
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Take what?");
            return;
        }

        Item item = currentRoom.getItem(command.getSecondWord());

        if(item != null){
            if(item.getTransportable()){
                if(pesoTotal() +  item.getPeso() <= PESO_MAX){
                    items.add(item);
                    currentRoom.removeItem(item);
                    System.out.println("Cogido el item");
                }
                else{
                    System.out.println("No puede llevar este objeto. Debe soltar algun objeto");
                }
            }
            else{
                System.out.println("El item no se puede coger");
            }
        }
        else {
            System.out.println("No existe el item");
        }
    }

    public void drop(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("drop what?");
            return;
        }
        Item item = getItem(command.getSecondWord());

        if(items.remove(item)){
            currentRoom.addItem(item);
            System.out.println("Soltado item");
        } 
        else {
            System.out.println("No se encuentra el item");
        }
    }

    public void items(){
        for(Item item : items){
            System.out.println(item.toString());
        }
        System.out.println("Peso total: " + pesoTotal());
    }

    private int pesoTotal(){
        int peso = 0;
        for(Item item : items){
            peso += item.getPeso();
        }
        return peso;
    }

    private Item getItem(String description){
        for(Item item : items){
            if(item.getDescription().equals(description)){
                return item;
            }
        }
        return null;
    }

}
