package de.chrissx.CNetworking.Server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CNServer {

	private int field_00007 = 001;
	private String field_00006 = "JavaSE 8_144 Version 0.0.1";
	private ServerSocket field_00000;
	private PrintStream field_00001;
	private Map<String, class_0000000> field_00002 = new HashMap<String, class_0000000>();
	private Thread field_00003;
	private List<CNListener> field_00004 = new ArrayList<CNListener>();
	private final long field_00005;
	
	public CNServer(PrintStream output, int port, long polling_rate) {
		this.field_00001 = output;
		this.field_00005 = polling_rate;
		try {
			field_00000 = new ServerSocket(port);
			output.println("Started CNetworking-ServerSocket on port "+port+".");
		} catch (IOException e) {
			e.printStackTrace(output);
		}
	}
	
	public void addListener(CNListener listener) {
		field_00004.add(listener);
	}
	
	public void start() {
		field_00003 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(field_00005);
					} catch (InterruptedException e) {
						e.printStackTrace(field_00001);
					}
					field_00002.forEach((argument_s, s) -> {
						String ss;
						try {
							while((ss = s.field_00002.readLine()) != null && ss != "") {
								for(CNListener l : field_00004) {
									l.onReceive(argument_s, s, ss);
								}
							}
						} catch (IOException e) {
							e.printStackTrace(field_00001);
						}
					});
				}
			}
		});
		field_00003.start();
	}
	
	public void acceptClient(String name, boolean thread) throws IOException {
		if(thread) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Socket s = field_00000.accept();
						field_00002.put(name, new class_0000000(s, field_00001));
					} catch (Exception e) {
						e.printStackTrace(field_00001);
					}
				}
			});
			t.start();
		}else {
			Socket s = field_00000.accept();
			field_00002.put(name, new class_0000000(s, field_00001));
		}
	}
	
	public Socket getSocket(String name) {
		return field_00002.get(name).field_00000;
	}
	
	public void rename(String currName, String newName) {
		class_0000000 s = field_00002.get(currName);
		field_00002.remove(currName);
		field_00002.put(newName, s);
	}
	
	public void send(String s) {
		field_00002.forEach((name, so) -> {
			try {
				so.field_00001.write(s);
			} catch (IOException e) {
				e.printStackTrace(field_00001);
			}
			try {
				so.field_00001.newLine();
			} catch (IOException e) {
				e.printStackTrace(field_00001);
			}
			try {
				so.field_00001.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			for(CNListener l : field_00004) {
				l.onSend(name, so, s);
			}
		});
	}
	
	public void send(String s, String client) {
		class_0000000 so = field_00002.get(client);
		try {
			so.field_00001.write(s);
		} catch (IOException e) {
			e.printStackTrace(field_00001);
		}
		try {
			so.field_00001.newLine();
		} catch (IOException e) {
			e.printStackTrace(field_00001);
		}
		try {
			so.field_00001.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(CNListener l : field_00004) {
			l.onSend(client, so, s);
		}
	}
}