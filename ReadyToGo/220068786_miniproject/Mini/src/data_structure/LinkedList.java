package data_structure;

import java.lang.reflect.Array;

import java.util.Collection;
import java.util.Iterator;

import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

public class LinkedList <T extends Comparable<T>> implements Collection<T>{
	
	private Node<T> head;
	private int size;
	
	
	public LinkedList() {
		this.head = null;
		this.size = 0;
	}


	
	public void AddFirst(T data) {
		Node<T> newNode = new Node<>(data);
		newNode.nextNode = head;
		head = newNode;
		size++;
	}

	public void addVertex(Vertex<T> vertex) {
		addVertex(vertex);
	}
	
	public void addEdge(Edge<T> edge) {
		addEdge(edge);
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public Node<T> getHead(){
		return head;
	}
	
	public void remove(Node<T> node) {
		if(head == null) {
			return;
		}
		
		if(head == node) {
			head = head.nextNode;
			size --;
			return;
		}
		
		Node<T> currentNode = head;
		while(currentNode.nextNode != null) {
			if(currentNode.nextNode == node) {
				currentNode.nextNode = node.nextNode;
				size--;
				return;
			}
			currentNode = currentNode.nextNode;
		}
	}
	
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		
		Node<T> currentNode = head;
		while (currentNode != null) {
			
			sBuilder.append(currentNode.data.toString());
			
			if(currentNode.nextNode != null) {
				sBuilder.append(" -> ");
			}
			
			currentNode = currentNode.nextNode;
			
		}
		return sBuilder.toString();
	}

	@Override
	public boolean contains(Object o) {
		if(head == null) {
			return false;
		}
		
		Node<T> currentNode = head;
		
		while(currentNode != null) {
			if(currentNode.data.equals(o)) {
				return true;
			}
			currentNode = currentNode.nextNode;
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {


		return new Iterator<T>() {

			private Node<T> currentNode = head;
			@Override
			public boolean hasNext() {
			
				return currentNode != null;
			}

			@Override
			public T next() {

				T dataT = currentNode.data;
				currentNode = currentNode.nextNode;

				return dataT;
			}
		};
		
	}

	@Override
	public Object[] toArray() {
		
		Object[] array = new Object[size];
		int i = 0;
		
		Node<T> current = head;
		
		while(current != null) {
			array[i++] = current.data;
			current = current.nextNode;
		}
		
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T1> T1[] toArray(T1[] a) {


		if(a.length < size) {
			a = (T1[]) Array.newInstance(a.getClass().getComponentType(), size);
		}
		int i = 0;
		
		Node<T> currentNode = head;
		
		while (currentNode != null) {
			a[i++] = (T1) currentNode.data;
			currentNode = currentNode.nextNode;
		}
		
		if(a.length > size) {
			a[size] = null;
		}
		return a;
	}

	@Override
	public boolean add(T e) {
	Node<T> newNode = new Node<>(e);
		
		if(head == null) {
			head = newNode;
		}else {
			Node<T> currentNode = head;
			
			while (currentNode.nextNode != null) {
				
				currentNode = currentNode.nextNode;
			}
			currentNode.nextNode = newNode;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {

if(head == null) {
	return false;
}

if(head.data.equals(o)) {
	head = head.nextNode;
	size--;
	return true;
}
	
Node<T> currentNode = head;
while(currentNode.nextNode != null) {
	if(currentNode.nextNode.data.equals(o)) {
		size--;
		return true;
	}
	currentNode = currentNode.nextNode;
}
return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {


		for(Object element : c) {
		
			if(!contains(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean modify = false;
		
		for(T elemenT : c) {
		
			add(elemenT);
			modify = true;
		}
		return modify;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> c) {


		boolean modify = false;
		
		for(Object object : c) {
			if(contains(object)) {
				remove((T)object);
				modify =  true;
			}
		}
		return modify;
	}

	@Override
	public boolean retainAll(Collection<?> c) {


		boolean modify = false;
		Node<T> currentNode = head;
		while (currentNode != null) {
			if(!c.contains(currentNode.data)) {
				remove(currentNode.data);
				modify =  true;
			}
			
		}
		return true;
	}

	@Override
	public void clear() {
	head= null;
	size = 0;
		
	}
}
