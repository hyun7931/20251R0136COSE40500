import java.io.*;
import java.util.*;

public class Main{
	
	class Edge {
	    int from, to, cost;
	    
	    Edge(int from, int to, int cost) {
	        this.from = from;
	        this.to = to;
	        this.cost = cost;
	    }
	}
	
	static int N,M,K;
	static int[][] dist;
	static ArrayList<Edge> adjList;
	static int MAXX=1000000000;
	
	public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        
        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        
        dist=new int[N+1][N+1];
        
        for(int i=0; i<=N; i++) {
        	Arrays.fill(dist[i], MAXX);
        	dist[i][i]=0;
        }
        
        for(int i=0; i<M; i++) {
        	st=new StringTokenizer(br.readLine());
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int cost=Integer.parseInt(st.nextToken());
            dist[from][to]=cost;
        }
        
        for (int k=1; k<=N; k++) {
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    if (dist[i][k]<MAXX && dist[k][j]<MAXX) {
                        dist[i][j]=Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        
        K=Integer.parseInt(br.readLine());
        int[] friends=new int[K];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
        	friends[i]=Integer.parseInt(st.nextToken());
        }
        
        int minDist=MAXX;
        List<Integer> meet=new ArrayList<>();
        
        for(int i=1; i<=N; i++) {
        	int maxDist=0;
        	for(int friend : friends) {
        		maxDist=Math.max(maxDist, dist[friend][i]+dist[i][friend]);
        	}
        	if(maxDist<minDist) {
        		minDist=maxDist;
        		meet.clear();
        		meet.add(i);
        	}else if(maxDist==minDist) {
        		meet.add(i);
        	}
        }
        
        Collections.sort(meet);
        for(int x : meet) {
        	sb.append(x).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
	}
}