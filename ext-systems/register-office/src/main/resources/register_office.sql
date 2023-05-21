drop table if exists ro_marriage_certificate;
drop table if exists ro_birth_certificate;
drop table if exists ro_passport;
drop table if exists ro_person;


create table ro_person(
    person_id serial primary key,
    sex smallint not null,
    first_name varchar(100) not null ,
    last_name varchar(100) not null ,
    patronymic varchar(100),
    date_of_birth date not null
);

create table ro_passport(
    passport_id serial primary key,
    person_id integer references ro_person(person_id) on delete restrict,
    serial varchar(10) not null,
    name varchar(10) not null,
    issue_date date not null,
    issue_department varchar(300) not null
);

create table ro_birth_certificate(
    birth_certificate_id serial primary key,
    person_id integer references ro_person(person_id) on delete restrict,
    certificate_number varchar(100) not null,
    issue_date date not null,
    father_id integer references ro_person(person_id) on delete restrict,
    mother_id integer references ro_person(person_id) on delete restrict
);

create table ro_marriage_certificate(
    marriage_certificate_id serial primary key,
    certificate_number varchar(100) not null,
    issue_date date not null,
    husband_id integer references ro_person(person_id) on delete restrict,
    wife_id integer references ro_person(person_id) on delete restrict,
    active boolean not null default false,
    end_date date
);

insert into ro_person (sex, first_name, last_name, patronymic, date_of_birth)
values (2, 'Ольга', 'Фролова', 'Константиновна', '1992-02-21'),
(1, 'Максим', 'Фролов', 'Олегович', '1989-11-2'),
(2, 'Екатерина', 'Фролова', 'Максимовна', '2019-5-15');


insert into ro_passport(person_id, serial, name, issue_date, issue_department)
values (1, '2451', '9102843', '2018-3-21', 'МФЦ города Адлер №3'),
(2, '1637', '5632451', '2021-12-4', 'МФЦ города Адлер №3');

insert into ro_birth_certificate(certificate_number, issue_date, person_id, father_id, mother_id)
values ('9138-26343', '2019-5-16', 3, 2, 1);

insert into ro_marriage_certificate(certificate_number, issue_date, husband_id, wife_id, active)
values ('239852395', '2009-4-6', 1, 2, true);