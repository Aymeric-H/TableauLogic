package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandlerExTwo extends DataHandler {

	/*
	 * Constructor of the class
	 * path => the path of the data file provided for this exercise
	 */
	public DataHandlerExTwo(String path) throws IOException {
		super(path);
	}
	
	
	/*
	 * Function which allows to write the data of the second exercise (One single row of Truth Table)
	 * expression => the expression input given by the user
	 * literals => a set with all the literals used in the expression given by the user
	 * nodes => a list with all the sub-expressions of the Truth Table
	 * answers => the values given by the user
	 * correct => the value telling whether the user gave the right answers or not
	 */
	public void write(String expression, java.util.Set<Literal> literals, ArrayList<Formula> nodes, String[] answers, boolean correct) throws IOException {
		FileWriter fileWriter = new FileWriter(this.file, true);
		String line = "";
		for (int i = 0; i < answers.length; i++) {
			if (i < literals.size()) {
				line += " " + literals.toArray()[i].toString().substring(4,literals.toArray()[i].toString().length() - 2).replace(" ", "") + ":" + answers[i].toString();
			}
			else{
				line += " " + nodes.get(i - literals.size()).toString().substring(4,nodes.get(i - literals.size()).toString().length() - 2).replace(" ", "") + ":" + answers[i];
			}
		}
		line = expression.replace(" ", "") + line;
		if (!this.isLineInDataFile(line)) {
			fileWriter.write(line + " " + correct + System.getProperty("line.separator"));
			fileWriter.flush();
		}
		fileWriter.close();		
	}
	
	
	/*
	 * Function which allows to know if the line is already present in the data file
	 * line => The line we want to know if it is already present in the data file
	 */
	public boolean isLineInDataFile(String line) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
		String lineInFile = bufferedReader.readLine();
		while (lineInFile != null) {
			if (lineInFile.equals(line)) {
				return true;
			}
			lineInFile = bufferedReader.readLine();
		}
		bufferedReader.close();
		return false;
	}

}
