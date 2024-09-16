package BinaryEncoder;

import java.util.HashMap;

public class BinarySpec {
	
	private HashMap<String, String> destMap;
    private HashMap<String, String> compMap;
    private HashMap<String, String> jumpMap;
    
    public BinarySpec() {
    	initializeMaps();
    }
    
    private void initializeMaps() {
    	initializeDestMap();
    	initializeCompMap();
    	initializeJumpMap();
    }
    
    public String getDestValue(String key) {
    	if(!destMap.containsKey(key)) {
    		System.out.println("Destination field cannot be mapped.");
    		System.exit(1);
    	}
    	return destMap.get(key);
    }
    
    public String getCompValue(String key) {
    	if(!compMap.containsKey(key)) {
    		System.out.println("Computation field cannot be mapped.");
    		System.exit(1);
    	}
    	return compMap.get(key);
    }
    
    public String getJumpValue(String key) {
    	if(!jumpMap.containsKey(key)) {
    		System.out.println("Jump field cannot be mapped.");
    		System.exit(1);
    	}
    	return jumpMap.get(key);
    }
    
    
    private void initializeDestMap() {
    	destMap = new HashMap<>();
    	
    	destMap.put(null,  "000");
    	destMap.put("M",   "001");
    	destMap.put("D",   "010");
    	destMap.put("MD",  "011");
    	destMap.put("A",   "100");
    	destMap.put("AM",  "101");
    	destMap.put("AD",  "110");
    	destMap.put("AMD", "111");
    }
    
    private void initializeCompMap() {
    	compMap = new HashMap<>();
    	
    	compMap.put("0",   "0101010");
    	compMap.put("1",   "0111111");
    	compMap.put("-1",  "0111010");
    	compMap.put("D",   "0001100");
    	compMap.put("A",   "0110000");
    	compMap.put("!D",  "0001101");
    	compMap.put("!A",  "0110001");
    	compMap.put("-D",  "0001111");
    	compMap.put("-A",  "0110011");
    	compMap.put("D+1", "0011111");
    	compMap.put("A+1", "0110111");
    	compMap.put("D-1", "0001110");
    	compMap.put("A-1", "0110010");
    	compMap.put("D+A", "0000010");
    	compMap.put("D-A", "0010011");
    	compMap.put("A-D", "0000111");
    	compMap.put("D&A", "0000000");
    	compMap.put("D|A", "0010101");
    	
    	compMap.put("M",   "1110000");
    	compMap.put("!M",  "1110001");
    	compMap.put("-M",  "1110011");
    	compMap.put("M+1", "1110111");
    	compMap.put("M-1", "1110010");
    	compMap.put("D+M", "1000010");
    	compMap.put("D-M", "1010011");
    	compMap.put("M-D", "1000111");
    	compMap.put("D&M", "1000000");
    	compMap.put("D|M", "1010101");
    }
    
    private void initializeJumpMap() {
    	jumpMap = new HashMap<>();
    	
    	jumpMap.put(null,  "000");
    	jumpMap.put("JGT", "001");
    	jumpMap.put("JEQ", "010");
    	jumpMap.put("JGE", "011");
    	jumpMap.put("JLT", "100");
    	jumpMap.put("JNE", "101");
    	jumpMap.put("JLE", "110");
    	jumpMap.put("JMP", "111");
    }
}



























