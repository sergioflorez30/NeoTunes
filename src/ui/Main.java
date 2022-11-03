package ui; 

import java.util.Scanner;
import model.MusicAppController;


public class Main {

	private Scanner reader;
	private MusicAppController controller;
	

	public Main() {
		reader = new Scanner(System.in);
		controller = new MusicAppController();
		

	}
	public Scanner getReader(){
		return reader; 
	}
	public MusicAppController getController(){
		return controller; 
	}

	public static void main(String[] args) {

		// creación del objeto. 
		Main main = new Main(); 
		// llamdo a uno de los metodos de la clase. 
		int option = 0; 

		do{

			option = main.getOptionShowMenu(); 
			main.executeOption(option);

		}while(option != 0);

		main.getReader().close();

	}

	public int getOptionShowMenu(){
		int option = 0; 
		System.out.println("<<<<< Welcome to NeoTones >>>>>");
		System.out.println( 
				"1. register a producer user\n" +
				"2. register a consumer user \n" +
				"3. register song or Podcast\n" +
				"4. register playlist\n" +
				"5. edit playlist\n" +
				"0. Exit. ");
		option =  validateIntegerInput();
		return option; 
	}

	public void executeOption(int option){
		String msj = ""; 
		String name = ""; 
		String nickname = ""; 
		String id = ""; 
		String url = "";  
		int type = 0; 

		switch(option){
			case 1:
				System.out.println("we are going to register a producer"); 
				System.out.println("type the nickname");
		        nickname=reader.next();
		        System.out.println("type the id");
		        id= reader.next();
		        System.out.println("type the url picture");
		        url= reader.next();
		        System.out.println("type your name");
		        name= reader.next();
		        System.out.println("type the type of producer user... 1. creator content.2. Artist");
		        type= validateIntegerInput();
		        if((type==2||type==1) && type != -1){
		            System.out.println(controller.registerProducer(nickname, id, url, name, type));
		         }
		         else{
		            System.out.println("enter a available option... :(");
		         }

				break; 

			case 2: 

				System.out.println("we are going to register a consumer");
				System.out.println("type the nickname");
		         nickname=reader.next();
		        System.out.println("type the id");
		         id= reader.next();
		        System.out.println("type the type of consumer user...  1. standard.2. premium");
		        type= validateIntegerInput();
		         if((type==2 || type==1) && type != -1){
		            System.out.println(controller.registerConsumer(nickname, id,type));
		         } else{
		            System.out.println("enter a available option... :(");
		         } 

				break; 

			case 3: 
				

				break; 

			case 4: 

				break;

			case 5:

				break; 


			case 0: 
				System.out.println("Exit program.");
				break; 

			default: 
				System.out.println("Invalid Option");
				break; 
		}
	}

	public int validateIntegerInput(){
		int option = 0; 

		if(reader.hasNextInt()){
			option = reader.nextInt(); 
		}
		else{
			// clear reader. 
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}

	public double validateDoubleInput(){
		double option = 0; 

		if(reader.hasNextDouble()){
			option = reader.nextDouble(); 
		}
		else{
			// clear reader. 
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}



}