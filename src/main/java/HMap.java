import java.util.ArrayList;
import java.util.Iterator;


public class HMap<Integer, T>/* implements Iterable<HMap.Entry<Integer, T>>*/{
    private int capacity = 10;
    ArrayList<ArrayList<T>> values = new ArrayList<ArrayList<T>>();
    ArrayList<ArrayList<Integer>> keys = new ArrayList<ArrayList<Integer>>();
                                ArrayList<String> nodeList = new ArrayList<>();


    public HMap(){

        this.capacity = capacity;
        for (int i = 0; i < this.capacity; i++) {
            keys.add(new ArrayList<Integer>());
            values.add(new ArrayList<T>());
        }
    }

    private int hash(int key){
        return key % capacity;
    }
    private int size(){
        return keys.size();
    }

    private T get(int key){
        if (keys.get(hash(key)).indexOf(key) == -1){
            return null;
        }
        return values.get(hash(key)).get(indexOfKey(key));
    }

    private void put(int key, T value){
                                Entry entry = new Entry(key, value);
                                entry.key = key;
                                entry.value = value;
                                nodeList.add(entry.key + " " + entry.value);

        if (!containsKey(key)){
            targetKeyList(key).add(key);
            values.get(hash(key)).add(value);
        } else {
            values.get(hash(key)).remove(indexOfKey(key));
            values.get(hash(key)).add(value);
        }
    }

    private void remove(int key){

        if (indexOfKey(key) != -1){
            values.get(hash(key)).remove(indexOfKey(key));
            targetKeyList(key).remove(indexOfKey(key));
            }
    }

    private boolean containsKey(int key){
        return indexOfKey(key) == -1 ? false : true;
    }

    private ArrayList<T> entryValue (){
        ArrayList<T> entry = new ArrayList<T>();
        for (int i = 0; i < values.size(); i++){
            for (int j = 0; j < values.get(i).size(); j++) {
                entry.add(values.get(i).get(j));
            }
        }
        return entry;
    }
    private ArrayList<String> entrySet(){
        return nodeList;
    }

    private ArrayList targetKeyList(int key){
        return keys.get(hash(key));
    }

    private int indexOfKey(int key){
        return targetKeyList(key).indexOf(key);
    }

    //@Override
    public Iterator<Entry<Integer, T>> iterator() {
        return new IterEntry();
    }

    class IterEntry implements Iterator<Entry<Integer, T>> {
        int mainIndex =0;
        Iterator<Integer> itK=keys.get(mainIndex).iterator();
        Iterator<T> itV=values.get(mainIndex).iterator();

        private void updateIterators() {
            mainIndex++;
        }

        private void shiftIndex() {
            if (itK.hasNext()) return;
            while (++mainIndex < keys.size()) {
                updateIterators();
                if (itK.hasNext()) {
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return mainIndex < keys.size() - 1;
        }

        @Override
        public Entry<Integer, T> next() {
            Entry e = new Entry<>(itK.next(), itV.next());
            shiftIndex();
            return e;
        }

        @Override
        public void remove() {
            keys.remove(mainIndex);
            values.remove(mainIndex);
        }
    }


    private class Entry<Integer, T> {
                        private Integer key;
                        private T value;

                        private Entry(Integer key, T value){
                            this.key = key;
                            this.value = value;
                        }
                        private int size(){
                            return keys.size();
                        }
                        @Override
                        public String toString() {
                            return String.format("key:%s, value:%s",key,value);
                        }
    }


    public static void main(String[] args) {
        HMap hmap = new HMap();
        hmap.put(1, "Hello");
        hmap.put(1, "Hey");
        hmap.put(15, "cat");
        hmap.put(2, "World,");
        hmap.put(22, "World!");
        hmap.put(3, "it's me!");
        hmap.put(7, "and again!");
        hmap.put(3335587, "wow!");
        hmap.put(33, 89);
        hmap.remove(2);



        //System.out.println(hmap.containsKey(16));
        //System.out.println(hmap.get(3));
        ArrayList alist = hmap.entrySet();

    }

    public void check(){
        HMap hmap = new HMap();
        Iterator<Entry<Integer, T>> it = hmap.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
