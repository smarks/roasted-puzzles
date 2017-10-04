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

import com.origamisoftware.puzzle.graph.Edge;

import java.util.List;

/**
 * A basic interface for a Node or Vertex used by graph algorithms.
 * The interface allows us to have specific implementations of Vertex
 * which could contain additional information about the Node which is not
 * required by the algorithm, but useful to other parts of the data model.
 * <p>
 * For example, a RoomNode implements this interface, but provides
 * additional information about the room.
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 * @see RoomNode
 */
public interface Vertex {

    /**
     * A unique id for the Vertex
     *
     * @return a String value
     */
    String getId();

    /**
     * A label for the Vertex. It does not have to be unique.
     *
     * @return a String value.
     */
    String getName();

    List<Edge> getEdges();
}