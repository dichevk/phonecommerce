Backend for Phone Store Ecommerce Java Web Application implemented using Spring with Swagger API and MVC/Repository pattern

running Swagger from vscode: 

How to Install the Swagger Extension:
1. Open Visual Studio Code.
Go to the Extensions view by clicking on the square icon on the sidebar (or use the shortcut Ctrl+Shift+X).
Search for "Swagger Viewer" in the Extensions Marketplace and install it.
2. Open Swagger File:

Open the Swagger (OpenAPI) file in Visual Studio Code.

3. Launch Swagger Viewer:

Right-click on the opened Swagger file.
From the context menu, select "Open with Swagger Viewer" or use the command palette (Ctrl+Shift+P) and type "Swagger Viewer: Show Preview".
This should open a new tab in your default web browser with the Swagger UI displaying your API documentation. You can interact with the endpoints and test your API directly from this interface.

If you're using a specific Swagger-related extension or tool, the steps might slightly vary. But generally, the process involves opening the Swagger file and using the extension to visualize it in a browser-based UI for testing and exploration.

<b>project prerequisites>/b>: 

1. Ensure java is installed and configured on your local machine - https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html
   I Windows:
    Open System Properties:
    
    Right-click on "This PC" or "My Computer" and select "Properties".
    Click on "Advanced system settings" on the left sidebar.
    Set Environment Variables:
    
    In the System Properties window, click on the "Environment Variables" button.
    Under "System variables", click "New" to add a new variable.
    Set the variable name as JAVA_HOME.
    Set the variable value to the path where your Java Development Kit (JDK) is installed (e.g., C:\Program Files\Java\jdk1.8.0_301).
    Update Path Variable (Optional):
    
    Find the Path variable in the list of system variables and click "Edit".
    Add %JAVA_HOME%\bin at the end of the existing values. This allows you to use Java and other Java-related commands from the command line.
    Apply Changes:
    
    Click "OK" in all open windows to save the changes.
    
    The Java path for Maven is typically set through the JAVA_HOME environment variable in your operating system. Maven uses this variable to locate your Java installation.
    
    Here's how you can set the JAVA_HOME variable:
    
    Open System Properties:
    
    Right-click on "This PC" or "My Computer" and select "Properties".
    Click on "Advanced system settings" on the left sidebar.
    Set Environment Variables:
    
    In the System Properties window, click on the "Environment Variables" button.
    Under "System variables", click "New" to add a new variable.
    Set the variable name as JAVA_HOME.
    Set the variable value to the path where your Java Development Kit (JDK) is installed (e.g., C:\Program Files\Java\jdk1.8.0_301).
    Update Path Variable (Optional):
    
    Find the Path variable in the list of system variables and click "Edit".
    Add %JAVA_HOME%\bin at the end of the existing values. This allows you to use Java and other Java-related commands from the command line.
    Apply Changes:
    
    Click "OK" in all open windows to save the changes.
  II macOS and Linux:
    1. Edit bash_profile or bashrc
    2. Add the following line:export JAVA_HOME=/Library/Java/JavaVirtualMachines/<jdkversion>.jdk/Contents/Home
    3. Reload bash_profile or bashrc
  
    Run source ~/.bash_profile or source ~/.bashrc in the terminal to apply the changes.
    Once you've set the JAVA_HOME variable, you can verify it by running echo %JAVA_HOME% on Windows or echo $JAVA_HOME on macOS/Linux in the terminal. It should display the path you've set for your Java installation.
    
    Open the Terminal.
    Edit your .bash_profile or .bashrc file using a text editor like nano or vim.
  3. Maven Installed: Ensure Maven is installed on your system and configured properly. You can verify by running mvn -version in your terminal or command prompt to check if Maven is installed and the environment variables are set correctly.
  4. Once you've ensured these prerequisites are met, you can run the Maven project that has Swagger integration using the command line: mvn spring-boot:run
