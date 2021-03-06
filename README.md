# TEAM2CS157A

Team name : Team 2

Members name :

- Anthony Vo
- Benjamin Liu
- Camvu Pham

Project Title : Fruit Store
___
Database Schema (DDL to create database and relations)
```
User(uID, username, password, email, isEmployee)
Fruit (fID, name, price)
Inventory(iID, fID, timeStamp, expirationDate, amount)
Order (oID, uID, timeStamp,  totalPrice, updatedAt)
OrderItem (oiID, oID, fID, amount)
Archive(oID, uID, timeStamp, totalPrice)
```

---
```
CREATE DATABASE FruitStore;
```
```
CREATE TABLE User(
	uID INT NOT NULL AUTO_INCREMENT,
	username  VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	isEmployee BOOLEAN DEFAULT FALSE,
	PRIMARY KEY (uID)
)
```
```
CREATE TABLE Fruit(
	fID INT NOT NULL AUTO_INCREMENT, 
	Name NOT NULL UNIQUE, 
	Price INT NOT NULL,
	PRIMARY KEY (fID)
)
```
```
CREATE TABLE Review(
	rID INT NOT NULL AUTO_INCREMEMNT
	fID INT NOT NULL,
	uID INT NOT NULL,
	rating INT NOT NULL DEFAULT 0,
	comment VARCHAR(1000) NOT NULL,
	ratingDate DATETIME DEFAULT NOW(),
	PRIMARY KEY (rID),
	FOREIGN KEY (fID) REFERENCES Fruid(fID),
	FOREIGN KEY (uID) REFERENCES User(uID)
)
```
```
CREATE TABLE Inventory(
	iID INT NOT NULL AUTO_INCREMENT, 
	fID INT NOT NULL, 
	timeStamp DATE, 
	expirationDate DATE, 
	Amount INT DEFAULT 0,
	PRIMARY KEY (iID),
	FOREIGN KEY (fID) REFERENCES Fruit(fID)
)
```
```
CREATE TABLE Order(
	oID INT NOT NULL AUTO_INCREMENT, 
	uID INT NOT NULL, 
	timeStamp Date,  
	totalPrice INT NOT NULL
	updatedAt NOT NULL DATE
)
```
```
CREATE TABLE OrderItem(
	oiID INT NOT NULL AUTO_INCREMENT, 
	oID INT NOT NULL, 
	fID INT NOT NULL, 
	amount INT NOT NULL
)
```
```
CREATE TABLE Archive(
	oID INT NOT NULL,
	uID INT NOT NULL,
	oTimeStamp Date,  
	oTotalPrice INT NOT NULL,
	FOREIGN KEY (oID) REFERENCES Order(oID),
	FOREIGN KEY (uID) REFERENCES User(uID)
	FOREIGN KEY (oTimeStamp) REFERENCES Order(timeStamp),
	FOREIGN KEY (oTotalPrice) REFERENCES Order(totalPrice)
)
```
___
Functional Requirements of your system in English, not necessarily in SQL. These are functions to support the business logic of your application.

1. Customer able to add rows to Orders table and specify how many they wish to purchase, kind of fruit, and how new they are based upon expiration date. 
2. User that is employee type able to add rows to Inventory table and specifying kind of fruit, expiration date, and how many in stock
3. User able to add rows to User table (sign up) and specify a unique username, unique email, password, first name, last name, and has a default column with value customer to specify the account is a customer type.   
4. User with account type employee able to change user types to employee instead of customer. 
5. Fruits that past expiration date are automatically removed from Inventory table
6. Inventory table will keep track of the amount of fruit available and when it expires
7. Users should be able to buy multiple fruit from one order
8. Users will be able to view all types of fruits
9. Users should be able to give reviews to fruits
10. Users should be able to update and delete reviews
11. Users should be able to see all reviews given to the fruit
12. Users should be able to sort the reviews based upon rating and ratingDate
13. Users should be able to sort fruits by name, price, and after clicking a fruit, sort them by expiration date. 
14. Users should be able to edit orders
15. Order table should keep track of the history of purchases
16. Users should receive a receipt or invoice after a successful order
17. System should show statistics on orders for example: most popular fruit sold, average total price of sale, numbers of fruit sold, number of orders
19.Order table will be archived.   


 

