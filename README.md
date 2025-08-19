# 🚀 Uzay Seyahat Simulasyonu (Java, Eclipse)

Bu proje, **Veri Yapıları dersi** kapsamında geliştirilmiş bir **Java konsol uygulamasıdır**. Uygulama; **gezegenler, uzay araçları ve kişiler** arasındaki yolculukları simüle eder. Program tamamen **nesne yönelimli (OOP)** prensiplerle yazılmıştır.  

## 📌 Genel Açıklama
- Yolcular **Kisiler.txt** dosyasından okunur.  
  - Format: `isim#yas#kalan_omur#bulundugu_uzay_aracı_adi`  
- Uzay araçları **Araclar.txt** dosyasından okunur.  
  - Format: `AracAdi#CikisGezegeni#VarisGezegeni#CikisTarihi#MesafeSaat`  
- Gezegenler **Gezegenler.txt** dosyasından okunur.  
  - Format: `GezegenAdi#GunSaat#Tarih`  

Program başladığında:  
- Tüm gezegenlerde saat **00:00**’dan başlar.  
- Uzay araçları çıkış tarihlerinde hareket eder.  
- Yolculuk boyunca her iterasyon **1 saat** ilerleme anlamına gelir.  
- Araçtaki tüm yolcular ölürse araç **İMHA** olarak işaretlenir.  
- Araç yolda iken yolcular hiçbir gezegen nüfusuna dahil edilmez.  
- Hedefe vardığında yolcular varış gezegeninin nüfusuna eklenir.  
- Tüm araçlar varış noktasına ulaştığında simülasyon sonlanır.  

## 🏗️ Sınıf Hiyerarşisi
- **Zaman** → Tarih ve saat işlemleri  
- **Kişi** → Ad, yaş, kalan ömür  
- **UzayAraci** → Yolcular, kalkış-varış bilgileri, imha durumu  
- **Gezegen** → Gün uzunluğu ve tarih bilgileri  
- **Simülasyon** → Ana akışı yöneten sınıf  
- **DosyaOkuma** → Dosyalardan verileri okuyan sınıf  

## 📂 Proje Yapısı
- `src/` → Kaynak dosyalar (.java)  
- `dist/` → Çalıştırılabilir `.jar` dosyası  
- `doc/` → Rapor (PDF)  
- `Kisiler.txt` → Kişi verileri (isim, yaş, kalan ömür, bulunduğu araç)  
- `Araclar.txt` → Uzay aracı verileri (çıkış, varış, tarih, mesafe)  
- `Gezegenler.txt` → Gezegen verileri (isim, gün uzunluğu, tarih)  
- `README.md` → Proje hakkında açıklamalar  
