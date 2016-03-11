package com.rohitshampur.glue;


import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Glue {
  private static final String TAG = "Glue";
  private static Class aClass;

  private Glue() { }

  public static void prepare(Class claz){
    aClass = claz;
  }
  public static void stickTo(Object activity,Object view) {
    if(view==null){
        view=activity;
    }
    Class<?> clazz = (null == view) ? activity.getClass() : view.getClass();
    Field[] fields = activity.getClass().getDeclaredFields();
    Method findViewById = getMethod(clazz, "findViewById", Integer.TYPE);

    for (Field field : fields) {
      if (findViewById!=null) {
        stickToView(activity,view, field, findViewById);
      }

      StickToResource stickToRes = field.getAnnotation(StickToResource.class);

      if (null != stickToRes) {
        try {
          int valId = stickToRes.value();
          stickToRes(activity,view, clazz, valId, field);
        } catch (InvocationTargetException e) {
          throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
          throw new IllegalStateException(e);
        }
      }
    }
  }
  public static void stickTo(Object object) {
    stickTo(object, null);
    Activity ac = (Activity) object;
  }

  private static void stickToView(Object activity,Object view, Field field, Method findViewById) {
    field.setAccessible(true);
    StickToView injectToView = field.getAnnotation(StickToView.class);

    if (null != injectToView) {
      int id = injectToView.value();
      String res = field.getName();

      Field f = null;
      try {
        f = aClass.getDeclaredField(res);
        int id1 = f.getInt(aClass);
        if (id == -1) {
          field.set(activity, findViewById.invoke(view, id1));
        }else{
          field.set(activity, findViewById.invoke(view, id));
        }
      } catch (NoSuchFieldException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
  /*int getMyVariable(String foo) throws IllegalArgumentException, IllegalAccessException{
    for(Field f:foo.getClass().getDeclaredFields()){
      *//**
       * Ensure the RetentionPolicy of 'MyAnnotation' is RUNTIME.
       *//*
      if(f.isAnnotationPresent(InjectView.class)){
        return f.getInt(foo);
      }
    }
    return -1;
  }*/

  private static void stickToRes(Object activity,Object view, Class<?> viewClazz,
      int valId, Field field) throws InvocationTargetException, IllegalAccessException {
    field.setAccessible(true);

    Class<?> t = field.getType();
    Method getResources = getMethod(viewClazz, "getResources");
    Object context = null;

    if (null == getResources) {
      Method getContext = getMethod(viewClazz, "getContext");

      if (null != getContext) {
        context = getContext.invoke(activity);
        getResources = getMethod(context.getClass(), "getResources");
      }
    }

    if (t.isAssignableFrom(String.class) && !t.isArray()) {
      Method getString = getMethod(viewClazz, "getString", Integer.TYPE);
      Object result = invoke(getString, activity, valId);

      field.set(activity, (null != result) ? result : "");
    } else if (null != getResources){
      Object resources = (null == context) ? getResources.invoke(activity) : getResources.invoke(context);
      Class<?> resourcesClazz = resources.getClass();

      if (t.isAssignableFrom(String.class)) {
        Method getString = getMethod(viewClazz, "getString", Integer.TYPE);
        Object result = invoke(getString, resources, valId);

        field.set(activity, (null != result) ? result : "");
      } else if (t.isAssignableFrom(String[].class)) {
        Method getStringArray = getMethod(resourcesClazz, "getStringArray", Integer.TYPE);
        Object result = invoke(getStringArray, resources, valId);

        field.set(activity, (null != result) ? result : new String[] { });
      } else if (t.isAssignableFrom(int.class)) {
        Method getInteger = getMethod(resourcesClazz, "getInteger", Integer.TYPE);
        Object result = invoke(getInteger, resources, valId);

        field.set(activity, (null != result) ? result : 0);
      } else if (t.isAssignableFrom(int[].class)) {
        Method getIntArray = getMethod(resourcesClazz, "getIntArray", Integer.TYPE);
        Object result = invoke(getIntArray, resources, valId);

        field.set(activity, (null != result) ? result : new int[] { });
      } else if (t.isAssignableFrom(boolean.class)) {
        Method getBoolean = getMethod(resourcesClazz, "getBoolean", Integer.TYPE);
        Object result = invoke(getBoolean, resources, valId);

        field.set(activity, (null != result) ? result : false);
      } else if (t.isAssignableFrom(float.class)) {
        Method getDimension = getMethod(resourcesClazz, "getDimension", Integer.TYPE);
        Object result = invoke(getDimension, resources, valId);

        field.set(activity, (null != result) ? result : 0.0f);
      }
    }
  }

  private static Method getMethod(Class<?> clazz, String name, Class<?>... types) {
    try {
      return clazz.getMethod(name, types);
    } catch (NoSuchMethodException e) {
      return null;
    }
  }

  private static Object invoke(Method method, Object obj, Object... args)
      throws InvocationTargetException, IllegalAccessException {
    if (null != method) {
      return method.invoke(obj, args);
    } else {
      return null;
    }
  }
}
