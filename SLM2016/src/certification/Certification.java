package certification;

public class Certification {
	private String id_="";
	private String owner_="";
		
	public Certification() {
	}
	
	@Override
	public Certification clone()
	{
		Certification cloneObject=new Certification();
		cloneObject.setId(this.getId());
		cloneObject.setOwner(this.getOwner());
		return cloneObject;
	}
	
	public void setId(String id)
	{
		id_=id;
	}
	
	public void setOwner(String owner)
	{
		owner_=owner;
	}
		
	public String getId()
	{
		return id_;
	}
	
	public String getOwner()
	{
		return owner_;
	}
}
