package by.hancharou;

public class MyLinkedList<T> {

    transient int size = 0;
    transient MyNode<T> first;
    transient MyNode<T> last;
    protected transient int modCount = 0;

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("This collection: [");
        for (int i = 0; i < this.size(); i++) {
            sb.append(this.get(i));
            if (i != this.size - 1) {
                sb.append(";");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyLinkedList<?> myLinkedList = (MyLinkedList<?>) o;
        return size() == myLinkedList.size() && this.checkMyLinkedListsForEquals(myLinkedList);
    }

    public boolean checkMyLinkedListsForEquals(MyLinkedList myLinkedList) {
        for (int i = 0; i < this.size; i++) {
            if (!get(i).equals(myLinkedList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        MyLinkedList<T> myLinkedList = this;
        int result = 1;
        for (int i = 0; i < myLinkedList.size; i++) {
            result = 31 * result + (myLinkedList.get(i) == null ? 0 : myLinkedList.get(i).hashCode());
        }
        return result;
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

    void linkBefore(T t, MyNode<T> succ) {
        final MyNode<T> pred = succ.prev;
        final MyNode<T> newMyNode = new MyNode<>(pred, t, succ);
        succ.prev = newMyNode;
        if (pred == null) {
            first = newMyNode;
        } else {
            pred.next = newMyNode;
        }
        size++;
        modCount++;
    }

    T unlink(MyNode<T> x) {
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
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        modCount++;
        return element;
    }

    public void add(int index, T element) {
        checkPositionIndex(index);
        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    MyNode<T> node(int index) {
        if (index < (size >> 1)) {
            MyNode<T> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            MyNode<T> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    public T remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public T get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    static class MyNode<T> {
        T item;
        MyNode<T> next;
        MyNode<T> prev;
        MyNode(MyNode<T> prev, T element, MyNode<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
