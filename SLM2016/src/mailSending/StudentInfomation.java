package mailSending;

import java.util.ArrayList;
import java.util.List;

public class StudentInfomation {
	private ArrayList<String> studentsName_;
	private ArrayList<String> mailAddresses_;

	public StudentInfomation() {
		studentsName_ = new ArrayList<String>();
		mailAddresses_ = new ArrayList<String>();
	}

	public StudentInfomation clone() {
		StudentInfomation cloneObject = new StudentInfomation();
		cloneObject.studentsName_ = new ArrayList<String>();
		cloneObject.mailAddresses_ = new ArrayList<String>();
		for (int i = 0; i < this.studentsName_.size(); i++)
			cloneObject.studentsName_.add(this.studentsName_.get(i));
		for (int i = 0; i < this.mailAddresses_.size(); i++)
			cloneObject.mailAddresses_.add(this.mailAddresses_.get(i));

		return cloneObject;
	}

	public void setStudents() {
		// set data form DB
	}

	public void setMailAddress() {
		// set data form DB
	}

	public List<String> getStudents() {
		return studentsName_;
	}

	public List<String> getMailAddress() {
		return mailAddresses_;
	}

	public void generateFakeDataOne() {
		studentsName_.add("Alice");
		studentsName_.add("Bob");
		studentsName_.add("Chris");
		studentsName_.add("David");
		studentsName_.add("Edison");
		studentsName_.add("Frank");
		studentsName_.add("George");
		studentsName_.add("Harry");
		studentsName_.add("Ivan");
		studentsName_.add("Jack");

		studentsName_.add("Kevin");
		studentsName_.add("Lee");
		studentsName_.add("Mark");
		studentsName_.add("Nick");
		studentsName_.add("Oliver");
		studentsName_.add("Pandora");
		studentsName_.add("Queena");
		studentsName_.add("Ray");
		studentsName_.add("Sam");
		studentsName_.add("Ted");

		studentsName_.add("Ultraman");
		studentsName_.add("Victor");
		studentsName_.add("William");
		studentsName_.add("Xenia");
		studentsName_.add("Yolanda");
		studentsName_.add("Zack");
		studentsName_.add("Alicia");
		studentsName_.add("Betty");
		studentsName_.add("Charlotte");
		studentsName_.add("Doris");

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

	public void generateFakeDataTwo() {
		studentsName_.add("Alice");
		studentsName_.add("Bob");
		studentsName_.add("Chris");
		studentsName_.add("David");
		studentsName_.add("Edison");
		studentsName_.add("Frank");
		studentsName_.add("George");
		studentsName_.add("Harry");
		studentsName_.add("Ivan");
		studentsName_.add("Jack");

		studentsName_.add("Kevin");
		studentsName_.add("Lee");
		studentsName_.add("Mark");
		studentsName_.add("Nick");
		studentsName_.add("Oliver");
		studentsName_.add("Pandora");
		studentsName_.add("Queena");
		studentsName_.add("Ray");
		studentsName_.add("Sam");
		studentsName_.add("Ted");

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
	}

	public void generateFakeDataThree() {
		studentsName_.add("Alice");
		studentsName_.add("Bob");
		studentsName_.add("Chris");
		studentsName_.add("David");
		studentsName_.add("Edison");
		studentsName_.add("Frank");
		studentsName_.add("George");
		studentsName_.add("Harry");
		studentsName_.add("Ivan");
		studentsName_.add("Jack");

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
	}
}
