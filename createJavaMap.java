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
        Map<String, Pair<Integer, Integer>> dataSet = new HashMap<>();
            
        // Read each line of the csv file and enter it into the map of data
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] food = line.split(csvSplitBy);
                System.out.println("Col1: " + food[0]);
                // System.out.println("Col2: " + food[1]);
                // System.out.println("Col3: " + food[2]);
                int tempCat = Integer.parseInt(food[1]);
                // System.out.println("tempCat: " + tempCat);
                int tempCal = Integer.parseInt(food[2]);
                // System.out.println("tempCal: " + tempCal);
                Pair<Integer, Integer> temp = new Pair<>(tempCat, tempCal);
                // Pair<String, String> temp = new Pair<>(food[1],food[2]);
                // System.out.println("Here");
                dataSet.put(food[0],temp);
            }
            // Pair<String, String> temp = dataSet.get("Chicken Tenders");
            // System.out.println(temp.getKey());
            // System.out.println(temp.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
