Module 12 consists of two task

**Task 1**

Task 1 is completed in a single Task1 class, that launches two additional threads
using java.util.concurrent.ScheduledExecutorService
by scheduling at fixed rate two threads:
First thread prints how many ms the program works for, prints every second.
Second thread prints "Пройшло 5 секунд" each 5 seconds.

**Task 2**

Task2 is completed with two separate classes in module12/task2 package.
The classes are: ConcurrentFizzBuzz and Task2.

ConcurrentFizzBuzz prints is instantiated with single
parameter constructor - int n
Then by calling printNumbers() of the instance, the numbers will be printed
but with "fizz" instead of number % 3 == 0, 
"buzz" instead of number % 5 == 0,
"fizzbuzz" instead of number % 15 == 0.
Printing is performed concurrently:

Thread A checks if number is fizz and adds 'fizz' to printing queue instead of this number

Thread B does the same, but for buzz numbers

Thread C does the same, but for fizzbuzz numbers

Thread D waits for results from A, B, C,
and if is they didn't put anything in the queue then the normal number will be printed

Task2 class is just a driver with main() method for program starting