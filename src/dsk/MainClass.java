package dsk;


import dsk.ram.Ram;
import dsk.ram_error.Af;
import dsk.ram_error.Cf;
import dsk.ram_test.Mats;
import dsk.ram_test.TestAlgorithm;
import dsk.stats.Draw;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class MainClass {
		
	public static void main(String[] args) {		

		Ram ram = new Ram(10);
		
		
		ram.addError(new Af(ram,5,5));
	// Iloœci b³êdów
		int errorsSAF = 10;
		int errorsSOF = 10;
		int errorsDRF = 10;
		int errorsTF = 10;
		int errorsCF = 10;
		int errorsPSF = 3;
		Mats testAlgorithm = new Mats(ram);
		testAlgorithm.test();
		
		ram.randomRam();
		ram.showRam();
		ram.damageRam(errorsSAF, errorsSOF, errorsDRF, errorsTF, errorsCF, errorsPSF);
		
		Draw draw = new Draw();
		new Thread() {
            @Override
            public void run() {
            	String[] a = {"12", "32", "1"};
                draw.launch(Draw.class, a);
                
            }
        }.start();
        
        //draw.printSomething();
		
		
		
		
	}
}
