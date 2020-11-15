package dad.javafx.ventana;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	
	private VentanaConMemoriaController controller;
	
	@Override
	public void init() throws Exception {
		controller = new VentanaConMemoriaController();
		controller.readConfig();		
	}	
	
	@Override
	public void start(Stage primaryStage) {	 
		Scene scene = new Scene(controller.getView());
		primaryStage.setX(controller.getPosX());
		primaryStage.setTitle("Ventana con Memoria");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void stop() {
		controller.saveConfig();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
