INSERT INTO TOOLS (tool_code, tool_type_id, brand) VALUES 
('CHNS', 2, 'Stihl'),
('LADW', 1, 'Werner'),
('JAKD', 3, 'DeWalt'),
('JAKR', 3, 'Ridgid');

INSERT INTO TOOLTYPE(tool_name, daily_charge, weekday_charge, weekend_charge, holiday_charge) VALUES
('Ladder', 1.99, TRUE, TRUE, FALSE),
('Chainsaw', 1.49, TRUE, FALSE, TRUE),
('Jackhammer', 2.99, TRUE, FALSE, FALSE);