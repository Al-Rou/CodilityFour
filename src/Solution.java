import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args)
    {
        Solution s = new Solution();
        //int[] input = {3,4,5,6,3,7,1};
        int[] input = {3,4,5,3,7,1};
        //int[] input = {3,4,3,7,1};
        System.out.println(s.solution(input));
    }
    public int solution(int[] A)
    {
        List<Integer> list = new ArrayList<>();
        //First we check whether the array is aesthetic, to return 0
        boolean check = true;
        if(A[0] < A[1])
        {
            for(int i = 2; i < A.length-1;)
            {
                if((A[i] < A[i-1]) && (A[i] < A[i+1]))
                {
                    i += 2;
                    continue;
                }
                else
                {
                    check = false;
                    break;
                }
            }
        }
        else if(A[0] > A[1])
        {
            for(int i = 2; i < A.length-1;)
            {
                if((A[i] > A[i-1]) && (A[i] > A[i+1]))
                {
                    i += 2;
                    continue;
                }
                else
                {
                    check = false;
                    break;
                }
            }
        }
        if(check == true)
        {
            return 0;
        }
        else
        {
            //Now, we check all cases which the return is -1, which means there is no way that
            //the trees be aesthetic with just one cut
            for(int i = 0; i < A.length-1; i++)
            {
                int difference = A[i] - A[i+1];
                //Differences between adjacent data are stored in a list
                list.add(difference);
            }
            for (int i = 1; i < list.size()-1; i++)
            {
                //Three entries in the list which are all positive or all negative, make the code return -1
                if(((list.get(i)*list.get(i-1)) > 0) && ((list.get(i)*list.get(i+1)) > 0) && ((list.get(i+1)*list.get(i-1)) > 0))
                {
                    return -1;
                }
            }
            //This counts the number of adjacent entries in the list which are both positive or both negative
            int counter = 0;
            for (int i = 1; i < list.size()-1; i++)
            {
                if(((list.get(i)*list.get(i-1)) > 0) || ((list.get(i)*list.get(i+1)) > 0))
                {
                    counter++;
                }
            }
            //If we have more than one, it is impossible to have trees aesthetic with just one cut
            if(counter > 1)
            {
                return -1;
            }
            else
            {
                //This counts possibilities of cutting one tree to have trees aesthetic
                int counterTwo = 0;

                for(int j = 0; j < A.length; j++)
                {
                    //Every time, an array should be created with the same data as in A without just one of them
                    //which represents cutting one tree each time
                    List<Integer> list2 = new ArrayList<>();
                    for(int i = 0; i < A.length; i++)
                    {
                        list2.add(A[i]);
                    }
                    int[] aux = new int[A.length-1];
                    //One tree is cut
                    list2.remove(j);
                    for(int k = 0; k < list2.size(); k++)
                    {
                        //The auxiliary array, which has one data less than A, is ready
                        aux[k] = list2.get(k);
                    }
                    //Recursive call of the method. If the return is 0, it means trees in this case are aestheric
                    if(solution(aux) == 0)
                    {
                        //So, this increments by one
                        counterTwo++;
                    }
                }
                return counterTwo;
            }
        }
    }
}
