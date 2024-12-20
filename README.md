# Christmas Tree

This charming project sprinkles holiday magic into your console! ✨ Watch as a glowing Christmas tree 🌲 comes to life, surrounded by gently falling snowflakes ❄️, creating a cozy winter wonderland right in your terminal. Perfect for adding festive cheer and joy to your coding setup! 🎉

## Features

- Detects the operating system and adjusts the console size accordingly.
- Displays a Christmas tree with customizable height and width.
- Includes decorative elements like snowflakes.

## Version
Current version: **0.0.1**  
For more information on updates, see the [changelog](CHANGELOG.md).

## Prerequisites

Ensure you have the following installed on your system:

- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase-downloads.html)

## Installation

1. **Clone the Repository**:
   ```bash  
	git clone https://github.com/seoLeir/christmas-tree.git  
   ```
2. **Navigate to the project directory**:
    ```bash
     cd project 
    ``` 
3. **Give Execution Permissions to Maven Wrapper (If Unix System)**:
     ```bash
      chmod +x ./mvnw
     ```
4. **Build the Project with Maven**:
   ```bash
   ./mvnw clean package
   ```
   This command compiles the project, resolves dependencies, and generates a JAR file.

5. **Running the Application**:
   After building the project, you can run the application using the following command:
   ```bash
   java -jar target/christmas-tree-0.0.1.jar
   ```
   The application will display a Christmas tree in the console with randomized snowflakes.

## License
This project is licensed under the GPL-3.0 License. See the [LICENSE](LICENCE.md) file for more details.