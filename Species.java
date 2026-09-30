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
class Species implements Serializable {

    private String sName;

    public Species(String sName) {
        this.sName = sName;

    }

    public Species() {
       
    }

    public String getSname() {
        return sName;
    }

    public void setSname(String sName) {
        this.sName = sName;
    }
}
