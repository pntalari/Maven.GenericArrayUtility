package com.zipcodewilmington.arrayutility;

import sun.tools.jconsole.inspector.XObject;

import java.io.ObjectStreamConstants;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<E extends Object> {
    E[] arr;

    public ArrayUtility(E[] inputArray) {
        this.arr = inputArray;
    }

    public E countDuplicatesInMerge(E[] arrayToMerge, E valueToEvaluate) {
        final E[] mergedArr = (E[]) java.lang.reflect.Array.newInstance(arrayToMerge.getClass().
                getComponentType(),arrayToMerge.length+this.arr.length);
        System.arraycopy(arrayToMerge,0,mergedArr,0,arrayToMerge.length);
        System.arraycopy(this.arr,0,mergedArr,arrayToMerge.length,this.arr.length);

        E result = (E) (((Object) Arrays.stream(mergedArr)
                .filter(n -> n == valueToEvaluate).count()));

        return result;

    }

    public E getMostCommonFromMerge(E[] arrayToMerge) {
        final E[] mergedArr = (E[]) java.lang.reflect.Array.newInstance(arrayToMerge.getClass().
                getComponentType(),arrayToMerge.length+this.arr.length);
        System.arraycopy(arrayToMerge,0,mergedArr,0,arrayToMerge.length);
        System.arraycopy(this.arr,0,mergedArr,arrayToMerge.length,this.arr.length);

       // Map<String,E> map = Arrays.stream(mergedArr)
          //      .collect(Collectors.groupingBy(Objects::toString, Collectors.counting()));

        return null;
    }

    public E getNumberOfOccurrences(E valueToEvaluate) {

        return (E)(Object) (Arrays.stream(arr).filter(val->val==valueToEvaluate).count());
    }

    public E[] removeValue(E valueToRemove) {
       List<E> list = Arrays.stream(arr)
               .filter(val -> val != valueToRemove)
               .collect(Collectors.toList());

       E[] arr1 = (E[]) Array.newInstance(arr.getClass().getComponentType(),list.size());

       return list.toArray(arr1);

    }
}
