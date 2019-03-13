package stacks;

import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;

import bridges.base.SLelement;


public class MyBridgesStack<E> extends MyStack<E> {
	public static void main(String args[]) {


		Bridges bridges = new Bridges(3,"magroves", "1226519867954");
		bridges.setTitle("Bridges Stack");

		SLelement <Integer> a = new SLelement<Integer>(20);
		SLelement <Integer> b = new SLelement<Integer>(13);
		SLelement <Integer> c = new SLelement<Integer>(45);
		SLelement <Integer> d = new SLelement<Integer>(22);
		SLelement <Integer> e = new SLelement<Integer>(85);
		SLelement <Integer> f = new SLelement<Integer>(5);

		// set each elements next node in list
		a.setNext(b);
		b.setNext(c);
		c.setNext(d);
		d.setNext(e);
		e.setNext(f);


		MyStack <SLelement<Integer>> s = new MyStack<SLelement<Integer>>();
		s.push(f);
		s.push(e);
		s.push(d);
		s.push(c);
		s.push(b);
		s.push(a);

		

		while (!s.isEmpty()) {
			
			SLelement<Integer> currentElement = s.pop();
			
			// color the node
			currentElement.getVisualizer().setColor("yellow");

			if (currentElement.getNext() != null) {
				// color the link
				currentElement
				.getLinkVisualizer(currentElement.getNext())
				.setColor("blue");
				// adjust link thickness
				currentElement
				.getLinkVisualizer(currentElement.getNext())
				.setThickness(1.0);//75 percent thinner
			}

			currentElement.setLabel(String.valueOf(currentElement.getValue()));
			currentElement.getLabel();
			currentElement = currentElement.getNext();
		}

		bridges.setDataStructure(a);

		display(bridges);


	} // end main

	public static void display(Bridges b) {
		try {
			
			b.visualize();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RateLimitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
