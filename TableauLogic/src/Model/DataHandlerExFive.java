package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandlerExFive extends DataHandler{

	
	/*
	 * The Data File is like : expression - number of mistakes (anticipations) - number of Beta-rules before Alpha-rules
	 */
	public DataHandlerExFive(String path) throws IOException {
		super(path);
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
	 * Function which allows to update the data for an expression given
	 * expression => the expression we want to update in the data file
	 * column => the index of the column (sub-formula) data we want to update
	 * numberOfMistakes => the number of mistakes for a sub-formula to add to the data
	 */
	public void update(String expression, int column, int numberOfMistakes) throws IOException{
		String content = "";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
		String line = bufferedReader.readLine();
		System.out.println("-" + expression + "-");
		while (line != null) {
			String[] data = line.split(" ");
			if (data[0].equals(expression.replace(" ", ""))) {
				int value = Integer.valueOf(data[column]) + numberOfMistakes;
				data[column] = String.valueOf(value);
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
	
}
