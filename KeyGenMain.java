import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class KeyGenMain{
	static public void main(String[] argv){
		int n = Integer.valueOf(argv[0]);
		
		n = n/2;
		if(n <= 0) return;

		BigInteger one = BigInteger.ONE;
		BigInteger zero = BigInteger.ZERO;
		
		SecureRandom rand = new SecureRandom();
		MillerRabinTest MRT = new MillerRabinTest();
		GCDBinaryEuclid GCDBE = new GCDBinaryEuclid();

		//素数pの生成
		BigInteger p = one;
		Boolean bool = false;
		do{
			BigInteger v1 = new BigInteger(n, rand);
			if(v1.signum() == 1) p = v1;
			int t = Integer.valueOf("40");
			
			if(p.compareTo(BigInteger.valueOf(3)) < 0 || !p.testBit(0)) continue;
			
			bool = MRT.isProbablePrime(p, t);
		}while(bool == false);
		//System.out.println("Prime p:\n"+ p +"\n");//素数pの表示
		
		//素数qの生成
		BigInteger q = one;
		bool = false;
		do{
			BigInteger v2 = new BigInteger(n, rand);
			if(v2.signum() == 1) q = v2;
			int t2 = Integer.valueOf("40");

			if(q.compareTo(BigInteger.valueOf(3)) < 0 || !q.testBit(0)) continue;

			bool = MRT.isProbablePrime(q, t2);
		}while(bool == false);
		//System.out.println("Prime q:\n"+ q + "\n");//素数qの表示
		
		BigInteger N = p.multiply(q);

		System.out.println("N:\n" + N);

		BigInteger a = p.subtract(one); //マイナス１
		BigInteger b = q.subtract(one); //マイナス１
		BigInteger pq = a.multiply(b);

		BigInteger g = GCDBE.gcd(a, b);

		BigInteger L = pq.divide(g);

		BigInteger gcd = one.add(one);
		BigInteger e = one;
		do{
			BigInteger v3 = new BigInteger(2*n, rand);
			if(v3.signum() == 1 && v3.compareTo(pq) == -1) e = v3;
			if(e.compareTo(BigInteger.valueOf(3)) < 0 || !e.testBit(0)) continue;
			gcd = GCDBE.gcd(e, L);
		}while(gcd.compareTo(one) == 1);
		System.out.println("Public Key:\n" + e + "\n");

		ModInverse MI = new ModInverse();
		BigInteger d = MI.modInverse(e, L);
		System.out.println("Private Key:\n" + d + "\n");
	}
}
