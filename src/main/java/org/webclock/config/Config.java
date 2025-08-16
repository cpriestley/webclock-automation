package org.webclock.config;

public class Config {
    private Chromedriver chromedriver;
    private Webclock webclock;
    private Credentials credentials;

    public Chromedriver getChromedriver() {
        return chromedriver;
    }

    public void setChromedriver(Chromedriver chromedriver) {
        this.chromedriver = chromedriver;
    }

    public Webclock getWebclock() {
        return webclock;
    }

    public void setWebclock(Webclock webclock) {
        this.webclock = webclock;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public static class Chromedriver {
        private String path;
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
    }

    public static class Webclock {
        private String url;
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
    }

    public static class Credentials {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
