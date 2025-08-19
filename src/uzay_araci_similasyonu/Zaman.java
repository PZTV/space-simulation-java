/**
*
* @author Omer Akkiyal | omer.akkiyal@ogr.sakarya.edu.tr
* @since 24.04.2025
* <p>
* Simulasyonda zamanin ilerlemesi, tarihlerin hesaplanmasi ve zamanlar arasi islemleri gerceklestirmek.
* </p>
*/



package uzay_araci_similasyonu;

public class Zaman {
	private int gun;
	private int ay;
	private int yil;
	private int saat;
	private int ayGunSayisi;
	
	
	public Zaman(int gun,int ay,int yil) {
		this.gun = gun;
		this.ay = ay;
		this.yil = yil;
		this.saat = 0;
		setAyGunSayisi();
	}

	// getler
	public int getGun() {return gun;}
	public int getAy() {return ay;}
	public int getYil() {return yil;}
	public int getSaat() {return saat;}
	public int getAyGunSayisi() {return ayGunSayisi;}
	public void setAyGunSayisi() {
		ayGunSayisi = ayGunSayisiHesapla(ay,yil);
	}
	
	public static int ayGunSayisiHesapla(int m, int y) {
	    switch (m) {
	        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
	            return 31;
	        case 4: case 6: case 9: case 11:
	            return 30;
	        case 2:
	            if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)) {
	                return 29;
	            } else {
	                return 28;
	            }
	        default:
	            return 30;
	    }
	}
	public static int ikiTarihArasiSaatiHesapla(Zaman tarih1, Zaman tarih2, int gunSaatSayisi) {
	    int toplamSaat = 0;

	    int gun1 = tarih1.getGun();
	    int ay1 = tarih1.getAy();
	    int yil1 = tarih1.getYil();
	    int saat1 = tarih1.getSaat();

	    int gun2 = tarih2.getGun();
	    int ay2 = tarih2.getAy();
	    int yil2 = tarih2.getYil();
	    int saat2 = tarih2.getSaat();

	    // Eğer tarih1 ile tarih2 eşitse sadece saat farkını al
	    if (gun1 == gun2 && ay1 == ay2 && yil1 == yil2) {
	        return saat2 - saat1;
	    }

	    // Gün farkı hesaplama için tarih1'den tarih2'ye kadar tüm günleri say
	    while (!(gun1 == gun2 && ay1 == ay2 && yil1 == yil2)) {
	        toplamSaat += gunSaatSayisi;
	        gun1++;

	        int ayGunSayisi = Zaman.ayGunSayisiHesapla(ay1, yil1);
	        if (gun1 > ayGunSayisi) {
	            gun1 = 1;
	            ay1++;
	            if (ay1 > 12) {
	                ay1 = 1;
	                yil1++;
	            }
	        }
	    }

	    // Son günün saat farkı
	    toplamSaat += saat2 - saat1;

	    return toplamSaat;
	}
	
	public static Zaman tariheSaatEkle(int ekSaat,Zaman tarih, int gunSaatSayisi) {
		int yeniSaat = tarih.getSaat() + ekSaat;
		int yeniGun = tarih.getGun();
		int yeniAy = tarih.getAy();
		int yeniYil = tarih.getYil();
		
		while(yeniSaat>= gunSaatSayisi) {
			yeniSaat -= gunSaatSayisi;
			yeniGun++;
			
			int ayGunSayisi = ayGunSayisiHesapla(yeniAy,yeniYil);
			if(yeniGun>ayGunSayisi) {
				yeniGun = 1;
				yeniAy++;
				if(yeniAy>12) {
					yeniAy = 1;
					yeniYil++;
				}
			}
		}
		Zaman yeniTarih = new Zaman(yeniGun,yeniAy,yeniYil);
		yeniTarih.saat = yeniSaat;
		return yeniTarih;
	}

	public void arttirSaat(boolean sonSaatMi) {
	    saat++;

	    if (sonSaatMi) {
	        saat = 0;

	        if (gun == ayGunSayisi) {
	            if (ay == 12) {
	                yil++;
	                ay = 1;
	            } else {
	                ay++;
	            }
	            setAyGunSayisi();
	            gun = 1;
	        } else {
	            gun++;
	        }
	    }
	}
	@Override
	public String toString() {
	    return String.format("%02d.%02d.%04d saat:%02d", gun, ay, yil, saat);
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;

	    Zaman zaman = (Zaman) obj;
	    return gun == zaman.gun &&
	           ay == zaman.ay &&
	           yil == zaman.yil;
	}
	
}

