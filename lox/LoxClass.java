package lox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


class LoxClass extends LoxInstance implements LoxCallable {
    final String name;
    final Map<String,LoxFunction> methods;
    final Map<String, LoxFunction> staticMethods;
    LoxClass(String name, Map<String, LoxFunction> methods){
        this.name = name;
        this.methods = methods;
        this.staticMethods = new HashMap<>();
    }
    LoxClass(String name, Map<String, LoxFunction> methods, Map<String, LoxFunction> staticMethods ){
        this.name = name;
        this.methods = methods;
        this.staticMethods = staticMethods;
    }

    LoxFunction findMethod(String name) {
        if (methods.containsKey(name)){
            return methods.get(name);
        }
        return null;
    }

    LoxFunction findStaticMethod(String name){
        if (staticMethods.containsKey(name)){
            return staticMethods.get(name);
        }
        return null;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        LoxInstance instance = new LoxInstance(this);
        LoxFunction initializer = findMethod("init");
        if(initializer != null){
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }

    Object get(Token name){
        LoxFunction method = findStaticMethod(name.lexeme);
        if (method != null) return method.bind(this);

        throw new RuntimeError(name, "Undefined property '" + name.lexeme + "'.");
    }

    @Override
    public int arity(){
        LoxFunction initializer = findMethod("init");
        if ( initializer == null) return 0;
        return initializer.arity();
    }

    @Override
    public String toString() {
        return name;
    }
}
