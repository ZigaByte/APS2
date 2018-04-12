import java.util.Scanner;

class Izziv6{
	
	static class Complex{
		double x, y;
		
		Complex(double x, double y){
			this.x = x;
			this.y = y;
		}
		
		Complex conjugate(){
			return new Complex(x, -y);
		}
		
		Complex add(Complex a){
			return new Complex(x + a.x, y + a.y);
		}
		Complex sub(Complex a){
			return new Complex(x - a.x, y - a.y);
		}
		
		Complex multiply(double a){
			return new Complex(x * a, y * a);	
		}
		
		Complex multiply(Complex a){
			return new Complex(x * a.x - y * a.y, x * a.y + y * a.x);
		}
		
		Complex divide(Complex a){
			Complex enumerator = this.multiply(a.conjugate());
			Complex denominator = a.multiply(a.conjugate());
			return enumerator.multiply(1/denominator.x);
		}	
		
		static Complex e(double theta){
			return new Complex(Math.cos(theta), Math.sin(theta));
		}
		
		public String toString(){
			if(y >= 0)
				return String.format("%.1f + %.1fi", x, y);
			else
				return String.format("%.1f - %.1fi", x, -y);
		}
	}
	
	static Complex[] fft(Complex[] a){
		int n = a.length;
		if(n == 1) return a;
		
		Complex[] aS = new Complex[n/2];
		Complex[] aL = new Complex[n/2];
		for(int i = 0; i < n; i++){
			if(i % 2 == 0)
				aS[i/2] = a[i];
			else
				aL[i/2] = a[i];
		}
		
		aS = fft(aS);
		aL = fft(aL);
		
		Complex w = Complex.e(2*Math.PI / n);
		Complex wk = new Complex(1,0);
		Complex[] y = new Complex[n];
		for(int i = 0; i < n/2; i++){
			y[i]= aS[i].add(wk.multiply(aL[i]));
			y[i+n/2]= aS[i].sub(wk.multiply(aL[i]));
			wk = wk.multiply(w);
		}
		for(Complex c : y){
			System.out.print(c+ " ");
		}	
		System.out.println();		
		
		return y;
	}
	
	public static void main(String... args){
		
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(args[0]);
		int t = 1;
		while(t < n){
			t *= 2;
		}
		Complex[] a = new Complex[t];
		for(int i = 0; i < t; i++){
			if(i < n){
				double d = scanner.nextDouble();
				a[i] = new Complex(d, 0);
			} else{
				a[i] = new Complex(0, 0);
			}
		}
		fft(a);
		
	
		
		
	}
	
}