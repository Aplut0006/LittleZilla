package app.littlezilla.littlezilla.Models;

public class RegisterModel {
    String username,email,password,phone;

    public RegisterModel(String username, String email, String password, String phoneno) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public RegisterModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneno() {
        return phone;
    }

    public void setPhoneno(String phoneno) {
        this.phone= phoneno;
    }
}
