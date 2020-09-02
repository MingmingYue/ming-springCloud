package com.service.gateway.weight;

import java.util.Map;

/**
 * @author xiaoMing
 * Create on 2020-09-01.
 */
public class WeightRandomUtil {

    public static <T> WeightMeta<T> buildWeightMeta(final Map<T, Integer> weightMap) {
        if(weightMap.isEmpty()){
            return null;
        }
        final int size = weightMap.size();
        Object[] nodes = new Object[size];
        int[] weights = new int[size];
        int index = 0;
        int weightAdder = 0;
        for (Map.Entry<T, Integer> each : weightMap.entrySet()) {
            nodes[index] = each.getKey();
            weights[index++] = (weightAdder = weightAdder + each.getValue());
        }
        return new WeightMeta<T>((T[]) nodes, weights);
    }
}
