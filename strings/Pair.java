public class Pair<T> {
    private T first;
    private T second;
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }
    public void setFirst(T val) {
        first = val;
    }
    public void setSecond(T val) {
        second = val;
    }
    public T getFirst() {
        return first;
    }
    public T getSecond() {
        return second;
    }
}
