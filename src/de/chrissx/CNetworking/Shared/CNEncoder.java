package de.chrissx.CNetworking.Shared;

import java.math.BigInteger;
import java.util.HashMap;

public class CNEncoder {

	private String s;
	
	public String encodeLine(HashMap<Object, CNDatatype> objects) {
		s = "";
		objects.forEach((o, t) -> encode(o, t));
		return s;
	}
	
	private void encode(Object o, CNDatatype t) {
		switch(t) {
			case CHR: s+=Char((char)o); break;
			case DOU: s+=Double((double)o); break;
			case FLO: s+=Float((float)o); break;
			case I32: s+=Int32((int)o); break;
			case I64: s+=Int64((long)o); break;
			case IBI: s+=BigInt((BigInteger)o); break;
			case STR: s+=String((String)o); break;
			default: s+="CRITICAL ERROR IN CNEncoder!!"; break;
		}
	}
	
	public static String String(String s) {
		return CNDatatype.STR+"::{"+s+"}::";
	}
	
	public static String Int32(int i) {
		return CNDatatype.I32+"::{"+i+"}::";
	}
	
	public static String Int64(long l) {
		return CNDatatype.I64+"::{"+l+"}::";
	}
	
	public static String BigInt(BigInteger bi) {
		return CNDatatype.IBI+"::{"+bi+"}::";
	}
	
	public static String Float(float f) {
		return CNDatatype.FLO+"::{"+f+"}::";
	}
	
	public static String Double(double d) {
		return CNDatatype.DOU+"::{"+d+"}::";
	}
	
	public static String Char(char c) {
		return CNDatatype.CHR+"::{"+c+"}::";
	}
}