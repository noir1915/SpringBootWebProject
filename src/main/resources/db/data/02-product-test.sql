
insert into public.material(type)
values ('steel'), ('titan'), ('cooper');

insert into public.technologist(name, email)
values ('Nikolay', 'nikolay@mail.ru'),
       ('Bob', 'bob@mail.ru'),
       ('Maria', 'maria@mail.ru');

insert into public.product (product_title, product_type, written_program, material_id, end_date, preparation,
                            technologist_id)
values ('pro1', 'NEW', true, 1, null, 'NOT_DONE', 1),
       ('pro2', 'NEW', true, 1, null, 'NOT_DONE', 2),
       ('pro3', 'NEW', true, 1, null, 'NOT_DONE', 3);