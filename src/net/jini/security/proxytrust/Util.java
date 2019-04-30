package net.jini.security.proxytrust;

import java.lang.reflect.Proxy;

class Util
{
    /**
     * Returns true if proxy2 is a generated Proxy (proxy1 is assumed to
     * be one) and the classes of both proxies implement the same ordered
     * list of interfaces, and returns false otherwise.
     */
    public static boolean sameProxyClass(Object proxy1, Object proxy2) {
    return (proxy1.getClass() == proxy2.getClass() ||
        (Proxy.isProxyClass(proxy2.getClass()) &&
         equalInterfaces(proxy1, proxy2)));
    }

    /**
     * Returns true if the interfaces implemented by obj1's class
     * are the same (and in the same order) as obj2's class.
     */
    public static boolean equalInterfaces(Object obj1, Object obj2) {
    Class[] intf1 = obj1.getClass().getInterfaces();
    Class[] intf2 = obj2.getClass().getInterfaces();
    if (intf1.length != intf2.length) {
        return false;
    } else {
        for (int i = 0; i < intf1.length; i++) {
        if (intf1[i] != intf2[i]) {
            return false;
        }
        }
        return true;
    }
    }
}
