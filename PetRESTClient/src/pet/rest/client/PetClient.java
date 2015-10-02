package pet.rest.client;
import java.net.URI;
import java.util.ArrayList;
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
			System.out.println("Enter person id: ");
	        try{
	            id = Integer.parseInt(br.readLine(), 10);
	            getPerson(id);
	        }
	        catch(NumberFormatException nfe){
	            System.err.println("Invalid Format!");
	        }
	        System.out.println("Enter new pet name: ");
	        String name = br.readLine();
	        updatePet(id, name);
	        getPerson(id);
	        input = br.readLine();
		}        
	}
	
	private static void getPet(int id){
		WebTarget methodTarget = serviceTarget.path("rest").path("petservice").path("pet").path(String.valueOf(id));
		Builder requestBuilder = methodTarget.request().accept(MediaType.APPLICATION_JSON);
		Response response = requestBuilder.get();
		
		if (response.getStatus() == 200){
			Pet p = response.readEntity(Pet.class);
			System.out.println(p.toString());
		}
	}
	
	private static void getPerson(int id){
		WebTarget methodTarget = serviceTarget.path("rest").path("petservice").path("person").path(String.valueOf(id));
		Builder requestBuilder = methodTarget.request().accept(MediaType.APPLICATION_JSON);
		Response response = requestBuilder.get();
		
		if (response.getStatus() == 200){
			Person p = response.readEntity(Person.class);
			System.out.println(p.toString());
		}
	}
	
	private static void updatePet(int id, String petname){
		Form form = new Form();
		form.param("id", String.valueOf(id));
		form.param("petname", petname);
		
		Response response = serviceTarget.path("rest").path("petservice").request().accept(MediaType.TEXT_PLAIN)
				.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		
		if (response.getStatus() == Response.Status.CREATED.getStatusCode()){
			System.out.println("PUT OK!");
		}
		else {
			System.out.println("PUT has failed! (" + response + ")"); }
	}
	
	private void deletePerson(int id){
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
	
	private void getAllPersons(){
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
		   System.out.println("ERROR: Cannot get the number of students! (" +response +")");
		}
	}

}
