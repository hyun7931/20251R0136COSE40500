//다익스트라 보통 제일 짧은걸 구하지만 여기선 제일 긴 시간!
//dist[next.dest] == next.cost + current.cost 일때도 adjList2.add를 해줘야 한다는 것을 까먹음!!!

// 놓침0 : 역추적 해야함,,,,
// 놓침1 : dist[next.dest] == next.cost + current.cost 일때도 adjList2.add를 해줘야 한다는 것을 까먹음!!!
// 놓침2 : bfs에서 중복 간선이 있더라도 딱 한번만 세야하기 때문에,,,,간선을 따로 체크해주어야한다…
//        -> hashSet
// 놓침3 : 다익스트라에서 visited=true는 continue로 해놔서,,,,더 긴 거리를 놓칠 수 잇음,,,보통 다익스트라에서는 ㄱㅊ지만 지금은 최장거리를 구하기 때문에 문제가 됨!!!
//        -> visited 제거

import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node>{
        int dest, cost;

        public Node(int dest, int cost){
            this.dest=dest;
            this.cost=cost;
        }
        
        public int compareTo(Node o) {
        	//보통은 return this.cost-o.cost;
        	return o.cost-this.cost; //최장경로를 구하는 것이므로 거꾸로 하는 것이 더 좋음
        }
        
	}
	
	static int N,M;
	static ArrayList<Node>[] adjList;
	static List<Integer>[] adjList2;
    static int[] dist, cnt;
    static boolean[] visited, visited2;

	public static void main(String[] args) throws IOException {
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[N+1];
        for(int n=1; n<=N; n++){
            adjList[n] = new ArrayList<>();
        }
        adjList2 = new ArrayList[N+1];
        for(int n=1; n<=N; n++){
            adjList2[n] = new ArrayList<>();
        }

        dist = new int[N+1];
        cnt = new int[N+1];
        //visited = new boolean[N+1];
        visited2 = new boolean[N+1];
        
        int from, to, cost;
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, cost));
        }
        
        st=new StringTokenizer(br.readLine());
		int start=Integer.parseInt(st.nextToken());
		int end=Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		
		int result=dist[end];
		int num=bfs(end);
		bw.write(result+"\n");
		bw.write(num+"\n");
		bw.flush();
		bw.close();
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
        
        Arrays.fill(dist, 0);
        Arrays.fill(cnt, 0);
        
        pq.offer(new Node(start, 0));
        
        while(!pq.isEmpty()) {
        	Node current=pq.poll();
        	
        	//visited는 필요 없음!!!
        	//기존 최단경로 다익스트라에서는 visited를 사용하지만
        	//지금은 최장경로를 구함
        	//최장경로를 구하는데 visited 쓰면 처음 도달한 시간만 체크하고
        	//더 오래 걸려서 도착한 시간을 무시하게 됨
        	
        	//if(visited[current.dest]) {
        	//	continue;
        	//}
        	//visited[current.dest]=true;
        	
        	for(Node next:adjList[current.dest]) {
        		if(dist[next.dest] < next.cost+current.cost){
                    dist[next.dest] = next.cost+current.cost;
                    pq.offer(new Node(next.dest, dist[next.dest]));
                    adjList2[next.dest].clear();
                    adjList2[next.dest].add(current.dest);
                }
        		if(dist[next.dest] == next.cost+current.cost){
        			adjList2[next.dest].add(current.dest);
        		}
        	}
        }
	}
	
	static int bfs(int start) {
		//중복 간선이 있더라도 딱 한번만 세야하기 때문에,,,,간선을 따로 체크해주어야한다...
		//1. hashSet<Pair>로 (from, to)
		//2. hashSet에 from->to 문자열로 저장 (이게 더 간단해서 이거루,,,)
		
		Set<String> visitedEdges = new HashSet<>();
		
		int cnt=0;
		ArrayDeque<Integer> q = new ArrayDeque<>();
		visited2[start]=true;
		q.add(start);
		
		while(!q.isEmpty()) {
			int current=q.poll();
			for(int next:adjList2[current]) {
				String edgeKey = current + "->" + next;
				if (!visitedEdges.contains(edgeKey)) {
				    visitedEdges.add(edgeKey);
				    cnt++;
				}
				if(!visited2[next]) {
					visited2[next]=true;
					q.add(next);
				}
			}
		}
		return cnt;
	}
}
