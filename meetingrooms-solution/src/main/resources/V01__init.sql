create table meetingRooms (id bigint not null auto_increment, room_name varchar(255), room_length varchar(255),
room_width varchar(255), room_area double, primary key (id));
INSERT INTO meetingrooms (id, room_name, room_length, room_width, room_area) VALUES (1, "Green", 5, 4, 20);
INSERT INTO meetingrooms (room_name, room_length, room_width, room_area) VALUES ("Grey", 6, 7, 42);
INSERT INTO meetingrooms (room_name, room_length, room_width, room_area) VALUES ("Red", 4, 6, 24);