/*
 *  Copyright  (c)  2017.  Spencer Marks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.origamisoftware.puzzle.model;

/**
 * An enum that models north, south east or west - the direction
 * a room can be adjacent to another room in the map.
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public enum CardinalPoint {

    NORTH("north"), SOUTH("south"), EAST("east"), WEST("west");

    private final String name;

    CardinalPoint(String name) {
        this.name = name;
    }

    /**
     * @return the value in the XML document representing the direction
     * of the room.
     */
    public String getName() {
        return name;
    }


}
