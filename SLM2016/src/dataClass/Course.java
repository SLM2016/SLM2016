package dataClass;

public class Course {
	//private int courseId_;
	private String courseName_;
	private String batch_;
	private String date_;
	private String duration_;
	private String ticketType_;
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

	public void setBatch(String batch) {
		batch_ = batch;
	}

	public void setDate(String date) {
		date_ = date;
	}

	public void setDuration(String duration) {
		duration_ = duration;
	}

	public void setTicketType(String ticketType) {
		ticketType_ = ticketType;
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

	public String getBatch() {
		return batch_;
	}

	public String getDate() {
		return date_;
	}

	public String getDuration() {
		return duration_;
	}

	public String getTicketType() {
		return ticketType_;
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