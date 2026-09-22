package pkg17metaprogramming;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * metaprogramming1DynamicProxy.java
 * ---------------------------------
 * Dynamic proxies: create interface implementations at runtime via Proxy.
 *
 * DEFINITION:
 *   java.lang.reflect.Proxy generates a class that implements one or more
 *   interfaces. InvocationHandler receives every method call — basis for
 *   decorators, logging, transaction wrappers, and mock frameworks.
 *
 * KEY POINTS:
 *   - Only interfaces can be proxied (use bytecode libs for classes).
 *   - InvocationHandler.invoke() receives Method, args, and can delegate.
 *   - Used by Spring AOP, Mockito, Hibernate lazy loading.
 */
public class metaprogramming1DynamicProxy {

    interface Greeter { String greet(String name); }

    public static void main(String[] args) {
        Greeter real = name -> "Hello, " + name;

        Greeter logged = (Greeter) Proxy.newProxyInstance(
                Greeter.class.getModule().getClassLoader(),
                new Class<?>[] { Greeter.class },
                new InvocationHandler() {
                    final Greeter target = real;
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("  -> calling " + method.getName() + "(" + args[0] + ")");
                        Object result = method.invoke(target, args);
                        System.out.println("  <- result: " + result);
                        return result;
                    }
                });

        System.out.println(logged.greet("Ada"));
    }
}
