package certification;

import java.awt.Point;

public class TemplateCertificationMaker {

	public TemplateCertificationMaker() {
	}
	
	public TemplateCertification MakeTemplateCertification(String courseId)
	{
		//connect to DB require template certification data which according courseId
			
		TemplateCertification template=new TemplateCertification();
		template.setIdTextSize(25);
		template.setIdLocation(new Point(1340,225));
		template.setOwnerTextSize(65);
		template.setOwnerLocation(new Point(390,370));
		return template;
	}
}
