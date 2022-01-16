# Ledger Co : Geektrust backend challenge solution

 - Problem can be found here
 
https://www.geektrust.in/coding-problem/backend/ledger-co

 - Currently Testcases arent fixed completely. Working on it. PRs for the resolution are welcome.
 
 - This is the TestCase:
 
 ```
	LOAN IDIDI Dale 4000 3 4
	LOAN MBI Dale 10000 3 7
	PAYMENT MBI Dale 2000 0
	BALANCE IDIDI Dale 3
	BALANCE IDIDI Dale 0
	BALANCE MBI Dale 0
	BALANCE IDIDI Dale 12
	BALANCE MBI Dale 4
	BALANCE MBI Dale 30
 ```
 
 The Expected Output:
 
 ```
	IDIDI Dale 375 33
	IDIDI Dale 0 36
	MBI Dale 2000 30
	IDIDI Dale 1500 24
	MBI Dale 3348 26 
	MBI Dale 12100 0
 ```
 
 My Current Output:
 
 ```
	IDIDI Dale 375 33
	IDIDI Dale 0 36
	MBI Dale 2000 31
	IDIDI Dale 1500 24
	MBI Dale 3348 27
	MBI Dale 12110 1
 ```
