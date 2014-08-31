package JSPChatPackage;

public class LoginBean {
    private String username;
    private String password;

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
    
    public boolean checkLogin(String username, String password){
        
        if("admin".equals(getUsername()) && "admin".equals(getPassword())){
            return true;
        }
        
        return false;
    }
    
}
