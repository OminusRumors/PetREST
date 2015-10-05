package pet.rest.client;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

import pet.rest.classes.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PetClient {
	private static WebTarget serviceTarget;
	//private static WebTarget methodTarget;
	
	public PetClient() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config).register(Person.class);
	    URI baseURI = UriBuilder.fromUri("http://localhost:8080/PetRESTService").build();
		serviceTarget = client.target(baseURI);
		// Create a target on http://localhost:8080/SchoolRESTService/rest/students/count.
		//methodTarget = serviceTarget.path("rest").path("petservice");
	}

	public static void main(String[] args) throws IOException {
		PetClient client = new PetClient();
		//methodTarget = serviceTarget.path("rest").path("petservice");
		//methodTarget = serviceTarget.path("rest").path("petservice").path("person").path("delete").queryParam("id", "1");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		while (input != "exit"){ //does not work on exiting the client
			int id = -1;
			System.out.println("Enter delete id: ");
//			String name = br.readLine();
//			System.out.println("Enter person address: ");
//			String address = br.readLine();
//			System.out.println("Enter person's pet name: ");
//			String petname = br.readLine();
//			System.out.println("Enter pet's breed: ");
//			String breed = br.readLine();
			//System.out.println("Enter date lost: ");
			//Date dateLost = Date.parse(br.readLine());
			//Person p = new Person(name, address, petname, breed);
	        try{
	            id = Integer.parseInt(br.readLine(), 10);
	            getPerson(id);
	        	//addPerson(p);
	        }
	        catch(NumberFormatException nfe){
	            System.err.println("Invalid Format!");
	        }
	        //System.out.println("Enter new pet name: ");
	        //String name = br.readLine();
	        //updatePet(id);
	        //getPet(id);
	        input = br.readLine();
		}        
	}
	
	private static void addPet(Pet pet){
		WebTarget methodTarget = serviceTarget.path("rest").path("petservice").path("pet").path("add");
		Builder requestBuilder = methodTarget.request();
		Response response = requestBuilder.post(Entity.entity(pet, MediaType.APPLICATION_JSON));
		if (response.getStatus() == 204){
			Person p = (Person) response.readEntity(Person.class);
			System.out.println(response);
			System.out.println(p.toString());
		}
		else{
			System.out.println("Pet could not be added.");
			System.out.println(response);			
		}
	}
	
	private static void addPerson(Person person){
		WebTarget methodTarget = serviceTarget.path("rest").path("petservice").path("person").path("add");
		Builder requestBuilder = methodTarget.request();
		Response response = requestBuilder.post(Entity.entity(person, MediaType.APPLICATION_JSON)); 
		if (response.getStatus() == 204){
			Person p = (Person) response.readEntity(Person.class);
			System.out.println(response);
			System.out.println(p.toString());
		}
		else{
			System.out.println("Person could not be added.");
			System.out.println(response);			
		}
	}
	
	private static void getPet(int id){
		WebTarget methodTarget = serviceTarget.path("rest").path("petservice").path("pet").path(String.valueOf(id));
		Builder requestBuilder = methodTarget.request().accept(MediaType.APPLICATION_JSON);
		Response response = requestBuilder.get();
		
		if (response.getStatus() == 200){
			Pet p = response.readEntity(Pet.class);
			System.out.println(response.getEntity());
		}
	}
	
	private static void getPerson(int id){
		WebTarget methodTarget = serviceTarget.path("rest").path("petservice").path("person").path(String.valueOf(id));
		Builder requestBuilder = methodTarget.request().accept(MediaType.APPLICATION_JSON);
		Response response = requestBuilder.get();
		
		if (response.getStatus() == 200){
			Person p = (Person) response.readEntity(Person.class);
			System.out.println(response);
			System.out.println(p.toString());
		}
		else{
			System.out.println("Person not found.");
			System.out.println(response);			
		}
	}
	
	private static void updatePet(int id){
		Form form = new Form();
		form.param("id", String.valueOf(id));
		
		Response response = serviceTarget.path("rest").path("petservice").path("pet").path("updatefound").request().accept(MediaType.TEXT_PLAIN)
				.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		
		if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()){
			System.out.println("Pet updated!");
			System.out.println(response);
		}
		else {
			System.out.println("ERROR: " + response); 
		}
	}
	
	private void deletePet(int id){
		WebTarget methodTarget = serviceTarget.path("rest").path("petservice").path("pet").path("delete").queryParam("id", String.valueOf(id));
		Builder requestBuilder = methodTarget.request();
		Response response = requestBuilder.get(); 
		response = methodTarget.request().accept(MediaType.TEXT_PLAIN).delete();
		
		// status 204 (NO_CONTENT) -> DELETE is a success!
		if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()){
		   System.out.println("Pet " + id + " deleted succesfully.");
		} 
		else {
		    System.out.println("DELETE failed! (" + response + ")");
		}
	}
	
	private static void deletePerson(int id){
		WebTarget methodTarget = serviceTarget.path("rest").path("petservice").path("person").path("delete").queryParam("id", String.valueOf(id));
		Builder requestBuilder = methodTarget.request();
		Response response = requestBuilder.get(); 
		response = methodTarget.request().accept(MediaType.TEXT_PLAIN).delete();
		
		// status 204 (NO_CONTENT) -> DELETE is a success!
		if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()){
		   System.out.println("Person " + id + " deleted succesfully.");
		} 
		else {
		    System.out.println("DELETE failed! (" + response + ")");
		}
	}
	
	private static void getAllPets(){
		WebTarget methodTarget = serviceTarget.path("rest").path("petservice").path("pet").path("all");
		Builder requestBuilder = methodTarget.request();
		requestBuilder = requestBuilder.accept(MediaType.APPLICATION_JSON);
		Response response = requestBuilder.get();
		if (response.getStatus() == 200) { 
			// 200 (OK) -> GET is a success!
			ArrayList<Pet> list = response.readEntity(new GenericType<ArrayList<Pet>>(){});
			for (Pet pet : list) {
				System.out.println(pet.toString());
			}
		} 
		else {
		   System.out.println("ERROR: Cannot get all the pets! (" +response +")");
		}
	}
	
	private static void getAllPersons(){
		WebTarget methodTarget = serviceTarget.path("rest").path("petservice").path("person").path("all");
		
		// Build a request on http://localhost:8080/SchoolRESTService/rest/students/count.
		Builder requestBuilder =  methodTarget.request();
		requestBuilder= requestBuilder.accept(MediaType.APPLICATION_JSON);
		
		// Submit GET operation on http://localhost:8080/SchoolRESTService/rest/students/count.
		Response response = requestBuilder.get();
		//System.out.println(response.getStatus());

		if (response.getStatus() == 200) { 
			// 200 (OK) -> GET is a success!
			ArrayList<Person> list = response.readEntity(new GenericType<ArrayList<Person>>(){});
			for (Person person : list) {
				System.out.println(person);
			}
		} 
		else {
		   System.out.println("ERROR: Cannot get all people! (" +response +")");
		}
	}

}
