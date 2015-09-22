create user empleado with password 'E12345' createdb;

create user administrador with password 'A12345' superuser;

--ejecutar dentro la base de datos con un usuario con privilegios de superuser


alter database perzan owner to empleado;
grant all on all tables in schema public to empleado;

