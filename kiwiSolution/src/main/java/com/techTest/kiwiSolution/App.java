package com.techTest.kiwiSolution;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Kiwi Train Solution!
 * 
 * @author Ameet K
 *
 */
public class App {
	public static void main(String[] args) {

		PathFinder pathFinder;
		FileParser fPInpGrph;
		Graph graph = null;
		FileParser fPInpD;
		
		
		// Here we take the input of the graph that we want to work on
		fPInpGrph = new FileParser("graph.csv");

		
		try {
			graph = new Graph(fPInpGrph);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		pathFinder = new PathFinder(graph);

		// Here we take the input of the data(routes) that we want to work on
		fPInpD = new FileParser("input.csv");

		try {
			List<String> fields = fPInpD.getData();

			// Find shortest distance
			if ("distance".equalsIgnoreCase(fields.get(0))) {
				List<Vertex> vertices = new ArrayList<>();

				for (int i = 1; i < fields.size(); i++) {
					String str = fields.get(i);

					if (str == null) {
						System.out.println("No Such Path Exists");
						System.exit(0);
					} else {
						Vertex vrtx = graph.getPathList().get(str.toUpperCase());

						if (vrtx == null) {
							System.out.println("No Such Path Exists");
							System.exit(0);
						} else {
							vertices.add(vrtx);
						}
					}
				}

				int dist = pathFinder.getPathDistanceList(vertices);

				System.out.println("Distance found is " + dist);
			}
			// Find number of routes between two points with a max number of stops
			else if ("maximumStops".equalsIgnoreCase(fields.get(0))) {
				Vertex strtVertx, endtVertx;

				String start = fields.get(3);
				String end = fields.get(4);

				int minstp = Integer.parseInt(fields.get(1));
				int maxstp = Integer.parseInt(fields.get(2));

				if (start == null || end == null) {
					System.out.println("No Such Path Exists");
					System.exit(0);
				} else {
					strtVertx = graph.getPathList().get(start.toUpperCase());
					endtVertx = graph.getPathList().get(end.toUpperCase());

					if (strtVertx == null || endtVertx == null) {
						System.out.println("No Such Path Exists");
						System.exit(0);
					} else {
						int trips = pathFinder.pathBtwnPoints(strtVertx, endtVertx, minstp, maxstp);
						System.out.println("Total trips are " + trips);
					}
				}

			}
			// Find number of routes between two points with a maximum distance
			else if ("maximumdistance".equalsIgnoreCase(fields.get(0))) {
				Vertex origin, destination;

				String o = fields.get(2);
				String d = fields.get(3);

				int maxDistance = Integer.parseInt(fields.get(1));

				if (o == null || d == null) {
					System.out.println("No Such Path Exists");
					System.exit(0);
				} else {
					origin = graph.getPathList().get(o.toUpperCase());
					destination = graph.getPathList().get(d.toUpperCase());

					if (origin == null || destination == null) {
						System.out.println("No Such Path Exists");
						System.exit(0);
					} else {
						int trips = pathFinder.numberOfPaths(origin, destination, maxDistance);
						System.out.println("Total trips are " + trips);
					}
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(1);
		} catch (Exception e) {
			System.out.println("No Such Path Exists");
			System.exit(0);
		}

	}// main method

}
