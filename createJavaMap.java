import java.io.IOException;
import java.util.*;
import javafx.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class createJavaMap {
    
    // Populate the data set of all Dining options from the created csv file
	public static void main(String[] args) {
		String csvFile = "/mnt/c/Users/benbl/Desktop/CS/projects/R-MealPlanner/UCR_food.csv";
        String line = "";
        String csvSplitBy = ",";
        Map<String, Pair<String,String>> dataSet = new HashMap<>();
            
        // Read each line of the csv file and enter it into the map of data
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] food = line.split(cvsSplitBy);
                Pair<String, String> temp = new Pair<>(food[1],food[2]);
                dataSet.put(food[0],temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
