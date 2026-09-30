USE Zoo
GO

-- insert some values into the Species Table
INSERT INTO Species(speciesName)
VALUES ('Filidae'),
    ('Ursidae'),
    ('Hippotamidae'),
    ('Macropodidae'),
    ('Phascolarctidae'),
    ('Equidae'), 
    ('Hominidae'), 
    ('Spheniscidae'),
    ('Dromaiidae'),
    ('Crocdilia'),
    ('Alligatoridae'),
    ('Serpentes'), 
    ('Anura'), 
    ('Salamandroidea'),
    ('Filidae'),
    ('Testudines'), 
    ('Elephantidae')
GO

-- insert values into the Animals Table
INSERT INTO Animals(animalName, animalDescription)
VALUES ('Lion', 'A large cat that is a carnivore with orange fur and black stripes on its fur'),
    ('Panda', 'A bearvlike animal that is native to china is an omnivore, has black patches of fur on its white fur'),
    ('Hippo', 'A large semi-aquatic mammal with a big body and short legs '),
    ('Kangaroo', 'a lsrdge herbivore thatbhas a strong hind legs that help with jumping as well as large tail'),
    ('Koala', 'The koala is a tree-dwelling marsupial with large furry ears, a prominent black nose, long sharp claws adapted for climbing and no tail. It has fur ranging from grey to brown above, and white below, and varies in size and colour across its range in Australia. It is an iconic Australian animal and often called the koala “bear,” although it is not a bear.'),
    ('Zebra', 'An african mammal with black or dark brown strps on its white coat, jts similar to a horse or donkey'),
    ('Gorilla', 'A large ape covered with black fur has a broad shoulders with long arms and has small ears is vegetarian as well as shy'),
    ('Penguin', 'Usually black and white flightless bird that swims has a streamlined body shape'),
    ('Emu', 'second largest bird which is also Fightless, from Austrailia'),
    ('Crocodile', 'A large carnivorous amphibious reptile with a triangle shaped snout and thick skin'),
    ('Alligator', 'A large reptile with small limbs its tail is half its total length and its got a powerful tail that is used for swimming and defense'),
    ('Snake', 'Snakes are elongated limbless carnivorous reptilesThey have no external ears no eyelids and no limbs they only have one functional lung and a long slender body  '),
    ('Frog', 'A frog has smooth moist skin and big bulging eyes its back legs are twice as long as its front legs it is an amphibian that can live in water and on land but mostly it is found in water some live underground or in trees'),
    ('Salamander', 'A small animal that looks like a lizard with smooth wet skin and lives in both land and water is an amphibian that goes back into water only to breed '),
    ('Tiger', 'A light brown orangish carnivorous large cat that belongs to a Asia and east Russia its coat has white markings with black stripes '),
    ('Turtle', 'reptiles with a hard shell that protects them like a shield, most of them live in freshwater ponds lakes or rivers turtles can also live on land and they are called tortoises They returned to the location they were born so they can lay eggs '),
    ('Elephant', 'Have long trunks used for digging and finding food as well as communication have large ears to radiate excess heat and weigh almost 6 tonsAfrican elephants both male and female have tusks but the Asian elephants only the male elephants have tusks')
GO 

--insert values into the Users table
INSERT INTO Users(userName, userPassword)
VALUES ('Shameemah', 'BTS0713')
GO
