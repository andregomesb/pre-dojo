package br.com.game.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.game.Event;
import br.com.game.Match;
import br.com.game.Player;
import br.com.game.Ranking;

public class RankingTest {

	private Match match;
	private Player p1, p2, p3, p4;

	@Before
	public void setup() {
		LocalDateTime occurence = LocalDateTime.now();
		match = new Match(12341234);

		p1 = new Player("p1");
		p2 = new Player("p2");
		p3 = new Player("p3");
		p4 = new Player("p4");

		match.addPlayer(p3);
		match.addPlayer(p1);
		match.addPlayer(p2);
		match.addPlayer(p4);

		Event event1 = new Event(occurence);
		Event event2 = new Event(occurence.plusSeconds(30));
		Event event3 = new Event(occurence.plusSeconds(60));
		Event event4 = new Event(occurence.plusSeconds(90));

		event1.addKiller(p1);
		event1.addDeceased(p2);

		event2.addKiller(p2);
		event2.addDeceased(p3);

		event3.addKiller(p2);
		event3.addDeceased(p3);

		event4.addKiller(p3);
		event4.addDeceased(p4);

		match.addEvent(event1);
		match.addEvent(event2);
		match.addEvent(event3);
		match.addEvent(event4);
	}

	@Test
	public void createRanking() {
		Ranking rank = new Ranking(match);

		List<Player> rankedPlayers = rank.getRank();

		assertEquals(p2.getName(), rankedPlayers.get(0).getName());
		assertEquals(p1.getName(), rankedPlayers.get(1).getName());
		assertEquals(p3.getName(), rankedPlayers.get(2).getName());
		assertEquals(p4.getName(), rankedPlayers.get(3).getName());
	}
	
	@Test
	public void ignoreWorld(){
		Player world = new Player("<WORLD>");
		Event event = new Event(LocalDateTime.now().plusHours(1));
		
		event.addKiller(world);
		event.addDeceased(p4);
		match.addEvent(event);
				
		List<Player> ranks = new Ranking(match).getRank(); 
		
		assertEquals(2, p4.getDeaths());
		assertEquals(4, ranks.size());
		assertEquals(p2.getName(), ranks.get(0).getName());
		assertEquals(p1.getName(), ranks.get(1).getName());
		assertEquals(p3.getName(), ranks.get(2).getName());
		assertEquals(p4.getName(), ranks.get(3).getName());
		assertFalse(ranks.contains(world));		
	}
}
