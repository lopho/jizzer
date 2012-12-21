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

public class JizzFileTransferListener implements FileTransferListener {
	private ArrayList<JizzTransfer> transfers;
	private String folder;
	
	public JizzFileTransferListener(String folder) {
		this.folder = folder;
		transfers = new ArrayList<JizzTransfer>();
	}

	@Override
	public void fileTransferRequest(FileTransferRequest request) {
		IncomingFileTransfer t = request.accept();
		File f = new File(folder + System.getProperty("file.separator") + t.getFileName());
		String p = request.getRequestor();
		try {
			t.recieveFile(f);
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		JizzTransfer transfer = new JizzTransfer(p, t, f);
		transfers.add(transfer);
	}
	
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
		}
		return ret;
	}

}
