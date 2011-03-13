/*
 * Copyright 2011 Weigle Wilczek GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.scalatip

import lib.ScalaTipsActor
import lib.ScalaTipsActor.LookupScalaTips
import akka.actor.{ Actor, Scheduler }
import java.util.concurrent.TimeUnit.SECONDS
import net.liftweb.http.{ Bootable, Html5Properties, LiftRules, Req }
import net.liftweb.common.Loggable

class Boot extends Bootable with Loggable {

  override def boot() {
    logger.debug("About to boot ScalaTip ...")

    // Lift stuff
    LiftRules addToPackages getClass.getPackage
    LiftRules.early.append { _ setCharacterEncoding "UTF-8" }
    LiftRules.htmlProperties.default.set { req: Req => new Html5Properties(req.userAgent) }

    // Akka stuff
    val scalaTipsActor = Actor.actorOf[ScalaTipsActor].start
    Scheduler.schedule(scalaTipsActor, LookupScalaTips, 1, 30, SECONDS)

    logger.debug("Successfully booted ScalaTip. Have fun!")
  }
}
