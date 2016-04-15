package mailSending;

import java.util.ArrayList;
import java.util.List;

public class ClassInfomation {
	private String className;
	private List<String> students;

	public ClassInfomation(String className) {
		this.className = className;
		students = new ArrayList<String>();
	}

	public ClassInfomation Clone() {
		ClassInfomation cloneObject = new ClassInfomation(this.className);
		cloneObject.students = new ArrayList<String>();
		for (int i = 0; i < this.students.size(); i++)
			cloneObject.students.add(this.students.get(i));
		return cloneObject;
	}

	public void AddStudent(String studentName) {
		students.add(studentName);
	}

	public String GetClassName() {
		return className;
	}

	public List<String> GetStudents() {
		return students;
	}
}
