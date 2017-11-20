import java.io.*;
import java.util.*;

import javax.xml.crypto.Data;


public class BPTree {
	
	public static Node root = null;
	
	public static int degree;
	
	public BPTree(int deg){
		this.degree = deg;
	}
	
	protected static Node Ins(Node root, DataNode dn){
		
		if(!root.isLeafNode){
			root.isSplit = false;
			ListIterator<Double> lr = ((IntNode)root).list.listIterator();
			while(lr.hasNext()){
				if(lr.next() > dn.key) {
					int position = lr.previousIndex();
					Node temp = new Node();
					temp = Ins(((IntNode)root).pointers.get(position), dn);
					if(temp.isSplit){
						((IntNode)root).insert(((IntNode)temp).list.get(0), ((IntNode)temp).pointers.get(0),
								((IntNode)temp).pointers.get(1));
						if(((IntNode)root).list.size() == degree){
							int splitPos = ((int) Math.ceil(((double)degree)/2)) - 1;
							IntNode iNode = new IntNode();
							IntNode childNode = new IntNode();
							iNode.insert(((IntNode)root).list.get(splitPos), root, childNode);
							for(int i = splitPos+1, j = splitPos+1; i < degree; i++){
								childNode.list.add(((IntNode)root).list.remove(j));
							}
							for(int i = splitPos+1, j = splitPos+1; i <= degree; i++){
								childNode.pointers.add(((IntNode)root).pointers.remove(j));
							}
							return iNode;
						}
						return root;
					}
					else
						return root;
					//break;
				}
			}
		}
		else{
			((LeafNode)root).insertKey(dn);
//			System.out.println("\n"); 			// comment later !!!!!!!!!!
			if(((LeafNode)root).list.size() == degree){
				int pos = degree/2;
				LeafNode ln = new LeafNode();
				for(int i = pos, j = pos; i < degree; i++){
					ln.insertKey(((LeafNode)root).list.remove(j));
				}
				
				ln.nextLeaf = ((LeafNode)root).nextLeaf;
				((LeafNode)root).nextLeaf = ln;
				ln.prevLeaf = ((LeafNode)root);
				if(ln.nextLeaf != null)
					ln.nextLeaf.prevLeaf = ln;
				//SplitPointer sp = new SplitPointer(ln.list.get(0).key, ln);
				IntNode splitPointer = new IntNode();
				splitPointer.list.add(ln.list.get(0).key);
				splitPointer.isSplit = true;
				splitPointer.pointers.add((LeafNode)root);
				splitPointer.pointers.add(ln);
				return splitPointer;
			}
			else
				return root;
		}
		
		return root;
	}
	
	protected static void Insert(double key, String val){
		DataNode dNode = new DataNode(key, val);
		if(root == null){
			root = new LeafNode();
			((LeafNode)root).insertKey(new DataNode(key, val));
		}
		else{
			root = Ins(root, dNode);
/*			
			if(root.isLeafNode){
				((LeafNode)root).insertKey(dNode);
			}
			else{
				Ins(root, dNode);
			}
*/			
			double q = key;
		}
		
	}
	
	protected static String Search(double key){
		Node temp = new Node();
		temp = root;
		while(!temp.isLeafNode){
			int index = 0;
			while(index < ((IntNode)temp).list.size() && ((IntNode)temp).list.get(index) < key){
				index++;
			}
			temp = ((IntNode)temp).pointers.get(index);
		}
		
		for(int i=0; i < ((LeafNode)temp).list.size(); i++){
			if(((LeafNode)temp).list.get(i).key == key){
				return ((LeafNode)temp).list.get(i).val;
			}
		}
		
		return "Null";
	}
	protected static StringBuilder stringHelper(StringBuilder sb, Node node, Double key1, Double key2){
		int initialInd;
		ArrayList<DataNode> leafList = new ArrayList<DataNode>();
		leafList = ((LeafNode)node).list;
		for(initialInd = 0; initialInd < leafList.size(); initialInd++){
			if(leafList.get(initialInd).key >= key1 && leafList.get(initialInd).key <= key2 ){
//				 ((LeafNode)temp).list.get(i).val;
				//break;
				sb.append("(").append(leafList.get(initialInd).key).append(",").append(leafList.get(initialInd).val).append("), ");
			}
			if(leafList.get(initialInd).key > key2){
//				if(sb == null)
//					return sb;
//				else
//					return sb;
				return sb;
			}
		}
		
		return sb;
	}
	
	protected static String Search(double key1, double key2){
		Node temp = new Node();
		temp = root;
		while(!temp.isLeafNode){
			int index = 0;
			while(index < ((IntNode)temp).list.size() && ((IntNode)temp).list.get(index) < key1){
				index++;
			}
			temp = ((IntNode)temp).pointers.get(index);
		}
		
		StringBuilder sb = new StringBuilder();
		//sb = null;
		
		sb = stringHelper(sb, temp, key1, key2);
		
		while(((LeafNode)temp).nextLeaf != null){
			//temp = ((LeafNode)temp).nextLeaf;
			if(((LeafNode)temp).nextLeaf.list.get(0).key > key2){
//				if(sb == null)
//					return "Null";
//				else
//					return sb.toString();
				break;
			}
			else{
				sb = stringHelper(sb, ((LeafNode)temp).nextLeaf, key1, key2);
			}
		}
		
		if(sb == null)
			return "Null";
		else
			return sb.toString();
	}
	
	
}
