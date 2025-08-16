# WebClock Automation

Automated login and interaction with the [ITCS WebClock](https://www.itcs-webclock.com) system using **Java**, **Selenium WebDriver**, and **Gradle**.  
This tool is designed to streamline repetitive login tasks by automatically filling in credentials and submitting the login form.

---

## ✨ Features
- Opens the WebClock login page and handles iframe navigation
- Automatically enters username and password from a YAML configuration file
- Supports ChromeDriver path and WebClock URL configuration via YAML
- Submits the login form with Selenium WebDriver
- Supports building a **fat JAR** for easy execution on any system
- Clean separation of sensitive data (`config.yaml`) from code
- Default config file (`config.yaml`) OR custom path provided at runtime

---

## 📂 Project Structure
```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── webclock
│   │   │           ├── Main.java
│   │   │           ├── automation
│   │   │           │   └── WebClockBot.java
│   │   │           └── config
│   │   │               ├── Config.java
│   │   │               └── ConfigLoader.java
│   └── test
├── build.gradle
├── config.yaml        # external config in project root, ignored by git
└── README.md
``` 

---

## 🔧 Prerequisites
- **Java 17+**
- **Gradle 8+**
- **Google Chrome** installed
- [ChromeDriver](https://chromedriver.chromium.org/) available on your system PATH  
  *(Optional: WebDriverManager can be added to auto-manage drivers)*

---

## ⚙️ Setup
1. **Clone the repository**
```bash
git clone https://github.com/your-username/webclock-automation.git
cd webclock-automation
```

2. **Configure application**
   Create a `config.yaml` in the project root:
```yaml
chromedriver:
  path: "C:\\chromedriver-win64\\chromedriver.exe"

webclock:
  url: "https://www.itcs-webclock.com/prod/4ESC01/home.cfm"

credentials:
  username: "your_username"
  password: "your_password"
```

3. **Build fat JAR**
```bash
./gradlew fatJar
```

4. **Run the Automation**
- Using default `config.yaml` in project root:
```bash
java -jar build/libs/webclock-automation-1.0-all.jar
```
- Using custom config file:
```bash
java -jar build/libs/webclock-automation-1.0-all.jar ./my-config.yaml
```

---

## 🛡️ Security Notes
- Do not commit real usernames or passwords to GitHub.
- `config.yaml` is already in `.gitignore`.
- If deploying in a team or CI/CD pipeline, consider injecting credentials via environment variables instead of YAML.

---

## 📜 License
This project is licensed under the MIT License – see the LICENSE file for details.
