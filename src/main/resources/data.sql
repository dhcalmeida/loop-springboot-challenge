INSERT INTO brand (id, name) VALUES (1, 'LOJA');

INSERT INTO product (id, name, brand_id) VALUES (35455, 'Produto 1', 1);

INSERT INTO price_list (id, end_time, start_time, priority, currency) VALUES (1, '2020-12-31 23:59:59', '2020-06-14 00:00:00', 0, 0);
INSERT INTO price_list (id, end_time, start_time, priority, currency) VALUES (2, '2020-06-14 18:30:00', '2020-06-14 15:00:00', 1, 0);
INSERT INTO price_list (id, end_time, start_time, priority, currency) VALUES (3, '2020-06-15 11:00:00', '2020-06-15 00:00:00', 1, 0);
INSERT INTO price_list (id, end_time, start_time, priority, currency) VALUES (4, '2020-12-31 23:59:59', '2020-06-15 16:00:00', 1, 0);

INSERT INTO price (id, val, price_list_id, product_id) VALUES (1, 35.50, 1, 35455);
INSERT INTO price (id, val, price_list_id, product_id) VALUES (2, 25.45, 2, 35455);
INSERT INTO price (id, val, price_list_id, product_id) VALUES (3, 30.50, 3, 35455);
INSERT INTO price (id, val, price_list_id, product_id) VALUES (4, 38.95, 4, 35455);