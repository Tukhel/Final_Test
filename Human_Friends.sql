DROP DATABASE Human_Friends;
CREATE DATABASE Human_Friends;

USE Human_Friends;

DROP TABLE Animals;
CREATE TABLE Animals
(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
animal_class VARCHAR(45)
);

DROP TABLE Pets;
CREATE TABLE Pets
(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name_pets VARCHAR(45) NOT NULL,
type_pets VARCHAR(20) NOT NULL,
birthDate DATE NOT NULL,
commands VARCHAR(255),
id_class INT NOT NULL,
FOREIGN KEY (id_class) REFERENCES Animals (id)
);

DROP TABLE Pack_Animals;
CREATE TABLE Pack_Animals
(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name_pack_animal VARCHAR(45) NOT NULL,
type_pack_animal VARCHAR(20) NOT NULL,
birthDate DATE NOT NULL,
commands VARCHAR(255),
id_class INT NOT NULL,
FOREIGN KEY (id_class) REFERENCES Animals (id)
);

INSERT INTO Animals (animal_class)
VALUES
('Pets'),
('Pack_Animals');

INSERT INTO Pets (name_pets, type_pets, birthDate, commands, id_class)
VALUES
('Fido', 'Dog', '2020-01-01', 'Sit, Stay, Fetch', 1), 
('Whiskers', 'Cat', '2019-05-15', 'Sit, Pounce', 1),
('Hammy', 'Hamster', '2021-03-10', 'Roll, Hide', 1),
('Buddy', 'Dog', '2018-12-10', 'Sit, Paw, Bark', 1),
('Smudge', 'Cat', '2020-02-20', 'Sit, Pounce, Scratch', 1),
('Peanut', 'Hamster', '2021-08-01', 'Roll, Spin', 1),
('Bella', 'Dog', '2019-11-11', 'Sit, Stay, Roll', 1),
('Oliver', 'Cat', '2020-06-30', 'Meow, Scratch, Jump', 1);




INSERT INTO Pack_Animals (name_pack_animal, type_pack_animal, birthDate, commands, id_class)
VALUES
('Thunder', 'Horse', '2015-07-21', 'Trot, Canter, Gallop', 2), 
('Sandy', 'Camel', '2016-11-03', 'Walk, Carry Load', 2),
('Eeyore', 'Donkey', '2017-09-18', 'Walk, Carry Load, Bray', 2),
('Storm', 'Horse', '2014-05-05', 'Trot, Canter', 2),
('Dune', 'Camel', '2018-12-12', 'Walk, Sit', 2),
('Burro', 'Donkey', '2019-01-23', 'Walk, Bray, Kick', 2),
('Blaze', 'Horse', '2016-02-29', 'Trot, Jump, Gallop', 2),
('Sahara', 'Camel', '2015-08-14', 'Walk, Run', 2);

DROP TABLE Dogs;
CREATE TABLE Dogs
(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name_pets VARCHAR(45) NOT NULL,
breed VARCHAR(45),
color VARCHAR(20) NOT NULL,
id_pet INT NOT NULL,
FOREIGN KEY (id_pet) REFERENCES Pets (id)
);

DROP TABLE Cats;
CREATE TABLE Cats
(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name_pets VARCHAR(45) NOT NULL,
breed VARCHAR(45),
color VARCHAR(20) NOT NULL,
id_pet INT NOT NULL,
FOREIGN KEY (id_pet) REFERENCES Pets (id)
);

DROP TABLE Hamsters;
CREATE TABLE Hamsters
(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name_pets VARCHAR(45) NOT NULL,
breed VARCHAR(45),
age INT NOT NULL,
id_pet INT NOT NULL,
FOREIGN KEY (id_pet) REFERENCES Pets (id)
);

DROP TABLE Horses;
CREATE TABLE Horses
(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name_pack_animal VARCHAR(45) NOT NULL,
breed VARCHAR(45),
color VARCHAR(20) NOT NULL,
id_pack_animal INT NOT NULL,
FOREIGN KEY (id_pack_animal) REFERENCES Pack_Animals (id)
);

DROP TABLE Camels;
CREATE TABLE Camels
(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name_pack_animal VARCHAR(45) NOT NULL,
breed VARCHAR(45),
hump INT,
id_pack_animal INT NOT NULL,
FOREIGN KEY (id_pack_animal) REFERENCES Pack_Animals (id)
);

DROP TABLE Donkeys;
CREATE TABLE Donkeys
(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name_pack_animal VARCHAR(45) NOT NULL,
breed VARCHAR(45),
height INT NOT NULL,
id_pack_animal INT NOT NULL,
FOREIGN KEY (id_pack_animal) REFERENCES Pack_Animals (id)
);

INSERT INTO Dogs (name_pets, breed, color, id_pet)
VALUES
('Fido', 'pug', 'black', 1),
('Buddy', 'boxer', 'pale yellow', 4),
('Bella', 'corgi', 'ginger', 7);

INSERT INTO Cats (name_pets, breed, color, id_pet)
VALUES
('Whiskers', 'bengal', 'spotted', 2),
('Smudge', 'himalayan', 'white', 5),
('Oliver', 'maine coon', 'grey', 8);

INSERT INTO Hamsters (name_pets, breed, age, id_pet)
VALUES
('Hammy', 'Djungarian', 3, 3),
('Peanut', 'Syrian', 2, 6);

INSERT INTO Horses (name_pack_animal, breed, color, id_pack_animal)
VALUES
('Thunder', 'don', 'black', 1),
('Storm', 'mustang', 'white', 4),
('Blaze','breton', 'grey', 7);

INSERT INTO Camels (name_pack_animal, breed, hump, id_pack_animal)
VALUES
('Sandy', 'arabian', 1, 2),
('Dune', 'bactrian', 2, 5),
('Sahara', 'wild', 2, 8);

INSERT INTO Donkeys (name_pack_animal, breed, height, id_pack_animal)
VALUES
('Eeyore', 'cotentin', 150, 3),
('Burro', 'amiatina', 100, 6);

DROP TABLE Camels;

SELECT * FROM Horses
UNION
SELECT * FROM Donkeys;

DROP TABLE Young_Animals;
CREATE TABLE Young_Animals
(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name_animal VARCHAR(45) NOT NULL,
age VARCHAR(45) NOT NULL,
type_animal VARCHAR(20) NOT NULL
);

INSERT INTO Young_Animals (name_animal, age, type_animal)
SELECT name_pets,
CONCAT(TIMESTAMPDIFF(YEAR, birthDate, CURDATE()), ' years, ',
TIMESTAMPDIFF(MONTH, birthDate, CURDATE()) % 12, ' months') AS age,
type_pets
FROM Pets
WHERE TIMESTAMPDIFF(YEAR, birthDate, CURDATE()) BETWEEN 1 AND 3;

INSERT INTO Young_Animals (name_animal, age, type_animal)
SELECT name_pack_animal,
CONCAT(TIMESTAMPDIFF(YEAR, birthDate, CURDATE()), ' years, ',
TIMESTAMPDIFF(MONTH, birthDate, CURDATE()) % 12, ' months') AS age,
type_pack_animal
FROM Pack_Animals
WHERE TIMESTAMPDIFF(YEAR, birthDate, CURDATE()) BETWEEN 1 AND 3;

SELECT * FROM Young_Animals;

SELECT * FROM Pets
UNION ALL
SELECT * FROM Pack_Animals;

SELECT d.name_pets AS name_animal, breed, id_pet AS id_animal, Pets.id_class
FROM Dogs d
JOIN Pets ON d.id_pet = Pets.id
UNION ALL
SELECT c.name_pets AS name_animal, breed, id_pet AS id_animal, Pets.id_class
FROM Cats c
JOIN Pets ON c.id_pet = Pets.id
UNION ALL
SELECT h.name_pets AS name_animal, breed, id_pet AS id_animal, Pets.id_class
FROM Hamsters h
JOIN Pets ON h.id_pet = Pets.id
UNION ALL
SELECT hor.name_pack_animal AS name_animal, breed, id_pack_animal AS id_animal, Pack_animals.id_class
FROM Horses hor
JOIN Pack_Animals ON hor.id_pack_animal = Pack_Animals.id
UNION ALL
SELECT cam.name_pack_animal AS name_animal, breed, id_pack_animal AS id_animal, Pack_animals.id_class
FROM Camels cam
JOIN Pack_Animals ON cam.id_pack_animal = Pack_Animals.id
UNION ALL
SELECT don.name_pack_animal AS name_animal, breed, id_pack_animal AS id_animal, Pack_animals.id_class
FROM Donkeys don
JOIN Pack_Animals ON don.id_pack_animal = Pack_Animals.id;