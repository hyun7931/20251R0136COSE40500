import java.io.*;
import java.util.*;
//hash 사용할때 뭐가 key에 들어가야하는지 계속 틀리는데
//key가 중복되면 안되는 것!!! (다 다른것)
//오 개멍청,,,,,numa는 전체 횟수를 카운트할 뿐임!!!
//hashA.getOrDefault(tmp, 0)+1 이런식으로 부분합의 빈도수를 저장해주어야한다!!!
/*
 * 정수들의 값은 10억이라서 둘을 합쳐도 int형 범위 안이라 괜찮은데

cnt같은 경우는 초과될 수 있으니 long으로 설정하셔야 합니다.

이 부분 고치니깐 잘 돌아가네요.

아.. map을 이용해서 구현했는데 마지막 count (정답 변수) 도 long으로 했는데도 틀린분들은
map 선언 시, 값의 자료형 또한 long 으로 했는지 검토..

hashmap을 <long, long>으로 변경하지 않음 추가,,,
 */
public class  Main {
	
	static long[] arrA, arrB;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t=Integer.parseInt(br.readLine());
		
		int n=Integer.parseInt(br.readLine());
		arrA=new long[n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n; i++) {
			arrA[i]=Integer.parseInt(st.nextToken());
		}
		
		int m=Integer.parseInt(br.readLine());
		arrB=new long[m];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<m; i++) {
			arrB[i]=Integer.parseInt(st.nextToken());
		}
		
		//A,B부배열 전부 구하기
		HashMap<Long, Long> hashA = new HashMap<>();
		for(int i=0; i<n; i++) {
			long tmp=0;
			for(int j=i; j<n; j++) {
				tmp+=arrA[j];
				hashA.put(tmp, hashA.getOrDefault(tmp, 0L)+1);
			}
		}
		
		HashMap<Long, Long> hashB = new HashMap<>();
		for(int i=0; i<m; i++) {
			long tmp=0;
			for(int j=i; j<m; j++) {
				tmp+=arrB[j];
				hashB.put(tmp, hashB.getOrDefault(tmp, 0L)+1);
			}
		}
		
		long ans=0;
		for(long val:hashA.keySet()) {
			if(hashB.containsKey(t-val)) {
				ans+=hashA.get(val)*hashB.get(t-val);
			}
		}
		System.out.println(ans);
	}
}