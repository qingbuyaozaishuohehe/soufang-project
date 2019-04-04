package com.xu.soufang.base;

import com.google.common.collect.Sets;
import org.springframework.data.domain.Sort;

import java.util.Set;

/**
 * @author xuzhenqin
 * 房屋排序
 */
public class HouseSort {

    private static final String DEFAULT_SORT_KEY = "lastUpdateTime";

    private static final String DISTANCE_TO_SUBWAY_KEY= "distanceToSubway";

    private static final Set<String> SORT_KEY = Sets.newHashSet(
            DEFAULT_SORT_KEY,
            "createTime",
            "price",
            "area",
            DISTANCE_TO_SUBWAY_KEY
    );

    private static Sort genetareSort(String key,String distanceKey){
        key = getSortKey(key);

        Sort.Direction direction = Sort.Direction.fromString(distanceKey);
        if (direction == null){
            direction = Sort.Direction.DESC;
        }
        return new Sort(direction,key);
    }


    public static String getSortKey(String key){
        if (!SORT_KEY.contains(key)){
            key = DEFAULT_SORT_KEY;
        }
        return key;
    }
}
