package com.fast.family.cache;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;

public class MutliMapTest {

    public static void main(String[] args) {
        Multimap<String,String> myNultimap = ArrayListMultimap.create();
        myNultimap.put("Fruits","Bannana");
        myNultimap.put("Fruits","Apple");
        myNultimap.put("Fruits","Pear");
        myNultimap.put("Vegetables","Carrot");

        int size = myNultimap.size();
        System.out.println(size);

        Collection<String> fruits = myNultimap.get("Fruits");
        System.out.println(fruits);

        Collection<String> fruitsCol = myNultimap.get("Vegetables");
        System.out.println(fruitsCol);

        for (String value : myNultimap.values()){
            System.out.println(value);
        }

        myNultimap.removeAll("Fruits");
        System.out.println(myNultimap.get("Fruits"));
    }
}
