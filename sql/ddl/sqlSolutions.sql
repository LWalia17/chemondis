
--Query 1
 select 
    extract(hour from (TO_TIMESTAMP(timestamp, 'YYYY-MM-DD"T"HH24:MI:SS'))) as HOUR,  
    count(1) as num_events 
 from 
    public.activity c 
 where 
    userid in ('71')
 group by
 extract(hour from (TO_TIMESTAMP(timestamp, 'YYYY-MM-DD"T"HH24:MI:SS')))
 
 
 --Query 2
 select 
    	browser , 
    	count(1) as popularity
 from 
    	public."user_login" 
 group by 
    	browser 
 order by 
    	count(1) desc limit 1
  
  
  --Query 3
  
  select category ,  
  --to get distinct users per category
         count(distinct userid) 
  from  
         public.activity c 
  where 
         extract(hour from (TO_TIMESTAMP(timestamp, 'YYYY-MM-DD"T"HH24:MI:SS'))) between 1 and 7
  group by 
         category  
  order by 
        count(distinct userid) desc limit 3;
  
  
  --Query 4
  select
         count(1) as search_volume 
  from  
          public.activity c 
  where 
  ---using builtin function dow to get when day of week= monday then dow =1 
          extract(dow from (TO_TIMESTAMP(timestamp, 'YYYY-MM-DD"T"HH24:MI:SS'))) = 1
          and search = 'term2'
          
          
 --Query 5
 select		
 	category ,
 	label, 
	 action,
	 count(1)  
from 
	public.activity c 
group by 
	category,
	label,
	action
order by 
	count(1)
desc  limit 10
 
 --Query 6
 
 select   search, 
	  searchdate
from
(select

          Row_number()
           OVER 
--Partition by each date 
           ( PARTITION BY date(TO_TIMESTAMP(timestamp, 'YYYY-MM-DD"T"HH24:MI:SS'))
--order by respective timestamps of each date 
             ORDER BY TO_TIMESTAMP(timestamp, 'YYYY-MM-DD"T"HH24:MI:SS')) RN,
          date(TO_TIMESTAMP(timestamp, 'YYYY-MM-DD"T"HH24:MI:SS')) as searchDate,
          search
 from  
          public.activity  c ) x
 where 
 	  RN = 1

          
  
  
