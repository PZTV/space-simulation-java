/**
*
* @author Omer Akkiyal | omer.akkiyal@ogr.sakarya.edu.tr
* @since 24.04.2025
* <p>
* Disaridan .txt dosyalarindan veri okuyarak programin baslangic verilerini olusturmak.
* </p>
*/



package uzay_araci_similasyonu;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class DosyaOkuma {

	// Kisileri okuyan fonksiyon
	public static ArrayList<Kisi> kisileriOku(String dosyaYolu){
		ArrayList<Kisi> kisiler = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))){
			String satir;
			while((satir = br.readLine())!= null) {
				String[] parcalar = satir.split("#");
				if(parcalar.length == 4) {
					String ad = parcalar[0];
					int yas = Integer.parseInt(parcalar[1]);
					int kalanOmur = Integer.parseInt(parcalar[2]);
					String uzayAraci = parcalar[3];
					Kisi kisi = new Kisi(ad,yas,kalanOmur,uzayAraci);
					kisiler.add(kisi);
				}
			}
		} catch(Exception e) {
			System.out.println("Kisiler dosyası okunamadı." + e.getMessage());
		}
		
		return kisiler;	
	}
	
	// Araclari okuyan fonksiyon
	public static ArrayList<UzayAraci> araclariOku(String dosyaYolu){
		ArrayList<UzayAraci> araclar = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))){
			String satir;
			while((satir = br.readLine()) != null) {
				String[] parcalar = satir.split("#");
				if(parcalar.length == 5) {
					String ad = parcalar[0];
					String cikisGezegeni = parcalar[1];
					String varisGezegeni = parcalar[2];
					
					String[] tarihParca = parcalar[3].split("\\.");
					int gun = Integer.parseInt(tarihParca[0]);
					int ay = Integer.parseInt(tarihParca[1]);
					int yil = Integer.parseInt(tarihParca[2]);
					Zaman cikisTarihi = new Zaman(gun,ay,yil);
					
					int mesafeSaat = Integer.parseInt(parcalar[4]);
					
					UzayAraci uzayAraci = new UzayAraci(ad,cikisGezegeni,varisGezegeni,cikisTarihi,mesafeSaat);
					araclar.add(uzayAraci);
				}
			} 
		} catch(Exception e) {
			System.out.println("Araclar dosyasi okunamadi" + e.getMessage());
		}
		return araclar;
	}
	
	// Gezegenleri okuyan fonksiyon
	public static ArrayList<Gezegen> gezegenleriOku(String dosyaYolu){
		ArrayList<Gezegen> gezegenler = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))){
			String satir;
			while((satir = br.readLine()) != null) {
				String[] parcalar = satir.split("#");
				if(parcalar.length == 3) {
					String ad = parcalar[0];
					int gunSaatSayisi = Integer.parseInt(parcalar[1]);
					
					String[] tarihParca = parcalar[2].split("\\.");
					int gun = Integer.parseInt(tarihParca[0]);
					int ay = Integer.parseInt(tarihParca[1]);
					int yil = Integer.parseInt(tarihParca[2]);
					Zaman gezegenZamani = new Zaman(gun,ay,yil);
					
					Gezegen gezegen = new Gezegen(ad,gunSaatSayisi,gezegenZamani);
					gezegenler.add(gezegen);
				}
			}
		} catch(Exception e) {
			System.out.println("Gezegenler dosyasi okunamadi: " + e.getMessage());
		}
		
		return gezegenler;
	}
}
