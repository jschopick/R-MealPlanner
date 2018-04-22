import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FetchMeals {
	
	public static void main(String[] args) {
		
		try { // Scrapes Dining website to gather the information on menu options
			Document doc = Jsoup.connect("http://138.23.12.141/foodpro/shortmenu.asp?sName=University+of+California%2C+Riverside+Dining+Services&locationNum=02&locationName=Lothian+Residential+Restaurant&naFlag=1&WeeksMenus=This+Week%27s+Menus&myaction=read&dtdate=4%2F23%2F2018").userAgent("mozilla/17.0").get();
			Elements temp = doc.select("div.shortmenurecipes");
			int i = 0;
			int meal = 1;
			Vector<String> breakfast = new Vector<>();
			Vector<String> lunch = new Vector<>();
			Vector<String> dinner = new Vector<>();

			// Split food options based on time offered
			for(Element foodList : temp) {
				String tmp = foodList.getElementsByTag("a").first().text();
				// Handles special case for Albondigas and Artesian Bread Bar
				if(tmp.equals("Albondigas")) {
					meal = meal + 1;
					if(meal == 2) {
						lunch.add(tmp);
						tmp = foodList.getElementsByTag("a").first().text();
						lunch.add(tmp);
					} else {
						dinner.add(tmp);
						tmp = foodList.getElementsByTag("a").first().text();
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

			// Prints all menu items by category [For Testing]
			// System.out.println("Breakfast Items:");
			// printVector(breakfast);
			// System.out.println("Lunch Items:");
			// printVector(lunch);
			// System.out.println("Dinner Items:");
			// printVector(dinner);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Prints the list of all meals [For Testing]
	// private static void printVector(Vector<String> v) {
	// 	 for (int i = 0; i < v.size(); i++) {
	// 		 System.out.println(v.elementAt(i));
	// 	}	
	// }
}