package cn.lang.grpc;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class TestClient {
    private final ManagedChannel channel;
    private final TestServiceGrpc.TestServiceBlockingStub blockingStub;
    private static final String host="127.0.0.1";
    private static final int ip=50051;

    public static void main(String[] args) throws InterruptedException {
        TestClient client=new TestClient(host,ip);
        for (int i=0;i<=5;i++){
            client.testResult("<<<<<result>>>>>:"+i);
            TimeUnit.SECONDS.sleep(5);
        }
    }
    public TestClient(String host,int port){
        //usePlaintext表示明文传输，否则需要配置ssl
        //channel  表示通信通道
        channel= ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        //存根
        blockingStub=TestServiceGrpc.newBlockingStub(channel);
    }
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    public void  testResult(String name){
        TestRequest request=TestRequest.newBuilder().setName(name).build();
        TestResponse response=blockingStub.testSomeThing(request);
        System.out.println(response.getMessage());
    }

}

