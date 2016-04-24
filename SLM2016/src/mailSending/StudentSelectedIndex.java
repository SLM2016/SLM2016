package mailSending;

import java.util.ArrayList;
import java.util.List;

public class StudentSelectedIndex {
	private int classIndex_;
	private String ccAddresses_;
	private ArrayList<Integer> indexes_;

	public StudentSelectedIndex(int index, String ccAddresses) {
		classIndex_ = index;
		ccAddresses_ = ccAddresses;
		indexes_ = new ArrayList<Integer>();
	}

	public StudentSelectedIndex clone() {
		StudentSelectedIndex cloneObject = new StudentSelectedIndex(classIndex_, ccAddresses_);
		cloneObject.indexes_ = new ArrayList<Integer>();
		for (int i = 0; i < indexes_.size(); i++)
			cloneObject.indexes_.add(indexes_.get(i));
		return cloneObject;
	}

	public void add(int index) {
		indexes_.add(new Integer(index));
	}

	public List<Integer> getIndexes() {
		return indexes_;
	}

	public int getClassIndex() {
		return classIndex_;
	}

	public String getCCAddresses() {
		return ccAddresses_;
	}
}
