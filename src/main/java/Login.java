
class Login {
    private String username;
    private String password;
    private String Name;
    private String number;

    Login(String name, String username, String password, String number) {
        setName(name);
        setUsername(username);
        setPassword(password);
        setNumber(number);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}