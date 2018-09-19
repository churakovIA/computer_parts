USE test;

DROP TABLE IF EXISTS parts; 
CREATE TABLE parts( 
	id INT(11) NOT NULL AUTO_INCREMENT, 
    name VARCHAR(100) NOT NULL,
    need BOOL NOT NULL,
    count INT(10) NOT NULL,
	PRIMARY KEY (id))
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
insert into parts (name, need, count) values ('материнская плата', 1, 7);
insert into parts (name, need, count) values ('звуковая карта', 0, 22);
insert into parts (name, need, count) values ('процессор', 1, 77);
insert into parts (name, need, count) values ('подсветка корпуса', 0, 31);
insert into parts (name, need, count) values ('корпус', 1, 43);
insert into parts (name, need, count) values ('память', 1, 97);
insert into parts (name, need, count) values ('жесткий диск', 1, 72);
insert into parts (name, need, count) values ('видеокарта', 0, 45);
insert into parts (name, need, count) values ('блок питания', 1, 97);
insert into parts (name, need, count) values ('охлаждение процессора', 1, 30);
insert into parts (name, need, count) values ('охлаждение корпуса', 0, 53);
insert into parts (name, need, count) values ('охлаждение видеокарты', 0, 26);
insert into parts (name, need, count) values ('охлаждение диска', 0, 50);
insert into parts (name, need, count) values ('привод Blu-Ray', 0, 8);
insert into parts (name, need, count) values ('привод DVD±RW', 0, 7);
insert into parts (name, need, count) values ('USB концентратор', 0, 41);
insert into parts (name, need, count) values ('сетевая карта', 0, 26);
insert into parts (name, need, count) values ('адаптер WI-FI', 0, 1);
insert into parts (name, need, count) values ('кабель питания', 0, 49);
insert into parts (name, need, count) values ('ТВ тюнер', 0, 52);
insert into parts (name, need, count) values ('Видеозахват', 0, 67);
insert into parts (name, need, count) values ('переходник для HDD', 0, 86);
insert into parts (name, need, count) values ('контейнер для HDD', 0, 80);
insert into parts (name, need, count) values ('регулятор оборотов', 0, 33);
insert into parts (name, need, count) values ('кард-ридер', 0, 38);
insert into parts (name, need, count) values ('контроллер RS-232', 0, 39);
insert into parts (name, need, count) values ('контроллер RAID', 0, 6);

