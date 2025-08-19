/**
*
* @author Omer Akkiyal | omer.akkiyal@ogr.sakarya.edu.tr
* @since 24.04.2025
* <p>
* Uzay araclarinin cikis, varis ve yolculuk sureci yonetimini saglar.
* </p>
*/



package uzay_araci_similasyonu;

import java.util.ArrayList;
import java.util.Iterator;

public class UzayAraci {
	private String ad;
	private String cikisGezegeniAdi;
	private Gezegen cikisGezegeni;
	private String varisGezegeniAdi;
	private Gezegen varisGezegeni;
	private Zaman cikisTarihi;
	private Zaman varisTarihi;
	private int mesafeSaat;
	private ArrayList<Kisi> yolcular;
	private boolean imha;
	private boolean yolda;
	private boolean vardi;
	private boolean yolcularCiktiMi;
	
	public UzayAraci(String ad,String cikisGezegeniAdi,String varisGezegeniAdi,Zaman cikisTarihi, int mesafeSaat) {
		this.ad = ad;
		this.cikisGezegeniAdi = cikisGezegeniAdi;
		this.varisGezegeniAdi = varisGezegeniAdi;
		this.cikisTarihi = cikisTarihi;
		this.mesafeSaat = mesafeSaat;
		this.yolcular = new ArrayList<>();
		this.imha = false;
		this.yolda = false;
		this.vardi = false;
		this.yolcularCiktiMi = false;
	}
	
	public String getAd() {return ad;}
	public String getCikisGezegeniAdi() {return cikisGezegeniAdi;}
	public Gezegen getCikisGezegeni() {return cikisGezegeni;}
	public Zaman getCikisTarihi() {return cikisTarihi;}
	public Zaman getVarisTarihi() {return varisTarihi;}
	public String getVarisGezegeniAdi() {return varisGezegeniAdi;}
	public int getMesafeSaat() {return mesafeSaat;}
	public ArrayList<Kisi> getYolcular(){return yolcular;}
	public boolean isYolda() {return yolda;}
	public boolean isImha() {return imha;}
	public boolean isVardi() {return vardi;}
	public void setCikisGezegeni(Gezegen cikisGezegeni) {this.cikisGezegeni = cikisGezegeni;}
	public void setVarisGezegeni (Gezegen varisGezegeni) {this.varisGezegeni = varisGezegeni;}
	public void setVarisTarihi() {this.varisTarihi = Zaman.tariheSaatEkle(mesafeSaat+Zaman.ikiTarihArasiSaatiHesapla(cikisGezegeni.getGezegenZamani(), cikisTarihi, cikisGezegeni.getGunSaatSayisi()), varisGezegeni.getGezegenZamani(), varisGezegeni.getGunSaatSayisi());}
	
	public boolean imhaKontrol() {
		imha = (yolcular.size() == 0);
		return imha;
	}
	
	// Yola cikip cikmayacagini kontrol eden fonksiyon
	public boolean yolKontrol() {
		if(cikisGezegeni.getGezegenZamani().equals(cikisTarihi)) {
			yolda = true;
			if(!yolcularCiktiMi) gezegendenYolcuAzalt();
			
		} 
		else if(vardi){
			yolda = false;
		}
		return yolda;
	}
	public boolean mesafeAzalt() {
		mesafeSaat--;
		if(mesafeSaat == 0) {
			vardi = true;
			for(Kisi k: yolcular) {
				k.setVardiMi();
			}
		}
		return vardi;
	}
	public void yolcuKontrol() {
		Iterator<Kisi> iterator = yolcular.iterator();
		int olenSayisi = 0;
		while (iterator.hasNext()) {
		    Kisi k = iterator.next();
		    if (k.getKalanOmur() == 0) {
		        iterator.remove();
		        olenSayisi++;
		    }
		}
		// yolda degilse ve varmadiysa (arac cikis gezegenindeyse) olen yolculari cikis gezegeni nufusundan dus
		if(!yolda && !vardi && olenSayisi>0) cikisGezegeni.nufusAzalt(olenSayisi);
	}
	public void gezegeneYolcuAktar() {
		varisGezegeni.nufusArttir(yolcular.size());
	}
	public void gezegendenYolcuAzalt() {
		cikisGezegeni.nufusAzalt(yolcular.size());
		yolcularCiktiMi = true;
	}

}
