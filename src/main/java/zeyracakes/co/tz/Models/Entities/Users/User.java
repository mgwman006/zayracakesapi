package zeyracakes.co.tz.Models.Entities.Users;

import jakarta.persistence.*;
import zeyracakes.co.tz.Common.Enums.UserType;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private UserType userType;
    private String email;
    private String phoneNumber;
    private String passWord;

    public User() {
    }

    public User(UserType userType, String email, String passWord, String phoneNumber) {
        this.userType = userType;
        this.email = email;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
