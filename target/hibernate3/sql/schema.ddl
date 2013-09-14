
    create table USERS (
        id number(19,0) not null,
        login varchar2(255 char),
        primary key (id)
    );

    create sequence users_seq;
