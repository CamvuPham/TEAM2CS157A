DROP TABLE IF EXISTS User;
CREATE TABLE IF NOT EXISTS User(
    uID INT NOT NULL AUTO_INCREMENT,
    username  VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    isEmployee BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (uID)
);

DROP TABLE IF EXISTS Fruit;
CREATE TABLE IF NOT EXISTS Fruit(
    fID INT NOT NULL AUTO_INCREMENT, 
    Name VARCHAR(100) NOT NULL UNIQUE, 
    Price DOUBLE NOT NULL,
    PRIMARY KEY (fID)
);

DROP TABLE IF EXISTS Review;
CREATE TABLE IF NOT EXISTS Review(
    rID INT NOT NULL AUTO_INCREMENT,
    fID INT NOT NULL, 
    uID INT NOT NULL,
    rating INT NOT NULL DEFAULT 0,
    comment VARCHAR(1000) NOT NULL,
    ratingDate DATETIME,
    PRIMARY KEY (rID),
    FOREIGN KEY (fID) REFERENCES Fruit(fID),
    FOREIGN KEY (uID) REFERENCES User(uID)
);

DROP TABLE IF EXISTS Inventory;
CREATE TABLE IF NOT EXISTS Inventory(
    iID INT NOT NULL AUTO_INCREMENT, 
    fID INT NOT NULL, 
    tStamp TIMESTAMP, 
    expirationDate DATE, 
    Amount INT DEFAULT 0,
    PRIMARY KEY (iID),
    FOREIGN KEY (fID) REFERENCES Fruit(fID)
);


DROP TABLE IF EXISTS Orders;
CREATE TABLE IF NOT EXISTS Orders(
    oID INT NOT NULL AUTO_INCREMENT, 
    uID INT NOT NULL,
    tStamp TIMESTAMP,  
    totalPrice INT NOT NULL,
    updatedAt DATE NOT NULL,
    PRIMARY KEY (oID),
    FOREIGN KEY (uID) REFERENCES User(uID)
);

DROP TABLE IF EXISTS OrderItem;
CREATE TABLE IF NOT EXISTS OrderItem(
    oiID INT NOT NULL AUTO_INCREMENT, 
    oID INT NOT NULL, 
    fID INT NOT NULL, 
    amount INT NOT NULL,
    PRIMARY KEY (oiID),
    FOREIGN KEY (oID) REFERENCES Orders(oID),
    FOREIGN KEY (fID) REFERENCES Fruit(fID)
);

DROP TABLE IF EXISTS Archive;
CREATE TABLE IF NOT EXISTS Archive(
    oID INT NOT NULL,
    uID INT NOT NULL,
    oTimeStamp TIMESTAMP,  
    oTotalPrice INT NOT NULL,
    FOREIGN KEY (oID) REFERENCES Orders(oID),
    FOREIGN KEY (uID) REFERENCES User(uID)
);

DROP VIEW IF EXISTS orderView;
CREATE VIEW orderView AS
	SELECT User.username, OrderItem.oiID, fruit.Name, OrderItem.amount 
	FROM User
		JOIN Orders ON user.uID = Orders.uID
		JOIN orderItem ON Orders.oID = OrderItem.oID 
		JOIN fruit ON fruit.fID = OrderItem.fID
ORDER BY user.usernameName ASC, fruit.Name ASC;

DROP PROCEDURE IF EXISTS getAllUsers;
DELIMITER //
CREATE PROCEDURE getAllUsers ()
BEGIN
	SELECT * FROM User;
END //
DELIMITER ;

-- DROP TRIGGER IF EXISTS setEmployee ??? ;

DROP TRIGGER IF EXISTS deleteUserTrigger;
DELIMITER //
CREATE TRIGGER deleteUserTrigger
	BEFORE DELETE ON user FOR EACH ROW
	BEGIN
		DELETE FROM Order WHERE old.uID = uID;
--		DELETE FROM Archive WHERE old.uID = uID;
	END //
DELIMITER ;
