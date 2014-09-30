/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hydromatic.optiq.test;

import net.hydromatic.optiq.jdbc.OptiqConnection;

import org.eigenbase.sql.test.SqlOperatorBaseTest;
import org.eigenbase.sql.test.SqlTester;

/**
 * Embodiment of {@link org.eigenbase.sql.test.SqlOperatorBaseTest}
 * that generates SQL statements and executes them using Calcite.
 */
public class OptiqSqlOperatorTest extends SqlOperatorBaseTest {
  private static final ThreadLocal<OptiqConnection> LOCAL =
      new ThreadLocal<OptiqConnection>() {
        @Override protected OptiqConnection initialValue() {
          try {
            return OptiqAssert.getConnection("hr");
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
      };

  private static SqlTester getHrTester() {
    return tester(LOCAL.get());
  }

  public OptiqSqlOperatorTest() {
    super(false, getHrTester());
  }
}

// End OptiqSqlOperatorTest.java
