/*
 * File created on Apr 12, 2015
 *
 * Copyright (c) 2015 Carl Harris, Jr
 * and others as noted
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.List;

import com.hypofluid.snmp.*;

/**
 * An example that show how to retrieve the contents of a table using GETBULK.
 *
 * @author Carl Harris
 */
class ExampleTableRetrievalUsingGetBulk {

  public static void main(String[] args) throws Exception {
    Mib mib = MibFactory.getInstance().newMib();
    mib.load("SNMPv2-MIB");
    mib.load("IF-MIB");

    SimpleSnmpV2cTarget target = new SimpleSnmpV2cTarget();
    target.setAddress(System.getProperty("tnm4j.agent.address", "10.0.0.1"));
    target.setCommunity(System.getProperty("tnm4j.agent.community", "public"));

    Thread.sleep(15000);

    SnmpContext context = SnmpFactory.getInstance().newContext(target, mib);
    try {
      final int maxRepetitions = 10;

      final String[] columns = {
          "sysUpTime", "ifName", "ifDescr", "ifAdminStatus", "ifOperStatus",
          "ifInOctets", "ifOutOctets"
      };

      List<VarbindCollection> rows =
          context.getBulk(1, maxRepetitions, columns).get();

      outer:
      while (!rows.isEmpty()) {
        VarbindCollection lastRow = null;
        inner:
        for (VarbindCollection row : rows) {

          if (row.get("ifName") == null) break outer;

          if (row.size() < columns.length) break inner;

          lastRow = row;
          System.out.format("%14s %-8s %-20s %-4s %-4s %,15d %,15d\n",
              row.get("sysUpTime"),
              row.get("ifName"),
              row.get("ifDescr"),
              row.get("ifAdminStatus"),
              row.get("ifOperStatus"),
              row.get("ifInOctets").asLong(),
              row.get("ifOutOctets").asLong());
        }
        if (lastRow == null) {
          System.err.println("incomplete first row; too many objects requested");
          break outer;
        }
        rows = context.getBulk(1, maxRepetitions,
            lastRow.nextIdentifiers("sysUpTime")).get();
      }
    }
    finally {
      context.close();
    }

    Thread.sleep(10000);
    SnmpFactory.getInstance().close();
  }

}
