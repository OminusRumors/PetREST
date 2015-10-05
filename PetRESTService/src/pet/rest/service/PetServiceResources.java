package pet.rest.service;
import pet.rest.classes.*;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
		petList.add(new Pet(petList.size(), "Rocky", "Doperman", "Eindhoven"));
		petList.add(new Pet(petList.size(), "Rocky2", "Doperman2", "Eindhoven2"));
		petList.add(new Pet(petList.size(), "Rocky3", "Doperman3", "Eindhoven3"));
		petList.add(new Pet(petList.size(), "Rocky4", "Doperman4", "Eindhoven4"));
		personList.add(new Person(personList.size(), "George", "Kasteellaan 1", "Rocky2", "Doperman2")); //, new Date(2015, 9, 1)));
		personList.add(new Person(personList.size(), "George2", "Kasteellaan 2", "Rocky", "Doperman")); //, new Date(2015, 9, 2)));
		personList.add(new Person(personList.size(), "George3", "Kasteellaan 3", "Rocky2", "Doperman2")); //, new Date(2015, 9, 3)));
		personList.add(new Person(personList.size(), "George4", "Kasteellaan 4", "Rocky3", "Doperman3")); //, new Date(2015, 9, 4)));
		personList.add(new Person(personList.size(), "George5", "Kasteellaan 5", "Rocky2", "Doperman2")); //, new Date(2015, 9, 5)));
	}
	
	@POST
	@Path("person/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(Person p){
		p.setId(personList.size());
		personList.add(p);
		if (personList.get(p.getId()) == p){
			return Response.noContent().build();
		}
		else{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Cannot add person " + p.getName()).build();
		}
	}
	
	@POST
	@Path("pet/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPet(Pet p){
		p.setId(petList.size());
		petList.add(p);
		if (petList.get(p.getId()) == p){
			return Response.noContent().build();
		}
		else{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Cannot add pet " + p.getName()).build();
		}
	}
	
	
	@DELETE
	@Path("/person/delete")
	public Response deletePerson(@QueryParam("id") int id){
		Person p = personList.get(id);
		personList.remove(p);
		if (personList.get(id) == p){
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Cannot delete person " + p.toString()).build();
		}
		else{
			return Response.ok().build();
		}
	}
	
	@DELETE
	@Path("pet/delete")
	public Response deletePet(@QueryParam("id") int id){
		Pet p = petList.get(id);
		petList.remove(p);
		if (petList.get(id) == p){
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Cannot delete pet " + p.toString()).build();
		}
		else{
			return Response.ok().build();
		}
	}
	
	@GET
	@Path("/pet/count")
	@Produces(MediaType.TEXT_PLAIN)
	public int getPetsNumber(){
		return petList.size();
	}
	
	@PUT
	@Path("/pet/updatefound")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updatePet(@FormParam("id") int id){
		Pet pet = this.petList.get(id);
		pet.setFound(true);
		if (pet.getFound() == true){
			return Response.noContent().build();
		}
		else{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Cannot update pet " + pet.toString()).build();
		}
	}
	
	@GET
	@Path("/person/count")
	@Produces(MediaType.TEXT_PLAIN)
	public int getPersonNumber(){
		return personList.size();
	}
	
//	@GET
//	@Path("/person/name")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getPersonName(@QueryParam("id") int id) {
//		Person p = personList.get(id);
//	   	if (p != null) {
//		   	return p.getName();
//	   	} 
//	   	else {
//	   		return "Person with id "+ id + "was not found."; 
//	   	}
//	}
	
	@GET
	@Path("/person/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson(@PathParam("id") int id){
		Person p = personList.get(id);
	   	if (p != null) {
		   	return Response.ok(p).build();
	   	} 
	   	else {
	   		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Cannot find person with id " + id).build();
	   	}
	}
	
	@GET
	@Path("/pet/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPet(@PathParam("id") int id){
		Pet p = petList.get(id);
	   	if (p != null) {
	   		return Response.ok(p).build();
	   	} 
	   	else {
	   		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Cannot find pet with id " + id).build();
	   	}
	}
	
	@GET
	@Path("/person/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Person> getAllPerson(){
		return personList;
	}

}
