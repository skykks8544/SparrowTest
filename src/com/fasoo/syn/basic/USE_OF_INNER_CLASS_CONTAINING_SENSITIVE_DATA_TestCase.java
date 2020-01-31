package com.fasoo.syn.basic;
// USE_OF_INNER_CLASS_CONTAINING_SENSITIVE_DATA Test Case
public class USE_OF_INNER_CLASS_CONTAINING_SENSITIVE_DATA_TestCase {
    private String ownerMemberOne, ownerMemberTwo;
    private final long a = 0;
    private float aa = 0;

    public USE_OF_INNER_CLASS_CONTAINING_SENSITIVE_DATA_TestCase(String varOne, String varTwo){
        this.ownerMemberOne = varOne;
        this.ownerMemberTwo = varTwo;
        float aaa = aa%2;
    }

    public class InnerClassPublic{
        private String innerMemberOne;
        private String ownerMemberOne;

        public InnerClassPublic(String innerVarOne){
            this.innerMemberOne = innerVarOne;
            if(innerMemberOne.equals(ownerMemberTwo)){				/* Bug */
                System.out.println("true");
                ownerMemberOne = USE_OF_INNER_CLASS_CONTAINING_SENSITIVE_DATA_TestCase.this.ownerMemberOne;		/* Bug */
                this.innerMemberOne = ownerMemberTwo;					/* Bug */
                USE_OF_INNER_CLASS_CONTAINING_SENSITIVE_DATA_TestCase.this.ownerMemberOne = this.innerMemberOne;		/* Bug */
            }
        }

        public String concat(String separator){
            System.out.println(((a%1) + (aa*1)));				/* Bug */
            System.out.println("Value of memberOne is: " + ownerMemberOne + ", memberTow is: " + ownerMemberTwo);		/* Bug */
            if(ownerMemberOne == null || ownerMemberOne.length() == 0){
                System.out.println("ownerMemberOne null");
            }
            if(null == ownerMemberTwo || 0 == ownerMemberTwo.length()){			/* Bug */
                System.out.println("ownerMemberTwo null");
            }
            return USE_OF_INNER_CLASS_CONTAINING_SENSITIVE_DATA_TestCase.this.ownerMemberTwo + separator + this.innerMemberOne;		/* Bug */
        }

        public class InnerClassPublic2{
            private String member1, member2;

            public InnerClassPublic2(){
                member1 = ownerMemberOne;				/* Bug */
                member2 = ownerMemberTwo;				/* Bug */

                new AbstractClass(){
                    @Override
                    public void test(){
                        if(null == ownerMemberTwo || 0 == ownerMemberTwo.length()){			/* Bug */
                            System.out.println("ownerMemberTwo null");
                        }
                        ownerMemberTwo = "test";				/* Bug */
                        System.out.println("member1 : " + member1 + ", member2 : " + member2);
                    }
                }.test();
            }
        }
    }

    public class InnerClassPublic2{
        private String member1, member2;

        public InnerClassPublic2(){
            this.member1=ownerMemberOne;		/* Bug */
            ownerMemberTwo="test";						/* Bug */
        }
    }
}

abstract class AbstractClass{
    String ownerMemberOne, ownerMemberTwo;
    public abstract void test();

    public class InnerClassPublic2{
        private String member;

        public InnerClassPublic2(String member){
            this.member=ownerMemberOne;			/* Bug */
            ownerMemberTwo=member;				/* Bug */
        }
    }
}
