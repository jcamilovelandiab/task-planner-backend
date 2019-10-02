package taskplanner.app.apirest.entities;

import java.util.List;

public class Card {
    String name;
    List<Task> tasks;

    public Card(){}

    public Card(String name, List<Task> tasks){}

}
