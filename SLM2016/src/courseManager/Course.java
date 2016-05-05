package courseManager;

import java.util.ArrayList;
import java.util.List;

public class Course {
	// private int courseId_;
	private String courseName_;
	private String type_;
	private String batch_;
	private String date_;
	private int duration_;
	private List<String> ticketTypes_ = new ArrayList<String>();
	private List<Integer> prices_ = new ArrayList<Integer>();
	private String location_;
	private String lecturer_;
	private String status_;

	// public Course(int id) {
	// courseId_ = id;
	// };

	public Course clone() {
		Course cloneObject = new Course();
		cloneObject.courseName_ = courseName_;
		cloneObject.type_ = type_;
		cloneObject.batch_ = batch_;
		cloneObject.date_ = date_;
		cloneObject.duration_ = duration_;
		cloneObject.ticketTypes_.addAll(ticketTypes_);
		cloneObject.prices_.addAll(prices_);
		cloneObject.location_ = location_;
		cloneObject.lecturer_ = lecturer_;
		cloneObject.status_ = status_;
		return cloneObject;
	}

	public void setCourseName(String courseName) {
		courseName_ = courseName;
	}

	public void setType(String type) {
		type_ = type;
	}

	public void setBatch(String batch) {
		batch_ = batch;
	}

	public void setDate(String date) {
		date_ = date;
	}

	public void setDuration(int duration) {
		duration_ = duration;
	}

	public void addTicketType(String ticketType) {
		ticketTypes_.add(ticketType);
	}

	public void deleteTicketType(int index) {
		ticketTypes_.remove(index);
	}

	public void addPrice(int price) {
		prices_.add(price);
	}

	public void deletePrice(int index) {
		prices_.remove(index);
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

	public String getType() {
		return type_;
	}

	public String getBatch() {
		return batch_;
	}

	public String getDate() {
		return date_;
	}

	public int getDuration() {
		return duration_;
	}

	public List<String> getTicketTypes() {
		return ticketTypes_;
	}

	public List<Integer> getPrices() {
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