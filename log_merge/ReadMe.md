#Log Merger

A Java program that merges all log files in a directory into a single log file. 

Takes  two command line arguments. 

The first argument specifies an existing directory in the file system where the log files are located. 
The second argument specifies the path to an output file.
e.g. “java <ClassName> c:\temp\logs c:\temp\logs\merged_logs.txt” 


The program recursively scans the input directory for “.log” files. 
The directory may contain log files with the file size ranging from zero bytes to several giga bytes. 
The direcrory may contain other files which are ignored. 

All log files will adhere to the following log format. 
Log entries cannot span multiple lines, 
Each line in the log file constitutes a log entry. 
The line formst is: 
    
    yyyy-MM-dd HH:mm:ss,S  Level – Message\n

e.g. 
    2017-04-26 12:00:04,799  DEBUG - Lorem ipsum dolor sit amet, consectetur adipiscing elit

Log entries in the log files are in the time order.

The program will merges logs from all the log files and writes them to the output file.
The log entries in the output file are in the time order. 

-----------------
# Two Solutions 

#### com.origamisoftware.puzzles.logmerge.apps.
##LogMergeBasic

This version is reads a file into memory and stores it's data in sorted hashmap. 
It is fast, simple, and clear. _It does not rely on the fact that each log file is already sorted by date_
In short works, but will eventually run out of memory if the log files are too numerious or too large 

####com.origamisoftware.puzzles.logmerge.apps.
##LogMerger

To address the short comings of *LogMergeBasic* this versio of te program only reads one line of each log file at a time 
before writing to the output file. 

To be able to do this, this solution *does* rely on the log files being individually sorted by date. 
This allows the program to read a line from each input file and sort those lines by date and then write the earliest 
entry to the output file. Then, the next line from the file that the line was just written is ready and compared to the other lines. 
The earliest line is written to the output file. This process is repeated until all input is read.  In summary 

0. read a line from all files

1. sort lines by date
2. write earliest entry 
3. read line from file whose line was just written or remove if no data remains, remove file from list  
4. while there is more data go to step 1

Basically, you can think of each input file as a sorted array. 
This program is combining data from each array into a single array (the output file). 
Since data is read and written one line at a time, only a very large number of input files would cause this program to
run out of resources (memory). 

