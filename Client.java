/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo.center;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Client {

    private Socket socket;
    private DataOutputStream output, sendTask;
    private DataInputStream input;
    private ObjectOutputStream outObj;
    private ObjectInputStream inObj;

    public Client() {

    }

    public void connect() {
        try {
            socket = new Socket("127.0.0.1", 16000);//set the IP and the Socket of the Server

        } catch (IOException ex) {
            System.out.println("Server is Offline, Pls ask the IT Admin for more info....");
            System.out.println("Connect: " + ex.getMessage());
        }
    }

    //Inserting Animal's details into the database table
    public boolean setAnimal(Animals animalDetails) {
        String msgReceived = "false";
        boolean status;
        try {

            System.out.println("Server Accepted Client Request");
            output = new DataOutputStream(socket.getOutputStream());//set the cat output stream
            System.out.println("Details: ");
            output.writeUTF("Animals");//write the category as Exam            
            System.out.println("Animal details Sent.");
            sendTask = new DataOutputStream(socket.getOutputStream());//set the op output stream
            System.out.println("Operation: ");
            sendTask.writeUTF("Save");//write the operation as insert/save
            System.out.print("Operation Sent.");
            outObj = new ObjectOutputStream(socket.getOutputStream());//set the obj output stream   
            System.out.println("Object Stream out set");
            outObj.writeObject(animalDetails);//write exam obj into the socket via object output stream
            System.out.println("Object Sent.");
            inObj = new ObjectInputStream(socket.getInputStream());//set the respond obj
            System.out.println("Obj in.");
            Animals a = (Animals) inObj.readObject();
            System.out.println("Object Received with Name: " + a.getName());
            input = new DataInputStream(socket.getInputStream());//set the respond msg
            System.out.println("Receipt.");
            msgReceived = input.readUTF();//receipt of wether the exam was saved or not
            System.out.println("Receipt Received Status is " + msgReceived);

        } catch (IOException ex1) {
            System.out.println("Server is Offline, Pls ask the IT Admin for help....");
            System.out.println("Set Animal: " + ex1.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        status = Boolean.parseBoolean(msgReceived);//convert respond into a boolean
        return status;// return the boolean status
    }

    //capture exam's details into the time table
    public boolean setSpecies(Species speciesDetails) {
        String msgReceived = "false";
        boolean status;
        try {

            System.out.println("Server Accepted Client Request");
            output = new DataOutputStream(socket.getOutputStream());//set the cat output stream
            System.out.println("Details: ");
            output.writeUTF("Species");//write the category as Exam            
            System.out.println("Species details Sent");
            sendTask = new DataOutputStream(socket.getOutputStream());//set the op output stream
            System.out.println("Operation: ");
            sendTask.writeUTF("Save");//write the operation as insert/save
            System.out.println("Operation Sent.");
            outObj = new ObjectOutputStream(socket.getOutputStream());//set the obj output stream   
            System.out.println("Object Stream out set.");
            outObj.writeObject(speciesDetails);//write exam obj into the socket via object output stream
            System.out.print("Object Sent.");
            inObj = new ObjectInputStream(socket.getInputStream());//set the respond obj
            System.out.println("Obj in.");
            Species ex = (Species) inObj.readObject();
            System.out.println("Object Received with Code: " + ex.getSname());
            input = new DataInputStream(socket.getInputStream());//set the respond msg
            System.out.println("Receipt");
            msgReceived = input.readUTF();//receipt of wether the exam was saved or not
            System.out.println("Receipt Received Status is " + msgReceived);

        } catch (IOException ex1) {
            System.out.println("Server is Offline, Pls ask the IT Admin for help....");
            System.out.println("Set Species: " + ex1.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        status = Boolean.parseBoolean(msgReceived);//convert respond into a boolean
        return status;// return the boolean status
    }

    //search a particular exam details by code
    public Animals getAnimal(Animals animalDetails) {
        Animals animalFound = new Animals("Not Found");
        try {
            System.out.println("Server Accepted Client Request");
            output = new DataOutputStream(socket.getOutputStream());//set the cat output stream
            System.out.println("Details: ");
            output.writeUTF("Animals");//write the category as Exam            
            System.out.println("Animal details Sent");
            sendTask = new DataOutputStream(socket.getOutputStream());//set the op output stream
            System.out.println("Operation");
            sendTask.writeUTF("Search");//write the operation as insert/save
            System.out.print("Operation Sent");
            outObj = new ObjectOutputStream(socket.getOutputStream());//set the obj output stream   
            System.out.println("Object Stream out set");
            outObj.writeObject(animalDetails);//write exam obj into the socket via object output stream
            System.out.print("Object Sent");
            inObj = new ObjectInputStream(socket.getInputStream());//set the respond obj
            System.out.println("Obj in");
            animalFound = (Animals) inObj.readObject();//receve an exam object
            System.out.println("Object Received with Animal: " + animalFound.getName());
            //receiveRespondMSG.readUTF();

            input = new DataInputStream(socket.getInputStream());//set the respond msg
            System.out.println("Receipt");
            String msgReceived = input.readUTF();//receipt of wether the exam was saved or not
            System.out.println("Receipt Received Status is: " + msgReceived);
            //verified if the exam was found by checking if the code is now null or diffrent
            if (!((animalFound.getName()).equals(animalDetails.getName()))) {
                //set animal obj with not found on each field
                animalFound.setName("Not Found");
                animalFound.setDescription("Not Found");

            }
        } catch (IOException ex1) {
            System.out.println("Server is Offline, Pls ask the IT Admin for more info....");
            System.out.println("Get Animal: " + ex1.getMessage());
        } catch (ClassNotFoundException ex1) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex1);
        }
        return animalFound;//return the current instance of the object exam
    }

    //search for a species by name
    public Species getSpecies(Species speciesDetails) {
        Species speciesFound = new Species("Not Found");
        try {
            System.out.println("Server Accepted Client Request");
            output = new DataOutputStream(socket.getOutputStream());//set the cat output stream
            System.out.println("Details: ");
            output.writeUTF("Species");//write the category as Exam            
            System.out.println("species name Sent");
            sendTask = new DataOutputStream(socket.getOutputStream());//set the op output stream
            System.out.println("Operation: ");
            sendTask.writeUTF("Search");//write the operation as insert/save
            System.out.println("Operation Sent.");
            outObj = new ObjectOutputStream(socket.getOutputStream());//set the obj output stream   
            System.out.println("Object Stream out set.");
            outObj.writeObject(speciesDetails);//write exam obj into the socket via object output stream
            System.out.print("Object Sent");
            inObj = new ObjectInputStream(socket.getInputStream());//set the respond obj
            System.out.println("Obj in");
            speciesFound = (Species) inObj.readObject();//receve an exam object
            System.out.println("Object Received with Code: " + speciesFound.getSname());
            //receiveRespondMSG.readUTF();

            input = new DataInputStream(socket.getInputStream());//set the respond msg
            System.out.println("Receipt.");
            String msgReceived = input.readUTF();//receipt of wether the exam was saved or not
            System.out.println("Receipt Received Status is " + msgReceived);
            //verified if the exam was found by checking if the code is now null or diffrent
            if (!((speciesFound.getSname()).equals(speciesDetails.getSname()))) {
                //set exam obj with not found on each field
                speciesFound.setSname("Not Found");

            }
        } catch (IOException ex1) {
            System.out.println("Server is Offline, Pls ask the IT Admin for more info....");
            System.out.println("Get Species: " + ex1.getMessage());
        } catch (ClassNotFoundException ex1) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex1);
        }
        return speciesFound;//return the current instance of the object exam
    }

    //search a particular exam details by code
    public Users getUser(Users userDetails) {
        Users userFound = new Users("Not Found");
        try {
            System.out.println("Server Accepted Client Request");
            output = new DataOutputStream(socket.getOutputStream());//set the cat output stream
            System.out.println("Details");
            output.writeUTF("Users");//write the category as Exam            
            System.out.println("User details Sent");
            sendTask = new DataOutputStream(socket.getOutputStream());//set the op output stream
            System.out.println("Operation: ");
            sendTask.writeUTF("Search");//write the operation as insert/save
            System.out.print("Operation Sent.");
            outObj = new ObjectOutputStream(socket.getOutputStream());//set the obj output stream   
            System.out.println("Object Stream out set.");
            outObj.writeObject(userDetails);//write exam obj into the socket via object output stream
            System.out.print("Object Sent.");
            inObj = new ObjectInputStream(socket.getInputStream());//set the respond obj
            System.out.println("Obj in.");
            userFound = (Users) inObj.readObject();//receve an exam object
            System.out.println("Object Received with Code: " + userFound.getUname());
            //receiveRespondMSG.readUTF();

            input = new DataInputStream(socket.getInputStream());//set the respond msg
            System.out.println("Receipt.");
            String msgReceived = input.readUTF();//receipt of wether the exam was saved or not
            System.out.println("Receipt Received Status is " + msgReceived);
            //verified if the exam was found by checking if the code is now null or diffrent
            if (!((userFound.getUname()).equals(userDetails.getUname()))) {
                //set exam obj with not found on each field
                userFound.setUname("Not Found");
                userFound.setUpassword("Not Found");
            }
        } catch (IOException ex1) {
            System.out.println("Server is Offline, Pls ask the IT Admin for more info....");
            System.out.println("Get User: " + ex1.getMessage());
        } catch (ClassNotFoundException ex1) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex1);
        }
        return userFound;//return the current instance of the object exam
    }

    public boolean removeAnimal(Animals animalDetails) {
        String msgReceived = "false";
        boolean status = false;
        try {

            System.out.println("Server Accepted Client Request");
            output = new DataOutputStream(socket.getOutputStream());//set the cat output stream
            System.out.println("Details: ");
            output.writeUTF("Animals");//write the category as Exam            
            System.out.println("Animal details Sent.");
            sendTask = new DataOutputStream(socket.getOutputStream());//set the op output stream
            System.out.println("Operation: ");
            sendTask.writeUTF("Delete");//write the operation as insert/save
            System.out.print("Operation Sent.");
            outObj = new ObjectOutputStream(socket.getOutputStream());//set the obj output stream   
            System.out.println("Object Stream out set");
            outObj.writeObject(animalDetails);//write exam obj into the socket via object output stream
            System.out.println("Object Sent.");
            inObj = new ObjectInputStream(socket.getInputStream());//set the respond obj
            System.out.println("Obj in.");
            boolean isRemoved = (boolean) inObj.readObject();
            status = isRemoved;//convert respond into a boolean
            System.out.println("Object Removed: status " + isRemoved);
            input = new DataInputStream(socket.getInputStream());//set the respond msg
            System.out.println("Receipt.");
            msgReceived = input.readUTF();//receipt of wether the exam was saved or not
            System.out.println("Receipt Received Status is " + msgReceived);

        } catch (IOException ex1) {
            System.out.println("Server is Offline, Pls ask the IT Admin for help....");
            System.out.println("Set Animal: " + ex1.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;// return the boolean status
    }

    public void disconnect() {
        try {
            //close output streams
            output.close();
            sendTask.close();
            outObj.close();
            //close input streams
            inObj.close();
            input.close();
            //close socket 
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }

}
