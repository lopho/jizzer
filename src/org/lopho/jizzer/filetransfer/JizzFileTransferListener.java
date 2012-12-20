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
		ArrayList<JizzTransfer> id = new ArrayList<JizzTransfer>();
		for(JizzTransfer t : transfers) {
			if(t.getTransfer().isDone()) {
				ret.add(t);
				id.add(t);
			}
		}
		for(JizzTransfer t : id) {
			transfers.remove(t);
		}
		return ret;
	}

}
