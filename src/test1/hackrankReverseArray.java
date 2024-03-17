package test1;

public class hackrankReverseArray {
    public static void main(String[] args) {

        int N[] = {1, 2, 3, 4, 5};

        for(int i = 1; i <= 10; i++ ){
            System.out.println("2 * " + N[i] + " = " + (N[i] * i-1));
            if (i == 5) {
                break;
            }
        }

    }
}
