package mailSending;


import java.util.ArrayList;
import java.util.List;

public class StudentInfomation {
	private ArrayList<String> students;
	private ArrayList<String> mailAddresses;

	public StudentInfomation() {
		students = new ArrayList<String>();
		mailAddresses = new ArrayList<String>();
	}

	public StudentInfomation Clone() {
		StudentInfomation cloneObject = new StudentInfomation();
		cloneObject.students = new ArrayList<String>();
		cloneObject.mailAddresses = new ArrayList<String>();
		for (int i = 0; i < this.students.size(); i++)
			cloneObject.students.add(this.students.get(i));
		for (int i = 0; i < this.mailAddresses.size(); i++)
			cloneObject.mailAddresses.add(this.mailAddresses.get(i));
		
		return cloneObject;
	}

	public void SetStudents() {
		students.add("Alice");
		students.add("Bob");
		students.add("Chris");
		students.add("David");
		students.add("Eva");
		students.add("FLT");
		students.add("GTA");
		students.add("HIA");
		students.add("IT");
		students.add("Jack");

		students.add("Kevin");
		students.add("Lee");
		students.add("Mo");
		students.add("Net");
		students.add("Ox");
		students.add("P");
		students.add("Q");
		students.add("R");
		students.add("S");
		students.add("T");

		students.add("U");
		students.add("V");
		students.add("W");
		students.add("X");
		students.add("Y");
		students.add("Z");
		students.add("0.0");
		students.add("0w0");
		students.add("0o0");
		students.add("0A0");
	}

	public void SetMailAddress() {
		mailAddresses.add("t104598007+a@ntut.org.tw");
		mailAddresses.add("t104598007+b@ntut.org.tw");
		mailAddresses.add("t104598007+c@ntut.org.tw");
		mailAddresses.add("t104598007+d@ntut.org.tw");
		mailAddresses.add("t104598007+aaa@ntut.org.tw");
		mailAddresses.add("t104598007+bbb@ntut.org.tw");
		mailAddresses.add("t104598007+ccc@ntut.org.tw");
		mailAddresses.add("t104598007+ddd@ntut.org.tw");
		mailAddresses.add("t104598007+eee@ntut.org.tw");
		mailAddresses.add("t104598007+fff@ntut.org.tw");

		mailAddresses.add("t104598007+ggg@ntut.org.tw");
		mailAddresses.add("t104598007+hhh@ntut.org.tw");
		mailAddresses.add("t104598007+iii@ntut.org.tw");
		mailAddresses.add("t104598007+jjj@ntut.org.tw");
		mailAddresses.add("t104598007+kkk@ntut.org.tw");
		mailAddresses.add("t104598007+lll@ntut.org.tw");
		mailAddresses.add("t104598007+mmm@ntut.org.tw");
		mailAddresses.add("t104598007+nnn@ntut.org.tw");
		mailAddresses.add("t104598007+ooo@ntut.org.tw");
		mailAddresses.add("t104598007+ppp@ntut.org.tw");

		mailAddresses.add("t104598007+qqq@ntut.org.tw");
		mailAddresses.add("t104598007+rrr@ntut.org.tw");
		mailAddresses.add("t104598007+sss@ntut.org.tw");
		mailAddresses.add("t104598007+ttt@ntut.org.tw");
		mailAddresses.add("t104598007+uuu@ntut.org.tw");
		mailAddresses.add("t104598007+vvv@ntut.org.tw");
		mailAddresses.add("t104598007+www@ntut.org.tw");
		mailAddresses.add("t104598007+xxx@ntut.org.tw");
		mailAddresses.add("t104598007+yyy@ntut.org.tw");
		mailAddresses.add("t104598007+zzz@ntut.org.tw");
	}

	public List<String> GetStudents() {
		return students;
	}

	public List<String> GetMailAddress() {
		return mailAddresses;
	}
}
