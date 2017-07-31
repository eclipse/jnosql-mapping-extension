/*
 *  Copyright (c) 2017 Otávio Santana and others
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */
package org.jnosql.artemis.graph;

import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.jnosql.diana.api.Value;

final class TinkerPopUtil {

    private TinkerPopUtil() {
    }

    static ArtemisVertex toArtemisVertex(Vertex vertex) {
        ArtemisVertex artemisVertex = ArtemisVertex.of(vertex.label(), vertex.id());
        vertex.keys().stream().forEach(k -> artemisVertex.add(k, Value.of(vertex.value(k))));
        return artemisVertex;
    }


    static <OUT, IN> EdgeEntity<OUT, IN> toEdgeEntity(Edge edge, VertexConverter converter) {
        ArtemisVertex inVertex = TinkerPopUtil.toArtemisVertex(edge.inVertex());
        ArtemisVertex outVertex = TinkerPopUtil.toArtemisVertex(edge.outVertex());
        return new DefaultEdgeEntity<>(edge, converter.toEntity(inVertex),
                converter.toEntity(outVertex));
    }
}