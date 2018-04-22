import java.io.IOException;
import java.util.*;
import javafx.util.*;
import java.lang.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.FileReader;

public class mealPlannerComplete {
    
    // Main Function
    public static void main(String[] args) {
        
        System.out.print("How many calories would you like to eat during this meal?\nEnter Calorie Amount: ");
        Scanner scanner = new Scanner(System.in);
        int targetCal = scanner.nextInt();
        Vector<String> todayItems = new Vector<>();
        Map<String, Pair<Integer, Integer> > dataSet = new HashMap<>();
        Pair<Vector<String>, Vector<String> > proteinNcarbsVectors = new Pair<>(todayItems, todayItems);

        // Populate todayItems and dataSet
        // todayItems = fetchMeals();
        dataSet = populateMap();
        todayItems = populateVector();
        todayItems = fetchMeals(todayItems);

        // Generate list of proteins and carbs
        proteinNcarbsVectors = mealGenerator(todayItems, dataSet);
        
        // Print the food recommendations! Done!
        System.out.println("Protein Reccomendation: " + calorieCalc(targetCal / 4, proteinNcarbsVectors.getKey(), dataSet));
        System.out.println("Carbs Reccomendation: " + calorieCalc(targetCal / 4, proteinNcarbsVectors.getValue(), dataSet));
        System.out.println("Fruit Reccomendation: " );
        System.out.println("Veggies Reccomendation: " );
        System.out.println("Add dressing if desired for about 100 Calories.");
    }

    // Populates protein and carbs vectors to be used in our calorie calculator
    private static Pair<Vector<String>, Vector<String> > mealGenerator(Vector<String> todayItems, Map<String, Pair<Integer, Integer> > dataSet) {
       
        // Divide today's menu into protein and carbs
        Vector<String> protein = new Vector<>();
        Vector<String> carbs = new Vector<>();
        
        System.out.println("Today's Menu: " + todayItems + "\n");
        System.out.println("data set: " + dataSet);
        for(String value : todayItems) {
            System.out.println("Current Value: " + value);
            System.out.println("Data Set Key: " + dataSet.get(value));
            if((dataSet.get(value).getKey()) == 1) {
                System.out.println("Protein Key: " + dataSet.get(value));
                protein.add(value);
            } else if(dataSet.get(value).getKey() == 2) {
                System.out.println("Carbs Key: " + dataSet.get(value).getKey());
                carbs.add(value);
            } else {
                System.out.println("Food item " + value + " not found in data set");
            }
        }
        System.out.println();

        Pair<Vector<String>, Vector<String> > proteinNcarbs = new Pair<>(protein, carbs);

        return proteinNcarbs;
    }

    // Scrapes Dining website to gather the information on menu options
    private static Vector<String> fetchMeals(Vector<String> temp) {
        
        Vector<String> mealChoice = new Vector<>();

		// try {
			// Document doc = Jsoup.connect("http://138.23.12.141/foodpro/shortmenu.asp?sName=University+of+California%2C+Riverside+Dining+Services&locationNum=02&locationName=Lothian+Residential+Restaurant&naFlag=1&WeeksMenus=This+Week%27s+Menus&myaction=read&dtdate=4%2F23%2F2018").userAgent("mozilla/17.0").get();
			// Elements temp = doc.select("div.shortmenurecipes");
			int i = 0;
			int meal = 1;
			Vector<String> breakfast = new Vector<>();
			Vector<String> lunch = new Vector<>();
			Vector<String> dinner = new Vector<>();

			// Split food options based on time offered
			for(String foodItem : temp) {
				String tmp = foodItem;
				// Handles special case for Albondigas and Artesian Bread Bar
				if(tmp.equals("Albondigas")) {
					meal = meal + 1;
					if(meal == 2) {
						lunch.add(tmp);
						tmp = foodItem;
						lunch.add(tmp);
					} else {
						dinner.add(tmp);
						tmp = foodItem;
						dinner.add(tmp);
					}
                } else if(tmp.equals("Artesian Bread Bar")) {
					meal = meal + 1;
					if(meal == 2) {
						lunch.add(tmp);
					} else {
						dinner.add(tmp);
					}
				} else {
					if(meal == 1) {
						breakfast.add(tmp);
					} else if(meal == 2) {
						lunch.add(tmp);
					} else {
						dinner.add(tmp);
					}
				}
            }
            System.out.println("Dinner: " + dinner);

            // Return the vector of the correct meal time
            System.out.println("Breakfast, Lunch, or Dinner?");
            do {
                System.out.print("Enter meal time: ");
                Scanner mealTime = new Scanner(System.in);
                String time = mealTime.nextLine();
                if((time.equals("Breakfast")) || (time.equals("breakfast"))) { 
                    mealChoice = breakfast;
                    return breakfast;
                } else if((time.equals("Lunch")) || (time.equals("lunch"))) {
                    mealChoice = lunch;
                    return lunch;
                } else if((time.equals("Dinner")) || (time.equals("dinner"))) {
                    mealChoice = dinner;
                    return dinner;
                } else { 
                    System.out.println("Incorrect input. Please try again.");
                }
            } while(!mealChoice.equals(breakfast) && !mealChoice.equals(lunch) && 
                    !mealChoice.equals(breakfast));
		// } catch (IOException e) {
		// 	e.printStackTrace();
        // }
        
        return mealChoice;

	}

    // Populate the data set of all Dining options from the created csv file
    private static Map<String, Pair<Integer, Integer> > populateMap() {
		String csvFile = "/mnt/c/Users/benbl/Desktop/CS/projects/R-MealPlanner/UCR_food.csv";
        String line = "";
        String csvSplitBy = ",";
        Map<String, Pair<Integer, Integer> > dataSet = new HashMap<>();

        // Read each line of the csv file and enter it into the map of data
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                while ((line = br.readLine()) != null) {
                String[] food = line.split(csvSplitBy);
                Pair<Integer, Integer> temp = new Pair<>(Integer.parseInt(food[1]),
                                                         Integer.parseInt(food[2]));
                dataSet.put(food[0],temp);
            }     
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSet;
    }
    
    // Populate the data set of all Dining options from the created csv file
    private static Vector<String> populateVector() {
		String csvFile = "/mnt/c/Users/benbl/Desktop/CS/projects/R-MealPlanner/UCR_food.csv";
        String line = "";
        String csvSplitBy = ",";
        Vector<String> data = new Vector<>();

        // Read each line of the csv file and enter it into the map of data
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                while ((line = br.readLine()) != null) {
                String[] food = line.split(csvSplitBy);
                data.add(food[0]);
            }     
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
	}

    // Generates a list of fruits and vegetables offered makes a recommendation
    private static void fruitNveggieGenerator(String[] args) {
        Map<String, Integer> fruits = new HashMap<>();
        Map<String, Integer> veggies = new HashMap<>();
        Map<String, Integer> dressing = new HashMap<>();

        // Populate the fruit menu
        fruits.put("Apple", 95);
        fruits.put("Banana", 105);
        fruits.put("Honeydew", 61);
        fruits.put("Cantaloupe", 53);
        fruits.put("Pineapple", 82);

        // Populate the veggie menu
        veggies.put("Broccoli", 31);
        veggies.put("Cucumbers", 16);
        veggies.put("Carrots", 45);
        veggies.put("Lettuce", 15);
        veggies.put("Spinach", 23);

        // Populate the salad dressing menue
        dressing.put("Ranch Dressing", 145);
        dressing.put("Italian Dressing", 120);

    }
    
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