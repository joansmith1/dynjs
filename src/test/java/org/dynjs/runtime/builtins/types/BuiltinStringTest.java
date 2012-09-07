package org.dynjs.runtime.builtins.types;

import static org.fest.assertions.Assertions.*;

import org.dynjs.exception.ThrowException;
import org.dynjs.runtime.AbstractDynJSTestSupport;
import org.dynjs.runtime.PrimitiveDynObject;
import org.junit.Test;

public class BuiltinStringTest extends AbstractDynJSTestSupport {

    @Test
    public void testFromCharCode() {
        assertThat(eval("String.fromCharCode(66, 79, 66)")).isEqualTo("BOB");
    }

    @Test
    public void testConstructorWithString() {
        PrimitiveDynObject o = (PrimitiveDynObject) eval("new String('howdy')");
        assertThat(o.getClassName()).isEqualTo("String");
        assertThat(o.getPrimitiveValue()).isEqualTo("howdy");
    }

    @Test
    public void testConstructorWithNonString() {
        PrimitiveDynObject o = (PrimitiveDynObject) eval("new String(42)");
        assertThat(o.getClassName()).isEqualTo("String");
        assertThat(o.getPrimitiveValue()).isEqualTo("42");
    }

    @Test
    public void testValueOf() {
        Object o = eval("new String(42).valueOf()");
        assertThat(o).isEqualTo("42");
    }

    @Test
    public void testValueOfOnLiteral() {
        Object o = eval("'taco'.valueOf()");
        assertThat(o).isEqualTo("taco");
    }

    @Test
    public void testToString() {
        Object o = eval("new String(42).toString()");
        assertThat(o).isEqualTo("42");
    }

    @Test
    public void testCharAtWithinBounds() {
        Object o = eval("new String(42).charAt(1)");
        assertThat(o).isEqualTo("2");
    }

    @Test(expected = ThrowException.class)
    public void testToStringMustThrowIfNotAPrimitiveResult() {
        eval("var obj = { toString:function(){ return new Object(); } };",
                "String(obj)");
    }

    @Test
    public void testCharCodeAt() {
        assertThat(eval("'BOB'.charCodeAt(2)")).isEqualTo(66L);
    }

    @Test
    public void testConcat() {
        assertThat(eval("'bob'.concat( 'lance', 'qmx' )")).isEqualTo("boblanceqmx");
    }

    @Test
    public void testIndexOf() {
        assertThat(eval("'bob'.indexOf('o')")).isEqualTo(1L);
    }

    @Test
    public void testIndexOfWithPos() {
        assertThat(eval("'bobobo'.indexOf('o',2)")).isEqualTo(3L);
    }

    @Test
    public void testIndexOfNotFound() {
        assertThat(eval("'bobobo'.indexOf('taco',2)")).isEqualTo(-1L);
    }
    
    @Test
    public void testLastIndexOf() {
        assertThat( eval( "'bob'.lastIndexOf('b')")).isEqualTo(2L);
    }
    
    @Test
    public void testLastIndexOfWithPos() {
        assertThat( eval( "'bob'.lastIndexOf('b',1)")).isEqualTo(0L);
    }
    
    @Test
    public void testLastIndexOfNotFound() {
        assertThat(eval("'bobobo'.lastIndexOf('taco',2)")).isEqualTo(-1L);
    }
    
    

}
