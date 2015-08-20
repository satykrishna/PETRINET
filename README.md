Petri Net Simulator for a Simplified MIPS Processor
===================================================

In this project we created a Petri Net simulator for a simplified MIPS Processor. The model will use colored tokens.  There are 8 Transitions and  three important places (instruction memory, register file, and data memory) of the Petri net model. All the arc carries (consumes) 1 token unless marked otherwise.


Instruction Memory (INM)
-------------------------
The processor to be simulated only supports three types of instructions: Add (ADD), Subtract (SUB), and
Load (LD). At a time step, the place denoted as Instruction Memory (INM) can have up to 8 instruction
tokens.

Sample instruction tokens and equivalent functionality are shown below:
<ADD, R1, R2, R3> R1 = R2 + R3
<SUB, R4, R3, R5> 4 = R3 - R5
<LD, R7, R1, 4> R7 = DataMemory[R1+4]

Register File (RGF)
---------------------
This MIPS processor supports up to 8 registers (R0 through R7). At a time step it can have up to 8 tokens.
The token format is <registername, registervalue>, e.g., <R1, 5>.  The values of registers are 
provide by an input file (registers.txt) with register tokens that we can use to initialize some of the registers.

Data Memory (DAM)
-----------------
This MIPS processor supports up to 8 locations in the data memory. At a time step it can have up to 8
tokens. The token format is <address, value>, e.g., <6, 5> implies that memory address 6 has value 5.

READ
----- 
The READ transition is a slight deviation from Petri net semantics since it does not have any direct access
to instruction tokens. Assume that it knows the top (in-order) instruction in the Instruction Memory
(INM). It checks for the availability of the source operands in the Register File (RGF) for the top
instruction token and passes them to Instruction Buffer (INB) by replacing the source operands with the
respective values.

DECODE
------
The DECODE transition consumes the top (in-order) instruction token from INM and updates the values
of the source registers with the values from RGF (with the help of READ transition, as described above),
and places the modified instruction token in INB.

ISSUE1
------
ISSUE1 transition consumes one arithmetic (ADD or SUB) instruction token (if any) from INB and places it in the Arithmetic Instruction Buffer (AIB).

ISSUE2
------
ISSUE2 transition consumes one load (LD) instruction token (if any) from INB and places it in the Load Instruction Buffer (LIB).

Add - Subtract (ASU)
--------------------
ASU transition performs arithmetic computations (adds or subtracts) as per the instruction token from AIB, and places the result in the result buffer (REB). The format of the token in result buffer is same as a token in RGF i.e., <destination-register-name, value>.

Address Calculation (ADDR)
--------------------------
ADDR transition performs effective address calculation for the load instruction by adding the offset with the source operand. It produces a token as < destination-register-name, data memory address> and places it in the address buffer (ADB).

LOAD
-------
The LOAD transition consumes a token from ADB and gets the data from the data memory for the corresponding address. Assume that you will always have the data for the respective address in the data memory in the same time step. It places the data value (result of load) in the result buffer (REB). The format of the token in result buffer is same as a token in RGF i.e., <destination-register-name, data value>.

WRITE
-----
Transfers the result (one token) from the Result Buffer (REB) to the register file (RGF). If there are more than one token in REB in a time step, the WRITE transition writes the token that belongs to the in-order first instruction. You do not have to worry about write-after-write hazard. We will not provide testcases that produces hazards or exceptions.
<opcode,dest,src1,src2>


