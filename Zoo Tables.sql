USE Zoo
GO


--Creating Species table
CREATE TABLE Species(
  speciesID INT NOT NULL IDENTITY(1,2),
  speciesName VARCHAR(50) NOT NULL ,
  PRIMARY KEY (speciesID)

)
GO
PRINT 'Species table created'



--Creating Animal's table
CREATE TABLE Animals(
  animalID INT NOT NULL IDENTITY(1,2),
  animalName VARCHAR(50) NOT NULL,
  animalDescription VARCHAR(1000) NOT NULL,
  speciesID int ,
  PRIMARY KEY(animalID),
  FOREIGN KEY(speciesID) REFERENCES Species(speciesID)
)
GO

PRINT 'Animals table created'


--Creating Users table
CREATE TABLE Users(
  userID INT NOT NULL IDENTITY(1,3),
  userName VARCHAR(25) NOT NULL,
  userPassword VARCHAR(45) NOT NULL,
  PRIMARY KEY (userID)
) 
GO
PRINT 'Users table created'

SELECT * FROM Species
GO


SELECT * FROM Animals
GO

SELECT * FROM Animals, Species WHERE Animals.speciesID = Species.speciesID ORDER BY animalID DESC;

SELECT
  Animals.animalName AS animal_name,
  Animals.animalDescription AS animal_description,
  Species.speciesName As species_name
FROM Animals
JOIN Species ON Animals.speciesID = Species.speciesID;


USE Zoo
GO

WITH cte AS (
    SELECT 
	    animalID,
        animalName, 
        animalDescription, 
        ROW_NUMBER() OVER (
            PARTITION BY 
                animalID, 
                animalName, 
                animalDescription
            ORDER BY 
                animalID, 
                animalName, 
                animalDescription
        ) row_num
     FROM 
        Animals
)
DELETE FROM cte
WHERE row_num > 1;
GO

DROP TABLE Animals;

DROP TABLE Species;

DROP TABLE Users;