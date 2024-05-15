drop database wallet;
create database wallet;
use wallet;

create table Staff(
	sID int auto_increment PRIMARY KEY, 
    sUsername varchar(50) UNIQUE,
    sPassword varchar(50)
);

insert into Staff(sUsername, sPassword) values("bob", "password");
insert into Staff(sUsername, sPassword) values("michael", "password123");
insert into Staff(sUsername, sPassword) values("simon", "secret123");
insert into Staff(sUsername, sPassword) values("ana", "pass123");
insert into Staff(sUsername, sPassword) values("robert", "lion123");

create table Wallet(
    walletID int auto_increment PRIMARY KEY,
    balance double,
    seed varchar(100) UNIQUE
);

insert into Wallet(balance, seed) values(100.3,"sunset guitar elephant rainbow pillow coffee kaleidoscope ladder bubbles avocado");
insert into Wallet(balance, seed) values(64,"moon bicycle waterfall feather volcano headphones lightning cookie compass seashell");
insert into Wallet(balance, seed) values(90,"ocean telescope galaxy feather diamond mountain microphone lightning seagull lavender");
insert into Wallet(balance, seed) values(100,"jungle compass rainbow glacier headphones desert diamond lightning orchid river");

create table Customer(
	custID int auto_increment PRIMARY KEY, 
    cPassword varchar(50),
    cPin char(4),
    subscriptionType varchar(4),
    mobileNumber varchar(13),
    walletID int,
    CHECK (cast(cPin as unsigned) != 0 or cPin="0000"),
    CHECK (length(cPin) = 4),
    FOREIGN KEY (walletID) REFERENCES Wallet(walletID)
);

insert into Customer(cPassword,cPin,walletID) values("password123",1234,1);
insert into Customer(cPassword,cPin,walletID) values("testing123",2346,2);
insert into Customer(cPassword,cPin,walletID) values("walletpass",7125,3);
insert into Customer(cPassword,cPin,walletID) values("app123",5781,4);

create table Cryptocurrency(
    cryptoName varchar(20),
    walletID int,
    balance double,
    publicAddress varchar(20) UNIQUE,
    privateKey varchar(20) UNIQUE,
    PRIMARY KEY (publicAddress, privateKey),
    FOREIGN KEY (walletID) REFERENCES Wallet(walletID)
);

insert into Cryptocurrency values ("Bitcoin",1,90.3,"pubkey1","privatekey1");
insert into Cryptocurrency values ("Ethereum",1,10,"pubkey1.1","privatekey1.1");

insert into Cryptocurrency values ("Bitcoin",2,30,"pubkey2","privatekey2");
insert into Cryptocurrency values ("Ethereum",2,10,"pubkey2.2","privatekey2.2");

insert into Cryptocurrency values ("Bitcoin",3,14,"pubkey3","privatekey3.3");
insert into Cryptocurrency values ("Ethereum",3,14,"pubkey3.3","privatekey3.3");

insert into Cryptocurrency values ("Bitcoin",4,1000,"pubkey4","privatekey4");
insert into Cryptocurrency values ("Ethereum",4,1000,"pubkey4.4","privatekey4.4");



create table Transaction(
    tID int auto_increment PRIMARY KEY,
    senderPA varchar(50),
    receiverPA varchar(50),
    amount double,
    datePlaced datetime,
    cryptocurrency varchar(20),
    suspicious boolean,
	FOREIGN KEY (senderPA) REFERENCES Cryptocurrency(publicAddress),
    FOREIGN KEY (receiverPA) REFERENCES Cryptocurrency(publicAddress)
);

insert into Transaction(senderPA, receiverPA, amount, datePlaced, cryptocurrency,suspicious) values ("pubkey1","pubkey2",0.3,"2023-04-13 09:07:00","Bitcoin",false);
insert into Transaction(senderPA, receiverPA, amount, datePlaced, cryptocurrency,suspicious) values ("pubkey2.2","pubkey1",1000.6,"2023-04-13 14:07:00","Bitocin",true);
insert into Transaction(senderPA, receiverPA, amount, datePlaced, cryptocurrency,suspicious) values ("pubkey2.2","pubkey4",0.6,"2023-04-13 09:07:00","Bitcoin",false);

create table Consultant(
	consID int auto_increment PRIMARY KEY, 
	consName varchar(20),
    startingHour time,
    endingHour time
);

insert into Consultant(consName, startingHour, endingHour) values("John", "09:00:00", "17:00:00");

create table BookedSessions(
	sessionID int auto_increment PRIMARY KEY,
	consID int,
    consName varchar(20),
    custID int,
    bookingTimeStamp datetime,
    sessionDateTime datetime,
    meetingURL varchar(40),
	FOREIGN KEY (consID) REFERENCES Consultant(consID),
    FOREIGN KEY (custID) REFERENCES Customer(custID)
);

insert into BookedSessions(consID, consName, custID, bookingTimeStamp, sessionDateTime, meetingURL) values(1, "John", 1, "2023-04-13 09:23:47", "2023-04-13 09:00:00","https://meet.google.com/tpf-bwwu-xdt");
insert into BookedSessions(consID, consName, custID, bookingTimeStamp, sessionDateTime, meetingURL) values(1, "John", 1, "2023-04-13 09:07:23", "2023-04-13 11:00:00","https://meet.google.com/tpf-bwwu-xdt");
insert into BookedSessions(consID, consName, custID, bookingTimeStamp, sessionDateTime, meetingURL) values(1, "John", 1, "2023-04-07 10:04:12", "2023-04-13 10:00:00","https://meet.google.com/tpf-bwwu-xdt");