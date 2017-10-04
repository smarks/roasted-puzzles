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
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for the Graph class
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class GraphTest {

    private List<Vertex> vertexes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private Graph graph;

    @Before
    public void setup() {
        graph = new Graph(vertexes, edges);
    }

    @Test
    public void testGetVertex() {
        assertEquals("Vertex match", vertexes, graph.getVertexes());
    }

    @Test
    public void testGetEdges() {
        assertEquals("Edges match", edges, graph.getEdges());
    }

}
