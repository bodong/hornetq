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
package org.hornetq.rest.test;
import java.lang.reflect.Field;

import org.hornetq.rest.MessageServiceManager;
import org.hornetq.rest.util.LinkHeaderLinkStrategy;
import org.hornetq.rest.util.LinkStrategy;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.BaseClientResponse;
import org.jboss.resteasy.spi.Link;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 */
public class MessageTestBase
{
   public static Embedded server;
   public static MessageServiceManager manager;
   private static Field executorField;

   static
   {
      try
      {
         executorField = BaseClientResponse.class.getDeclaredField("executor");
      }
      catch (NoSuchFieldException e)
      {
         throw new RuntimeException(e);
      }
      executorField.setAccessible(true);
   }

   @BeforeClass
   public static void setupHornetQServerAndManager() throws Exception
   {
      server = new Embedded();
      server.start();
      manager = server.getManager();
   }

   @AfterClass
   public static void shutdownHornetqServerAndManager() throws Exception
   {
      manager = null;
      server.stop();
      server = null;
   }

   public static Link getLinkByTitle(LinkStrategy strategy, ClientResponse response, String title)
   {
      if (strategy instanceof LinkHeaderLinkStrategy)
      {
         return response.getLinkHeader().getLinkByTitle(title);
      }
      else
      {
         String headerName = "msg-" + title;
         String href = (String) response.getHeaders().getFirst(headerName);
         if (href == null) return null;
         //System.out.println(headerName + ": " + href);
         Link l = new Link(title, null, href, null, null);
         try
         {
            l.setExecutor((ClientExecutor) executorField.get(response));
         }
         catch (IllegalAccessException e)
         {
            throw new RuntimeException(e);
         }
         return l;
      }
   }

}
