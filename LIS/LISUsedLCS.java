package LIS;

import java.util.Arrays;

import static java.util.Arrays.sort;

import LCS.LCSDynamic;


/**
 * הפונקציה שלנו לוקחת מערך X וממיינת אותו
 * כדי שנוכל להשתמש בפונקציה של LCS נצטרך ךשנות אותה שתפעל על
 * מערך ולא על מחרוזות בלבד
 */
public class LISUsedLCS {

    public static void main(String[] args) {
        int[] x = {1, 7, 9, 50, 2, 3, 4, 5};
        int[] arr = {6, 2, 8, 3, 15, 9};
        System.out.println("==========================");
        System.out.println("sub arr "+Arrays.toString(usedLCS(arr)));
        System.out.println("==========================");
        int[] lenPath = usedLCS(x);
        System.out.println(Arrays.toString(lenPath) + " " + lenPath.length);

    }

    private static int[] usedLCS(int[] x) {
        int[] sorted = new int[x.length];

        for (int i = 0; i < x.length; i++) {
            sorted[i] = x[i];
        }
        sort(sorted);

        return LCS(x,sorted);
    }

    public static int [] LCS(int[] x, int[] y) {
        System.out.println(Arrays.toString(x));
        int[][] helpTable = new int[y.length + 1][x.length + 1];
        for (int i = 0; i < helpTable.length; i++) {
            for (int j = 0; j < helpTable[0].length; j++) {
                if (i == 0 || j == 0)
                    helpTable[i][j] = 0;
                else if (y[i - 1] == x[j - 1])
                    helpTable[i][j] = helpTable[i - 1][j - 1] + 1;
                else
                    helpTable[i][j] = Math.max(helpTable[i - 1][j], helpTable[i][j - 1]);

            }
            System.out.println(Arrays.toString(helpTable[i]));
        }
        System.out.println(Arrays.toString(y));

        int[] ans = new int[helpTable[y.length][x.length]];
        int i = helpTable.length - 1;
        int j = helpTable[0].length - 1;
        int index = ans.length-1;
        while (helpTable[i][j] > 0) {
            if (y[i - 1] == x[j - 1]) {
                ans[index--] = y[i - 1];
                i--;
                j--;
            } else {
                if (helpTable[i - 1][j] > helpTable[i][j - 1])
                    i--;
                else j--;
            }
        }



        return ans;
    }
}
