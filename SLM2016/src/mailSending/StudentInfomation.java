package mailSending;

import java.util.ArrayList;
import java.util.List;

public class StudentInfomation {
	private ArrayList<String> students_;
	private ArrayList<String> mailAddresses_;

	public StudentInfomation() {
		students_ = new ArrayList<String>();
		mailAddresses_ = new ArrayList<String>();
	}

	public StudentInfomation clone() {
		StudentInfomation cloneObject = new StudentInfomation();
		cloneObject.students_ = new ArrayList<String>();
		cloneObject.mailAddresses_ = new ArrayList<String>();
		for (int i = 0; i < this.students_.size(); i++)
			cloneObject.students_.add(this.students_.get(i));
		for (int i = 0; i < this.mailAddresses_.size(); i++)
			cloneObject.mailAddresses_.add(this.mailAddresses_.get(i));
		
		return cloneObject;
	}

	public void setStudents() {
		students_.add("Alice");
		students_.add("Bob");
		students_.add("Chris");
		students_.add("David");
		students_.add("Eva");
		students_.add("FLT");
		students_.add("GTA");
		students_.add("HIA");
		students_.add("IT");
		students_.add("Jack");

		students_.add("Kevin");
		students_.add("Lee");
		students_.add("Mo");
		students_.add("Net");
		students_.add("Ox");
		students_.add("P");
		students_.add("Q");
		students_.add("R");
		students_.add("S");
		students_.add("T");

		students_.add("U");
		students_.add("V");
		students_.add("W");
		students_.add("X");
		students_.add("Y");
		students_.add("Z");
		students_.add("0.0");
		students_.add("0w0");
		students_.add("0o0");
		students_.add("0A0");
	}

	public void setMailAddress() {
		mailAddresses_.add("t104598007+a@ntut.org.tw");
		mailAddresses_.add("t104598007+b@ntut.org.tw");
		mailAddresses_.add("t104598007+c@ntut.org.tw");
		mailAddresses_.add("t104598007+d@ntut.org.tw");
		mailAddresses_.add("t104598007+aaa@ntut.org.tw");
		mailAddresses_.add("t104598007+bbb@ntut.org.tw");
		mailAddresses_.add("t104598007+ccc@ntut.org.tw");
		mailAddresses_.add("t104598007+ddd@ntut.org.tw");
		mailAddresses_.add("t104598007+eee@ntut.org.tw");
		mailAddresses_.add("t104598007+fff@ntut.org.tw");

		mailAddresses_.add("t104598007+ggg@ntut.org.tw");
		mailAddresses_.add("t104598007+hhh@ntut.org.tw");
		mailAddresses_.add("t104598007+iii@ntut.org.tw");
		mailAddresses_.add("t104598007+jjj@ntut.org.tw");
		mailAddresses_.add("t104598007+kkk@ntut.org.tw");
		mailAddresses_.add("t104598007+lll@ntut.org.tw");
		mailAddresses_.add("t104598007+mmm@ntut.org.tw");
		mailAddresses_.add("t104598007+nnn@ntut.org.tw");
		mailAddresses_.add("t104598007+ooo@ntut.org.tw");
		mailAddresses_.add("t104598007+ppp@ntut.org.tw");

		mailAddresses_.add("t104598007+qqq@ntut.org.tw");
		mailAddresses_.add("t104598007+rrr@ntut.org.tw");
		mailAddresses_.add("t104598007+sss@ntut.org.tw");
		mailAddresses_.add("t104598007+ttt@ntut.org.tw");
		mailAddresses_.add("t104598007+uuu@ntut.org.tw");
		mailAddresses_.add("t104598007+vvv@ntut.org.tw");
		mailAddresses_.add("t104598007+www@ntut.org.tw");
		mailAddresses_.add("t104598007+xxx@ntut.org.tw");
		mailAddresses_.add("t104598007+yyy@ntut.org.tw");
		mailAddresses_.add("t104598007+zzz@ntut.org.tw");
	}

	public List<String> getStudents() {
		return students_;
	}

	public List<String> getMailAddress() {
		return mailAddresses_;
	}
}
