# MP3 Tagger 🎵

A simple, lightweight Java Swing application for editing MP3 metadata and cover art.

![App Icon](./src/main/resources/img/icon.png)

## ✨ Features

- 🏷️ Edit ID3v2 MP3 tags:
  - Title
  - Artist
  - Album
- 🖼️ Add or replace cover artwork (JPEG or PNG)
- 🔍 Preview selected cover image
- 🧩 Clean, minimal UI built with Java Swing
- ⚙️ Uses [Jaudiotagger](https://www.jthink.net/jaudiotagger/) under the hood

---

## 🚀 Getting Started

### 📦 Download

📥 [Download v1.0.0](https://github.com/astrapi69/astrapi69-mp3-tagger-ui/releases/tag/v1.0.0)

### Prerequisites

- Java 21 or higher
- Gradle 8.6+ (or use the included wrapper)

### Build and Run

```bash
./gradlew run
```

Or build a JAR:

```bash
./gradlew clean build
```

Then run:

```bash
java -jar build/libs/astrapi69-mp3-tagger-1.0.0.jar
```

* * *

📦 Dependencies
---------------

* Jaudiotagger for reading/writing MP3 metadata

* [Lombok](https://projectlombok.org/) for cleaner code (make sure your IDE supports it)


* * *

🧑‍💻 Author
------------

**Asterios Raptis** – [@astrapi69](https://github.com/astrapi69)

* * *

📄 License
----------

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

The jaudiotagger is under the GNU Lesser General Public License
