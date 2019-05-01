通过jstack找到死锁的线程

```JAVA
[zhangjiamingdeMacBook-Pro:Desktop zhangjiaming$ jps
15811 Launcher
15812 DealLock
15797 RemoteMavenServer
15813 Jps
487 
15387 Main

[zhangjiamingdeMacBook-Pro:Desktop zhangjiaming$ jstack 15812
2019-04-14 19:22:31
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.171-b11 mixed mode):

"Attach Listener" #12 daemon prio=9 os_prio=31 tid=0x00007ff86206b000 nid=0x470b waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"pool-1-thread-2" #11 prio=5 os_prio=31 tid=0x00007ff86381e800 nid=0x3903 waiting for monitor entry [0x000070000ed7b000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at com.zjm.java.jvm.memory.DealLock$2.run(DealLock.java:42)
	- waiting to lock <0x00000007957881e0> (a java.lang.Object)
	- locked <0x00000007957881f0> (a java.lang.Object)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-1-thread-1" #10 prio=5 os_prio=31 tid=0x00007ff862069800 nid=0x3f03 waiting for monitor entry [0x000070000ec78000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at com.zjm.java.jvm.memory.DealLock$1.run(DealLock.java:26)
	- waiting to lock <0x00000007957881f0> (a java.lang.Object)
	- locked <0x00000007957881e0> (a java.lang.Object)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"Service Thread" #9 daemon prio=9 os_prio=31 tid=0x00007ff86289c800 nid=0x4103 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread2" #8 daemon prio=9 os_prio=31 tid=0x00007ff86206a800 nid=0x4203 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" #7 daemon prio=9 os_prio=31 tid=0x00007ff862823000 nid=0x3303 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #6 daemon prio=9 os_prio=31 tid=0x00007ff86381c000 nid=0x3203 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Monitor Ctrl-Break" #5 daemon prio=5 os_prio=31 tid=0x00007ff862824000 nid=0x3003 runnable [0x000070000e666000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at sun.nio.cs.StreamDecoder.readBytes(StreamDecoder.java:284)
	at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:326)
	at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
	- locked <0x0000000795708a08> (a java.io.InputStreamReader)
	at java.io.InputStreamReader.read(InputStreamReader.java:184)
	at java.io.BufferedReader.fill(BufferedReader.java:161)
	at java.io.BufferedReader.readLine(BufferedReader.java:324)
	- locked <0x0000000795708a08> (a java.io.InputStreamReader)
	at java.io.BufferedReader.readLine(BufferedReader.java:389)
	at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:64)

"Signal Dispatcher" #4 daemon prio=9 os_prio=31 tid=0x00007ff863047000 nid=0x2f03 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=31 tid=0x00007ff863811000 nid=0x4d03 in Object.wait() [0x000070000e460000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x0000000795588ed0> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
	- locked <0x0000000795588ed0> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:212)

"Reference Handler" #2 daemon prio=10 os_prio=31 tid=0x00007ff863043800 nid=0x2b03 in Object.wait() [0x000070000e35d000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x0000000795586bf8> (a java.lang.ref.Reference$Lock)
	at java.lang.Object.wait(Object.java:502)
	at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
	- locked <0x0000000795586bf8> (a java.lang.ref.Reference$Lock)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"main" #1 prio=5 os_prio=31 tid=0x00007ff863008000 nid=0x2803 waiting on condition [0x000070000dd4b000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
	at java.lang.Thread.sleep(Native Method)
	at com.zjm.java.jvm.memory.DealLock.main(DealLock.java:52)

"VM Thread" os_prio=31 tid=0x00007ff863041000 nid=0x5003 runnable 

"GC task thread#0 (ParallelGC)" os_prio=31 tid=0x00007ff862804800 nid=0x1e07 runnable 

"GC task thread#1 (ParallelGC)" os_prio=31 tid=0x00007ff862805800 nid=0x1f03 runnable 

"GC task thread#2 (ParallelGC)" os_prio=31 tid=0x00007ff863800000 nid=0x5403 runnable 

"GC task thread#3 (ParallelGC)" os_prio=31 tid=0x00007ff862806000 nid=0x5203 runnable 

"VM Periodic Task Thread" os_prio=31 tid=0x00007ff863048000 nid=0x4003 waiting on condition 

JNI global references: 15


Found one Java-level deadlock:
=============================
"pool-1-thread-2":
  waiting to lock monitor 0x00007ff8638dc9b8 (object 0x00000007957881e0, a java.lang.Object),
  which is held by "pool-1-thread-1"
"pool-1-thread-1":
  waiting to lock monitor 0x00007ff862819d58 (object 0x00000007957881f0, a java.lang.Object),
  which is held by "pool-1-thread-2"

Java stack information for the threads listed above:
===================================================
"pool-1-thread-2":
	at com.zjm.java.jvm.memory.DealLock$2.run(DealLock.java:42)
	- waiting to lock <0x00000007957881e0> (a java.lang.Object)
	- locked <0x00000007957881f0> (a java.lang.Object)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
"pool-1-thread-1":
	at com.zjm.java.jvm.memory.DealLock$1.run(DealLock.java:26)
	- waiting to lock <0x00000007957881f0> (a java.lang.Object)
	- locked <0x00000007957881e0> (a java.lang.Object)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

Found 1 deadlock.

```