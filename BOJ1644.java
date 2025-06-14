//숫자 입력받고
//자기보다 작은 숫자 모으고 (에라토스체
//연속된 => 투포인터!!!!!!!
import java.io.*;
import java.util.*;

public class boj1644 {
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        //StringTokenizer st;
        //StringBuilder sb=new StringBuilder();
		
		
		int n = Integer.parseInt(br.readLine());
	    int[] primeArr = smallPrime(n);
	    
	    
	    int s=0, e=0, sum=0, cnt=0;

        while (true) {
            if (sum>=n) {
                sum-=primeArr[s++];
            } else if (e==primeArr.length) {
                break;
            } else {
                sum += primeArr[e++];
            }

            if (sum==n) cnt++;
        }

        bw.write(cnt+"\n");
	    bw.flush();
	    bw.close();
	    
	}
	
	static int[] smallPrime(int n) { //에라토스테네스의 체
	    if(n==1) return new int[0];
	    boolean[] prime = new boolean[n+1];
	    
	    int size = n-1;
	    Arrays.fill(prime, true);
	    
	    prime[0] = prime[1] = false;
	
	    for(int i=2; i*i<=n; i++) {
	        if(!prime[i]) continue;
	        for(int j=i*i; j<=n; j+=i) {
	            if(prime[j]) {
	                prime[j] = false;
	                size--;
	            }
	        }
	    }
	
	    int[] result=new int[size];
	    int idx=0;
	    for(int i=2; i<=n; i++) {
	        if(prime[i]) result[idx++] = i;
	    }
	    return result;
	}
}
