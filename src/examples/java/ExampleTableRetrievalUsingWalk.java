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

import com.hypofluid.snmp.Mib;
import com.hypofluid.snmp.MibFactory;
import com.hypofluid.snmp.SimpleSnmpV2cTarget;
import com.hypofluid.snmp.SnmpContext;
import com.hypofluid.snmp.SnmpFactory;
import com.hypofluid.snmp.SnmpWalker;
import com.hypofluid.snmp.VarbindCollection;

/**
 * An example that show how to retrieve the contents of a table using the
 * high level walk operation.
 *
 * @author Carl Harris
 */
class ExampleTableRetrievalUsingWalk {

  public static void main(String[] args) throws Exception {
    Mib mib = MibFactory.getInstance().newMib();
    mib.load("SNMPv2-MIB");
    mib.load("IF-MIB");

    SimpleSnmpV2cTarget target = new SimpleSnmpV2cTarget();
    target.setAddress(System.getProperty("tnm4j.agent.address", "10.0.0.1"));
    target.setCommunity(System.getProperty("tnm4j.agent.community", "public"));

    SnmpContext context = SnmpFactory.getInstance().newContext(target, mib);
    try {
      SnmpWalker<VarbindCollection> walker = context.walk(1, "sysUpTime", "ifName",
          "ifDescr", "ifAdminStatus", "ifOperStatus", "ifInOctets", "ifOutOctets");
      VarbindCollection row = walker.next().get();
      while (row != null) {
        System.out.format("%14s %-8s %-20s %-4s %-4s %,15d %,15d\n",
            row.get("sysUpTime"),
            row.get("ifName"),
            row.get("ifDescr"),
            row.get("ifAdminStatus"),
            row.get("ifOperStatus"),
            row.get("ifInOctets").asLong(),
            row.get("ifOutOctets").asLong());
        row = walker.next().get();
      }
    }
    finally {
      context.close();
    }

    SnmpFactory.getInstance().close();
  }

}
