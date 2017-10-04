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

import com.origamisoftware.puzzle.model.Vertex;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.List;


/**
 * A Graph is a collection of RoomNodes (vertexes) and Edges.
 * <p>
 * An edge is basically the line between two vertexes (or RoomNodes).
 * <p>
 * This class will contain all the RoomNodes and Edges as parsed from the input data.
 * (The map.xml file)
 * <p>
 * This models an unweighted graph, connected graph.
 * <p>
 * The Graph class is immutable.
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
@Immutable
public class Graph {

    /**
     * All the vertexes in the graph
     */
    private final List<Vertex> vertexes;

    /**
     * All the edges in the graph
     */
    private final List<Edge> edges;

    /**
     * Create a new Graph instance.
     *
     * @param vertexes the number of rooms (aka Nodes, aka vertexes)
     * @param edges    the 'lines' or routes contenting the rooms.
     */
    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    /**
     * @return the list of RoomNode associated with this Graph
     */
    List<Vertex> getVertexes() {
        return vertexes;
    }

    /**
     * @return the list of Edges associated with this Graph
     */
    List<Edge> getEdges() {
        return edges;
    }


}
