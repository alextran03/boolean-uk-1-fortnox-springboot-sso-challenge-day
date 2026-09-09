-- Seed data: 5 products, 3 customers, 3 orders placed by 2 different customers.

-- Products
INSERT INTO products (id, name, price) VALUES
    (1, 'Laptop 14"',        12000.00),
    (2, 'Wireless Mouse',      250.00),
    (3, 'Mechanical Keyboard', 800.00),
    (4, '27" Monitor',        3200.00),
    (5, 'USB-C Cable',         150.00);

-- Customers
INSERT INTO customers (id, name, email) VALUES
    (1, 'Alex Tran', 'AlexTran@example.com'),
    (2, 'Mimi Ngiem',   'MimiNghiem@example.com'),
    (3, 'Kevin Tran',  'KevinTran@example.com');

-- Orders (total_amount = sum of the linked product prices)
-- Order 1 + 2 belong to Alice (id 1), order 3 belongs to Bob (id 2).
INSERT INTO orders (id, created_at, total_amount, customer_id) VALUES
    (1, TIMESTAMP '2026-09-01 10:15:00', 13050.00, 1),
    (2, TIMESTAMP '2026-09-03 14:40:00',  3350.00, 1),
    (3, TIMESTAMP '2026-09-05 09:05:00',  1200.00, 2);

-- Order <-> Product links
INSERT INTO order_products (order_id, product_id) VALUES
    (1, 1), (1, 2), (1, 3),
    (2, 4), (2, 5),
    (3, 2), (3, 3), (3, 5);

-- Explicit ids above did not advance the identity sequences; move them past the seeded rows
-- so the first Hibernate INSERT does not collide with an existing id.
SELECT setval(pg_get_serial_sequence('customers', 'id'), (SELECT MAX(id) FROM customers));
SELECT setval(pg_get_serial_sequence('products',  'id'), (SELECT MAX(id) FROM products));
SELECT setval(pg_get_serial_sequence('orders',    'id'), (SELECT MAX(id) FROM orders));
