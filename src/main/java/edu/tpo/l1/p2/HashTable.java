package edu.tpo.l1.p2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class HashTable<T extends Comparable<T>> {

    protected Object[] array;
    protected int capacity;
    protected int actualCapacity;
    protected StringBuilder log;

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    public class Entry {
        private T obj;
        private int key;

        private Entry() {
        }
    }

    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    protected class Bucket {
        private Entry value;
        private Bucket next;

        private Bucket() {
        }
    }

    public HashTable() {
        actualCapacity = 0;
        capacity = 16;
        array = new Object[capacity];
        log = new StringBuilder();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getActualCapacity() {
        return actualCapacity;
    }

    public String getLog() {
        return log.toString();
    }

    public T contains(T obj) {
        log.append("operation='contains', message='start'\n");

        int index = getIndex(obj);

        Bucket bucket = (Bucket) array[index];
        while (bucket != null && bucket.value != null) {
            if (bucket.value.obj.equals(obj)) {
                log.append(String.format("operation='contains', message='entry was found with key=%d'\n", index));
                return bucket.value.obj;
            }

            bucket = bucket.next;
        }

        log.append("operation='contains', message='element was not found'\n");
        throw new NoSuchElementException(String.format("Entry with given value (%s) was not found", obj));
    }

    public void add(T obj) {
        log.append("operation='add', message='start'\n");

        if (actualCapacity + 1 > capacity) {
            log.append("operation='add', message='need to extend inner array size'\n");
            extendArray();
        }

        actualCapacity++;
        int index = getIndex(obj);

        if (array[index] == null) {
            log.append(String.format("operation='add', message='add new bucket with index=%d'\n", index));
            array[index] = new Bucket(new Entry(obj, index), null);
        } else {
            log.append(String.format("operation='add', message='add new entry in bucket chain with index=%d'\n", index));
            Bucket bucket = (Bucket) array[index];
            while (true) {
                int comparisonResult = bucket.value.obj.compareTo(obj);
                if (comparisonResult > 0) {
                    Bucket newBucket = new Bucket(new Entry(obj, index), bucket);
                    array[index] = newBucket;
                    break;
                } else if (comparisonResult == 0) {
                    actualCapacity--;
                    break;
                } else {
                    if (bucket.next == null) {
                        bucket.next = new Bucket(new Entry(obj, index), null);
                        break;
                    }
                }
            }
        }
    }

    public void remove(T obj) {
        log.append("operation='remove', message='start'\n");

        int index = getIndex(obj);

        if (array[index] != null) {
            Bucket prev = null;
            Bucket bucket = (Bucket) array[index];
            while (bucket != null) {
                if (bucket.value.obj.equals(obj)) {
                    if (prev == null) {
                        log.append("operation='remove', message='bucket was removed'\n");
                        array[index] = null;
                    } else {
                        log.append("operation='remove', message='entry was removed from chain'\n");
                        prev.next = null;
                    }
                    return;
                }

                prev = bucket;
                bucket = bucket.next;
            }
        }

        log.append("operation='remove', message='no entry to remove'\n");
    }

    protected int getIndex(Object obj) {
        log.append("operation='getIndex', message='start'\n");

        if (obj == null) throw new NullPointerException("This hash table can not store null");

        log.append(String.format("operation='getIndex', message='index=%d'\n", (obj.hashCode() % capacity)));

        return obj.hashCode() % capacity;
    }

    protected void extendArray() {
        log.append("operation='extendArray', message='start'\n");
        Object[] initialArray = array;

        log.append(String.format("operation='extendArray', message='increase capacity to %d'\n", capacity*2));

        this.capacity = capacity * 2;
        this.actualCapacity = 0;
        array = new Object[capacity];

        for (Object obj : initialArray) {
            Bucket bucket = (Bucket) obj;

            while (bucket != null) {
                add(bucket.value.obj);
                bucket = bucket.next;
            }
        }
    }

}
