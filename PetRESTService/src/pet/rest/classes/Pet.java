package pet.rest.classes;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Pet {
	@XmlElement
	public int id;
	@XmlElement
	public String name;
	@XmlElement
	public String breed;
	@XmlElement
	public String area;
	@XmlElement
	public boolean found;
	
	public Pet(int id, String name, String breed, String area){
		/**this.id=id;
		this.name=name;
		this.breed=breed;
		this.area=area;
		this.found=false;*/
		setId(id);
		setName(name);
		setBreed(breed);
		setArea(area);
		setFound(false);
	}
	
	public Pet(){
		
	}
	
	//setters
	public void setId(int id){
		this.id=id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setBreed(String name){
		this.breed=name;
	}
	
	public void setArea(String name){
		this.area=name;
	}
	
	public void setFound(boolean f){
		this.found=f;
	}
	
	//getters
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getBreed(){
		return breed;
	}
	
	public String getArea(){
		return area;
	}
}
