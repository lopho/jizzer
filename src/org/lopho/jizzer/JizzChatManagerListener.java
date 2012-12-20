package org.lopho.jizzer;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.lopho.jizzer.message.JizzMessageListener;

public class JizzChatManagerListener implements ChatManagerListener {
	private JizzMessageListener jml;
	
	public JizzChatManagerListener(JizzMessageListener jml) {
		this.jml = jml;
	}
	
	@Override
	public void chatCreated(Chat chat, boolean createdLocally) {
		System.out.println(chat.getParticipant());
		chat.addMessageListener(jml);
	}

}
