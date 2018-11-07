INSERT into user(user_id,username,password,name,last_name,active) VALUES (1,'admin','$2b$10$NDHG7zTMt3p2iircSF/SU.sGlZmlk7apOwMWTEk54623sdJIFEJcq','Admin','Admin',1);
INSERT into role(role_id,role) VALUES (1,'admin');
INSERT into user_role(user_id,role_id) VALUES(1,1);