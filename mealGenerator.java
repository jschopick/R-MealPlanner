import java.io.IOException;
import java.util.*;
import javafx.util.*;

public class mealGenerator {
    
    public static void main(String[] args) {
    
        int targetCal = 700;
        Vector<String> todayItems = new Vector<>();
        Map<String, Pair<Integer, Integer> > dataSet = new HashMap<>();

        todayItems.add("Chicken");
        todayItems.add("Cowboy Fried Chicken");
    
        Pair<Integer, Integer> chkn = new Pair<>(1, 200);
        Pair<Integer, Integer> cboy = new Pair<>(1, 300);

        dataSet.put("Chicken", chkn);
        dataSet.put("Cowboy Fried Chicken", cboy);
        
        System.out.println("Today's Menu:");
        for(String value : todayItems) {
            System.out.println(value);
        }

        System.out.println("Complete Data Set:\n");
        System.out.println(dataSet);        
        
    }
}
