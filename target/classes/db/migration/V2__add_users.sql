insert into users (id, f_name, m_name, l_name, email, phone, password, vehicle_id)
values (1, 'Петр', 'Петрович', 'Петров', 'petr@mail.ru', '89006002233', '$2a$10$D3C6etqYPhI7qR3asDRP4uU1ItRqJ8fO/8tARU7xehucAylPHuVLy', null),
(2, 'Сергей', 'Сергеевич','Сергеев', 'serg@gmail.com','89016002244', '$2a$10$/uY1/WeEpo4XxsXSWPbLyugdlBz91GvArZTFruEv2DoZJ3FLC7xWS', null),
(3, 'Андрей', 'Андреевич', 'Андреев', 'andr@mail.ru', '89996663344', '$2a$08$JUv1UEWfnzuL5GFcPqFTSu8noqdmjkkCtdKDcThaHjlp/GgLWSfQ2', null);

insert into roles_project (user_id, name) values (1, 'ROLE_USER'), (2, 'ROLE_USER'), (3, 'ROLE_DRIVER');