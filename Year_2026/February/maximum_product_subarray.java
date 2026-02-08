package February;

public class maximum_product_subarray{
   public static void main(String[] args){
      
   }

   static class Solution {
    int maxProduct(int[] arr) {
        int maxProduct = Integer.MIN_VALUE;
        int left = 0;
        for(int i = 0 ; i < arr.length ; i++){
            if(left == 0) left = 1;
            left *= arr[i];
            maxProduct = Math.max(left , maxProduct);
        }
        // let's suppose a window in which there is no zero
        // what could be the maxProduct ?
        // it will always include the element from start to a point i similarily from end to i why?
        // first reason the moment we exclude any particular element the window divides and we are not able to access more elements
        // it always matters the count of negatives since they drop the product
        //  let's suppose I have a window which is giving a postive product
        // if both sides have either negative or postive we expand the window on both sides
        // but if we have negative on only one of side then we definetely take the positive element and think either take or not take negative side
        int right = 0;
        for(int i = arr.length - 1 ; i >= 0 ; i--){
            if(right == 0) right = 1;
            right *= arr[i];
            maxProduct = Math.max(right , maxProduct);
        }
        return maxProduct;
    }
} 
}
