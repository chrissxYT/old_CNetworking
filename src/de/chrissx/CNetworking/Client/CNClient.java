package de.chrissx.CNetworking.Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class CNClient {

	private int field_00002 = 001;
	private String field_00001 = "JavaSE 8_144 Version 0.0.1";
	private Socket field_00000;
	private PrintStream field_00003;
	private BufferedWriter field_00004;
	private BufferedReader field_00005;
	
	public CNClient(String address, int port, PrintStream output) {
		this.field_00003 = output;
		try {
			field_00000 = new Socket(address, port);
			output.println("Started CNetworking-ClientSocket for "+address+":"+port+".");
			field_00004 = new BufferedWriter(new PrintWriter(field_00000.getOutputStream()));
			field_00005 = new BufferedReader(new InputStreamReader(field_00000.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace(output);
		}
	}
	
	public void send(String s) {
		try {
			field_00004.write(s);
		} catch (IOException e) {
			e.printStackTrace(field_00003);
		}
		try {
			field_00004.newLine();
		} catch (IOException e) {
			e.printStackTrace(field_00003);
		}
	}
}