-- first query 

select e.firstname,e.lastname,e.contact_info
from projectemployee prjemp
join employee e on e.id = prjemp.employee_id
where project_id = (
  select project_id from
  (select count(*) c,pe.project_id
     from projectemployee pe
     join position p on p.id = pe.position_id
    where p.title = 'JavaDev'
    group by pe.project_id
    order by c desc limit 1) tmp)
  and prjemp.position_id = (select id from position where title = 'PM')
order by prjemp.start desc limit 1;

-- second query 

select e.firstname,e.lastname -- , count(pe.project_id)
from employee e
join projectemployee pe on pe.employee_id = e.id
join position p on pe.position_id = p.id
join project pr on pr.id = pe.project_id
where p.title = 'QASpecialist'
group by e.firstname,e.lastname
order by count(pe.project_id) desc;