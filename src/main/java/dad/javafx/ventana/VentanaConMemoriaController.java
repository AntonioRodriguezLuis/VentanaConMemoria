package dad.javafx.ventana;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class VentanaConMemoriaController implements Initializable {

	@FXML
	private VBox view;

	@FXML
	private Slider redSlider;

	@FXML
	private Slider greenSlider;

	@FXML
	private Slider blueSlider;

	private VentanaConMemoriaModel model;
	private Double posX;
	private Double posY;
	
	private String rutaPerfil = System.getProperty("user.home");

	public VentanaConMemoriaController() throws IOException {
		model = new VentanaConMemoriaModel();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanaConMemoriaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Bindings
		model.redProperty().bindBidirectional(redSlider.valueProperty());
		model.greenProperty().bindBidirectional(greenSlider.valueProperty());
		model.blueProperty().bindBidirectional(blueSlider.valueProperty());

		model.redProperty().addListener((o, ov, nv) -> {
			Color colorRGB = Color.rgb(model.getRed(), model.getGreen(), model.getBlue());
			setBackgroundColor(colorRGB);
		});

		model.greenProperty().addListener((o, ov, nv) -> {
			Color colorRGB = Color.rgb(model.getRed(), model.getGreen(), model.getBlue());
			setBackgroundColor(colorRGB);
		});

		model.blueProperty().addListener((o, ov, nv) -> {
			Color colorRGB = Color.rgb(model.getRed(), model.getGreen(), model.getBlue());
			setBackgroundColor(colorRGB);
		});
	}

	private void setBackgroundColor(Color colorRGB) {
		this.view.setBackground(new Background(new BackgroundFill(colorRGB, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public void readConfig() {
		try (InputStream input = new FileInputStream(rutaPerfil + "/ventana.config")) {
			Properties prop = new Properties();
			prop.load(input);
			
			this.model.setRed(Integer.parseInt(prop.getProperty("background.red")));
			this.model.setGreen(Integer.parseInt(prop.getProperty("background.green")));
			this.model.setBlue(Integer.parseInt(prop.getProperty("background.blue")));
			this.view.setPrefWidth(Double.parseDouble(prop.getProperty("size.width")));
			this.view.setPrefHeight(Double.parseDouble(prop.getProperty("size.height")));
			this.setPosX(Double.parseDouble(prop.getProperty("location.x")));
			this.setPosY(Double.parseDouble(prop.getProperty("location.y")));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void saveConfig() {

		try (OutputStream output = new FileOutputStream(rutaPerfil + "/ventana.config")) {
			Properties prop = new Properties();

			prop.setProperty("background.red", this.model.getRed() + "");
			prop.setProperty("background.green", this.model.getGreen() + "");
			prop.setProperty("background.blue", this.model.getBlue() + "");
			prop.setProperty("size.width", this.view.getWidth() + "");
			prop.setProperty("size.height", this.view.getHeight() + "");
			prop.setProperty("location.x", getX() + "");
			prop.setProperty("location.y", getY() + "");

			prop.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	private Double getX() {
		return this.view.getScene().getWindow().getX();
//		return view.localToScreen(view.getBoundsInLocal()).getMinX();
	}

	private Double getY() {
		return this.view.getScene().getWindow().getY();
//		return view.localToScreen(view.getBoundsInLocal()).getMinY();
	}

	public Double getPosX() {
		return posX;
	}

	public void setPosX(Double posX) {
		this.posX = posX;
	}

	public Double getPosY() {
		return posY;
	}

	public void setPosY(Double posY) {
		this.posY = posY;
	}

	public VBox getView() {
		return this.view;
	}
}