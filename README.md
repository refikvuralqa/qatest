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

## Proje Yapısı

```
src/
├── main/java/com/zara/test/
│   ├── config/
│   │   └── TestConfig.java          # Test konfigürasyonu
│   ├── pages/
│   │   ├── BasePage.java            # Temel sayfa sınıfı
│   │   ├── HomePage.java            # Ana sayfa
│   │   ├── SearchPage.java          # Arama sayfası
│   │   ├── ProductPage.java         # Ürün sayfası
│   │   └── CartPage.java            # Sepet sayfası
│   └── utils/
│       ├── ExcelReader.java         # Excel okuma işlemleri
│       └── FileWriter.java          # Dosya yazma işlemleri
├── main/resources/
│   └── log4j2.xml                  # Log4J konfigürasyonu
└── test/java/com/zara/test/
    └── ZaraTest.java                # Ana test sınıfı
```

## Kurulum

### Gereksinimler

- Java 11 veya üzeri
- Maven 3.6 veya üzeri
- Chrome tarayıcısı

### Adımlar

1. Projeyi klonlayın:
```bash
git clone <repository-url>
cd zara-selenium-test
```

2. Maven dependencies'leri indirin:
```bash
mvn clean install
```

3. Excel test verisi dosyasını oluşturun:
   - `src/test/resources/` klasöründe `testdata.xlsx` dosyası oluşturun
   - A1 hücresine "şort" yazın
   - B1 hücresine "gömlek" yazın

## Kullanım

### Test Çalıştırma

```bash
# Tüm testleri çalıştır
mvn test

# Belirli test sınıfını çalıştır
mvn test -Dtest=ZaraTest

# IDE üzerinden çalıştır
# ZaraTest.java dosyasını açın ve test metodunu çalıştırın
```

### Konfigürasyon

`TestConfig.java` dosyasında aşağıdaki ayarları değiştirebilirsiniz:

- `BASE_URL`: Zara web sitesi URL'i
- `EMAIL`: Test kullanıcı e-posta adresi
- `PASSWORD`: Test kullanıcı şifresi
- `EXCEL_FILE_PATH`: Excel dosya yolu
- `OUTPUT_FILE_PATH`: Çıktı dosya yolu

## Loglama

Proje Log4J2 kullanarak kapsamlı loglama yapar:

- Console çıktısı
- `logs/zara-test.log` dosyasına log kayıtları

## Hata Ayıklama

### Yaygın Sorunlar

1. **ChromeDriver hatası**: WebDriverManager otomatik olarak uygun driver'ı indirir
2. **Element bulunamadı**: Sayfa yüklenme sürelerini kontrol edin
3. **Excel okuma hatası**: Dosya yolunu ve formatını kontrol edin

### Debug Modu

Log4J seviyesini `debug` olarak ayarlayarak detaylı loglar alabilirsiniz:

```xml
<Logger name="com.zara.test" level="debug" additivity="false">
```

## Katkıda Bulunma

1. Fork yapın
2. Feature branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Commit yapın (`git commit -m 'Add amazing feature'`)
4. Push yapın (`git push origin feature/amazing-feature`)
5. Pull Request oluşturun

## Lisans

Bu proje MIT lisansı altında lisanslanmıştır.

## İletişim

Proje ile ilgili sorularınız için issue açabilirsiniz.

## Notlar

- Test çalıştırılmadan önce internet bağlantınızın stabil olduğundan emin olun
- Zara web sitesi yapısı değişirse locator'ları güncellemeniz gerekebilir
- Test verilerini (e-posta, şifre) kendi hesap bilgilerinizle değiştirin
