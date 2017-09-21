[Jira安装部署](http://pangge.blog.51cto.com/6013757/1539622)  
 [Health Check: Thread Limit](https://confluence.atlassian.com/kb/health-check-thread-limit-858578685.html)

 ## 1. install JDK and check the version:

 ```
 BiruLyu:~ BiruLyu$ java -version
 java version "1.8.0_111"
 Java(TM) SE Runtime Environment (build 1.8.0_111-b14)
 Java HotSpot(TM) 64-Bit Server VM (build 25.111-b14, mixed mode)
 ```

## 2. install Postgres and check the version
```
 BiruLyu:~ BiruLyu$ postgres -V
 postgres (PostgreSQL) 9.6.2
```
   [Getting Started with PostgreSQL on Mac OSX](https://www.codementor.io/devops/tutorial/getting-started-postgresql-server-mac-osx)
   
```
 BiruLyu:~ BiruLyu$ psql postgres
```
        
## 3. install JIRA

```
 BiruLyu:~ BiruLyu$ brew install jira
```
+ ### 3.1 Setting your JIRA Home Directory
 1. 在 `/usr/local` 新建文件夹 `jira`
 2. `mv atlassian-jira-core-7.4.2-standalone/ jira`
 3. `BiruLyu:jira BiruLyu$ mkdir jira_home`
 4. `BiruLyu:jira BiruLyu$ vim /usr/local/jira/atlassian-jira-core-7.4.2-standalone/atlassian-jira/WEB-INF/classes/jira-application.properties`
 5. `BiruLyu:jira BiruLyu$ cat /usr/local/jira/atlassian-jira-core-7.4.2-standalone/atlassian-jira/WEB-INF/classes/jira-application.properties` 
 **Do not modify this file unless instructed. It is here to store the location of the JIRA home directory only and is typically written to by the installer.**
 
 `jira.home = /usr/local/jira/jira_home`
 6. 启动服务
    ```
    BiruLyu:bin BiruLyu$ pwd
    /usr/local/jira/atlassian-jira-core-7.4.2-standalone/bin
    BiruLyu:bin BiruLyu$ ./startup.sh
    或者
    BiruLyu:bin BiruLyu$ ./start-jira.sh
    ```
 7. 停止服务
    ```
    BiruLyu:bin BiruLyu$ ./stop-jira.sh
    ```
+ ### 3.2 访问:  http://localhost:8080/

   ServerID: `BPBR-RILF-YLGR-J11Q`
   License Key : https://my.atlassian.com/product
   

