/*
 * File created on Apr 13, 2015
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

import com.hypofluid.snmp.BlockingQueueSnmpCompletionService;
import com.hypofluid.snmp.Mib;
import com.hypofluid.snmp.MibFactory;
import com.hypofluid.snmp.SimpleSnmpV2cTarget;
import com.hypofluid.snmp.SnmpCompletionService;
import com.hypofluid.snmp.SnmpEvent;
import com.hypofluid.snmp.SnmpFactory;
import com.hypofluid.snmp.SnmpOperation;
import com.hypofluid.snmp.VarbindCollection;

/**
 * An example that illustrates the use of {@link SnmpCompletionService}
 *
 * @author Carl Harris
 */
public class ExampleUsingCompletionService {

  public static void main(String[] args) throws Exception {
    Mib mib = MibFactory.getInstance().newMib();
    mib.load("SNMPv2-MIB");

    SnmpCompletionService<VarbindCollection> completionService =
        new BlockingQueueSnmpCompletionService<VarbindCollection>();

    String address = System.getProperty("tnm4j.agent.address", "10.0.0.1");
    String community = System.getProperty("tnm4j.agent.community", "public");

    completionService.submit(newOperation(address, community, mib));

    while (!completionService.isIdle()) {
      SnmpEvent<VarbindCollection> event = completionService.take();
      VarbindCollection result = event.getResponse().get();
      System.out.format("%s: sysName=%s sysUpTime=%s\n",
          event.getContext().getTarget().getAddress(),
          result.get("sysName"),
          result.get("sysUpTime"));

      event.getContext().close();
    }

  }

  private static SnmpOperation<VarbindCollection> newOperation(String address,
      String community, Mib mib) {
    SimpleSnmpV2cTarget target = new SimpleSnmpV2cTarget();
    target.setAddress(address);
    target.setCommunity(community);

    return SnmpFactory.getInstance().newContext(target, mib)
        .newGetNext("sysName", "sysUpTime");
  }

}
