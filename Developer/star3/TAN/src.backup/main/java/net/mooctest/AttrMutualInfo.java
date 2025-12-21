package net.mooctest;

/**
 * 属�?�之间的互信息�?�，表示属�?�之间的关联性大�?
 * @author lyq
 *
 */
public class AttrMutualInfo implements Comparable<AttrMutualInfo>{
	//互信息�??
	Double value;
	//关联属�?��?�对
	Node[] nodeArray;
	
	public AttrMutualInfo(double value, Node node1, Node node2){
		this.value = value;
		
		this.nodeArray = new Node[2];
		this.nodeArray[0] = node1;
		this.nodeArray[1] = node2;
	}

	public int compareTo(AttrMutualInfo o) {
		// TODO Auto-generated method stub
		return o.value.compareTo(this.value);
	}
	
}
