# Write your MySQL query statement below
Select c.id,c.movie,c.description,c.rating
from Cinema c
Where c.id % 2 !=0 And c.description != "boring"
Order by c.rating desc;