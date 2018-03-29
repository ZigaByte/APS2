import java.util.Random;

class Izziv4{
	
	private static final int INT_LIMIT = 1000;
	
	static class Rezultat {
		int min, max, primerjav;
	}
	
	static int[] generirajTabelo(int n){
		int[] t = new int[n];
		Random r = new Random();
		for(int i = 0; i < t.length; i++){
			t[i] = r.nextInt(INT_LIMIT);
		}
		return t;
	}
	
	/*
	*	Iterativna metoda, ki poišèe min, max in število primerjav
	*	O(n)
	*/
	static Rezultat minMaxIter(int[] t){
		if(t.length == 0) return null;
		
		Rezultat r = new Rezultat();
		r.min = t[0];
		r.max = t[0];
		r.primerjav = 0;
		for(int a : t){
			if(a > r.max){
				r.max = a;
				r.primerjav++;
				continue;
			}else if(a < r.min){
				r.min = a;
				r.primerjav += 2;
				continue;
			}
			r.primerjav += 2;
		}
		return r;
	}
	
	/*
	*	Minmax po postopku divide and conquer
	*
	*	Po master theorem:
	*	d = 0, a = 2, b = 2
	* 	=> O(n)
	*/
	static Rezultat minMaxDAC(int[] t, int i0, int i1){
		if(i1 == i0){
			Rezultat r = new Rezultat();
			r.min = t[i0];
			r.max = t[i0];
			r.primerjav =  0;
			return r;
		} else if (i1 - 1 == i0){
			Rezultat r = new Rezultat();
			if(t[i0] < t[i1]){
				r.min = t[i0];
				r.max = t[i1];
			}else{
				r.min = t[i1];
				r.max = t[i0];
			}

			r.primerjav =  1;
			return r;
		}
		
		int iS = (i0 + i1) / 2;
		Rezultat r1 = minMaxDAC(t, i0, iS);
		Rezultat r2 = minMaxDAC(t, iS + 1, i1);
		
		Rezultat rez = new Rezultat();
		if (r1.min < r2.min) {
			rez.min = r1.min;
		} else {
			rez.min = r2.min;
		}
		if (r1.max > r2.max) {
			rez.max = r1.max;
		} else {
			rez.max = r2.max;
		}
		rez.primerjav = r1.primerjav + r2.primerjav + 2;
		
		return rez;
	}
	
	
	public static void main(String... args){
		for(int i = 1; i < 100; i++){
			int[] t = generirajTabelo(i);
			Rezultat iter = minMaxIter(t);
			Rezultat rek = minMaxDAC(t, 0, t.length - 1);
			System.out.printf("%d: %d, %d, %d, %d, %d, %d\n", i, iter.min, iter.max, iter.primerjav, rek.min, rek.max, rek.primerjav);
			// rek naredi manj primerjav.
		}
	}
	
}