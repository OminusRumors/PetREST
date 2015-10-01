package pet.rest.service;
import pet.rest.classes.*;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/petservice")
@Singleton
public class PetServiceResources {
	
	ArrayList<Pet> petList;
	ArrayList<Person> personList;
	
	public PetServiceResources(){
		petList = new ArrayList<Pet>();
		personList = new ArrayList<Person>();
		populate();
	}
	
	@SuppressWarnings("deprecation")
	private void populate(){
		petList.add(new Pet(1, "Rocky", "Doperman", "Eindhoven"));
		petList.add(new Pet(2, "Rocky2", "Doperman2", "Eindhoven2"));
		petList.add(new Pet(3, "Rocky3", "Doperman3", "Eindhoven3"));
		petList.add(new Pet(4, "Rocky4", "Doperman4", "Eindhoven4"));
		personList.add(new Person(1, "George", "Kasteellaan 1", "Rocky2", "Doperman2", new Date(2015, 9, 1)));
		personList.add(new Person(2, "George2", "Kasteellaan 2", "Rocky", "Doperman", new Date(2015, 9, 2)));
		personList.add(new Person(3, "George3", "Kasteellaan 3", "Rocky2", "Doperman2", new Date(2015, 9, 3)));
		personList.add(new Person(4, "George4", "Kasteellaan 4", "Rocky3", "Doperman3", new Date(2015, 9, 4)));
		personList.add(new Person(5, "George5", "Kasteellaan 5", "Rocky2", "Doperman2", new Date(2015, 9, 5)));
	}
	
	public boolean remove (Person person){
		return personList.remove(person);
	}
	
	@DELETE
	@Path("/person/delete")
	public void deletePerson(@QueryParam("id") int id){
		Person p = personList.get(id);
		personList.remove(p);
	}
	
	@GET
	@Path("/pet/count")
	@Produces(MediaType.TEXT_PLAIN)
	public int getPetsNumber(){
		return petList.size();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updatePet(@FormParam("id") int id, @FormParam("petname") String petname){
		Person p= this.getPerson(id);
		p.setPetName(petname);
		return Response.noContent().build();
	}
	
	@GET
	@Path("/person/count")
	@Produces(MediaType.TEXT_PLAIN)
	public int getPersonNumber(){
		return personList.size();
	}
	
	@GET
	@Path("/person/name")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPersonName(@QueryParam("id") int id) {
		Person p = personList.get(id);
	   	if (p != null) {
		   	return p.getName();
	   	} 
	   	else {
	   		return "Person with id "+ id + "was not found."; 
	   	}
	}
	
	@GET
	@Path("/person/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("id") int id){
		Person p = personList.get(id);
	   	if (p != null) {
		   	return p;
	   	} 
	   	else {
	   		throw new RuntimeException("Person was not found."); 
	   	}
	}
	
	@GET
	@Path("/person/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Person> getAllPerson(){
		return personList;
	}

}
