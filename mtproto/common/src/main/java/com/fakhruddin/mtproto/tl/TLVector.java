package com.fakhruddin.mtproto.tl;

import com.fakhruddin.mtproto.MTMessage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class TLVector<T> extends TLObject implements List<T> {
  public static final int ID = 0x1cb5c415;
  public static final boolean IS_CONSTRUCTOR = true;
  public static String NAME = "vector";
  protected final ArrayList<T> items = new ArrayList<>();

  public Class<T> clazz;
  public boolean isBareTypeItem = false;

  public TLVector(Class<T> clazz) {
    this.clazz = clazz;
  }

  public TLVector() {

  }

  @Override
  public int getId() {
    return ID;
  }

  @Override
  public boolean isConstructor() {
    return IS_CONSTRUCTOR;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
    ostream.writeInt32(items.size());
    for (T item : items) {
      if (item instanceof MTMessage message) {
        message.writeParams(ostream);
      } else if (item instanceof TLObject object) {
        object.isBareType = isBareTypeItem;
        object.write(ostream);
      } else if (item instanceof Integer integer) {
        ostream.writeInt32(integer);
      } else if (item instanceof Long int64) {
        ostream.writeInt64(int64);
      } else if (item instanceof Double double_) {
        ostream.writeDouble(double_);
      } else if (item instanceof String string) {
        ostream.writeTLString(string);
      } else if (item instanceof byte[] bytes) {
        ostream.writeTLBytes(bytes);
      }
    }
  }

  @Override
  public void readParams(TLInputStream istream, TLContext context) throws Exception {
    items.clear();
    int size = istream.readInt32();
    for (int i = 0; i < size; i++) {
      if (clazz == Integer.class) {
        Integer integer = istream.readInt32();
        items.add(clazz.cast(integer));
      } else if (clazz == Long.class) {
        Long int64 = istream.readInt64();
        items.add(clazz.cast(int64));
      } else if (clazz == Double.class) {
        Double value = istream.readDouble();
        items.add(clazz.cast(value));
      } else if (clazz == String.class) {
        String value = istream.readTLString();
        items.add(clazz.cast(value));
      } else if (clazz == Byte[].class) {
        byte[] value = istream.readTLBytes();
        items.add(clazz.cast(value));
      } else if (clazz == MTMessage.class) {
        MTMessage message = new MTMessage();
        message.readParams(istream, context);
        items.add(clazz.cast(message));
      } else if (TLObject.class.isAssignableFrom(clazz) && Modifier.isAbstract(clazz.getModifiers()) &&
        !clazz.equals(TLObject.class)) {
        Method readType = clazz.getMethod("readType", TLInputStream.class, TLContext.class);
        T obj = (T) readType.invoke(null, istream, context);
        items.add(obj);
      } else if (TLObject.class.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
        T object = clazz.getDeclaredConstructor().newInstance();
        if (object instanceof TLObject tlObject) {
          tlObject.isBareType = isBareTypeItem;
          tlObject.read(istream, context);
        }
        items.add(object);
      } else {
        items.add((T) context.readObject(istream));
      }
    }
  }

  @Override
  public JSONObject toJSON() {
    JSONObject json = new JSONObject();
    json.put("@id", getId());
    json.put("@name", getName());
    JSONArray array = new JSONArray();
    for (T object : items) {
      if (object instanceof TLObject tlObject) {
        array.put(tlObject.toJSON());
      } else {
        array.put(object);
      }
    }
    json.put("items", array);
    return json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }

  @Override
  public int size() {
    return items.size();
  }

  @Override
  public boolean isEmpty() {
    return items.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return items.contains(o);
  }

  @Override
  public int indexOf(Object o) {
    return items.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return items.lastIndexOf(o);
  }

  @Override
  public Object[] toArray() {
    return items.toArray();
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    return items.toArray(a);
  }

  @Override
  public T get(int index) {
    return items.get(index);
  }

  @Override
  public T set(int index, T element) {
    return items.set(index, element);
  }

  @Override
  public boolean add(T t) {
    return items.add(t);
  }

  @Override
  public void add(int index, T element) {
    items.add(index, element);
  }

  @Override
  public T remove(int index) {
    return items.remove(index);
  }

  @Override
  public boolean remove(Object o) {
    return items.remove(o);
  }

  @Override
  public void clear() {
    items.clear();
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    return items.addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    return items.addAll(index, c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return items.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return items.retainAll(c);
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    return items.listIterator(index);
  }

  @Override
  public ListIterator<T> listIterator() {
    return items.listIterator();
  }

  @Override
  public Iterator<T> iterator() {
    return items.iterator();
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    return items.subList(fromIndex, toIndex);
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    items.forEach(action);
  }

  @Override
  public Spliterator<T> spliterator() {
    return items.spliterator();
  }

  @Override
  public boolean removeIf(Predicate<? super T> filter) {
    return items.removeIf(filter);
  }

  @Override
  public void replaceAll(UnaryOperator<T> operator) {
    items.replaceAll(operator);
  }

  @Override
  public void sort(Comparator<? super T> c) {
    items.sort(c);
  }

  @Override
  public int hashCode() {
    return items.hashCode();
  }

  @Override
  public Stream<T> stream() {
    return items.stream();
  }

  @Override
  public Stream<T> parallelStream() {
    return items.parallelStream();
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return items.containsAll(c);
  }
}
