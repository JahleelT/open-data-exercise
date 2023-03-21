package edu.nyu.cs;

// some basic java imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.lang3.SystemUtils;

import java.util.regex.Matcher;

// some imports used by the UnfoldingMap library
import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.providers.OpenStreetMap.*;
import de.fhpotsdam.unfolding.providers.MapBox;
import de.fhpotsdam.unfolding.providers.Google.*;
import de.fhpotsdam.unfolding.providers.Microsoft;
// import de.fhpotsdam.unfolding.utils.ScreenPosition;


/**
 * A program that pops open an interactive map.
 */
public class App extends PApplet {
	/****************************************************************/
	/*                  BEGIN - DON'T MODIFY THIS CODE              */
	/****************************************************************/
	UnfoldingMap map; // will be a reference to the actual map
	String mapTitle; // will hold the title of the map
	final float SCALE_FACTOR = 0.0002f; // a factor used to scale pedestrian counts to calculate a reasonable radius for a bubble marker on the map
	final int DEFAULT_ZOOM_LEVEL = 11;
	final Location DEFAULT_LOCATION = new Location(40.7286683f, -73.997895f); // a hard-coded NYC location to start with
	String[][] data; // will hold data extracted from the CSV data file
	/****************************************************************/
	/*                    END - DON'T MODIFY THIS CODE              */
	/****************************************************************/

	/**
	 * This method is automatically called every time the user presses a key while viewing the map.
	 * The `key` variable (type char) is automatically is assigned the value of the key that was pressed.
	 * Complete the functions called from here, such that:
	 * 	- when the user presses the `1` key, the code calls the showMay2021MorningCounts method to show the morning counts in May 2021, with blue bubble markers on the map.
	 * 	- when the user presses the `2` key, the code calls the showMay2021EveningCounts method to show the evening counts in May 2021, with blue bubble markers on the map.
	 * 	- when the user presses the `3` key, the code calls the showMay2021EveningMorningCountsDifferencemethod to show the difference between the evening and morning counts in May 2021.  If the evening count is greater, the marker should be a green bubble, otherwise, the marker should be a red bubble.
	 * 	- when the user presses the `4` key, the code calls the showMay2021VersusMay2019Counts method to show the difference between the average of the evening and morning counts in May 2021 and the average of the evening and morning counts in May 2019.  If the counts for 2021 are greater, the marker should be a green bubble, otherwise, the marker should be a red bubble.
	 * 	- when the user presses the `5` key, the code calls the customVisualization1 method to show data of your choosing, visualized with marker types of your choosing.
	 * 	- when the user presses the `6` key, the code calls the customVisualization2 method to show data of your choosing, visualized with marker types of your choosing.
	 */
	public void keyPressed() {
		Scanner scn = new Scanner(System.in);
		char key = scn.next().charAt(0); 
		System.out.println("Key pressed: " + key);
		if (key == 1) {
			showMay2021MorningCounts(data);
		}
		else if (key == 2) {
			showMay2021EveningCounts(data);
		}
		else if (key == 3) {
			showMay2021EveningMorningCountsDifference(data);
		}
		else if (key == 4) {
			showMay2021VersusMay2019Counts(data); 
		}
		else if (key == 5) {
			customVisualization1(data);
		}
		else if (key == 6) {
			customVisualization2(data);
		}
		else {
			System.out.println("Try pressing another key");
		}
		scn.close();
	}

	/**
	 * Adds markers to the map for the morning pedestrian counts in May 2021.
	 * These counts are in the second-to-last field in the CSV data file.  So we look at the second-to-last array element in our data array for these values.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021MorningCounts(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "May 2021 Morning Pedestrian Counts";
		int dataIndex = (data[0].length - 3); // both the show morning and show evening counts say second-to-last array element so for the morning I used 3rd to last after manually going to look at the second to last and third to last elements on the website
		for (int i = 0 ; i < data.length ; i++) {
			String pedestrianCount = data[i][dataIndex];
			int count = Integer.parseInt(pedestrianCount);
			String lat = data[i][0];
			double latitude = Double.parseDouble(lat);
			String lng = data[i][1];
			double longitude = Double.parseDouble(lng);
			Location markerLocation = new Location(latitude, longitude);
			float markerRadius = count * SCALE_FACTOR;
			float[] markerColor = {255, 0, 0, 127};
			MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor);
			map.addMarker(marker);
		}}
	

	/**
	 * Adds markers to the map for the evening pedestrian counts in May 2021.
	 * These counts are in the second-to-last field in the CSV data file.  So we look at the second-to-last array element in our data array for these values.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021EveningCounts(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "May 2021 Evening Pedestrian Counts";
		int dataIndex = (data[0].length - 2);
		for (int i = 0 ; i < data.length ; i++) {
			String pedestrianCount = data[i][dataIndex];
			int count = Integer.parseInt(pedestrianCount);
			String lat = data[i][0];
			double latitude = Double.parseDouble(lat);
			String lng = data[i][1];
			double longitude = Double.parseDouble(lng);
			Location markerLocation = new Location(latitude, longitude);
			float markerRadius = count * SCALE_FACTOR;
			float[] markerColor = {200, 50, 0, 127};
			MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor);
			map.addMarker(marker);
		}}
		// complete this method

	/**
	 * Adds markers to the map for the difference between evening and morning pedestrian counts in May 2021.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021EveningMorningCountsDifference(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "Difference Between May 2021 Evening and Morning Pedestrian Counts";
		int eveningIndex = data[0].length - 2;
		int morningIndex = data[0].length - 3;
		for( int i = 0; i < data.length; i++) {
			String eveningCount = data[i][eveningIndex];
			String morningCount = data[i][morningIndex];
			int eveCount = Integer.parseInt(eveningCount);
			int mornCount = Integer.parseInt(morningCount);
			int difference = eveCount - mornCount;
			double lat = Double.parseDouble(data[i][0]);
			double lng = Double.parseDouble(data[i][1]);
			if ( difference > 0) {
				Location markerLocation = new Location(lat, lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {0, 255, 0, 127};
				MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor);
				map.addMarker(marker);
			}
			else if (difference < 0) { //not sure if this is even necessary to add since there was quite literally always more people in the evening than the morning, but it is here anyways.
				Location markerLocation = new Location(lat, lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {255, 0, 255, 127};
				MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor);
				map.addMarker(marker);
			}
		}
		// complete this method
	}

	/**
	 * Adds markers to the map for the difference between the average pedestrian count in May 2021 and the average pedestrian count in May 2019.
	 * Note that some pedestrian counts were not done in May 2019, and the data is blank.  
	 * Do not put a marker at those locations with missing data.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021VersusMay2019Counts(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "Difference Between May 2021 and May 2019 Pedestrian Counts";
		//The following is for the 2021 numbers
		int may2021eveningIndex = data[0].length - 2;
		int may2021morningIndex = data[0].length - 3;
		int may2019eveningIndex = data[0].length - 8;
		int may2019morningIndex = data[0].length - 9;
		for( int i = 0; i < data.length; i++) {
			String may2021eveningCount = data[i][may2021eveningIndex];
			String may2021morningCount = data[i][may2021morningIndex];
			int may2021eveCount = Integer.parseInt(may2021eveningCount);
			int may2021mornCount = Integer.parseInt(may2021morningCount);
			int may2021average = (may2021eveCount + may2021mornCount) / 2;
			double may2021Lat = Double.parseDouble(data[i][0]);
			double may2021Lng = Double.parseDouble(data[i][1]);
			String may2019eveningCount = data[i][may2019eveningIndex];
			String may2019morningCount = data[i][may2019morningIndex];
			int may2019eveCount = Integer.parseInt(may2019eveningCount);
			int may2019mornCount = Integer.parseInt(may2019morningCount);
			int may2019average = (may2019eveCount + may2019mornCount) / 2;
			int difference = may2021average - may2019average;
			if (!(may2019average == 0)) {
				Location markerLocation = new Location(may2021Lat, may2021Lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {0, 255, 0, 127};
				MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor);
				map.addMarker(marker);
			}
			else {
				//nothing will be performed here so im going to put code to start the marker, but not add the marker
				Location markerLocation = new Location(may2021Lat, may2021Lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {50, 50, 50, 127};
			}}
		// complete this method
	}

	/**
	 * A data visualization of your own choosing.  
	 * Do some data analysis and map the results using markers.
	 * 
	 * @param data
	 */
	public void customVisualization1(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "Difference Between May 2021 and May 2012 Counts";
		int may2021eveningIndex = data[0].length - 2;
		int may2021morningIndex = data[0].length - 3;
		int may2012morningIndex = (data[0].length - 48);
		int may2012eveningIndex = (data[0].length - 47);
		for (int i = 0 ; i < data.length ; i++) {
			String may2021eveningCount = data[i][may2021eveningIndex];
			String may2021morningCount = data[i][may2021morningIndex];
			int may2021eveCount = Integer.parseInt(may2021eveningCount);
			int may2021mornCount = Integer.parseInt(may2021morningCount);
			int may2021average = (may2021eveCount + may2021mornCount) / 2;
			double may2021Lat = Double.parseDouble(data[i][0]);
			double may2021Lng = Double.parseDouble(data[i][1]);
			String may2012eveningCount = data[i][may2012eveningIndex];
			String may2012morningCount = data[i][may2012morningIndex];
			int may2012eveCount = Integer.parseInt(may2012eveningCount);
			int may2012mornCount = Integer.parseInt(may2012morningCount);
			int may2012average = (may2012eveCount + may2012mornCount) / 2;
			int difference = may2021average - may2012average;
			if (!(may2012average == 0) && (difference > 0) && ((difference % 3) == 0)) {
				Location markerLocation = new Location(may2021Lat, may2021Lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {0, 255, 0, 127};
				MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor);
				map.addMarker(marker);
			}
			else if (difference == 0) {
				Location markerLocation = new Location(may2021Lat, may2021Lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {50, 50, 50, 127};
				MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor);
				map.addMarker(marker);
			}
			else if (difference > 0) {
				Location markerLocation = new Location(may2021Lat, may2021Lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {75, 255, 0, 127};
				MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor);
				map.addMarker(marker);
			}
			else { //purposely doing nothing with this block other than establishing some important data for the marker if it were to be placed on the map.
				//nothing will be performed here so im going to put code to start the marker, but not add the marker
				Location markerLocation = new Location(may2021Lat, may2021Lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {255, 255, 255, 127};
			}
		}
			}
		// complete this method		

	/**
	 * A data visualization of your own choosing.  
	 * Do some data analysis and map the results using markers.
	 * 
	 * @param data
	 */
	public void customVisualization2(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "Difference Between September 2018 and September 2017 Counts";
		int sept2018eveningIndex = data[0].length - 11;
		int sept2018morningIndex = data[0].length - 12;
		int sept2017morningIndex = (data[0].length - 18);
		int sept2017eveningIndex = (data[0].length - 17);
		for (int i = 0 ; i < data.length ; i++) {
			String sept2018eveningCount = data[i][sept2018eveningIndex];
			String sept2018morningCount = data[i][sept2018morningIndex];
			int sept2018eveCount = Integer.parseInt(sept2018eveningCount);
			int sept2018mornCount = Integer.parseInt(sept2018morningCount);
			int sept2018average = (sept2018eveCount + sept2018mornCount) / 2;
			double sept2018Lat = Double.parseDouble(data[i][0]);
			double sept2018Lng = Double.parseDouble(data[i][1]);
			String sept2017eveningCount = data[i][sept2017eveningIndex];
			String sept2017morningCount = data[i][sept2017morningIndex];
			int sept2017eveCount = Integer.parseInt(sept2017eveningCount);
			int sept2017mornCount = Integer.parseInt(sept2017morningCount);
			int sept2017average = (sept2017eveCount + sept2017mornCount) / 2;
			int difference = sept2018average - sept2017average;
			if (!(sept2017average == 0) && (difference > 0) && !(sept2018average == 0) && ((difference % 2) == 0)){
				Location markerLocation = new Location(sept2018Lat, sept2018Lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {0, 255, 0, 127};
				MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor);
				map.addMarker(marker);
			}
			else if (!(sept2017average == 0) && (difference > 0) && !(sept2018average == 0)) {
				Location markerLocation = new Location(sept2018Lat, sept2018Lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {0, 255, 75, 127};
				MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor);
				map.addMarker(marker);
			}
			else if (difference == 0) {
				Location markerLocation = new Location(sept2018Lat, sept2018Lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {50, 50, 50, 127};
				MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor);
				map.addMarker(marker);
			}
			else { //purposely doing nothing with this block other than establishing some important data for the marker if it were to be placed on the map.
				//nothing will be performed here so im going to put code to start the marker, but not add the marker
				Location markerLocation = new Location(sept2018Lat, sept2018Lng);
				float markerRadius = difference * SCALE_FACTOR;
				float[] markerColor = {255, 255, 255, 127};
			}
		}
		// complete this method	
	}

	/**
	 * Opens a file and returns an array of the lines within the file, as Strings with their line breaks removed.
	 * 
	 * @param filepath The filepath to open
	 * @return A String array, where each String contains the text of a line of the file, with its line break removed.
	 * @throws FileNotFoundException
	 */

	public String[] getLinesFromFile(String filepath) throws FileNotFoundException {
		File file = new File(filepath);
		Scanner scn = new Scanner(new FileReader(filepath));
		String lineSeparator = Pattern.quote(System.getProperty("line.separator"));
		String getContent = scn.useDelimiter("\\A").next();
		String [] finalList = getContent.split(lineSeparator);
		scn.close();
		return finalList;
	}
	/**
	 * Takes an array of lines of text in comma-separated values (CSV) format and splits each line into a sub-array of data fields.
	 * For example, an argument such as {"1,2,3", "100,200,300"} could result in a returned array { {"1", "2", "3"}, {"100", "200", "300"} }
	 * This method must skip any lines that don't contain mappable data (i.e. don't have any geospatial data in them) 
	 * and do any other cleanup of the data necessary for it to be easily mapped by other code in the program.
	 *
	 * @param lines A String array of lines of text, where each line is in comma-separated values (CSV) format.
	 * @return A two-dimensional String array, where each inner array contains the data from one of the lines, split by commas.
	 */
	public String[][] getDataFromLines(String[] lines) {
		String[][] all_lines = new String[lines.length][];
		for (int i = 0; i < lines.length; i++) {
			if ((lines[i] != null) && !lines[i].isEmpty() && !lines[i].trim().isEmpty()	) {
				all_lines[i] = lines[i].split(",");
			}
		}
		return all_lines;

	}


	/****************************************************************/
	/**** YOU WILL MOST LIKELY NOT NEED TO EDIT ANYTHING BELOW HERE */
	/****               Proceed at your own peril!                  */
	/****************************************************************/

	/**
	 * This method will be automatically called when the program runs
	 * Put any initial setup of the window, the map, and markers here.
	 */
	public void setup() {
		size(1200, 800, P2D); // set the map window size, using the OpenGL 2D rendering engine
		// size(1200, 800); // set the map window size, using Java's default rendering engine (try this if the OpenGL doesn't work for you)
		map = getMap(); // create the map and store it in the global-ish map variable

		// load the data from the file... you will have to complete the functions called to make sure this works
		try {
			String cwd = Paths.get("").toAbsolutePath().toString(); // the current working directory as an absolute path
			String path = Paths.get(cwd, "data", "PedCountLocationsMay2015.csv").toString(); // e.g "data/PedCountLocationsMay2015.csv" on Mac/Unix vs. "data\PedCountLocationsMay2015.csv" on Windows
			String[] lines = getLinesFromFile(path); // get an array of the lines from the file.
			data = getDataFromLines(lines); // get a two-dimensional array of the data in these lines; complete the getDataFromLines method so the data from the file is returned appropriately
			// System.out.println(Arrays.deepToString(data)); // for debugging

			// automatically zoom and pan into the New York City location
			map.zoomAndPanTo(DEFAULT_ZOOM_LEVEL, DEFAULT_LOCATION);

			// by default, show markers for the morning counts in May 2021 (the third-to-last field in the CSV file)
			showMay2021MorningCounts(data);

			// various ways to zoom in / out
			// map.zoomLevelOut();
			// map.zoomLevelIn();
			// map.zoomIn();
			// map.zoomOut();

			// it's possible to pan to a location or a position on the screen
			// map.panTo(nycLocation); // pan to NYC
			// ScreenPosition screenPosition = new ScreenPosition(100, 100);
			// map.panTo(screenPosition); // pan to a position on the screen

			// zoom and pan into a location
			// int zoomLevel = 10;
			// map.zoomAndPanTo(zoomLevel, nycLocation);

			// it is possible to restrict zooming and panning
			// float maxPanningDistance = 30; // in km
			// map.setPanningRestriction(nycLocation, maxPanningDistance);
			// map.setZoomRange(5, 22);


		}
		catch (Exception e) {
			System.out.println("Error: could not load data from file: " + e);
		}

	} // setup

	/**
	 * Create a map using a publicly-available map provider.
	 * There will usually not be a need to modify this method. However, in some cases, you may need to adjust the assignment of the `map` variable.
	 * If there are error messages related to the Map Provider or with loading the map tile image files, try all of the other commented-out map providers to see if one works.
	 * 
	 * @return A map object.
	 */
	private UnfoldingMap getMap() {
		// not all map providers work on all computers.
		// if you have trouble with the one selected, try the others one-by-one to see which one works for you.
		map = new UnfoldingMap(this, new Microsoft.RoadProvider());
		// map = new UnfoldingMap(this, new Microsoft.AerialProvider());
		// map = new UnfoldingMap(this, new GoogleMapProvider());
		// map = new UnfoldingMap(this);
		// map = new UnfoldingMap(this, new OpenStreetMapProvider());

		// enable some interactive behaviors
		MapUtils.createDefaultEventDispatcher(this, map);
		map.setTweening(true);
		map.zoomToLevel(DEFAULT_ZOOM_LEVEL);

		return map;
	}
	
	/**
	 * This method is called automatically to draw the map.
	 * This method is given to you.
	 * There is no need to modify this method.
	 */
	public void draw() {
		background(0);
		map.draw();
		drawTitle();
	}

	/**
	 * Clear the map of all markers.
	 * This method is given to you.
	 * There is no need to modify this method.
	 */
	public void clearMap() {
		map.getMarkers().clear();
	}

	/**
	 * Draw the title of the map at the bottom of the screen
	 */
	public void drawTitle() {
		fill(0);
		noStroke();
		rect(0, height-40, width, height-40); // black rectangle
		textAlign(CENTER);
		fill(255);
		text(mapTitle, width/2, height-15); // white centered text
	}

	/**
	 * The main method is automatically called when the program runs.
	 * This method is given to you.
	 * There is no need to modify this method.
	 * @param args A String array of command-line arguments.
	 */
	public static void main(String[] args) {
		System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n", SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
		boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
		if (!isGoodJDK) {
			System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
		}
		else {
			PApplet.main("edu.nyu.cs.App"); // do not modify this!
		}
	}

}
