package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandlerExThree extends DataHandler{

	public DataHandlerExThree(String path) throws IOException {
		super(path);
	}
	
	/*
	 * The Data File is like : expression - sub-formula : number of mistakes
	 * Function which allows to write data in the data file
	 * data => the data (as String) to write in the file
	 */
	public void write(String data) throws IOException{
		// We write the data only if the expression isn't already in the file
		if (!this.isExpressionInDataFile(data.split(" ")[0])) {
			FileWriter fileWriter = new FileWriter(this.file, true);
			fileWriter.write(data + System.getProperty("line.separator"));
			fileWriter.flush();
			fileWriter.close();
		}
	}
	
	/*
	 * Function which allows to write data in the data file (Initialization of the number of mistakes for an expression)
	 * expression => the data (an expression)
	 * numberOfColumns => the number of columns needed to display all the data wanted (one data for each sub-formula)
	 */
	public void write(String expression, int numberOfColumns) throws IOException{
		if (!this.isExpressionInDataFile(expression)) {
			FileWriter fileWriter = new FileWriter(this.file, true);
			String data = "";
			for (int i = 0; i < numberOfColumns; i++) {
				data += " 0";
			}
			fileWriter.write(expression.replace(" ", "") + data + System.getProperty("line.separator"));
			fileWriter.flush();
			fileWriter.close();
		}
	}
	
	/*
	 * Function which allows to write data in the data file (Initialization of the data "sub-formula:0")
	 * expression => the data (an expression)
	 * subFormulas => an array of every sub-formulas in the expression provided
	 */
	public void write(String expression, ArrayList<Formula> subFormulas) throws IOException{
		if (!this.isExpressionInDataFile(expression)) {
			FileWriter fileWriter = new FileWriter(this.file, true);
			String data = "";
			for (Formula subFormula : subFormulas) {
				data += " " + subFormula.getFormulaString().replace(" ", "") + ":0";
			}
			fileWriter.write(expression.replace(" ", "") + data + System.getProperty("line.separator"));
			fileWriter.flush();
			fileWriter.close();
		}
	}
	
	/*
	 * Function which allows to update the data for an expression given
	 * expression => the expression we want to update in the data file
	 * column => the index of the column (sub-formula) data we want to update
	 * numberOfMistakes => the number of mistakes for a sub-formula to add to the data
	 */
	public void update(String expression, int column, int numberOfMistakes) throws IOException{
		String content = "";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
		String line = bufferedReader.readLine();
		while (line != null) {
			String[] data = line.split(" ");
			if (data[0].equals(expression.replace(" ", ""))) {
				int value = Integer.valueOf(data[column + 1].split(":")[1]) + numberOfMistakes;
				data[column + 1] = data[column + 1].split(":")[0] + ":" + String.valueOf(value);
				content = content + String.join(" ", data) + System.getProperty("line.separator");
			}
			else{
				content = content + line + System.getProperty("line.separator");
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		FileWriter fileWriter = new FileWriter(new File(this.path));
		fileWriter.write(content);
		fileWriter.close();
	}
	
	/*
	 * Function which allows to get all the expressions for which the user made no mistakes at all
	 */
	public ArrayList<String> getExamplesOneShot() throws IOException{
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
		String line = bufferedReader.readLine();
		while (line != null) {
			String[] splitLine = line.split(" ");
			boolean oneShot = true;
			int cpt = 1;
			while (oneShot && cpt < splitLine.length) {
				if (!splitLine[cpt].split(":")[1].equals("0")) {
					oneShot = false;
				}
				cpt++;
			}
			if (oneShot) {
				data.add(splitLine[0].split(":")[0]);
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return data;
	}
	
	/*
	 * Function which allows to get the expressions for which the user made at least one mistake
	 */
	public ArrayList<String[]> getMistakes() throws IOException{
		ArrayList<String[]> data = new ArrayList<String[]>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
		String line = bufferedReader.readLine();
		while (line != null) {
			String[] splitLine = line.split(" ");
			int cpt = 1;
			boolean mistake = false;
			while (!mistake && cpt < splitLine.length) {
				if (!splitLine[cpt].split(":")[1].equals("0")) {
					mistake = true;
				}
				cpt++;
			}
			if (mistake) {
				data.add(line.split(" "));
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return data;
	}

}
