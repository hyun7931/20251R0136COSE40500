import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Integer>[] adjList;
    static int[][] parent;
	static int[] depth;
	static boolean[] visited;
	static int LOG=17;
	static int n;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		n=Integer.parseInt(br.readLine());
		
		adjList=new ArrayList[n+1];
		
		for(int i=1; i<=n; i++) {
			adjList[i]=new ArrayList<>();
		}
		visited=new boolean[n+1];
		
		depth=new int[n+1];
		parent=new int[LOG+1][n+1];
		
		for(int i=0; i<n-1; i++) {
			st=new StringTokenizer(br.readLine());
			int n1=Integer.parseInt(st.nextToken());
			int n2=Integer.parseInt(st.nextToken());
			
			adjList[n1].add(n2);
			adjList[n2].add(n1);
		}
		
		bfs(1);
		
		findAncestor();
		
		int m=Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			int ans=0;
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			ans=LCA(a,b);
			sb.append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void bfs(int root) {
		ArrayDeque<Integer> q=new ArrayDeque<>();
		q.add(root);
		visited[root] = true;
        depth[root] = 0;
		
		while(!q.isEmpty()) {
			int current=q.poll();
			
			for(int next : adjList[current]) {
				if(!visited[next]) {
					visited[next]=true;
					depth[next]=depth[current]+1;
					parent[0][next] = current;
					q.add(next);
				}
			}
		}
	}
	
	public static void findAncestor() {
		for(int i=1; i<=LOG; i++) {
			for(int j=1; j<=n; j++) {
				parent[i][j]=parent[i-1][parent[i-1][j]];
			}
		}
	}
	
	public static int LCA(int a, int b) {
		if(depth[a]>depth[b]) {
			int tmp = a;
		    a = b;
		    b = tmp;
		}
		
		for(int i=LOG; i>=0; i--) {
			if(depth[b]-depth[a] >= (1<<i)) {
				b=parent[i][b];
			}
		}
		
		if(a==b) return a;
		
		for(int i=LOG; i>=0; i--) {
			if(parent[i][a]!=parent[i][b]) {
				a=parent[i][a];
				b=parent[i][b];
			}
		}
		return parent[0][a];
	}
}