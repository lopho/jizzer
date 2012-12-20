package org.lopho.jizzer;

import java.security.InvalidParameterException;

public class JizzConfig {
	private String user;
	private String server;
	private String password;
	private String destMUC;
	private String folder;
	private String url;
	private String log;
	
	public JizzConfig(String[] args) throws InvalidParameterException {
		user = null;
		server = null;
		password = null;
		destMUC = null;
		folder = null;
		url = null;
		log = null;
		
		for (int i = 0; i < args.length; i++) {
			if (args.length > i + 1 && !args[i + 1].startsWith("-")) {
				if (args[i].equals("--user")) {
					user = args[i + 1];
				} else if (args[i].equals("--server")) {
					server = args[i + 1];
				} else if (args[i].equals("--password")) {
					password = args[i + 1];
				} else if (args[i].equals("--muc")) {
					destMUC = args[i + 1];
				} else if (args[i].equals("--folder")) {
					folder = args[i + 1];
				} else if (args[i].equals("--url")) {
					url = args[i + 1];
				} else if (args[i].equals("--log")) {
					log = args[i + 1];
				}
			}
		}
		
		if (user == null || server == null || password == null
				|| destMUC == null || folder == null || url == null) {
			throw new InvalidParameterException("Missing Arguments.");
		}
	}
	
	private JizzConfig(JizzConfig jizzConfig) {
		user = jizzConfig.user;
		server = jizzConfig.server;
		password = jizzConfig.password;
		destMUC = jizzConfig.destMUC;
		folder = jizzConfig.folder;
		url = jizzConfig.url;
		log = jizzConfig.log;
	}
	
	public JizzConfig clone() {
		return new JizzConfig(this);
	}
	
	public String getUser() { return user; }
	public String getServer() { return server; }
	public String getPassword() { return password; }
	public String getDestMUC() { return destMUC; }
	public String getFolder() { return folder; }
	public String getUrl() { return url; }
	public String getLog() { return log; }
		
}
