package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Team {

    private String name;
    private List<Player> players = new ArrayList<>();
    private Integer points = 0;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Integer getPoints() {
        return points;
    }

    public void addPoints(Integer pointsToAdd) {
        points += pointsToAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
