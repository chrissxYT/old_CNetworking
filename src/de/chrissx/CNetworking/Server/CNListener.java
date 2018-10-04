package de.chrissx.CNetworking.Server;

public abstract class CNListener {

	public abstract void onStart();
	
	public abstract void onReceive(String socketName, class_0000000 socket, String msg);
	
	public abstract void onSend(String socketName, class_0000000 socket, String msg);
	
	public abstract void onShutdown();
}