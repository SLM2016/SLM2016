package mailSending;

import java.util.ArrayList;
import java.util.List;

public class SendStudentIndex {
	private ArrayList<Integer> indexes;
	
	public SendStudentIndex(){
		indexes=new ArrayList<Integer>();
	}
	
	public SendStudentIndex Clone(){
		SendStudentIndex cloneObject=new SendStudentIndex();
		cloneObject.indexes=new ArrayList<Integer>();
		for (int i = 0; i < this.indexes.size(); i++)
			cloneObject.indexes.add(this.indexes.get(i));
		return cloneObject;
	}
	
	public void Add(int index){
		indexes.add(new Integer(index));
	}
	
	public List<Integer> GetIndexes(){
		return indexes;
	}
}
