package symboltable;

import common.SymbolType;

public class SymbolEntry {
	
	private String name;
	private SymbolType type;
	private long address;
	
	public SymbolEntry(String name, SymbolType type, long address) {
		this.name = name;
		this.type = type;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	
	public SymbolType getType() {
		return type;
	}
	
	public long getAddress() {
		return address;
	}
}
