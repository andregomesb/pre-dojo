package br.com.game;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {

	public static void main(String[] args) {
		Path path = FileSystems.getDefault().getPath("src/test", "log.txt");
		LogAnalyzer.analyze(path);
	}
}
