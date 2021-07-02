# COMP1110/6710 2021 S1 Final Exam

This repo contains an IntelliJ project for the 2021 final exam.

You have **3 hours and 30 mins** to complete the exam.

This exam is open-book, meaning you may use books, notes, and other such *pre-existing* information as you complete the exam.

You **must not** communicate with any person other than the examiners at any time during the exam.

Chat, text, email and all other such forms of communications **must** be turned off before the exam and must remain off for the **duration of the exam**.
**The penalties for cheating in an exam at ANU are severe.**

Important notes:
* There are six (6) questions, each worth different marks.
* The total marks for the exam are 85 (10 + 5 + 25 + 20 + 5 + 20)
* The exam will be **entirely auto-graded**, so it is important that your code passes the tests and that you commit and push your work.
* The CI for the multiple-choice question will *not* tell you whether your answer is correct; it will only tell you if your answer is *formatted correctly*.
* We have provided two practice questions, **P1** and **P2**, to help you prepare your environment for the exam.
  You do not need to complete them.
  They are worth **zero** marks.

---




## Question 1  [10 Marks] Imperative Programming in Java
 
### Q1.1  [5 Marks]  Basic Imperative Programming

Using the incomplete template for [Q1FindAllStarting.java](src/comp1110/exam/Q1FindAllStarting.java),
complete the unimplemented method `findAllStarting()`.
 
Use the tests provided to test your solution, and then commit and push your
changes to GitLab.

### Q1.2  [5 Marks]  Recursion (harder)

Using the incomplete template for [Q1Alternating.java](src/comp1110/exam/Q1Alternating.java), complete the unimplemented method `alternating()`.
 
Use the tests provided to test your solution, and then commit and push your changes to GitLab.

---

## Q2  [5 Marks]  File I/O, Imperative Programming

Using the incomplete template for [Q2AsciiSum.java](src/comp1110/exam/Q2AsciiSum.java),
complete the unimplemented method `asciiSum()`.
 
Use the tests provided to test your solution, and then commit and push your
changes to GitLab.

---

## Q3  [25 Marks]

### Q3.1 [20 Marks] Software Implementation

Using the incomplete template for [Q3Recipes.java](src/comp1110/exam/Q3Recipes.java),
complete *all unimplemented methods*.
 
You must complete your solution as **a single file, [Q3Recipes.java](src/comp1110/exam/Q3Recipes.java)**.
You are encouraged to create additional classes as required to solve the problem;
any additional classes must be implemented as **nested classes** within the
`Q3Recipes` class.
 
Use the tests provided to test your solution, and then commit and push your
changes to GitLab.

### Q3.2  [5 Marks]  Software Testing

Using the incomplete template for [Q3GetMaxRecipesInCategoryTest.java](src/comp1110/exam/Q3GetMaxRecipesInCategoryTest.java),
write one or more unit tests for the `getMaxRecipesInCategory()` method. You must complete
your solution as a single file, `Q3GetMaxRecipesInCategoryTest.java`. When writing the
tests, you may assume that the provided methods in the `Q3Recipes` class are
correctly implemented. The specification of the `getMaxRecipesInCategory()` method
is provided in the Javadoc comment immediately above the method.

This question is auto-graded; your tests will be run against multiple
implementations of the `getMaxRecipesInCategory()` method,
one of which is correct and some incorrect. Your test must be able to pass
the correct implementation.   If you cannot pass the correct implementation
you will get zero (regardless of how many incorrect implementations you fail.
If you pass the correct implementation, you will get one mark, and then one
additional mark for each incorrect implementation that you fail.

Once you have completed your tests, commit and push your changes to GitLab.

---

## Q4  [20 Marks]  Data Structures

Using the incomplete template for [Q4Replicator.java](src/comp1110/exam/Q4Replicator.java), complete each of the unimplemented methods. 

Your solution must implement the data structure from first principles (as was done in lectures), avoiding use of Java's collection classes such as `java.util.List`.
*Solutions that ignore this requirement will be penalized accordingly.*

Use the tests provided to test your solution, and then commit and push your changes to GitLab.

---

## Q5  [5 Marks]  Hashing and Equality

Using the incomplete template for [Q5Monarch.java](src/comp1110/exam/Q5Monarch.java), complete the unimplemented methods `hash()` and `equals()`.
You must implement `hash()` **without using Java's built-in `hashCode()` method**.

Use the tests provided to test your solution, and then commit and push your changes to GitLab.

---

## Q6 [20 Marks]  Multiple Choice

For each of the following multiple-choice questions, identify the choice that
provides the best answer.  Record your choices in the file [q-6.csv](q-6.csv),
one line per question.  
 
For example, to answer "a" to question Q6.1, "d" to question Q6.2, "a" to
question Q6.4, and leave question Q6.3 unanswered, you would record the following
in `q-6.csv`:
 ````
1,a
2,d
4,a
````

Please note that you can only provide **one** answer to any multiple choice 
question.   Please chose the answer that you think is the best answer.   If
you are unsure, leave it out, as in the example above where question 3 is left
out.

Each question that is *correctly* answered **gains you 5 marks**, each question
answered *incorrectly* **loses you 1 mark**, a question left *unanswered*
**neither loses nor gains marks**. The final mark for the question is calculated
by bounding the sum of marks between 0 and 20. For example, if you answered all
questions correctly in the exam you would gain 20. If you answer 1 correctly
and 2 incorrectly you would gain 3/20.

The exam CI will check whether your answer is *correctly formatted*. Note that 
the CI won't tell you whether your answer is *correct*.   After you finish your
questions you should **commit** your `q-6.csv` file and **push** it to the
server.   You should check the CI for any error messages relating to `Q6` and
fix them if they exist.  

### Q6.1  [5 Marks]  Threads

The code below is for a program in which workers each complete work items from a shared work queue.
There are two workers, each of which must complete ten work items, so the program should print the numbers [0..19] (not necessarily in that order).
However, the code has a race condition, which means that it does not always print the correct output.

````
public class Workers implements Runnable {
    static class State {
        static State instance; // a
        int workItem; // b

        public static State load() {
            if (instance == null) {
                instance = new State();
            }
            return instance;
        }

        public synchronized int getWorkItem() {
            return workItem++;
        }
    }

    public void run() {
        for (int i = 0; i < 10; i++) { // c
            State s = State.load(); // d
            int w = s.getWorkItem(); // e
            System.out.println(w);
        }
    }

    public static void main(String[] args) {
        Workers g = new Workers(); // f
        (new Thread(g)).start();
        (new Thread(g)).start();
    }
}
````

In the code above, a race condition on which field or variable may cause incorrect output?

 a) `State s`
  
 b) `State.instance`

 c) `int i`

 d) `State.workItem`

 e) `int w`

 f) `Workers g`

### Q6.2  [5 Marks]  JavaFX

Consider the following JavaFX application methods:
````
    static void squares(Pane parent, int level) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (level > 1) {
                    var p = new TilePane();
                    p.setPrefColumns(3);
                    squares(p, level-1);
                    parent.getChildren().add(p);
                } else {
                    var r = new Rectangle(15, 15);
                    r.setFill((i * 3 + j) % 2 == 1 ? Color.RED : Color.BLACK);
                    parent.getChildren().add(r);
                }
            }
        }
    }

    public void start(Stage stage) throws Exception {
        var root = new Group();
        var scene = new Scene(root, 405, 405);
        var p = new TilePane();
        p.setPrefColumns(3);
        squares(p, 3);
        root.getChildren().add(p);
        stage.setScene(scene);
        stage.show();
    }
````

How many nodes are in the scene graph of the application?

a) 730

b) 729

c) 9

d) 405

e) 821

f) 27

### Q6.3  [5 Marks]  Complexity

Consider the method `z()` below.
````
    static <T extends Comparable<T>> void z(List<T> l) {
        for (int i = 0; i < l.size(); i++) {
            T v = l.get(i);
            int j = i;
            int k;
            for (k = 1; k < l.size(); k = k * 2) {
                l.set(j, l.get(k));
                j = k;
            }
            l.set(j, v);
        }
    }
````

Which of the following statements is correct?

a) The time complexity of `z()` with `l` of size n is *O(2 n)*.

b) The time complexity of `z()` with `l` of size n is *O(n)*.

c) The time complexity of `z()` with `l` of size n is *O(n log n)*.

d) The time complexity of `z()` with `l` of size n is *O(n^2)*.

### Q6.4  [5 Marks]  Grammars

This question relates to sentences (statements) in a language defined by a simple EBNF grammar.
For reference, some symbols of EBNF are as follows:

````
= defines a production rule
, concatenation
| alternation / choice
[...] optional - zero or one
{...} optional - zero or more
(...) grouping
- exception
; terminates a production rule.
````

Given the following EBNF grammar:


````
vowel = 'a' | 'e' | 'i' | 'o' | 'u' ;
consonant = 'h' | 'k' | 'l' | 'm' | 'n' | 'p' | 'w' | 'ʻ' ;
syllable = consonant , vowel, [ vowel - 'a' ];
word = ( vowel | syllable ), { syllable } ;
````

Identify which of the following strings cannot be produced by the grammar.

1)
````
lauleʻa
````
    
b) 
````
hoʻoponopono
````

c)
````
kokua
````

d)
````
maikaʻi
````

e)
````
puʻuwai
````


*This is the end of the exam.   Instructions for the practice questions follow.*

---

## Practice Questions

These are instructions for what to do **before** the exam.

You should complete the two practice questions below and ensure that they work in
your environment.
Please check that:

1. the provided test for P1 runs correctly in IntelliJ;
2. you can successfully pass the test for P1;
3. you can fill in the answers for P2;
4. you can commit and push your work; and
5. the CI says that you have passed P1 and that P2 has been checked.

Note that the CI will not tell you whether the multiple-choice question P2 is
correct or incorrect, it will only tell you whether it was correctly formatted.

Remember, the questions below are **practice** questions which you should attempt *before* the exam.
The practice questions exist only to help you check that your environment is working correctly.
**The practice questions are not worth any marks.**

The first is an example of a programming question.

### P1 [0 Marks]  Hello World

Using the incomplete template for [P1HelloWorld.java](src/comp1110/exam/P1HelloWorld.java), complete the unimplemented `main` method.

Use the tests provided to test your solution, and then commit and push your
changes to GitLab.


### P2 [0 Marks] Sample Multiple Choice

For each of the following multiple-choice questions, identify the choice that provides the best answer.
Then, record your choice in the file [p-2.csv](p-2.csv),
one line per question.

For example, to answer "a" to practice question P2.1, "d" to P2.2, "a" to P2.4, and leave P2.3 blank, you would record the following
in `p-2.csv`:
 ````
1,a
2,d
4,a
````

These *practice* questions are worth **zero** marks.
In the actual exam, there will be four multiple-choice questions.
Each correct answer gains you 5 marks; each incorrect answer loses you 1 mark; a question left unanswered neither loses nor gains marks.
The final mark for the question in the exam is calculated by bounding the sum of marks between 0 and 20.
For example, if you answered all questions correctly in the exam, you would gain 20.
If you answer 1 correctly and 2 incorrectly, you would gain 3/20.

Once you have answered the questions, you should **commit** your `p-2.csv` file and **push** it.
You should **check the exam CI** to see whether it passed the formatting check.
If it did not, you should read the error messages and fix up any formatting problems with your answer.
*Note that the CI won't tell you whether your answers are correct, only whether they are correctly formatted.*

### P2.1  [0 Marks]  Sky

Which of the following colours best represents the colour of the sky?

a) blue

b) green

c) pink

d) purple

e) yellow

### P2.2  [0 Marks]  Maths

Which of the following numbers best represents the result of the
addition `2 + 2`?

a) 3

b) 2

c) 4

d) 5

e) 1

### P2.3  [0 Marks]  Animals

Which of the following animals is most closely associated with Australia?

a) tiger

b) elephant

c) kangaroo

d) lion

e) bear

### P2.4  [0 Marks]  Cars

Which of the following brands is best known as a car manufacturer?

a) Microsoft

b) Qantas

c) Toyota

d) Coca Cola

e) Facebook
