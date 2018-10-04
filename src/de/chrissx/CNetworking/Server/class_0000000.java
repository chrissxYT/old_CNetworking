package de.chrissx.CNetworking.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class class_0000000 {
	
	public Socket field_00000;
	public BufferedWriter field_00001;
	public BufferedReader field_00002;
	
	public class_0000000(Socket s, PrintStream output) {
		try {
			this.field_00000 = s;
			field_00001 = new BufferedWriter(new PrintWriter(s.getOutputStream()));
			field_00002 = new BufferedReader(new InputStreamReader(s.getInputStream()));
		}catch(Exception e) {
			e.printStackTrace(output);
		}
	}
}