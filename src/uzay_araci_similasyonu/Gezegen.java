/**
*
* @author Omer Akkiyal | omer.akkiyal@ogr.sakarya.edu.tr
* @since 24.04.2025
* <p>
* Gezegenlerin zaman ve nufus yonetimini saglamak.
* </p>
*/




package uzay_araci_similasyonu;


public class Gezegen {
	private String ad;
	private int gunSaatSayisi;
	private Zaman gezegenZamani;
	private int nufus;
	
	public Gezegen(String ad,int gunSaatSayisi, Zaman gezegenZamani) {
		this.ad = ad;
		this.gunSaatSayisi = gunSaatSayisi;
		this.gezegenZamani = gezegenZamani;
		this.nufus = 0;
	}
	
	
	public String getAd() {return ad;}
	public Zaman getGezegenZamani() {return gezegenZamani;}
	public int getNufus() {return nufus;}
	public int getGunSaatSayisi() {return gunSaatSayisi;}
	
	public void ilerletZaman() {
		// gunun son saatiyse
		if(gezegenZamani.getSaat() == gunSaatSayisi-1) {
			gezegenZamani.arttirSaat(true);
		}else {
			gezegenZamani.arttirSaat(false);
		}
	}
	public void nufusArttir(int insanSayisi) {
		nufus += insanSayisi; 
	}
	public void nufusAzalt(int insanSayisi) {
		nufus -= insanSayisi;
	}
}
