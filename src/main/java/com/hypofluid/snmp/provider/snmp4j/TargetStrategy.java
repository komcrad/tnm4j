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
package com.hypofluid.snmp.provider.snmp4j;

import org.snmp4j.Target;

import com.hypofluid.snmp.SnmpTarget;

/**
 * A strategy for creating an Snmp4j {@link Target} for given subtype of
 * {@link SnmpTarget}.
 *  *
 * @author Carl Harris
 */
interface TargetStrategy {

  Target newTarget(SnmpTarget target);
  
}
