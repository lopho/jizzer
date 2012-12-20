package org.lopho.jizzer;

import java.security.InvalidParameterException;

import org.jivesoftware.smack.XMPPException;

/**
 * Jizzer
 * @author lopho
 * @author b1gmct5
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			(new Jizzer(new JizzConfig(args))).run();
		} catch (XMPPException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (InvalidParameterException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

}
