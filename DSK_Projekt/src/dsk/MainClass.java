package dsk;

public class MainClass {

	public static void main(String[] args) {
		
		Ram ram = new Ram(1024,1024);
	// Iloœci b³êdów
		int errorsSAF = 10;
		int errorsSOF = 10;
		int errorsDRF = 10;
		int errorsTF = 10;
		int errorsCF = 10;
		int errorsPSF = 3;
		
		
		
		ram.randomRam();
		ram.showRam();
		ram.damageRam(errorsSAF, errorsSOF, errorsDRF, errorsTF, errorsCF, errorsPSF);
		
	}
}
