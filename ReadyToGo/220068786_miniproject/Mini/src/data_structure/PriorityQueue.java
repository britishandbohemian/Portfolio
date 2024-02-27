package data_structure;



public class PriorityQueue <T extends Comparable<T>> {

	private LinkedList<T> list;

	public PriorityQueue() {
		
		this.list = new LinkedList<T>();
	}
	
	public void insert(T element) {
		list.add(element);
	}
	
	public int size() {
		return list.size();
	}
	
	public String toString() {
		return list.toString();
	}
	
	
}
