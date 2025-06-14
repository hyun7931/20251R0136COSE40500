package samsungSDS;

import java.io.*;
import java.util.*;

public class boj12015 {
	
	static int[] input, lis;
	
	public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //StringBuilder sb=new StringBuilder();
        
        int N=Integer.parseInt(br.readLine());
        
        input=new int[N+1];
        lis=new int[N+1];
        
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	input[i]=Integer.parseInt(st.nextToken());
        }
        
        int lastidx=0;
        lis[0]=input[0];
        
        for(int i=1; i<input.length; i++) {
        	if(input[i]>lis[lastidx]) {
        		lis[++lastidx]=input[i];
        	}else {
        		int lb=lowerbound(lastidx, input[i]);
        		lis[lb]=input[i];
        	}
        }
        
        System.out.println(lastidx+1);
	}
	
	static int lowerbound(int idx, int target) {
		int mid;
		int left=0;
		int right=idx;
		
		while(left<right) {
			mid=(left+right)/2;
			if(lis[mid]>=target) {
				right=mid;
			}else {
				left=mid+1;
			}
		}
		return right;
	}
}
