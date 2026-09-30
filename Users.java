/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo.center;

/**
 *
 * @author User
 */
public class Users {
    private String uName;
    private String password;    
   

    public Users(String uName, String password) {
        this.uName = uName;
        this.password = password;
        
    }
    public Users(String notFound) {
        this.uName = notFound;
        this.password = notFound;
        
    }

    
    public String getUname() {
        return uName;
    }

    public void setUname(String name) {
        this.uName = uName;
    }

    public String getUpassword() {
        return password;
    }
    
    public void setUpassword(String password) {
        this.password = password;
    }
}
