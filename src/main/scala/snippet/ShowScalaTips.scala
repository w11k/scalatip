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
package snippet

import akka.actor.Actor
import lib.{ ScalaTip, ScalaTipsActor }
import net.liftweb.util.BindHelpers
import net.liftweb.util.ClearClearable
import scala.collection.immutable.Seq
import scala.xml.Unparsed

object ShowScalaTips {

  def render = {
    import BindHelpers._
    def renderScalaTip(scalaTip: ScalaTip) = {
      ".user *" #> Unparsed(scalaTip.user) &
      ".date *" #> Unparsed(scalaTip.date) &
      ".message *" #> Unparsed(scalaTip.message)
    }
    val scalaTips = {
      import Actor._
      import ScalaTipsActor._
      Actor.registry.actorFor[ScalaTipsActor].headOption flatMap { scalaTipsActor =>
        (scalaTipsActor !! GetAllScalaTips).as[Seq[ScalaTip]]
      } getOrElse Nil
    }
    ".tip" #> (scalaTips map renderScalaTip) &
        ClearClearable
  }
}
