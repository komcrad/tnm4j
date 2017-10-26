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

package com.github.komcrad.snmp.provider.snmp4j;

import com.github.komcrad.snmp.SnmpException;
import com.github.komcrad.snmp.SnmpResponse;
import com.github.komcrad.snmp.TimeoutException;

/**
 * A response that represents a successful result.
 *
 * @author Carl Harris
 */
class SuccessResponse<V> implements SnmpResponse<V> {

  private final V result;
  
  /**
   * Constructs a new instance.
   * @param result
   */
  public SuccessResponse(V result) {
    this.result = result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public V get() throws SnmpException, TimeoutException {
    return result;
  }

}
