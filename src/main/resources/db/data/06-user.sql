--liquibase formatted sql

--changeset init_tables:1 endDelimiter:/
--comment встака данных
insert into public.user(user_name, password, email) values ('Admin', '$2y$10$0iw5joKWfZs60TICdRjoD.Ca1O9vZRVsZ768nGZHlGWu83KBo0kqe', 'admin@mail.com');

insert into public.user(user_name, password, email) values ('Manager', '$2y$10$0iw5joKWfZs60TICdRjoD.Ca1O9vZRVsZ768nGZHlGWu83KBo0kqe', 'manager@mail.com');
insert into public.user(user_name, password, email) values ('TEST', '$2y$10$0iw5joKWfZs60TICdRjoD.Ca1O9vZRVsZ768nGZHlGWu83KBo0kqe', 'manager@mail.com');

insert into public.role(role_name) values ('ROLE_MANAGER');
insert into public.role(role_name) values ('ROLE_ADMIN');
insert into public.role(role_name) values ('ROLE_EMPLOYEE');

insert into public.user_role(user_id, role_id) values (1, 1);
insert into public.user_role(user_id, role_id) values (1, 2);
insert into public.user_role(user_id, role_id) values (2, 1);
insert into public.user_role(user_id, role_id) values (3, 3);


