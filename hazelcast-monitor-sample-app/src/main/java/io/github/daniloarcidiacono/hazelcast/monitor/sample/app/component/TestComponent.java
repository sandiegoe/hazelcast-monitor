package io.github.daniloarcidiacono.hazelcast.monitor.sample.app.component;

import com.hazelcast.cache.ICache;
import com.hazelcast.cardinality.CardinalityEstimator;
import com.hazelcast.core.*;
import com.hazelcast.ringbuffer.Ringbuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
public class TestComponent {
    @Autowired
    private HazelcastInstance hazelcastInstance;

    static class ComplexKey implements Serializable {
        private String name;
        private long age;

        public ComplexKey() {
        }

        public ComplexKey(String name, long age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getAge() {
            return age;
        }

        public void setAge(long age) {
            this.age = age;
        }
    }

    static class ComplexValue implements Serializable {
        private List<Integer> stats = new ArrayList<>();

        public ComplexValue() {
        }

        public ComplexValue(int ...values) {
            for (int value : values) {
                stats.add(value);
            }
        }

        public List<Integer> getStats() {
            return stats;
        }

        public void setStats(List<Integer> stats) {
            this.stats = stats;
        }
    }

    private IExecutorService myExecutor;
    private CardinalityEstimator myCardinalityEstimator;
    private ICache<Object, Object> myCache;
    private ISet<Object> mySet;
    private ITopic<Object> myTopic;
    private ITopic<Object> myTopic1;
    private ITopic<Object> myTopic2;
    private ITopic<Object> myTopic3;
    private ITopic<Object> myTopic4;
    private ITopic<Object> myTopic5;
    private ITopic<Object> myTopic6;
    private IQueue<Object> myQueue;
    private IMap<Object, Object> myMap;
    private IMap<Object, Object> myMap2;
    private IAtomicLong myAtomicLong;
    private IAtomicReference<Object> myAtomicReference;
    private ICountDownLatch myCountDownLatch;
    private IList<Object> myList;
    private ILock myLock;
    private MultiMap<Object, Object> myMultiMap;
    private ITopic<Object> myReliableTopic;
    private ReplicatedMap<Object, Object> myReplicatedMap;
    private Ringbuffer<Object> myRingbuffer;
    private ISemaphore mySemaphore;

    private ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);
    private int t = 0;

    @PostConstruct
    public void init() {
        hazelcastInstance.getExecutorService("myExecutor");
        myCache = hazelcastInstance.getCacheManager().getCache("myCache");
        myCardinalityEstimator = hazelcastInstance.getCardinalityEstimator("myCardinalityEstimator");
        myCardinalityEstimator.add(1);
        myCardinalityEstimator.add(2);
        myCardinalityEstimator.add(3);
        myCache.put("ciao", "Ceo");
        mySet = hazelcastInstance.getSet("mySet");
        myTopic = hazelcastInstance.getTopic("myTopic");
        myTopic1 = hazelcastInstance.getTopic("myTopic1");
        myTopic2 = hazelcastInstance.getTopic("myTopic2");
        myTopic3 = hazelcastInstance.getTopic("myTopic3");
        myTopic4 = hazelcastInstance.getTopic("myTopic4");
        myTopic5 = hazelcastInstance.getTopic("myTopic5");
        myTopic6 = hazelcastInstance.getTopic("myTopic6");
        myQueue = hazelcastInstance.getQueue("myQueue");
        myMap = hazelcastInstance.getMap("myMap");
        myMap2 = hazelcastInstance.getMap("myMap2");
        myAtomicLong = hazelcastInstance.getAtomicLong("myAtomicLong");
        myAtomicReference = hazelcastInstance.getAtomicReference("myAtomicReference");
        myCountDownLatch = hazelcastInstance.getCountDownLatch("myCountDownLatch");
        myList = hazelcastInstance.getList("myList");
        myLock = hazelcastInstance.getLock("myLock");
        myMultiMap = hazelcastInstance.getMultiMap("myMultiMap");
        myReliableTopic = hazelcastInstance.getReliableTopic("myReliableTopic");
        myReplicatedMap = hazelcastInstance.getReplicatedMap("myReplicatedMap");
        myRingbuffer = hazelcastInstance.getRingbuffer("myRingbuffer");
        mySemaphore = hazelcastInstance.getSemaphore("mySemaphore");


        myReplicatedMap.put("ciao", new ComplexKey("sdsdfas", 111));

        myQueue.add("fdjasoijfasdofjsdoifodsjfoidjasiojdasoicuondnaosadno23j093214u231084u318yrhf9sdhcncdsaiocjsadoifujcsadoifuweqoiujoiru32ru342198yr43189yfhcuisdabcino");
        myQueue.add(new ComplexKey("zzzz", 1));

        mySet.add("ciao2");
        mySet.add(new ComplexKey("babfds", 180));

        myMultiMap.put("Ciao", 1);
        myMultiMap.put("Ciao", new ComplexKey("dada", 15));

        myList.add("ciao");
        myList.add(new ComplexKey("Test", 15));
        myList.add(new ComplexValue(0, 1, 2));

        for (int i = 0; i < 20; i++) {
            myList.add("entry" + i);
        }

        // We avoid deadlocks the running two instances!
//        myLock.lock(45, TimeUnit.SECONDS);
        myMap.put(new ComplexKey("Danilo1", 28), new ComplexValue(1, 2, 3));
        myMap.put(new ComplexKey("Mario1", 50), new ComplexValue(7, -1, 5));


        // We avoid deadlocks the running two instances!
        if (myMap2.tryLock("ciao")) {
            myMap2.put("ciao", new ComplexValue(1, 2, 3));
            myMap2.put(new ComplexKey("Mario1", 50), new ComplexValue(7, -1, 5));
//            myMap2.lock("ciao");
        }

        System.out.println("Running the scheduled future!");
        threadPool.scheduleWithFixedDelay(() -> {
            try {
                myList.set(0, "ciao" + t);

                final ComplexKey o = (ComplexKey) myList.get(1);
                o.age = t;
                myList.set(1, o);

                final ComplexValue o2 = (ComplexValue) myList.get(2);
                o2.getStats().set(0, t);
                myList.set(2, o2);

                myTopic.publish(new ComplexKey("A new message has arrived", t));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(myList.get(1));
            }
            t = (t + 1) % 100;
        }, 0, 1, TimeUnit.SECONDS);

        threadPool.scheduleWithFixedDelay(() -> {
            final String getKey = String.valueOf(1 + Math.floor(Math.random() * 10));
            final String putKey = String.valueOf(1 + Math.floor(Math.random() * 10));
            final String removeKey = String.valueOf(1 + Math.floor(Math.random() * 10));
            myCache.get(getKey);
            myCache.put(putKey, "1");
            myCache.remove(removeKey);
        }, 0, 1, TimeUnit.SECONDS);
    }
}