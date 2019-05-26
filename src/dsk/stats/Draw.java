package dsk.stats;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Draw extends Application {
	
    //https://docs.oracle.com/javafx/2/api/javafx/application/Application.html
	//https://www.tutorialspoint.com/javafx/javafx_application.htm
	private int x;
	
	public Draw() {
		// TODO Auto-generated constructor stub
	}

	public void init() throws Exception{
		this.x = 10;
		System.out.println("Aplikacja - inicjalizacja");
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("Aplikacja - start");
		Parameters params = getParameters();
		List<String> list = params.getRaw();
		System.out.println("d³ugoœæ="+list.size());
		
		Circle circ = new Circle(40, 40, 30);
        Group root = new Group(circ);
        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("My JavaFX Application"+x);
        stage.setScene(scene);
        stage.show();
	}

    public void stop() throws Exception {
    	System.out.println("Aplikacja - STOP");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


}