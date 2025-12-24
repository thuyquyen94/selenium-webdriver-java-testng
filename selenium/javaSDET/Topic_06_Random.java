package javaSDET;

import org.testng.annotations.Test;
import zmq.socket.pubsub.Pub;

import java.util.Random;

public class Topic_06_Random {
	// Global
	String prefixEmail = "Automation";

	String postFixEmail = "@gmail.com";

	@Test
	public void testEmail(){
		Random rand = new Random();
		// khai báo trong hàm gọi là biến Local
		// String fullEmail = prefixEmail + rand.nextInt(99999) + postFixEmail;

		System.out.printf(prefixEmail + rand.nextInt(99999) + postFixEmail);
		System.out.printf(prefixEmail + rand.nextInt(99999) + postFixEmail);
		System.out.printf(prefixEmail + rand.nextInt(99999) + postFixEmail);
		System.out.printf(prefixEmail + rand.nextInt(99999) + postFixEmail);
		System.out.printf(prefixEmail + rand.nextInt(99999) + postFixEmail);

	}

}
