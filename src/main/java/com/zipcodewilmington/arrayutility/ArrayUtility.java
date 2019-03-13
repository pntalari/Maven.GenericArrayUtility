package com.zipcodewilmington.arrayutility;

import sun.tools.jconsole.inspector.XObject;

import java.io.ObjectStreamConstants;
import java.lang.reflect.Array;
import java.security.PublicKey;
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

    public Integer countDuplicatesInMerge(E[] arrayToMerge, E valueToEvaluate) {
        Long result = Arrays.stream(mergeArrays(arrayToMerge))
                .filter(n -> n == valueToEvaluate).count();

        return result.intValue();
    }

    public E[] mergeArrays(E[] arrayToMerge) {
        final E[] mergedArr = (E[]) java.lang.reflect.Array.newInstance(arrayToMerge.getClass().
                getComponentType(), arrayToMerge.length + this.arr.length);
        System.arraycopy(arrayToMerge, 0, mergedArr, 0, arrayToMerge.length);
        System.arraycopy(this.arr, 0, mergedArr, arrayToMerge.length, this.arr.length);

        return mergedArr;
    }

    public E getMostCommonFromMerge(E[] arrayToMerge) {
        Integer maxCount = 0;
        E ele = null;

        E[] mergedArr = mergeArrays(arrayToMerge);

        for (E element : mergedArr) {
            Integer currentCount = getNumberOfOccurrences(element);
            maxCount = currentCount;
            ele = element;
            if (currentCount > maxCount) {
                maxCount = currentCount;
                ele = element;
            }
        }

        return ele;
    }

    public Integer getNumberOfOccurrences(E valueToEvaluate) {
        Long result = (Arrays.stream(arr).filter(val -> val == valueToEvaluate).count());

        return result.intValue();
    }

    public E[] removeValue(E valueToRemove) {
        List<E> list = Arrays.stream(arr)
                .filter(val -> val != valueToRemove)
                .collect(Collectors.toList());

        E[] arr1 = (E[]) Array.newInstance(arr.getClass().getComponentType(), list.size());

        return list.toArray(arr1);

    }
}
