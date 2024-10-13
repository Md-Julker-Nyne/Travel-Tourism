package application.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread{
	private Socket client;
	private BufferedWriter writer;
	private BufferedReader reader;
	
	
	
	public ClientThread(Socket client, BufferedWriter writer, BufferedReader reader) {
		this.client = client;
		this.writer = writer;
		this.reader = reader;
	}


	public ArrayList<String> tourLocation(String place) {
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

	
	
	@Override
	public void run() {
		while(true) {
			try {
				String s1 = reader.readLine();
				
				if(s1.equals("location")) {
					String loc = reader.readLine();
					
					ArrayList<String> list = tourLocation(loc);
					String strList = String.join("--", list);
					System.out.println(strList);
					writer.write(strList+"\n");
					writer.flush();
				}
				else {
					System.out.println("nothing to show");
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	
}
