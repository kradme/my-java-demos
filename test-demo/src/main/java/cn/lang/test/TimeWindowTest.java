package cn.lang.test;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TimeWindowTest {

    public static final Long[] timestamps = new Long[]{1642645103930L,1642645104807L,1642645105129L,1642645105533L,1642645106294L,1642645107203L,1642645108295L,1642645109134L,1642645110887L,1642645110952L,1642645113245L,1642645113879L,1642645115918L,1642645117983L,1642645119651L,1642645121387L,1642645123602L,1642645125430L,1642645126345L,1642645128917L,1642645129811L,1642645132309L,1642645133231L,1642645134304L,1642645136638L,1642645137074L,1642645138027L,1642645138515L,1642645138863L,1642645139605L,1642645141214L,1642645141958L,1642645142349L,1642645142672L,1642645144018L,1642645144442L,1642645145523L,1642645145636L,1642645147557L,1642645147959L,1642645150558L,1642645151331L,1642645152104L,1642645153316L,1642645155647L,1642645156044L,1642645156658L,1642645157327L,1642645159220L,1642645161595L,1642645161733L,1642645162244L,1642645164270L,1642645166234L,1642645169179L,1642645169816L,1642645170588L,1642645172190L,1642645173778L,1642645174130L,1642645174879L,1642645177818L,1642645179120L,1642645179312L,1642645180068L,1642645180691L,1642645183595L,1642645184880L,1642645187329L,1642645187586L,1642645189035L,1642645192015L,1642645194860L,1642645197421L,1642645198610L,1642645199251L,1642645200137L,1642645202152L,1642645202401L,1642645203514L,1642645203877L,1642645204268L,1642645206083L,1642645207703L,1642645209369L,1642645211626L,1642645213829L,1642645216075L,1642645218285L,1642645220904L,1642645223426L,1642645225162L,1642645226817L,1642645229342L,1642645229477L,1642645231375L,1642645231514L,1642645232616L,1642645233410L,1642645233633L};

    public static void main(String[] args) {
        Map<Long, List<Long>> map = new LinkedHashMap<>();
        for (Long timestamp : timestamps) {
            long l = timestamp/3000+1000;
            putMap(map, l, timestamp);
        }
        for (Map.Entry<Long, List<Long>> entry : map.entrySet()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
            String format = simpleDateFormat.format(new Date(entry.getKey() * 3000L));
            System.out.print(format+": [");
            for (Long aLong : entry.getValue()) {
                System.out.print(simpleDateFormat.format(new Date(aLong))+",");
            }
            System.out.println("]");
        }
        System.out.println(map);
    }
    private static void putMap(Map<Long, List<Long>> map, Long l, Long timestamp){
        if (map.containsKey(l)){
            map.get(l).add(timestamp);
        }else {
            map.put(l, new ArrayList<>());
            map.get(l).add(timestamp);
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 100; i++) {
//            System.out.println(System.currentTimeMillis());
//            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(3000));
//        }
//    }
}
