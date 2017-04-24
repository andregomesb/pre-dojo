package br.com.game.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import br.com.game.Event;
import br.com.game.Match;
import br.com.game.Player;

public class MatchTest {

	@Test
	public void createMatch() {
		String match_str = "1236544";
		int match_id = 12365448;
		Match match1 = new Match(match_str);
		Match match2 = new Match(match_id);

		assertEquals(Long.parseLong(match_str), match1.getId());
		assertEquals(match_id, match2.getId());
	}

	@Test
	public void matchHasBeginAndEndDate() {
		Match match = new Match(123987);
		LocalDate matchBeginDate = LocalDate.of(2017, 05, 10);
		LocalTime matchBeginTime = LocalTime.of(15, 20, 30);
		LocalDate matchEndDate = matchBeginDate;
		LocalTime matchEndTime = matchBeginTime.plusMinutes(5);
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		match.setStartDate(matchBeginDate, matchBeginTime);
		match.setEndDate(matchEndDate, matchEndTime);

		assertEquals("10/05/2017 15:20:30", match.getStartDate().format(dateFormat));
		assertEquals("10/05/2017 15:25:30", match.getEndDate().format(dateFormat));
	}

	@Test
	public void listOfPlayers() {
		Match match = new Match(1238312);
		Player p1 = new Player("p1");
		Player p2 = new Player("p2");
		Player p3 = new Player("p3");
		Set<Player> list = new HashSet<Player>(Arrays.asList(p1, p2, p3));

		match.addPlayer(p1);
		match.addPlayer(p2);
		match.addPlayer(p3);

		assertTrue(list.containsAll((match.getPlayers())));
	}

	@Test
	public void playersAreUnique() {
		Match match = new Match(1238312);
		Player p1 = new Player("p1");
		Player p2 = new Player("p2");
		Player p3 = new Player("p3");
		Set<Player> list = new HashSet<Player>(Arrays.asList(p1, p2, p3));

		match.addPlayer(p1);
		match.addPlayer(p1);
		match.addPlayer(p2);
		match.addPlayer(p3);
		match.addPlayer(p3);

		assertTrue(list.containsAll((match.getPlayers())));
		assertTrue(match.getPlayers().containsAll((list)));
		assertEquals(3, list.size());
	}

	@Test
	public void listOfEvents() {
		Match match = new Match(12341234);
		Player p1 = new Player("p1");
		Player p2 = new Player("p2");
		LocalDateTime occurence = LocalDateTime.now();
		Event event1 = new Event(occurence);
		Event event2 = new Event(occurence.plusSeconds(30));

		event1.addKiller(p1);
		event1.addDeceased(p2);
		event2.addKiller(p2);
		event2.addDeceased(p1);
		match.addEvent(event1);
		match.addEvent(event2);

		assertEquals(event1, match.findEvent(event1));
		assertEquals(event2, match.findEvent(event2));
	}
}
