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
	//returns the class, that is, the class becomes visible to the main method.
	public Scanner getReader(){
		return reader; 
	}

	//returns the class, that is, the class becomes visible to the main method.
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
	/**
	getOptionShowMenu: This method shows all the options available in the menu, 
					after having the user enter an option.
	@return option: int: this parameter read the option entered by the user. 
	*/

	public int getOptionShowMenu(){
		int option = 0; 
		System.out.println("<<<<< Welcome to NeoTones >>>>>");
		System.out.println( 
				"1. register a producer user\n" +
				"2. register a consumer user \n" +
				"3. register song or Podcast\n" +
				"4. register playlist\n" +
				"5. edit playlist\n" +
				"6.Share a playlist.\n" + 
				"7. play an audio. \n" +
				"8. boy a song \n" + 
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
		String namePlaylist = "";   
		String audio = ""; 
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
		             description=reader.next();
		             System.out.println("type  the type of podcast... .1.Politic, 2.Entertaiment, 3. Fashion, 4. Videogame");
		             typePodcast=validateIntegerInput(); 
		             if(typePodcast>4||typePodcast<1){
		             	System.out.println("enter a available  option .... :("); 
		             }
		             System.out.println(controller.registerPodcast(nickname, name, url, duration,description, typePodcast));
		             break;

		            default:
		            System.out.println("you must enter an available option");
		             break;
		            }
				

				break; 

			case 4: 

				System.out.println("type the users nickname... standard or premium");
		        nickname=reader.next();
		        System.out.println("type the playlists name");
		        name= reader.next();
		        System.out.println("type the playlists type...  1.only songs   2.only podcasts   3. podcast and songs");
		        type= validateIntegerInput();
		        if(type>4|| type<1){
		            System.out.println("you must enter an available option");
		        }
		        else{
		            System.out.println(controller.registerPlaylist(nickname, name, type));
		        }

				break;

			case 5:
				System.out.println("type the users nickname... standard or premium");
		        nickname=reader.next();
		        System.out.println("type the option you want.. 1.add audio in playlist    or     2.eliminate audio in playlist");
		        type= validateIntegerInput(); 
		        if((type==1 || type==2) && type != -1){

		            System.out.println("type the playlists name");
		            namePlaylist=reader.next();
		            System.out.println("type  the audios name");
		            audio=reader.next();

		            System.out.println(controller.editAudioPlaylist(type, nickname, namePlaylist, audio));
		             
		         }

		         else{
		            System.out.println("you must enter an available option");
		         }
		    

				break; 

			case 6: 

				System.out.println("type the users nickname standard or premium");
		        nickname=reader.next();
		        System.out.println("enter the name of the playlist");
		        namePlaylist=reader.next();
		        
		        System.out.println(controller.sharePlaylist(nickname, namePlaylist));

		        break;

		     case 7: 

		     	System.out.println("Type the users nickname standard or premium");
		     	nickname = reader.next();
		     	System.out.println("type the audios name");
		     	audio = reader.next(); 

		     	System.out.println(controller.playingAudio(nickname, audio));
		     	break;

		     case 8: 
		     	System.out.println("type the users nickname standard or premium");
		     	nickname = reader.next();
		     	System.out.println("type the songs name"); 
		     	audio = reader.next();

		     	System.out.println(controller.buySong(nickname, audio));


		     	break;



			case 0: 
				System.out.println("Exit program.");
				break; 

			default: 
				System.out.println("Invalid Option");
				break; 
		}
	}
	/**
	 * validateIntegerInput: this method validates that the option entered by the user is actually an integer data type
	 * @return option: is a int option. 
	 */

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
	/**
	 * validateDoubleInput: this method validates that the option entered by the user is actually an double data type
	 * @return option: is a double or int  option. 
	 */

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