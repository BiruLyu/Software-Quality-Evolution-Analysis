# Set up JIRA on Google Could



## Create a VM instance

### 1. check the java version

   ![](https://ws4.sinaimg.cn/large/006tKfTcgy1fjoba1eqd4j30qm03qq44.jpg)

   ```
   sudo apt-get install default-jre
   ```

   This will install the Java Runtime Environment (JRE). If you instead need the Java Development Kit (JDK), which is usually needed to compile Java applications (for example [Apache Ant](http://ant.apache.org/), [Apache Maven](http://maven.apache.org/), [Eclipse](https://www.eclipse.org/)and [IntelliJ IDEA](http://www.jetbrains.com/idea/,%20etc.) execute the following command:

   ```
   sudo apt-get install default-jdk
   ```

   ![](https://ws2.sinaimg.cn/large/006tKfTcgy1fjoc0a5qquj30r203uq42.jpg)

### 2. install **MySQL** or **PostgreSQL**

#### 2.1 install mysql
Install MySQL by the following command:

   ```
   shell> sudo apt-get install mysql-server
   ```

   This installs the package for the MySQL server, as well as the packages for the client and for the database common files.

   1. **Starting and Stopping the MySQL Server**

   The MySQL server is started automatically after installation. You can check the status of the MySQL server with the following command:

      ```
      shell> sudo service mysql status
      ```
      Stop the MySQL server with the following command:

      ```
      shell> sudo service mysql stop
      ```
      To restart the MySQL server, use the following command:
      ```
      shell> sudo service mysql start
      ```

#### 2.2 install PostgreSQL


![](https://ws4.sinaimg.cn/large/006tKfTcgy1fjold4w9cbj30ke05at9s.jpg)

![](https://ws3.sinaimg.cn/large/006tKfTcgy1fjont9i08gj31cg16gafw.jpg)

   ```sudo apt-get install postgresql-9.6```

   ```sudo -u postgres psql postgres```

   ```\password postgres```

   ​

### 3. install **JIRA**

```wget https://www.atlassian.com/software/jira/downloads/binary/atlassian-jira-6.4.4-x64.bin```

```chmod a+x atlassian-jira-X.Y.bin```

![](https://ws3.sinaimg.cn/large/006tKfTcgy1fjodqzdfqkj30ug04g75h.jpg)

![](https://ws4.sinaimg.cn/large/006tKfTcgy1fjoljcl9arj30so04g0u0.jpg)

![](https://ws2.sinaimg.cn/large/006tKfTcgy1fjoobdpy33j30si05qwfh.jpg)

### 4. Conclusion 

1. First, I try to set up JIRA directly in Google Compute Engine(GCE), because it has built-in java feature etc. Then I accessed lira through cloud shell, it was really unstable and failed to connect to database used for store lira data.
2. Then, I noticed that I should create Linux VM and deploy Postgres or MySQL on it before I set up JIRA. 
   + I tried Google Could SQL to create both MySQL and PostgreSQL instance. But I failed to figure out how to integrated it with the JIRA that I set up in VM.
   + I tried to install MySQL in VM, however the MySQL driver is no longer bundled with JIRA, then extra effort will be required for this.
   + Finally I chose the PostgreSQL.
3. After installing Java, PostgreSQL and JIRA, it was supposed that I could reach JIRA setup page in browser through `http://<computer_name_or_IP_address>:8080`, while nothing happened. I’ve created new instance and repeated the same steps several times. Finally I noticed that it was firewall problem. The port 8080 is access prohibited. I’ve added new firewall rule to resolve this problem.
4. I finally got access to JIRA setup page successully, but the page crashed again when I tried to create issue. 
```
Caused by: org.postgresql.util.PSQLException: ERROR: column am.amcanorder does not exist
Position: 407
```
   It seems that the PostgreSQL Driver need to be updated(https://github.com/intermine/intermine/pull/1475). However, I chose to replace the newer version 9.6* with older version of the PostgresSQL.
   
5. I created `Ubuntu 14.04 LTS` VM instance instead of `Debian GNU/Linux 9 (stretch)`, then installed comparable version of java, postgresql and jira on it. Then I configured JIRA server(include Database, Administrator ect. ) through `35.202.228.114:8080`.

To sum up, most problem I faced and failed to resolve is because I am not  familiar with Google Cloud and Linux Server. The unfamiliar with command line also get me in trouble😔.


