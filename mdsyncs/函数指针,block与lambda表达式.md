---
title: "函数指针,block与lambda表达式"
date: 2020-07-07T00:56:36+08:00
draft: true
tags: ["iOS"]
category: "iOS"
---

**C语言**中的**函数指针**, **OjbC**中的**block**,以及**C++**和**Java**中的**lambda表达式**非常类似.

在学习单个语言时不会想到他们之间的共通点和区别，最近在学习C++ 和 Java语法时，发觉**lambda表达式**，这不是跟ObjC中的 block 非常相似吗？

说到底不都是，可以**捕获变量**的**指向函数的指针**吗?

所以打算写一篇总结各语言中**函数指针**的使用的文章。

因为笔者是iOS开发人员，所以对 ObjC的block会介绍的详细一些。 C函数指针 和 C++ lambda表达式简单过一下。

## 一, C函数指针

### 概念

- 在学习 C语言时，我们学到了**函数指针**，其定义就是指向函数的指针。
- 通常**指针**是指向一个**变量**，而**函数指针**是指向函数。
- 函数指针也可以像函数一样，**调用**函数，**传递参数**。



### 代码举例 

以下代码是一个简单的**函数指针**的用法

```c
// 函数
int sum(int a, int b){
    return a + b;
}
int main(int argc, const char * argv[]) {
    @autoreleasepool {       
        // 定义之战 ptr 指向 sum函数
        int (*ptr)(int, int) = sum;
        // 通过 函数指针 ptr 调用 sum函数
        int res = ptr(10, 20);
        printf("%d\n", res);
    }
    return 0;
}
```



## 二, C++ lambda表达式

### 概念

- **C++**中的 **lambda表达式** 类似 ObjC中的block，其本质上就是**函数**.
- 完整结构 : **[capture list][paramas list\]  mutable exception -> return type{function body}**
  - **capture list** ：捕获外部变量列表
  - **params list** : 形参列表，不能使用默认参数, 不能省略参数名
  - **mutable** : 用来说明是否可以修改捕获的变量
  - **exception** : 异常设定
  - **return type** : 返回值类型
  - **function body** : 函数体
- 有时可以省略部分结构 : 
  - **[capture list]\(params list) -> return type{function body}**
  - **[capture list]\(params list) {function body}**
  - **[capture list]\{function body}**

### 代码举例 

```cpp
int main(int argc, const char * argv[]) {
    // 定义 lambda表达式
    int (*ptr1)(int, int) = [](int a, int b) -> int{
        return a + b;
    };
    cout << ptr1(10, 20) << endl;
    return 0;
}
```

- 而在 C++11以后，引入了**自动类型推断**
- 所以我们可以省略上边复杂的类型定义， 直接写 **auto**
- 自动类型推断是编译器来识别推断类型
- 在编译后形成的汇编代码中，和主动写的类型没有任何区别
- 不会影响程序指向效率

```cpp
int main(int argc, const char * argv[]) {
    // 定义 lambda表达式
    int (*ptr1)(int, int) = [](int a, int b) -> int{
        return a + b;
    };
    cout << ptr1(10, 20) << endl;
    return 0;
    
    // 定义 lambda表达式
    // C++11 加入自动类型推断
    // 所以我们可以省略上边复杂的 类型，直接写auto
    // 自动类型推断是编译器来识别推断。
    // 在编译后形成的代码跟制定类型没有区别
    // 不会影响程序运行效率
    auto ptr2 = [](int a, int b) -> int{
        return a + b;
    };
    cout << ptr2(10, 20) << endl;
}
```



### 本质

lambda表达式在编译时，编译器会将**lambda表达式生成函数**， 所以 lambda表达式的本质其实就是**函数**, 而lambda表达式的调用，其实就是**函数调用**。

我们用汇编代码来证明这个问题。

- 上述代码在运行时转为汇编以后，可以看到第一张图中，是将 main.cpp 文件的第14行转为了一下的汇编，而第14行正是lambda表达式。
- 我们一步步跟进去汇编代码，到了第二张图函数的实现，可以看到 确实是**加法**操作
  - 一步步的使用寄存器赋值，赋值后进行加法操作，再将结果返回

![截屏2020-07-05下午10.17.00](https://tva1.sinaimg.cn/large/007S8ZIlly1ggggje2tcvj31ce0fyaen.jpg)

![截屏2020-07-05下午10.17.56](https://tva1.sinaimg.cn/large/007S8ZIlly1ggggjjgmaaj31fw07cwgu.jpg)



### 作为参数

- 假设我们有 加减乘除 四个方法，分别是传入两个数字，进行加减乘除操作
- 还有个 exec方法，用来传入两个参数，并传入函数名，进行对应的加减乘除操作

#### 不使用 lambda表达式

代码如下 : 

```cpp
int add(int v1, int v2){
    return v1 + v2;
}

int subtrace(int v1, int v2){
    return v1 - v2;
}

int multiply(int v1, int v2){
    return v1 * v2;
}

int divide(int v1, int v2){
    return v1 / v2;
}

int exec(int v1, int v2, int(*func)(int, int)){
    return func(v1, v2);
}

int main(int argc, const char * argv[]) {

    cout << exec(20, 10, add) << endl;
    cout << exec(20, 10, subtrace) << endl;
    cout << exec(20, 10, multiply) << endl;
    cout << exec(20, 10, divide) << endl;
    
    return 0;
}
```



#### 使用 lambda表达式

代码如下 : 

```cpp
int main(int argc, const char * argv[]) {

    cout << exec(20, 10, [](int a, int b){ return a + b; }) << endl;
    cout << exec(20, 10, [](int a, int b){ return a - b; }) << endl;
    cout << exec(20, 10, [](int a, int b){ return a * b; }) << endl;
    cout << exec(20, 10, [](int a, int b){ return a / b; }) << endl;
    
    return 0;
}
```

- 上述代码执行和不使用 第一种写法 效果一样
- 以上就是作为参数使用的例子



### 变量捕获capture

#### 概念

- 中括号 [] 里边放的是什么呢？貌似我们上边一只没有用到
- []里放入的是用来做变量捕获的。 
- 我们在 **labmda表达式**中使用**外部局部变量**时，默认是不可以访问的
- 需要将变量名添加到 [] 中， 才可以访问。



#### 举例说明

![截屏2020-07-05下午10.51.02](https://tva1.sinaimg.cn/large/007S8ZIlly1ggghhvxdxrj31fy0a475w.jpg)

- 我们在 func 中，访问局部变量 a , 是不可以直接访问的。
- 如果需要访问，需要将变量名放到 [] 中，代码如下

```cpp
int main(int argc, const char * argv[]) {

    int a = 10;
    int b = 20;
    auto func = [a, b]{
        cout << a << endl;
        cout << b << endl;
    };
    func();
    return 0;
}
```

- 上述代码中， 我们可以直接访问变量a 和 变量 b



#### 值捕获？地址捕获？

```cpp
int main(int argc, const char * argv[]) {

    int a = 10;
    int b = 20;
    auto func = [a, b]{
        cout << a << endl;
        cout << b << endl;
    };
  	
  	a = 30;
  	b = 40;
  
    func();
    return 0;
}
```

- 我们可以访问 a, b变量以后，如果我们后续将 a， b的值改了，再调用 func() 时，访问到的是10，20还是 30，40呢？
  - 答案是10, 20
- why ?  看起来，我明明将用到的 a 和 b 的值改掉了，为什么访问还是原来的值呢？
  - 因为这里的变量捕获是**值捕获** ，也就是说func直接将 10，20 捕获了，后边修改 a，b的值跟func内部捕获的值完全没有关系。
- 如何实现，在 a，b修改以后，调用 func() 时，访问新的 a，b的值呢？
  - 答案是 **地址捕获**，如何办到地址捕获呢？ (类似在用的非常多的 **swap()**函数中的**地址引用**)
    - 很简单，我们在 [] 中捕获变量时，直接对a取地址，传进去，也就是将&a放大 [] 中
    - 地址捕获以后，我们就可以
      - 在 func() 函数调用时，根据 a 的地址，找到 a 最新的值
      - 并且，可以根据 a 的地址给 a 赋值



### mutable

- 在上边小段的学习中，我们知道值捕获时不可以修改引用的变量的值的，因为我们相当于单单引用了值，也就是常量
- 还有另外一种做法，来实现 在 func 内部修改 a 的值
  - 使用 mutable 关键字
- 首先明确一点， 使用 mutable关键字后， 变量仍然是 **值捕获**
- 所以，在 func 内部不可能修改外部 a 的值
- 但是可以修改 func() 内部变量 a 的值
- **mutable**关键字做法类似于
  - 在 func() 函数体内部，初始化了一个变量，初始值为 a
  - 所以在 func()函数题内部，当然可以修改 其内部的 a
  - 但是无法对外部的 a 产生影响。

```java
int main(int argc, const char * argv[]) {
    int a = 10;
    auto func = [a]() mutable{
        a = 20;
        cout << "func -> " << a << endl;
    };
    func();
    cout << a << endl;
    return 0;
}
```

- 所以上述代码的打印结果为
  - **func -> 20**
  - **10***
  - **Program ended with exit code: 0**
- 其内部的a被修改了，但是外部的值仍然是原来的值。



## 三,ObjC block

### 从C++看block本质

- 首先来看一个最简单的block, 没有返回值，没有参数

  ```objc
  int a = 10;
  void (^block)(void) = ^{
     NSLog(@"a is %d", a);
  };
  block();
  ```

- 我们编写的 OC代码，底层实现其实都是 C/C++代码

- 所以要理解 block 的本质， 我们将下边代码转换为 C++ 代码

  ```
  xcrun -sdk iphoneos clang -arch arm64 -rewrite-objc main.m -o main.cpp
  ```

- 转为C++代码以后, 我们创建和调用 block 代码如下 : 

  ```cpp
  // block的赋值 
  void (*block)(void) = &__main_block_impl_0(__main_block_func_0, &__main_block_desc_0_DATA, a));
  // block的调用
  block->FuncPtr();
  ```

  ```cpp
  // block结构体
  struct __main_block_impl_0 {
    struct __block_impl impl;					// 实现
    struct __main_block_desc_0* Desc; // 描述
    int a;														// 捕获的变量
  };
  ```

  ```cpp
  // block 的实现结构体
  struct __block_impl {
    void *isa;
    int Flags;
    int Reserved;
    void *FuncPtr;	// FuncPtr指向函数的具体实现
  };
  ```

  ```java
  // block 的描述信息
  static struct __main_block_desc_0 {
    size_t reserved;
    size_t Block_size;
  }
  ```

  ```cpp
  // __block_impl 的 funcPtr指向的函数具体实现
  // 就是，我们编写的 ObjC 的 打印 "a is %d", a
  static void __main_block_func_0(struct __main_block_impl_0 *__cself) {
    	int a = __cself->a; // bound by copy
  		NSLog(***, a);
  }
  ```

- 经过转为 C++ 代码，分析后发现，我们在 ObjC 中编写的 block 代码，在 C++中转为了**结构体**

- 这个结构体中，包含 block 函数的具体实现，block 的size等等

- 并且在 block结构体中，有我们访问的**外部局部变量 int a**



### block 的变量捕获 capture

#### 举例分析

- 上边的分析中，我们得知 block 访问的外部局部变量，被**捕获**到了block结构体内部

- 为什么要捕获这个变量呢？ 

- 举个🌰，查看下边代码 : 

  ```objc
  int main(int argc, const char * argv[]) {
      @autoreleasepool {
          void (^block)(void) = nil;
          {
              int a = 10;
              block = ^{
                  NSLog(@"a is %d", a);
              };
          }
          block();
      }
      return 0;
  }
  ```

- int a 是局部变量, 在花括号结束后， a就释放了

- 既然它释放了，我们在花括号外调用block() 如何访问 a 呢

- 这就用到了**变量捕获**, 要**保证我们在 block 函数体中能访问需要的局部变量，所以需要变量捕获。**



#### C++分析

- 从上述 C++ 代码可以看出，在构建 block 的 结构体时，将 变量 a 传入了 结构体的**构造函数**， 使用**C++中初始化列表**给结构体中的 a 赋值

- 在**__main_block_func_0** 中，访问 a 时。 直接取出来 **int a = __cself->a;** ,再进行访问

  

#### 值引用？地址引用？

##### 值引用

先看如下代码。

```c
 int a = 10;
 void (^block)(void)  = ^{
     NSLog(@"a is %d", a);
 };
 a = 20;
 block();
```

- 问题： 在 block 中 输出的 a 是10 还是 20 ？
- 答案是 **10**
- 为什么是10呢？ 从上述 C++ 代码分析可以看出来， 我们是直接拿 a 的值直接传入构造函数，为结构体中 a 赋值
- 所以这里是**值引用**， 相当于这里直接把 10 捕获到结构体中， 再与 变量 a 没有一点关系。
- 这也是为什么，在block中不能给 a 赋值的原因，block中能访问的 a 相当于是常量 10



##### 地址引用

那么，如何能实现, 外部 a 变化以后，block内部的 a 也发生变化？以及，如何才能在 block 中修改 a 的值？

- 答案就是使用，**引用传递**
- 通过前边 C++的学习，C++的lambda表达式非常方便改为引用传递，直接在捕获列表中 把 a 变为 &a 即可
- 而在 ObjC中不可以显式的设置捕获列表。OC为我们提供了 **__block** 关键字
  - 使用 __block 修饰的变量，可以在 block函数体内部赋值，并且外部修改后，也可以值同步
- 我们猜想，使用 __block修饰后的变量，从**值引用遍历了地址引用**
- 接下来，我们通过C++代码来验证我们的猜想



##### 从C++代码验证__block关键字

- 同样，我们把 main.m 转化为 main.cpp
- 转化为 cpp后，发现代码变得特别复杂，我们这里着重看重点代码，没必要去分析晦涩难懂的大量 C++代码

```cpp
// block的结构体
struct __main_block_impl_0 {
  struct __block_impl impl;
  struct __main_block_desc_0* Desc;
  // 封装了变量a地址的结构体
  __Block_byref_a_0 *a; // by ref
};
```

```objc
// 变量a 在 __block修饰后，变为结构体，并且结构体中地址引用变量 a
__Block_byref_a_0 a = {(void*)0,(__Block_byref_a_0 *)&a, 0, sizeof(__Block_byref_a_0), 10};

block = &__main_block_impl_0(__main_block_func_0, &__main_block_desc_0_DATA, (__Block_byref_a_0 *)&a, 570425344));

(a.__forwarding->a) = 20;

block->FuncPtr)();
```

```cpp
struct __Block_byref_a_0 {
  void *__isa;
__Block_byref_a_0 *__forwarding;
 int __flags;
 int __size;
 int a;
};
```

```cpp
static void __main_block_func_0(struct __main_block_impl_0 *__cself) {
  		__Block_byref_a_0 *a = __cself->a; // bound by ref
  		(a->__forwarding->a) = 30;
}
```

- 重点代码是以上三段主要代码
- 可以看出，使用 __block修饰的变量a, 在定义时，就被封装成了一个 结构体

  - **__Block_byref_a_0**
    - 此结构体中，isa地址是 变量 a 的地址
    - 有一个指向自己的 **__forwarding**指针指向 
    - 还有 变量 a 的值
- 在 构造 **__main_block_impl_0**  时，传入的是 **__Block_byref_a_0** 的地址值
- 当 访问 a  时，直接取到的 __Block_byref_a_0结构体 的 (a.__forwarding->a) = 20; 地址赋值
- 在取值时，仍然是 通过  __Block_byref_a_0结构体的 __forwwarding指针，再找到 a 的地址来修改的
- 总结来说，就是  用 **__block** 修饰后，变量的**值引用 变为 地址引用**
  - 所以可以通过地址来访问变量 a 的更改后的值
  - 也可以通过地址来修改外部变量 a 的值

- 以上是对 **局部auto变量的总结**
- 下边我们看另外一种情况， static变量

##### static变量

```objc
static int a = 10;
void (^block)(void)  = ^{
    NSLog(@"a is %d", a);
};
a = 20;
block();
```

仍然使用命令，将以上代码转化为 C++代码，如下 : 

```cpp
struct __main_block_impl_0 {
  struct __block_impl impl;
  struct __main_block_desc_0* Desc;
  int *a;
};

static void __main_block_func_0(struct __main_block_impl_0 *__cself) {
            int *a = __cself->a; // bound by copy
            NSLog(XXX  (*a));
}

int main(int argc, const char * argv[]) {
    /* @autoreleasepool */ { __AtAutoreleasePool __autoreleasepool;

        static int a = 10;
        // 注意这里是，地址引用
        block = &__main_block_impl_0(__main_block_func_0, &__main_block_desc_0_DATA, &a));
        a = 20;
        (block)->FuncPtr;
    }
    return 0;
}
```

- 可以看出来，static变量，在block中的引用是 **地址引用**
- 所以可以在block中修改变量的值。
- 为什么static变量是**地址引用**呢？ 
  - 猜想是因为，static变量在内存中只有一份，不会被释放，不会存在，block中访问时，变量已被释放的风险
  - 所以可以直接引用地址



##### 全局变量

- 全局变量不会进行变量捕获
- 在 block 函数体中，可以直接访问全局变量

```objc
int a = 10;
int main(int argc, const char * argv[]) {
    @autoreleasepool {
        
        void (^block)(void)  = ^{
            NSLog(@"a is %d", a);
        };
        a = 20;
        block();
    }
    return 0;
}
```

```cpp
int a = 10;

struct __main_block_impl_0 {
  struct __block_impl impl;
  struct __main_block_desc_0* Desc;
};
static void __main_block_func_0(struct __main_block_impl_0 *__cself) {
    NSLog(a);
}

int main(int argc, const char * argv[]) {
    /* @autoreleasepool */ { __AtAutoreleasePool __autoreleasepool;

        block = &__main_block_impl_0(__main_block_func_0, &__main_block_desc_0_DATA));
        a = 20;
        block->FuncPtr;

    }
    return 0;
}
```



##### 变量捕获总结 : 

- 局部变量会被捕获.
  - auto(默认) 类型的局部变量是值捕获
  - static(静态) 类型的局部变量是地址捕获
- 全局变量不会被捕获
  - 全局变量在 block 函数体中，可以直接访问，不会被捕获



##### 进阶 - 另一种变量

- 下边代码中， Person是一个 ObjC类，请问在  test 函数中的block，是否会捕获 self ?

```objc
void (^block)(void);

- (void)test{
    block = ^{
        NSLog(@"%p", &self);
    };
}

- (instancetype)init{
    self = [super init];
    if (self) {
        [self test];
        block();
    }
    return self;
}
```

- 根据上边的经验，我们知道，如果变量是 局部变量，就会被捕获。
- 所以上边的问题，转化成了，test 函数中的 self 是局部变量还是全局变量 ? 
- 接下来，我们就把 Person.m转换成 .cpp文件，来窥探是否被捕获

转化为 .cpp后，主要代码如下 : 

```cpp
// block 的定义
void (*block)(void);

// block 结构体
struct __Person__test_block_impl_0 {
  struct __block_impl impl;
  struct __Person__test_block_desc_0* Desc;
  Person *self;
};

// block实现函数
static void __Person__test_block_func_0(struct __Person__test_block_impl_0 *__cself) {
    Person *self = __cself->self; // bound by copy
    NSLog(&self);
}

// test函数的实现
static void _I_Person_test(Person * self, SEL _cmd) {
    block = (&__Person__test_block_impl_0(__Person__test_block_func_0, &__Person__test_block_desc_0_DATA, self, 570425344));
}
```

- 根据上面代码，我们发现：
- self 被捕获到 block结构体中了
- 而且是**值捕获**，说明 self 是一个局部变量，而非全局变量
- 从test函数的实现 **_I_Person_test**可以看出， test函数有两个**隐式参数** 
  - self,   这就是为什么我们可以在 对象方法，函数体中，直接访问 self 的原因
  - _cmd， 函数名.



### block的类型

- block 有三种类型，可以通过调用 class 和 isa指针查看具体类型，最终都继承自 NSBlock 类型，而 NSBlock 继承自 NSObject
  - __NSGlobalBlock__ （ _NSConcreteGlobalBlock ）
  - __NSStackBlock__ （ _NSConcreteStackBlock ）
  - __NSMallocBlock__ （ _NSConcreteMallocBlock ）

![截屏2020-07-07上午12.13.02](https://tva1.sinaimg.cn/large/007S8ZIlly1gghphr2viaj30uy0gyq7l.jpg)



- 为了能更好的理解，block的三种类型，我们最好关闭 **ARC**， 因为ARC环境下，编译器会帮我们做很多事情，
  - 比如在指针指向时，会自动把 block 从 栈区拷贝到堆区。 
  - 不利于我们学习 block 的三种类型



- GlobalBlock 是没有访问外部auto局部变量的block	
  - 存放在 程序的**数据区域**，也就是代码区
- StackBlock 访问了局部变量的block
  - 存放在 **栈区**
- MallocBlock 访问了局部变量的block，调用了copy，就会被复制到堆区，成为 MallocBlock
  - 存放在 **堆区**

![截屏2020-07-07上午12.19.57](https://tva1.sinaimg.cn/large/007S8ZIlly1gghpnhi2m2j30vc0g444j.jpg)



###### GlobalBlock

```objc
void (^block)(void) = ^{
    NSLog(@"GlobalBlock");
};
block();
NSLog(@"%@", [block class]);
```

- 像这种没有访问 auto变量的 block，其类型为 **GlobalBlock**



###### StackBlock

```objc
int a = 10;
void (^block)(void) = ^{
    NSLog(@"a is %d", a);
};
NSLog(@"%@", [block class]);
block();
```

- 像这种，访问了auto局部变量的block， 其类型为 **StackBlock**
- **StackBlock**存放在栈区，而存放在栈区的数据，是有编译器决定其什么时候释放的，程序员无法更改
- 这样就很有可能，当我们访问 **StackBlock**时，block内部的变量已经被释放，无法访问

```objc
void (^block)(void);

void test(){
    int a = 10;
    block = ^{
        NSLog(@"a is %d", a);
    };
    NSLog(@"%@", [block class]);
}

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        
        test();
        block();
    }
    return 0;
}
```

- 比如，上边代码，block虽然声明为全局， 但是block是在**栈区**， 其内部需要的变量比如 a 也在栈区。

- 当test函数，花括号结束后， 栈的内存空间已经销毁

- 所以执行block时，出现混乱

  ```shell
  2020-07-07 00:29:21.841482+0800 测试[12408:4050060] __NSStackBlock__
  2020-07-07 00:29:21.842224+0800 测试[12408:4050060] a is -272632552
  ```



所以，如何避免block在栈区，访问变量可能会被随时销毁，程序员无法控制的问题呢？

答案是 ; 将 **StackBlock** 拷贝到 **堆区**。而堆区的内存是程序员可以控制其生命周期的。



###### MallocBlock

- 上边我们总结过，StackBlock调用 copy ， 会从**栈区**拷贝到**堆区**， 成为**MallocBlock**

  ```objc
  void (^block)(void);
  
  void test(){
      int a = 10;
      block = [^{
          NSLog(@"a is %d", a);
      } copy];
      NSLog(@"%@", [block class]);
      [block release];
  }
  
  int main(int argc, const char * argv[]) {
      @autoreleasepool {
          
          test();
          block();
      }
      return 0;
  }
  ```

  ```shell
  2020-07-07 00:40:36.241385+0800 测试[12695:4058169] __NSMallocBlock__
  2020-07-07 00:40:36.242059+0800 测试[12695:4058169] a is 10
  ```

- 可以看到，block调用copy以后，StackBlock果然变为 MallocBlock,  并且其中局部变量可以访问。



- 在ARC环境下，编译器会根据情况自动将栈上的block复制到堆上，比如以下情况
  - block作为函数返回值时
  - 将block赋值给__strong指针时
  - block作为Cocoa API中方法名含有usingBlock的方法参数时
  - block作为GCD API的方法参数时

- MRC下block属性的建议写法
  ```objc
  @property (copy, nonatomic) void (^block)(void);
  ```

- ARC下block属性的建议写法

  ```objc
  - @property (strong, nonatomic) void (^block)(void);
  - @property (copy, nonatomic) void (^block)(void);
  ```

  

### Block中访问 对象类型的 auto变量

- 当block内部访问了对象类型的auto变量时

  - 如果block是在栈上，将不会对auto变量产生强引用

  

- 如果block被拷贝到堆上

  - 会调用block内部的copy函数
  - copy函数内部会调用_Block_object_assign函数
  - _Block_object_assign函数会根据auto变量的修饰符（__strong、__weak、__unsafe_unretained）做出相应的操作，形成强引用（retain）或者弱引用

- 如果block从堆上移除
  - 会调用block内部的dispose函数
  - dispose函数内部会调用_Block_object_dispose函数
  - _Block_object_dispose函数会自动释放引用的auto变量（release）



### __block的内存管理

- 当block在栈上时，并不会对__block变量产生强引用
- 当block被copy到堆时
  - 会调用block内部的copy函数
  - copy函数内部会调用_Block_object_assign函数_
  - Block_object_assign函数会对__block变量形成强引用（retain）

- 当block从堆中移除时
  - 会调用block内部的dispose函数
  - dispose函数内部会调用_Block_object_dispose函数
  - _Block_object_dispose函数会自动释放引用的__block变量（release）



### 对象类型的auto变量、__block变量

- 当block在栈上时，对它们都不会产生强引用
- 当block拷贝到堆上时，都会通过copy函数来处理它们
  - __block变量（假设变量名叫做a）
  - Block_object_assign((void*)&dst->a, (void*)src->a, 8/*BLOCK_FIELD_IS_BYREF*/);
- 对象类型的auto变量（假设变量名叫做p）
  - _Block_object_assign((void*)&dst->p, (void*)src->p, 3/*BLOCK_FIELD_IS_OBJECT*/)
- 当block从堆上移除时，都会通过dispose函数来释放它们
  - __block变量（假设变量名叫做a）
    - Block_object_dispose((void*)src->a, 8/*BLOCK_FIELD_IS_BYREF*/);
  - 对象类型的auto变量（假设变量名叫做p）
  - _Block_object_dispose((void*)src->p, 3/*BLOCK_FIELD_IS_OBJECT*/);



### 被__block修饰的对象类型

- 当__block变量在栈上时，不会对指向的对象产生强引用
- 当__block变量被copy到堆时
  - 会调用__block变量内部的copy函数
  - copy函数内部会调用_Block_object_assign函数
  - _Block_object_assign函数会根据所指向对象的修饰符（__strong、__weak、__unsafe_unretained）做出相应的操作，形成强引用（retain）或者弱引用（注意：这里仅限于ARC时会retain，MRC时不会retain）

- 如果__block变量从堆上移除
  - 会调用__block变量内部的dispose函数
  - dispose函数内部会调用_Block_object_dispose函数
  - _Block_object_dispose函数会自动释放指向的对象（release）



### 循环引用问题

#### 解决循环引用问题 - ARC

- 用__weak、__unsafe_unretained解决

  ![截屏2020-07-08上午12.10.36](https://tva1.sinaimg.cn/large/007S8ZIlly1ggiv016t1hj30ka09uwh9.jpg)

- 用__block解决（必须要调用block）

  ![截屏2020-07-08上午12.11.19](https://tva1.sinaimg.cn/large/007S8ZIlly1ggiv0qu8f5j31ao07idm4.jpg)



#### 解决循环引用问题 - MRC

- 用__unsafe_unretained解决

  ![截屏2020-07-08上午12.12.01](https://tva1.sinaimg.cn/large/007S8ZIlly1ggiv1gjdbuj30k004ugmv.jpg)

- 用__block解决

  ![截屏2020-07-08上午12.13.06](https://tva1.sinaimg.cn/large/007S8ZIlly1ggiv2lw0cjj30eo04mq3x.jpg)

