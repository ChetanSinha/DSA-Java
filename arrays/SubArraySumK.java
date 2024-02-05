import java.util.HashMap;

// https://leetcode.com/problems/subarray-sum-equals-k/

/*
 * 
 * Sub array sum of K can be solved using sliding window but not with arrays containing negative
 * elements
 * 
 * To solve for negative element we are using prefix sum map:
 *  - compute prefix sum till each index
 *  - maintain a map, that contains prefix sum to number of occurences of this prefix sum mapping
 *  - for each index, compute prefix sum, find the counterpart of this prefix sum in map (ie sum - k)
 *  - if there's an occurence, result will be number of occurrence of that counterpart.
 * 
 * 
 * 
 * logically, this works as:
 *  [-1, -3, -4, 5]
 * prefix sum => [-1, -4, -8, 3]
 * target => -4
 * 
 * now we try to get -4 at each index, and finds the counter part in map
 * counterparts => [3, 0, -4, 7]
 */
public class SubArraySumK {
    
    public static void main(String[] args) {
        int[] arr = new int[]{-1,-3,-4,5};
        subarraySum(arr, -4);
    }

    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int target = sum - k;
            if (map.containsKey(target)) {
                result += map.get(target);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}
