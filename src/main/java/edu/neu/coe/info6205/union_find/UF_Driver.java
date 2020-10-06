package edu.neu.coe.info6205.union_find;

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

		for (int i = 100; i < 30000; i += 100) {

			int total = 0;
			for (int jk = 0; jk < runs; jk++) {
				total = total + count(i);
			}

			count++;
			double logFactor = Math.log(i) * i;
			c += (total / runs) / logFactor;
			System.out.println("n = " + i + ", pairs = " + total / runs + ", run = " + runs);
			System.out.println("n = " + i + ", m = " + total / runs + ", c = " + ((total / runs) / logFactor) + "\n");
		}

		System.out.println("Avg(c) = " + (c / count));

	}

	
}