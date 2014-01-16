/*
 * Copyright 2005-2014 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.hornetq.utils;

import org.jboss.logging.BasicLogger;
import org.jboss.logging.LogMessage;
import org.jboss.logging.Logger;
import org.jboss.logging.Message;
import org.jboss.logging.MessageLogger;

/**
 * @author <a href="mailto:andy.taylor@jboss.org">Andy Taylor</a>
 *         3/15/12
 *
 * Logger Code 20
 *
 * each message id must be 6 digits long starting with 20, the 3rd digit donates the level so
 *
 * INF0  1
 * WARN  2
 * DEBUG 3
 * ERROR 4
 * TRACE 5
 * FATAL 6
 *
 * so an INFO message would be 201000 to 201999
 */
@MessageLogger(projectCode = "HQ")
public interface HornetQUtilLogger extends BasicLogger
{
   /**
    * The default logger.
    */
   HornetQUtilLogger LOGGER = Logger.getMessageLogger(HornetQUtilLogger.class, HornetQUtilLogger.class.getPackage().getName());

   @LogMessage(level = Logger.Level.WARN)
   @Message(id = 202000, value = "Missing privileges to set Thread Context Class Loader on Thread Factory. Using current Thread Context Class Loader",
         format = Message.Format.MESSAGE_FORMAT)
   void missingPrivsForClassloader();
}
