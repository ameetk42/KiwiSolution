package com.techTest.kiwiSolution;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Ameet K
 */
public class FileParser {

	static final String seperator = ",";
	private String fileN;

	public FileParser(String filename) {
		this.fileN = filename;
	}

	public List<String> getData() throws Exception {

		if (Objects.isNull(fileN) || fileN.isEmpty()) {
			throw new Exception("Please Provide a file name.");
		}

		File f = new File(fileN);

		List<String> data = new ArrayList<>();

		try (Scanner scanner = new Scanner(f)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				String[] v = line.split(seperator);

				for (String s : v) {
					data.add(s.trim());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}// getData

	public HashMap<String, Vertex> getPathList() throws Exception {

		HashMap<String, Vertex> vertices = new HashMap<>();

		for (String s : getData()) {

			char from = s.charAt(0);
			char to = s.charAt(1);

			int distance = Integer.parseInt(s.substring(2));

			vertices.putIfAbsent(String.valueOf(from), new Vertex(String.valueOf(from)));
			vertices.putIfAbsent(String.valueOf(to), new Vertex(String.valueOf(to)));

			Vertex start = vertices.get(String.valueOf(from));
			Vertex end = vertices.get(String.valueOf(to));

			start.addConnecVertices(end, distance);
		}

		return vertices;

	}// getPathList

}//FileParser
