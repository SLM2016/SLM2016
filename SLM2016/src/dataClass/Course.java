package dataClass;

public class Course {
	//private int courseId_;
	private String courseName_;
	private String datch_;
	private String date_;
	private String time_;
	private String ticket_;
	private int price_;
	private String location_;
	private String lecturer_;
	private String status_;

//	public Course(int id) {
//		courseId_ = id;
//	};

	public void setCourseName(String courseName) {
		courseName_ = courseName;
	}

	public void setDatch(String datch) {
		datch_ = datch;
	}

	public void setDate(String date) {
		date_ = date;
	}

	public void setTime(String time) {
		time_ = time;
	}

	public void setTicket(String ticket) {
		ticket_ = ticket;
	}

	public void setPrice(int price) {
		price_ = price;
	}

	public void setLocation(String location) {
		location_ = location;
	}

	public void setLecturer(String lecturer) {
		lecturer_ = lecturer;
	}

	public void setStatus(String status) {
		status_ = status;
	}

//	public int getCourseId() {
//		return courseId_;
//	}

	public String getCourseName() {
		return courseName_;
	}

	public String getDatch() {
		return datch_;
	}

	public String getDate() {
		return date_;
	}

	public String getTime() {
		return time_;
	}

	public String getTicket() {
		return ticket_;
	}

	public int getPrice() {
		return price_;
	}

	public String getLocation() {
		return location_;
	}

	public String getLecturer() {
		return lecturer_;
	}

	public String getStatus() {
		return status_;
	}
}