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

import java.security.InvalidParameterException;

/**
 * @author lopho
 * @author b1gmct5
 */
public class JizzConfig implements Cloneable {
	private String user;
	private String server;
	private String password;
	private String muc;
	private String folder;
	private String url;
	private String nick;
	
	/**
	 * @param args
	 * @throws InvalidParameterException
	 */
	public JizzConfig(String[] args) throws InvalidParameterException {
		user = null;
		server = null;
		password = null;
		muc = null;
		folder = null;
		url = null;
		nick = null;
		
		for (int i = 0; i < args.length; i++) {
			if (args.length > i + 1 && !args[i + 1].startsWith("-")) {
				if (args[i].equals("--user")) {
					user = args[i + 1];
				} else if (args[i].equals("--server")) {
					server = args[i + 1];
				} else if (args[i].equals("--password")) {
					password = args[i + 1];
				} else if (args[i].equals("--muc")) {
					muc = args[i + 1];
				} else if (args[i].equals("--folder")) {
					folder = args[i + 1];
				} else if (args[i].equals("--url")) {
					url = args[i + 1];
				} else if (args[i].equals("--nick")) {
					nick = args[i + 1];
				}
			}
		}
		
		if (user == null || server == null || password == null
				|| muc == null || url == null ) {
			throw new InvalidParameterException("Missing Arguments.");
		}
		if (nick == null) {
			nick = "jizzer";
		}
		if (folder == null) {
			folder = ".";
		}
	}
	
	/**
	 * @param jizzConfig
	 */
	private JizzConfig(JizzConfig jizzConfig) {
		user = jizzConfig.user;
		server = jizzConfig.server;
		password = jizzConfig.password;
		muc = jizzConfig.muc;
		folder = jizzConfig.folder;
		url = jizzConfig.url;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public JizzConfig clone() {
		return new JizzConfig(this);
	}
	
	/**
	 * @return
	 */
	public String getUser() { return user; }
	/**
	 * @return
	 */
	public String getServer() { return server; }
	/**
	 * @return
	 */
	public String getPassword() { return password; }
	/**
	 * @return
	 */
	public String getMUC() { return muc; }
	/**
	 * @return
	 */
	public String getFolder() { return folder; }
	/**
	 * @return
	 */
	public String getUrl() { return url; }
	/**
	 * @return
	 */
	public String getNick() { return nick; }
		
}
