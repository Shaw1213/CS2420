package test1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Contains several methods for solving problems on generic, directed, unweighted, sparse graphs.
 * 
 * @author Prof. Parker & YiChong Chen & Yang Hong
 * @version October 19, 2023
 */
public class GraphUtility {
	/**
	 * To check if there is a path between the item or not.
	 *
	 * @param sources, destinations, sources data, and destinations data
	 * @return  true or false
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException("size don't match");
		}
		if (!(sources.contains(srcData) || destinations.contains(srcData)) ||
				!(sources.contains(dstData) || destinations.contains(dstData))) {
			throw new IllegalArgumentException("it doesn't contain the data");
		}

		Graph<Type> graph = new Graph<>(sources, destinations);
		return graph.DFS(srcData, dstData);
	}

	/**
	 * To calculate the shortest path between two items.
	 *
	 * @param sources, destinations, sources data, and destinations data
	 * @return  an integer -- represent the shortest path
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException();
		}
		if (!(sources.contains(srcData) || destinations.contains(srcData)) ||
				!(sources.contains(dstData) || destinations.contains(dstData))) {
			throw new IllegalArgumentException();
		}

		Graph<Type> graph = new Graph<>(sources, destinations);
		List<Type> shortestPath = graph.BFS(srcData,dstData);
		if (shortestPath.size() == 0)
			throw new IllegalArgumentException("no path");
		return shortestPath;
	}

	/**
	 * To sort the sources and destinations together. this can give you a basic map of the combination on two.
	 *
	 * @param sources, destinations
	 * @return  a sorted list
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException();
		}

		Graph<Type> graph = new Graph<>(sources, destinations);
		return graph.topological();
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments 
	 * --accepts one edge per line or edges terminated with ; 
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename - name of the DOT file
	 * @param sources - empty ArrayList, when method returns it is a valid
	 *        "sources" list that can be passed to the public methods in this
	 *        class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *        "destinations" list that can be passed to the public methods in
	 *        this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while(scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if(line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if(edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while(scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for(int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if(vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if(vertex2.equals(""))
					continue;

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if(substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}
}