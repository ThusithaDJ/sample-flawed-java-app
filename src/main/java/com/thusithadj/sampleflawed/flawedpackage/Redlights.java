package com.thusithadj.sampleflawed.flawedpackage;

import java.security.SecureRandom;

public class Redlights {

	public static void sufficientEntropy_REDLIGHT() {
		SecureRandom sr = new SecureRandom();
		System.out.println(sr.nextInt());
	}
}
