public class DontCallThreadRun {
   void foo() {
       Thread t = new Thread();
       t.run();
   }
}
