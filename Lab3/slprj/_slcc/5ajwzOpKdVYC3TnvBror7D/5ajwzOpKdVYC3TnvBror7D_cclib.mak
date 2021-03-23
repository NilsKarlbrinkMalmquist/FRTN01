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
     -I"c:\users\morri\google drive\lth år 5\realtidssystem\lab3\slprj\_slcc\5ajwzopkdvyc3tnvbror7d" \
     -I"c:\users\morri\google drive\lth år 5\realtidssystem\lab3\include" \
     -I"c:\users\morri\google drive\lth år 5\realtidssystem\lab3" \
     -I"c:\users\morri\google drive\lth år 5\realtidssystem\lab3\src" \
     -I"c:\program files\matlab\r2020b\sys\lcc64\lcc64\include64" \
     -I"c:\program files\matlab\r2020b\sys\lcc64\lcc64\mex" \

#--------------------------------- Rules --------------------------------------

5ajwzOpKdVYC3TnvBror7D_cclib.dll : $(MAKEFILE) $(OBJECTS)
	$(LD) $(LDFLAGS) /OUT:5ajwzOpKdVYC3TnvBror7D_cclib.dll $(OBJECTS)  $(STATICLIBS) "C:\Program Files\MATLAB\R2020b\extern\lib\win64\microsoft\libmex.lib" "C:\Program Files\MATLAB\R2020b\extern\lib\win64\microsoft\libmx.lib"
vel_float.obj :	c:\users\morri\GOOGLE~1\LTHR5~1\REALTI~1\lab3\src\VEL_FL~1.C
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "c:\users\morri\google drive\lth år 5\realtidssystem\lab3\src\vel_float.c"
pos_float.obj :	c:\users\morri\GOOGLE~1\LTHR5~1\REALTI~1\lab3\src\POS_FL~1.C
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "c:\users\morri\google drive\lth år 5\realtidssystem\lab3\src\pos_float.c"
vel_fixed.obj :	c:\users\morri\GOOGLE~1\LTHR5~1\REALTI~1\lab3\src\VEL_FI~1.C
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "c:\users\morri\google drive\lth år 5\realtidssystem\lab3\src\vel_fixed.c"
pos_fixed.obj :	c:\users\morri\GOOGLE~1\LTHR5~1\REALTI~1\lab3\src\POS_FI~1.C
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "c:\users\morri\google drive\lth år 5\realtidssystem\lab3\src\pos_fixed.c"
slcc_interface_5ajwzOpKdVYC3TnvBror7D.obj :	C:\Users\morri\GOOGLE~1\LTHR5~1\REALTI~1\Lab3\slprj\_slcc\5AJWZO~1\SLCC_I~1.C
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "C:\Users\morri\Google Drive\LTH År 5\Realtidssystem\Lab3\slprj\_slcc\5ajwzOpKdVYC3TnvBror7D\slcc_interface_5ajwzOpKdVYC3TnvBror7D.c"
lccstub.obj :	C:\PROGRA~1\MATLAB\R2020b\sys\lcc64\lcc64\mex\lccstub.c
	$(CC) $(CFLAGS) $(INCLUDE_PATH) "C:\Program Files\MATLAB\R2020b\sys\lcc64\lcc64\mex\lccstub.c"
