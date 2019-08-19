package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandlerExFive extends DataHandler {

	public DataHandlerExFive(String path) throws IOException {
		super(path);
	}
	
	public void write(String expression, java.util.Set<Literal> literals, ArrayList<Formula> nodes, String[] answers) throws IOException {
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
		fileWriter.write(expression.replace(" ", "") + line + System.getProperty("line.separator"));
		fileWriter.flush();
		fileWriter.close();		
	}
	
	public void writeCorrectAnswer(String expression, java.util.Set<Literal> literals, ArrayList<Formula> nodes, String[] answers) throws IOException {
		if (!this.isExpressionInDataFile(expression)) {
			this.write(expression, literals, nodes, answers);
		}		
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
