package test1;

public class frogjumping {
    public static void main(String[] args) {

        System.out.println(frogjump(40, 3,1));
    }

    public static int frogjump(int deep, int jumphigh, int fallhigh){
        int finalhigh = deep / (jumphigh - fallhigh);
        return finalhigh;
    }
}
