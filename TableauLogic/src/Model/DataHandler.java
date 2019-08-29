package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataHandler {

	protected String path;
	protected File file;
	
	/*
	 * Constructor of the class DataHandler
	 * path => the path (a String) of the file where the data is stored
	 */
	public DataHandler(String path) throws IOException{
		// If the user has a H-Drive (it means he's certainly on the university computer) we store the data
		// on this Drive
		if (path.charAt(0) == 'H') {
			this.path = Paths.get(path).toString();
		}
		// In the other case we store it on the current folder (the one of the app)
		else{
			this.path = Paths.get("").toAbsolutePath().toString() + "/" + path;
		}
		// We create the data file if it doesn't already exist
		this.file = new File(this.path);
		if (!this.file.exists()) {
			this.file.createNewFile();
		}
	}
	
	/*
	 * Function which allows to know whether an expression is already written in the file or not
	 * expression => the expression we want to know about
	 */
	public boolean isExpressionInDataFile(String expression) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
		String line = bufferedReader.readLine();
		while (line != null) {
			String[] data = line.split(" ");
			if (data[0].equals(expression.replace(" ", ""))) {
				bufferedReader.close();
				return true;
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return false;
	}
	
	/*
	 * Function which allows to get all the data present in the data file (an array with every lines of data)
	 */
	public ArrayList<String[]> getData() throws IOException{
		ArrayList<String[]> data = new ArrayList<String[]>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
		String line = bufferedReader.readLine();
		while (line != null) {
			data.add(line.split(" "));
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return data;
	}
	
	/*
	 * Function which allows to get the maximum number of columns (sub-formulas) an expression can have in the data file
	 */
	public int getMaxNumberOfColumns() throws IOException{
		int res = 0;
		BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
		String line = bufferedReader.readLine();
		while (line != null) {
			String[] splitLine = line.split(" ");
			if (splitLine.length > res) {
				res = splitLine.length;
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return res;
	}
	
}
