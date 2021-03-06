create sequence hibernate_sequence start with 1 increment by 1;
create table client (id bigint not null, email varchar(255) not null, first_name varchar(255) not null, last_name varchar(255) not null, primary key (id));
create table interaction (type varchar(31) not null, created TIMESTAMP, id bigint not null, direction varchar(255), description varchar(255), state varchar(255), message blob, client_address varchar(255), message_id varchar(255), message_text varchar(4000), message_text_type varchar(50),  message_from varchar(255), message_to varchar(255), client_id bigint, primary key (id));
create table role (id bigint generated by default as identity, description varchar(255), name varchar(255), primary key (id));
create table user (id bigint generated by default as identity, first_name varchar(255), last_name varchar(255), login varchar(255), password varchar(255), primary key (id));
create table user_role (user_id bigint not null, role_id bigint not null);
create table inbound_mailbox_property (id bigint generated by default as identity, protocol varchar(255), host varchar(255), user varchar(255), password varchar(255), primary key (id));
alter table client add constraint UK_bfgjs3fem0hmjhvih80158x29 unique (email);
alter table interaction add constraint FKo2qy332w8dt0q02crc2hf6nb foreign key (client_id) references client;
alter table interaction add constraint UK_fwo5hus5vim1bpp9aextyhqi9 unique (message_id);
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role;
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user;
