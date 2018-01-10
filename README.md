<!--
{% comment %}
Licensed to Julian Hyde under one or more contributor license
agreements.  See the NOTICE file distributed with this work
for additional information regarding copyright ownership.
Julian Hyde licenses this file to you under the Apache
License, Version 2.0 (the "License"); you may not use this
file except in compliance with the License.  You may obtain a
copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied.  See the License for the specific
language governing permissions and limitations under the
License.
{% endcomment %}
-->
[![Build Status](https://github.com/hydromatic/look-data-hsqldb/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/hydromatic/look-data-hsqldb/actions?query=branch%3Amain)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.hydromatic/look-data-hsqldb/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.hydromatic/look-data-hsqldb)

# look-data-hsqldb
'The Look' data set in hsqldb format

This project contains the 'The Look' data set as an embedded
HSQLDB database.

It originated as an internal test data set for the Looker business
intelligence (BI) tool, modeling a fictitious fashion business.

# Schema

The Look's schema consists of 5 tables:

| Table               | Row count |
| :------------------ | --------: |
| all_types           | 1         |
| nested_and_repeated | 2         |
| orders              | 4,066     |
| order_items         | 12,142    |
| users               | 85        |

Its size is about 970 KB uncompressed, 160 KB compressed.

# Using the data set

The data set is packaged as a jar file that is published to
[Maven Central](https://search.maven.org/#search%7Cga%7C1%7Ca%3Alook-data-hsqldb)
as a Maven artifact. To use the data in your Java application,
add the artifact to your project's dependencies:

```xml
<dependency>
  <groupId>net.hydromatic</groupId>
  <artifactId>look-data-hsqldb</artifactId>
  <version>0.1</version>
</dependency>
```

Now you can connect using Java code:

```java
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

final String url = "jdbc:hsqldb:res:look";
final String sql = "select \"id\", \"name\" from \"users\"";
try (Connection c = DriverManager.getConnection(url, "looker", "looker");
    Statement s = c.createStatement();
    ResultSet r = s.executeQuery(sql)) {
  while (r.next()) {
    System.out.println(r.getInt(1) + ":" + r.getString(2));
  }
}
```

For your convenience, the
[net.hydromatic.look.data.hsqldb.LookHsqldb](https://javadoc.io/doc/net.hydromatic/look-data-hsqldb/latest/net/hydromatic/look/data/hsqldb/LookHsqldb.html)
class has public constants:

* [URI](https://javadoc.io/doc/net.hydromatic/look-data-hsqldb/latest/net/hydromatic/look/data/hsqldb/LookHsqldb.html#URI) = "jdbc:hsqldb:res:look"
* [USER](https://javadoc.io/doc/net.hydromatic/look-data-hsqldb/latest/net/hydromatic/look/data/hsqldb/LookHsqldb.html#USER) = "looker"
* [PASSWORD](https://javadoc.io/doc/net.hydromatic/look-data-hsqldb/latest/net/hydromatic/look/data/hsqldb/LookHsqldb.html#PASSWORD) = "looker"
* [SCHEMA](https://javadoc.io/doc/net.hydromatic/look-data-hsqldb/latest/net/hydromatic/look/data/hsqldb/LookHsqldb.html#SCHEMA) = "look"

You can also connect using a JDBC interface such as [sqlline](https://github.com/julianhyde/sqlline).
Make sure that `look-data-hsqldb.jar` is on the class path, and start `sqlline`:

```sql
$ ./sqlline
sqlline version 1.12.0
sqlline> !connect jdbc:hsqldb:res:look sa ""
0: jdbc:hsqldb:res:look> select count(*) from "look"."orders";
+------+
|  C1  |
+------+
| 4066 |
+------+
1 row selected (0.004 seconds)
0: jdbc:hsqldb:res:look> !quit
```

If you use username and password "looker" and "looker", the default
schema is "look", so you can omit the table prefix, if you wish:

```sql
$ ./sqlline
sqlline version 1.12.0
sqlline> !connect jdbc:hsqldb:res:look looker looker
0: jdbc:hsqldb:res:look> select count(*) from "users";
+------+
|  C1  |
+------+
| 4066 |
+------+
1 row selected (0.004 seconds)
0: jdbc:hsqldb:res:look> !quit
```

## Get look-data-hsqldb

### From Maven

Get look-data-hsqldb from
<a href="https://search.maven.org/#search%7Cga%7C1%7Cg%3Anet.hydromatic%20a%3Alook-data-hsqldb">Maven Central</a>:

```xml
<dependency>
  <groupId>net.hydromatic</groupId>
  <artifactId>look-data-hsqldb</artifactId>
  <version>0.1</version>
</dependency>
```

### Download and build

Java version 8 or higher.

```bash
$ git clone git://github.com/hydromatic/look-data-hsqldb.git
$ cd look-data-hsqldb
$ ./mvnw install
```

On Windows, the last line is

```bash
> mvnw install
```

## See also

Similar data sets:
* [chinook-data-hsqldb](https://github.com/julianhyde/chinook-data-hsqldb)
* [flight-data-hsqldb](https://github.com/julianhyde/flight-data-hsqldb)
* [foodmart-data-hsqldb](https://github.com/julianhyde/foodmart-data-hsqldb)
* [scott-data-hsqldb](https://github.com/julianhyde/scott-data-hsqldb)
* [steelwheels-data-hsqldb](https://github.com/julianhyde/steelwheels-data-hsqldb)

## More information

* License: Apache License, Version 2.0
* Author: Julian Hyde
* Blog: http://blog.hydromatic.net
* Project page: http://www.hydromatic.net/look-data-hsqldb
* Source code: https://github.com/hydromatic/look-data-hsqldb
* Distribution: <a href="https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22look-data-hsqldb%22">Maven Central</a>
* Developers list:
  <a href="mailto:dev@calcite.apache.org">dev at calcite.apache.org</a>
  (<a href="https://mail-archives.apache.org/mod_mbox/calcite-dev/">archive</a>,
  <a href="mailto:dev-subscribe@calcite.apache.org">subscribe</a>)
* Issues: https://github.com/hydromatic/look-data-hsqldb/issues
* <a href="HISTORY.md">Release notes and history</a>
