package dsk;

public class BitOperations{
	
	// PRINT BINARY
	
	public static void printBinary(byte b) {
		System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
	}
	
	public static void printBinary(short s) {
		System.out.println(String.format("%16s", Integer.toBinaryString(s)).replace(' ', '0'));
	}
	
	public static void printBinary(int i) {
		System.out.println(String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0'));
	}
	
	public static void printBinary(long l) {
		System.out.println(String.format("%64s", Long.toBinaryString(l)).replace(' ', '0'));
	}
	
	
	// SET BIT
	
	public static byte setBit(byte b,int index, boolean value) {
		if(value) {
			byte mask = (byte)(1 << index); // All 0 and single 1
			return (byte)(b | mask);
		}else {
			byte mask = (byte)~(1 << index); // All 1 and single 0
			return (byte)(b & mask);
		}		
	}
	
	public static short setBit(short b,int index, boolean value) {
		if(value) {
			short mask = (short)(1 << index); // All 0 and single 1
			return (short)(b | mask);
		}else {
			short mask = (short)~(1 << index); // All 1 and single 0
			return (short)(b & mask);
		}		
	}
	
	public static int setBit(int i,int index, boolean value) {
		if(value) {
			int mask = 1 << index; // All 0 and single 1
			return i | mask;
		}else {
			int mask = ~(1 << index); // All 1 and single 0
			return i & mask;
		}		
	}
	
	public static long setBit(long b,int index, boolean value) {
		if(value) {
			long mask = (long)(1L << index); // All 0 and single 1
			return (long)(b | mask);
		}else {
			long mask = (long)~(1L << index); // All 1 and single 0
			return (long)(b & mask);
		}		
	}
	
	//GET BIT
	
	public static boolean getBit(byte b, int index) {
		byte mask = (byte)(1 << index);
		return (b & mask) != 0;
	}
	
	public static boolean getBit(short b, int index) {
		short mask = (short)(1 << index);
		return (b & mask) != 0;
	}
	
	public static boolean getBit(int i, int index) {
		int mask = (1 << index);
		return (i & mask) != 0;
	}
	
	public static boolean getBit(long l, int index) {
		long mask = (byte)(1L << index);
		return (l & mask) != 0;
	}
}
