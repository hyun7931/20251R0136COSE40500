//도가뉴 항등식을 이용
package samsungSDS;

import java.util.*;
import java.io.*;

public class boj11444 {
    static final int MOD = 1000000007;
    static Map<Long, Long> dp = new HashMap<>();

    public static void main(String[] args) throws IOException {
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        //StringTokenizer st;
        //StringBuilder sb=new StringBuilder();
        
        long n = Long.parseLong(br.readLine());

        if (n == 0) {
            bw.write("0\n");
            bw.flush();
            return;
        }
        if (n == 1) {
            bw.write("1\n");
            bw.flush();
            return;
        }
        
        dp.put(0L, 0L);
        dp.put(1L, 1L);
        dp.put(2L, 1L);
        
        long ans=fibo_docanu(n);
      
        bw.write(ans+"\n");
        bw.flush();
    }
    
    public static long fibo_docanu(long n) {
        if (dp.containsKey(n)) return dp.get(n);

        long result;
        if ((n & 1) == 1) { //홀수
            long fn = fibo_docanu(n / 2);
            long fn1 = fibo_docanu(n / 2 + 1);
            result = (fn * fn + fn1 * fn1) % MOD;
        } else { //짝수
            long fn = fibo_docanu(n / 2);
            long fn2 = fibo_docanu(n / 2 - 1);
            result = (fn * (2 * fn2 + fn)) % MOD;
        }

        dp.put(n, result);
        return result;
    }
}