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

import java.util.ArrayList;
import java.util.HashMap;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;
import org.lopho.jizzer.util.JizzLogger;

/**
 * @author lopho
 * @author b1gmct5
 */
public class JizzMessageListener implements MessageListener {
	private ArrayList<JizzCommand> commandQueue;
	private JizzLogger log;
	static private final HashMap<String, String> COMMANDS =
			new HashMap<String, String>();
	
	static {
		COMMANDS.put("stop", "");
		COMMANDS.put("ping", "");
		COMMANDS.put("pause", "");
		COMMANDS.put("resume", "");
	}
	
	/**
	 * @param log
	 */
	public JizzMessageListener(JizzLogger log) {
		this.log = log;
		commandQueue = new ArrayList<JizzCommand>();
	}
	
	/* (non-Javadoc)
	 * @see org.jivesoftware.smack.MessageListener#processMessage(org.jivesoftware.smack.Chat, org.jivesoftware.smack.packet.Message)
	 */
	@Override
	public void processMessage(Chat chat, Message message) {
		String body = message.getBody().toLowerCase();
		String peer = chat.getParticipant();
		if (body != null) {
			log.add("[recieved]["+ peer + "] " + body);
		}
		if (body.startsWith("**jizzer*")) {
			String command = body.substring(9);
			if (COMMANDS.containsKey(command)) {
				commandQueue.add(
						new JizzCommand(command, peer, chat));
			}
		}
	}
	
	/**
	 * @return
	 */
	public boolean hasNext() {
		if (commandQueue.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * @return
	 */
	public JizzCommand next() {
		JizzCommand command = commandQueue.get(0);
		commandQueue.remove(0);
		return command;
	}
}
