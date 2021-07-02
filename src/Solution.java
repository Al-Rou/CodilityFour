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
            int counter = 0;
            for (int i = 1; i < list.size()-1; i++)
            {
                if(((list.get(i)*list.get(i-1)) > 0) || ((list.get(i)*list.get(i+1)) > 0))
                {
                    counter++;
                }
            }
            if(counter > 1)
            {
                return -1;
            }
            else
            {
                int counterTwo = 0;

                for(int j = 0; j < A.length; j++)
                {
                    List<Integer> list2 = new ArrayList<>();
                    for(int i = 0; i < A.length; i++)
                    {
                        list2.add(A[i]);
                    }
                    int[] aux = new int[A.length-1];
                    list2.remove(j);
                    for(int k = 0; k < list2.size(); k++)
                    {
                        aux[k] = list2.get(k);
                    }
                    if(solution(aux) == 0)
                    {
                        counterTwo++;
                    }
                }
                return counterTwo;
            }
        }
    }
}
