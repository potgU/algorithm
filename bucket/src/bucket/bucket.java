package bucket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class bucket {

   public static int n, m;
   public static ArrayList<Integer> v = new ArrayList<>();
   public static int[][] dp = new int[100][100];
   
   public static int min(int a, int b) {
      if (a > b) return b;
      else return a;
   }
   
   public static int dfs(int a, int b) {
      if (b == 1) {
         int total = 0;
         for (int i = 0; i < a; i++) {
            total += v.get(i);
         }
         float ave = (float)total / (float)a;
         int ave2 = Math.round(ave);
         total = 0;
         for (int i = 0; i < a; i++) {
            total += (v.get(i) - ave2)  * (v.get(i) - ave2);
         }
         dp[a][b] = total;
         return dp[a][b];
      }
      
      if (dp[a][b] != 987654321) return dp[a][b];
      
      for (int i = a - 1; i >= 1; i--) {
         if (i < b - 1) break;
         
         int total = 0;
         
         for (int j = a - 1; j >= i; j--) {
            total += v.get(j);
         }
         
         float ave = (float)total / (float)(a - i);
         int ave2 = Math.round(ave);
         total = 0;
         for (int j = a - 1; j >= i; j--) {
            total += (v.get(j) - ave2) * (v.get(j) - ave2);
         }
         dp[a][b] = min(dp[a][b], total + dfs(i, b - 1));
      }
      return dp[a][b];
   }
   
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      for (int i = 0; i < 100; i++) {
         for (int j = 0; j < 100; j++) {
            dp[i][j] = 987654321;
         }
      }
      Scanner in = new Scanner(System.in);
      
      n = in.nextInt();
      m = in.nextInt();
      
      for (int i = 0; i < n; i++) {
         int a;
         a = in.nextInt();
         v.add(a);
      }
      
      Collections.sort(v);
      
      int ans = dfs(n, m);
      
      System.out.println(ans);
   }
   
   

}