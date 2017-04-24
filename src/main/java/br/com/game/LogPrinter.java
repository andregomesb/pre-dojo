package br.com.game;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LogPrinter {

	public void print(List<Player> players, long matchId) {		
		try (PrintWriter writer = new PrintWriter(new FileWriter("log_output.txt", true))) {			
			
			writer.print("MATCH: " + matchId);
			writer.format("%n%-18s%s %-5s%n", "", "Kill", "Death");
			
			int counter = 1;
			for (Player player : players) {
				writer.format("Rank (%d): %-8s %-5s %-5s%n", 
						counter, 
						player.getName(), 
						player.getKills(),
						player.getDeaths());
				counter++;
			}
			writer.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}