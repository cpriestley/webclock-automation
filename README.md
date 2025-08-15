# WebClock Automation

Automated login and interaction with the [ITCS WebClock](https://www.itcs-webclock.com) system using **Java**, **Selenium WebDriver**, and **Gradle**.  
This tool is designed to streamline repetitive login tasks by automatically filling in credentials and submitting the login form.

---

## ✨ Features
- Opens the WebClock login page and handles iframe navigation
- Automatically enters username and password from a YAML configuration file
- Submits the login form with Selenium WebDriver
- Supports building a **fat JAR** for easy execution on any system
- Clean separation of credentials (`credentials.yaml`) from code

---

## 📂 Project Structure
.  
├── src  
│ ├── main  
│ │ ├── java  
│ │ │ └── org/example/WebClockLogin.java  
│ │ └── resources  
│ │ └── credentials.yaml  
│ └── test  
├── build.gradle  
└── README.md  

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
2. **Configure credentials**
Create or edit src/main/resources/credentials.yaml:
```yaml
credentials:
  username: "your_username"
  password: "your_password"
```
4. **Build fat JAR**
 ```bash
./gradlew fatJar
```
▶️ Running the Automation
Run the generated fat JAR:
```bash
java -jar build/libs/webclock-automation-1.0-all.jar
```
## 🛡️ Security Notes
- Do not commit real usernames or passwords to GitHub.
- Add credentials.yaml to .gitignore so sensitive information stays local.
- If deploying in a team or CI/CD pipeline, consider injecting credentials via environment variables instead of YAML.

## 📜 License
This project is licensed under the MIT License – see the LICENSE file for details.
