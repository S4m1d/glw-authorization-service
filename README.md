# glw-authorization-service
Authorization microservice for my project DnD Game Logs Writer
# DB configuration scrip
```{MySQL} 
create database glw_user_info_db;

use glw_user_info_db;

create table user_credentials(
id int primary key,
login varchar(30) unique,
pwd varchar(30)
);
```


