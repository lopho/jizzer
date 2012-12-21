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
package org.lopho.jizzer.filetransfer;

import java.io.File;
import java.util.ArrayList;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;
import org.jivesoftware.smackx.filetransfer.IncomingFileTransfer;
import org.lopho.jizzer.util.JizzLogger;

/**
 * @author lopho
 * @author b1gmct5
 */
public class JizzFileTransferListener implements FileTransferListener {
	private ArrayList<JizzTransfer> transfers;
	private String folder;
	private boolean accept;
	private JizzLogger log;
	
	/**
	 * @param folder
	 * @param log
	 */
	public JizzFileTransferListener(String folder, JizzLogger log) {
		this.folder = folder;
		this.log = log;
		transfers = new ArrayList<JizzTransfer>();
		accept = true;
	}

	/* (non-Javadoc)
	 * @see org.jivesoftware.smackx.filetransfer.FileTransferListener#fileTransferRequest(org.jivesoftware.smackx.filetransfer.FileTransferRequest)
	 */
	@Override
	public void fileTransferRequest(FileTransferRequest request) {
		if (accept) {
			IncomingFileTransfer t = request.accept();
			File f = new File(folder + System.getProperty("file.separator") + t.getFileName());
			String p = request.getRequestor();
			log.add("[upload request][" + p + "] " + t.getFileName());
			try {
				t.recieveFile(f);
			} catch (XMPPException e) {
				log.add("[Error]");
				e.printStackTrace();
			}
			JizzTransfer transfer = new JizzTransfer(p, t, f);
			transfers.add(transfer);
		}
	}
	
	/**
	 * @return
	 */
	public ArrayList<JizzTransfer> update() {
		ArrayList<JizzTransfer> ret = new ArrayList<JizzTransfer>();
		ArrayList<JizzTransfer> rem = new ArrayList<JizzTransfer>();
		for (JizzTransfer t : transfers) {
			if (t.getTransfer().isDone()) {
				ret.add(t);
				rem.add(t);
			}
		}
		for (JizzTransfer t : rem) {
			transfers.remove(t);
			log.add("[upload done][" + t.getPeer() + "] "
						+ t.getTransfer().getFileName());
		}
		return ret;
	}
	
	/**
	 * 
	 */
	public void deny() {
		accept = false;
	}
	
	/**
	 * 
	 */
	public void allow() {
		accept = true;
	}

}
