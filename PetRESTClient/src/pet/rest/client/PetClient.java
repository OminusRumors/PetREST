package pet.rest.client;
import java.net.URI;
import java.util.ArrayList;

import pet.rest.classes.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;

public class PetClient {
	private static WebTarget serviceTarget;
	private static WebTarget methodTarget;
	
	public PetClient() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config).register(Person.class);
	    URI baseURI = UriBuilder.fromUri("http://localhost:8080/PetRESTService").build();
		serviceTarget = client.target(baseURI);
		// Create a target on http://localhost:8080/SchoolRESTService/rest/students/count.
		//methodTarget = serviceTarget.path("rest").path("petservice");
	}

	public static void main(String[] args) {
		PetClient client = new PetClient();
		methodTarget = serviceTarget.path("rest").path("petservice").path("person").path("all");
					
		// Build a request on http://localhost:8080/SchoolRESTService/rest/students/count.
		Builder requestBuilder =  methodTarget.request();
		requestBuilder= requestBuilder.accept(MediaType.APPLICATION_JSON);
		
		// Submit GET operation on http://localhost:8080/SchoolRESTService/rest/students/count.
		Response response = requestBuilder.get();
		//System.out.println(response.getStatus());

		if (response.getStatus() == 200) { 
			// 200 (OK) -> GET is a success!
			//Person p = response.readEntity(Person.class);
			//Person i = response.readEntity(Person.class);
			//System.out.println(i.getName() + i.getPetName());
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
