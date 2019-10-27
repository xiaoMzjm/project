import java.util.*;

public class Main {

    private final static Integer MIN = 0;

    private final static Integer MAX = 300000000;

    private final static String SPLIT = ",";

    /**
     * 1490942090132,302
     * 1490942040813,646
     * 1490942055040,485
     * 1490942149547,947
     * @param args
     */
    public static void main(String[] args) {
        // 输入行数
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // 题目要求数据量为[0,30000万]
        if(n < MIN || n > MAX) {
            throw new IllegalArgumentException("0<= n <= 300000000");
        }

        // 数据为0时，输出0，代表0条
        if(n == 0) {
            System.out.println(0);
            return ;
        }

        // 使用TreeMap排序
        TreeMap<Long,Integer> treeMap = new TreeMap<>();
        for(int i = 0 ; i < n ; i ++) {
            String line = in.next();
            if(line == null || "".equals(line)) {
                throw new IllegalArgumentException("line is blank");
            }
            String[] lineArray = line.split(",");
            if(lineArray.length != 2) {
                throw new IllegalArgumentException("line need split by ','");
            }
            treeMap.put(Long.valueOf(lineArray[0]) , Integer.valueOf(lineArray[1]));

        }

        // 起始分钟
        Long firstMinute = treeMap.firstEntry().getKey() / 10000 * 10000;
        // 截止分钟
        Long lastMinute = treeMap.lastEntry().getKey() / 10000 * 10000;

        // 总共有几分钟
        int minuteNum = 0;

        Iterator<Long> iterator = treeMap.keySet().iterator();


        List<String> list = new ArrayList<>();

        int min = Integer.MAX_VALUE ;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int count = 0 ;
        for(long nowMinute = firstMinute ; nowMinute <= lastMinute ; ) {



            Long nowTime = 0L;
            if(iterator.hasNext()){
                nowTime = iterator.next();
            }

            System.out.println("=====nowTime = " + nowTime + " , nowMinute=" + nowMinute + " , lastMinute=" + lastMinute);

            // 如果在这一分钟内，则不断计算最大值、最小值和累加
            if(nowMinute <= nowTime && nowTime < lastMinute) {


                int nowValue = treeMap.get(nowTime);
                //System.out.println("=====" + nowMinute + " , " + nowValue);

                sum += nowValue;
                min = Math.min(min , nowValue);
                max = Math.max(max , nowValue);
                count ++;
            }

            // 否则的话，记录这1分钟的数据，复位临时变量
            else {

                // 如果这1分钟没数据，那么都为0
                if(count == 0 ) {
                    list.add(Main.append(nowMinute , 0 , 0, 0 ));
                }
                else {
                    list.add(Main.append(nowMinute , max , min, sum ));
                }

                // 复位
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                sum = 0;
                count = 0;

                // 下一分钟
                nowMinute += 60000;

                // 分钟数
                minuteNum ++;
            }
        }


        // 输出结果
        System.out.println(minuteNum);
        for(String str : list) {
            System.out.println(str);
        }

    }

    private static String append(Long minute , int max ,int min , int sum){
        return minute + SPLIT + max + SPLIT + min + SPLIT + sum;
    }




}
