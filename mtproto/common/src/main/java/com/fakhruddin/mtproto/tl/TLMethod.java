package com.fakhruddin.mtproto.tl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Fakhruddin Fahim on 10/09/2022
 */
public abstract class TLMethod<T extends TLObject> extends TLObject {
  private final Class<?> clazz;

  public TLMethod(Class<?> clazz) {
    this.clazz = clazz;
  }

  public TLMethod(Class<?> clazz, Class<?> item) {
    this.clazz = createParameterizedType(clazz, item).getClass();
  }

  private ParameterizedType createParameterizedType(Class<?> rawType, Class<?> childType) {
    return new ParameterizedType() {
      @Override
      public Type[] getActualTypeArguments() {
        return new Type[]{childType};
      }

      @Override
      public Type getRawType() {
        return rawType;
      }

      @Override
      public Type getOwnerType() {
        return null;
      }
    };
  }


  public T readResult(TLInputStream istream, TLContext context) throws Exception {
    T object = null;
    if (Modifier.isAbstract(clazz.getModifiers())) {
      Method readType = clazz.getMethod("readType", TLInputStream.class, TLContext.class);
      Object obj = readType.invoke(null, istream, context);
      if (clazz.isInstance(obj)) {
        object = (T) clazz.cast(obj);
      }
    } else {
      object = (T) clazz.getDeclaredConstructor().newInstance();
      if (object instanceof TLObject tlObject) {
        tlObject.read(istream, context);
      }
    }
    return object;
  }
}
