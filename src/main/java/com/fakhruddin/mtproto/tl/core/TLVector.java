package com.fakhruddin.mtproto.tl.core;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
public class TLVector<T extends TLObject> extends TLObject implements List<T> {
    private static final String TAG = TLVector.class.getSimpleName();
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
    protected void writeParams(TLOutputStream outputStream) throws Exception {
        outputStream.writeInt(items.size());
        for (TLObject item : items) {
            item.isBareType = isBareTypeItem;
            item.write(outputStream);
        }
    }

    @Override
    protected void readParams(TLInputStream inputStream) throws Exception {
        int size = inputStream.readInt();
        for (int i = 0; i < size; i++) {
            if (Modifier.isAbstract(clazz.getModifiers())) {
                Method readObject = clazz.getMethod("readObject", TLInputStream.class);
                T obj = (T) readObject.invoke(null, inputStream);
                items.add(obj);
            } else {
                T object = clazz.getDeclaredConstructor().newInstance();
                object.isBareType = isBareTypeItem;
                object.read(inputStream);
                items.add(object);
            }
        }
    }

    @Override
    public String toString() {
        return "TLVector{items=" + items + '}';
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

    @SuppressWarnings("SuspiciousToArrayCall")
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
