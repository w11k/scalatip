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
package lib

import com.mongodb.casbah.Imports.DBObject
import com.mongodb.casbah.commons.MongoDBObject

object ScalaTip {

  def fromMongoDBObject(obj: MongoDBObject): Option[ScalaTip] =
    for {
      user <- obj.getAs[String](User)
      date <- obj.getAs[String](Date)
      message <- obj.getAs[String](Message)
    } yield ScalaTip(user, date, message)

  implicit val mongoFormat: ScalaTip => DBObject =
    scalaTip => {
      import scalaTip._
      MongoDBObject(User -> user, Date -> date, Message -> message)
    }

  private[lib] val User = "user"

  private[lib] val Date = "date"

  private[lib] val Message = "message"
}

case class ScalaTip(user: String, date: String, message: String) {
  require(user != null, "user must not be null!")
  require(date != null, "date must not be null!")
  require(message != null, "message must not be null!")
}
