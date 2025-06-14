import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost){
            this.from=from;
            this.to=to;
            this.cost=cost;
        }

        public int compareTo(Edge o) {
            return this.cost-o.cost;
        }
    }
	
	static int[] parent;
	static List<Edge> edges;
	
	public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        
        while(true) {
        	st=new StringTokenizer(br.readLine());
        	int n=Integer.parseInt(st.nextToken());
        	int m=Integer.parseInt(st.nextToken());
        	
        	if(n==0&&m==0) break;
        	
        	edges=new ArrayList<>();
        	int sumcost=0;
        	for(int i=0; i<m; i++) {
        		st=new StringTokenizer(br.readLine());
            	int x=Integer.parseInt(st.nextToken());
            	int y=Integer.parseInt(st.nextToken());
            	int c=Integer.parseInt(st.nextToken());
            	edges.add(new Edge(x,y,c));
            	sumcost+=c;
        	}
        	
        	int ans=sumcost-kruscal(m);
        	bw.write(ans+"\n");
        }
        bw.flush();
        bw.close();
	}
	static int kruscal(int v) {
		Collections.sort(edges);
		parent=new int[v];
		
		for(int i=0; i<v; i++) {
			parent[i]=i;
		}
		int mstCost=0;
		int edgecnt=0;
		
		for(Edge edge:edges) {
			if(find(edge.from)!=find(edge.to)) {
				union(edge.from, edge.to);
				mstCost+=edge.cost;
				edgecnt++;
				if(edgecnt==v-1) break;
			}
		}
		return mstCost;
	}
	static int find(int a) {
		if(parent[a]==a) return a;
		else return parent[a]=find(parent[a]);
	}
	static void union(int a, int b) {
		int aRoot=find(a);
		int bRoot=find(b);
		
		if(aRoot!=bRoot) {
			parent[bRoot]=aRoot;
		}
	}
}
