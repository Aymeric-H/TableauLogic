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

	private String path;
	private File file;
	
	public DataHandler(String path) throws IOException{
		if (path.charAt(0) == 'H') {
			this.path = Paths.get(path).toString();
		}
		else{
			this.path = Paths.get("").toAbsolutePath().toString() + "/" + path;
		}
		this.file = new File(this.path);
		if (!this.file.exists()) {
			this.file.createNewFile();
		}
	}
	
	public void write(String data) throws IOException{
		if (!this.isExpressionInDataFile(data.split(" ")[0])) {
			FileWriter fileWriter = new FileWriter(this.file, true);
			fileWriter.write(data + System.getProperty("line.separator"));
			fileWriter.flush();
			fileWriter.close();
		}
	}
	
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
	
	public ArrayList<String[]> getMistakes() throws IOException{
		ArrayList<String[]> data = new ArrayList<String[]>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
		String line = bufferedReader.readLine();
		while (line != null) {
			String[] splitLine = line.split(" ");
			boolean mistake = false;
			int cpt = 1;
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
