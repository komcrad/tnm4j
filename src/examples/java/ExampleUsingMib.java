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
import com.hypofluid.snmp.VarbindCollection;

/**
 * An example that shows how to use a MIB to perform a GETNEXT operation using
 * named objects.
 *
 * @author Carl Harris
 */
public class ExampleUsingMib {

  public static void main(String[] args) throws Exception {
    Mib mib = MibFactory.getInstance().newMib();
    mib.load("SNMPv2-MIB");

    SimpleSnmpV2cTarget target = new SimpleSnmpV2cTarget();
    target.setAddress(System.getProperty("tnm4j.agent.address", "10.0.0.1"));
    target.setCommunity(System.getProperty("tnm4j.agent.community", "public"));

    SnmpContext context = SnmpFactory.getInstance().newContext(target, mib);
    try {
      VarbindCollection result = context.getNext("sysUpTime").get();
      System.out.println(result.get("sysUpTime"));
    }
    finally {
      context.close();
    }
  }

}
