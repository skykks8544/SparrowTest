package com.fasoo.syn.basic;

public class DOUBLE_CHECKED_LOCKING_TestCase {
    Helper helper;

    public Helper MakeHelper10(){
        if(null==helper){ /* BUG */
            synchronized(this){
                if(null==helper){
                    helper=new Helper();
                }
            }
        }
        return helper;
    }

    public Helper MakeHelper20(){
        if(null==helper) /* BUG */
            synchronized(this){
                if(null==helper)
                    helper=new Helper();
            }
        return helper;
    }

    public Helper MakeHelper30(){
        //if(helper!=null) return helper;
        if(null!=helper) /* BUG */
            synchronized(this){
                if(null==helper)
                    helper=new Helper();
        }
        return helper;
    }

    public Helper MakeHelper40(){
        int a=0;
        if(a>1 && helper==null){ /* BUG */
            synchronized(this){
                if(helper==null){
                    helper=new Helper();
                }
            }
        }
        return helper;
    }

    public Helper MakeHelper41(){
        int a=0;
        if(a>1 && helper==null){
            synchronized(this){
                helper=new Helper();
            }
        }
        if(helper==null) helper=new Helper();
        return helper;
    }

    public synchronized Helper MakeHelper50(){
        if(null==helper){
            synchronized(this){
                if(null==helper){
                    helper=new Helper();
                }
            }
        }
        return helper;
    }

    public synchronized Helper MakeHelper60(){
        if(null==helper){
            helper=new Helper();
        }
        return helper;
    }

    public Helper MakeHelper61(){
        if(null==helper){
            synchronized(this){
                helper=new Helper();
            }
        }
        return helper;
    }

    public Helper MakeHelper62(){
        synchronized(this){
            if(null==helper){
                helper=new Helper();
            }
        }
        return helper;
    }

    public synchronized Helper MakeHelper70(){
        if(null==helper)
            synchronized(this){
                if(null==helper){
                    helper=new Helper2(){
                        @Override
                        void create(){
                            if(null==helper){ /* BUG */
                                synchronized(this){
                                    if(null==helper){
                                        helper=new Helper();
                                    }
                                }
                            }
                        }
                    };
                }
            }
        return helper;
    }

    class Helper{
        public synchronized Helper MakeHelper(){
            return new Helper();
        }
    }

    abstract class Helper2 extends Helper{
        abstract void create();
    }
}
