package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandlerExTwo extends DataHandler {

	public DataHandlerExTwo(String path) throws IOException {
		super(path);
	}
	
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
