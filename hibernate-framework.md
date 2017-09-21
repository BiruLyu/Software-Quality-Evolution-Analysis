 
+ #### What's the different between `merge()` and `saveandupdate()`?
+ #### Increment Seq
  ``` @GeneratedValue(strategy = GenerationType.AUTO)```
+ #### DDL AUTO
  Although it is quite an old post but as i did some research on the topic so thought of sharing it.
  ```
  hibernate.hbm2ddl.auto
  ```
+ #### As per the documentation it can have four valid values:

  ```create | update | validate | create-drop```

Following is the explanation of the behaviour shown by these value:

`create ` :  create the schema, the data previously present (if there) in the schema is lost
`update` : update the schema with the given values.
`validate`: 
- validate the schema. It makes no change in the DB.
`create-drop`:
- create the schema with destroying the data previously present(if there). It also drop the database schema when the SessionFactory is closed.
Following are the important points worth noting:
- In case of `update`, if schema is not present in the DB then the schema is created.
- In case of `validate`, if schema does not exists in DB, it is not created. Instead, it will throw an error:
- `Table not found:<table name>`
In case of ```create-drop```, schema is not dropped on closing the session. It drops only on closing the SessionFactory.
In case if i give any value to this property(say abc, instead of above four values discussed above) or it is just left blank. It shows following behaviour:
- If schema is not present in the DB:
  - It creates the schema
- If schema is present in the DB:
  - update the schema.
  
+ #### `varchar(255)`  VS `text`
  Some months have passed, new knowledge aquired, so I'll answer my own question:

  ```
  @Lob
  @Column
  final String someString;
  ```
  yields the most correct result. With the version of `hbm2ddl` I'm using, this will be transformed to the type `text` with `SqlServerDialect`. Since `varchar(max)` is the replacement for `text` in newer versions of SqlServer, hopefully, newer versions of `hbm2ddl` will yield `varchar(max)` instead of `text` for this type of mapping (I'm stuck at a quite dated version of Hibernate at the moment..)