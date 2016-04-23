package mailSending;

import java.util.ArrayList;
import java.util.List;

public class StudentSelectedIndex {
	private int classIndex_;
	private ArrayList<Integer> indexes_;
	
	public StudentSelectedIndex(int index){
		classIndex_= index;
		indexes_=new ArrayList<Integer>();
	}
	
	public StudentSelectedIndex clone(){
		StudentSelectedIndex cloneObject=new StudentSelectedIndex(classIndex_);
		cloneObject.indexes_ = new ArrayList<Integer>();
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
	
	public int getClassIndex(){
		return classIndex_;
	}
}
