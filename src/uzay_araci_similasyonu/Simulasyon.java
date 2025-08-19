/**
*
* @author Omer Akkiyal | omer.akkiyal@ogr.sakarya.edu.tr
* @since 24.04.2025
* <p>
* Tum simulasyonun ana akisini ve kontrolunu saglar.
* </p>
*/


package uzay_araci_similasyonu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Simulasyon {
	public static boolean tumAraclarVardi(ArrayList<UzayAraci> araclar) {
	    for (UzayAraci ua : araclar) {
	        if (!ua.isImha() && !ua.isVardi()) return false;
	    }
	    return true;
	}
	public static void ekraniTemizle(boolean windowsMu) {
	    try {
	        if (windowsMu) {
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        } else {
	            System.out.print("\033[H\033[2J");
	            System.out.flush();
	        }
	    } catch (Exception e) {
	        System.out.println("Ekran temizlenemedi!");
	    }
	}

	public static void yazdirDurum(ArrayList<Gezegen> gezegenler, ArrayList<UzayAraci> uzayAraclari) {
	    System.out.println("Gezegenler:");
	    final int GEZEGEN_PER_ROW = 5;
	    
	    for (int i = 0; i < gezegenler.size(); i += GEZEGEN_PER_ROW) {
	        // Gezegen isimleri
	        System.out.print("\t");
	        for (int j = i; j < i + GEZEGEN_PER_ROW && j < gezegenler.size(); j++) {
	            System.out.print("--- " + gezegenler.get(j).getAd() + " ---\t");
	        }
	        System.out.println();
	        
	        // Gezegen tarihleri
	        System.out.print("Tarih\t");
	        for (int j = i; j < i + GEZEGEN_PER_ROW && j < gezegenler.size(); j++) {
	            Zaman z = gezegenler.get(j).getGezegenZamani();
	            System.out.print(String.format("%02d.%02d.%04d\t", z.getGun(), z.getAy(), z.getYil()));
	        }
	        System.out.println();
	        
	        // Gezegen nüfusları
	        System.out.print("Nüfus\t");
	        for (int j = i; j < i + GEZEGEN_PER_ROW && j < gezegenler.size(); j++) {
	            System.out.print(gezegenler.get(j).getNufus() + "\t\t");
	        }
	        System.out.println("\n");
	    }
	    
	    System.out.println("Uzay Araçları:");
	    System.out.println("Araç Adı\tDurum\t\tÇıkış\tVarış\tHedefe Kalan Saat\tHedefe Varacağı Tarih");

	    for (UzayAraci ua : uzayAraclari) {
	        String durum = ua.isImha() ? "İMHA" : ua.isVardi() ? "Vardı" : ua.isYolda() ? "Yolda" : "Bekliyor";
	        String kalanSaat = ua.isImha() ? "--" : String.valueOf(ua.getMesafeSaat());
	        String varisTarih;

	        if (ua.isImha()) {
	            varisTarih = "--";
	        } else {
	            Zaman vt = ua.getVarisTarihi();
	            varisTarih = String.format("%02d.%02d.%04d", vt.getGun(), vt.getAy(), vt.getYil());
	        }

	        System.out.printf("%-10s\t%-10s\t%s\t%s\t%-20s\t%s\n",
	                ua.getAd(), durum, ua.getCikisGezegeniAdi(), ua.getVarisGezegeniAdi(), kalanSaat, varisTarih);
	    }
	    System.out.println("\n---------------------------------------------\n");
	    try {
	        Thread.sleep(0);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}
	
	
	public static void main(String[] args) {
		long baslangicZamani = System.currentTimeMillis();
		boolean windowsMu = System.getProperty("os.name").contains("Windows");
		
		int iterasyonSay = 0;
		ArrayList<Kisi> kisiler = DosyaOkuma.kisileriOku("Kisiler.txt");
		ArrayList<UzayAraci> uzayAraclari= DosyaOkuma.araclariOku("Araclar.txt");
		ArrayList<Gezegen> gezegenler = DosyaOkuma.gezegenleriOku("Gezegenler.txt");
		
		// Gezegen adlari ve zamanlarindan olusan anahtar-deger ciftleri
		Map<String,Zaman> gezegenZamanlari = new HashMap<>();
		for(Gezegen g: gezegenler) {
			gezegenZamanlari.put(g.getAd(), g.getGezegenZamani());
		}
		
		// Kisilerin ait oldugu uzay araclarina eklenmesi
		for(Kisi k: kisiler) {
			for(UzayAraci ua: uzayAraclari) {
				if(ua.getAd().equals(k.getUzayAraciAdi())) {
					ua.getYolcular().add(k);
					k.setVaracagiGezegenAdi(ua.getVarisGezegeniAdi());
					break;
				}
			}
		}
		
		
		// uzay araclarinin cikis gezegenlerinin atanmasi ve cikis gezegenlerinin nufusunun atanmasi
		for(UzayAraci ua: uzayAraclari) {
			for(Gezegen g: gezegenler) {
				if(ua.getCikisGezegeniAdi().equals(g.getAd())) {
					g.nufusArttir(ua.getYolcular().size());
					ua.setCikisGezegeni(g);
					break;
				}
			}
		}
		
		// uzay araclarinin varis gezegenlerinin ve varis tarihlerinin atanmasi
		for(UzayAraci ua: uzayAraclari) {
			for(Gezegen g: gezegenler) {
				if(ua.getVarisGezegeniAdi().equals(g.getAd())) {
					ua.setVarisGezegeni(g);
					ua.setVarisTarihi();
					break;
				}
			}
		}
		
		// kisilerin varacagi gezegenin atanmasi
		for(Kisi k: kisiler) {
			for(Gezegen g: gezegenler) {
				if(k.getVaracagiGezegenAdi().equals(g.getAd())) {
					k.setVaracagiGezegen(g);
				}
			}
		}
		

		while(!tumAraclarVardi(uzayAraclari)) {
			ekraniTemizle(windowsMu);
			yazdirDurum(gezegenler,uzayAraclari);
			System.out.println("\n Program basladigindan bu yana simulasyonda gecen saat:"+iterasyonSay+"\n");
			iterasyonSay++;
			// kisiler icin zamani ilerlet(kalan omurleri azalt)
			Iterator<Kisi> iterator = kisiler.iterator();
			while (iterator.hasNext()) {
			    Kisi k = iterator.next();
			    k.ilerletZaman();
			    if (k.getKalanOmur() == 0) {
			    	if(k.getVardiMi()) {
			    		k.varacagiGezegenNufusAzalt();
			    	}
			        iterator.remove();
			    }
			}
			
			// gezegenler icin zamani ilerlet
			for(Gezegen g: gezegenler) {
				g.ilerletZaman();
			}
		
			// Uzay Araclari icin saatlik kontroller
			for(UzayAraci ua: uzayAraclari) {
				ua.yolcuKontrol();
				if(!ua.imhaKontrol()) {
					// yoldaysa
					if(ua.yolKontrol()) {
						//vardiysa
						if(ua.mesafeAzalt()) {
							ua.gezegeneYolcuAktar();
							
						}
					}					
				}
			}
		}
		long bitisZamani = System.currentTimeMillis();
		long gecenSure = bitisZamani - baslangicZamani;
		ekraniTemizle(windowsMu);
		yazdirDurum(gezegenler,uzayAraclari);
		System.out.println("\n Program basladigindan bu yana simulasyon icinde gecen saat:"+iterasyonSay+"\n");
        System.out.println("\n Program calisma suresi: " + gecenSure + " milisaniye");
        System.out.println("\n Program calisma suresi: " + (gecenSure / 1000.0) + " saniye");
		
	}
	
}


