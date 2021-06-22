# Java Journeyman Badge
#### Brandon Fowler Membership Team
See this link for the requirments I completed an detailed below:
https://fhconfluence.churchofjesuschrist.org/display/Product/Core+Skills+-+Java+-+Journeyman

## Design

### Java Class Design
In Colors.java I stored strings that shouldn't be changed and
that I'd like to use in other classes. 

StubbornBilly.java in the misc package is and example of an immutable class.
With concurrency you often have to worry about data sharing 
between threads. If an object is immutable you know that you 
don't have worry about it.

In StubbornBilly.java I also override the hashCode, equals and toString methods. 

### Advanced Class Design
Interfaces are useful when you want to apply general functionalities to different
types of objects. If the object implements the interface, you can treat it the same
as any other object that implements the interface. Abtract classes are useful when 
you want to declare a function in the parent class but force child classes to implement
a certain method(s).

In the printColors package the ColorPrinter.java class is extended by
Wheel.java, Square.java dn RandomColor.java.

In the threads package DanceRunnable.java implements the runnable interface

StrayController is an interface which is implemented in RandomColor.java as an anonymous
class. in ClariceBrain.java there an nested class called ClariceSinging which implements
runnable. It's used to make a thread so that Clarice will sing a little song every once 
in a while.

### Object-Oriented Design Principles

[comment]: <> (Using a Java codebase you are working in, show examples &#40;or blatant violations&#41; of the following object-oriented principles &#40;SOLID&#41;:)

In fileBattes > FileBattleManager.java I split up a function that was going to move a file
and then return it's location into two functions. 
[comment]: <> (Single Responsibility Principle)

In cmisx-unit for queries that go to graphql we have a separate query object for each unique query.
This is better than having all the queries in the same class or something like that. Doing this, to 
add a new query we just have to make a new query class and then add a function to the graphql client.
Perhaps this could be better followed if the graphql client had just one function which took a generic query
and returned a generic object. However, if we did this then we'd have to find a way to map the json
in the response to an object at some point. 
[comment]: <> (Open/Closed Principle)

In printColors > I created an interface of ColorPrinter which the other classes I made implemented.
I then could have the print something and know they would print some sort of color display.
[comment]: <> (Liskov Substitution Principle)

In cmisx-member-updates-worker we create to jpa repository interfaces for each database instead of one.
If they where together there would be functions that a user would need that were available.
[comment]: <> (Interface Segregation Principle)

I couldn't find a very good example of this principle but in cmisx-member-updates-worker the same two interfaces
abstract the two query methods that are implemented by jpa under the hood. The force the user to use the
interface they actually need and nothing else. 
[comment]: <> (Dependency Inversion Principle)

## JVM Concepts

### Configuration

The JVM Memory Model describes how variables are stored during runtime. It's an abstraction over how they are
actually stored in the computer. In a thread or process, local variables are stored on the threads' stack. If
these variables are primitive types, the variable is actually stored in the stack. If the variable is an object,
then only a reference is stored on the stack and the actual object is stored in the heap. Static variables are
stored on the heap in the class definition. 
[comment]: <> (Describe the JVM Memory Model)

When you set up your JVM there are different options you can use to change the size of the heap and change 
parameters for garbage collection. For most things the defaults seem to be good and tend to be more stable.
First you should see if you can't optimize speed by writing the code in a better way.
[comment]: <> (Describe how one would tune the JVM perfomance characteristics)

You can use jconsole to see how an application uses resources and performs. There's also other tools that
integrate with JMX to help you monitor an application. 
[comment]: <> (Describe how you would use JMX to monitor the JVM)

### Threads
DanceRunnable.java implements the Runnable interface. In Threads.java
the function threadDance() calls to join() to wait for the threads to
finish. Also in threadDance() the two threads are from the same 
DanceRunnable instance and share the dataTrack ArrayBlockingQueue, the
RacerIdAssigner and the Lock. These are controlled by Lock to 
synchronize the data access. Sometimes code that writes to a file will 
not write things in the expected order. Also, sometimes it's just 
better to keep the code simple without using threads. 



### Concurrency
ArrayBlockingQueue which is part of java.util.concurrent is used in 
Threads.java in the makeDataTrack() function. The DanceRunnable.java 
uses ReentrantLock in its run() function. 

BeepBopCallable.java in package threads.beepBop uses an 
ReentrantReadWriteLock to control access to a common list and some 
other values.

It also implements callable.

genericFork > ForkManager.java uses the fork join framework. 


### Exceptions and Assertions
in trys > trys.java I made a try with two catch blocks for different exceptions. The finally clause prints out
a message afterwards. 
[comment]: <> (Develop code that handles multiple Exception types in a single catch block and implements finally)

Also in trys > trys.java I made a try with function that uses scanner which is auto closeable. 
[comment]: <> (Develop code that uses try-with-resources statements &#40;including using classes that implement the AutoCloseable interface&#41; - Java 7/8)

trys > ClariceSillyException.java is a custom exception that inherits just from the base class Exception.
[comment]: <> (Create custom exceptions)
 
in test > ExceptionTests.java I made a class that uses assertThrows to test to see if an exception was thrown. 
[comment]: <> (&#40;stretch&#41; Test invariants by using assertions)

## JDK Libraries

### Generics and Collections

genericFork > Fork.java is a generic class. 
[comment]: <> (Create a generic class)

On line 148 of genericFork > ForkManager.java I use the diamond to initialize an arraylist of strings.
[comment]: <> (Use the diamond for type inference  )

Collections that use raw or generic types are very useful as any types can be stored in them.
[comment]: <> (Analyze the interoperability of collections that use raw types and generic types)


[comment]: <> (Use wrapper classes, autoboxing and unboxing)

[comment]: <> (Use java.util.Comparator and java.lang.Comparable)

[comment]: <> (Sort and search arrays and lists)

[comment]: <> (&#40;stretch&#41; Create and use List, Set and Deque implementations)

[comment]: <> (&#40;stretch&#41; Create and use Map implementations)

### String Processing

[comment]: <> (Search, parse and build strings &#40;including Scanner, StringTokenizer, StringBuilder, String and Formatter&#41;)
In ClariceBrain.java checkForKeyWords() uses regex to parse input
looking for keywords.

[comment]: <> (Search, and replace strings by using regular expressions, using expression patterns for matching limited to: . &#40;dot&#41;, * &#40;star&#41;, + &#40;plus&#41;, ?, \d, \D, \s, \S,  \w, \W, \b, \B, [], &#40;&#41;.)

[comment]: <> (Format strings using the formatting parameters: %b, %c, %d, %f, and %s in format strings.)

### Java I/O Fundamentals
In Threads.java in the threads package threadPicture() uses DataStream input and output. 
BufferedReader, File and FileReader are used in printFile() in ClariceBrain.java.  
ObjectStreams in and out are used in all of the functions in ClauseHelper.java under
parsing. 

[comment]: <> (Use streams to read from and write to files by using classes in the java.io package including, BufferedWriter, FileWriter, and PrintWriter)

[comment]: <> (ObjectStreams and PrintWriter left.)

### Java File I/O (NIO.2)
In parsing > KeywordManager.java in the removeIndexFile() function I get a path to a 
file using Paths.get. In fileBattles > DefenderThread.java I operate on a directory 
with the Path class. 
[comment]: <> (Operate on file and directory paths with the Path class)

In parsing > KeywordManager.java in the removeIndexFile() function I delete a file with the 
Files class. 
[comment]: <> (Check, delete, copy, or move a file or directory with the Files class)

In fileBattles > FileBattleManager I use DirectoryStream to print out the file structure in the package.
In fileBattles > DefenderThread.java I use a FileVisitor which I implemented as Crawler.java.
[comment]: <> (Recursively access a directory tree using the DirectoryStream and FileVisitor interfaces)

In filebattes > FileBattleManager.java I changed the lastModified time of file with BasicFileAttributes.
I also got the owner of the file with PosixFileAttributes. I also added a custom file attribute.
[comment]: <> (&#40;stretch&#41; Read and change file and directory attributes, focusing on the BasicFileAttributes, DosFileAttributes, and PosixFileAttributes interfaces)

In fileBattles > Crawler.java I use PathMatcher when scanning a file tree.
[comment]: <> (&#40;stretch&#41; Find a file with the PathMatcher interface)

fileBattles > FileBattleManager.java uses a WatchService to print out the file structure whenever there is a change in the fileBattles 
package.
[comment]: <> (&#40;stretch&#41; Watch a directory for changes with the WatchService interface)


## External Libraries

### Spring

[comment]: <> (Write a spring application -- simple application &#40;simple xml configuration&#41;  -- Make a simple application with 3 components and two of the components use another component.  Wire them up in spring and show that each is used.)

[comment]: <> (Write a spring application -- simple application &#40;simple annotation configuration&#41;  -- Same as the simple XML app, but use Spring Annotations instead.)
### Testing

[comment]: <> (Demonstrate the use of JUnit, Hamcrest, and Mockito in Unit and Component Tests)

[comment]: <> (Write a custom Hamcrest Matcher)

### Logging

[comment]: <> (Write a small class that uses slf4j and logback to log a message to a console)

[comment]: <> (Describe the features of slf4j and logback that make them better than log4j.)

## Advanced Java Language Features

### Annotations
[comment]: <> (Explain or give an example of Annotation Inheritance)

[comment]: <> (Explain or give an example of Annotation target)

[comment]: <> (Explain or give an example of Retention policy)

### Localization

[comment]: <> (Read and set the locale by using the Locale object)

[comment]: <> (Build a resource bundle for each locale)

[comment]: <> (Call a resource bundle from an application)

[comment]: <> (Format dates, numbers, and currency values for localization with the NumberFormat and DateFormat classes &#40;including number format patterns&#41;)

[comment]: <> (Describe the advantages of localizing an application)

[comment]: <> (Define a locale using language and country codes)

## Aspect Oriented Programming (AOP)

### Methods and Parameters

[comment]: <> (Write an aspect to trace the method calls to a simple interface and implementation that does the following)

[comment]: <> (Trace the method calls)

[comment]: <> (Inspect or modify a method parameter)

[comment]: <> (Log an exception thrown by the method)

[comment]: <> (Write the above using a decorator pattern and &#40;stretch&#41; using a dynamic proxy.)