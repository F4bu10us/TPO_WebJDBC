-- tables
-- Table: User_Table
CREATE TABLE User_Table (
    idUser int  NOT NULL,
    login varchar(30)  NOT NULL,
    firstName varchar(30)  NOT NULL,
    lastName varchar(30)  NOT NULL,
    userPassword varchar(30)  NOT NULL,
    CONSTRAINT User_pk PRIMARY KEY (idUser)
);
-- Table: Resources_Table
CREATE TABLE Resource_Table (
    idResource int  NOT NULL,
    resName varchar(30)  NOT NULL,
    resContent varchar(120)  NOT NULL,
    CONSTRAINT Resource_pk PRIMARY KEY (idResource)
);
-- Table: User_Resource
CREATE TABLE User_Resource (
    idUser_Resource int  NOT NULL,
    idUser int  NOT NULL,
    idResource int  NOT NULL,
    CONSTRAINT User_Resource_pk PRIMARY KEY (idUser_Resource),
    CONSTRAINT user_fk
    FOREIGN KEY (idUser)
    REFERENCES User_Table (idUser)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE,
    CONSTRAINT resource_fk
    FOREIGN KEY (idResource)
    REFERENCES Resource_Table (idResource)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
);

-- Inserts

--INSERTING VALUES INTO User_Table

INSERT INTO User_Table VALUES
    (1, 'admin', 'Severyn', 'Lutskiv', 'admin');
    
INSERT INTO User_Table VALUES
    (2, 'abc', 'Some', 'User1', 'q1w2e3');

INSERT INTO User_Table VALUES
    (3, 'pass', 'Some', 'User2', 'word');
    
--INSERTING VALUES INTO Resource_Table

INSERT INTO Resource_Table VALUES
    (1, 'Interesting', 'Warsaw is the capital and largest city of Poland. It was founded in the 13th century');
    
INSERT INTO Resource_Table VALUES
    (2, 'Boring', 'Nothing interesting here');

INSERT INTO Resource_Table VALUES
    (3, 'Secret', 'login:admin; password:admin');
    
INSERT INTO Resource_Table VALUES
    (4, 'Favourite Color', 'Yellow');
    
INSERT INTO Resource_Table VALUES
    (5, 'Favourite Drink', 'Coffee');
    
--INSERTING VALUES INTO User_Resource
--First User
INSERT INTO User_Resource VALUES
    (1, 1, 1);
    
INSERT INTO User_Resource VALUES
    (2, 1, 2);

INSERT INTO User_Resource VALUES
    (3, 1, 3);
    
INSERT INTO User_Resource VALUES
    (4, 1, 4);
    
INSERT INTO User_Resource VALUES
    (5, 1, 5);
--Second User
INSERT INTO User_Resource VALUES
    (6, 2, 1);
    
INSERT INTO User_Resource VALUES
    (7, 2, 3);
--Third User
INSERT INTO User_Resource VALUES
    (8, 3, 2);
    
INSERT INTO User_Resource VALUES
    (9, 3, 4);
    
INSERT INTO User_Resource VALUES
    (10, 3, 5);
    
    select * from RESOURCE_TABLE;