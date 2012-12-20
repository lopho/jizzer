package org.lopho.jizzer.filetransfer;

import java.io.File;

import org.jivesoftware.smackx.filetransfer.IncomingFileTransfer;

public class JizzTransfer {
	private final File file;
	private final IncomingFileTransfer transfer;
	private final String peer;
	
	public JizzTransfer(String peer, IncomingFileTransfer transfer, File file) {
		this.file = file;
		this.transfer = transfer;
		this.peer = peer;
	}
	
	public File getFile() {
		return file;
	}
	
	public IncomingFileTransfer getTransfer() {
		return transfer;
	}
	
	public String getPeer() {
		return peer;
	}
}
