import java.io.*;
import java.util.*;
//arr의 시작 인덱스가 1부터!!!
public class boj14428 {
	static long [] arr, iTree;
	static int leafnum;
	
	public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        
        int N=Integer.parseInt(br.readLine());
        arr=new long[N];
        
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	arr[i]=Integer.parseInt(st.nextToken());
        }
        
        int size=1;
        while(size<N) {
        	size<<=1;
        }
        leafnum=size;
        iTree=new long[leafnum*2];
        for(int i=0; i<N; i++) {
        	int idx=i+leafnum;
        	iTree[idx]=arr[i];
        }
        for(int i=leafnum-1; i>0; i--) {
        	iTree[i]=iTree[i*2]+iTree[i*2+1];
        }
        
        int M=Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
        	st=new StringTokenizer(br.readLine());
        	int a=Integer.parseInt(st.nextToken());
        	int b=Integer.parseInt(st.nextToken());
        	int c=Integer.parseInt(st.nextToken());
        	
        	if(a==1) { //update
        		update(b-1,c);
        	}
        	if(a==2) { //min index
        		int ans=query(b-1,c-1)-leafnum+1;
        		sb.append(ans).append("\n");
        	}
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
	}
	
	static void update(int i, int val) {
		int idx=i+leafnum;
		iTree[idx]=val;
		
		while(idx>1) {
			idx/=2;
			iTree[idx]=iTree[idx*2]+iTree[idx*2+1];
		}
	}
	
	static int query(int i, int j) {
		int start=i+leafnum;
		int end=j+leafnum;
		
		long min=iTree[start];
		int minidx=start;
		
		for(int p=start; p<=end; p++) {
			if(iTree[p]<min) {
				min=iTree[p];
				minidx=p;
			}
		}
		return minidx;
	}
}
