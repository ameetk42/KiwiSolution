package com.techTest.kiwiSolution;

import java.util.*;

/**
  * @author Ameet K
 */
public class PathFinder {

	private Graph graph;

	public PathFinder(Graph graph) {
		this.graph = graph;
	}

	public int getPathDistanceList(List<Vertex> vertexs) throws Exception {
		return drawPath(vertexs).getDistance();
	}


	private Path drawPath(List<Vertex> vertexs) throws Exception {
		Path path = new Path();

		for (Vertex n : vertexs) {

			if (!path.addVertex(graph.getPathList().get(n.getName()))) {
				throw new Exception("This Path does not exist.");
			}
		}

		return path;
	}//drawPath

//Calculate number of Routes between two Nodes within a maximum number stops.
	public int pathBtwnPoints(Vertex start, Vertex end, int minStp, int maxStp) throws Exception {

		// make paths for vertices we've already explored
		HashSet<Path> paths = new HashSet<>();

		Vertex[] visited = new Vertex[maxStp + 1];
		visited[0] = start;

		findPaths(paths, visited, end, 0, minStp, maxStp);

		if (paths.size() == 0)
			throw new Exception("NO SUCH ROUTE");

		return paths.size();
	}//pathBtwnPoints

	//Function to find all the paths from starting to end vertex
	private void findPaths(HashSet<Path> paths, Vertex[] visited, Vertex end, int limit, int minStops,
			int maxStops) {
		limit++;
		if (limit > maxStops) // Check if the limit is already reached
			return;

		Vertex current = visited[limit - 1];

		// iterate through the connecting vertices
		for (Vertex neightbour : current.getConnecVertices().keySet()) {

			// If we have reached last vertex, then we set the path.
			if (Objects.equals(neightbour, end) && limit >= minStops) {
				Path path = new Path();

				// it adds all the vertices that we have crossed
				for (int i = 0; i < limit; i++) {
					path.addVertex(visited[i]);
				}

				// it adds the last vertex.
				path.addVertex(end);

				paths.add(path);
			}
			visited[limit] = neightbour;

			//it calls it self again until reached

			findPaths(paths, visited, end, limit, minStops, maxStops);

		}
	}//findPaths

	//here Dijkstra algorithm is used
	public int shortestDistanceDijkstra(Vertex start, Vertex end) {

		// it gets the current object we are working on
		start = graph.getPathList().get(start.getName());
		end = graph.getPathList().get(end.getName());

		List<UnsettledVertex> nodes = new ArrayList<>();

		// Set all other vertexs' distance to Infinity
		for (Vertex n : graph.getPathList().values()) {
			if (Objects.equals(n, start)) {
				nodes.add(new UnsettledVertex(n, 0, false));
			} else {
				nodes.add(new UnsettledVertex(n, Integer.MAX_VALUE, false));
			}
		}

		// set others to unvisited
		int visited = 0;
		Path path = new Path();

		// +1 to allow start Vertex to be revisited
		while (visited < nodes.size() + 1) {
			// The vertex with the smallest distance that has NOT been visited
			Collections.sort(nodes);
			UnsettledVertex current = nodes.get(0);

			for (Map.Entry<Vertex, Integer> entry : current.vertex.getConnecVertices().entrySet()) {
				Vertex key = entry.getKey();
				Integer value = entry.getValue();

				UnsettledVertex neighbour = getUnsettledVertex(nodes, key).get();

				neighbour.distance = Math.min(current.distance + value, neighbour.distance);
			}

			// Adding shortest number to path
			path.addVertex(current.vertex);

			current.visited = true;

			visited++;
		}

		return path.getDistance();
	}//shortestDistanceDijkstra

	
	 // Find UnsettledVertex for a specific Vertex
	
	public Optional<UnsettledVertex> getUnsettledVertex(List<UnsettledVertex> unsettledVertexs, Vertex current) {
		for (UnsettledVertex t : unsettledVertexs) {
			if (t.vertex == current) {
				return Optional.ofNullable(t);
			}
		}
		return Optional.empty();
	}//getUnsettledVertex

	private class UnsettledVertex implements Comparable<UnsettledVertex> {
		Vertex vertex;
		int distance;
		boolean visited;

		public UnsettledVertex(Vertex vertex, int distance, boolean visited) {
			this.vertex = vertex;
			this.distance = distance;
			this.visited = visited;
		}//UnsettledVertex

		@Override
		public String toString() {
			return "UnsettledVertex{" + "vertex=" + vertex.getName() + ", distance=" + distance + ", visited=" + visited
					+ '}';
		}//toString

		@Override
		public int compareTo(UnsettledVertex o) {

			// If comparison is same, it sorts by distances
			if (this.visited == o.visited) {
				return this.distance < o.distance ? -1 : 1;
			} else if (this.visited) {
				return 1;
			} else {
				return -1;
			}
		}//compareTo
	}//class UnsettledVertex

	public int numberOfPaths(Vertex start, Vertex end, int maxDist) throws Exception {
		// Build up a set of routes we've explored
		HashSet<Path> paths = new HashSet<>();

		Path visited = new Path();
		visited.addVertex(start); // Add our starting point

		findPathsMaxDist(paths, visited, end, maxDist);

		if (paths.size() == 0)
			throw new Exception("NO SUCH ROUTE");

		return paths.size();
	}

	//function to find all paths from the starting Vertex to the end
	private void findPathsMaxDist(HashSet<Path> paths, Path visited, Vertex end, int maxDistance) {
		if (visited.getDistance() >= maxDistance) {
			return;
		}

		Vertex current = visited.getLast();
		if (visited.stops() > 1 && Objects.equals(current, end)) {
			paths.add(visited);
		}

		// Visit all connecting vertices
		for (Vertex neighbour : current.getConnecVertices().keySet()) {
			Path path = new Path();
			for (Vertex n : visited) {
				path.addVertex(n);
			}
			path.addVertex(neighbour);
			findPathsMaxDist(paths, path, end, maxDistance);
		}
	}//findPathsMaxDist

	
}//class
