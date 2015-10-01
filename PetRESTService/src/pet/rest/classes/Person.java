package pet.rest.classes;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Person {
	
	public int id;
	public String name;
	public String petname;
	public String breed;
	public String address;
	public Date dateLost;
	
	@SuppressWarnings("deprecation")
	public Person(int id, String name, String address, String petname, String breed, Date dateLost){
		/**this.id=id;
		this.name=name;
		this.petname=petname;
		this.breed=breed;
		this.address=address;
		this.dateLost=dateLost;*/
		setId(id);
		setName(name);
		setPetName(petname);
		setBreed(breed);
		setAddress(address);
		setDateLost(dateLost.getDay(), dateLost.getMonth(), dateLost.getYear());
	}
	
	public Person(){
		
	}
	
	//setters
	public void setId(int id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setPetName(String name){
		this.petname=name;
	}
	
	public void setBreed(String name){
		this.breed=name;
	}
	
	public void setAddress(String name){
		this.address=name;
	}
	
	@SuppressWarnings("deprecation")
	public void setDateLost(int day, int month, int year){
		this.dateLost=new Date(year, month, day);
	}
	
	//getters
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPetName(){
		return petname;
	}
	
	public String getBreed(){
		return breed;
	}
	
	public String getAddress(){
		return address;
	}
	
	public Date getDate(){
		return dateLost;
	}

}
