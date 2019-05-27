package dsk;


import java.util.ArrayList;
import java.util.List;

import dsk.ram.Ram;
import dsk.ram.error.Af;
import dsk.ram.error.Cf;
import dsk.ram.error.RamError;
import dsk.ram.stats.Draw;
import dsk.ram.test_algorithm.Mats;
import dsk.ram.test_algorithm.RamTestAlgorithm;
import dsk.ram.test_algorithm.TestLog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class MainClass {
	
	public static List<TestLog> runTestsForEachError(Ram ram, List<RamError> errors, List<RamTestAlgorithm> algorithms) {
		List<TestLog> testLogs = new ArrayList<TestLog>();
		ram.clearErrors();
		for(RamError error : errors) {
			ram.addError(error);
			for(RamTestAlgorithm algorithm : algorithms) {
				testLogs.add(
						new TestLog(algorithm, algorithm.test(ram), error)
				);
			}
			ram.clearErrors();
		}
		return testLogs;
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
            	String[] a = {"1", "2", "-10", "4", "5", "6", "7", 
            			"1", "2", "3", "4", "5", "6", "7"};
                draw.launch(Draw.class, a);       
            }
        }.start();
        
        //draw.printSomething();
		
		
		
		
	}
}
