import java.io.IOException;
import java.util.*;
import javafx.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class createJavaMap {
	
	public static void main(String[] args) {
		String csvFile = "/mnt/c/Users/benbl/Desktop/CS/projects/R-MealPlanner/UCR_food.csv";
        String line = "";
        String cvsSplitBy = ",";

        Map<String, Pair<String,String>> dataSet = new HashMap<>();
        
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
