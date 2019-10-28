See the Assessment Guide for information on how to interpret this report.
ASSESSMENT SUMMARY Compilation:
PASSED API: PASSED
Spotbugs:
PASSED PMD: FAILED (1 warning)
Checkstyle: PASSED
Correctness: 21/33 tests passed
Memory: 8/8 tests passed
Timing: 20/20 tests passed
Aggregate score: 78.18% [Compilation: 5%, API: 5%, Spotbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]
ASSESSMENT DETAILS The following files were submitted:
----------------------------------
4.1K Oct 22 03:08 Percolation.java 2.4K Oct 22 03:08 PercolationStats.java ******************************************************************************** * COMPILING ******************************************************************************** % javac11 Percolation.java *----------------------------------------------------------- % javac11 PercolationStats.java *-----------------------------------------------------------
============================================================ Checking the APIs of your programs. *----------------------------------------------------------- Percolation: PercolationStats: ================================================================ ******************************************************************************** * CHECKING STYLE AND COMMON BUG PATTERNS ******************************************************************************** % spotbugs *.class *----------------------------------------------------------- ================================================================ % pmd . *-----------------------------------------------------------
Percolation.java:9: The private instance (or static) variable 'wquUF' can be made 'final';
it is initialized only in the declaration or constructor.
[ImmutableField] PMD ends with 1 warning. ================================================================ % checkstyle *.java *----------------------------------------------------------- % custom checkstyle checks for Percolation.java *----------------------------------------------------------- % custom checkstyle checks for PercolationStats.java *----------------------------------------------------------- ================================================================ ******************************************************************************** * TESTING CORRECTNESS ******************************************************************************** Testing correctness of Percolation *-----------------------------------------------------------
Running 18 total tests.
Tests 1 through 8 create a Percolation object using your code, then repeatedly open sites by calling open().
After each call to open(), it checks the return values of isOpen(), percolates(), numberOfOpenSites(), and isFull() in that order. Tests 11 through 14 create a Percolation object using your code, then repeatedly call the methods open(), isOpen(), isFull(), percolates(), and, numberOfOpenSites() in random order with probabilities p = (p1, p2, p3, p4, p5).
The tests stop immediately after the system percolates. Tests 16 through 18 test backwash.
Except as noted, a site is opened at most once.
Test 1: open predetermined list of sites using file inputs * filename = input6.txt * filename = input8.txt * filename = input8-no.txt * filename = input10-no.txt * filename = greeting57.txt * filename = heart25.txt
==> passed
Test 2: open random sites until just before system percolates * n = 3 * n = 5 * n = 10 * n = 10 * n = 20 * n = 20 * n = 50 * n = 50
==> passed
Test 3: open predetermined sites for n = 1 and n = 2 (corner case test) * filename = input1.txt * filename = input1-no.txt * filename = input2.txt * filename = input2-no.txt
==> passed
Test 4: check predetermined sites with long percolating path * filename = snake13.txt * filename = snake101.txt
==> passed
Test 5: open every site * filename = input5.txt
==> passed
Test 6: open random sites until just before system percolates, allowing open() to be called on a site more than once * n = 3 * n = 5 * n = 10 * n = 10 * n = 20   * n = 20 * n = 50 * n = 50
==> passed
Test 7: call methods with invalid arguments * n = 10, (row, col) = (-1, 5) * n = 10, (row, col) = (11, 5) * n = 10, (row, col) = (0, 5) * n = 10, (row, col) = (5, -1) * n = 10, (row, col) = (5, 11) * n = 10, (row, col) = (5, 0) * n = 10, (row, col) = (-2147483648, -2147483648) * n = 10, (row, col) = (2147483647, 2147483647)
==> passed
Test 8: call constructor with invalid argument * n = -10 * n = -1   * n = 0
==> passed
Test 9: create multiple Percolation objects at the same time (to make sure you didn't store data in static variables)
==> passed
Test 10: open predetermined list of sites using file inputs, but permute the order in which methods are called * filename = input8.txt; order = isFull(), isOpen(), percolates() * filename = input8.txt; order = isFull(), percolates(), isOpen() * filename = input8.txt; order = isOpen(), isFull(), percolates() * filename = input8.txt; order = isOpen(), percolates(), isFull() * filename = input8.txt;  order = percolates(), isOpen(), isFull() * filename = input8.txt; order = percolates(), isFull(), isOpen()
==> passed
Test 11: call open(), isOpen(), and numberOfOpenSites() in random order until system percolates * n = 3, trials = 40, p = (0.4, 0.4, 0.0, 0.0, 0.3) * n = 5, trials = 20, p = (0.4, 0.4, 0.0, 0.0, 0.3) * n = 7, trials = 10, p = (0.4, 0.4, 0.0, 0.0, 0.3) * n = 10, trials = 5, p = (0.4, 0.4, 0.0, 0.0, 0.3) * n = 20, trials = 2, p = (0.4, 0.4, 0.0, 0.0, 0.3) * n = 50, trials = 1, p = (0.4, 0.4, 0.0, 0.0, 0.3)
==> passed
Test 12: call open() and percolates() in random order until system percolates * n = 3, trials = 40, p = (0.5, 0.0, 0.0, 0.5, 0.0) * n = 5, trials = 20, p = (0.5, 0.0, 0.0, 0.5, 0.0) * n = 7, trials = 10, p = (0.5, 0.0, 0.0, 0.5, 0.0) * n = 10, trials = 5, p = (0.5, 0.0, 0.0, 0.5, 0.0) * n = 20, trials = 2, p = (0.5, 0.0, 0.0, 0.5, 0.0) * n = 50, trials = 1, p = (0.5, 0.0, 0.0, 0.5, 0.0)
==> passed
Test 13: call open() and isFull() in random order until system percolates * n = 3, trials = 40, p = (0.5, 0.0, 0.5, 0.0, 0.0) * n = 5, trials = 20, p = (0.5, 0.0, 0.5, 0.0, 0.0) * n = 7, trials = 10, p = (0.5, 0.0, 0.5, 0.0, 0.0) * n = 10, trials = 5, p = (0.5, 0.0, 0.5, 0.0, 0.0) * n = 20, trials = 2, p = (0.5, 0.0, 0.5, 0.0, 0.0) * n = 50, trials = 1, p = (0.5, 0.0, 0.5, 0.0, 0.0)
==> passed
Test 14: call all methods in random order until system percolates * n = 3, trials = 40, p = (0.2, 0.2, 0.2, 0.2, 0.2) * n = 5, trials = 20, p = (0.2, 0.2, 0.2, 0.2, 0.2) * n = 7, trials = 10, p = (0.2, 0.2, 0.2, 0.2, 0.2) * n = 10, trials = 5, p = (0.2, 0.2, 0.2, 0.2, 0.2) * n = 20, trials = 2, p = (0.2, 0.2, 0.2, 0.2, 0.2) * n = 50, trials = 1, p = (0.2, 0.2, 0.2, 0.2, 0.2)
==> passed
Test 15: call all methods in random order until almost all sites are open, but with inputs not prone to backwash * n = 3 * n = 5 * n = 7 * n = 10 * n = 20 * n = 50
==> passed


Test 16: check for backwash with predetermined sites
* filename = input20.txt - isFull() returns wrong value after 231 sites opened
  - student isFull(18, 1) = true - reference isFull(18, 1) = false
* filename = input10.txt - isFull() returns wrong value after 56 sites opened
  - student isFull(9, 1) = true - reference isFull(9, 1) = false
* filename = input50.txt - isFull() returns wrong value after 1412 sites opened
  - student isFull(22, 28) = true - reference isFull(22, 28) = false
* filename = jerry47.txt - isFull() returns wrong value after 1076 sites opened
 - student isFull(11, 47) = true
 - reference isFull(11, 47) = false
* filename = sedgewick60.txt - isFull() returns wrong value after 1577 sites opened
  - student isFull(21, 59) = true
  - reference isFull(21, 59) = false
* filename = wayne98.txt - isFull() returns wrong value after 3851 sites opened
  - student isFull(69, 9) = true
  - reference isFull(69, 9) = false
==> FAILED
Test 17: check for backwash with predetermined sites that have          multiple percolating paths
* filename = input3.txt - isFull() returns wrong value after 4 sites opened - student isFull(3, 1) = true - reference isFull(3, 1) = false
* filename = input4.txt - isFull() returns wrong value after 7 sites opened - student isFull(4, 4) = true - reference isFull(4, 4) = false
* filename = input7.txt - isFull() returns wrong value after 12 sites opened - student isFull(6, 1) = true - reference isFull(6, 1) = false
==> FAILED
Test 18: call all methods in random order until all sites are open, allowing isOpen() to be called on a site more than once (these inputs are prone to backwash) * n = 3 - isFull() returns wrong value after 7 sites opened - student isFull(3, 1) = true - reference isFull(3, 1) = false - failed on trial 3 of 40 * n = 5 - isFull() returns wrong value after 17 sites opened - student isFull(5, 1) = true - reference isFull(5, 1) = false - failed on trial 6 of 20 * n = 7 - isFull() returns wrong value after 34 sites opened - student isFull(7, 1) = true - reference isFull(7, 1) = false - failed on trial 3 of 10 * n = 10 - isFull() returns wrong value after 51 sites opened - student isFull(10, 1) = true - reference isFull(10, 1) = false - failed on trial 1 of 5 * n = 20 - isFull() returns wrong value after 211 sites opened - student isFull(12, 6) = true - reference isFull(12, 6) = false
==> FAILED
