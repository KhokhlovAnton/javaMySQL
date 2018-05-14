# javaMySQL

Application shows connection between java and MySQL

First and second point mades by schema.sql
queries.sql contains two queries that I use in app

In first query I use "limit" to choose only one record because we can have situation where on one project we have 2 PM
worked in different timeslots so I just took first of them and let MySQL choose who it will be.
Also I limited nested query because it's common situation when we have the same count of JavaDevs on two projects.

Table _projectemployee_ used to store history about projects and employees.
In this table we have position_id - I use this field to predict situation 
when our SQLDev works as JavaDev (Glen Werymon) or JavaDev helps QA's on QA's position.

In _projectemployee_ we can create unique key use 'project_id','employee_id','position_id' and date field

In Application I use private method _run_ where I store all logic, create connection, statement and some kind of listener.
for this purpose I use infinite while loop, if user types 'first' - app return result set for first query; 
'second' - returns result set for second query.
Notice that I commented out count of projects in 2nd query. If you want to see them just remove two dashes and 
add variable for this in "else-if" section
To exit just type 'exit'.
