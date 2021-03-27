#------------------------ Tool Specifications & Options ----------------------

COMPILER  =  lcc

CC        =  "C:\Program Files\MATLAB\R2020b\sys\lcc64\lcc64\bin\lcc64.exe"
LD        =  "C:\Program Files\MATLAB\R2020b\sys\lcc64\lcc64\bin\lcclnk64.exe"
LIBCMD    =  "C:\Program Files\MATLAB\R2020b\sys\lcc64\lcc64\bin\lcclib64.exe"
CFLAGS    =  -dll -noregistrylookup  -c -Zp8 -DLCC_WIN64 -DMATLAB_MEX_FILE -nodeclspec
LDFLAGS   =  -s -dll -entry LibMain 5ajwzOpKdVYC3TnvBror7D_cclib.def -L"C:\Program Files\MATLAB\R2020b\sys\lcc64\lcc64\lib64"

OBJECTS = \
	   vel_float.obj \
	   pos_float.obj \
	   vel_fixed.obj \
	   pos_fixed.obj \
	   slcc_interface_5ajwzOpKdVYC3TnvBror7D.obj \
	   lccstub.obj \

STATICLIBS = \

#------------------------------ Include/Lib Path ------------------------------

INCLUDE_PATH = \
     -I"c:\program files\matlab\r2020b\extern\include" \
     -I"c:\program files\matlab\r2020b\simulink\include" \
     -I"d:\skola\realtid\frtn01\lab3\slprj\_slcc\5ajwzopkdvyc3tnvbror7d" \
     -I"d:\skola\realtid\frtn01\lab3\include" \
     -I"d:\skola\realtid\frtn01\lab3" \
     -I"d:\skola\realtid\frtn01\lab3\src" \
     -I"c:\program files\matlab\r2020b\sys\lcc64\lcc64\include64" \
     -I"c:\program files\matlab\r2020b\sys\lcc64\lcc64\mex" \

#--------------------------------- Rules --------------------------------------

5ajwzOpKdVYC3TnvBror7D_cclib.dll : $(MAKEFILE) $(OBJECTS)
	$(LD) $(LDFLAGS) /OUT:5ajwzOpKdVYC3TnvBror7D_cclib.dll $(OBJECTS)  $(STATICLIBS) "C:\Program Files\MATLAB\R2020b\extern\lib\win64\microsoft\libmex.lib" "C:\Program Files\MATLAB\R2020b\extern\lib\win64\microsoft\libmx.lib"
vel_float.obj :	d:\skola\realtid\frtn01\lab3\src\vel_float.c
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "d:\skola\realtid\frtn01\lab3\src\vel_float.c"
pos_float.obj :	d:\skola\realtid\frtn01\lab3\src\pos_float.c
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "d:\skola\realtid\frtn01\lab3\src\pos_float.c"
vel_fixed.obj :	d:\skola\realtid\frtn01\lab3\src\vel_fixed.c
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "d:\skola\realtid\frtn01\lab3\src\vel_fixed.c"
pos_fixed.obj :	d:\skola\realtid\frtn01\lab3\src\pos_fixed.c
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "d:\skola\realtid\frtn01\lab3\src\pos_fixed.c"
slcc_interface_5ajwzOpKdVYC3TnvBror7D.obj :	D:\Skola\Realtid\FRTN01\Lab3\slprj\_slcc\5ajwzOpKdVYC3TnvBror7D\slcc_interface_5ajwzOpKdVYC3TnvBror7D.c
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "D:\Skola\Realtid\FRTN01\Lab3\slprj\_slcc\5ajwzOpKdVYC3TnvBror7D\slcc_interface_5ajwzOpKdVYC3TnvBror7D.c"
lccstub.obj :	C:\PROGRA~1\MATLAB\R2020b\sys\lcc64\lcc64\mex\lccstub.c
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "C:\Program Files\MATLAB\R2020b\sys\lcc64\lcc64\mex\lccstub.c"
