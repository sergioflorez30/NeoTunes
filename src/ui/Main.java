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
		String name = ""; 
		String nickname = ""; 
		String album = ""; 
		String id = ""; 
		String url = "";
		String description = "";   
		int type = 0;
		int duration =0;
		int typeSong = 0; 
		int typePodcast =0; 
		double price =0.0;   

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

				System.out.println("type the nickname of artist or content creator");
		        nickname=reader.next();
		        System.out.println("type the audios name");
		        name= reader.next();
		        System.out.println("type the url");
		        url= reader.next();
		        System.out.println("type the audios duration");
		        duration=validateIntegerInput();
		        if(duration == -1){
		        	System.out.println("enter a available  option .... :("); 
		        }
		        System.out.println("type the type of audio....  1. Song.2. podcast");
		        type= validateIntegerInput();

		        switch(type){
		            case 1:
		            System.out.println("enter the name of album");
		            album=reader.next();
		            System.out.println("enter the price of song");
		            price=validateDoubleInput();
		            if(price == -1){
		        	System.out.println("enter a available  option .... :("); 
		        	}
		            System.out.println("enter the type of song.1.rock,2.pop,3.trap,4.house");
		            typeSong=reader.nextInt();
		            if(typeSong>4||typeSong<1){
		                System.out.println("you must enter an available option");
		            } else{
		                System.out.println(controller.registerSong(nickname, name, url, duration, album, price, typeSong));
		             }

		             break;

		            case 2:
		             System.out.println("type the podcasts description");
		             description=reader.nextLine();
		             System.out.println("type  the type of podcast... .1.Politic, 2.Entertaiment, 3. Fashion, 4. Videogame");
		             typePodcast=validateIntegerInput(); 
		             if(typeSong>4||typeSong<1){
		             	System.out.println("enter a available  option .... :("); 
		             }
		             reader.next(); 
		             System.out.println(controller.registerPodcast(nickname, name, url, duration,description, typePodcast));
		             break;

		            default:
		            System.out.println("you must enter an available option");
		             break;
		            }
				

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