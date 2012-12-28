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
package org.lopho.jizzer.message;

import org.jivesoftware.smack.Chat;

/**
 * @author lopho
 * @author b1gmct5
 */
public class JizzCommand {
	private final String command;
	private final String[] options;
	private final String peer;
	private final Chat chat;
	
	/**
	 * @param command
	 * @param peer
	 * @param chat
	 */
	public JizzCommand(String command, String[] options, String peer, Chat chat) {
		this.command = command;
		this.options = options;
		this.peer = peer;
		this.chat = chat;
	}
	
	public String[] getOptions() {
		return options;
	}

	/**
	 * @return
	 */
	public String getCommand() {
		return command;
	}
	
	/**
	 * @return
	 */
	public String getPeer() {
		return peer;
	}
	
	/**
	 * @return
	 */
	public Chat getChat() {
		return chat;
	}
}
