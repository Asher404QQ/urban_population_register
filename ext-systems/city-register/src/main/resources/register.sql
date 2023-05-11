drop table if exists cr_address_person;
drop table if exists cr_person;
drop table if exists cr_address;
drop table if exists cr_street;
drop table if exists cr_district;

create table cr_district (
    district_code integer not null,
    district_name varchar(300),
    primary key (district_code)
);

insert into cr_district (district_code, district_name) values (1, 'Адлерсктй');

create table cr_street(
    street_code integer not null,
    street_name varchar(300),
    primary key(street_code)
);

insert into cr_street (street_code ,street_name) values (1, 'Ленина');

create table cr_address(
    address_id serial,
    district_code integer not null,
    street_code integer not null,
    building varchar(10) not null,
    floor varchar(10) not null,
    apartment varchar(10) not null,
    primary key (address_id),
    foreign key (district_code) references cr_district(district_code) on delete restrict,
    foreign key (street_code) references cr_street(street_code) on delete restrict
);

insert into cr_address (district_code, street_code, building, floor, apartment) values (1, 1, '10', '2', '21');
insert into cr_address (district_code, street_code, building, floor, apartment) values (1, 1, '62', null, null);

create table cr_person(
    person_id serial,
    sur_name varchar(100) not null,
    given_name varchar(100) not null,
    patronymic varchar(100) not null,
    date_of_birth date not null,
    passport_serial varchar(10),
    passport_number varchar(10),
    passport_date date,
    certificate_number varchar(10),
    certificate_date date,
    primary key(person_id)
);

insert into cr_person (sur_name, given_name, patronymic, date_of_birth, passport_serial, passport_number, passport_date)
values ('Орлов', 'Сергей', 'Михайлович', '1989-02-24', '53153246', '1335', '2012-05-12');

insert into cr_person (sur_name, given_name, patronymic, date_of_birth, passport_serial, passport_number, passport_date)
values ('Орлова', 'Мария', 'Анатольевна', '1992-11-21', '463472154', '1567', '2015-01-01');

insert into cr_person (sur_name, given_name, patronymic, date_of_birth, certificate_number, certificate_date)
values ('Орлова', 'Анастасия', 'Сергеевна', '2008-03-05', '4312', '2008-03-06');

create table cr_address_person(
    person_address_id serial,
    address_id integer not null,
    person_id integer not null,
    start_date date not null,
    end_date date,
    temporal boolean default false,
    primary key (person_address_id),
    foreign key (address_id) references cr_address(address_id) on delete restrict,
    foreign key (person_id) references cr_person(person_id) on delete restrict
);

insert into cr_address_person (address_id, person_id, start_date)
values (1, 1, '2014-10-12');

insert into cr_address_person (address_id, person_id, start_date)
values (2, 2, '2015-03-04');

insert into cr_address_person (address_id, person_id, start_date)
values (1, 3, '2019-12-23');