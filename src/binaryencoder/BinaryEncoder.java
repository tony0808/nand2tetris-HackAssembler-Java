package binaryencoder;

import common.AssemblySpecs;

public class BinaryEncoder {
	
	private BinarySpec binarySpec;

	public BinaryEncoder(BinarySpec binarySpec) {
		this.binarySpec = binarySpec;
	}
	
	public String encode_C_Commad(String dest, String comp, String jump) {
		StringBuilder binary = new StringBuilder("");
		
		binary.append("111");
		binary.append(getCompBinary(comp));
		binary.append(getDestBinary(dest));
		binary.append(getJumpBinary(jump));
		
		return binary.toString();
	}
	
	public String encode_A_command(int decimal) {
		StringBuilder binary = new StringBuilder("");
		
		binary.append("0");
		binary.append(encodeDecimal(decimal));
		
		return binary.toString();
	}
	
	private String getDestBinary(String dest) {
		return binarySpec.getDestValue(dest);
	}
	
	private String getCompBinary(String comp) {
		return binarySpec.getCompValue(comp);
	}
	
	private String getJumpBinary(String jump) {
		return binarySpec.getJumpValue(jump);
	}
	
	private String encodeDecimal(int decimal) {
		StringBuilder binary = new StringBuilder("");
		int zerosToAdd;
		
		while(decimal > 0) {
			binary.append((decimal % 2) == 0 ? "0" : "1");
			decimal /= 2;
		}
		
		zerosToAdd = AssemblySpecs.DECIMAL_SIZE - binary.length();
		
		binary.append("0".repeat(zerosToAdd));
		binary.reverse();
		
		return binary.toString();
	}
}
