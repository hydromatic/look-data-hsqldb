/*
 * Licensed to Julian Hyde under one or more contributor license
 * agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership. Julian Hyde
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hydromatic.look.data.hsqldb;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Kick the tires.
 */
public class LookHsqldbTest {
  @Test public void testSniffData() throws SQLException {
    try (Connection connection =
             DriverManager.getConnection(LookHsqldb.URI, LookHsqldb.USER,
                 LookHsqldb.PASSWORD);
         Statement s = connection.createStatement()) {
      checkData(s, "select * from \"all_types\" order by 1", 1, is(1));
      checkData(s, "select * from \"nested_and_repeated\" order by 1", 1, is(2));
      checkData(s, "select * from \"users\" order by 1", 2, is(85));
      checkData(s, "select * from \"orders\" order by 1", 2, is(4_066));
      checkData(s, "select * from \"order_items\" order by 1", 2, is(12_142));
    }
  }

  private void checkData(Statement statement, String sql, int printLimit,
      Matcher<Integer> rowCountMatcher) throws SQLException {
    try (ResultSet resultSet = statement.executeQuery(sql)) {
      final ResultSetMetaData metaData = resultSet.getMetaData();
      final int columnCount = metaData.getColumnCount();
      int row = 0;
      while (resultSet.next()) {
        if (row++ >= printLimit || printLimit < 0) {
          continue;
        }
        for (int i = 0; i < columnCount; i++) {
          if (i > 0) {
            System.out.print(", ");
          }
          System.out.print(metaData.getColumnLabel(i + 1));
          System.out.print(": ");
          System.out.print(resultSet.getObject(i + 1));
        }
        System.out.println();
      }
      assertThat(row, rowCountMatcher);
    }
  }
}

// End LookHsqldbTest.java
