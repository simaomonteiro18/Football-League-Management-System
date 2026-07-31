package application;

import entities.*;
import services.InvalidTeamsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        List<Team> teams = new ArrayList<>();

        List<Player> benficaPlayers = new ArrayList<>();
        benficaPlayers.add(new Player("Vangelis Pavlidis", Position.ATA, 15, 4));
        benficaPlayers.add(new Player("António Silva", Position.DF, 2, 1));
        benficaPlayers.add(new Player("Fredrik Aursnes", Position.MF, 1, 6));
        benficaPlayers.add(new Player("Anatoliy Trubin", Position.GK, 1, 0));
        Team benfica = new Team("Sport Lisboa e Benfica", benficaPlayers);
        teams.add(benfica);

        List<Player> portoPlayers = new ArrayList<>();
        portoPlayers.add(new Player("Pepê", Position.ATA, 9, 7));
        portoPlayers.add(new Player("Froholdt", Position.MF, 3, 10));
        portoPlayers.add(new Player("Diogo Costa", Position.GK, 0, 0));
        Team porto = new Team("FC Porto", portoPlayers);
        teams.add(porto);

        List<Player> sportingPlayers = new ArrayList<>();
        sportingPlayers.add(new Player("Luís Suaréz", Position.ATA, 12, 3));
        sportingPlayers.add(new Player("Pote", Position.MF, 8, 5));
        Team sporting = new Team("Sporting CP", sportingPlayers);
        teams.add(sporting);

        List<Player> bragaPlayers = new ArrayList<>();
        bragaPlayers.add(new Player("Pau Víctor", Position.ATA, 6, 2));
        bragaPlayers.add(new Player("Ricardo Horta", Position.MF, 7, 8));
        Team braga = new Team("SC Braga", bragaPlayers);
        teams.add(braga);

        Match jogo1 = new Match(benfica, porto, 3, 1);
        Match jogo2 = new Match(sporting, braga, 2, 2);
        Match jogo3 = new Match(benfica, sporting, 1, 0);
        Match jogo4 = new Match(porto, braga, 4, 0);
        Match jogo5 = new Match(benfica, braga, 2, 1);
        Match jogo6 = new Match(porto, sporting, 1, 3);

        League league = new League("Liga Betclic", teams);

        league.registerMatch(jogo1);
        league.registerMatch(jogo2);
        league.registerMatch(jogo3);
        league.registerMatch(jogo4);
        league.registerMatch(jogo5);
        league.registerMatch(jogo6);

        System.out.println("=== CLASSIFICAÇÃO ===\n");

        List<Team> standings = league.getStandings();
        for (int i = 0; i < standings.size(); i++) {
            Team team = standings.get(i);
            System.out.println((i + 1) + ". " + team.getName() + " - " + team.getPoints() + " pts");
        }

        System.out.println();

        System.out.println("=== MELHOR MARCADOR ===\n");

        System.out.println(league.getTopScorer().getName() + " - " + league.getTopScorer().getGoals());

        System.out.println();

        System.out.println("=== MELHOR ASSISTENTE ===\n");

        System.out.println(league.getTopAssistant().getName() + " - " + league.getTopAssistant().getAssists());

        System.out.println();

        System.out.println("=== AVANÇADOS DA LIGA ===\n");

        List<Player> avancados = league.getPlayersByPosition(Position.ATA);
        for (Player p : avancados) {
            System.out.println(p.getName());
        }

        System.out.println();

        System.out.println("=== MÉDIA DE GOLOS POR EQUIPA ===\n");

        for (Team team : teams){
            System.out.println(team + " : " + String.format("%.2f", league.getTeamAverageGoals(team)));
        }

        System.out.println("\n=== TESTE DE VALIDAÇÃO ===");
        try {
            league.registerMatch(new Match(benfica, benfica, 1, 1));
        } catch (InvalidTeamsException e) {
            System.out.println("Erro apanhado: " + e.getMessage());
        }

    }

}
