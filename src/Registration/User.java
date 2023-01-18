package Registration;

import java.util.Objects;

public class User {

    private int id;
    private Roles type;
    //   private Roles type;
    private String login;
    private String email;
    private String password;
    private String FirstName;
    private String LastName;
    private String Patronomic;
    private String PhoneNumber;

    public User(int id, Roles type, String login, String email, String password,String FirstName,String LastName,String Patronomic,String PhoneNumber) {
        this.id = id;
        this.type = type;
        this.login = login;
        this.email = email;
        this.password = password;
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.Patronomic=Patronomic;
        this.PhoneNumber=PhoneNumber;
    }

    public User(int id, Roles type, String login, String password) {
        this.id=id;
        this.type = type;
        this.login=login;
        this.password=password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Roles getType() {
        return type;
    }

    public void setType(Roles type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPatronomic() {
        return Patronomic;
    }

    public void setPatronomic(String patronomic) {
        Patronomic = patronomic;
    }

    public String getPhoneNomber() {
        return PhoneNumber;
    }

    public void setPhoneNomber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Roles setType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return (Objects.equals(login, user.login) || Objects.equals(email, user.email)) && Objects.equals(password, user.password);
    }

}

