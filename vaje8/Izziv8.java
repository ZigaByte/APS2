
public class Izziv8{
	
	static int[][] p;
	
	public static int pascal(int n, int k){
		if(n < 0 || k < 0 || n >= p.length || k >= p[n].length) return 0;
		if(p[n][k] != -1) return p[n][k];
		if(k == 0) {
			p[n][k] = 1;
			return 1;
		}
		if(n == k) {
			p[n][k] = 1;
			return 1;
		}
		else {
			p[n][k] = p[n-1][k-1] + p[n-1][k];
			return p[n-1][k-1] + p[n-1][k];
		}
	}
	
	public static void pascal2(int n, int k, int t[]){
		if(t.length >= n+1)return;
		
		int[] nt = new int[t.length + 1];
		nt[0] = 1;
		nt[nt.length - 1] = 1;
		for(int x = 1; x < nt.length - 1; x++){
			nt[x] = t[x-1] + t[x];
		}
		for(int x = 0; x < nt.length; x++){
			System.out.print(nt[x] + " ");
		}
		System.out.println();
		
		pascal2(n, k, nt);
		
	}
	
	static int[][] j;

	public static int jajca(int n, int k){
		if(n < 0 || k < 0) return 0;
		if(k == 0){
			j[n][k] = 0;
		}
		if(j[n][k] != -1) return j[n][k];
		if(k == 1 || n <= 1){ 
			j[n][k] = n;
			return n;
		}
		
		int min = Integer.MAX_VALUE;
		for(int x = 1; x <= n; x++){
			int max = Math.max(j[x-1][k-1], j[n-x][k]);
			if(max < min)
				min = max;
		}
		j[n][k] = min + 1;
		return j[n][k];
		
	}
	
	public static void main(String... args){
		int n = Integer.parseInt(args[0]);
		int k = Integer.parseInt(args[1]);
		p = new int[n+1][k+1];
		j = new int[n+1][k+1];
		for(int i = 0; i <= n; i++){
			for(int l = 0; l <= k; l++){
				p[i][l] = -1;
				j[i][l] = -1;
			}
		}
		
		for(int i = 0; i <= n; i++){
			for(int j = 0; j <= i; j++){
				System.out.print(pascal(i, j) + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		
		pascal2(n, k, new int[]{1});
		
		System.out.println();
		System.out.println();
		
		for(int i = 0; i <= n; i++){
			for(int j = 1; j <= k; j++){
				System.out.print(jajca(i, j) + " ");
			}
			System.out.println();
		}
		
		
	}
	
}