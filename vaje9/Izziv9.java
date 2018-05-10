import java.util.Scanner;

class Izziv9{
	
	static int V;
	static boolean[][] memo;
	static boolean[][] toAdd; 
	
	public static void dodaj(int v, int c){
		if(v > V){
			//System.out.println("Odstranjujem " + v + " " + c);
			return;
		}
		
		if(memo[v][c]){
			System.out.println("Odstranimo (" + v + ", " + c + ")");
			return;
		}
		
		// Enaka cena, vecja prostornina
		for(int vv = 0; vv < memo.length; vv++){
			if(memo[vv][c]){
				if(vv < v){
					System.out.println("Odstranimo (" + v + ", " + c + ")");
				}else{
					memo[v][c] = true;
					memo[vv][c] = false;
					toAdd[vv][c] = false;
					System.out.println("Odstranimo (" + vv + ", " + c + ")");
				}
				
				return;
			}
		}
		// Enaka prostornina, manjsa cena
		for(int cc = 0; cc < memo[v].length; cc++){
			if(memo[v][cc]){
				if(cc < c){
					memo[v][c] = true;
					memo[v][cc] = false;
					toAdd[v][cc] = false;
					System.out.println("Odstranimo (" + v + ", " + cc + ")");
				}else{
					System.out.println("Odstranimo (" + v + ", " + c + ")");
				}
				return;
			}
		}
		
		toAdd[v][c] = true;
		//System.out.println("Dodam " + v + " " + c);
		
	}
	
	public static void rek(int[] vt, int[] ct, int i){
		if(i >= vt.length)return;
		
		int v = vt[i];
		int c = ct[i];

		for(int j = 0; j < memo.length; j++){
			for(int k = 0; k < memo[j].length; k++){
				if(!memo[j][k])continue;
				
				dodaj(j+v, k+c);
			}
		}
		System.out.print((i+1)+": ");
		for(int j = 0; j < memo.length; j++){
			for(int k = 0; k < memo[j].length; k++){
				memo[j][k] |= toAdd[j][k];
				if(memo[j][k])
					System.out.print("("+ j +", "+ k +")");
			}
		}
		System.out.println();
		
		
		rek(vt, ct, i+1);
	}
	
	public static void main(String... args){
		int n = Integer.parseInt(args[0]);
		
		Scanner scanner = new Scanner(System.in);
		int v[] = new int[n];
		int c[] = new int[n];
		
		for(int i = 0; i < n; i++){
			v[i] = scanner.nextInt();
		}
		int csum = 0;
		for(int i = 0; i < n; i++){
			c[i] = scanner.nextInt();
			csum += c[i];
		}		
		
		V = scanner.nextInt();
		memo = new boolean[V+1][csum+1];
		toAdd = new boolean[V+1][csum+1];
		memo[0][0] = true;
		
		System.out.println("0: (0, 0)");
		rek(v, c, 0);
	}
	
}