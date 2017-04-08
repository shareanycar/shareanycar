-- locations
insert into Location (country, city,id) values('Russia', 'Moscow',1);
insert into Location (country, city,id) values('Russia', 'Alushta',2);
insert into Location (country, city,id) values('USA', 'Chicago',3);
insert into Location (country, city,id) values('USA', 'New York',4);
insert into Location (country, city,id) values('Germany', 'Munich', 5);

-- car type
insert into CarType(name,id) values('sedan',1);
insert into CarType(name,id) values('suv',2);
insert into CarType(name,id) values('jeep',3);
insert into CarType(name,id) values('minivan',4);

-- transmission type
insert into TransmissionType(name,id) values('manual',1);
insert into TransmissionType(name,id) values('automatic',2);

-- fuel type
insert into FuelType(name,id) values('petrol',1);
insert into FuelType(name,id) values('disel',2);

-- manufacturer
insert into Manufacturer(name, id) values('BMW',1);

-- insurer
insert into Insurer(name, id) values('Alpha',1);

--users
insert into User(firstname, lastname, email, password, id) values('Tom', 'Cruz', 'e', 'pass',1);

--cars
insert into Car(insurer_id, fuelType_id, carType_id, manufacturer_id, transmissionType_id, location_id, user_id,status, id) values(1,1,1, 1, 2, 1, 1,1,1);
