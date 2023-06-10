--вставка данных в таблицу order
insert into public.order(created, modified, total_cost, completion, order_title, is_complected, customer_id)
values ('2020-10-21', '2023-12-22', 322123.231, '2022-12-22', 'авто', TRUE, 1),
       ('2019-8-21', '2019-3-21', 2343344.23, '2022-12-22', 'квартира', TRUE, 2),
       ('2018-9-21', '2023-12-1', 500000, '2020-12-12', 'мотоцикл', FALSE, 3);
