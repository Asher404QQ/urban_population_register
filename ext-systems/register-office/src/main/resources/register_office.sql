drop table if exists ro_person;
drop table if exists ro_passport;


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

insert into ro_person (sex, first_name, last_name, patronymic, date_of_birth)
values (1, 'Ольга', 'Фролова', 'Константиновна', '1992-02-21');
insert into ro_person (sex, first_name, last_name, patronymic, date_of_birth)
values (2, 'Максим', 'Фролов', 'Олегович', '1989-11-2');

insert into ro_passport(person_id, serial, name, issue_date, issue_department)
values (1, '2451', '9102843', '2018-3-21', 'МФЦ города Адлер №3');
insert into ro_passport(person_id, serial, name, issue_date, issue_department)
values (2, '1637', '5632451', '2021-12-4', 'МФЦ города Адлер №3');