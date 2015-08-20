Petri Net Simulator for a Simplified MIPS Processor
===================================================

In this project we created a Petri Net simulator for a simplified MIPS Processor. The model will use colored tokens.  There are 8 Transitions and  three important places (instruction memory, register file, and data memory) of the Petri net model. All the arc carries (consumes) 1 token unless marked otherwise.


Instruction Memory (INM)
-------------------------
The processor to be simulated only supports three types of instructions: Add (ADD), Subtract (SUB), and
Load (LD). At a time step, the place denoted as Instruction Memory (INM) can have up to 8 instruction
tokens.

Register File (RGF)
---------------------
This MIPS processor supports up to 8 registers (R0 through R7). At a time step it can have up to 8 tokens.
The token format is <registername, registervalue>, e.g., <R1, 5>.  We will
provide an input file (registers.txt) with register tokens that you can use to initialize some of the registers.

Data Memory (DAM)
-----------------
This MIPS processor supports up to 8 locations in the data memory. At a time step it can have up to 8
tokens. The token format is <address, value>, e.g., <6, 5> implies that memory address 6 has value 5.
