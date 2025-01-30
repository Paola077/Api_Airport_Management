INSERT INTO roles (id_role, role_name) VALUES (default, 'ROLE_USER');
INSERT INTO roles (id_role, role_name) VALUES (default, 'ROLE_ADMIN');

INSERT INTO users (username, email, password)
VALUES
    ('John Doe', 'john.doe@example.com', '$2a$10$yGQtiOMK/wM8F25pVaC.ieea5z0PXX/XPuxbR4.mpCoJW2wgvV3Hq'),
    ('Jane Smith', 'jane.smith@example.com', '$2a$10$yGQtiOMK/wM8F25pVaC.ieea5z0PXX/XPuxbR4.mpCoJW2wgvV3Hq'),
    ('Admin User', 'admin@example.com', '$2a$10$yGQtiOMK/wM8F25pVaC.ieea5z0PXX/XPuxbR4.mpCoJW2wgvV3Hq'),
    ('Alice Brown', 'alice.brown@example.com', '$2a$10$yGQtiOMK/wM8F25pVaC.ieea5z0PXX/XPuxbR4.mpCoJW2wgvV3Hq'),
    ('Bob White', 'bob.white@example.com', '$2a$10$yGQtiOMK/wM8F25pVaC.ieea5z0PXX/XPuxbR4.mpCoJW2wgvV3Hq');

INSERT INTO profiles (user_id, username, email, image_url) VALUES
    (1, 'John Doe', 'john.doe@example.com', 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'),
    (2, 'Jane Smith', 'jane.smith@example.com', 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'),
    (3, 'Admin User', 'admin@example.com', 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'),
    (4, 'Alice Brown', 'alice.brown@example.com', 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'),
    (5, 'Bob White', 'bob.white@example.com', 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png');

INSERT INTO role_users (id, id_role) VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 1),
    (5, 2);

INSERT INTO airport (code, name, location) VALUES
('JFK', 'John F. Kennedy International Airport', 'New York, USA'),
('LAX', 'Los Angeles International Airport', 'Los Angeles, USA'),
('ORD', 'O Hare International Airport', 'Chicago, USA'),
('ATL', 'Hartsfield-Jackson Atlanta International Airport', 'Atlanta, USA'),
('DFW', 'Dallas/Fort Worth International Airport', 'Dallas, USA');

INSERT INTO flight (origin_id, destination_id, departure_date_time, arrival_date_time, available_seats, total_seats, status) VALUES
(1, 2, '2025-06-16T08:00:00', '2025-06-16T12:00:00', 100, 150, 'ACTIVE'),
(2, 3, '2025-04-17T09:30:00', '2025-04-17T13:45:00', 80, 120, 'ACTIVE'),
(3, 4, '2025-03-18T14:00:00', '2025-03-18T18:00:00', 50, 100, 'ACTIVE'),
(4, 5, '2025-01-19T10:00:00', '2025-01-19T14:30:00', 200, 200, 'INACTIVE'),
(5, 1, '2025-01-20T07:00:00', '2025-01-20T16:15:00', 150, 150, 'INACTIVE');