/*
 * File created on Apr 14, 2015
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
import com.hypofluid.snmp.SnmpCallback;
import com.hypofluid.snmp.SnmpContext;
import com.hypofluid.snmp.SnmpEvent;
import com.hypofluid.snmp.SnmpException;
import com.hypofluid.snmp.SnmpFactory;
import com.hypofluid.snmp.SnmpListener;
import com.hypofluid.snmp.SnmpNotificationEvent;
import com.hypofluid.snmp.SnmpNotificationHandler;
import com.hypofluid.snmp.VarbindCollection;

/**
 * An example that shows how to perform a GETNEXT operation asynchronously.
 *
 * @author Carl Harris
 */
public class ExampleNotificationHandling {

  public static void main(String[] args) throws Exception {
    Mib mib = MibFactory.getInstance().newMib();
    mib.load("SNMPv2-MIB");

    SnmpListener listener = SnmpFactory.getInstance().newListener(10162, mib);
    try {
      listener.addHandler(new SnmpNotificationHandler() {
        @Override
        public Boolean handleNotification(SnmpNotificationEvent event) {
          System.out.println("received a notification: " + event);
          return true;
        }
      });

      Thread.sleep(60000L);     // wait for some notifications to arrive
    }
    finally {
      listener.close();         // listeners must be closed when no longer needed
    }
  }

}