import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
	private String Date;
	private String Mood;
	
	public MyDate(Date date,String mood) {
		SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		Date=format.format(date);
		Mood=mood;
	}
}
