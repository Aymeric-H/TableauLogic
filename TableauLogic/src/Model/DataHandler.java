package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataHandler {

	private String path;
	private File file;
	
	public DataHandler(String path) throws IOException{
		this.path = Paths.get("").toAbsolutePath().toString() + "/" + path;
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
		System.out.println(expression + " - " + column + " - " + numberOfMistakes);
		String content = "";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
		String line = bufferedReader.readLine();
		while (line != null) {
			String[] data = line.split(" ");
			if (data[0].equals(expression.replace(" ", ""))) {
				int value = Integer.valueOf(data[column + 1]) + numberOfMistakes;
				data[column + 1] = String.valueOf(value);
				content = content + String.join(" ", data) + System.getProperty("line.separator");
			}
			else{
				content = content + line + System.getProperty("line.separator");
			}
			System.out.println(content);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		FileWriter fileWriter = new FileWriter(new File(this.path));
		fileWriter.write(content);
		fileWriter.close();
	}
	
}
