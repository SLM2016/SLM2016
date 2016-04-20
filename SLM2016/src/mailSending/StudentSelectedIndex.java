package mailSending;

import java.util.ArrayList;
import java.util.List;

public class StudentSelectedIndex {
	private ArrayList<Integer> indexes_;
	
	public StudentSelectedIndex(){
		indexes_=new ArrayList<Integer>();
	}
	
	public StudentSelectedIndex clone(){
		StudentSelectedIndex cloneObject=new StudentSelectedIndex();
		cloneObject.indexes_=new ArrayList<Integer>();
		for (int i = 0; i < indexes_.size(); i++)
			cloneObject.indexes_.add(indexes_.get(i));
		return cloneObject;
	}
	
	public void add(int index){
		indexes_.add(new Integer(index));
	}
	
	public List<Integer> getIndexes(){
		return indexes_;
	}
}
