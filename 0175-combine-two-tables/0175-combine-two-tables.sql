# Write your MySQL query statement below
SELECT p.firstname ,p.lastname,a.city,a.state
From Person p
LEFT JOIN Address a ON p.personId=a.personId;