package taskplanner.app.apirest.data.entities;

import java.util.List;

public class Team {
    String name;
    List<Dashboard> dashboards;
    List<User> members;

    public Team(){}
}
