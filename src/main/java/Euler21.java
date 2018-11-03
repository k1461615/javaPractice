package main.java;

public class Euler21 {
    public static void main(String args[]){
        int answer = 0;

        for(int a = 220; a < 10000; a++){
            int b = d(a);
            int db = d(b);

            if(db == a && a != b){
                System.out.println(a + " " + b);
                answer += a + b;
                a = b;
            }
        }

        System.out.println(answer);
    }

    public static int d(int n){
        int answer = 0;

        for(int i=1; i<=n/2; i++){
            if(n%i==0){
                answer += i;
            }
        }

        return answer;
    }
}
