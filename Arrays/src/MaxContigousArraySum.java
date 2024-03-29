// Java program to print largest contiguous 
// array sum 

public class MaxContigousArraySum { 
  
    static int maxSubArraySum(int a[], int size) 
    { 
    int max_so_far = a[0]; 
    int curr_max = a[0]; 
  
    for (int i = 1; i < size; i++) 
    { 
           curr_max = Math.max(a[i], curr_max+a[i]); 
        max_so_far = Math.max(max_so_far, curr_max); 
    } 
    return max_so_far; 
    } 
  
    /* Driver program to test maxSubArraySum */
    public static void main(String[] args) 
    { 
    int a[] = {999,7}; 
    int n = a.length;    
    int max_sum = maxSubArraySum(a, n); 
    System.out.println("Maximum contiguous sum is " 
                       + max_sum); 
    } 
} 