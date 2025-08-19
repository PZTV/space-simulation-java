/**
*
* @author Omer Akkiyal | omer.akkiyal@ogr.sakarya.edu.tr
* @since 24.04.2025
* <p>
* Simulasyonda yer alan yolcularin (kisilerin) temel ozellikleri ve yasam surelerini yonetmek için kullanilir.
* </p>
*/



package uzay_araci_similasyonu;

public class Kisi {
	private String ad;
	private int yas;
	private int kalanOmur;
	private String uzayAraciAdi;
	private String varacagiGezegenAdi;
	private Gezegen varacagiGezegen;
	private boolean vardiMi;
	
	public Kisi(String ad, int yas, int kalanOmur, String uzayAraciAdi) {
		this.ad = ad;
		this.yas = yas;
		this.kalanOmur = kalanOmur;
		this.uzayAraciAdi = uzayAraciAdi;
		this.vardiMi = false;
	}
	
	public String getAd() {return ad;}
	public String getUzayAraciAdi() {return uzayAraciAdi;}
	public int getKalanOmur() {return kalanOmur;}
	public String getVaracagiGezegenAdi() {return varacagiGezegenAdi;}
	public boolean getVardiMi() {return vardiMi;}
	public void setVaracagiGezegenAdi(String varacagiGezegenAdi) {this.varacagiGezegenAdi = varacagiGezegenAdi;} 
	public void setVaracagiGezegen(Gezegen varacagiGezegen) {this.varacagiGezegen = varacagiGezegen;}
	public void setVardiMi() {this.vardiMi = true;}
	
	public void ilerletZaman() {
		if(kalanOmur>0) kalanOmur--;
	}
	public void varacagiGezegenNufusAzalt() {
		varacagiGezegen.nufusAzalt(1);
	}
	
}
