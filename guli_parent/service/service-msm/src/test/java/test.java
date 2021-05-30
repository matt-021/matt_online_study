import java.util.Random;

import org.junit.Test;

import com.matt.commonutils.RandomUtil;

public class test {
	@Test
	public void test() {
		Random random = new Random();
		
	System.out.println(random.nextInt(10000));
	String code = RandomUtil.getFourBitRandom();
	System.out.println("-----:"+code);
	}
}
