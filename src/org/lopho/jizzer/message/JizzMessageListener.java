package org.lopho.jizzer.message;

import java.util.ArrayList;
import java.util.HashMap;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

public class JizzMessageListener implements MessageListener {
	private ArrayList<String> commandList;
	static private final HashMap<String, String> COMMANDS =
			new HashMap<String, String>();
	
	static {
		COMMANDS.put("stop", "");
		COMMANDS.put("echo", "");
	}
	
	public JizzMessageListener() {
		commandList = new ArrayList<String>();
	}
	
	@Override
	public void processMessage(Chat chat, Message message) {
		String body = message.getBody();
		if (body != null) {
			System.out.println(body);
		}
		if (body.toLowerCase().startsWith("**jizzer*")) {
			String command = body.substring(9).toLowerCase();
			if ( COMMANDS.containsKey(command)) {
				commandList.add(body.substring(9));
			}
		}
	}
	
	public boolean hasNext() {
		if (commandList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public String next() {
		String command = commandList.get(0);
		commandList.remove(0);
		return command;
	}
}
