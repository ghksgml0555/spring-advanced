package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field(){
        log.info("main start");
        Runnable userA = ()  -> {
            service.logic("userA");
        };
        Runnable userB = ()  -> {
            service.logic("userB");
        };
        //러너블과 쓰레드가 잘 이해가 안되면 자바 기본서적을 봐야한다.
        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadA.setName("thread-B");

        //A의 로직은 1초면 끝나니깐 완전히 끝나고 B가 실행되도록 한다.(동시성문제 발생x)
        threadA.start();
        sleep(2000); //동시성 문제 발생x
        //sleep(100); //동시성 문제 발생o
        threadB.start();
        sleep(3000); //쓰레드b가 동작할때 메인쓰레드가 끝나지 않도록
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
