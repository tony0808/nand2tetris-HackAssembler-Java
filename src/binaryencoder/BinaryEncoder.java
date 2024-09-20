package binaryencoder;

import common.AssemblySpecs;
import exception.FieldMappingException;

public class BinaryEncoder {
	
	private BinarySpec binarySpec;

	public BinaryEncoder(BinarySpec binarySpec) {
		this.binarySpec = binarySpec;
	}
	
	public String encode_C_Commad(String dest, String comp, String jump) throws FieldMappingException {
		StringBuilder binary = new StringBuilder("");
		
		binary.append("111");
		binary.append(getCompBinary(comp));
		binary.append(getDestBinary(dest));
		binary.append(getJumpBinary(jump));
		
		return binary.toString();
	}
	
	public String encodeDecimal(long decimal) {
		StringBuilder binary = new StringBuilder("");
		
		binary.append("0");
		binary.append(decimalToBinary(decimal));
		
		return binary.toString();
	}
	
	private String getDestBinary(String dest) throws FieldMappingException {
		return binarySpec.getDestValue(dest);
	}
	
	private String getCompBinary(String comp) throws FieldMappingException {
		return binarySpec.getCompValue(comp);
	}
	
	private String getJumpBinary(String jump) throws FieldMappingException {
		return binarySpec.getJumpValue(jump);
	}
	
	private String decimalToBinary(long decimal) {
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
