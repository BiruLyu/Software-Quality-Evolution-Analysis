# How to connect to a remote database?

* **Details**

  **USE :**

  `psql -h <host> -p <port> -u <database>`

  `psql -h <host> -p <port> -U <username> -W <password> <database>`

  **Problem : **

  ![](https://ws4.sinaimg.cn/large/006tKfTcgy1fk7b2o6072j310a07ggnm.jpg)

  ---

  * check the **FIrewall rules** of the remove server, make sure that it allow the specific protocols for certain ports

    ![](https://ws3.sinaimg.cn/large/006tNc79gy1fkbjeu04wvj31bq0kon1m.jpg)

  * Configure **pg\_hba.conf \(Client Authentication Configuration File\)** file of PostgreSQL DB on the server



    `$ sudo -s`

    `$ find / -type f -name "pg_hba.conf"`

    ![](https://ws3.sinaimg.cn/large/006tNc79gy1fkbjj9qu0mj30o203kgn0.jpg)

    `host all all x.x.x.x/24 trust`

    ![](https://ws1.sinaimg.cn/large/006tNc79gy1fkbjnxn847j30tc0aemz6.jpg)

    Now edit postgresql.conf \(PostgreSQL configuration\) that's located in the same directory.

    `$ vim postgresql.conf`

    `listen_addresses = '*'`

    ![](https://ws1.sinaimg.cn/large/006tNc79gy1fkbjrgeb6pj30ya062dha.jpg)

    Restart PostgreSQL and try connecting again from the client machine.



    ```
    root@jira-project:~# find / -type f -name "postgresql"
    /etc/init.d/postgresql
    ```

    `$ /etc/init.d/postgresql restart`

    ![](https://static.notion-static.com/5846957de86648c5a588eb303c2d7a47/Untitled)

  * Connect to remote database in terminal



    `psql -h <host> -p <port> -U <username>`

    ![](https://ws4.sinaimg.cn/large/006tNc79gy1fkbjv5hw8xj318w0h6wih.jpg)

# How to synchronize two remote postgreSQL?





* Details

  * What Is a Standby Database?

    A standby database is a database replica created from a backup of a primary database. By applying archived redo logs from the primary database to the standby database, you can keep the two databases synchronized.

    A standby database has the following main purposes:

    * Disaster protection
    * Protection against data corruption
    * Supplemental reporting

    If the primary database is destroyed or its data becomes corrupted, you can perform a failover to the standby database, in which case the standby database becomes the new primary database. You can also open a standby database with the read-only option, thereby allowing it to function as an independent reporting database.

  * Create another two VM instances and deploy PostgreSQL on them

    `sudo apt-get install postgresql`

    ![](https://ws4.sinaimg.cn/large/006tNc79gy1fkbk9pj30ej31aw08u0vp.jpg)

    ![](https://static.notion-static.com/4afad86f5b114997b774d4146e9aad34/Untitled)

    `psql postgresql -V`

    ![](https://static.notion-static.com/731e7d1b50db463cb099fd81f3c1f6e2/Untitled)

    ![](https://static.notion-static.com/f32f4ce336ae4840b850e0f4f28ec069/Untitled)

    `sudo -u postgres psql postgres`

    `postgres=# \password`

    ![](https://static.notion-static.com/a36e6a5834fd4a1bb192c4f4115f06f3/Untitled)

  * Connect to the new DBs through terminal on my laptop

    [Remote PostgreSQLs](https://www.notion.so/54927fd19fbb4df1b51c201398f082b3)

    ![](https://static.notion-static.com/f4d2153ab96940fc95fa97312744ddf4/Untitled)

    ![](https://static.notion-static.com/726fa217ac6e493394a356d8300ee6be/Untitled)

  * Synchronized these two DBs based on **hot standby **



    * Details

      * **Configure Primary Database**

        on the **instance-1 VM, ** open **postgresql.conf ** configure

        ```
        listen_address = ‘*’（默认localhost）
        wal_level = hot_standby（默认是minimal）
        // 表示启动搭建Hot Standby
        max_wal_senders=2（默认是0）
        // 需要设置为一个大于0的数，它表示主库最多可以有多少个并发的standby数据库
        wal_keep_segments=64（默认是0）
         [//wal_keep_segments也应当设置为一个尽量大的值，以防止主库生成WAL日志太快，日志还没有来得及传送到standby就被覆盖，但是需要考虑磁盘空间允许，一个WAL日志文件的大小是16M](//wal_keep_segments也应当设置为一个尽量大的值，以防止主库生成WAL日志太快，日志还没有来得及传送到standby就被覆盖，但是需要考虑磁盘空间允许，一个WAL日志文件的大小是16M：) :16384 KB / 1024
        ```

        一个WAL日志文件是16M，如果wal\_keep\_segments设置为64，也就是说将为standby库保留64个WAL日志文件，那么就会占用16\*64=1GB的磁盘空间，所以需要综合考虑，在磁盘空间允许的情况下设置大一些，就会减少standby重新搭建的风险。接下来还需要在主库创建一个超级用户来专门负责让standby连接去拖WAL日志：

        * **For Windows :**

          `create user repl superuser password '111111';`

        * **For Unix:**

          `/# adduser username`

          `\# usermod -aG sudo repl`

          * **Delete User:**

            `sudo userdel -r XXX`

        * 接下来打开数据目录下的pg\_hba.conf文件然后做以下修改：

          ![](https://static.notion-static.com/57d2e0dbeb0a46f5b241c03f8eeb8321/Untitled)

        重启 `$ /etc/init.d/postgresql restart`

        查看PostgreSQL的用户 `SELECT usename FROM pg_user;`

      * Configure Standby Database

        首先要通过pg\_basebackup命令行工具在从库上生成基础备份，命令如下：

        ```
        ./pg_basebackup -h 192.168.111.101 -U repl -F p -x -P -R -D /usr/local/postgresql/data/ -l replbackup20161122
        1
        ```

        参数说明 `pg_basebackup —help`

        `-h` 指定连接的数据库的主机名或IP地址，这里就是主库的ip。

        `-h` , `—host=HOSTNAME` database server host or socket directory

        `-U` 指定连接的用户名，此处是我们刚才创建的专门负责流复制的repl用户。

        `-U` , `—username=NAME` connect as specified database user

        `-F` 指定了输出的格式，支持p（原样输出）或者t（tar格式输出）

        `-F` , `—format=p|t` output format \(plain \(default\), tar\)

        `-x` 表示备份开始后，启动另一个流复制连接从主库接收WAL日志

        `-x` , `—xlog` include required WAL files in backup \(fetch mode\)

        `-P` 表示允许在备份过程中实时的打印备份的进度。

        `-P` , `—progress` show progress information

        `-R` 表示会在备份结束后自动生成recovery.conf文件，这样也就避免了手动创建。

        `-R` , `--write-recovery-conf` write recovery.conf for replication

        `-D` 指定把备份写到哪个目录，这里尤其要注意一点就是做基础备份之前从库的数据目录（/usr/local/postgresql/data）目录需要手动清空。

        `-D` , `—pgdata=DIRECTORY` receive base backup into directory

        `-l` 表示指定一个备份的标识，

        `-l` , `—label=LABEL` set backup label

        运行命令后看到如下进度提示就说明生成基础备份成功：

        ![](https://static.notion-static.com/405f622a9c91411ea45c6d5a1be96215/Untitled)

        **Problem**

        ![](https://static.notion-static.com/d62c6d740e7048f2ba3bb15e09c0612e/Untitled)

        use user **postgres ** instead

        ![](https://static.notion-static.com/fea210795be94890940ad2d72b5d7ed7/Untitled)

        ![](https://static.notion-static.com/32529dab8dcf4442bfea09b4a0db911d/Untitled)

        Got stuck😳!!!

        ![](https://static.notion-static.com/5a7b18bafce94a3d9fa1d46ca4f72c5e/Untitled)

        **Reinstall the PostgreSQL**

        `apt-get purge postgresql*`

        `sudo apt-get install postgresql postgresql-contrib`

        ![](https://static.notion-static.com/ce437adda2de450a8b9ff77718e97b05/Untitled)

        `./pg_basebackup -h 104.197.8.42 -U postgres -F p -x -P -R -D /usr/local/postgresql/data/ -l   
        replbackup20171008`

        `./pg_basebackup -h 104.197.8.42 -U postgres -F p -x -P -R -D /var/lib/postgresql/9.3/main/ -l replbackup20171008`

        ![](https://static.notion-static.com/e7ead9c847d64be8aea3571c2ab8dadf/Untitled)

        **View log Fail**

        `./pg_ctl start -l /usr/local/postgresql/log/pg_server.log`

        ![](https://static.notion-static.com/a4b6f159dbbe4a43a0242afdd393b582/Untitled)





        In GCE, you login using RSA keys, not using passwords. There are no passwords for users in google compute vm AFAIK

        `sudo -i -u postgres`

        `tail /var/log/postgresql/postgresql-9.3-main.log`

        ![](https://static.notion-static.com/a54a6bb70e494d0bad1a64044b7613da/Untitled)

        Failed 😱!!



    * Details

      * **Configure Primary Database**

        * create testTable

          ```
          $ sudo -s
          $ sudo -u postgres psql postgres

          CREATE TABLE guestbook (visitor_email text, vistor_id serial, date timestamp, message text);

          INSERT INTO guestbook (visitor_email, date, message) VALUES ( 'jim@gmail.com', current_date, 'This is a test.');

          \q
          ```

          ![](https://static.notion-static.com/2cb5c7deb9eb4ab8abfda28fd9e1106b/Untitled)

        * Configuring the primary server

          * Create a Postgres user for replication activities.
            `$ sudo -u postgres createuser -U postgres repuser -P -c 5 —replication`
            * 参数说明
              * `sudo -u postgres`  ensures that the  `createuser`  command runs as the user  `postgres` . Otherwise, Postgres will try to run the command by using peer authentication, which means the command will run under your Ubuntu user account. This account probably doesn't have the right privileges to create the new user, which would cause an error.
              * The  `-U`  option tells the  `createuser`  command to use the user  `postgres`  to create the new user.
              * The name of the new user is  `repuser` . You'll enter that username in the configuration files.
              * `-P`  prompts you for the new user's password. Important: For any system with an Internet connection, use a strong password to help keep the system secure.
              * `-c`  sets a limit for the number of connections for the new user. The value  `5`  is sufficient for replication purposes.
              * `--replication`  grants the  `REPLICATION`  privilege to the user named  `repuser` .
          * Create a directory to store archive files.
            `$ mkdir -p ../../var/lib/postgresql/main/mnt/server/archivedir`
          * Edit two configuration files:  `pg_hba.conf`  and  `postgresql.conf` .

            * `**pg_hba.conf**` ** **

              ```
              # Allow replication connections
              host replication repuser 35.188.59.0/24 md5
              ```

              ![](https://static.notion-static.com/ea0eae82988b490b8c6bf3e3339873d4/Untitled)

            * `postgresql.conf`

              ```
              wal_level = hot_standby
              archive_mode = on
              archive_command = 'test ! -f mnt/server/archivedir/%f && cp %p mnt/server/archivedir/%f'
              # This setting tells Postgres to write the archive files to the directory that you created in a previous step
              max_wal_senders = 3
              ```

* Restart the primary server
  `$ sudo service postgresql restart`
  * Backing up the primary server to the standby server
* stop the service before changing configuration!!!
  `$ sudo service postgresql stop`
* The backup utility won't overwrite existing files, so you must rename the data directory on the standby server. Run the following command:
  `$ mv ../../var/lib/postgresql/9.3/main ../../var/lib/postgresql/9.3/main_old`
* Run the backup utility
  `$ sudo -u postgres pg_basebackup -h 104.197.8.42 -D /var/lib/postgresql/9.3/main -U repuser -v -P —xlog-method=stream`
* Edit postgresql.conf :

  ```
  hot_standby = on
  ```

* Create the recovery configuration file  
  `$ cp -avr ../../usr/share/postgresql/9.3/recovery.conf.sample /../../var/lib/postgresql/9.3/main/recovery.conf`   
  `$ vim /../../var/lib/postgresql/9.3/main/recovery.conf`

      primary_conninfo = 'host= ``104.197.8.42`` port=5432 user=repuser password='
      trigger_file = '/tmp/postgresql.trigger.5432'
      # The trigger_file path that you specify is the location where you can add a file when you want the system to fail over to the standby server. The presence of the file "triggers" the failover. Alternatively, you can use the pg_ctl promote command to trigger failover.

* Start the standby server  
  `$ service postgresql start`

  ---

  * Seeing the replication at work

  ![](https://static.notion-static.com/35a1d60146d2404ca0d12f86abb71045/Untitled)

## How to partially synchronize







pglogical is a logical replication system implemented entirely as a PostgreSQL extension. Fully integrated, it requires no triggers or external programs. This alternative to physical replication is a highly efficient method of replicating data **using a publish/subscribe model for selective replication.**



pglogical is available for PostgreSQL 9.4 - 9.6, and will work with PostgreSQL 10 due to release Fall 2017.

![](https://static.notion-static.com/a221df03201d42c681c5ba62090f2b1b/Untitled)

![](https://static.notion-static.com/9940aab34dde4c158361a2ca44da69e8/Untitled)



`apt-get install lsb-core`

`lsb_release -a`

![](https://static.notion-static.com/eb3c8e2c0ba549c3b46ff7dcf5087195/Untitled)

