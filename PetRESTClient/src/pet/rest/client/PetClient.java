package pet.rest.client;
import java.net.URI;
import pet.rest.classes.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;

public class PetClient {
	private static WebTarget serviceTarget;
	private static WebTarget methodTarget;
	
	public PetClient() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
	    URI baseURI = UriBuilder.fromUri("http://localhost:8080/PetRESTService/rest/").build();
		serviceTarget = client.target(baseURI);
		// Create a target on http://localhost:8080/SchoolRESTService/rest/students/count.
		methodTarget = serviceTarget.path("petservice");
	}

	public static void main(String[] args) {
		PetClient client = new PetClient();
		methodTarget = serviceTarget.path("person").path("count");
					
		// Build a request on http://localhost:8080/SchoolRESTService/rest/students/count.
		Builder requestBuilder =  methodTarget.request();
		requestBuilder= requestBuilder.accept(MediaType.TEXT_PLAIN);
		
		// Submit GET operation on http://localhost:8080/SchoolRESTService/rest/students/count.
		Response response = requestBuilder.get();

		if (response.getStatus() == 200) { 
			// 200 (OK) -> GET is a success!
			//Person p = response.readEntity(Person.class);
			Integer i = response.readEntity(Integer.class);
			System.out.println(i);
		} 
		else {
		   System.out.println("ERROR: Cannot get the number of students! (" +response +")");
		}

	}

}
