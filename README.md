# java8函数式编程
#### 引子
###### 为什么修改java
	多核cpu 为弥补并行开发的短板
###### 什么是函数式编程
	使用高阶函数map，filter等，从更高的抽象层次考虑问题。
###### 有什么好处
	简化开发，简洁的代码 
	提高性能 
		lambda vs 匿名类
		stream延迟遍历vs for循环（过滤器和迭代器的一场对决）

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
	- collect(toList())方法由流里的值生成一个列表，是一个及早求值的操作
	- map 如果有函数可将一个类型的值转换成 另一种类型，map操作就可以使用该函数，将流中的值转换成一个新的流
	- filter 遍历数据并检查其中的元素
	- flatMap可用stream替换值，然后将多个stream连接成一个。
	- max，min 求最大最小值
	- reduce 实现从一组值生成一个值。
###### 多次调用流操作应该避免，换为链式操作

#### 新类库
###### 在代码中使用Lambda表达式
	以Logger为例
###### 针对基本类型的特殊处理
	如果方法返回的是基本类型，就在基本类型前家To，如ToLongFunction。（返回的是一个特殊处理的Stream）
	如果是参数是基本类型，则不加前缀只需类型名，如LongFunciton。
	如果高阶函数使用基本类型，则在操作后加后缀To再加基本类型，如mapToLong。
	特殊的Stream提供额外的方法，避免重复实现一些通用的方法，让代码更能体现出数值计算的意图。
###### 重载如何解析类型
	规则：
	1.只有一个可能类型的情况，由相应函数接口的参数类型推导得出。
	2.有多个可能的目标类型，由最具体的类型推导得出。
	3.有多个可能类型且具体类型不明确，需要人为指定类型。
###### @FunctionalInterface注解
###### 二进制接口的兼容，默认方法 （用public、default修饰， 典型例子：Comparator接口）
###### 多重继承
	原则：1.类的优先级高于接口；2，子接口高于父接口，子类高于父类
	权衡： 多重继承的优缺点
###### 接口的静态方法
	在编程过程中积累类这样一条经验：类是放置工具方法的好地方，比如Java7中引入的Objects类。
	如果一个方法在语义上和类|接口的概念相关，就该将这个方法放入那个类|接口中，而不是另一个工具类中，这样有助于更好的组织代码，
	Stream的静态方法不止of，还有range和iterate
###### Optional 
	（替代null）of(),empty(),get(),isPresent(),orElse()

#### 高级集合类和收集器
###### 方法引用
	能用Lambda表达式的地方就能用方法引用
###### 元素顺序
	有序集合生成的流中元素是有序的，无序集合生成的是无序的
	有些方法可以生成顺序，如sorted方法
	有些操作在有序流上开销更大，这时可以用unordered方法消除顺序
	但多数在有序流上效率更高，为保证元素在并行时按顺序处理，就需要调用forEachOrdered方法
###### 收集器 [可用来计算流的最终值，是reduce方法的模拟）
	定义：一种通用的从流生成值的结构。只要把它传给collect方法就可以了。
	转换成集合（toList,toSet,toCollection)
	值 (maxBy,minBy按照特定顺序生成值,averagingInt,summingInt特殊流上的额外方法)
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
	
#### 设计和架构的原则
###### 函数式语言中的设计模式
	模式已被吸收成为语言的一部分
	模式中描述的解决方法在函数式范式下依然成立，但实现细节有所变化
	新的语言或范式下获得了原本没有的能力，产生了新的解决方法（元编程）
###### 设计模式 （的本质是弥补语言功能的弱点）
	命令者模式 大量地用在实现组件化的图形界面系统、撤销功能、线程池、事务和向导中。 当语言有了闭包特性，就不需要命令者模式了
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
	英文版《java8函数式编程》、《函数式编程思维》、《Learning Scala》
	《代码的未来》


