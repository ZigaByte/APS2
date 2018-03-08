import java.util.*;

public class Izziv1{
	
	int[] generateTable(int n){
		int[] t = new int[n];
		for(int i = 0; i < n; i++)
			t[i] = i + 1;
		return t;
	}
	
	int findLinear(int[] a, int v){
		for(int i = 0; i < a.length; i++){
			if(a[i] == v)
				return a[i];
		}
		return 0;
	}
	
	int findBinary(int[] a, int l, int r, int v){
		int c = (l + r) / 2;
		if(a[c] == v) {
			return a[c];
		} else if(a[c] < v) {
			return findBinary(a, c, r, v);
		} else {
			return findBinary(a, l, c, v);
		}
	}
	
	long timeLinear(int n){
		long startTime = System.nanoTime();
		Random r = new Random();
		
		int[] t = generateTable(n);
		for(int i = 0; i < 1000; i++){
			int v = r.nextInt(n) + 1;
			findLinear(t, v);
		}
		return System.nanoTime() - startTime;
	}
	
	long timeBinary(int n){
		long startTime = System.nanoTime();
		Random r = new Random();
		
		int[] t = generateTable(n);
		for(int i = 0; i < 1000; i++){
			int v = r.nextInt(n) + 1;
			findBinary(t, 0, t.length, v);
		}
		return System.nanoTime() - startTime;
	}
	
	public static void main(String... args){
		Izziv1 is = new Izziv1();
		System.out.println("    n    | linearno | dvojisko ");
		System.out.println("--------------------------------");
		for(int i = 1000; i <= 100000; i+= 1000){
			System.out.printf("%8d | %8d | %8d\n", i , is.timeLinear(i) / 1000, is.timeBinary(i) / 1000);
		}
	}
	
	
}