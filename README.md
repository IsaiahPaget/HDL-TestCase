# HDL-TestCase

this program makes a tst file that can be used in hardware testing suite to evaluate HDL, in the case where a gate has many many inputs it would be really tedious to make all the permutations of a truth table so I wrote this instead.

In the case where you are testing an And gate this is the output:

load And.hdl,
output-file And.out,

set a 0, 
set b 0, 
eval,
output;

set a 0, 
set b 1, 
eval,
output;

set a 1, 
set b 0, 
eval,
output;

set a 1, 
set b 1, 
eval,
output;
