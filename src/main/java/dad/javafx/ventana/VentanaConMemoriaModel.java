package dad.javafx.ventana;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class VentanaConMemoriaModel {

	private IntegerProperty red = new SimpleIntegerProperty();
	private IntegerProperty green = new SimpleIntegerProperty();
	private IntegerProperty blue = new SimpleIntegerProperty();
	
	public final IntegerProperty redProperty() {
		return this.red;
	}
	
	public final int getRed() {
		return this.redProperty().get();
	}
	
	public final void setRed(final int red) {
		this.redProperty().set(red);
	}
	
	public final IntegerProperty greenProperty() {
		return this.green;
	}
	
	public final int getGreen() {
		return this.greenProperty().get();
	}
	
	public final void setGreen(final int green) {
		this.greenProperty().set(green);
	}
	
	public final IntegerProperty blueProperty() {
		return this.blue;
	}
	
	public final int getBlue() {
		return this.blueProperty().get();
	}
	
	public final void setBlue(final int blue) {
		this.blueProperty().set(blue);
	}
}
