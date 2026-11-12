# Java-Socket-HTTP-Web-Sunucusu

Bu proje, herhangi bir 3. parti kütüphane veya harici çerçeve (framework) kullanılmadan, yalnızca standart Java SE API (`java.net`, `java.io`) ve Socket Programlama mimarisi kullanılarak geliştirilmiş çok iş parçacıklı (multi-threaded) basit bir HTTP web sunucusudur.

## Proje Bilgileri

* **Geliştirici:** Zekeriya Yusuf Cezlan
* **Öğrenci No:** 1250505031
* **Bölüm:** Kırklareli Üniversitesi - Yazılım Mühendisliği
* **Dinlenen Port:** `1989`
* **Protokol:** HTTP/1.1

## Özellikler

* **Saf Java Mimarisi:** Harici bağımlılık olmadan `ServerSocket` ve `Socket` sınıfları ile TCP/IP tabanlı iletişim.
* **Eşzamanlı İstek İşleme (Multi-threading):** Her gelen istemci bağlantısı ayrı bir `Thread` üzerinde işlenerek sunucunun kilitlenmesi önlenir.
* **SOLID Prensipleri:** Sunucu dinleme (`Main`) ve istemci istek/yanıt yönetimi (`RequestHandler`) sorumlulukları birbirinden ayrılmıştır.
* **Dinamik HTML/CSS Yanıtı:** Bağlanan web tarayıcısına UTF-8 karakter setiyle biçimlendirilmiş öğrenci biyografi sayfası sunulur.

## Proje Yapısı

```text
server/
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── example/
│                   ├── Main.java            # 1989 portunu dinleyen ve istemcileri kabul eden ana sınıf
│                   └── RequestHandler.java  # Gelen HTTP isteklerini işleyip HTML yanıtı döndüren sınıf
├── pom.xml
└── README.md
```

## Kurulum ve Çalıştırma

### 1. Projeyi Klonlayın
```bash
git clone [https://github.com/KULLANICI_ADIN/Java-Socket-HTTP-Web-Sunucusu.git](https://github.com/KULLANICI_ADIN/Java-Socket-HTTP-Web-Sunucusu.git)
cd Java-Socket-HTTP-Web-Sunucusu
```

### 2. Derleme ve Çalıştırma

**IDE Üzerinden (IntelliJ IDEA):**
1. Projeyi IntelliJ IDEA ile açın.
2. `src/main/java/org/example/Main.java` dosyasını çalıştırın (**Run**).

**Terminal / Komut İstemi Üzerinden:**
```bash
javac -d out src/main/java/org/example/*.java
java -cp out org.example.Main
```

### 3. Tarayıcıdan Erişim
Sunucu başlatıldıktan sonra herhangi bir web tarayıcısını açarak aşağıdaki adrese gidin:
```text
http://localhost:1989
```

## Teknik Mimari ve SOLID Yaklaşımı

* **Single Responsibility Principle (Tek Sorumluluk):** `Main` sınıfı yalnızca soket bağlantısını dinlemekten; `RequestHandler` sınıfı ise HTTP başlıklarını okumaktan ve HTML içeriğini göndermekten sorumludur.
* **Liskov Substitution & Interface Segregation:** `RequestHandler` sınıfı, Java'nın tek amaçlı standart `Runnable` arayüzünü uygulayarak `Thread` yapısına tam uyum sağlar.
* **Dependency Inversion:** `RequestHandler`, soket bağımlılığını kurucu metot (`Constructor Injection`) üzerinden dışarıdan alır.
