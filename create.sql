create sequence hibernate_sequence start with 1 increment by 1
create table client (id bigint not null, email varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, primary key (id))
create table email_address (id bigint not null, address varchar(255), client_id bigint, primary key (id))
create table interaction (type varchar(31) not null, id bigint not null, description varchar(255), message blob, client_id bigint, primary key (id))
alter table email_address add constraint FKdh43nqaxsxkf6wt5k6orm6ipp foreign key (client_id) references client
alter table interaction add constraint FKo2qy332w8dt0q02crc2hf6nb foreign key (client_id) references client
