insert into department ("name") values ('sales'), ('it'), ('maintenance');

delete from employee;
insert into employee ("name", department_id)
  select ('John Doe ' || round(random() * 100)) as name , department.id from department cross join generate_series(1,4) where department.name = 'sales';

delete from office;
insert into office ("name", department_id)
  select 'Room #' || round(random() * 100), id from department cross join generate_series(1,4) where department.name = 'sales';
