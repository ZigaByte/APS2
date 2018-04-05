import java.util.Scanner;

class Izziv5{
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
		
		public String toString(){
			if(y >= 0)
				return String.format("%.5f + %.5fi", x, y);
			else
				return String.format("%.5f - %.5fi", x, -y);
		}
	}
	
	public static void main(String[] args){
		Complex c1 = new Complex(0,1);
		Complex c = new Complex(1, 0);
		for(int i = 0; i < 10; i++){
			c = c.multiply(c1);
			System.out.println(c);
		}
	}
}