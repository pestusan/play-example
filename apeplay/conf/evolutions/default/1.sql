# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table to_do (
  id                        bigint not null,
  description               varchar(255),
  done                      boolean,
  date_of_done              timestamp,
  assigned_user_email       varchar(255),
  constraint pk_to_do primary key (id))
;

create table user (
  email                     varchar(255) not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create sequence to_do_seq;

create sequence user_seq;

alter table to_do add constraint fk_to_do_assignedUser_1 foreign key (assigned_user_email) references user (email) on delete restrict on update restrict;
create index ix_to_do_assignedUser_1 on to_do (assigned_user_email);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists to_do;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists to_do_seq;

drop sequence if exists user_seq;

