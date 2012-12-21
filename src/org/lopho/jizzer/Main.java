/**
 * jizzer - a simple jabber bot for managing file transfers in multi user chats
 * Copyright (C) 2012  lopho, b1gmct5
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.lopho.jizzer;

import java.security.InvalidParameterException;

import org.jivesoftware.smack.XMPPException;
import org.lopho.jizzer.util.JizzConfig;

/**
 * jizzer - a simple jabber bot for managing file transfers in multi user chats
 * @author lopho
 * @author b1gmct5
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JizzConfig jizzConfig = null;
		try {
			jizzConfig = new JizzConfig(args);
		} catch (InvalidParameterException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		if (jizzConfig != null) {
			Jizzer jizzer = new Jizzer(jizzConfig);
			
			try {
				jizzer.run();
			} catch (XMPPException e) {
				jizzer.stop(true);
				e.printStackTrace();
				System.exit(1);
			} catch (InterruptedException e) {
				jizzer.stop(true);
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

}
