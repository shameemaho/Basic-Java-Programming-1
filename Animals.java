/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo.center;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Animals implements Serializable {
    private String name;
    private String description;    
   

    public Animals(String name, String description) {
        this.name = name;
        this.description = description;
        
    }
    public Animals(String notFound) {
        this.name = notFound;
        this.description = notFound;
        
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
