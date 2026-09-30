Basic Java Programming Project 1 Documentation
Zoo Project Documentation

In this project there is a: 
A client application. 
A server application that runs the client applications and can be read from many machines.
And a database that has three tables, which are stored on the server. 

The client application has two GUIs. The first GUI is where a user enters an animal's name or species to search for it. The user is able to enter a part of the name of the animal they are searching for and get what they are looking for. For example, if the user is looking for a Salamander through the Zoo’s search page, then they type “Sal”, any animal present in the zoo with “Sal” including the Salamander’s information will be displayed to the user in the Name and Description fields. On the Search page, when the user closes the application, they’re given the option to minimise, exit or cancel the task.
The second GUI is an administrator GUI. The zoo admin is the only one who will use this interface and they will do so with a username and a password that only they know. All functions of the client application are in a menu on the search page and the admin page. The Zoo Admin will also be able to search, delete and insert information from the database.
The server has a start button and a stop button. These are present on the IT administrator page. The start button starts the server, and the stop button shuts the server down separately. The admin is the only one in charge of switching the server on and off. Regular Expressions are used to check that valid information was entered. The server can take multiple client connections and only the server will request and send information to and from the database directly, by obtaining requests from the client. The product will be sent to the client for display to the user. 

The database has three tables and Microsoft SQL Server was used to create this database. The first table is the Species table which has a speciesId as the primary key field and a speciesName. The second table has an animalId as the primary key field, animalName, description and lastly a speciesId as the Foreign Key field. The third table is the User table this has a userId as a primary key field, username and password. The user will be able to insert data into both tables but will only be able to delete from the Animal table, not the Species table.
Author: Shameemah Omar
How to run:
Install Netbeans IDE 
Version: NetBeans IDE 8.2
Install Microsoft JDBC Driver
Version: mysql-connector-java-8.0.27
