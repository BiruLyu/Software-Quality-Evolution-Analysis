# Postgresql Restore

Attached, please the "dumped" DB. It supposed to have all the information that exists in my own test DB. The instruction to restore the db is at the following link:  
\[[https://www.postgresql.org/docs/9.1/static/backup-dump.html](https://www.postgresql.org/docs/9.1/static/backup-dump.html)

\]\([https://www.postgresql.org/docs/9.1/static/backup-dump.html](https://www.postgresql.org/docs/9.1/static/backup-dump.html)\)

More specifically, I used the following command to dump it:

```
pg_dump -U ATLAS -W TEST > ~/Desktop/TEST_Backup
```

You can find out the meaning of options by typing:

```
pg_dump --help
```

For summary:

`-U` means username \(On my computer this user name is ATLAS\)  
`-W` means you will need to provide a password  
TEST is the name of the database on MY COMPUTER. In your Computer, it can have a different name

You can first create an empty DB in pgadmin, let's call it Biru. Then you can import the `TEST_Backup` in Biru using the following command

```
psql -d Biru -U ATLAS -W < ~/Desktop/TEST_Backup
```

You can find out the meaning of the options by typing

```
psql --help
```

`-d` Means the name of the database on your computer

1. First create a Database called `Biru` in pgAdmin4
2. `psql -d Biru -U BiruLyu -W < ~/Desktop/TEST_Backup`

# Using the Spring JdbcTemplate class to control basic JDBC processing

Go over this link:  
[  
https://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html  
](/ https://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html)

1. Integrate java project and postgre by adding `postgresql.jar`
2. Maven is a build automation tool used primarily for Java projects. Using Maven can easily  create Spring MVC project by finding and putting appropriate Maven dependencies
   [https://mvnrepository.com/artifact/org.springframework/spring-jdbc](https://mvnrepository.com/artifact/org.springframework/spring-jdbc)
3. Understand Spring Framework
<img src="https://ws4.sinaimg.cn/large/006tNc79gy1fjvth88bjej30vq0p20w4.jpg" style="width:50%;"/>

**[#]** Spring is nothing but an API, a bunch of JARS and we creat xml files then we develop applications just using those jars and xml. The most important part is **the IoC container**. **IoC** stands for **Inversion of Control**.

![](https://ws3.sinaimg.cn/large/006tNc79gy1fjvtiklsskj31i60ne42g.jpg)

**IoC container** creates `HelloWorldService` object by setter method passing `HelloWorld` object to `HelloWorldService`. In other words, injecting the correlation between `objects(HelloWorld and HelloWorldService)` to `HelloWorldService`.
4. Spring + JDBC
+ Create a **Applications** class to store the data(the data is to store in DB, or data retrieved from DB)
+ Using the **DAO(Data Access Object)** Interface to access data
+ create **applicationsDAO** and **datasource** in Spring bean configuration files
5. Using JdbcTemplate to reduce redundant code

# Question

[http://www.java-samples.com/showtutorial.php?tutorialid=813](http://www.java-samples.com/showtutorial.php?tutorialid=813)
**[#]** Hibernate is flexible and powerful ORM solution to map Java classes to database tables. Hibernate itself takes care of this mapping using XML files so developer does not need to write code for this.

# Errors:

## source database “template1”

**postgresql - can't create database - OperationalError: source database “template1” is being accessed by other users**

You can also try to terminate the current process thread by the TerminalSearch the Process :
```
sudo ps aux | grep template1
```
Kill the Process :
```
sudo kill -9 < your process id >
```

## toDate()

Can't infer the SQL type to use for an instance of org.joda.time.DateTime. Use **setObject()** with an explicit Types value to specify the type to use. 

`issue.getCreationDate() == null ? null : issue.getCreationDate().toDate()`,
`issue.getUpdateDate() == null ? null : issue.getUpdateDate().toDate()`,
`issue.getDueDate() == null ? null : issue.getDueDate().toDate()`,

Added the using of `toDate` method.

## DDL vs. DML

**Data Definition Language (DDL)** statements are used to define the database structure or schema. Some examples:  `getJdbcTemplate().execute(query);`

1. **CREATE** - to create objects in the database 
2. **ALTER** - alters the structure of the database  
3. **DROP** - delete objects from the database 
4. **TRUNCATE** - remove all records from a table, including all spaces allocated for the records are removed 
5. **COMMENT** - add comments to the data dictionary 
6. **RENAME** - rename an object

**Data Manipulation Language (DML)** statements are used for managing data within schema objects. Some examples:  `getJdbcTemplate().update(query);`

1. **SELECT** - retrieve data from the a database  
2. **INSERT** - insert data into a table UPDATE - updates existing data within a table 
3. **DELETE** - deletes all records from a table, the space for the records remain 
4. **MERGE** - UPSERT operation (insert or update) 
5. **CALL** - call a PL/SQL or Java subprogram     
6. **EXPLAIN PLAN** - explain access path to data  
7. **LOCK TABLE** - control concurrency

# Summary 

Replace 

+ `net.behnamghader.db.PostgreJDBC` 
+ `net.behnamghader.db.DB_Constants`

BY 

+ `net.behnamghader.atlas.db.dao` ( define interface used to manipulate data 
+ `net.behnamghader.atlas.db.dao.impl` 
+ `Spring-Datasource.xml` (connecting to databaseSpring
+ `Applications.xml` (Define beansapplicationContext ( 

[http://docs.spring.io/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html#d0e24263](http://docs.spring.io/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html#d0e24263)

[https://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html](https://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html)

![](https://ws1.sinaimg.cn/large/006tNc79gy1fjvue34fmcj31i60yldxq.jpg)

[http://stackoverflow.com/questions/3855324/where-i-can-find-a-detailed-comparison-of-java-xml-frameworks](http://stackoverflow.com/questions/3855324/where-i-can-find-a-detailed-comparison-of-java-xml-frameworks)

# Maven Tutorial for Beginners 5 

- How to create a jar file with Maven

[https://www.youtube.com/watch?v=vGtGxKZQ-l8](https://www.youtube.com/watch?v=vGtGxKZQ-l8)
[https://cemerick.com/2010/08/24/hosting-maven-repos-on-github/](https://cemerick.com/2010/08/24/hosting-maven-repos-on-github/)
[http://charlie.cu.cc/2012/06/how-add-external-libraries-maven/](http://charlie.cu.cc/2012/06/how-add-external-libraries-maven/)
[http://stackoverflow.com/questions/16219052/mvn-deploydeploy-file-failed-to-deploy-artifacts-could-not-find-artifact](http://stackoverflow.com/questions/16219052/mvn-deploydeploy-file-failed-to-deploy-artifacts-could-not-find-artifact)
[https://maven.apache.org/guides/mini/guide-3rd-party-jars-remote.html](https://maven.apache.org/guides/mini/guide-3rd-party-jars-remote.html)
[https://www.youtube.com/watch?v=0zrfcDKPdYg](https://www.youtube.com/watch?v=0zrfcDKPdYg)


