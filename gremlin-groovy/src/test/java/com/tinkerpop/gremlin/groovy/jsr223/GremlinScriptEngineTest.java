package com.tinkerpop.gremlin.groovy.jsr223;

import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraphFactory;
import junit.framework.TestCase;

import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class GremlinScriptEngineTest extends TestCase {

    public void testGremlinLoading() throws Exception {
        ScriptEngine engine = new GremlinScriptEngine();
        List list = new ArrayList();
        engine.put("g", TinkerGraphFactory.createTinkerGraph());
        engine.put("list", list);
        assertEquals(list.size(), 0);
        engine.eval("g.v(1).outE.inV.fill(list)");
        assertEquals(list.size(), 3);
    }

    public void testImports() throws Exception {
        ScriptEngine engine = new GremlinScriptEngine();
        engine.eval("new TinkerGraph()");
    }

    public void testBindings() throws Exception {
        ScriptEngine engine = new GremlinScriptEngine();
        assertTrue(engine.eval("g = TinkerGraphFactory.createTinkerGraph()") instanceof TinkerGraph);
        assertTrue(engine.get("g") instanceof TinkerGraph);
        assertEquals(engine.eval("g.v(1)"), TinkerGraphFactory.createTinkerGraph().getVertex(1));
    }

}
