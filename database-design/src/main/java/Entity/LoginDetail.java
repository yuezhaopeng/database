package Entity;

public class LoginDetail {
    private String username;
    private String role;
    private String no;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "LoginDetail{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", no='" + no + '\'' +
                '}';
    }
}
