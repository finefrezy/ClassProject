package com.elife.classproject.viewinject;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by tzhang on 2016/10/12.
 */
public class ViewInjectUtils {

    private static final String METHOD_SET_CONTENTVIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

    public static void inject(Activity activity) {

        injectContentView(activity);
        injectViews(activity);
        injectEvents(activity);

    }

    private static void injectEvents(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        // 获取类中所有的方法
        Method[] methods = clazz.getMethods();
        // 遍历方法
        for (Method md : methods) {
            // 获取对应方法对应的注解信息
            // 方法很多，并不知道该方法用的是什么annotation，所以无法明确的获取OnClick注解实例

            Annotation[] annotations = md.getAnnotations();
            for (Annotation annotation : annotations) {
                // 返回当前注解的类型 Returns the type of this annotation  @OnClick
                // 代表当前注解的类实例 OnClick--注解类
                Class<? extends Annotation> anno = annotation.annotationType();
                // 根据onClick实例获取其注解中的注解信息，得到EventBase注解实例，注解中只要有他就行
                EventBase eb = anno.getAnnotation(EventBase.class);
                if (null != eb) {
                    String listenerSetter = eb.listenerSetter();// setOnClickListener
                    Class<?> listenerType = eb.listenerType();// View.OnClickListener.class
                    String methodName = eb.methodName();// onClick

                    try {
                        //拿到Onclick注解中的value方法 anno @OnClick实例  annotation @OnClick注解
                        Method aMethod = anno.getDeclaredMethod("value");
                        Log.e("ViewInjectUtils-------",aMethod.getName());
                        Log.e("ViewInjectUtils-------","method name->" + methodName);

                        //取出所有的viewId
                        int[] viewIds = (int[]) aMethod.invoke(annotation, null);

                        Log.e("ViewInjectUtils-------","viewIds->" + viewIds.length);


                        //通过InvocationHandler设置代理
                        DynamicHandler handler = new DynamicHandler(activity);
                        handler.addMethod(methodName, md);
                        // Invocation 代表一个远程的调用
                        // 代理类的classloader--类对象数组，返回的代理对象实现了这些接口---处理方法分发---用于处理分发的方法
                        // 代理的是View.OnClickListener.class
                        Object listener = Proxy.newProxyInstance(
                                listenerType.getClassLoader(),
                                new Class<?>[] { listenerType }, handler);


                        //遍历所有的View，设置事件
                        for (int viewId : viewIds)
                        {

                            View view = activity.findViewById(viewId);

                            Method setEventListenerMethod = view.getClass()
                                    .getMethod(listenerSetter, listenerType);
// view,调用这个方法的对象-------------listener参数
                            // listener.onClick，listener代理的是接口，当点击时触发事件，点击时触发调用
                            setEventListenerMethod.invoke(view, listener);

                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void injectViews(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field fld : fields) {
            ViewInjector vj = fld.getAnnotation(ViewInjector.class);
            if (null != vj) {
                int viewId = vj.value();
                if (viewId != -1) {
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, int.class);
                        method.setAccessible(true);
                        Object resView = method.invoke(activity, viewId);
                        fld.setAccessible(true);
                        fld.set(activity, resView);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void injectContentView(Activity activity) {

        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (null != contentView) {
            int contentViewId = contentView.value();
            try {
                Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW, int.class);
                method.setAccessible(true);
                method.invoke(activity, contentViewId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }


    }
}
