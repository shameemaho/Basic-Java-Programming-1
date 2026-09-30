USE master
GO

--I've created the database
CREATE DATABASE Zoo
ON PRIMARY --Primary file group
  ( NAME = 'Zoo_data',
    FILENAME = 'C:\Projects\Zoo project\Zoo_data.mdf',
	SIZE = 10MB,
	FILEGROWTH = 5%
)
LOG ON --Log file group
  ( NAME = 'Zoo_log',
    FILENAME = 'C:\Projects\Zoo project\Zoo_log.ldf',
	SIZE = 15MB,
	FILEGROWTH = 5%
)
GO


















