package br.com.game.test;

import static org.junit.Assert.*;
import org.junit.Test;

import br.com.game.Player;

public class PlayerTest {

	@Test
	public void createPlayer() {
		String name = "Ozymandias";
		Player player = new Player(name);

		player.addKill();
		player.addDeath();
		player.addDeath();
		player.addDeath();
		player.addKill();

		int kills = player.getKills();
		int deaths = player.getDeaths();
		assertEquals(2, kills);
		assertEquals(3, deaths);
		assertEquals(name, player.getName());
	}

	@Test
	public void comparePlayersByKill() {
		Player player1 = new Player("Ozymandias");
		Player player2 = new Player("NiteOwl");

		player1.addKill();
		player1.addKill();
		player2.addKill();

		player1.addDeath();
		player2.addDeath();
		player2.addDeath();

		int result = player1.compareTo(player2);

		assertTrue(result >= 1);
	}

	@Test
	public void comparePlayersByDeath() {
		Player player1 = new Player("Ozymandias");
		Player player2 = new Player("NiteOwl");

		player1.addKill();
		player2.addKill();

		player1.addDeath();
		player1.addDeath();
		player2.addDeath();

		int result = player1.compareTo(player2);

		assertTrue(result < 0);
	}

	@Test
	public void playersAreEqual() {
		Player player1 = new Player("Ozymandias");
		Player player2 = new Player("NiteOwl");

		int result = player1.compareTo(player2);

		assertTrue(result == 0);
	}
}
