//301. Remove Invalid Parentheses - https://leetcode.com/problems/remove-invalid-parentheses/
//Time Complexity: O(2^n) ~ 2 possibilities of choose and don't chose the character
//Space Complexity: O(n) ~ HashSet space

//BFS
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        boolean flag = false;
        //base case
        if(s == null || s.length() == 0){
            return result;
        }

        Queue<String> q = new LinkedList<>();
        q.add(s); //add the string initially

        HashSet<String> set = new HashSet<>(); //visited set to avoid duplicates
        set.add(s); //add the string with q when visited

        while(!q.isEmpty() && !flag){
            int size = q.size(); //if found valid string, no need to proceed to next level
            for(int i=0; i<size; i++){
                String curr = q.poll();
                if(isValid(curr)){
                    result.add(curr);
                    flag=true; //to avoid creating further baby strings when valid string is found
                } else{
                    if(!flag){ //if flag is true, don't process further
                        for(int k=0; k<curr.length(); k++){
                            if(Character.isAlphabetic(curr.charAt(k))){
                                continue; //if a character, contine
                            }//form baby String by removing one character at a time
                            String baby = curr.substring(0, k) + curr.substring(k+1); //0-k, k+1: kth character is removed
                            //after removing, add to result and set
                            if(!set.contains(baby)){
                                set.add(baby);
                                q.add(baby);
                            }
                        }
                    }

                }
            }
        }
        return result;
    }

    private boolean isValid(String s){
        int count=0;
        for(char c: s.toCharArray()){
            if(Character.isAlphabetic(c)) continue;
            if(c == '('){
                count++;
            } else{
                count--;
            }
            if(count < 0) return false;
        }
        return count == 0;
    }
}