public class HashEntry<K,V> {
    private K key;
    private Value value;

    HashEntry(K key, Value value) {
        this.key = key;
        this.value = value;

    }

    public K getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }
}
