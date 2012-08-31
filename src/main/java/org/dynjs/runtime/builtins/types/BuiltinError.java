package org.dynjs.runtime.builtins.types;

import org.dynjs.runtime.AbstractNativeFunction;
import org.dynjs.runtime.DynArray;
import org.dynjs.runtime.DynObject;
import org.dynjs.runtime.ExecutionContext;
import org.dynjs.runtime.GlobalObject;
import org.dynjs.runtime.JSObject;
import org.dynjs.runtime.builtins.types.error.ToString;

public class BuiltinError extends AbstractNativeFunction {

    public BuiltinError(final GlobalObject globalObject) {
        super(globalObject);
        
        final DynObject proto = new DynObject( globalObject );
        put(null, "prototype", proto, false );
        proto.setClassName("Error");
        proto.put(null, "constructor", this, false );
        proto.put(null, "name", "Error", false );
        proto.put(null, "message", "", false );
        proto.put(null, "toString", new ToString(globalObject), false );
        
        setPrototype( globalObject.getPrototypeFor( "Function" ));
    }

    @Override
    public Object call(ExecutionContext context, Object self, final Object... args) {
        return self;
    }

    @Override
    public JSObject createNewObject(ExecutionContext context) {
        DynObject o = new DynObject( context.getGlobalObject() );
        o.setClassName("Error");
        return o;
    }

    // ----------------------------------------------------------------------

    public static DynArray newArrayLiteral(ExecutionContext context) {
        BuiltinError ctor = (BuiltinError) context.getGlobalObject().get(context, "Array");
        return (DynArray) context.construct(ctor);
    }
}
