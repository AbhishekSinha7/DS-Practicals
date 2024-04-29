    String Reverse using corba , idl and java implementation : 

1. Create the all ReverseServer.java , ReverseClient.java , ReverseImpl.java & ReverseModule.idl  files.

2. Run the IDL-to-Java compiler idlj, on the IDL file to create stubs and skeletons. This step assumes that you have included the path to the java/bin directory in your path.

  idlj -fall  ReverseModule.idl
The idlj compiler generates a number of files.
3. Compile the .java files, including the stubs and skeletons (which are in the directory newly created directory). This step assumes the java/bin directory is included in your path.

   javac *.java  ReverseModule/*.java
4. Start orbd. To start orbd from a UNIX command shell, enter :

  	orbd -ORBInitialPort 1050&
5. Start the server. To start the  server from a UNIX command shell, enter :
  java ReverseServer -ORBInitialPort 1050& -ORBInitialHost localhost&

6. Run the client application :
  java ReverseClient -ORBInitialPort 1050 -ORBInitialHost localhost


