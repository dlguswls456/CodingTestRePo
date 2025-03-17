import java.util.*;

class Solution {
    static PriorityQueue<Work> pq = new PriorityQueue<>();
    static PriorityQueue<WorkTime> pqTime = new PriorityQueue<>();
    
    static class Work implements Comparable<Work>{
        int no;
        int reqTime;
        int takeTime;
        
        Work(int no, int reqTime, int takeTime){
            this.no = no;
            this.reqTime = reqTime;
            this.takeTime = takeTime;
        }
        
        public int compareTo(Work o){
            if(this.takeTime == o.takeTime){
                if(this.reqTime == o.reqTime){
                    return this.no - o.no;
                }else{
                    return this.reqTime - o.reqTime;
                }
            }else{
                return this.takeTime - o.takeTime;
            }
        }
    }
        
        
    static class WorkTime implements Comparable<WorkTime>{
        int no;
        int reqTime;
        int takeTime;

        WorkTime(int no, int reqTime, int takeTime){
            this.no = no;
            this.reqTime = reqTime;
            this.takeTime = takeTime;
        }

        public int compareTo(WorkTime o){
            if(this.reqTime == o.reqTime){
                if(this.takeTime == o.takeTime){
                    return this.no - o.no;
                }else{
                    return this.takeTime - o.takeTime;
                }
            }else{
                return this.reqTime - o.reqTime;
            }
        }
    }
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        int idx = 0;
        for(int[] job: jobs){
            pqTime.add(new WorkTime(idx++, job[0], job[1]));
        }
        
        int curTime =0;
        while(true){         
            while(!pqTime.isEmpty() && curTime>=pqTime.peek().reqTime){
                WorkTime wt = pqTime.poll();
                pq.add(new Work(wt.no, wt.reqTime, wt.takeTime));
            }
            
            if(pq.isEmpty() && !pqTime.isEmpty()){
                WorkTime wt = pqTime.poll();
                pq.add(new Work(wt.no, wt.reqTime, wt.takeTime));
                
                curTime = wt.reqTime;
            }
           
            Work work = pq.poll();
            answer += curTime + work.takeTime - work.reqTime;
            
            curTime += work.takeTime;
            
            if(pq.isEmpty() && pqTime.isEmpty()){
                break;
            }
        }
        
        return answer/jobs.length;
    }
}