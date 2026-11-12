# Java Socket Tabanlı HTTP Web Sunucusu

Bu proje, herhangi bir 3. parti kütüphane veya harici web çatısı (framework) kullanılmadan, yalnızca standart Java SE (`java.net`) kütüphaneleri ve Socket programlama teknikleri kullanılarak geliştirilmiş basit bir HTTP web sunucusudur.

## Proje Bilgileri

* **Geliştirici:** Zekeriya Yusuf Cezlan
* **Öğrenci Numarası:** 1250505031
* **Çalışma Portu:** `1989`

## Projenin Amacı ve Özellikleri

* **Saf Java Mimarisi:** Harici bir sunucu bağımlılığı olmadan doğrudan `ServerSocket` ve `Socket` sınıfları üzerinden TCP bağlantıları dinlenir ve HTTP/1.1 standartlarına uygun yanıtlar üretilir.
* **Dinamik HTML Yanıtı:** Tarayıcı üzerinden `http://localhost:1989` adresine istek atıldığında istemciye geçerli HTTP başlıkları (`HTTP/1.1 200 OK`, `Content-Type: text/html; charset=UTF-8`) ile birlikte biçimlendirilmiş HTML sayfası döndürülür.
* **Sayfa İçeriği:**
  * Ana başlık (`H1`) ile geliştiricinin Adı Soyadı
  * Alt başlık (`H2`) ile Öğrenci Numarası
  * CSS (renk, yazı tipi ve düzen) ile biçimlendirilmiş kısa biyografi metni
* **SOLID Uyumluluğu:** Sunucu başlatma/bağlantı kabul etme sorumluluğu ile gelen HTTP isteklerini işleyip yanıt üretme sorumluluğu ayrı sınıflara bölünerek **Tek Sorumluluk Prensibi (Single Responsibility Principle)** uygulanmıştır.

## Proje Yapısı

* `src/main/java/org/example/Main.java`: 1989 portunu dinleyen ve gelen bağlantıları kabul eden ana sunucu sınıfı
* `src/main/java/RequestHandler.java`: İstemci isteklerini okuyan ve HTML yanıtını oluşturan işleyici sınıf
* `pom.xml`: Proje yapılandırma dosyası
* `README.md`: Proje dokümantasyonu

## Kurulum ve Çalıştırma

1. Depoyu bilgisayarınıza klonlayın veya indirin:
   `git clone [https://github.com/YusufCezlan/Java-Socket-HTTP-Web-Sunucusu.git](https://github.com/YusufCezlan/Java-Socket-HTTP-Web-Sunucusu.git)`
2. Projeyi **IntelliJ IDEA** veya tercih ettiğiniz herhangi bir Java IDE'si ile açın.
3. `src/main/java/org/example/Main.java` dosyasını çalıştırın.
4. Herhangi bir web tarayıcısını açarak adres çubuğuna `http://localhost:1989` yazarak sayfayı görüntüleyin.
