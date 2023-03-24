package hello.advanced.trace;

import java.util.UUID;

public class TraceId {

    private String id; //트랜잭션 id
    private int level; //깊이

    public TraceId() {
        this.id = createId();
        this.level = 0 ;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0,8); //너무길어서 substring으로 자른다.
    }

    public TraceId createNextId(){
        return new TraceId(id, level + 1); //깊이를 증가시킴
    }

    public TraceId createPreviousId(){
        return new TraceId(id, level - 1); //깊이를 증가시킴
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
