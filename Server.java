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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Server extends Thread {
    private static volatile boolean running;
    private ServerSocket server;
    private Socket client;
    private DataInputStream input, getTask;
    private DataOutputStream output;
    private ObjectInputStream inObj;
    private ObjectOutputStream outObj;
    private ZDatabase zdb;

    public Server() {
        input = null;
        getTask = null;
        output = null;
        inObj = null;
        zdb = null;

        running = true;
        setConnection();
    }

    public void setConnection() {
        try {
            
            server = new ServerSocket(16000);
            System.out.println("Server Socket @ 16000 port ...");
            //System.out.println("Server Not Yet Ready...");
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean establishComm(boolean last) {
        if(!last)return last;
        try {

            System.out.println("Server Online...");
            client = server.accept();
            System.out.println("Client request accepted");
            input = new DataInputStream(client.getInputStream());//set the cat output stream            
            String details = input.readUTF();
            System.out.println(details);

            switch (details) {
                case "Animals":
                    getTask = new DataInputStream(client.getInputStream());//set the op output stream 
                    String operattion = getTask.readUTF();
                    System.out.println(operattion);

                    if (operattion.equals("Save")) {
                        inObj = new ObjectInputStream(client.getInputStream());//set the respond obj

                        Animals a = (Animals) inObj.readObject();
                        zdb = new ZDatabase();
                        zdb.connect();
                        boolean saveStatus = !zdb.insertAnimals(a);
                        zdb.disconnect();

                        outObj = new ObjectOutputStream(client.getOutputStream());//set the obj output stream
                        a.setName(" ");

                        outObj.writeObject(a);
                        output = new DataOutputStream(client.getOutputStream());//set the respond msg
                        output.writeUTF("" + saveStatus);
                    } else if (operattion.equals("Search")) {
                        inObj = new ObjectInputStream(client.getInputStream());//set the respond obj

                        Animals a = (Animals) inObj.readObject();
                        zdb = new ZDatabase();
                        zdb.connect();
                        Animals foundAnimal = zdb.selectAnimals(a);
                        zdb.disconnect();

                        outObj = new ObjectOutputStream(client.getOutputStream());//set the obj output stream

                        outObj.writeObject(foundAnimal);
                        output = new DataOutputStream(client.getOutputStream());//set the respond msg
                        output.writeUTF("Finished.");

                    }else if(operattion.equals("Delete")){
                        inObj = new ObjectInputStream(client.getInputStream());//set the respond obj

                        Animals a = (Animals) inObj.readObject();
                        zdb = new ZDatabase();
                        zdb.connect();
                        boolean removeAnimal = !zdb.deleteAnimal(a);
                        zdb.disconnect();

                        outObj = new ObjectOutputStream(client.getOutputStream());//set the obj output stream
                        //a.setName("");
                        //a.setDescription("");

                        outObj.writeObject(removeAnimal);
                        output = new DataOutputStream(client.getOutputStream());//set the respond msg
                        output.writeUTF("Done!");
                    }
                    break;
                
                    case "Species":
                    getTask = new DataInputStream(client.getInputStream());//set the op output stream 
                    String operation = getTask.readUTF();
                    System.out.println(operation);

                    if (operation.equals("Save")) {
                        inObj = new ObjectInputStream(client.getInputStream());//set the respond obj

                        Species s = (Species) inObj.readObject();
                        zdb = new ZDatabase();
                        zdb.connect();
                        boolean saveStatus = !zdb.insertSpecies(s);
                        zdb.disconnect();

                        outObj = new ObjectOutputStream(client.getOutputStream());//set the obj output stream
                        s.setSname(" ");

                        outObj.writeObject(s);
                        output = new DataOutputStream(client.getOutputStream());//set the respond msg
                        output.writeUTF("" + saveStatus);
                    } else if (operation.equals("Search")) {
                        inObj = new ObjectInputStream(client.getInputStream());//set the respond obj

                        Species s = (Species) inObj.readObject();
                        zdb = new ZDatabase();
                        zdb.connect();
                        Species foundSpecies = zdb.selectSpecies(s);
                        zdb.disconnect();

                        outObj = new ObjectOutputStream(client.getOutputStream());//set the obj output stream

                        outObj.writeObject(foundSpecies);
                        output = new DataOutputStream(client.getOutputStream());//set the respond msg
                        output.writeUTF("Finished.");

                    }
                    break;
                
                   case "Users":
                    getTask = new DataInputStream(client.getInputStream());//set the op output stream 
                    String operatiion = getTask.readUTF();
                    System.out.println(operatiion);

                    while(operatiion.equals("Search")){ 
                        inObj = new ObjectInputStream(client.getInputStream());//set the respond obj

                        Users u = (Users) inObj.readObject();
                        zdb = new ZDatabase();
                        zdb.connect();
                        Users foundUser = zdb.selectUsers(u);
                        zdb.disconnect();

                        outObj = new ObjectOutputStream(client.getOutputStream());//set the obj output stream

                        outObj.writeObject(foundUser);
                        output = new DataOutputStream(client.getOutputStream());//set the respond msg
                        output.writeUTF("Finished.");

                    }
                    break;
                default:
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return last;
    }
    
    public void closeConnection() {
        try {
            if (input != null && server != null && client != null && inObj != null && outObj != null) {
                //close input streams
                input.close();
                getTask.close();
                outObj.close();
                //close output streams
                inObj.close();
                output.close();
                //close socket 
                client.close();

                server.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Server Offline...");
    }
    
    public static void main(String[] args) throws Exception {
        Server s = new Server();
        s.start();
        
    }
    
    @Override
    public void run() throws IllegalThreadStateException {
       
        while ( establishComm(running)) {           

        }
        
        closeConnection(); 
    }
    
    public  void interrupt(){
        running = !running;
        System.out.println("NB: After Two Clients Request... the Server will Shutdown....");
        try {
            
            running = !running;
            Thread.sleep(1000);
            //closeConnection();
            System.out.println();
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
