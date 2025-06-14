import java.io.*;
import java.util.*;

public class boj1766 {
	
	static ArrayList<Integer>[] adjList;
	static int[] indegree;
	static int N,M;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        
        st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		adjList=new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adjList[i]=new ArrayList<>();
		}
		indegree=new int[N+1];
		
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			indegree[to]++;
		}
		
		toposort();
	}
	
	static void toposort() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        // 시작점:차수가 0인 노드 pq에 넣기 -> pq에 넣어서 자동으로 from 기준 오름차순
        for(int i=1; i<indegree.length; i++){
            if(indegree[i] == 0){
                pq.offer(i);
            }
        }

        while(!pq.isEmpty()){
            int curr = pq.poll();
            sb.append(curr + " ");

            //treeset으로 뒤에 와야할 노드들 넣기. 자동 오름차순에 중복 제거
            TreeSet<Integer> nextnodes=new TreeSet<>();
            for (Integer to : adjList[curr]) {
                // node -> to 차수 줄이기
                indegree[to]--;

                if(indegree[to] == 0){
                    nextnodes.add(to);
                }
            }
            
            while(!nextnodes.isEmpty()) {
            	//pollFirst는 가장 작은 값을 꺼내줌!
            	pq.offer(nextnodes.pollFirst());
            }
        }

        System.out.println(sb.toString());
	}
}
