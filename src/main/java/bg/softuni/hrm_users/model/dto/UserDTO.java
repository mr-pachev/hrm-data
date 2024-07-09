package bg.softuni.hrm_users.model.dto;

public class UserDTO {
    private long userId;
    private String identificationNumber;
    private String username;
    private String password;
    private String role;

    public UserDTO(long userId, String identificationNumber, String username, String password, String role) {
        this.userId = userId;
        this.identificationNumber = identificationNumber;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
