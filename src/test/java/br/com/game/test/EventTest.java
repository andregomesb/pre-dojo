package br.com.game.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;

import br.com.game.Event;
import br.com.game.Player;

public class EventTest {

	@Test
	public void createEvent() {
		LocalDate date = LocalDate.of(2017, 05, 10);
		LocalTime time = LocalTime.of(15, 20, 30);
		LocalDateTime occurence = LocalDateTime.of(date, time);
		
		Event event = new Event(occurence);
		Player killer = new Player("Assassin");
		Player deceased = new Player("Killed");

		event.addKiller(killer);
		event.addDeceased(deceased);

		assertEquals(killer, event.getKiller());
		assertEquals(deceased, event.getDeceased());
		assertEquals(occurence, event.getOcurrence());
	}
}
