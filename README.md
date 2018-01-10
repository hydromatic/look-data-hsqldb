# chinook-data-hsqldb
Chinook data set in hsqldb format

This project contains the Chinook data set as an embedded
HSQLDB database.

It originated as the
[Chinook database](https://github.com/lerocha/chinook-database)
version 1.4, which itself is an alternative to the
[Northwind database](https://docs.microsoft.com/en-us/dotnet/framework/data/adonet/sql/linq/downloading-sample-databases).

# Schema

Chinook's schema consists of 11 tables:

| Table         | Row count |
| :------------ | --------: |
| Album         | 347       |
| Artist        | 275       |
| Customer      | 59        |
| Employee      | 8         |
| Genre         | 25        |
| Invoice       | 412       |
| InvoiceLine   | 2,240     |
| MediaType     | 5         |
| Playlist      | 18        |
| PlaylistTrack | 8,715     |
| Track         | 3,503     |

Its size is about 900 KB uncompressed, 160 KB compressed.

Here is a schema diagram:

![Chinook schema diagram](schema.png)

# Using the data set

The data set is packaged as a jar file that is published to
[Maven Central](https://search.maven.org/#search%7Cga%7C1%7Ca%3Achinook-data-hsqldb)
as a Maven artifact. To use the data in your Java application,
add the artifact to your project's dependencies:

```xml
<dependency>
  <groupId>net.hydromatic</groupId>
  <artifactId>chinook-data-hsqldb</artifactId>
  <version>0.1</version>
</dependency>
```

Now you can connect using Java code:

```java
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

final String url = "jdbc:hsqldb:res:chinook";
final String sql = "select EmployeeId, LastName from Employee";
try (Connection c = DriverManager.getConnection(url, "sa", "");
    Statement s = c.createStatement();
    ResultSet r = s.executeQuery(sql)) {
  while (r.next()) {
    System.out.println(r.getInt(1) + ":" + r.getString(2));
  }
}
```

You can also connect using a JDBC interface such as [sqlline](https://github.com/julianhyde/sqlline).
Make sure that `chinook-data-hsqldb.jar` is on the class path, and start `sqlline`:

```sql
$ ./sqlline
sqlline version 1.3.0
sqlline> !connect jdbc:hsqldb:res:chinook sa ""
0: jdbc:hsqldb:res:chinook> select count(*) from Track;
+----------------------+
|          C1          |
+----------------------+
| 3503                 |
+----------------------+
1 row selected (0.004 seconds)
0: jdbc:hsqldb:res:chinook> !quit
```

## Get chinook-data-hsqldb

### From Maven

Get chinook-data-hsqldb from
<a href="https://search.maven.org/#search%7Cga%7C1%7Cg%3Anet.hydromatic%20a%3Achinook-data-hsqldb">Maven Central</a>:

```xml
<dependency>
  <groupId>net.hydromatic</groupId>
  <artifactId>chinook-data-hsqldb</artifactId>
  <version>0.1</version>
</dependency>
```

### Download and build

```bash
$ git clone git://github.com/julianhyde/chinook-data-hsqldb.git
$ cd chinook-data-hsqldb
$ mvn install
```

## See also

Similar data sets:
* [foodmart-data-hsqldb](https://github.com/julianhyde/foodmart-data-hsqldb)
* [scott-data-hsqldb](https://github.com/julianhyde/scott-data-hsqldb)

## More information

* License: Apache License, Version 2.0
* Author: Julian Hyde
* Blog: http://julianhyde.blogspot.com
* Project page: http://www.hydromatic.net/chinook-data-hsqldb
* Source code: http://github.com/julianhyde/chinook-data-hsqldb
* Developers list:
  <a href="mailto:dev@calcite.apache.org">dev at calcite.apache.org</a>
  (<a href="http://mail-archives.apache.org/mod_mbox/calcite-dev/">archive</a>,
  <a href="mailto:dev-subscribe@calcite.apache.org">subscribe</a>)
* Issues: https://github.com/julianhyde/chinook-data-hsqldb/issues
* <a href="HISTORY.md">Release notes and history</a>
