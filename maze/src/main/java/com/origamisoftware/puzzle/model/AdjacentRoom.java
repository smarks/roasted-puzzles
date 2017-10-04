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

/**
 * Models adjoining rooms (in more general graph parlance, an edge)
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 **/
public class AdjacentRoom implements Edge {

    /**
     * The staring point
     */
    private final RoomNode source;

    /**
     * The end point
     */
    private final RoomNode destination;

    /**
     * In some graphs (in some use cases), Edges can have a different weights associated with each one.
     * E.g. a weighted graph.
     * Our use case does not require varying weight edges. That is, while our graph is technically weighted
     * all of them have a weight of one. We use a weighted graph because Dijkstra's shortest path Algorithm
     * (which we use in this program) requires the weight as input.
     * <p>
     * However, for our use, this value never varies and we just 1.
     */
    final static int WEIGHT = 1;

    /**
     * Create an Edge
     *
     * @param source      the starting node
     * @param destination the ending node
     */
    AdjacentRoom(RoomNode source, RoomNode destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public Vertex getSource() {
        return source;
    }

    @Override
    public Vertex getDestination() {
        return destination;
    }

    /**
     * Always 1 (since the puzzle does not required a weighted graph but
     * Dijkstra Algorithm expects some weight value.
     *
     * @return the weight between the source and destination
     */
    public int getWeight() {
        return WEIGHT;
    }

    @Override
    public String toString() {
        return "Edge{" + "source=" + source + ", destination=" + destination + '}';
    }


}
