package de.chrissx.CNetworking.Shared;

import java.math.BigInteger;
import java.util.HashMap;

public class CNDecoder {

	public static HashMap<Object, CNDatatype> decodeLine(String line) {
		HashMap<Object, CNDatatype> out = new HashMap<Object, CNDatatype>();
		for(String s : line.split("}::")) {
			CNDatatype t = CNDatatype.valueOf(s.substring(0, 2));
			Object o = new Object();
			switch(t) {
				case CHR: o = Char(s); break;
				case DOU: o = Double(s); break;
				case FLO: o = Float(s); break;
				case I32: o = Int32(s); break;
				case I64: o = Int64(s); break;
				case IBI: o = BigInt(s); break;
				case STR: o = String(s); break;
				default: System.out.println("[CN]ERROR DECODING LINE!"); o = "ERROR IN CN"; break;
			}
			out.put(o, t);
		}
		return out;
	}
	
	public static String String(String s) {
		return s.replace(CNDatatype.STR+"::{", "");
	}
	
	public static int Int32(String s) {
		return Integer.parseInt(s.replace(CNDatatype.I32+"::{", ""));
	}
	
	public static long Int64(String s) {
		return Long.parseLong(s.replace(CNDatatype.I64+"::{", ""));
	}
	
	public static BigInteger BigInt(String s) {
		return new BigInteger(s.replace(CNDatatype.IBI+"::{", ""));
	}
	
	public static float Float(String s) {
		return Float.parseFloat(s.replace(CNDatatype.FLO+"::{", ""));
	}
	
	public static double Double(String s) {
		return Double.parseDouble(s.replace(CNDatatype.DOU+"::{", ""));
	}
	
	public static char Char(String s) {
		return (s.replace(CNDatatype.FLO+"::{", "")).charAt(0);
	}
}