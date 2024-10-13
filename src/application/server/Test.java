package application.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Test {
	
	public static ArrayList<String> tourLocation(String place) {
		ArrayList<String> list = new ArrayList<>();
		try {
			FileReader file = new FileReader("src/application/server/touristSpot.txt");
			BufferedReader reader = new BufferedReader(file);
			
			String line;
			while((line = reader.readLine()) != null) {
				String [] parts1 = line.split("#");
				String [] parts2 = parts1[1].split("-");
				String loc = parts2[0];
				if(loc.equals(place)) {
					String str = "Package Number: " + parts1[0] + 
							"#Location: " + parts2[0]+
							"#Number of persons: " + parts2[1]+
							"#Price: " + parts2[2];
					list.add(str);
				}
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return list;
	}
	public static void main(String[] args) {
		try {
			ArrayList<String> list = tourLocation("Cox,s Bazar");
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
