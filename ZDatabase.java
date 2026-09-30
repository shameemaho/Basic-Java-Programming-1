/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo.center;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ZDatabase {

    private Connection conn;

    public void connect() {
        try {
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=Zoo";
            String user = "Shameemah";
            String pass = "kitten";
            conn = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(ZDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertAnimals(Animals a) {
        boolean error = true;
        PreparedStatement ps = null;
        try {
            //SQL Statement
            String query = "INSERT INTO Animals(animalName, AnimalDescription)" + "VALUES(?, ?)";

            ps = conn.prepareStatement(query);
            ps.setString(1, a.getName());
            ps.setString(2, a.getDescription());

            //execute Prepared Statement
            error = ps.execute();
            //false if an error is not present
            return error;

        } catch (SQLException sqle1) {
            Logger.getLogger(ZDatabase.class.getName()).log(Level.SEVERE, null, sqle1);
        } finally {
            try {
                ps.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return error;
    }

    public boolean insertSpecies(Species s) {
        boolean error = true;
        PreparedStatement ps = null;
        try {
            //SQL Statement
            String query = "INSERT INTO Species(speciesName)" + "VALUES(?)";

            ps = conn.prepareStatement(query);
            ps.setString(1, s.getSname());

            //execute Prepared Statement
            error = ps.execute();
            //false if an error is not present
            return error;

        } catch (SQLException sqle1) {
            Logger.getLogger(ZDatabase.class.getName()).log(Level.SEVERE, null, sqle1);
        } finally {
            try {
                ps.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return error;
    }

    public Animals selectAnimals(Animals a) {
        Statement ss = null;
        Animals foundAnimal = new Animals("Not Found");
        try {
            ss = conn.createStatement();
            ResultSet rs = ss.executeQuery("SELECT animalName, animalDescription FROM Animals "
                    + "WHERE animalName LIKE '%" + a.getName() + "%' ");

            while (rs.next()) {
                System.out.print(rs.getString(1) + " "); //Second column
                System.out.print(rs.getString(2) + " "); //Third column

                foundAnimal.setName(rs.getString(1)); //Animal's name column
                foundAnimal.setDescription(rs.getString(2));  //Animal's description column
            }
        } catch (SQLException sqle2) {
            Logger.getLogger(ZDatabase.class.getName()).log(Level.SEVERE, null, sqle2);
        } finally {
            try {
                ss.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return foundAnimal;
    }

    public Species selectSpecies(Species s) {
        Statement ss = null;
        Species foundSpecies = new Species("Not Found");
        try {
            ss = conn.createStatement();
            ResultSet rs = ss.executeQuery("SELECT speciesName FROM Species "
                    + "WHERE speciesName LIKE '%" + s.getSname() + "%' ");

            while (rs.next()) {
                System.out.print(rs.getString(1) + " "); //Second column

                foundSpecies.setSname(rs.getString(1)); //Species' name column

            }
        } catch (SQLException sqle2) {
            Logger.getLogger(ZDatabase.class.getName()).log(Level.SEVERE, null, sqle2);
        } finally {
            try {
                ss.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return foundSpecies;
    }

    public Users selectUsers(Users u) {
        Statement ss = null;
        Users foundUser = new Users("Not Found");
        try {
            ss = conn.createStatement();
            ResultSet rs = ss.executeQuery("SELECT userName, userPassword FROM Users" + "WHERE userName LIKE'%" + u.getUname() + "%'");

            while (rs.next()) {
                System.out.print(rs.getString(1) + " "); //Second column
                System.out.print(rs.getString(2) + " "); //Third column

                foundUser.setUname(rs.getString(1)); //Animal's name column
                foundUser.setUpassword(rs.getString(2));  //Animal's description column
            }
        } catch (SQLException sqle2) {
            Logger.getLogger(ZDatabase.class.getName()).log(Level.SEVERE, null, sqle2);
        } finally {
            try {
                ss.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return foundUser;
    }

    public boolean deleteAnimal(Animals a) {
        boolean error = false;
        /* Statement ss = null;
        Animals foundAnimal = new Animals("Found");
        try {
            //SQL Statement
            String query = "DELETE FROM Animals WHERE animalName = '" + a.getName() + "' AND animalDescription = '" + a.getDescription() + "'";
            ss = conn.createStatement();
            

            //String query = "INSERT INTO Animals (animalName , animalDescription) VALUES ( '"+a.getName()+"' , '"+a.getDescription()+"') ";
            int rowsAffected = ss.executeUpdate(query);
            
//            boolean x = ps.execute(query);
//            if(rowsAffected>0){
//                System.out.println("Animal details successfully deleted");

            if (rowsAffected>0) {
                System.out.println("Animal details successfully deleted");

            } else {
                System.out.println("No rows affected. Try again...");
            }

            //ps.setString(1, "");
            //ps.setString(2, "");
            //execute Prepared Statement
            //error = ss.execute();
            //false if an error is not present
            //return error;
        } catch (Exception sqle1) {
            //Logger.getLogger(ZDatabase.class.getName()).log(Level.SEVERE, null, sqle1);
            System.out.println("Error 100000000: " + sqle1.getMessage());
        } finally {
            try {
                ss.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return foundAnimal;*/
        Animals removedAnimal = new Animals("Found");

        PreparedStatement ps = null;
        try {
            //SQL Statement
            String query = "DELETE FROM Animals WHERE animalName = ?  ";

            ps = conn.prepareStatement(query);
            ps.setString(1, a.getName());

            //execute Prepared Statement
             error = ps.execute();
            //false if an error is not present
            if (error) {

                System.out.println("No rows affected. Try again...");
            } else {

                System.out.println("Animal details successfully deleted");
            }
          

        } catch (SQLException sqle1) {
            Logger.getLogger(ZDatabase.class.getName()).log(Level.SEVERE, null, sqle1);
        } finally {
            try {
                ps.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return error;
    }

    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ZDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
    }
}
