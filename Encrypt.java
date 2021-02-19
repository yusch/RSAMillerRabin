import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Encrypt{
	static public void main(String[] argv){
		BigInteger e = new BigInteger(argv[0]);
		BigInteger N = new BigInteger(argv[1]);
		BigInteger M = new BigInteger(argv[2]);

		int w = Integer.valueOf(4);
		
		//スライディングウィンドウ法
		ModPowSlidingWindow MPSW = new ModPowSlidingWindow();
		BigInteger C = MPSW.modPow(M, e, N, w);
		
		System.out.println("\nciphertext C: " + C + "\n");
	}
}
