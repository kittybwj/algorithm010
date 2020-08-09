class Solution {
    public String reverseStr(String s, int k) {
        char[] str = s.toCharArray();
        for(int i = 0; i < s.length(); i += 2 * k) {
            int l = i, r = Math.min(i + k - 1, str.length - 1);
            reverse(str, l, r);
        }
        return new String(str);
    }

     public void reverse(char[] str, int l, int r) {
        while(l <= r) {
            char tmp = str[l];
            str[l] = str[r];
            str[r] = tmp;
            l++;
            r--;
        }
    }
}