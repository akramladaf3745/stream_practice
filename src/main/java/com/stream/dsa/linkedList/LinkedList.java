package com.stream.dsa.linkedList;

import java.util.Iterator;

public class LinkedList {

	private Node head;
	private Node tail;
	private int length;

	public class Node {
		private int value;
		private Node next;

		public Node(int value) {
			super();
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

	public LinkedList(int value) {
		Node node = new Node(value);
		head = node;
		tail = node;
		length = 1;
	}

	public void printLidst() {
		Node temp = head;
		while (temp != null) {
			System.err.println(temp.value);
			temp = temp.next;

		}
	}

	public void getHead() {
		System.err.println("Head :" + head.value);
	}

	public void getTail() {
		System.err.println("Tail :" + tail.value);
	}

	public void getLength() {
		System.err.println("Length :" + length);
	}

	public void add(int value) {
		Node newNode = new Node(value);
		if (length == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;

		}
		length++;

	}

	public Node removeLast() {
		if (length == 0) {
			return null;
		}

		Node temp = head;
		Node pre = head;

		while (temp.next != null) {
			pre = temp;
			temp = temp.next;

		}
		tail = pre;
		tail.next = null;
		length--;
		if (length == 0) {
			head = null;
			tail = null;
		}
		return temp;

	}

	public void preApend(int value) {
		Node node = new Node(value);
		if (length == 0) {
			head = node;
			tail = node;
		}
		node.next = head;
		head = node;
		length++;

	}

	public Node RemoveFirst() {
		if (length == 0) {
			return null;
		}
		Node temp = head;
		head = head.next;
		temp.next = null;
		length--;
		if (length == 0) {
			head = null;
			tail = null;
		}
		return temp;

	}

	public Node get(int index) {
		if (index < 0 || index >= length) {
			return null;
		}

		Node temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp;
	}

	public Boolean set(int index, int value) {

		Node temp = get(index);

		if (temp != null) {
			temp.setValue(value);
			return true;
		}
		return false;
	}

	public boolean insert(int index, int value) {
		if (index < 0 || index > length) {
			return false;
		}
		if (index == 0) {
			preApend(value);
			return true;
		}
		if (index == length) {
			add(value);
			return true;
		}
		Node node = new Node(value);
		Node temp = get(index - 1);
		node.next = temp.next;

		temp.next = node;
		length++;
		return true;

	}

	public Node remove(int index) {
		if (index < 0 || index >= length) {
			return null;
		}
		if (index == length - 1) {
			return removeLast();
		}
		if (index == 0) {
			return RemoveFirst();
		}
		Node pre = get(index - 1);
		Node temp = pre.next;
        pre.next=temp.next;
        temp.next=null;
        length--;
		return temp;

	}
	
	public void reverse() {
		Node temp=head;
		head=tail;
		tail=temp;
		Node after=temp.next;
		Node before=null;
		
		for (int i = 0; i < length; i++) {
			after=temp.next;
			temp.next=before;
			before=temp;
			temp=after;
		}
	}

}
