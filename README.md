# Zara Selenium Test Project

Bu proje, Zara web sitesi için Selenium WebDriver kullanarak otomatik test senaryoları içeren bir Java Maven projesidir.

## Proje Özellikleri

- **Java 11** programlama dili kullanılmıştır
- **Maven** proje yapısı
- **Selenium WebDriver 4.15.0** web otomasyon
- **JUnit 5** test framework
- **Log4J 2** loglama sistemi
- **Apache POI** Excel dosya işlemleri
- **Page Object Pattern** tasarım deseni
- **OOP** prensiplerine uygun yazılmıştır

## Test Senaryosu

Proje aşağıdaki test senaryosunu otomatik olarak gerçekleştirir:

1. www.zara.com/tr/ sitesi açılır
2. Çerezler kabul edilir
3. Kullanıcı girişi yapılır
4. Menü → Erkek → Tümünü Gör butonlarına tıklanır
5. Arama kutucuğuna "şort" kelimesi girilir (Excel'den okunur)
6. "şort" kelimesi silinir
7. Arama kutucuğuna "gömlek" kelimesi girilir (Excel'den okunur)
8. Enter tuşuna basılır
9. Sonuçlardan rastgele bir ürün seçilir
10. Ürün bilgisi ve fiyatı txt dosyasına yazılır
11. Ürün sepete eklenir
12. Ürün sayfasındaki fiyat ile sepetteki fiyat karşılaştırılır
13. Adet arttırılarak ürün adedinin 2 olduğu doğrulanır
14. Ürün sepetten silinerek sepetin boş olduğu kontrol edilir

### Konfigürasyon

`TestConfig.java` dosyasında aşağıdaki ayarları değiştirebilirsiniz:

- `BASE_URL`: Zara web sitesi URL'i
- `EMAIL`: Test kullanıcı e-posta adresi
- `PASSWORD`: Test kullanıcı şifresi
- `EXCEL_FILE_PATH`: Excel dosya yolu
- `OUTPUT_FILE_PATH`: Çıktı dosya yolu

