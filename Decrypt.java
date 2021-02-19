import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Decrypt{
	static public void main(String[] argv){
		BigInteger d = new BigInteger(argv[0]);
		BigInteger N = new BigInteger(argv[1]);
		BigInteger C = new BigInteger(argv[2]);

		ModPowSlidingWindow MPSW = new ModPowSlidingWindow();
		int w  = Integer.valueOf(4);
		BigInteger.valueOf(4);
		BigInteger M = MPSW.modPow(C, d, N, w);
		System.out.println("\ndecryption M: " + M);
	}
}
