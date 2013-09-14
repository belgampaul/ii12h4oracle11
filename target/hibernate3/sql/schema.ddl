
    create table Customer (
        id number(19,0) not null,
        firstName varchar2(255 char),
        lastName varchar2(255 char),
        primary key (id)
    );

    create table USERS (
        id number(19,0) not null,
        login varchar2(255 char),
        primary key (id)
    );

    create sequence hibernate_sequence;

    create sequence users_seq;
