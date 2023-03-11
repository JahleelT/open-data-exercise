package edu.nyu.cs;

// some basic java imports
import java.io.File;
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
		// System.out.println("Key pressed: " + key);
		// complete this method

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

		// complete this method - DELETE THE EXAMPLE CODE BELOW

		// remove the code below and replace with your own code that solves the problem indicated in the comments
		// example of how to create a marker at a specific location and place it on the map
		float lat = 40.737375365084105f; // latitude of a location of interest
		float lng = -74.00101207586745f; // longitude of a location of interest
		Location markerLocation = new Location(lat, lng); // create a Location object
		int pedestrianCount = 11024; // an example pedestrian count (in reality, you will get these from a file)
		float markerRadius = pedestrianCount * SCALE_FACTOR; // scale down the marker radius to look better on the map
		float[] markerColor = {255, 0, 0, 127}; // a color, specified as a combinatino of red, green, blue, and alpha (i.e. transparency), each represented as numbers between 0 and 255.
		MarkerBubble marker = new MarkerBubble(this, markerLocation, markerRadius, markerColor); // don't worry about the `this` keyword for now... just make sure it's there.
		map.addMarker(marker);
	}

	/**
	 * Adds markers to the map for the evening pedestrian counts in May 2021.
	 * These counts are in the second-to-last field in the CSV data file.  So we look at the second-to-last array element in our data array for these values.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021EveningCounts(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "May 2021 Evening Pedestrian Counts";
		// complete this method
	}

	/**
	 * Adds markers to the map for the difference between evening and morning pedestrian counts in May 2021.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021EveningMorningCountsDifference(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "Difference Between May 2021 Evening and Morning Pedestrian Counts";
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
		mapTitle = "Enter Custom Map 1 Title Here";
		// complete this method		
	}

	/**
	 * A data visualization of your own choosing.  
	 * Do some data analysis and map the results using markers.
	 * 
	 * @param data
	 */
	public void customVisualization2(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "Enter Custom Map 2 Title Here";
		// complete this method	
	}

	/**
	 * Opens a file and returns an array of the lines within the file, as Strings with their line breaks removed.
	 * 
	 * @param filepath The filepath to open
	 * @return A String array, where each String contains the text of a line of the file, with its line break removed.
	 * @throws FileNotFoundException
	 */
	public String[] getLinesFromFile(String filepath) {
		String[] lines = {"foo", "bar"};
		return lines;
		// delete the two lines above... they are placeholder only
		// complete this method
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
		// complete this method - DELETE THE EXAMPLE CODE BELOW

		// hint: ultimately, you want this function to return data structured something like the following (you can structure your array differently if you prefer)
		// in this example, the geospatial Point data (latitude and longitude), which is one field in the original CSV file, has been broken up into two fields... you would be wise to do this too.
		String[][] allLines = { 
			// the first two values are the latitude and longitude... these were a single field in the original CSV file
			{"-73.90459140730678","40.87919896648574","Bronx","1","1","Broadway","West 231st Street","Naples Terrace","N","1189","4094","2508","734","2646","2939","802","4015","2631","1125","4310","3420","1001","3475","2832","991","4262","2469","1010","3609","3128","863","4119","2217","997","4440","2687","1328","3820","2428","1288","3328","3365","1268","4315","2276","1210","4710","3825","1206","4590","3008","1220","4384","2641","1450","4646","2996","1788","4980","3033","1204","4520","2999","1246","4531","2686","1309","3642","2830","1916","5893","2776","1111","4044","2731","1271","4502","2899","1708","4464","2967","","","","486","2843","1754","630","3262","4710"},
			{"-73.92188432870218","40.82662794123294","Bronx","2","2","East 161st Street","Grand Concourse","Sheridan Avenue","Y","1511","3184","1971","1855","3754","1183","1136","2638","1522","1939","3283","1383","1351","3111","1304","1227","3137","2762","2077","3283","1409","1007","3069","1477","1734","3333","1772","2051","3525","1752","1233","1875","1912","2113","4099","1970","2278","4215","2288","2071","3890","1832","2206","4363","2315","1949","4435","2388","2318","4589","2483","2005","4790","2512","2053","4721","2311","2109","5485","2548","1848","4920","2143","2389","5952","2832","1749","5148","2156","2006","4723","1604","1702","4347","1576","780","1892","1287","1405","2097","8410"},
			{"-73.92785197149036","40.80034506063933","Harlem River Bridges","113","113","Triborough Bridge (Manhattan span)","midpoint","","N","17","35","34","11","44","24","30","44","16","30","200","23","37","44","23","20","174","66","12","39","55","36","205","64","10","45","11","7","119","39","26","21","49","6","33","15","12","42","16","13","31","40","14","32","10","21","42","20","19","36","14","17","40","28","10","18","8","21","43","21","7","19","5","16","38","24","6","14","4","12","15","6","","","","","","","23","52","6437"},
			{"-73.93686603590555","40.78611224350854","Harlem River Bridges","114","114","Wards Island Bridge","midpoint","","N","57","207","71","63","186","149","45","203","113","80","190","120","33","213","324","43","151","173","37","169","674","77","205","913","32","66","70","62","189","936","78","249","439","102","460","569","191","455","435","92","514","594","164","527","312","123","458","564","189","539","312","117","424","581","160","484","300","159","490","587","169","493","312","178","519","608","187","543","351","213","490","263","","","","","","","237","405","6353"}
		};
		return allLines;

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
