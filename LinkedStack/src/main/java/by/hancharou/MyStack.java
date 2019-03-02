package by.hancharou;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class MyStack<T> {

    transient int size = 0;
    transient MyNode<T> first;
    transient MyNode<T> last;
    protected transient int modCount = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyStack<?> myStack = (MyStack<?>) o;
        return size == myStack.size && checkMyStackForEquals(myStack);
    }

    public boolean checkMyStackForEquals(MyStack myStack) {
        for (int i = size - 1; i >= 0; i--) {
            if (!get(i).equals(myStack.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = size - 1; i >= 0; i--) {
            result = result + 31 * (get(i).hashCode());
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("This Stack: ");
        for (int i = size - 1; i >= 0; i--) {
            sb.append(get(i).toString());
            if (i != 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    int size() {
        return size;
    }

    private void linkLast(T t) {
        final MyNode<T> l = last;
        final MyNode<T> newMyNode = new MyNode<>(l, t, null);
        last = newMyNode;
        if (l == null) {
            first = newMyNode;
        } else {
            l.next = newMyNode;
        }
        size++;
        modCount++;
    }

    private T unlink(MyNode<T> x) {
        final T element = x.item;
        final MyNode<T> next = x.next;
        final MyNode<T> prev = x.prev;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        if (next == null) {
            last = prev;
        }
        x.item = null;
        size--;
        modCount++;
        return element;
    }

    public void push(T t) {
        linkLast(t);
    }

    public T pop() {
        T obj;
        int len = size();
        obj = peek();
        if (obj == null) {
            throw new NoSuchElementException();
        }
        remove(len - 1);
        return obj;
    }

    public synchronized T peek() {
        int len = size();
        if (len == 0) {
            throw new EmptyStackException();
        }
        return get(len - 1);
    }

    public T remove(int index) {
        return unlink(node(index));
    }

    public T get(int index) {
        return node(index).item;
    }

    MyNode<T> node(int index) {
        MyNode<T> x = last;
        for (int i = size - 1; i > index; i--) {
            x = x.prev;
        }
        return x;
    }

    private static class MyNode<T> {
        T item;
        MyNode<T> next;
        MyNode<T> prev;
        MyNode(MyStack.MyNode<T> prev, T element, MyStack.MyNode<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

}
