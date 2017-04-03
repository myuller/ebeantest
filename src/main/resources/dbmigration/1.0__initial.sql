-- apply changes
create table department (
  id                            bigserial not null,
  name                          varchar(255),
  constraint pk_department primary key (id)
);

create table employee (
  id                            bigserial not null,
  name                          varchar(255),
  department_id                 bigint,
  constraint pk_employee primary key (id)
);

create table office (
  id                            bigserial not null,
  name                          varchar(255),
  department_id                 bigint,
  constraint pk_office primary key (id)
);

alter table employee add constraint fk_employee_department_id foreign key (department_id) references department (id) on delete restrict on update restrict;
create index ix_employee_department_id on employee (department_id);

alter table office add constraint fk_office_department_id foreign key (department_id) references department (id) on delete restrict on update restrict;
create index ix_office_department_id on office (department_id);

