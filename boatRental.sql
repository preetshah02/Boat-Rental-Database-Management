DROP DATABASE IF EXISTS boatRental;
CREATE DATABASE boatRental;
USE boatRental;

drop table if exists user;
drop table if exists store;
drop table if exists boat;
drop table if exists rental;
drop table if exists rate;
drop table if exists rentalArchive;

create table user (
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    driversLicense varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    admin boolean default false,
    primary key(username)
);

create table rate(
    boatType varchar(255) not null,
    price float not null,
    primary key(boatType)
);

create table boat(
    boatID int auto_increment not null,
    boatType char(255),
    make char(255) not null,
    model varchar(255) not null,
    primary key(boatID),
    foreign key(boatType)
        references rate(boatType)
        ON DELETE SET NULL
);

create table store (
    storeID varchar(255) not null,
    boatID int not null,
    foreign key(boatID)
            references boat(boatID)
            ON DELETE CASCADE
);

create table rental(
    username varchar(50),
    boatID int,
    returnDate date not null,
    boatReturned boolean default false,
    updatedOn timestamp not null default current_timestamp on update current_timestamp,
    primary key(boatID),
    foreign key(username)
      references user(username)
      ON DELETE CASCADE,
    foreign key(boatID)
        references boat(boatID)
        ON delete cascade
);


create table rentalArchive(
    username varchar(50),
    boatID int not null,
    returnDate date not null,
    boatReturned boolean default false,
    updatedOn timestamp not null
);

drop procedure if exists spGetUserBoatAndPrice;
create procedure spGetUserBoatAndPrice 
(UN varchar(255))
select * 
from rate
where boatType in 
    (select boatType from boat where boatID in
        (select boatID from rental where username = UN));
        
DROP PROCEDURE
IF EXISTS archive;
delimiter //
CREATE PROCEDURE
archive(IN updatedOn DATE) 
begin 
insert into rentalArchive 
(select * from rental where rental.updatedOn <= updatedOn);
delete from rental where rental.updatedOn <= updatedOn;
end //
delimiter ;


DROP TRIGGER IF EXISTS DeleteBoat;
delimiter //
CREATE TRIGGER DeleteBoat
AFTER DELETE ON boat
FOR EACH ROW
BEGIN
delete from store where boatID = Old.boatID;
END//
delimiter ;

DROP TRIGGER IF EXISTS AddBoat;
delimiter //
CREATE TRIGGER AddBoat
AFTER INSERT ON boat
FOR EACH ROW
BEGIN
INSERT into store values ('100', New.boatID);
END//
delimiter ;


INSERT INTO user VALUES('Cristiano', 'Ronaldo', 'B0002100', 'CR7', '777', false);
INSERT INTO user VALUES ('Lionel', 'Messi', 'A0003144', 'LM10', '101010', false);
INSERT INTO user VALUES ('Adam', 'Doe', 'C0005104', 'adamDoe', 'adoe', false);

INSERT INTO user VALUES('Rebecca', 'Johnson', 'B0002112', 'rebeccaJohnson', 'rebecca123', true);
INSERT INTO user VALUES ('Klaus', 'Mickelson', 'A0007237', 'klausMickelson', 'klaus123', false);
INSERT INTO user VALUES ('James', 'Smith', 'C0002391', 'jamesSmith', 'james123', false);
insert into user VALUES('Mary', 'Copper', 'B0001242', 'maryCopper', 'mary123', true);
INSERT INTO user VALUES ('Elijah', 'Mickelson', 'A0001639', 'elijahMickelson', 'elijah123', false);
INSERT INTO user VALUES ('Robert', 'Doe', 'C0003750', 'robertDoe', 'robert123', false);


INSERT INTO rate VALUES ('Pontoon', 500);
INSERT INTO rate VALUES ('Jet Ski', 350);
INSERT INTO rate VALUES ('Fishing', 500);
INSERT INTO rate VALUES ('Sailboat', 1500);
INSERT INTO rate VALUES ('Yacht', 10000);

INSERT INTO boat VALUES (122, 'Pontoon', 'Bennington', '22SSX'); 
INSERT INTO boat VALUES (123, 'Jet Ski', 'Kawasaki', 'Jet Ski Ultra 310'); 
INSERT INTO boat VALUES (124, 'Fishing', 'Tracker', 'Pro Team 190 TX');
INSERT INTO boat VALUES (125, 'Sailboat', 'Catalina', '22');
INSERT INTO boat VALUES (126, 'Yacht', 'Ferretti', '850'); 

INSERT INTO boat (boatType, make, model)  VALUES ('Yacht', 'Ferretti', '850');
INSERT INTO boat (boatType, make, model)  VALUES ('Pontoon', 'Harris Solstice', '240'); 
INSERT INTO boat (boatType, make, model)  VALUES ('Sailboat', 'Jeanneau Sun Odyssey', '349');
INSERT INTO boat (boatType, make, model)  VALUES ('Jet Ski', 'Sea-Doo', 'Spark'); 
INSERT INTO boat (boatType, make, model)  VALUES ('Jet Ski', 'Yamaha', 'WaveRunner EX'); 
INSERT INTO boat (boatType, make, model)  VALUES ('Jet Ski', 'Yamaha', 'FX Cruiser Ho'); 
INSERT INTO boat (boatType, make, model)  VALUES ('Fishing', 'Tracker', 'Pro Team 190 TX');
INSERT INTO boat (boatType, make, model)  VALUES ('Sailboat', 'Beneteau Oceanis', '45.1');
INSERT INTO boat (boatType, make, model)  VALUES ('Pontoon', 'Sun Tracker', 'Party Barge 20 DLX');

INSERT INTO rental VALUES ('CR7', 122, '2022-12-31', false, CURRENT_DATE());
INSERT INTO rental VALUES ('LM10', 123, '2022-12-31', false, CURRENT_DATE());
INSERT INTO rental VALUES ('LM10', 126, '2022-12-31', false, CURRENT_DATE());


INSERT INTO rental VALUES ('robertDoe', 127, '2022-12-30', false, CURRENT_DATE());
INSERT INTO rental VALUES ('robertDoe', 128, '2022-12-29', false, CURRENT_DATE());
INSERT INTO rental VALUES ('rebeccaJohnson', 130, '2022-12-26', false, CURRENT_DATE());
INSERT INTO rental VALUES ('robertDoe', 129, '2022-12-31', false, CURRENT_DATE());
INSERT INTO rental VALUES ('robertDoe', 131, '2022-12-29', false, CURRENT_DATE());
INSERT INTO rental VALUES ('maryCopper', 132, '2022-12-20', false, CURRENT_DATE());
