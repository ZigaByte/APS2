import java.util.Scanner;

public class Izziv10{
	
	public static void print(int h, int[] razdalje){
		System.out.print("h" + h + ": ");
		for(int i = 0; i < razdalje.length; i++){
			if(razdalje[i] >= 10000){
				System.out.print("Inf ");
			}else{
				System.out.print(razdalje[i] + " ");
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		int n = Integer.parseInt(args[0]);
		Scanner scanner = new Scanner(System.in);
		
		int[][] utezi = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				utezi[i][j] = 100000;
			}			
		}
		
		while(scanner.hasNext()){
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int w = scanner.nextInt();
			utezi[a][b] = w;
		}
		
		int[] razdalje = new int[n];
		for(int i = 1; i < n; i++){
			razdalje[i] = 100000;
		}
		
		for(int h = 0; h < n; h++){
			if(h == 0){
				razdalje[0] = 0;
				print(h, razdalje);
			}else{
				int[] noveRazdalje = new int[n];
				for(int i = 1; i < n; i++){
					noveRazdalje[i] = 100000;
				}
				for	(int v = 0; v < n; v++){
					for	(int e = 0; e < n; e++){
						noveRazdalje[v] = Math.min(noveRazdalje[v],Math.min(razdalje[v], razdalje[e] + utezi[e][v]));
					}
				}
				print(h, noveRazdalje);
				razdalje = noveRazdalje;
			}
		}
		
		
	}
	
}