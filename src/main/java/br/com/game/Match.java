package br.com.game;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Match {

	private long id;
	private Set<Player> players;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private List<Event> events;

	public Match(String id) {
		this(Long.parseLong(id));
	}

	public Match(long id) {
		this.id = id;
		this.players = new HashSet<Player>();
		this.events = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setStartDate(LocalDate matchBeginDate, LocalTime matchBeginTime) {
		setStartDate(LocalDateTime.of(matchBeginDate, matchBeginTime));
	}

	public void setStartDate(LocalDateTime matchBeginDateTime) {
		this.startDate = matchBeginDateTime;
	}
	
	public void setEndDate(LocalDate matchEndDate, LocalTime matchEndTime) {
		setEndDate(LocalDateTime.of(matchEndDate, matchEndTime));
	}
	
	public void setEndDate(LocalDateTime matchEndDateTime) {
		this.endDate = matchEndDateTime;
	}


	public LocalDateTime getStartDate() {
		return this.startDate;
	}

	public LocalDateTime getEndDate() {
		return this.endDate;
	}

	public void addPlayer(Player player) {
		this.players.add(player);
	}

	public HashSet<Player> getPlayers() {
		return new HashSet<Player>(this.players);
	}
	
	public Player findPlayer(Player player){
		if(players.contains(player)){
			return players.stream().filter(p -> p.equals(player)).findFirst().orElse(null);
		}
		addPlayer(player);
		return player;
	}

	public Event findEvent(Event event) {
		if (events.contains(event)) {
			return events.get(events.indexOf(event));
		}
		return null;
	}

	public void addEvent(Event event) {
		events.add(event);
	}

	public ArrayList<Event> getEvents() {
		return new ArrayList<Event>(events);
	}
}
