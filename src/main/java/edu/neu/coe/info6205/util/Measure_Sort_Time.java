package edu.neu.coe.info6205.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import edu.neu.coe.info6205.sort.simple.InsertionSort;

public class Measure_Sort_Time {

	public static void measure_insertion_sort(int size,List<String[]> dataLines) {
		InsertionSort<Integer> sort = new InsertionSort<>();
		Benchmark_Timer<Integer[]> bt = new Benchmark_Timer<Integer[]>("Insertion Sort Benchmark", null,
				(l) -> sort.sort(l, 0, l.length), null);
		int runs = 10;

		

		FileWriter f = new FileWriter("output.csv");
		int steps = 10000; // test from 1 step to 10000 steps

		Supplier<Integer[]> random = () -> random_list(size);
		double t = bt.runFromSupplier(random, runs);
		bt.logger.info(" Random List. Size = " + size + "  " + " Time = " + t + "\n");
		dataLines.add(new String[] { "random", Integer.toString(size),Double.toString(t)});

		Supplier<Integer[]> sorted = () -> sorted_list(size);
		t = bt.runFromSupplier(sorted, runs);
		bt.logger.info(" Sorted List. Size = " + size + "  " + " Time = " + t + "\n");
		dataLines.add(new String[] { "sorted", Integer.toString(size),Double.toString(t)});

		Supplier<Integer[]> reverse = () -> random_list(size);
		t = bt.runFromSupplier(reverse, runs);
		bt.logger.info(" Reversed List. Size = " + size + "  " + " Time = " + t + "\n");
		dataLines.add(new String[] { "reversed", Integer.toString(size),Double.toString(t)});

		Supplier<Integer[]> part = () -> partial_sorted_list(size);
		t = bt.runFromSupplier(part, runs);
		bt.logger.info(" Partially-sorted List. Size = " + size + "  " + " Time = " + t + "\n");
		dataLines.add(new String[] { "partial_sorted", Integer.toString(size),Double.toString(t)});
		
	
	}

	public static Integer[] random_list(int size) {
		List<Integer> l = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			l.add(r.nextInt(10000));
		}
		return l.toArray(new Integer[l.size()]);
	}

	public static Integer[] sorted_list(int size) {
		List<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			l.add(i);
		}
		return l.toArray(new Integer[l.size()]);
	}

	public static Integer[] reverse_list(int size) {
		List<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			l.add(size - 1);
		}
		return l.toArray(new Integer[l.size()]);
	}

	public static Integer[] partial_sorted_list(int size) {
		List<Integer> l = new ArrayList<Integer>();
		Random rd = new Random();
		double r = rd.nextDouble() * 0.9;
		for (int i = 0; i < size * r; i++) {
			l.add(i);
		}
		for (int i = size - 1; i >= size * r; i--) {
			l.add(rd.nextInt(10000));
		}

		return l.toArray(new Integer[l.size()]);
	}

	public static void main(String[] args) {
		List<String[]> dataLines = new ArrayList<>();
		dataLines.add(new String[] { "Type", "Size", "Time" });
		int n = 100;
		for (int i = 0; i < 6; i++) {
			measure_insertion_sort(n,dataLines);
			n = n * 2;
		}
		FileWriter f = new FileWriter("output.csv");
		try {
			f.toCSV(dataLines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
