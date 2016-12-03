# java8函数式编程
#### 引子
###### 为什么修改java
	多核cpu 为弥补并行开发的短板
###### 什么是函数式编程
	使用高阶函数map，filter等，从更高的抽象层次考虑问题。
###### 有什么好处
	简化开发，简洁的代码 
	提高性能 
		lambda vs 匿名类， stream延迟遍历vs for循环（过滤器和迭代器的一场对决）

#### Lambda表达式
###### 由匿名函数改造的第一个Lambda表达式
###### 类型
	BinaryOperator<Integer> add=(x,y)->x+y;
###### 引用的值 （final）
###### 函数接口
	定义 单抽象方法（SAM）接口 Runnable，Callable
	最重要的几个函数接口:（Predicate<T>，Consumer<T>,Function<T,R>,Supplier<T>,UnaryOperator<T>,BinaryOperator<T>
###### 类型推断

#### 流（Stream）
	和Iterator类似，是使用内部迭代，经常和Lambda表达式配合使用，可自动并行化代码是其很重要的一个特性。
###### 与集合的区别
	1.stream不存储值
	2.stream避免与状态发生关联
	3.stream上的操作尽量做到延迟求值
	4.stream可用无限长 limit（），findFirst（）
	5.像Iterator一样是消耗品。
###### 实现机制 惰性求值和及早求值
###### 常用的流操作（collect，map，filter，flatMap，max|min，reduce）
###### 多次调用流操作应该避免，换为链式操作

#### 新类库
###### 在代码中使用Lambda表达式
	以Logger为例
###### 针对基本类型的特殊处理
###### 重载如何解析类型
	规则：
	1.只有一个可能类型的情况
	2.有多个可能的目标类型
	3.有多个可能类型且具体类型不明确
###### @FunctionalInterface
###### 二进制接口的兼容，默认方法 （用default、public修饰， 典型例子：Comparator接口）
###### 多重继承
	原则：1.类的优先级高于接口；2，子接口高于父接口，子类高于父类
	权衡： 多重继承的优缺点
###### 接口的静态方法
###### Optional 
	（替代null）of(),empty(),get(),isPresent(),orElse()

#### 高级集合类和收集器
###### 方法引用
	能用Lambda表达式的地方就能用方法引用
###### 元素顺序
	有序集合生成的流中元素是有序的，无序集合生成的是无序的
	sorted方法和unordered方法，以及并行处理时用的forEachOrdered方法
###### 收集器 [可用来计算流的最终值，是reduce方法的模拟）
	转换成集合（toList,toSet,toCollection)
	值 (maxBy,minBy,averagingInt,summingInt)
	数据分块 (partitioningBy)
	数据分组 (groupingBy)
	字符串 (joining)
	组合收集器 (maping,下游收集器)
	重构和组合收集器 ( joining,Collector,StringJoiner)
	归一化处理 （reducing收集器）

#### 数据并行化
###### 并行和并发，并发的重要性
	阿姆达尔定律
###### 流的并行化 parallel  parallelStream
###### 模拟系统
	蒙特卡洛模拟法
###### 限制	parallel和sequential
###### 性能
	影响并行性能的要素（数据大小，源数据结构，装箱，核的数量，单元处理开销）
###### 并行化数组操作 parallelPrefix paralelSetAll parallelSort 


#### 测试、调试和重构
###### 重构侯选项 
	暴露内部状态：Logger 把Lambda表达式逻辑封装在Logger对象中
	孤独的覆盖：ThreadLocal 改用withInitial
	同样的东西写两遍： DRY模式	
###### 在测试替身时使用Lambda表达式
###### 日志和打印消息
	解决方案：peek
###### 设计和架构的原则
###### 函数式语言中的设计模式
	模式已被吸收成为语言的一部分
	实现细节有所变化
	新的语言或范式下获得了原本没有的能力，产生了新的解决方法（元编程）
###### 设计模式 （的本质是弥补语言功能的弱点）
	命令者模式 当语言有了闭包特性，就不需要命令者模式了
	策略模式
	观察者模式
	模版方法模式
###### 编写领域专用语言
###### SOLID原则

#### 编写并发程序
###### 为什么要使用非阻塞I／O
###### 回调
###### 消息传递架构
###### 末日金字塔
###### Future
###### CompletableFuture
###### 响应式编程
###### 何时何地使用新技术

#### 推荐
	《java8函数式编程》、《函数式编程思维》、《代码的未来》、《Learning Scala》


