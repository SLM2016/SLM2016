package certification;

import java.awt.Point;

public class TemplateCertification {
	private int idTextSize_=0;
	private int ownerTextSize_=0;
	private Point idLocation_=new Point(0,0);
	private Point ownerLocation_=new Point(0,0);

	public TemplateCertification() {
	}
		
	@Override
	public TemplateCertification clone()
	{
		TemplateCertification cloneObject=new TemplateCertification();
		cloneObject.setIdLocation(this.getIdLocation());
		cloneObject.setOwnerLocation(this.getOwnerLocation());
		cloneObject.setIdTextSize(this.getIdTextSize());
		cloneObject.setOwnerTextSize(this.getOwnerTextSize());
		return cloneObject;
	}
	
	public void setIdTextSize(int idTextSize)
	{
		idTextSize_=idTextSize;
	}
	
	public void setOwnerTextSize(int ownerTextSize)
	{
		ownerTextSize_=ownerTextSize;
	}
	
	public void setIdLocation(Point location)
	{
		idLocation_=(Point) location.clone();
	}
	
	public void setOwnerLocation(Point location)
	{
		ownerLocation_=(Point) location.clone();
	}
	
	public Point getIdLocation()
	{
		return (Point) idLocation_.clone();
	}
	
	public Point getOwnerLocation()
	{
		return (Point) ownerLocation_.clone();
	}

	public int getIdTextSize()
	{
		return idTextSize_;
	}
	
	public int getOwnerTextSize()
	{
		return ownerTextSize_;
	}
}
