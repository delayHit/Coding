package com.delay.Tutorial.supervisor;

import akka.actor.UntypedActor;
import scala.Option;

/**
 * Created by liubenlong on 2017/1/12.
 */
public class RestartActor extends UntypedActor {

    public  enum  Msg{
        DONE, RESTART;
    }


    @Override
    public void preStart() throws Exception {
        System.out.println("preStart    hashCode=" + this.hashCode());
    }
    @Override
    public void postStop() throws Exception {
        System.out.println("postStop    hashCode=" + this.hashCode());
    }



    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        System.out.println("preRestart    hashCode=" + this.hashCode());
    }
    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        System.out.println("postRestart    hashCode=" + this.hashCode());
    }



    @Override
    public void onReceive(Object o) throws Throwable {
        if(o == Msg.DONE){
            getContext().stop(getSelf());
        }else if(o == Msg.RESTART){
            System.out.println(((Object) null).toString());
            //抛出异常，默认会被restart，但这里会resume
            double a = 1/0;
        }else{
            unhandled(o);
        }

    }
}
