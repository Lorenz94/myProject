package net.thumbtack.school.old.introduction;

import java.lang.Integer;

public class FirstSteps {

    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        return x == y ? true : false;
    }

    public boolean isGreater(int x, int y) {
        return x > y ? true : false;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        return  (((xLeft <= x) && (x <= xRight)) && ((yTop <= y) && (y <= yBottom)));
    }

    public int sum(int[] array) {
        int sum = 0;
        for(int elem : array) {
            sum += elem;
        }
        return sum;
    }

    public int mul(int[] array) {
        if (array.length == 0) return 0;

        int mul = 1;
        for(int elem : array) {
            mul *= elem;
        }
        return mul;
    }

    public int min(int[] array) {
        if (array.length == 0) return Integer.MAX_VALUE;

        int min = Integer.MAX_VALUE;
        for(int elem : array) {
            if(min > elem) min = elem;
        }
        return min;
    }

    public int max(int[] array) {
        if (array.length == 0) return Integer.MIN_VALUE;

        int max = array[0];
        for(int elem : array) {
            if(max < elem) max = elem;
        }
        return max;
    }

    public double average(int[] array) {
        return array.length == 0 ? 0 : ((double) sum(array) / array.length);
    }

    public boolean isSortedDescendant(int[] array) {
        if (array.length == 0) return true;

        int temp = Integer.MAX_VALUE;
        for(int elem : array) {
            if (temp > elem) temp = elem;
            else {
                return false;
            }
        }
        return true;
    }

    public void cube(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.pow(array[i], 3);
        }
    }


    public boolean find(int[] array, int value) {
        for(int elem : array) {
            if (value == elem) {
                return true;
            }
        }
        return false;
    }

    public void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    public boolean isPalindrome(int[] array) {
        if (array.length == 0) return true;

        for (int i = 0; i < array.length; i++) {
            if (array[i] != array[array.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public int sum(int[][] matrix) {
        int sum = 0;

        for(int [] elem : matrix) {
            for (int elem1 : elem) {
                sum += elem1;
            }
        }
        return sum;
    }

    public int max(int[][] matrix) {
        if (matrix.length == 0) return Integer.MIN_VALUE;
        if (matrix.length == 1 && matrix[0].length == 0) return Integer.MIN_VALUE;

        int max = matrix[0][0];
        for(int [] elem : matrix) {
            for (int elem1 : elem) {
                if(max < elem1) max = elem1;
            }
        }
        return max;
    }

    public int diagonalMax(int[][] matrix) {
        if (matrix.length == 0) return Integer.MIN_VALUE;

        int max = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            max = max < matrix[i][i] ? matrix[i][i] : max;
        }
        return max;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        for(int [] elem : matrix) {
            if (!isSortedDescendant(elem)) {
                return false;
            }
        }
        return true;
    }


}
