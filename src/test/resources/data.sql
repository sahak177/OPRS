delete from user_role where user_id=(select id from user where email='James@gmail.com');
delete from user where  email='James@gmail.com';
insert into user(social_number,first_name,last_name,email,password)values( 12345678, 'James', 'Bond', 'James@gmail.com', '$2a$10$qP1zhVIpmM0tZ.G3z1XaaOLDYVuXb0u.OXl2pCsBSKZdsWT5iVgIS');
insert into user_role (user_id,role_id) values ((select id from user where email='James@gmail.com'),(select id from role where role_name ='USER_ROLE' ));
