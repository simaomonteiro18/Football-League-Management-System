package entities;

import entities.enums.Position;
import entities.exceptions.InvalidTeamsException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class League {

    private String name;
    private List<Team> teams = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

    public League(String name, List<Team> teams) {
        this.name = name;
        this.teams = teams;
    }

    public void registerMatch(Match match) {

        if (match.getHomeTeam().equals(match.getAwayTeam())) {
            throw new InvalidTeamsException("Uma equipa não pode jogar contra si mesma");
        }

        if (!teams.contains(match.getHomeTeam()) || !teams.contains(match.getAwayTeam())) {
            throw new InvalidTeamsException("Equipa inválida ou não existente na liga");
        }

        matches.add(match);

        Team winner = match.winner();
        if (winner == null){
            match.getHomeTeam().addPoints(1);
            match.getAwayTeam().addPoints(1);
        } else {
            winner.addPoints(3);
        }
    }

    public List<Team> getStandings(){
        return teams.stream()
                .sorted(Comparator.comparingInt(Team::getPoints).reversed())
                .collect(Collectors.toList());
    }

    public Player getTopScorer() {
        return teams.stream()
                .flatMap(team -> team.getPlayers().stream())
                .max(Comparator.comparingInt(Player::getGoals))
                .orElse(null);
    }

    public Player getTopAssistant() {
        return teams.stream()
                .flatMap(team -> team.getPlayers().stream())
                .max(Comparator.comparingInt(Player::getAssists))
                .orElse(null);
    }

    public List<Player> getPlayersByPosition(Position position) {
        return teams.stream()
                .flatMap(team -> team.getPlayers().stream())
                .filter(player -> player.getPosition() == position)
                .collect(Collectors.toList());
    }

    public double getTeamAverageGoals(Team team) {

        return team.getPlayers().stream()
                .mapToInt(Player::getGoals)
                .average()
                .orElse(0.0);

    }

}
