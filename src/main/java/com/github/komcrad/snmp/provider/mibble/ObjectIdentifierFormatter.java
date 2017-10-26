/*
 * tnm4j - Simplified SNMP API for Java
 * Copyright (C) 2012 Carl Harris, Jr
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.komcrad.snmp.provider.mibble;

import com.github.komcrad.snmp.Formatter;

class ObjectIdentifierFormatter implements Formatter {

  public String format(Object value) {
    int[] oid = (int[]) value;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < oid.length; i++) {
      sb.append(oid[i]);
      if (i < oid.length - 1) {
        sb.append('.');
      }
    }
    return sb.toString();
  }

}
