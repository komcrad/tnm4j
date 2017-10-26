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

import com.hypofluid.snmp.SimpleSnmpV2cTarget;
import com.hypofluid.snmp.SnmpContext;
import com.hypofluid.snmp.SnmpFactory;
import com.hypofluid.snmp.VarbindCollection;

/**
 * An example that shows a simple GET operation using the numeric object
 * identifiers for the {@code sysUpTime} object in the standard MIB.
 *
 * @author Carl Harris
 */
public class ExampleGetOperation {

  public static void main(String[] args) throws Exception {
    SimpleSnmpV2cTarget target = new SimpleSnmpV2cTarget();
    target.setAddress(System.getProperty("tnm4j.agent.address", "10.0.0.1"));
    target.setCommunity(System.getProperty("tnm4j.agent.community", "public"));

    SnmpContext context = SnmpFactory.getInstance().newContext(target);
    try {
      VarbindCollection result = context.get("1.3.6.1.2.1.1.3.0").get();
      System.out.println(result.get(0));
    }
    finally {
      context.close();
    }

  }

}
