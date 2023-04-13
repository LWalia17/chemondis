CREATE TABLE IF NOT EXISTS public.user (
  userid INT  NOT NULL,
  name VARCHAR(120) NOT NULL,
  ip VARCHAR(45) NOT  NULL,
  PRIMARY KEY (userid))
  
CREATE TABLE IF NOT EXISTS public.activity (
  category  VARCHAR(120) NOT NULL,
  label  VARCHAR(120) NULL,
  action  VARCHAR(120)  NULL,
  search  VARCHAR(120)  NOT NULL,
  mod_ts  VARCHAR(120) NOT NULL,
  userid INT NULL)


CREATE TABLE IF NOT EXISTS public.user_login (
  ip  VARCHAR(120) NOT NULL,
  browser  VARCHAR(120) NULL,
  userid INT  NOT NULL
)
