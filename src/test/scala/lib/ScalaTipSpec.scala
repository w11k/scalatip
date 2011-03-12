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

import org.specs.Specification

class ScalaTipSpec extends Specification {

  "Creating a ScalaTip instance" should {
    "throw an IllegalArgumentException for a null user" in {
      ScalaTip(null, "time", "message") must throwA[IllegalArgumentException]
    }
    "throw an IllegalArgumentException for a null time" in {
      ScalaTip("user", null, "message") must throwA[IllegalArgumentException]
    }
    "throw an IllegalArgumentException for a null message" in {
      ScalaTip("user", "time", null) must throwA[IllegalArgumentException]
    }
  }
}
