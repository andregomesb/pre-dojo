package br.com.game;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LogAnalyzer {

	public static void analyze(Path filepath) {
		LogLoader loader = new LogLoader();
		loader.loadLog(filepath);

		Ranking rank;
		List<Player> players;
		List<Match> matches = loader.getMatches();
		LogPrinter printer = new LogPrinter();

		for (Match match : matches) {
			rank = new Ranking(match);
			players = new ArrayList<Player>(rank.getRank());
			printer.print(players, match.getId());
		}
	}
}
