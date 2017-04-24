package br.com.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogLoader {

	private static final String REGEX_NEW_MATCH = "New\\smatch\\s(?<matchId>\\d+)\\shas\\sstarted";
	private static final String REGEX_END_MATCH = "[mM]atch\\s(?<matchId>\\d+)\\shas\\sended";
	private static final String REGEX_EVENT = "(?<killer>[a-zA-Z0-9]+)\\skilled\\s(?<deceased>[a-zA-Z0-9]+)\\susing\\s(?<weapon>[a-zA-Z0-9]+)";
	private static final String REGEX_WORLD_EVENT = "(?<killer><WORLD>|<world>)\\skilled\\s(?<deceased>[a-zA-Z0-9]+)\\sby\\s(?<weapon>[a-zA-Z0-9]+)";
	private static final String REGEX_DATE = "(?<date>(?<day>0?[1-9]|[12][0-9]|3[01])/(?<month>0?[1-9]|1[0-2])/(?<year>20\\d{2}))";
	private static final String REGEX_TIME = "(?<time>(?<hour>[01]\\d|2[0-3]):(?<minute>[0-5]\\d):(?<second>[0-5]\\d))";
	private static final String REGEX_SEPARATOR = "\\s-\\s";
	private static final String REGEX_DATE_TIME = "(?<dateTime>" + REGEX_DATE + "\\s" + REGEX_TIME + ")";

	private static final String NEW_MATCH = REGEX_DATE_TIME + REGEX_SEPARATOR + REGEX_NEW_MATCH;
	private static final String END_MATCH = REGEX_DATE_TIME + REGEX_SEPARATOR + REGEX_END_MATCH;
	private static final String EVENT = REGEX_DATE_TIME + REGEX_SEPARATOR + REGEX_EVENT;
	private static final String WORLD_EVENT = REGEX_DATE_TIME + REGEX_SEPARATOR + REGEX_WORLD_EVENT;

	private List<Match> matches;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private Match match;

	public void loadLog(Path file) {
		matches = new ArrayList<Match>();

		try (BufferedReader br = Files.newBufferedReader(file)) {
			String line;
			while ((line = br.readLine()) != null) {
				execute(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void execute(String line) {
		Matcher matcherRegex;
		if (line.matches(NEW_MATCH)) {
			matcherRegex = generateRegexGroup(NEW_MATCH, line);
			beginMatch(line, matcherRegex);
		} else if (line.matches(EVENT)) {
			matcherRegex = generateRegexGroup(EVENT, line);
			setEvent(line, matcherRegex);
		} else if (line.matches(WORLD_EVENT)) {
			matcherRegex = generateRegexGroup(WORLD_EVENT, line);
			setWorldEvent(line, matcherRegex);
		} else if (line.matches(END_MATCH)) {
			matcherRegex = generateRegexGroup(END_MATCH, line);
			endMatch(line, matcherRegex);
		}
	}

	public List<Match> getMatches() {
		return matches;
	}

	private void beginMatch(String line, Matcher m) {
		String id = m.group("matchId");
		LocalDateTime time = LocalDateTime.parse(m.group("dateTime"), dateFormatter);
		match = new Match(id);
		match.setStartDate(time);
	}

	private void setEvent(String line, Matcher m) {
		LocalDateTime ocurrence = LocalDateTime.parse(m.group("dateTime"), dateFormatter);
		Event event = new Event(ocurrence);
		
		Player killer = new Player(m.group("killer"));
		Player deceased = new Player(m.group("deceased"));		
		
		killer = match.findPlayer(killer);
		deceased = match.findPlayer(deceased);
		
		event.addKiller(killer);
		event.addDeceased(deceased);
		match.addEvent(event);
	}

	private void setWorldEvent(String line, Matcher m) {
		setEvent(line, m);
	}

	private void endMatch(String line, Matcher m) {
		if (match.getId() == Long.parseLong(m.group("matchId"))) {
			LocalDateTime time = LocalDateTime.parse(m.group("dateTime"), dateFormatter);
			match.setEndDate(time);
			matches.add(match);
			match = null;
		}
	}

	private Matcher generateRegexGroup(String regex, String line) {
		Matcher matcher = Pattern.compile(regex).matcher(line);
		matcher.matches();
		return matcher;
	}
}
