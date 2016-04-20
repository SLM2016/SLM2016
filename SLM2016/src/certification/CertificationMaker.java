package certification;

public class CertificationMaker {

	public CertificationMaker() {
		// TODO Auto-generated constructor stub
	}
	
	public Certification MakeCertification(String courseId,String studentId)
	{
		//connect to DB require certification data which according courseId or studentId
		Certification certification=new Certification();
		certification.setId(courseId);
		certification.setOwner(studentId);
		return certification;
	}
}
