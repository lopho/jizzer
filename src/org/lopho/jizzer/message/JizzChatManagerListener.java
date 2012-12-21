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
import org.jivesoftware.smack.ChatManagerListener;
import org.lopho.jizzer.util.JizzLogger;

/**
 * @author lopho
 * @author b1gmct5
 */
public class JizzChatManagerListener implements ChatManagerListener {
	private JizzMessageListener jml;
	private JizzLogger log;
	
	/**
	 * @param jml
	 * @param log
	 */
	public JizzChatManagerListener(JizzMessageListener jml, JizzLogger log) {
		this.jml = jml;
		this.log = log;
	}
	
	/* (non-Javadoc)
	 * @see org.jivesoftware.smack.ChatManagerListener#chatCreated(org.jivesoftware.smack.Chat, boolean)
	 */
	@Override
	public void chatCreated(Chat chat, boolean createdLocally) {
		log.add("[open][" + chat.getParticipant() + "]");
		chat.addMessageListener(jml);
	}

}
