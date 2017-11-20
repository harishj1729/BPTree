
import java.io.*;
import java.util.*;

public class IntNode extends Node {
	
	protected ArrayList<Double> list = new ArrayList<Double>();
	protected ArrayList<Node> pointers = new ArrayList<Node>();
	//protected boolean isSplit = false;
	
	protected void insert(double key, Node ptr1, Node ptr2){
		if(this.list.isEmpty()){
			this.list.add(key);
			this.pointers.add(ptr1);
			this.pointers.add(ptr2);
		}
		else{
			ListIterator<Double> iter = this.list.listIterator();
			while(iter.hasNext()){
				if(iter.next() > key) {
					int position = iter.previousIndex();
					this.list.add(position, key);
					this.pointers.add(position+1, ptr2);
					break;
				}
			}
		}
	}
	
}
