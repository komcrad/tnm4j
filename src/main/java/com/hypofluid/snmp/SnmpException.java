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
package com.hypofluid.snmp;

/**
 * Base class for any SNMP exceptions.
 *
 * @author Carl Harris
 */
public class SnmpException extends RuntimeException {

  private static final long serialVersionUID = -713788800474374648L;

  /**
   * Constructs a new instance.
   */
  public SnmpException() {
    super();
  }

  /**
   * Constructs a new instance.
   * @param message
   * @param cause
   */
  public SnmpException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new instance.
   * @param message
   */
  public SnmpException(String message) {
    super(message);
  }

  /**
   * Constructs a new instance.
   * @param cause
   */
  public SnmpException(Throwable cause) {
    super(cause);
  }

}
