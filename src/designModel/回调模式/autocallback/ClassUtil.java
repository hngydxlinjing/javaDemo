package designModel.回调模式.autocallback;

import org.apache.commons.beanutils.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassUtil {
    public ClassUtil() {
    }

    public static Object invokeMethod(Object object, String methodName) {
        return invokeMethod(object, methodName, (Object[])null);
    }

    public static Object invokeMethod(Object object, String methodName, Object arg) {
        try {
            return MethodUtils.invokeMethod(object, methodName, arg);
        } catch (Exception var4) {
            throw new RuntimeException("调用方法出错！", var4);
        }
    }

    public static Object invokeMethod(Object object, String methodName, Object[] args) {
        try {
            return MethodUtils.invokeMethod(object, methodName, args);
        } catch (Exception var4) {
            throw new RuntimeException("调用方法出错！", var4);
        }
    }

    public static Object invokeMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes) {
        try {
            return MethodUtils.invokeMethod(object, methodName, args);
        } catch (Exception var5) {
            throw new RuntimeException("调用方法出错！", var5);
        }
    }

    public static Object invokeStaticMethod(String className, String methodName, Object arg) {
        try {
            return MethodUtils.invokeExactStaticMethod(createClassByName(className), methodName, arg);
        } catch (Exception var4) {
            throw new RuntimeException("调用方法出错！", var4);
        }
    }

    public static Object invokeStaticMethod(String className, String methodName, Object[] args) {
        try {
            return MethodUtils.invokeExactStaticMethod(createClassByName(className), methodName, args);
        } catch (Exception var4) {
            throw new RuntimeException("调用方法出错！", var4);
        }
    }

    public static Object invokeStaticMethod(String className, String methodName, Object[] args, Class<?>[] parameterTypes) {
        try {
            return MethodUtils.invokeExactStaticMethod(createClassByName(className), methodName, args, parameterTypes);
        } catch (Exception var5) {
            throw new RuntimeException("调用方法出错！", var5);
        }
    }

    public static Object getObjectByName(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (Exception var2) {
            throw new RuntimeException("实体: " + className + " 无法加载", var2);
        }
    }

    public static Class<?> createClassByName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException var2) {
            throw new RuntimeException("实体: " + className + " 无法加载", var2);
        }
    }

    public static Object newInstanceByName(String className, Class<?>[] clazzes, Object[] args) {
        Class clazz = createClassByName(className);

        try {
            return clazz.getConstructor(clazzes).newInstance(args);
        } catch (IllegalArgumentException var5) {
            throw new RuntimeException("非法参数类型，实例化失败：" + className, var5);
        } catch (SecurityException var6) {
            throw new RuntimeException("安全性限制，实例化失败：" + className, var6);
        } catch (InstantiationException var7) {
            throw new RuntimeException("实例化失败：" + className, var7);
        } catch (IllegalAccessException var8) {
            throw new RuntimeException("没有相应的构造函数，实例化失败：" + className, var8);
        } catch (InvocationTargetException var9) {
            throw new RuntimeException("非法调用，实例化失败：" + className, var9);
        } catch (NoSuchMethodException var10) {
            throw new RuntimeException("没有相应的构造函数，实例化失败：" + className, var10);
        }
    }

    public static Object newInstanceByName(Class<?> clazz, Class<?>[] clazzes, Object[] args) {
        try {
            return clazz.getConstructor(clazzes).newInstance(args);
        } catch (IllegalArgumentException var4) {
            throw new RuntimeException("非法参数类型，实例化失败：" + clazz.getName(), var4);
        } catch (SecurityException var5) {
            throw new RuntimeException("安全性限制，实例化失败：" + clazz.getName(), var5);
        } catch (InstantiationException var6) {
            throw new RuntimeException("实例化失败：" + clazz.getName(), var6);
        } catch (IllegalAccessException var7) {
            throw new RuntimeException("没有相应的构造函数，实例化失败：" + clazz.getName(), var7);
        } catch (InvocationTargetException var8) {
            throw new RuntimeException("非法调用，实例化失败：" + clazz.getName(), var8);
        } catch (NoSuchMethodException var9) {
            throw new RuntimeException("没有相应的构造函数，实例化失败：" + clazz.getName(), var9);
        }
    }

    public static Object newInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException var2) {
            throw new RuntimeException("实例化失败：" + clazz.getName());
        } catch (IllegalAccessException var3) {
            throw new RuntimeException("实例化失败：" + clazz.getName());
        }
    }

    public static List newInstanceList(String[] classNames) {
        return newInstanceList(Arrays.asList(classNames));
    }

    public static List newInstanceList(List classNames) {
        List result = new ArrayList();
        int i = 0;

        for(int n = classNames.size(); i < n; ++i) {
            String className = (String)classNames.get(i);
            if (className != null && !"".equals(className)) {
                result.add(getObjectByName(className));
            }
        }

        return result;
    }

    public static boolean isImplInterface(Class<?> clazz, Class<?> interfaceClazz) {
        return Arrays.asList(clazz.getInterfaces()).contains(interfaceClazz);
    }

    public static boolean isInstance(Object obj, Class<?> clazz) {
        return clazz.isInstance(obj);
    }
}
