package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;


public class SampleController implements Initializable{
	File klasor02 = new File("C:\\VES\\EXEL_DATA");



	@FXML
	void btn_SIL_ekle_ekranUst(ActionEvent event) {
		if(lv_ekranUst_secilen!=null && !lv_ekranUst_secilen.equals("")) {
			File f = new File("C:\\VES\\EXEL_DATA\\"+lbl_secilenGurup.getText()+"\\"+lv_ekranUst_secilen);
			if(f.exists()) {
				lv_ekranUst.getItems().clear();
		        String[] dosya_list = f.list();
		        if(dosya_list.length>0) {
		        	 for (int i = 0; i < dosya_list.length; i++) {
				            File f89 = new File(f.getAbsolutePath()+"\\"+dosya_list[i]);
				            f89.delete();
				     }
		        }
				f.delete();
				System.out.println(f.getAbsolutePath());
				lv_ekran.getItems().clear();
			}
		}
		gurubunIcındekileriListele(("C:\\VES\\EXEL_DATA\\"+cb_gurup.getValue()));
	}
	

    @FXML
    void btn_SIL(ActionEvent event) {
    	if(lbl_secilenGurup.getText()!=null && !lbl_secilenGurup.getText().equals("")) {
    		File f = new File("C:\\VES\\EXEL_DATA\\"+lbl_secilenGurup.getText());
    		if(f.exists()) {
    			lv_ekranUst.getItems().clear();
    			lv_ekran.getItems().clear();    			
    			String[] dosya_list = f.list();
		        if(dosya_list.length>0) {
		        	 for (int i = 0; i < dosya_list.length; i++) {
		        		 File f89 = new File(f.getAbsolutePath()+"\\"+dosya_list[i]);
		        		 String[] dosya_list2 = f89.list();
		        		 if(dosya_list2.length>0) {
		        			 for (int j = 0; j < dosya_list2.length; j++) {
						            File f99 = new File(f.getAbsolutePath()+"\\"+dosya_list[i]+"\\"+dosya_list2[j]);
						            f99.delete();
						     }
		        		 }
		        		 f89.delete();
				     }
		        }
		        f.delete();
		        Listele(klasor02);
    		}
		}
    }
    
    @FXML
    void btn_SIL_ekle_ekran(ActionEvent event) {
    	
    	if(lbl_secilenGurup.getText()!=null && !lbl_secilenGurup.getText().equals("")) {
    		if(lv_ekranUst_secilen!=null && !lv_ekranUst_secilen.equals("")) {
    			if(lv_ekran_secilen!=null && !lv_ekran_secilen.equals("")) {
    				String yol = klasor02+"\\"+lbl_secilenGurup.getText()+"\\"+lv_ekranUst_secilen+"\\DATA.txt";
    				String[] okunanVeriler = Oku2(yol);
    				File dosya = new File(yol);
    				boolean silinenilkmi = false;
    				try {
    					if(!dosya.exists()) {
    						dosya.createNewFile();
    					}
    					FileWriter fw = new FileWriter(dosya, false);
    					BufferedWriter yaz = new BufferedWriter(fw);
   
    					if(ide_ekran == 0) {
	
						}
    					for(int i=0; i<okunanVeriler.length; i++) {
    						
    						if(ide_ekran == i) {
    							if(ide_ekran == 0) {
    								silinenilkmi = true;
    							}
    						}else {
    							if(silinenilkmi) {
    								yaz.write(okunanVeriler[i]);
    								silinenilkmi=false;
    							}else {
    								if(i == 0) {			
        								yaz.write(okunanVeriler[i]);
        							}else {
        								yaz.write("\n"+okunanVeriler[i]);
        							}
    							}
    							
    						}

    						
    						
    					}
    					
    					yaz.flush();
    					yaz.close();
    					fw.close();	
    				} catch (IOException e) {
    					e.printStackTrace();
    					System.out.println(yol);
    				}
    				lv_ekran.getItems().clear();
    		    	lv_ekran.getItems().addAll(okudugunuYazdir(yol));
            	}   
    	
        	}	
 
    	}
    	
		

		
	
		

		
    }

    @FXML
    private ProgressBar bar0;
    
    @FXML
    private ProgressBar bar99;
    
    
	@FXML
	private ComboBox<String> cb_gurup;
	

    @FXML
    private Label lbl_Yuzde;

    @FXML
    private TextArea tf_gurup;

	  
    @FXML
    private ListView<String> lv_ekran;

    @FXML
    private Label lbl_secilenGurup;
   
    @FXML
    private Label lbl_YuzdeGurup;
    
    @FXML
    private ListView<String> lv_ekranUst;

    String lv_ekran_secilen="";
    String lv_ekranUst_secilen="";
    int ide_ekran;
    
    String yazilacakYol="";

    void temizle() {
    	tf_gurup.clear();
    }
    
    @FXML
    void btn_yaz(ActionEvent event) {
    	
    	Yaz(yazilacakYol, tf_gurup.getText());
    	lv_ekran.getItems().clear();
    	lv_ekran.getItems().addAll(okudugunuYazdir(yazilacakYol));
    	hesaplaYuzdeEkranUst(yazilacakYol);
    	temizle();
    }
    

    @FXML
    void btn_ekle(ActionEvent event) {
    	OlusturKlasor(tf_gurup.getText());
    	Listele(klasor02);
    	temizle();
    }
    
    @FXML
    void cb_degistimiaction(ActionEvent event) {

    	lbl_secilenGurup.setText(cb_gurup.getValue());
    	gurubunIcındekileriListele(("C:\\VES\\EXEL_DATA\\"+cb_gurup.getValue()));
    	
    	toplamYuzdeHesapla99();
    	
    }
    
    void toplamYuzdeHesapla() {
    	File dosya = new File("C:\\VES\\EXEL_DATA\\");
        String[] dosya_list = dosya.list();
        for (int i = 0; i < dosya_list.length; i++) {
        	File dosya2 = new File("C:\\VES\\EXEL_DATA\\"+dosya_list[i]);
        	String[] dosya_list2 = dosya2.list();
            for (int j = 0; j < dosya_list2.length; j++) {          	
            	//String yolx = "C:\\VES\\EXEL_DATA\\"+dosya_list[i]+"\\"+dosya_list2[j];
            	//System.out.println(dosya_list[i]+" - "+dosya_list2[j]+" - "+hesaplaYuzdeEkranUst2(yolx,dosya_list2[j].length()));
            	System.out.println(dosya_list[i]+" - "+dosya_list2[j]+" - "+dosya_list2.length);
            }
        }
        
        
    }
    void toplamYuzdeHesapla99() {
    	File dosya = new File("C:\\VES\\EXEL_DATA\\"+lbl_secilenGurup.getText());
    	String[] dosya_list = dosya.list();
    	int toplam = 0;
    	for(int i=0; i<dosya_list.length; i++){
    		String yol95 = "C:\\VES\\EXEL_DATA\\"+lbl_secilenGurup.getText()+"\\"+dosya_list[i]+"\\DATA.txt";
    		
    		System.out.println(dosya_list[i] +" - "+ hesaplaYuzdeEkranUst2(yol95));
    		toplam += hesaplaYuzdeEkranUst2(yol95);
    		
    	}
    	lbl_YuzdeGurup.setText((toplam/dosya_list.length)+"%");
    	double progress = Math.ceil(toplam/dosya_list.length)/100;
    	bar0.setProgress(progress);
    	
    }
    
    int hesaplaYuzdeEkranUst2(String yol) {
		String[] okunanVeriler = Oku2(yol);
		int tamamlananSayisi = 0;
		float s2=okunanVeriler.length;
		
		
		for(int i=0; i<okunanVeriler.length; i++) {
			
			if(okunanVeriler[i].length()>13 && okunanVeriler[i].substring(okunanVeriler[i].length()-13, okunanVeriler[i].length()).equals(" - TAMAMLANDI")) {
				tamamlananSayisi++;
			}else {
				
				//yazma = okunanVeriler[i].substring(0, okunanVeriler[i].length()-2)+" - TAMAMLANDI";
			}
			
		}
		
		// lbl_Yuzde.setText(""+lv_ekran.getItems().size()+" / "+tamamlananSayisi);
		float s1 = tamamlananSayisi;
		float yuzdeSayisi = (s1/s2)*100;
		//System.out.println(""+lv_ekran.getItems().size()+" / "+tamamlananSayisi);
		return ((int)Math.ceil(yuzdeSayisi));
	}
    

    @FXML
    void btn_ekle_ekranUst(ActionEvent event) {
    	if(tf_gurup.getText() != null && tf_gurup.getText() != "") {
    		OlusturKlasor(cb_gurup.getValue()+"\\"+tf_gurup.getText());
    		gurubunIcındekileriListele(("C:\\VES\\EXEL_DATA\\"+cb_gurup.getValue()));
    	}
    	temizle();
    	toplamYuzdeHesapla99();
    }
    
    @FXML
    void btn_tamamla(ActionEvent event) {
    	TAMAMLANDIYap(ide_ekran, yazilacakYol);
    	hesaplaYuzdeEkranUst(yazilacakYol);
    	toplamYuzdeHesapla99();
    }


    
	public void initialize(URL arg0, ResourceBundle arg1) {
		Listele(klasor02);
		bar0.setStyle("-fx-accent: #00FF00;");
		bar99.setStyle("-fx-accent: #00FF00;");
		
		
		lv_ekranUst.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				lv_ekranUst_secilen = lv_ekranUst.getSelectionModel().getSelectedItem();
				yazilacakYol = "C:\\VES\\EXEL_DATA\\"+lbl_secilenGurup.getText()+"\\"+lv_ekranUst_secilen+"\\DATA.txt";
				lv_ekran.getItems().clear();
		    	lv_ekran.getItems().addAll(okudugunuYazdir(yazilacakYol));
		    	hesaplaYuzdeEkranUst(yazilacakYol);
		    	//System.out.println(yazilacakYol);
				
			}
			
		});
		
		lv_ekran.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				lv_ekran_secilen = lv_ekran.getSelectionModel().getSelectedItem();	
				ide_ekran = lv_ekran.getSelectionModel().getSelectedIndex();
				OkuLast(yazilacakYol, ide_ekran);
				
			}
			
		});
		 
		 
		 
		 
	}
	
	
	
	void hesaplaYuzdeEkranUst(String yol) {
		String[] okunanVeriler = Oku2(yol);
		int tamamlananSayisi = 0;
		for(int i=0; i<okunanVeriler.length; i++) {
			
			if(okunanVeriler[i].length()>13 && okunanVeriler[i].substring(okunanVeriler[i].length()-13, okunanVeriler[i].length()).equals(" - TAMAMLANDI")) {
				tamamlananSayisi++;
			}else {
				
				//yazma = okunanVeriler[i].substring(0, okunanVeriler[i].length()-2)+" - TAMAMLANDI";
			}
			
		}
		
		// lbl_Yuzde.setText(""+lv_ekran.getItems().size()+" / "+tamamlananSayisi);
		float s1 = tamamlananSayisi;
		float s2 = lv_ekran.getItems().size();
		float yuzdeSayisi = (s1/s2)*100;
		System.out.println(""+lv_ekran.getItems().size()+" / "+tamamlananSayisi);
		lbl_Yuzde.setText(""+(int)Math.ceil(yuzdeSayisi)+"%");
		double progress = Math.ceil(yuzdeSayisi)/100;
		bar99.setProgress(progress);
	}
	
	void Yaz(String yol, String veri){
		File dosya = new File(yol);
		try {
			if(!dosya.exists()) {
				dosya.createNewFile();
			}
			FileWriter fw = new FileWriter(dosya, true);
			BufferedWriter yaz = new BufferedWriter(fw);
			if((dosya.length()<=0)) {
				yaz.write(veri);
			}else {
				yaz.write("\n"+veri);
			}
			yaz.flush();
			yaz.close();
			fw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	String Oku(File dosya){
		String veri = ""; 
		char[] veriler = new char[(int)dosya.length()];
		try {
			FileReader fr = new FileReader(dosya);
			fr.read(veriler);			
			veri = new String(veriler);
			System.out.println(veri);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return veri;
	}
	
	void OkuLast(String yol, int ide) {
		//String[] veri = Oku2(yol);
		//lbl_tryIde.setText(ide+" "+veri[ide].substring(veri[ide].length()-13, veri[ide].length()));
	}
	
	void TAMAMLANDIYap(int ide, String yol) {
		String[] okunanVeriler = Oku2(yol);
		
		File dosya = new File(yol);
		try {
			if(!dosya.exists()) {
				dosya.createNewFile();
			}
			FileWriter fw = new FileWriter(dosya, false);
			BufferedWriter yaz = new BufferedWriter(fw);
			String yazma = "";
			for(int i=0; i<okunanVeriler.length; i++) {
				
				if(ide == i) {
					if(okunanVeriler[i].length()>13 && okunanVeriler[i].substring(okunanVeriler[i].length()-13, okunanVeriler[i].length()).equals(" - TAMAMLANDI")) {
						yazma = okunanVeriler[i].substring(0, okunanVeriler[i].length()-13);
					}else {
						yazma = okunanVeriler[i]+" - TAMAMLANDI";

					}
					
					if(i == 0) {			
						yaz.write(yazma);
					}else {
						yaz.write("\n"+yazma);
					}
					
				}else {
					if(i == 0) {			
						yaz.write(okunanVeriler[i]);
					}else {
						yaz.write("\n"+okunanVeriler[i]);
					}
				}
				
			}
			
			yaz.flush();
			yaz.close();
			fw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		lv_ekran.getItems().clear();
    	lv_ekran.getItems().addAll(okudugunuYazdir(yazilacakYol));
		
    	
    	
	}
	
	String[] Oku2(String yol){
		File dosya = new File(yol);
		List<String> veriler = new ArrayList<>();
		try {
			FileReader fr = new FileReader(dosya);
			BufferedReader br = new BufferedReader(fr);
			String satir = br.readLine();
			while(satir != null) {
				veriler.add(satir);
				satir = br.readLine();
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String[] veri = new String[veriler.size()]; 
		ListIterator<String> i = veriler.listIterator();
		while(i.hasNext()){	
			veri[i.nextIndex()] = i.next();
		}
		
		
		return veri;
	}
	
	String[] okudugunuYazdir(String yol) {
		String[] veri = Oku2(yol);
		
		/*
		for(int i=0; i<veri.length; i++) {
			veri[i] = veri[i].substring(0, veri[i].length()-2);
		}
			*/
		return veri;
	}
	
	void Listele(File dosya) {
		cb_gurup.getItems().clear();
        String[] dosya_list = dosya.list();
        for (int i = 0; i < dosya_list.length; i++) {
            cb_gurup.getItems().add(dosya_list[i]);
        }
	}
	void gurubunIcındekileriListele(String ad) {
		File dosya = new File(ad);
		lv_ekranUst.getItems().clear();
        String[] dosya_list = dosya.list();
        for (int i = 0; i < dosya_list.length; i++) {
            lv_ekranUst.getItems().add(dosya_list[i]);
        }
	}
	
	void OlusturKlasor(String ad) {
		File klasor99 = new File("C:\\VES\\EXEL_DATA\\"+ad);
		try {
			if(!klasor99.exists()) {
				klasor99.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	void Kontrol99(String ad) {
		File dosya99 = new File(ad);
		try {
			if(!dosya99.exists()) {
				dosya99.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}



