import java.io.*;
import java.util.*;

public class LeafNode extends Node {
	
	protected ArrayList<DataNode> list = new ArrayList<DataNode>();
	protected LeafNode nextLeaf = null;
	protected LeafNode prevLeaf = null;
	//protected boolean isSplit = false;
	//protected boolean isLeafNode = true;
	
/*	LeafNode(double key, String val){
		DataNode dn = new DataNode(key, val);
		this.insertKey(dn);
	}
*/	
	protected LeafNode() {
		this.isLeafNode = true;
	}
	
	protected void insertKey(DataNode dn){
		if(list.isEmpty()){
			list.add(dn);
		}
		else{
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).key > dn.key){
					list.add(i, dn);
//					System.out.println(list.get(i).key + " "); 				// Comment later!!!!!!!
					return;
				}
			}
			list.add(dn);
			
//			ListIterator<DataNode> dnIter = list.listIterator();
//			while(dnIter.hasNext()){
//				if(dnIter.next().key > dn.key) {
//					int position = dnIter.previousIndex();
//					list.add(position, dn);
//					break;
//				}
//			}
		
		}
	}
}
