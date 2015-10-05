package pet.rest.classes;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement 
public class Person {
	
	@XmlElement
	public int id;
	@XmlElement
	public String name;
	@XmlElement
	public String petname;
	@XmlElement
	public String breed;
	@XmlElement
	public String address;
	@XmlElement
	public Date dateLost;
	
	@SuppressWarnings("deprecation")
	public Person(int id, String name, String address, String petname, String breed){
		setId(id);
		setName(name);
		setPetName(petname);
		setBreed(breed);
		setAddress(address);
		//setDateLost(dateLost.getDay(), dateLost.getMonth(), dateLost.getYear());
	}
	
	@SuppressWarnings("deprecation")
	public Person(String name, String address, String petname, String breed){
		setName(name);
		setPetName(petname);
		setBreed(breed);
		setAddress(address);
		//setDateLost(dateLost.getDay(), dateLost.getMonth(), dateLost.getYear());
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
	
	public Date getDateLost(){
		return dateLost;
	}
	
	//methods
	public String toString(){
		return "Name: " + getName() + " Address: " + getAddress() + " Date lost: " + getDateLost().getDate() +
				"/" + getDateLost().getMonth() + "/" + getDateLost().getYear();
	}

}
