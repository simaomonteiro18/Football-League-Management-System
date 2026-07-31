package entities;

import entities.enums.Position;

public class Player {

    private String name;
    private Position position;
    private Integer goals;
    private Integer assists;

    public Player(String name, Position position, Integer goals, Integer assists) {
        this.name = name;
        this.position = position;
        this.goals = goals;
        this.assists = assists;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Integer getGoals() {
        return goals;
    }

    public Integer getAssists() {
        return assists;
    }
    
}
