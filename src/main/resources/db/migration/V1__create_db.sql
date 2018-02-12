create sequence hibernate_sequence start with 1 increment by 1;
create table client (id bigint not null, email varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, primary key (id));
create table email_address (id bigint not null, address varchar(255), client_id bigint, primary key (id));
create table interaction (type varchar(31) not null, id bigint not null, description varchar(255), message blob, client_id bigint, primary key (id));
create table role (id bigint generated by default as identity, description varchar(255), name varchar(255), primary key (id));
create table user (id bigint generated by default as identity, first_name varchar(255), last_name varchar(255), login varchar(255), password varchar(255), primary key (id));
create table user_role (user_id bigint not null, role_id bigint not null);
alter table email_address add constraint FKdh43nqaxsxkf6wt5k6orm6ipp foreign key (client_id) references client;
alter table interaction add constraint FKo2qy332w8dt0q02crc2hf6nb foreign key (client_id) references client;
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role;
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user;
