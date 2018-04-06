### 基本知识介绍：

#### 1.standard

 standard是Activity默认的启动模式，在不进行显示指定的情况下，所有活动都会自动使用这种启动模式。
  每次启动都一个新的Activity位于栈顶。
  android:launchMode="standard",此时每次点击按钮都会创建新的Activity


#### 2.singleTop

当Activity的启动模式为singleTop时,当启动的Activity已经处于Activity栈顶时，则直接使用。
android:launchMode="singleTop"

#### 3.singleTask

当活动的启动模式为singleTask时，启动该Activity会现在栈中检查是否已存在，若存在则直接将该活动之上的Activity全部出栈。
android:launchMode="singleTask"

#### 3.singleInstance
singleInstance模式下会有一个单独的返回栈来管理活动。不管哪个应用程序来访问该活动，都共用同一个栈，这样就可以允许其他程序调用，实现共享该活动。
android:launchMode="singleInstance"


### 通过demo来验证上面的说明


### 一 、可以通过demo来组合各种状态下的task的结构，非常的清晰明了。

请根据理解需要，执行各种状态下的启动。


###二 、执行命令行，adb shell dumpsys activity.

查看Running activities (most recent first):可以非常清楚的看到目前的activity的回退结构。

例如

```
    Running activities (most recent first):
      TaskRecord{1030004 #1438 A=com.abbott.launchmode U=0 StackId=1 sz=5}
        Run #5: ActivityRecord{f2379d4 u0 com.abbott.launchmode/.SingleTopB t1438}
        Run #4: ActivityRecord{a90482d u0 com.abbott.launchmode/.SingleTaskC t1438}
      TaskRecord{f6069ed #1439 A=com.abbott.launchmode U=0 StackId=1 sz=1}
        Run #3: ActivityRecord{eae0886 u0 com.abbott.launchmode/.SingleInstanceD t1439}
      TaskRecord{1030004 #1438 A=com.abbott.launchmode U=0 StackId=1 sz=5}
        Run #2: ActivityRecord{9ce7b2 u0 com.abbott.launchmode/.SingleTopB t1438}
        Run #1: ActivityRecord{85b44e u0 com.abbott.launchmode/.StandardA t1438}
        Run #0: ActivityRecord{a289987 u0 com.abbott.launchmode/.MainActivity t1438}

    mResumedActivity: ActivityRecord{f2379d4 u0 com.abbott.launchmode/.SingleTopB t1438}
```




### 总结：

1、其中一个TaskRecord可以理解为一个task.一个task可以包含多个activity.
由于SingleInstance是独占一个task。可以年到A是默认以包名存在，但是taskId是不一样的。

2、由上面的图可以看到一个相同的task可以被别外一个task隔开。如上图两个一样的1438被1439隔开。但是他们还是同一个task

3、SingleTask是模式是如果没有指定taskAffinity的情况下，会在当前默认的task(即启动他的task)中。


4、SingleTask是模式是如果在指定taskAffinity后，会新建一个task.在TaskRecord中A与taskAffinity中指定的值一样。如下

```
    Running activities (most recent first):
      TaskRecord{7b7175c #1442 A=com.abc.test U=0 StackId=1 sz=2}
        Run #6: ActivityRecord{b2ff2df u0 com.abbott.launchmode/.SingleTopB t1442}
        Run #5: ActivityRecord{9c53cf6 u0 com.abbott.launchmode/.SingleTaskC t1442}
      TaskRecord{f3b2a65 #1441 A=com.abbott.launchmode U=0 StackId=1 sz=1}
        Run #4: ActivityRecord{430f9b3 u0 com.abbott.launchmode/.SingleInstanceD t1441}
      TaskRecord{88b3beb #1440 A=com.abbott.launchmode U=0 StackId=1 sz=4}
        Run #3: ActivityRecord{c10d172 u0 com.abbott.launchmode/.StandardA t1440}
        Run #2: ActivityRecord{f2d8a0e u0 com.abbott.launchmode/.SingleTopB t1440}
        Run #1: ActivityRecord{d7da355 u0 com.abbott.launchmode/.StandardA t1440}
        Run #0: ActivityRecord{61c7502 u0 com.abbott.launchmode/.MainActivity t1440}

    mResumedActivity: ActivityRecord{b2ff2df u0 com.abbott.launchmode/.SingleTopB t1442}

```


