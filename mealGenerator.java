import java.io.IOException;
import java.util.*;
import javafx.util.*;
import java.lang.*;

public class mealGenerator {
    
    public static void main(String[] args) {
    
        int targetCal = 800;
        Vector<String> todayItems = new Vector<>();
        Map<String, Pair<Integer, Integer> > dataSet = new HashMap<>();

        // Populate the current menu [For Testing]
        todayItems.add("Chicken");
        todayItems.add("Cowboy Fried Chicken");
        todayItems.add("Pasta");
        todayItems.add("Rice");
        todayItems.add("Steak");
        todayItems.add("Toast");

        // Create the pairs to be used in the map [For Testing]
        Pair<Integer, Integer> chkn = new Pair<>(1, 210);
        Pair<Integer, Integer> cboy = new Pair<>(1, 300);
        Pair<Integer, Integer> steak = new Pair<>(1, 150);
        Pair<Integer, Integer> pasta = new Pair<>(2, 350);
        Pair<Integer, Integer> rice = new Pair<>(2, 250);
        Pair<Integer, Integer> toast = new Pair<>(2, 100);
         
        // Populate the Map [For Testing]
        dataSet.put("Chicken", chkn);
        dataSet.put("Cowboy Fried Chicken", cboy);
        dataSet.put("Pasta", pasta);
        dataSet.put("Rice", rice);
        dataSet.put("Steak", steak);
        dataSet.put("Toast", toast);

        // Print out complete menu for the day [For Testing]       
        System.out.println("Today's Menu:\n");
        for(String value : todayItems) {
            System.out.println(value);
        }
        System.out.println();
        
        // Print out complete data set [For Testing]
        System.out.println("Complete Data Set:\n");
        System.out.println(dataSet);        
        
        // Divide today's menu into protein and carbs
        Vector<String> protein = new Vector<>();
        Vector<String> carbs = new Vector<>();
        
        for(String value : todayItems) {
            if(dataSet.get(value).getKey() == 1) {
                protein.add(value);
            } else if(dataSet.get(value).getKey() == 2) {
                carbs.add(value);
            } else {
                System.out.println("Food item " + value + " not found in data set");
            }
        }
        System.out.println();

        // Print the food recommendations! Done!
        System.out.println("Protein Reccomendation: " + calorieCalc(targetCal / 4, protein, dataSet));
        System.out.println("Carbs Reccomendation: " + calorieCalc(targetCal / 4, carbs, dataSet));
        System.out.println("Fruit Reccomendation: " );
        System.out.println("Veggies Reccomendation: " );

    }

    // Generates a list of fruits and vegetables offered makes a recommendation
    // private static void fruitNveggieGenerator(String[] args) {
    //     Map<String, Pair<Integer, Integer> > fruits = new HashMap<>();
    //     Map<String, Pair<Integer, Integer> > veggies = new HashMap<>();

    //     // Populate the current menu [For Testing]
    //     fruits.put("Apple");
    //     fruits.put("Banana");
    //     fruits.put("Honeydew");
    //     fruits.put("Cantaloupe");
    //     fruits.put("Pineapple");

    //     // Create the pairs to be used in the map [For Testing]
    //     Pair<Integer, Integer> chkn = new Pair<>(1, 210);
    //     Pair<Integer, Integer> cboy = new Pair<>(1, 300);
    //     Pair<Integer, Integer> steak = new Pair<>(1, 150);
    //     Pair<Integer, Integer> pasta = new Pair<>(2, 350);
    //     Pair<Integer, Integer> rice = new Pair<>(2, 250);
    //     Pair<Integer, Integer> toast = new Pair<>(2, 100);
    // }
    
    // Find the closest food item in protein to 1/4 of the target calorie amount to get a balanced meal
    private static String calorieCalc(int categoryCal, Vector<String> food, Map<String, 
                                      Pair<Integer, Integer> > data) {
        String optimalFood = "";
        if(!food.isEmpty()) {

            // Sets up the initial optimal food for comparison later
            optimalFood = food.get(0);
            int minDiff = Math.abs(categoryCal - data.get(optimalFood).getValue());
            int tmpDiff = 0;

            // Compare the calorie difference with the target amount between each value in the category 
            for(String value : food) {
                tmpDiff = Math.abs(categoryCal - data.get(value).getValue());
                if(tmpDiff < minDiff) {
                    minDiff = tmpDiff;
                    optimalFood = value;
                }
            }
        }
        return optimalFood + " - " + data.get(optimalFood).getValue() + " Calories";
    }
}