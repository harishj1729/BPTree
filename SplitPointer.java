import java.io.*;
import java.util.*;

public class SplitPointer extends Node{
	protected static boolean isSplit = true;
	protected static double key;
	protected static Node pointer;
	
	public SplitPointer(double k, Node n) {
		this.key = k;
		this.pointer = n;
	}

}
