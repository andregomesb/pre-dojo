package br.com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

public class Ranking {

	private List<Player> rankedPlayers;

	public Ranking(Match match) {
		ArrayList<Player> players = new ArrayList<>(match.getPlayers());
		removeWorld(players);
		this.rankedPlayers = sortPlayers(players);
	}

	public List<Player> getRank() {
		return this.rankedPlayers;
	}

	private List<Player> sortPlayers(List<Player> players) {
		rankedPlayers = players
				.stream().sorted(comparing((Player p) -> p.getKills())
						.thenComparing(reverseOrder(comparing(p -> p.getDeaths()))).reversed())
				.collect(Collectors.toList());
		return rankedPlayers;
	}

	private void removeWorld(List<Player> players) {
		players.remove(new Player("<WORLD>"));
	}
}
