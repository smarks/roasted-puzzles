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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for AdjacentRoom
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class AdjacentRoomTest {

    private RoomNode source;
    private RoomNode destination;
    private AdjacentRoom adjacentRoom;

    @Before
    public void setup() {
        source = new RoomNode("source", "source");
        destination = new RoomNode("destination", "destination");
        adjacentRoom = new AdjacentRoom(source, destination);
    }

    @Test
    public void testGetDestination() {
        assertEquals("destination matches", destination, adjacentRoom.getDestination());
    }

    @Test
    public void testGetSource() {
        assertEquals("source matches", source, adjacentRoom.getSource());
    }

    @Test
    public void testGetWeight() {
        assertEquals("weight is 1", AdjacentRoom.WEIGHT, adjacentRoom.getWeight());
    }
}
