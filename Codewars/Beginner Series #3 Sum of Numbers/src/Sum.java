public class Sum {
    public int GetSum(int a, int b) {
        if (a == b) {
            return a;
        }
        if (a > b) {
            a += b; //a=15 (5+10)
            b=a-b; //b=5 (15-10)
            a -= b; //a=10 (15-5)
        }
        int sum = 0;
        while (a <= b) {
            sum += a;
            a++;
        }
        return sum;
    }
}