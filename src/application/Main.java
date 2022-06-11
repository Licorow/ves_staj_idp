package application;
	
import java.io.File;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		dosyaIslemleri();
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("İşleri Düzenleme Programı");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		launch(args);
		
	}
	
	void dosyaIslemleri() {
		File klasor01 = new File("C:\\VES");
		File klasor02 = new File("C:\\VES\\EXEL_DATA");
		try {
			if(!klasor01.exists()) {
				klasor01.mkdir();
			}
			if(!klasor02.exists()) {
				klasor02.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*		
		File dosya01 = new File("C:\\VES\\EXEL_DATA\\DATA01.txt");
		try {
			if(!dosya01.exists()) {
				dosya01.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
		*/
	}
}
