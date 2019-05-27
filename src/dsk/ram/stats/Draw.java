package dsk.ram.stats;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Draw extends Application {
	
    //https://docs.oracle.com/javafx/2/api/javafx/application/Application.html
	//https://www.tutorialspoint.com/javafx/javafx_application.htm
	
	List<XYChart.Data<String, Number>> dataErrorsFound;
	List<XYChart.Data<String, Number>> dataErrorsNotFound;

	public void init() throws Exception{

	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
	// Ustawienia pocz¹tkowe wykresu ********************************************************
		final CategoryAxis xAxis = new CategoryAxis();
	    final NumberAxis yAxis = new NumberAxis();
		StackedBarChart<String, Number> sbc =
	            new StackedBarChart<String, Number>(xAxis, yAxis);
		final XYChart.Series<String, Number> series1 =
	            new XYChart.Series<String, Number>();
		final XYChart.Series<String, Number> series2 =
	            new XYChart.Series<String, Number>();
		String[] labelList = {"AF", "CF", "DRF", "PSF", "SAF", "SOF", "TF"};    
        xAxis.setCategories(FXCollections.<String>observableArrayList(labelList));
        xAxis.setLabel("Typ b³êdu");
        yAxis.setLabel("Iloœæ wyst¹pieñ");
        series1.setName("Iloœæ wykrytych b³êdów");
        series2.setName("B³êdy niewykryte");
		
    // Przes³anie parametrów do wykresu ****************************************************
		Parameters params = getParameters();
		List<String> list = params.getRaw();
		for(int i=0 ; i<list.size() ; i++) {
			System.out.println("element : "+ list.get(i));
		}
		
		dataErrorsFound = new ArrayList<>();
		for(int i=0 ; i<list.size()/2 ; i++) {
			dataErrorsFound.add(new XYChart.Data<String, Number>(labelList[i], Integer.parseInt(list.get(i))));
			series1.getData().add(dataErrorsFound.get(i));
		}
		
		dataErrorsNotFound = new ArrayList<>();
		for(int i=list.size()/2 ; i<list.size() ; i++) {
			dataErrorsNotFound.add(new XYChart.Data<String, Number>(labelList[i-7], Integer.parseInt(list.get(i))));
			series2.getData().add(dataErrorsNotFound.get(i-7));
		}
        
    // Ustawienia koñcowe sceny *****************************************************************
        Scene scene = new Scene(sbc, 800, 600);
        sbc.setCategoryGap(50);
        //for(int i=0 ; i<list.size()/2 ; i++) {
        //	displayLabelForData(dataErrorsFound.get(i));
        //}
        sbc.getData().addAll(series1, series2);
        
        stage.setTitle("WYKRESY");
        stage.setScene(scene);
        stage.show();
	}

    public void stop() throws Exception {
    	
    }
    
    private void displayLabelForData(XYChart.Data<String, Number> data) {
    	  final Node node = data.getNode();
    	  final Text dataText = new Text(data.getYValue() + "");
    	  node.parentProperty().addListener(new ChangeListener<Parent>() {
    	    @Override 
    	    public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
    	      Group parentGroup = (Group) parent;
    	      parentGroup.getChildren().add(dataText);
    	    }

    	  });

    	  node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
    	    @Override public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
    	      dataText.setLayoutX(
    	        Math.round(
    	          bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
    	        )
    	      );
    	      dataText.setLayoutY(
    	        Math.round(
    	          bounds.getMinY() - dataText.prefHeight(-1) * 0.5
    	        )
    	      );
    	    }
    	  });
    	}
}