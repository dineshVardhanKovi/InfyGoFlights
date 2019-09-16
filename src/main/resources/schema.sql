create table FLIGHT_DETAILS(
flight_id varchar2(50) primary key,
source varchar2(50) default null,
destination varchar2(50) default null,
airlines varchar2(50) default null,
fare number(5,2),
flight_available_date date default null,
seat_count number(3) ,
departure_time date default null, 
arrival_time date default null
);