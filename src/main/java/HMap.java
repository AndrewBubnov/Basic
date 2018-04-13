import java.util.ArrayList;
import java.util.Iterator;


public class HMap<Integer, T> implements Iterable<Integer>{
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

//    private ArrayList<Integer> keySet(ArrayList<String> nodeList){
//        ArrayList<Integer> keySet = new ArrayList<>();
//        for (String iterator : nodeList){
//            Integer key = Integer.parseInt(iterator.split(" ")[0]);
//            keySet.add(key);
//        }
//        return keySet;
//    }



    private ArrayList targetKeyList(int key){
        return keys.get(hash(key));
    }

    private int indexOfKey(int key){
        return targetKeyList(key).indexOf(key);
    }

    @Override
    public Iterator iterator() {
        return (Iterator) new MyHMapIterator();
    }
    private class MyHMapIterator implements Iterator<Integer>{

        private Entry entry;

        @Override
        public boolean hasNext() {
            return (java.lang.Integer)entry.key < entry.size() - 1;
        }

        @Override
        public java.lang.Integer next() {

            return (java.lang.Integer) entry.key;
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

        System.out.println(hmap.containsKey(16));
        System.out.println(hmap.get(3));
        ArrayList alist = hmap.entrySet();
//        for (int i = 0; i < alist.size(); i++) {
//            System.out.printf("%s ", alist.get(i));
//        }
    }
}
