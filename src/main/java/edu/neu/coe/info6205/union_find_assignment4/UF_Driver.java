package edu.neu.coe.info6205.union_find_assignment4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UF_Driver {
	
	public static int count(int n) {
		UF_HWQUPC u = new UF_HWQUPC(n, false);
		
		Random random = new Random();
		int c = 0;
		while (u.components() != 1) {
			int n1 = random.nextInt(n);
			int n2 = random.nextInt(n);
			if (!u.connected(n1, n2)) {
				u.union(n1, n2);
			}
			c++;
		}
		return c;
	}

	public static void main(String[] args) {
		int runs = 100;
		double c = 0; //coefficient
		int count = 0;
		List<String[]> dataLines = new ArrayList<>();
		String[] s = {"n","m","r", "c"};
		dataLines.add(s);
		
		for (int i = 100; i < 30000; i += 100) {

			int total = 0;
			for (int jk = 0; jk < runs; jk++) {
				total = total + count(i);
			}

			count++;
			c += (total / runs) / (Math.log(i) * i);
			String n = Integer.toString(i);
			String m = Double.toString(total / runs);
			String r = Integer.toString(runs);
			String coe = Double.toString((total / runs) / (Math.log(i) * i));
			System.out.println("n = " + n + ", m = " + m + ", run = " + r + ", c = " + coe);
			String[] l  = {n,m,r,coe};
			dataLines.add(l);
			
		}

		FileWriter f = new FileWriter("assignment3_output.csv");
		try {
			f.toCSV(dataLines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Avg(c) = " + (c / count));

	}

	
}