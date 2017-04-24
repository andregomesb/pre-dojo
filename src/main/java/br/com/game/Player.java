package br.com.game;

public class Player implements Comparable<Player> {

	private String name;
	private int kills;
	private int deaths;

	public Player(String name) {
		this.name = name;
		this.kills = 0;
		this.deaths = 0;
	}

	public void addDeath() {
		this.deaths++;
	}

	public void addKill() {
		this.kills++;
	}

	public int getKills() {
		return this.kills;
	}

	public int getDeaths() {
		return this.deaths;
	}

	public Object getName() {
		return this.name;
	}

	@Override
	public int compareTo(Player p) {
		int kill_difference = kills - p.getKills();
		if (kill_difference == 0) {
			return -(deaths - p.getDeaths());
		}
		return kill_difference;
	}

	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Player player = (Player) obj;
		return name.equals(player.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
