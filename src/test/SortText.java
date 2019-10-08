/**
 * Created by wen on 2017/7/19.
 */
public class SortText {
    static int arr[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
    public static void main(String[] args) {
        //insertSort();
        bubbleSort();
        for(int a:arr){
            System.out.println(a);
        }
    }

    //插入排序
    public static void insertSort(){
        int temp=0;
        for(int i=1;i<arr.length;i++){
            int j=i-1;
            temp=arr[i];
            for(;j>=0&&temp<arr[j];j--){
                arr[j+1]=arr[j];
            }
            arr[j+1]=temp;
        }
    }

    public static void bubbleSort(){
        int temp=0;
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1;j++){
                if(arr[j]>arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
}
