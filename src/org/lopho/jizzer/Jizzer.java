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

import java.util.ArrayList;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.lopho.jizzer.filetransfer.JizzFileTransferListener;
import org.lopho.jizzer.filetransfer.JizzTransfer;
import org.lopho.jizzer.message.JizzMessageListener;

public class Jizzer {
	private JizzConfig conf;
	private ConnectionConfiguration connConf;
	private Connection conn;
	private FileTransferManager ftm;
	private JizzMessageListener ml;
	private JizzChatManagerListener cml;
	private JizzFileTransferListener ftl;
	private MultiUserChat muc;
	private boolean run;
	private int delta;
	
	public Jizzer(JizzConfig jizzConfig) throws XMPPException {
		conf = jizzConfig;
		connConf = new ConnectionConfiguration(conf.getServer(), 5222);
		conn = new XMPPConnection(connConf);
		
		conn.connect();
		
		ftm = new FileTransferManager(conn);
		ml = new JizzMessageListener();
		cml = new JizzChatManagerListener(ml);
		ftl = new JizzFileTransferListener(conf.getFolder());
		ftm.addFileTransferListener(ftl);
		
		conn.getChatManager().addChatListener(cml);
		conn.login(conf.getUser(), conf.getPassword(), "jizzer");
		
		muc = new MultiUserChat(conn, conf.getDestMUC());
		
		run = true;
		delta = 1000;
	}
	
	public void run() throws InterruptedException, XMPPException {
		while (run)
		{
			update(delta);
		}
		conn.disconnect();
	}
	
	private void update(int delta) throws InterruptedException, XMPPException {
		ArrayList<JizzTransfer> transfers = ftl.update();
		if (!transfers.isEmpty()) {
			for (JizzTransfer t : transfers) {
				System.out.println(t.getTransfer().getFileName());
				Chat chat = conn.getChatManager().createChat(t.getPeer(), ml);
				chat.sendMessage(conf.getUrl() + t.getTransfer().getFileName());

			    muc.join("jizzer");
			    muc.sendMessage(conf.getUrl() + t.getTransfer().getFileName());
			    muc.leave();
			}
		}
		
		if (ml.hasNext()) {
			String command = ml.next();
			if (command != null && command.equals("stop")) {
				run = false;
				System.out.println("recieved stop signal - shutting down");
			}
		}
		Thread.sleep(delta);
	}
	
	public JizzConfig getConfig() {
		return conf.clone();
	}
}
