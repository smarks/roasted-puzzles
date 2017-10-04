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

package com.origamisoftware.puzzle.graph;

import com.origamisoftware.puzzle.model.AdjacentRoom;
import com.origamisoftware.puzzle.model.Vertex;

/**
 * An Edge models 'the line' between two nodes in a Graph.
 * <p>
 * e.g.
 * <p>
 * A --- B
 * <p>
 * The dashes are the edge
 * <p>
 * This distance can have a weight which could
 * make some edges more costly to traverse than others.
 * This value is used in weighted graphs and is required by
 * some  graph algorithms.
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 * @see AdjacentRoom
 */
public interface Edge {

    /**
     * The starting point of the  edge. An Edge is
     * <p>
     * A --- B
     * <p>
     * This is the A value.
     *
     * @return a room node
     */
    Vertex getSource();

    /**
     * The far end of the edge. An Edge is
     * <p>
     * A --- B
     * <p>
     * This is the B value.
     *
     * @return a room node
     */
    Vertex getDestination();

    /**
     * The 'weight' of this edge.
     * (Used in weighted graphs)
     *
     * @return an int value
     */
    int getWeight();

}