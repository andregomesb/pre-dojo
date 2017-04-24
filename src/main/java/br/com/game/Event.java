package br.com.game;

import java.time.LocalDateTime;

public class Event {

	private Player killer;
	private Player deceased;
	private LocalDateTime occurence;

	public Event(LocalDateTime ocurrence) {
		this.occurence = ocurrence;
	}

	public void addKiller(Player killer) {
		killer.addKill();
		this.killer = killer;
	}

	public void addDeceased(Player deceased) {
		deceased.addDeath();
		this.deceased = deceased;
	}

	public Player getKiller() {
		return killer;
	}

	public Player getDeceased() {
		return deceased;
	}

	public LocalDateTime getOcurrence() {
		return this.occurence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((occurence == null) ? 0 : occurence.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (occurence == null) {
			if (other.occurence != null)
				return false;
		} else if (!occurence.equals(other.occurence))
			return false;
		return true;
	}
}
