package cn.lang.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

public class MaxSlidingWindow {



    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null || nums.length==0){
            return null;
        }
        LinkedHashMap<Integer, Integer> priority = new LinkedHashMap(k){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {

                return super.removeEldestEntry(eldest);
            }
        };
        int[] result = new int[nums.length-k+1];

        for(int i=0;i<k;i++){
            priority.put(i, nums[i]);
        }
        result[0] = findMax(priority, nums);
        for(int i=0;i<nums.length-k;i++){
            result[i+1]=find(priority, i+k, i, nums);
        }
        return result;
    }
    private int find(LinkedHashMap priority, int next, int previous, int[] nums){
        priority.remove(previous);
        priority.put(next, null);
        return findMax(priority, nums);
    }
    private int findMax(LinkedHashMap<Integer, Integer> priority, int[] nums){
        int maxValueIndex = -1;
        for(Map.Entry<Integer, Integer> entry : priority.entrySet()){
            if(maxValueIndex==-1){
                maxValueIndex = entry.getKey();
                continue;
            }
            if(nums[entry.getKey()]>nums[maxValueIndex]){
                maxValueIndex = entry.getKey();
            }
        }
        return nums[maxValueIndex];
    }
}
