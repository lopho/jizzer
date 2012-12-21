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
package org.lopho.jizzer.util;

import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author lopho
 * @author b1gmct5
 */
public class JizzLogger {
	private final PrintStream o;
	
	/**
	 * 
	 */
	public JizzLogger() {
		o = System.out;
	}
	
	/**
	 * @param ostream
	 */
	public JizzLogger(PrintStream ostream) {
		o = ostream;
	}
	
	/**
	 * @param message
	 */
	public void add(String message) {
		o.println(getTimestamp() + " " + message);
	}
	
	/**
	 * @return
	 */
	public String getTimestamp() {
		return "["+(new Timestamp((new Date()).getTime())).toString().substring(0, 19)+"]";
	}
}
