package mailSending;

import java.util.ArrayList;
import java.util.List;

public class ClassInfomation {
	private String className_;
	private List<String> students_;

	public ClassInfomation(String className) {
		this.className_ = className;
		students_ = new ArrayList<String>();
	}

	public ClassInfomation clone() {
		ClassInfomation cloneObject = new ClassInfomation(this.className_);
		cloneObject.students_ = new ArrayList<String>();
		for (int i = 0; i < this.students_.size(); i++)
			cloneObject.students_.add(this.students_.get(i));
		return cloneObject;
	}

	public void addStudent(String studentName) {
		students_.add(studentName);
	}

	public String getClassName() {
		return className_;
	}

	public List<String> getStudents() {
		return students_;
	}
}
