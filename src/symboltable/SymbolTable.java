package symboltable;

import java.util.HashMap;

import common.SymbolType;

public class SymbolTable {
	
	private HashMap<String, SymbolEntry> symTab;
	
	public SymbolTable() {
		symTab = new HashMap<>();
		initialize();
	}
	
	public boolean contains(String key) {
		return symTab.containsKey(key);
	}
	
	public SymbolEntry getEntry(String key) {
		return symTab.get(key);
	}
	
	public void addSymbol(String name, SymbolType type, long address) {
		symTab.put(name, new SymbolEntry(name, type, address));
	}
	
	private void initialize() {
		symTab.put("SP", new SymbolEntry("SP", SymbolType.PREDEFINED, 0L));
		symTab.put("LCL", new SymbolEntry("LCL", SymbolType.PREDEFINED, 1L));
		symTab.put("ARG", new SymbolEntry("ARG", SymbolType.PREDEFINED, 2L));
		symTab.put("THIS", new SymbolEntry("THIS", SymbolType.PREDEFINED, 3L));
		symTab.put("THAT", new SymbolEntry("THAT", SymbolType.PREDEFINED, 4L));
		symTab.put("R0", new SymbolEntry("R0", SymbolType.PREDEFINED, 0L));
		symTab.put("R1", new SymbolEntry("R1", SymbolType.PREDEFINED, 1L));
		symTab.put("R2", new SymbolEntry("R2", SymbolType.PREDEFINED, 2L));
		symTab.put("R3", new SymbolEntry("R3", SymbolType.PREDEFINED, 3L));
		symTab.put("R4", new SymbolEntry("R4", SymbolType.PREDEFINED, 4L));
		symTab.put("R5", new SymbolEntry("R5", SymbolType.PREDEFINED, 5L));
		symTab.put("R6", new SymbolEntry("R6", SymbolType.PREDEFINED, 6L));
		symTab.put("R7", new SymbolEntry("R7", SymbolType.PREDEFINED, 7L));
		symTab.put("R8", new SymbolEntry("R8", SymbolType.PREDEFINED, 8L));
		symTab.put("R9", new SymbolEntry("R9", SymbolType.PREDEFINED, 9L));
		symTab.put("R10", new SymbolEntry("R10", SymbolType.PREDEFINED, 10L));
		symTab.put("R11", new SymbolEntry("R11", SymbolType.PREDEFINED, 11L));
		symTab.put("R12", new SymbolEntry("R12", SymbolType.PREDEFINED, 12L));
		symTab.put("R13", new SymbolEntry("R13", SymbolType.PREDEFINED, 13L));
		symTab.put("R14", new SymbolEntry("R14", SymbolType.PREDEFINED, 14L));
		symTab.put("R15", new SymbolEntry("R15", SymbolType.PREDEFINED, 15L));
		symTab.put("R16", new SymbolEntry("R16", SymbolType.PREDEFINED, 16L));
		symTab.put("SCREEN", new SymbolEntry("SCREEN", SymbolType.PREDEFINED, 16384L));
		symTab.put("KBD", new SymbolEntry("KBD", SymbolType.PREDEFINED, 24576L));
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(String.format("%-20s %-20s %-10s\n", "Name", "Type", "Address")); // Headers
	    sb.append(String.format("%-20s %-20s %-10s\n", "------", "----", "-----")); // Divider

	    for (SymbolEntry entry : symTab.values()) {
	        sb.append(String.format("%-20s %-20s %-10d\n",
	            entry.getName(), 
	            entry.getType(), 
	            entry.getAddress()));
	    }

	    return sb.toString();
	}
}
