package org.dynjs.runtime.builtins.types.array.prototype;

import org.dynjs.exception.ThrowException;
import org.dynjs.runtime.AbstractNativeFunction;
import org.dynjs.runtime.ExecutionContext;
import org.dynjs.runtime.GlobalObject;
import org.dynjs.runtime.JSFunction;
import org.dynjs.runtime.JSObject;
import org.dynjs.runtime.Types;

public class ToLocaleString extends AbstractNativeFunction {

    public ToLocaleString(GlobalObject globalObject) {
        super(globalObject);
    }

    @Override
    public Object call(ExecutionContext context, Object self, Object... args) {
        // 15.4.4.3
        JSObject o = Types.toObject(context, self);

        int len = Types.toUint32(o.get(context, "length"));

        if (len == 0) {
            return "";
        }

        String separator = ",";

        StringBuffer str = new StringBuffer();

        boolean first = true;
        for (int i = 0; i < len; ++i) {
            if (!first) {
                str.append(separator);
            }
            first = false;
            Object element = o.get( context, ""+ i);
            if ( element != Types.UNDEFINED || element != Types.NULL ) {
                JSObject jsElement = Types.toObject(context, element);
                Object toLocaleString = jsElement.get( context, "toLocaleString" );
                
                if ( ! Types.isCallable(toLocaleString)) {
                    throw new ThrowException( context.createTypeError( "toLocaleString must be callable" ));
                }
                
                str.append( context.call( (JSFunction)toLocaleString, jsElement ) );
            }
        }

        return str.toString();
    }

}
