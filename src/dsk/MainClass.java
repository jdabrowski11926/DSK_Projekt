package dsk;


import java.util.List;

import dsk.ram.Ram;
import dsk.ram.error.Af;
import dsk.ram.error.Cf;
import dsk.ram.error.RamError;
import dsk.ram.stats.Draw;
import dsk.ram.test_algorithm.Mats;
import dsk.ram.test_algorithm.RamTestAlgorithm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class MainClass {
	
	public static void runTests(Ram ram, List<RamError> errors, List<RamTestAlgorithm> algorithms) {
		for(RamError error : errors) {
			ram.addError(error);
			for(RamTestAlgorithm algorithm : algorithms) {
				algorithm.test(ram);
			}
			ram.clearErrors();
		}
	}
	
	public static void main(String[] args) {		

		Ram ram = new Ram(10);
		
		
		ram.addError(new Af(ram,5,5));
		
		Mats testAlgorithm = new Mats();
		testAlgorithm.test(ram);
		
		ram.randomRam();
		ram.showRam();
		
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
