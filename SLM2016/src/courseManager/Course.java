package courseManager;

import java.util.ArrayList;
import java.util.List;

public class Course {
	// private int courseId_;
	private String courseName_;
	private String type_;
	private String batch_;
	private String date_;
	private String duration_;
	private String ticketTypes_;
	private int prices_;
	private String location_;
	private String lecturer_;
	private String status_;

	// public Course(int id) {
	// courseId_ = id;
	// };

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
		ticketTypes_ = ticketType;
	}

	public void setPrice(int price) {
		prices_ = price;
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

	// public int getCourseId() {
	// return courseId_;
	// }

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
		return ticketTypes_;
	}

	public int getPrice() {
		return prices_;
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